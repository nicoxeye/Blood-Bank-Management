function renderPatients(data) {
    const tbody = document.getElementById('patients-table-body');
    tbody.innerHTML = "";
    data.forEach(patient => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${patient.id}</td>
            <td>${patient.name ?? ''}</td>
            <td>${patient.surname ?? ''}</td>
            <td>${getBloodType(patient) ?? ''}</td>
            <td>${patient.dateOfBirth ?? ''}</td>
            <td>${patient.gender ?? ''}</td>
            <td>${patient.address?.country ?? ''}, ${patient.address?.city ?? ''}, ${patient.address?.street ?? ''}</td>
            <td>${patient.phoneNumber ?? ''}</td>
        `;
        tbody.appendChild(row);
    });
}


function fetch_patients() {
    fetch('/api/patients')
        .then(response => response.json())
        .then(data => renderPatients(data))
        .catch(error => console.error('Error fetching patients:', error));
}


function searchPatients() {
    const surname = document.getElementById('search-surname').value.trim();
    const city = document.getElementById('search-city').value.trim();

    if (!city && !surname) {
        alert('Please enter a surname or city to search.');
        return;
    }

    let url = '/api/patients/search?';

    if (surname) {
        url += `surname=${encodeURIComponent(surname)}&`;
    }
    else if (city) {
        url += `city=${encodeURIComponent(city)}&`;
    }

    fetch(url)
        .then(res => res.json())
        .then(data => renderPatients(data))
        .catch(console.error);
}


function getBloodType(patient) {
    return `${patient.bloodType.bloodGroup}${patient.bloodType.protein === 'POSITIVE' ? '+' : '-'}`;
}

