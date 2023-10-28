package com.rbl.socialmediahub;

/*
 * Post
 *
 * version 1.0
 *
 * 25/08/2023
 *
 * Copyright notice
 */

import java.util.Date;

/**
 * An object of a social media post containing the variables ID, content, author, likes, shares and datetime.
 */

public class PostController {

    private Integer ID;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private Date datetime;

    /**
     * Post Constructor
     *
     * @param ID ID of the post
     * @param content Content of the post
     * @param author Author of the post
     * @param likes Number of like the post has
     * @param shares Number of shares the post has
     * @param datetime Date and time the post was made in the format "dd/mm/yyyy hh:mm"
     */
    public PostController(Integer ID, String content, String author, int likes, int shares, Date datetime) {
        this.ID = ID;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        this.datetime = datetime;
    }

    public Integer getID(){
        return ID;
    }

    public String getContent(){
        return content;
    }

    public String getAuthor(){
        return author;
    }

    public int getLikes(){
        return likes;
    }

    public int getShares(){
        return shares;
    }

    public Date getDateTime(){
        return datetime;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setLikes(int likes){
        this.likes = likes;
    }

    public void setShares(int shares){
        this.shares = shares;
    }

    public void setDateTime(Date datetime){
        this.datetime = datetime;
    }

    public void printPost(){
        System.out.println(ID + " | " + content + " | " + author + " | " + likes + " | " + shares + " | " + datetime);
    }
}