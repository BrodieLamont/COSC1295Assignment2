package com.example.socialmediahub;

/*
 * UserDatabase
 *
 * version 1.0
 *
 * 14/09/2023
 *
 * Copyright notice
 */
import java.util.*;
/**
 * Class for storing ArrayList of User objects.
 * Contains method getUserDatabase() to return the database.
 */

public class UserDataBaseController {

    private HashMap<String, RegularUser> regularDataBase = new HashMap<>();
    private HashMap<String, VIPUser> vipDataBase = new HashMap<>();

    public HashMap<String, RegularUser> getRegularDataBase(){
        return regularDataBase;
    }

    public HashMap<String, VIPUser> getVipDataBase(){
        return vipDataBase;
    }

    public void setRegularDataBase(HashMap<String, RegularUser> regularDataBase){
        this.regularDataBase = regularDataBase;
    }
    public void setVipDataBase(HashMap<String, VIPUser> vipDataBase){
        this.vipDataBase = vipDataBase;
    }

}
