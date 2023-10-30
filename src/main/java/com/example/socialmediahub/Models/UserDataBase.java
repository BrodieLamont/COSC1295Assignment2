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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /*
    public static void createUser(ActionEvent event, String username, String password, String firstname, String lastname){
        UserDataBase db = new UserDataBase();
        try {
            if (db.getVipDataBase().containsKey(username) || db.getRegularDataBase().containsKey(username)){
                System.out.println("That username is not available");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use that username");
                alert.show();
            }
            else{
                RegularUser newUser = new RegularUser(username, password, firstname, lastname);
                db.getRegularDataBase().put(username, newUser);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


   public static void logIn(ActionEvent event, String username, String password){
        UserDataBase db = new UserDataBase();
        try {
            if (db.getVipDataBase().containsKey(username)) {
                User user = db.getVipDataBase().get(username);
                if (!Objects.equals(user.getPassword(), password)){
                    System.out.println("Incorrect Credentials");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The username or password you provided are incorrect");
                    alert.show();
                } else {
                    System.out.println("OOPS");
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    */

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
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO account VALUES ("+username+","+password+","+firstname+","+lastname+","+b+");");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet checkUserExists(String username){
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM account WHERE username = "+username);
            //statement.setString(1, username);
            System.out.println(username);
            System.out.println(statement);
            resultSet =  statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}