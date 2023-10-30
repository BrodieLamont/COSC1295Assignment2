package com.example.socialmediahub.Models;

/*
 * UserDatabase
 *
 * version 1.0
 *
 * 14/09/2023
 *
 * Copyright notice
 */


import java.sql.*;
import java.util.*;
/**
 * Class for Connecting to MySQL database of Users.
 * Contains method getUserDatabase() to return the database.
 */

public class UserDataBase {
    private Connection connection;

    public UserDataBase() {
    }

    public ResultSet getCredentials(String username, String enteredpassword){
        Statement statement;
        ResultSet resultSet = null;

        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts","root","password");
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM account WHERE username='"+username+"'AND password='"+enteredpassword+"';");
            this.connection.close();
        }catch (Exception e){
            e.printStackTrace();
        } return resultSet;
    }

    public void addUser(String username, String password, String firstname, String lastname, boolean b) {
        Statement statement;
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts","root","password");
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO account VALUES ("+username+","+password+","+firstname+","+lastname+","+b+");");
            this.connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet checkUserExists(String username){
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts","root","password");
            statement = this.connection.prepareStatement("SELECT * FROM account WHERE username = "+username);
            resultSet =  statement.executeQuery();
            this.connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}