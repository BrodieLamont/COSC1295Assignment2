package com.example.socialmediahub.Models;

import com.example.socialmediahub.Views.ViewFactory;

import java.sql.ResultSet;
import java.util.ArrayList;

/*
    This class was developed using code from https://www.youtube.com/watch?v=lkov5shwRQs
 */
public class Model {
    private final ViewFactory viewFactory;
    private static Model model;
    private static UserDataBase userDataBase;

    // Users
    private User user;
    private Boolean credentialCheck;
    private boolean vipCheck;
    private ArrayList<Post> posts;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.userDataBase = new UserDataBase();
        this.credentialCheck = false;
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public UserDataBase getUserDataBase(){
        return userDataBase;
    }

    // METHODS FOR USERS
    public boolean getCredentialCheck(){
        return this.credentialCheck;
    }
    public void setCredentialCheck(boolean check){
        this.credentialCheck = check;
    }
    public boolean getVIPCheck(){
        return this.vipCheck;
    }
    public void setVipCheck(boolean check){
        this.vipCheck = check;
    }
    public boolean evalLogIn(String username, String password){
        String firstName;
        String lastName;

        ResultSet resultSet =  userDataBase.getCredentials(username, password);
        try{
            if (resultSet.isBeforeFirst()){
                resultSet.next();

                if(resultSet.getBoolean("vipStatus")){
                    this.setVipCheck(true);
                    this.user = new VIPUser(username, password, resultSet.getString("firstname"),resultSet.getString("lastname"), true);
                }
                else{
                    this.user = new RegularUser(username, password, resultSet.getString("firstname"),resultSet.getString("lastname"), false);
                }

                this.setCredentialCheck(true);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.credentialCheck;
    }

    public RegularUser getUser(){
        return (RegularUser) this.user;
    }
}


