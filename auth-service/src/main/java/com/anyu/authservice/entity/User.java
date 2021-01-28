package com.anyu.authservice.entity;

public class User {

    private String username;
    private String password;
    private String level;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public User setLevel(String level) {
        this.level = level;
        return this;
    }
}
