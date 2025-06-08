let allDonations = [];
let ascending = true;

function renderDonations(data) {
    const tbody = document.getElementById('donations-table-body');
    tbody.innerHTML = "";

    // filtering only through valid donation objects oopsie
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
        `;
        tbody.appendChild(row);
    });
}



function fetch_donations() {
    fetch('/api/donations')
        .then(response => response.json())
        .then(data => {
            allDonations = data;
            renderDonations(allDonations);

            const header = document.getElementById("dateHeader");
            header.textContent = "Date ⬍";
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


function sortByDate() {
    allDonations.sort((a, b) => {
        const dateA = new Date(a.date);
        const dateB = new Date(b.date);
        return ascending ? dateA - dateB : dateB - dateA;
    });

    const header = document.getElementById("dateHeader");
    header.textContent = `Date ${ascending ? '▲' : '▼'}`;

    ascending = !ascending;
    renderDonations(allDonations);
}

