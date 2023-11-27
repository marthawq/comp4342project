package com.example.mobile;

public class LoginTest {
    private final String existingEmails = "1234@gmail.com";
    private final String existingPasswords = "12341234";

    public boolean validLoginInput(String email, String password) {
        if(existingEmails.equals(email) && existingPasswords.equals(password)){
            return true;
        }
        return false;
    }
}
