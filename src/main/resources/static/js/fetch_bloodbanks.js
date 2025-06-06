function renderBloodbanks(data) {
    const tbody = document.getElementById('bloodbanks-table-body');
    tbody.innerHTML = "";
    data.forEach(bloodbank => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${bloodbank.id}</td>
            <td>${bloodbank.name ?? ''}</td>
            <td>${bloodbank.address?.country ?? ''}, ${bloodbank.address?.city ?? ''}, ${bloodbank.address?.street ?? ''}</td>
            <td>${bloodbank.donationsCount ?? 0}</td>
            <td>${summarizeInventories(bloodbank.bloodInventories)}</td>
        `;
        tbody.appendChild(row);
    });
}


function fetch_bloodbanks() {
    fetch('/api/bloodbanks')
        .then(response => response.json())
        .then(data => renderBloodbanks(data))
        .catch(error => console.error('Error fetching Blood Banks:', error));
}


function searchBloodBanks(){
    const city = document.getElementById('search-city').value.trim();

    if (!city) {
        alert('Please enter a city to search.');
        return;
    }

    const url = `/api/bloodbanks/city/${encodeURIComponent(city)}`;

    fetch(url)
        .then(res => res.json())
        .then(data => renderBloodbanks(data))
        .catch(console.error);
}


function summarizeInventories(inventories) {
    if (!Array.isArray(inventories) || inventories.length === 0) return 'No data';

    const summary = {};

    inventories.forEach(inv => {
        if (!inv.bloodType || typeof inv.quantityInLiters !== 'number') return;

        const type = `${inv.bloodType.bloodGroup}${inv.bloodType.protein === 'POSITIVE' ? '+' : '-'}`;
        summary[type] = (summary[type] || 0) + inv.quantityInLiters;
    });

    return Object.entries(summary)
        .map(([type, liters]) => `${type}: ${liters.toFixed(2)}L`).join(', ');
}



