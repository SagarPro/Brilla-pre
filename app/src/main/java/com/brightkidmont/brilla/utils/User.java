package com.brightkidmont.brilla.utils;

public class User {

    public String fatherName;
    public String motherName;
    public String email;
    public String phone;
    public String childName;
    public String dob;
    public String preSchool;
    public String level;
    public String location;
    public String password;
    public String acceptance;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    //other constructors
    public User(String fatherName, String motherName, String email, String childName, String dob, String preSchool, String level, String location) {
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.email = email;
        this.childName = childName;
        this.dob = dob;
        this.preSchool = preSchool;
        this.level = level;
        this.location = location;
    }

    public User(String fatherName, String motherName, String email, String phone, String childName, String dob, String preSchool, String level, String location, String password, String acceptance) {
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.email = email;
        this.phone = phone;
        this.childName = childName;
        this.dob = dob;
        this.preSchool = preSchool;
        this.level = level;
        this.location = location;
        this.password = password;
        this.acceptance = acceptance;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPreSchool() {
        return preSchool;
    }

    public String getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPreSchool(String preSchool) {
        this.preSchool = preSchool;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
