package com.example.mobile;

import java.util.List;

public class RegisterTest {
    private final List<String> existingEmails = List.of("qwer1234@gmail.com", "1234@gmail.com");

    public boolean validRegisterInput(String username, String email, String password) {
        if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
            return false;
        }
        if(existingEmails.contains(email)){
            return false;
        }
        return true;
    }
}
