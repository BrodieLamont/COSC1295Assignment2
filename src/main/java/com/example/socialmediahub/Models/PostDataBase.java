package com.example.socialmediahub.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PostDataBase {
    private ArrayList<Post> database = new ArrayList<>();
    private Connection connection;

    /**
     * connect to MySQL database
     */
    public PostDataBase() {

    }

    /**
     * @return ArrayList of Post objects
     */
    public ArrayList<Post> getDatabase() {
        return database;
    }

    public void createPostDataBase(String username){
        Statement statement;
        ResultSet resultSet;
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts","root","password");
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM '"+username);
            try{
                if(resultSet.isBeforeFirst()){
                    while(resultSet.next()){
                        Post newPost = new Post(resultSet.getInt("postID"),
                                resultSet.getString("content"),
                                resultSet.getString("author"),
                                resultSet.getInt("likes"),
                                resultSet.getInt("shares"),
                                resultSet.getDate("date"));
                        database.add(newPost);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setDatabaseArray(ArrayList<Post> database){
        this.database = database;
    }

    public Post getPost(int ID) {

        int index = Collections.binarySearch(database,ID, new Comparator<Object>(){
            @Override
            public int compare(Object o1, Object o2){
                Post post = (Post) o1;
                int ID = (int)o2;
                return post.getID().compareTo(ID);
            }
        });

        return database.get(index);
    }


    /**
     * Adds a post to the database and then sorts by ID
     */
    public void addPost(Post post){
        database.add(post);
        database.sort(new IDSorter());
    }

    public void removePost(Post post){
        database.remove(post);
        database.sort(new IDSorter());
    }

    public ResultSet checkPostExists(String username, String postID){
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts","root","password");
            statement = this.connection.prepareStatement("SELECT * FROM '"+username+"' WHERE postID = '"+postID);
            resultSet =  statement.executeQuery();
            this.connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    /**
     * Checks if post has a unique ID in the database.
     * Loops through database comparing input ID to post ID
     *
     * @param input User input of post ID
     * @param database ArrayList of Post objects
     * @throws Exceptions.IDException Thrown if ID already exists in database
     */
    static void checkID(int input, ArrayList<Post> database) throws Exceptions.IDException {
        for (Post p:database){
            if (p.getID() == input) {
                throw new Exceptions.IDException("A post with that ID already exists");
            }
        }
    }

    static void checkNumbers(int i) throws Exceptions.IllegalNumber {
        if (i<0){
            throw new Exceptions.IllegalNumber("This number value cannot be negative");
        }
    }
}

/**
 * Sorts posts by the most likes using a Comparator
 *
 */
class LikeSorter implements Comparator<Post>{
    /**
     *
     * @param post1 First post for comparison
     * @param post2 Second post for comparison
     * @return      Integer difference between number of likes
     */
    @Override
    public int compare(Post post1, Post post2) {
        return post2.getLikes() - post1.getLikes();
    }

    static ArrayList<Post> topNLikes(ArrayList<Post> database, int number) {
        ArrayList<Post> topLikes = new ArrayList<>();
        database.sort(new LikeSorter()); // sort database by most likes

        // if user input is greater than size of database
        // makes input the size of the database
        if (number > database.size()) {
            number = database.size();
        }


        for(int i = 0; i < number; i++){
            topLikes.add(database.get(i));
        }

        return topLikes;
    }
}

/**
 * Sorts posts by the most shares using a Comparator
 */
class ShareSorter implements Comparator<Post>{
    /**
     *
     * @param post1 First post for comparison
     * @param post2 Second post for comparison
     * @return      Integer difference between number of shares
     */
    @Override
    public int compare(Post post1, Post post2) {
        return post2.getShares() - post1.getShares();
    }

    /**
     * Sorts posts in database by number of shares.
     * Prompts user to input (N) the number of posts they wish to see.
     * Prints N posts with the most shares.
     *  @param database ArrayList of Post objects
     * @param number Number of posts with most shares to display
     */
    static void topNShares(ArrayList<Post> database, int number) {
        database.sort(new ShareSorter());

        // if user input is greater than size of database
        // makes input the size of the database
        if (number > database.size()) {
            number = database.size();
        }

        for (int i = 0; i < number; i++) {
            Post post = database.get(i);
            post.printPost();
        }
    }
}

/**
 * Sorts posts by their ID (lowers to highest) using a Comparator
 */
class IDSorter implements Comparator<Post>{
    /**
     *
     * @param post1 First post for comparison
     * @param post2 Second post for comparison
     * @return      Integer difference between ID of posts
     */
    @Override
    public int compare(Post post1, Post post2){
        return post1.getID() - post2.getID();
    }
}