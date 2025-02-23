package com.example.class_recycle_view;

public class User {
    private String username;
    private String fullname;
    private String email;

    public User(String userName, String email, String fullName) {
        this.username = userName;
        this.email = email;
        this.fullname = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
    }
}
