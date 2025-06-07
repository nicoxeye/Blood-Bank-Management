function create_donor() {
    document.getElementById('donorForm').addEventListener('submit', function (e) {
        e.preventDefault();

        const donor = {
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

        fetch('/api/donors', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(donor)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Something went wrong. Try refreshing the page.");
                }
                alert("Donor created successfully.");
                window.history.back();
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Failed to create donor.");
            });
    });
}

document.addEventListener("DOMContentLoaded", create_donor);
