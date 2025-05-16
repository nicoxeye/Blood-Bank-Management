package com.app.bloodbank.tests;

import com.app.bloodbank.model.*;
import java.time.LocalDate;

public class PatientConsoleTest {
    public static void main(String[] args) {
        try {
            Patient p1 = new Patient("Name", "Surname", LocalDate.of(2000, 2, 2), "MALE", new Address("USA", "California", "Street", "ZIPCODE"), "123123123");
            System.out.println("Patient 1: " + p1);

        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
