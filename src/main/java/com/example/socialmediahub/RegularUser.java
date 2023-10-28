package com.example.socialmediahub;
public class RegularUser extends User{
    /**
     * User Constructor
     *
     * @param username  Unique username of user
     * @param password  Users password
     * @param firstname Users first name
     * @param lastname  Users last name
     */
    public RegularUser(String username, String password, String firstname, String lastname) {
        super(username, password, firstname, lastname);
    }

}