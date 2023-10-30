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
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts","root","password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getCredentials(String username, String enteredpassword){
        Statement statement;
        ResultSet resultSet = null;

        try{
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM account WHERE username='"+username+"'AND password='"+enteredpassword+"';");
        }catch (Exception e){
            e.printStackTrace();
        } return resultSet;
    }

    public void addUser(String username, String password, String firstname, String lastname, boolean b) {
        Statement statement;
        PreparedStatement statement2;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO account VALUES ("+username+","+password+","+firstname+","+lastname+","+b+");");
            statement2 = this.connection.prepareStatement("CREATE TABLE '"+username+"' (postID INT, author VARCHAR(255), likes INT, shares INT, date DATE, content MEDIUMTEXT);");
            statement2.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateUser(String username, String password, String firstname, String lastname, boolean b, String oldUsername){
        Statement statement;
        try{

            statement = this.connection.createStatement();
            statement.executeUpdate("UPDATE account SET username = '"+username+"' password = '"+password+"' firstname = '"+firstname+" ' lastname = ' "+lastname+"' vipStatus = '"+b+"' WHERE username = '"+oldUsername);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet checkUserExists(String username){
        PreparedStatement statement;
        ResultSet resultSet;
        try{

            statement = this.connection.prepareStatement("SELECT * FROM account WHERE username = "+username);
            resultSet =  statement.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}