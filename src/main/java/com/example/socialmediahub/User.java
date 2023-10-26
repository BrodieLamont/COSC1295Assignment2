package com.example.socialmediahub;
/*
 * User
 *
 * version 1.0
 *
 * 14/09/2023
 *
 * Copyright notice
 */

import java.util.*;

abstract class User {
    private String username;
    private String password;
    private String firstname;
    private String lastname;


    /**
     * User Constructor
     *
     * @param username Unique username of user
     * @param password Users password
     * @param firstname Users first name
     * @param lastname Users last name
     */
    public User(String username, String password, String firstname, String lastname){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    /**
     * Create a post and adds it to the PostDataBase
     *
     */
    public void createPost(){
        PostDataBaseController postdatabase = new PostDataBaseController();
        Scanner sc = new Scanner(System.in);
        Scanner contents = new Scanner(System.in);
        System.out.println("Enter Post ID:");
        int ID = sc.nextInt();
        System.out.println("Enter Post Content:");
        String content = contents.next();
        System.out.println("Enter Number of Likes:");
        int likes = sc.nextInt();
        System.out.println("Enter number of Shares:");
        int shares = sc.nextInt();
        Date datetime = new Date();

        PostController post = new PostController(ID, username, content, likes, shares, datetime);
        try{
            PostDataBaseController.checkID(ID, postdatabase.getDatabase());
            PostDataBaseController.checkNumbers(shares);
        } catch (Exceptions.IDException e){
            System.out.println("That post ID already exists");
            System.exit(0);

        } catch (Exceptions.IllegalNumber e) {
            System.out.println("A post cannot have non positive number of shares");
            System.exit(0);
        }
        postdatabase.addPost(post);
    }

    public void getPost(Integer ID){
        PostDataBaseController postdatabase = new PostDataBaseController();

        PostController post = postdatabase.getPost(ID);
        post.printPost();
    }

    public void removePost(Integer ID){
        PostDataBaseController postdatabase = new PostDataBaseController();

        PostController post = postdatabase.getPost(ID);
        postdatabase.removePost(post);

    }
}