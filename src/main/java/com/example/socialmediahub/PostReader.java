package com.example.socialmediahub;
/*
 * PostReader
 *
 * version 1.0
 *
 * 25/08/2023
 *
 * Copyright notice
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A PostReader object has the methods to read a .csv and create an ArrayList of Post objects
 */
public class PostReader {

    /**
     * Reads lines from a .csv and adds them to an ArrayList.
     * Ignores the first line of the .csv using a counter.
     * The counter is incremented by 1 for every line read.
     * Post objects are only created and added to the ArrayList if counter is > 0.
     * <p>
     * Each line is read as a string, separated by "," and then added to a list.
     * Strings are parsed to Integer for ID, likes and shares.
     * String parsed to Date for datetime.
     *
     * @param database ArrayList of Post objects
     * @param file path to the .csv file as a String
     */
    public void read(String file, PostDataBaseController database){
        int count = 0; // initialize counter at 0
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            while((line = br.readLine()) != null){
                String[] values = line.split(",",0);

                if (count > 0) { // convert data from String[] to appropriate type
                    int ID = Integer.parseInt(values[0]);
                    String content = values[1];
                    String author = values[2];
                    int likes = Integer.parseInt(values[3]);
                    int shares = Integer.parseInt(values[4]);
                    try{
                        PostDataBaseController.checkNumbers(shares);
                        PostDataBaseController.checkNumbers(likes);
                    } catch (Exceptions.IllegalNumber illegalNumber) {
                        System.out.println("A post cannot have non positive number of shares");
                        System.exit(0);
                    }
                    Date datetime = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(values[5]); // convert String to Date object

                    PostController post = new PostController(ID, content, author, likes, shares, datetime);
                    try{
                        PostDataBaseController.checkID(post.getID(), database.getDatabase());
                    }catch(Exceptions.IDException e){
                        System.out.println("Something went wrong");
                        System.out.println("There is a duplicate ID in the data");
                        System.exit(0);
                    }
                    database.addPost(post);
                }
                count += 1; // increment counter
            }

        } catch(IOException e){
            System.out.println("File not found");
            System.exit(0);
        } catch(ParseException | ArrayIndexOutOfBoundsException | IllegalArgumentException e){
            System.out.println("Something went wrong");
            System.out.println("Input data is not in correct format");
            System.exit(0);
        }
    }
}