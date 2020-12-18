package com.example.bookshopfirebase;

public class UserDetails {

    private String userId,username,password,phone,email;

    public UserDetails(String username, String password, String phone, String email) {

        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
    public UserDetails() {
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
