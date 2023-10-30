package com.example.socialmediahub.Models;

import com.example.socialmediahub.Views.ViewFactory;

import java.sql.ResultSet;
import java.util.ArrayList;

/*
    This class was developed using code from https://www.youtube.com/watch?v=lkov5shwRQs
 */
public class Model {
    // MODEL VARIABLES
    private final ViewFactory viewFactory;
    private static Model model;
    private static UserDataBase userDataBase;

    // USER VARIABLES
    private User user;
    private Boolean credentialCheck;
    private static PostDataBase postDataBase;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.userDataBase = new UserDataBase();
        this.user = new User("","","","", true);
        this.postDataBase = new PostDataBase();
        this.credentialCheck = false;
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public Model setModel(Model x){
        this.model = x;
        return x;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public UserDataBase getUserDataBase(){
        return userDataBase;
    }

    // ACCESS USER VARIABLES
    public User getUser(){
        return this.user;
    }
    public PostDataBase getPostDataBase(){
        return postDataBase;
    }
    public boolean getCredentialCheck(){
        return this.credentialCheck;
    }
    public void setCredentialCheck(boolean check){
        this.credentialCheck = check;
    }
    public boolean evalLogIn(String username, String password){
        ResultSet resultSet;

        resultSet =  userDataBase.getCredentials(username, password);
        try{
            if (resultSet.isBeforeFirst()){
                resultSet.next();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setVipStatus(resultSet.getBoolean("vipStatus"));
                this.setCredentialCheck(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.credentialCheck;
    }
}
