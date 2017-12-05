package com.everi.xview.util;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public class Validations {

    //STRING VALIDATION
    public boolean checkForValidString(String stringToTest){
        return !(stringToTest == null || stringToTest.trim().length() == 0);
    }

    //EMAIL VALIDATION
    public boolean checkForValidEmail(String email) {
        return (email.contains("@") && email.contains(".") && !email.isEmpty());
    }

    //PASSWORD VALIDATION
    public boolean checkForValidPassword(String password) {
        return !(password.equalsIgnoreCase("") || password.isEmpty() || password.length()<6);
    }

}
