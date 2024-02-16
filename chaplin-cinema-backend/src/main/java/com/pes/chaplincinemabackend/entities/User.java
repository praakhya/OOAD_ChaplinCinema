package com.pes.chaplincinemabackend.entities;

public class User {
    String username = "";
    String password = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String login(String username, String password) {
        return "";
    }
    void logout(String username) {

    }
    void signup(String username, String password) {

    }
}
