package com.example.socialmediahub.Models;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class PostDataBase {
    private ArrayList<Post> database = new ArrayList<>();
    private Connection connection;

    /**
     * connect to MySQL database
     */
    public PostDataBase() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts","root","password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " +username+ ";");
            System.out.println(resultSet);
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
    public void addnewPost(Post post){
        database.add(post);
        database.sort(new IDSorter());
        System.out.println("Post added and sorted");
        Statement statement;
        try{
            String username = Model.getInstance().getUser().getUsername();
            Integer postID = post.getID();
            String author = post.getAuthor();
            Integer likes = post.getLikes();
            Integer shares = post.getShares();
            java.sql.Date sqDate = new java.sql.Date(post.getDateTime().getTime());
            System.out.println(sqDate);


            String content = post.getContent();
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO " +username+ " VALUES (' "+postID+" ',' "+author+" ',' "+likes+" ',' "+shares+" ',' "+ sqDate +" ',' "+content+" ');");

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removePost(Post post)  {
        database.remove(post);
        database.sort(new IDSorter());
        PreparedStatement statement;
        try{
            String username = Model.getInstance().getUser().getUsername();
            Integer postID =  post.getID();
            statement = this.connection.prepareStatement("DELETE FROM " +username+ " WHERE postID = ' "+postID+" ';" );
            statement.executeUpdate();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
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

    public ResultSet checkPostExists(Integer postID, String username){
        Statement statement;
        ResultSet resultSet;
        try{
            String sql = ("SELECT * FROM " +username+ " WHERE postID= '"+postID+"';");
            statement = this.connection.createStatement();
            resultSet =  statement.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
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
    static ArrayList<Post> topNShares(ArrayList<Post> database, int number) {
        ArrayList<Post> tops = new ArrayList<>();
        database.sort(new ShareSorter());

        // if user input is greater than size of database
        // makes input the size of the database
        if (number > database.size()) {
            number = database.size();
        }

        for (int i = 0; i < number; i++) {
            tops.add(database.get(i));
        }
        return tops;
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