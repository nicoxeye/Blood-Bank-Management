document.addEventListener("DOMContentLoaded", () => {
    const donorSelect = document.getElementById("donor");
    const bloodBankSelect = document.getElementById("bloodBank");
    const dateInput = document.getElementById("date");

    // loading donors from api to choose from
    fetch("/api/donors")
        .then(res => res.json())
        .then(donors => {
            donors.forEach(d => {
                const option = document.createElement("option");
                option.value = d.id;
                option.text = `${d.name} ${d.surname} (ID: ${d.id})`;
                donorSelect.appendChild(option);
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


    document.getElementById("createDonationPast").addEventListener("submit", function (e) {
        e.preventDefault();

        const donorId = donorSelect.value;
        const bloodBankId = bloodBankSelect.value;
        const date = dateInput.value;

        fetch(`/api/donations/register-with-date?date=${date}&donorId=${donorId}&bloodBankId=${bloodBankId}`, {
            method: "POST",
        })
            .then(response => {
                if (!response.ok) throw new Error("Failed to register donation.");
                alert("Donation registered.");
                window.history.back();
            })
            .catch(error => {
                console.error(error);
                alert("Error registering donation.");
            });
    });

});
