function create_patient() {
    document.getElementById('patientForm').addEventListener('submit', function (e) {
        e.preventDefault();

        const patient = {
            name: document.getElementById('name').value,
            surname: document.getElementById('surname').value,
            bloodType: {
                id: parseInt(document.getElementById('bloodtype').value)
            },
            dateOfBirth: document.getElementById('birthday').value,
            gender: document.getElementById('gender').value,
            address: {
                country: document.getElementById('country').value,
                city: document.getElementById('city').value,
                street: document.getElementById('street').value,
                zipcode: document.getElementById('zipcode').value
            },
            phoneNumber: document.getElementById('phone').value
        };

        fetch('/api/patients', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(patient)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Something went wrong. Try refreshing the page.");
                }
                alert("Patient created successfully.");
                window.history.back();
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Failed to create Patient.");
            });
    });
}

document.addEventListener("DOMContentLoaded", create_patient);
