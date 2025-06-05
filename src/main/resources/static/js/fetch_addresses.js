function renderAddresses() {
    fetch('/api/addresses')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('address-table-body');
            data.forEach(address => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${address.id}</td>
                    <td>${address.street ?? ''}</td>
                    <td>${address.city ?? ''}</td>
                    <td>${address.country ?? ''}</td>
                    <td>${address.zipcode ?? ''}</td>
                    <td>
                      <button class="btn btn-sm btn-dark" onclick="deleteAddress(${address.id})">Delete</button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching addresses:', error));
}

function deleteAddress(id) {
    if (confirm("Are you sure you want to delete this address?")) {
        fetch(`/api/addresses/${id}`, { method: 'DELETE' })
            .then(() => {
                document.getElementById('address-table-body').innerHTML = ""; // clearing table
                renderAddresses(); // reloading the page
            })
            .catch(error => console.error('Delete failed:', error));
    }
}

