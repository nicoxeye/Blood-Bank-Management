function renderDonors(data) {
    const tbody = document.getElementById('donors-table-body');
    tbody.innerHTML = "";
    data.forEach(donor => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${donor.id}</td>
            <td>${donor.name ?? ''}</td>
            <td>${donor.surname ?? ''}</td>
            <td>${getBloodType(donor) ?? ''}</td>
            <td>${donor.dateOfBirth ?? ''}</td>
            <td>${donor.gender ?? ''}</td>
            <td>${donor.address?.country ?? ''}, ${donor.address?.city ?? ''}, ${donor.address?.street ?? ''}</td>
            <td>${donor.phoneNumber ?? ''}</td>
            <td>
              <button class="btn btn-sm btn-dark" onclick="">Update</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}


function fetch_donors() {
    fetch('/api/donors')
        .then(response => response.json())
        .then(data => renderDonors(data))
        .catch(error => console.error('Error fetching donors:', error));
}


function searchDonors() {
    const surname = document.getElementById('search-surname').value.trim();
    const city = document.getElementById('search-city').value.trim();

    if (!city && !surname) {
        alert('Please enter a surname or city to search.');
        return;
    }

    let url = '/api/donors/search?';

    if (surname) {
        url += `surname=${encodeURIComponent(surname)}&`;
    }
    else if (city) {
        url += `city=${encodeURIComponent(city)}&`;
    }

    fetch(url)
        .then(res => res.json())
        .then(data => renderDonors(data))
        .catch(console.error);
}


function getBloodType(donor) {
    return `${donor.bloodType.bloodGroup}${donor.bloodType.protein === 'POSITIVE' ? '+' : '-'}`;
}

