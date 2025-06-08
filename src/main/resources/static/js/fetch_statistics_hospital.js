function fetch_statistics() {
    document.addEventListener("DOMContentLoaded", function () {
        fetch("/api/hospitalstatistics")
            .then(res => res.json())
            .then(data => {
                document.getElementById("totalPatients").textContent = data.totalPatients;
                document.getElementById("totalRequests").textContent = data.totalRequests;

                const tbody = document.getElementById("requestStats");

                tbody.innerHTML = "";

                Object.entries(data.requestStats).forEach(([status, quantity]) => {
                    const row = document.createElement("tr");
                    row.innerHTML = `<td>${status}</td><td>${quantity}</td>`;
                    tbody.appendChild(row);
                });

            });
    });

}
