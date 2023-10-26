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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
/**
 * Class for storing ArrayList of User objects.
 * Contains method getUserDatabase() to return the database.
 */

public class UserDataBaseController {

    private HashMap<String, RegularUser> regularDataBase = new HashMap<>();
    private HashMap<String, VIPUser> vipDataBase = new HashMap<>();

    public HashMap<String, RegularUser> getRegularDataBase() {
        return regularDataBase;
    }

    public HashMap<String, VIPUser> getVipDataBase() {
        return vipDataBase;
    }

    public void setRegularDataBase(HashMap<String, RegularUser> regularDataBase) {
        this.regularDataBase = regularDataBase;
    }

    public void setVipDataBase(HashMap<String, VIPUser> vipDataBase) {
        this.vipDataBase = vipDataBase;
    }

    public static void changeScene(ActionEvent event, String title, String username, String password, String fxmlFile) {
        Parent root = null;

        if (username != null && password != null) {
            UserDataBaseController db = new UserDataBaseController();

            if (db.getVipDataBase().containsKey(username)) {
                try {
                    FXMLLoader loader = new FXMLLoader(User.class.getResource(fxmlFile));
                    root = loader.load();
                    User user = db.getVipDataBase().get(username);
                    VIPDashBoardController vipDashBoard = loader.getController();
                    vipDashBoard.setWelcome(user.getFirstname(), user.getLastname());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (db.getRegularDataBase().containsKey(username)) {
                try {
                    FXMLLoader loader = new FXMLLoader(User.class.getResource(fxmlFile));
                    root = loader.load();
                    User user = db.getVipDataBase().get(username);
                    RegularDashBoardController regularDashBoard = loader.getController();
                    regularDashBoard.setWelcome(user.getFirstname(), user.getLastname());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    root = FXMLLoader.load(UserDataBaseController.class.getResource(fxmlFile));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void createUser(ActionEvent event, String username, String password, String firstname, String lastname){
        UserDataBaseController db = new UserDataBaseController();
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

                changeScene(event, "Welcome", username, password, "RegularDashBoard.fxml");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}