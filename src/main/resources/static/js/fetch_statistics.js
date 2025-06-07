function fetch_statistics() {
    document.addEventListener("DOMContentLoaded", function () {
        fetch("/api/statistics")
            .then(res => res.json())
            .then(data => {
                document.getElementById("totalDonations").textContent = data.totalDonations;
                document.getElementById("totalDonors").textContent = data.totalDonors;
                document.getElementById("totalBloodLiters").textContent = data.totalLiters.toFixed(2);

                const table = document.getElementById("inventoryTable");

                Object.entries(data.inventoryByType).forEach(([bloodType, quantity]) => {
                    const row = document.createElement("tr");
                    row.innerHTML = `<td>${bloodType}</td><td>${quantity.toFixed(2)}</td>`;
                    table.appendChild(row);
                });
            });
    });

}
