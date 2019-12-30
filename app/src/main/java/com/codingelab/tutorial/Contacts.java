package com.codingelab.tutorial;

/**
 * done by Ibrahim on 25/12/2019.
 */

public class Contacts {

    @Override
    // returns the string representation of the object.
    // method returns a string equal to the value of: getClass()
    public String toString() {
        return "Contacts{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
        //special method that is used to initialize objects. The constructor is called when an object of a class is created.
    public Contacts(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;
    private String phone;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contacts(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }



        //Empty constructor is called when an object of a class is created.
    public Contacts() {

    }

}
