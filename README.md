# BLOOD BANK MANAGEMENT
first draft of the database, will update it with any changes

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
