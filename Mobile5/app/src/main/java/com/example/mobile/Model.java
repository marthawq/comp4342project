package com.example.mobile;

public class Model {

    private final Account newUser;
    Model(){
        this.newUser = new Account();
    }

    public Account getNewUser() {
        return newUser;
    }


}
