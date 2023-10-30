package com.example.socialmediahub.Models;

public class VIPUser extends User {
    /**
     * User Constructor
     *
     * @param username  Unique username of user
     * @param password  Users password
     * @param firstname Users first name
     * @param lastname  Users last name
     * @param b
     */
    public VIPUser(String username, String password, String firstname, String lastname, boolean b) {
        super(username, password, firstname, lastname);
    }
}