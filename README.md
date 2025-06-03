# BLOOD BANK MANAGEMENT 🩸
<pre>
  A simple web app back-end for a Blood Bank System.
      In the future I plan to add React to the project to create a front-end and visualize the web app :]

  In its default configuration, this project uses an data-file database (H2) which gets populated at startup with data. The h2 console is exposed at http://localhost:8080/h2-console.
    There are 9 model classes (entities) in total, which most of them have some sort of controller.
</pre>

### features +++ 🩸
- address: fetching all and deletion by id [additional, for viewing; in real only would be available for admin to see]
- bloodbank: fetching all, getting one by id, creating, deleting, updating
- donation: fetching all, getting one by id, getting all donations of a donor id, registering donation (with date of now), registering donation with a given date, updating a donation
- donor: fetching all, getting one by id, getting donor by a specifed param f.e [api/donors/search?city=LONDON], sorting by given value [name, lastname etc.], creating donor, updating and deletion
- hospital: fetching all, getting one by id, creating a hospital, updating and deletion
- patient: fetching all, getting one by id, getting patient by a specifed param f.e [api/patients/search?name=JANE], sorting by given value, creation a patient, updating and deletion
- requests: fetching all, getting one by id, creating, updating and deletion
- bloodinventories: fetching all, getting blood stock for a specified blood bank and blood id: [api/bloodinventory/bloodbank/52/bloodtype/7]
  
|+ statistics which include how much of blood quantity there is total in all of blood banks and how many requests have been assigned a specified status and grouped by it :]

### todo +++ in the future
- implementation of frontend using react or thymeleaf
- additional sorting when it is needed

## PREVIEW OF LOADED DATA + ONES ADDED BY ME WHILE TESTING THE CONTROLLERS;
![image](https://github.com/user-attachments/assets/cea78778-4e65-4871-bbee-a911f8b69420)

![image](https://github.com/user-attachments/assets/0c658c7b-47ef-41e5-90ba-eac80d2d2e6d)

![image](https://github.com/user-attachments/assets/92d99e44-0a76-4e2a-b531-76250b280ca6)

![image](https://github.com/user-attachments/assets/f69e44a6-27dd-47b2-88c4-538b8ddd6a8d)


## PREVIEW OF TABLES (the relationships may be wrong)
  done first on this project and it is more as a visualization than implementation
  
created on https://dbdiagram.io
![Untitled](https://github.com/user-attachments/assets/fba3e7bb-d2da-475c-a53a-d8c533fd89bc)
<pre>
Table Address {
  id integer [primary key]
  city string
  country string
  street string
  zipcode string
}

Table Blood_Bank {
  address_id integer
  id integer [primary key]
  name string
}

Table Blood_Inventory {
  quantity_in_liters double
  bloodbank_id integer
  bloodtype_id integer
  id integer [primary key]
}

Table Blood_Type {
  id integer [primary key]
  blood_group string
  protein string
}

Table Donation {
  date date
  bloodbank_id integer
  bloodtype_id integer
  donor_id integer
  id integer [primary key]
}

Table Donor {
  dateofbirth date
  gender integer
  address_id integer
  bloodtype_id integer
  id integer [primary key]
  name string 
  surname string
  phonenumber string
}

Table Hospital {
  address_id integer
  id integer [primary key]
  contact_email string
  name string
  phonenumber string
}

Table Patient {
  dateofbirth date
  gender integer
  address_id integer
  bloodtype_id integer
  id integer [primary key]
  name string
  surname string
  phonenumber string
}

Table Request {
  quantity_in_liters double
  requestdate date
  bloodbank_id integer
  bloodtype_id integer
  hospital_id integer
  id integer [primary key]
  status string
}


Ref address_bloodbank: Address.id > Blood_Bank.address_id
Ref bloodinventory_bloodbank: Blood_Bank.id > Blood_Inventory.bloodbank_id
Ref bloodinventory_bloodtype: Blood_Type.id > Blood_Inventory.bloodtype_id
Ref bloodbank_donation: Blood_Bank.id > Donation.bloodbank_id
Ref bloodtype_donation: Blood_Type.id > Donation.bloodtype_id
Ref donor_donation: Donor.id > Donation.donor_id
Ref address_donor: Address.id > Donor.address_id
Ref bloodtype_donor: Blood_Type.id > Donor.bloodtype_id
Ref address_hospital: Address.id > Hospital.address_id
Ref address_patient: Address.id > Patient.address_id
Ref bloodtype_patient: Blood_Type.id > Patient.bloodtype_id
Ref bloodbank_request: Blood_Bank.id > Request.bloodbank_id
Ref bloodtype_request: Blood_Type.id > Request.bloodtype_id
Ref hospital_request: Hospital.id > Request.hospital_id
</pre>
