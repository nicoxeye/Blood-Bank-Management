function renderDonations(data) {
    const tbody = document.getElementById('donations-table-body');
    tbody.innerHTML = "";

    // filtrujemy tylko poprawne obiekty donacji
    const donations = data.filter(donation => donation && typeof donation === 'object' && donation.id);

    donations.forEach(donation => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${donation.id}</td>
            <td>${donation.date ?? ''}</td>
            <td>${donation.donor?.id ?? ''}</td>
            <td>${donation.donor?.name ?? ''}, ${donation.donor?.surname ?? ''}</td>
            <td>${getBloodType(donation) ?? ''}</td>
            <td>${donation.bloodBank?.id ?? ''}</td>
            <td>${donation.bloodBank?.name ?? ''}</td>
            <td>
              <button class="btn btn-sm btn-dark" onclick="">Update</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}



function fetch_donations() {
    fetch('/api/donations')
        .then(response => response.json())
        .then(data => {
            renderDonations(data);
        })
        .catch(error => console.error('Error fetching donations:', error));
}


function searchDonations() {
    const donor_id = document.getElementById('search-donor').value.trim();

    if (!donor_id) {
        alert('Please enter a donor id to search.');
        return;
    }

    let url;

    if (donor_id) {
        url = `/api/donations/donor/${encodeURIComponent(donor_id)}`;
    }

    fetch(url)
        .then(res => res.json())
        .then(data => renderDonations(data))
        .catch(console.error);
}


function getBloodType(donation) {
    return `${donation.bloodType.bloodGroup}${donation.bloodType.protein === 'POSITIVE' ? '+' : '-'}`;
}

