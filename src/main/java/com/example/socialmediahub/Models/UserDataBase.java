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

    public void addUser(String username, String password, String firstname, String lastname, int b) {
        Statement statement;
        PreparedStatement statement2;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO account VALUES ('"+username+"','"+password+"','"+firstname+"','"+lastname+"','"+b+"');");
            statement2 = this.connection.prepareStatement("CREATE TABLE " +username+ " (postID INTEGER, author VARCHAR(255), likes INTEGER, shares INTEGER, date DATE, content MEDIUMTEXT, PRIMARY KEY(postID))");
            statement2.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateUser(String username, String password, String firstname, String lastname, boolean b, String oldUsername){
        Statement statement;
        int bool = 0;
        if (b){
            bool = 1;
        }
        try{
            statement = this.connection.createStatement();
            statement.executeUpdate("UPDATE account SET username = ' "+username+" ', password = ' "+password+" ', firstname = ' "+firstname+" ', lastname = ' "+lastname+" ', vipStatus = '"+bool+" ' WHERE username = ' "+oldUsername+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet checkUserExists(String username){
        Statement statement;
        ResultSet resultSet;
        try{
            String sql = ("SELECT * FROM account WHERE username='"+username+"';");
            statement = this.connection.createStatement();
            resultSet =  statement.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}