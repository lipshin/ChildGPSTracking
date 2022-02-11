package com.example.ChildGPSTracking.Models;

public class Person {

    String name;
    String id;
    String email;
    String password;
    Boolean isAdult;

    public Person(String name, String id, String email, Boolean isAdult) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.isAdult = isAdult;
    }

    public Person() {

    }

    public Boolean isAdult() {
        return isAdult;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
