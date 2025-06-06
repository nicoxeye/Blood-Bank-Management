function renderHospitals(data) {
    const tbody = document.getElementById('hospitals-table-body');
    tbody.innerHTML = "";

    const hospitals = data.filter(hospital=> hospital && typeof hospital === 'object' && hospital.id);

    hospitals.forEach(hospital => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${hospital.id}</td>
            <td>${hospital.name ?? ''}</td>
            <td>${hospital.address?.country ?? ''}, ${hospital.address?.city ?? ''}, ${hospital.address?.street ?? ''}</td>
            <td>${hospital.contactEmail ?? ''}</td>
            <td>${hospital.phoneNumber ?? ''}</td>
        `;
        tbody.appendChild(row);
    });
}


function fetch_hospitals() {
    fetch('/api/hospital')
        .then(response => response.json())
        .then(data => {
            renderHospitals(data);
        })
        .catch(error => console.error('Error fetching hospitals:', error));
}


function searchHospitals(){
    const city = document.getElementById('search-city').value.trim();

    if (!city) {
        alert('Please enter a city to search.');
        return;
    }

    const url = `/api/hospital/city/${encodeURIComponent(city)}`;

    fetch(url)
        .then(res => res.json())
        .then(data => renderHospitals(data))
        .catch(console.error);

}

