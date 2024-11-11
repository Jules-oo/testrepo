function onSearchSubmit(event) {
	event.preventDefault();

	$.ajax({
		url: '/home/search',
		type: 'POST',
		headers: {
			'X-Requested-With': 'XMLHttpRequest'
		},
		data: $("#searchForm").serialize(),
		success: function(data) {
			var resContainer = document.getElementById("pippo");
			if (resContainer) {
				resContainer.innerHTML = data;
			} else {
				console.error("Element with id 'res-container' not found");
			}
		}
	});
}

/* -------------------------- FUNZIONI PER LA POPOLAZIONE DELLE SELECT --------------------------------- */
// creazione della gerarchia region -> country -> location -> department
let hierarchy;
document.addEventListener("DOMContentLoaded", () => {
	$.ajax({
		url: '/home/hierarchy',
		type: 'GET',
		success: function(response) {
			hierarchy = mapper(hierarchy, response)
			localStorage.setItem('hierarchy', JSON.stringify(hierarchy))
		}
	})
})

function mapper(h, res) {
	h = res.reduce((acc, dto) => {
		let region = acc.find(r => r.regionId === dto.regionId)
		if (!region) {
			region = {
				regionId: dto.regionId,
				regionName: dto.regionName,
				countries: []
			}
			acc.push(region);
		}

		let country = region.countries.find(c => c.countryId === dto.countryId)
		if (!country) {
			country = {
				countryId: dto.countryId,
				countryName: dto.countryName,
				locations: []
			}
			region.countries.push(country)
		}

		let location = country.locations.find(l => l.locationId === dto.locationId)
		if (!location) {
			location = {
				locationId: dto.locationId,
				city: dto.city,
				departments: []
			}
			country.locations.push(location)
		}

		let department = location.departments.find(d => d.departmentId === dto.departmentId)
		if (!department) {
			department = {
				departmentId: dto.departmentId,
				departmentName: dto.departmentName,
			}
			location.departments.push(department)
		}
		return acc
	}, []);
	return h
}


function cambioTenda(obj) {
	let selectedId = obj.id;
	let hierarchy = JSON.parse(localStorage.getItem("hierarchy"));
	switch (selectedId) {
		case "departmentSelect":
			var dep = obj.value;
			console.log(dep);

			var found = hierarchy.find(r =>
				r.countries.some(c =>
					c.locations.some(l =>
						l.departments.some(d => {
							if (d.departmentName === dep) {
								document.getElementById("locationSelect").value = l.city;
								document.getElementById("countrySelect").value = c.countryName;
								document.getElementById("regionSelect").value = r.regionName;
								return true;
							}
							return false;
						})
					)
				)
			);
			if (found)
				console.log("setup eseguito")
			else {
				clearSelect("locationSelect");
				clearSelect("countrySelect");
				clearSelect("regionSelect");
			}
			break;

		case "locationSelect":

			var loc = obj.value;
			console.log(loc);

			var found = hierarchy.find(r =>
				r.countries.some(c =>
					c.locations.some(l => {
						if (l.city === loc) {
							document.getElementById("regionSelect").value = r.regionName;
							document.getElementById("countrySelect").value = c.countryName;
							// scremare la departments con i dipartimenti appropriati
							populateSelect("departmentSelect", l.departments, "departmentName")

							return true;
						}
						return false;
					})
				)
			)
			if (found)
				console.log("setup eseguito")
			else {
				clearSelect("departmentSelect")
				clearSelect("countrySelect");
				clearSelect("regionSelect");
			}

			break;
		case "countrySelect":
			// behaviour strano ma gli stati non so praticamente mappati, in realtà funziona
			var country = obj.value;
			console.log(country);

			var found = hierarchy.find(r =>
				r.countries.some(c => {
					if (c.countryName === country) {
						document.getElementById("regionSelect").value = r.regionName;
						// scremare le location e i dipartimenti
						populateSelect("locationSelect", c.locations, "city")
						var departments = c.locations.flatMap(l => l.departments);
						populateSelect("departmentSelect", departments, "departmentName")
						return true;
					}
					return false;
				})
			)
			if (found)
				console.log("setup eseguito")
			else {
				clearSelect("departmentSelect")
				clearSelect("locationSelect");
				clearSelect("regionSelect");
			}
			break;
		case "regionSelect":
			// aggiorno tutte le altre select per scremare le opzioni

			clearSelect("departmentSelect")
			clearSelect("locationSelect");
			clearSelect("countrySelect");

			var region = hierarchy.find(r => r.regionName === obj.value);
			populateSelect("countrySelect", region.countries, "countryName");

			var locations = region.countries.flatMap(c => c.locations);
			populateSelect("locationSelect", locations, "city");

			var deps = locations.flatMap(l => l.departments);
			populateSelect("departmentSelect", deps, "departmentName");

			break;
	}

}

// in risalita, setto l'opzione padre incontrata risalendo nell'albero, a cascata fino alla region.
// in discesa, scremo e lascio le opzioni disponibili dipendentemente dal ramo raggiunto nell'albero

function clearSelect(selectId) {
	const select = document.getElementById(selectId);
	select.value = "";
}

function populateSelect(selectId, items, text) {
	console.log("chiamata populate")
	let select = document.getElementById(selectId);
	select.innerHTML = "<option value=''>Select a value</option>"
	items.forEach(i => {
		let option = document.createElement("option");
		option.value = i[text];
		option.text = i[text];
		select.appendChild(option);
	});
}


// per il modale
function injectEmployee(element) {
	
	var firstName = element.closest('tr').dataset.firstname;
	var department = element.closest('tr').dataset.departmentName;
	var salary = element.closest('tr').dataset.salary;
	
	console.log(firstName + ' ' + department + ' ' + salary)
	
}




