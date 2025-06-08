document.addEventListener("DOMContentLoaded", () => {

    const hospitalSelect = document.getElementById("hospital");
    const bloodBankSelect = document.getElementById("bloodBank");
    const bloodTypeSelect = document.getElementById("bloodtype");
    const quantityInput = document.getElementById("quantity");

    // loading hospitals from api to choose from
    fetch("/api/hospital")
        .then(res => res.json())
        .then(hospitals => {
            hospitals.forEach(h => {
                const option = document.createElement("option");
                option.value = h.id;
                option.text = `${h.name} (ID: ${h.id})`;
                hospitalSelect.appendChild(option);
            });
        });

    // loading blood banks from api to choose from
    fetch("/api/bloodbanks")
        .then(res => res.json())
        .then(banks => {
            banks.forEach(b => {
                const option = document.createElement("option");
                option.value = b.id;
                option.text = `${b.name} (ID: ${b.id})`;
                bloodBankSelect.appendChild(option);
            });
        });


    document.getElementById("createRequest").addEventListener("submit", function (e) {
        e.preventDefault();

        const quantity = Number(quantityInput.value);
        if (quantity <= 0) {
            alert("Requested quantity must be greater than 0.");
            return;
        }

        const request = {
            hospital: { id: Number(hospitalSelect.value) },
            bloodBank: { id: Number(bloodBankSelect.value) },
            bloodType: { id: Number(bloodTypeSelect.value) },
            quantityInLiters: Number(quantityInput.value),
        };

        fetch('/api/request', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(request)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to register request.");
                }
                alert("Blood request registered.");
                window.history.back();
            })
            .catch(error => {
                console.error(error);
                alert("Error registering Request.");
            });
    });
})
