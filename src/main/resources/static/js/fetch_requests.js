let allRequests = [];
let ascending = true;

function renderRequests(data) {
    const tbody = document.getElementById('requests-table-body');
    tbody.innerHTML = "";

    data.forEach(request => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${request.id}</td>
            <td>${request.requestDate ?? ''}</td>
            <td>${request.hospital?.id ?? ''}</td>
            <td>${request.hospital?.name ?? ''}</td>
            <td>${request.bloodBank?.id ?? ''}</td>
            <td>${request.bloodBank?.name ?? ''}</td>
            <td>${getBloodType(request) ?? ''}</td>
            <td>${request.quantityInLiters ?? ''}</td>
            <td>${request.status ?? ''}</td>
        `;
        tbody.appendChild(row);
    });
}


function fetch_requests() {
    fetch('/api/request')
        .then(response => response.json())
        .then(data => {
            allRequests = data;
            renderRequests(allRequests);

            const header = document.getElementById("dateHeader");
            header.textContent = "Date ⬍";
        })
        .catch(error => console.error('Error fetching requests:', error));
}


function getBloodType(request) {
    return `${request.bloodType.bloodGroup}${request.bloodType.protein === 'POSITIVE' ? '+' : '-'}`;
}


function sortByDate() {
    allRequests.sort((a, b) => {
        const dateA = new Date(a.requestDate);
        const dateB = new Date(b.requestDate);
        return ascending ? dateA - dateB : dateB - dateA;
    });

    const header = document.getElementById("dateHeader");
    header.textContent = `Date ${ascending ? '▲' : '▼'}`;

    ascending = !ascending;
    renderRequests(allDonations);
}

