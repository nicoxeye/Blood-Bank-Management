package com.app.bloodbank.tests;

import com.app.bloodbank.model.BloodGroup;
import com.app.bloodbank.model.BloodType;
import com.app.bloodbank.model.RhFactor;

public class BloodTypeConsoleTest {
    public static void main(String[] args) {
        try {
            BloodType bt1 = new BloodType(BloodGroup.A, RhFactor.POSITIVE);
            System.out.println("BloodType 1: " + bt1);

            BloodType bt2 = new BloodType(BloodGroup.AB, RhFactor.NEGATIVE);
            System.out.println("BloodType 2: " + bt2);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
