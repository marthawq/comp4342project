package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

public class Account extends AppCompatActivity {
    private String userName;
    private int highestScore;
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public Account(){

    }

    public Account(String userName, int highestScore) {
        this.userName = userName;
        this.highestScore = highestScore;
    }

    public String get_userName() {
        return userName;
    }

    public void set_userName(String userName) {this.userName = userName;}

    public int getHighest_Score() {
        return highestScore;
    }

    public void setHighest_Score(int highestScore) {

        //TODO change when database add score
        this.highestScore = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
