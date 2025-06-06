function renderDonations(data) {
    const tbody = document.getElementById('donations-table-body');
    tbody.innerHTML = "";
    data.forEach(donation => {
        const row = document.createElement('tr');
        const donor = typeof donation.donor === 'object' ? donation.donor : null;
        row.innerHTML = `
            <td>${donation.id}</td>
            <td>${donation.date ?? ''}</td>
            <td>${donor?.id ?? donation.donor ?? ''}</td>
            <td>${donor.name ?? ''}, ${donor.surname ?? ''}</td>
            <td>${getBloodType(donation) ?? ''}</td>
            <td>${donation.bloodBank.id ?? ''}</td>
            <td>${donation.bloodBank.name ?? ''}</td>
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
            console.log('Donations fetched:', data);
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

