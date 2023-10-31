package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.Models.RegularUser;
import com.example.socialmediahub.Models.VIPUser;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Button buttonMenuLogout;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonView;

    @FXML
    private Button buttonRemove;

    @FXML
    private Button buttonExport;

    @FXML
    private Button buttonSignUp;

    @FXML
    private Button buttonProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String fname = Model.getInstance().getUser().getFirstname();
        String lname = Model.getInstance().getUser().getLastname();
        listeners();
        setWelcome(fname, lname);
    }

    /**
     * The lambda method for event handling is from
     * https://www.tutorialspoint.com/how-to-implement-javafx-event-handling-using-lambda-in-java
     * */
    private void listeners(){

        buttonView.setOnAction(actionEvent -> viewPost());
        buttonAdd.setOnAction(actionEvent -> addPost());
        buttonRemove.setOnAction(actionEvent -> removePost());
        buttonExport.setOnAction(actionEvent -> exportPost());
        buttonSignUp.setOnAction(actionEvent -> signUp());
        buttonMenuLogout.setOnAction(actionEvent -> logout());
        buttonProfile.setOnAction(actionEvent -> editProfile());
    }

    private void setWelcome(String fname, String lname) {
        firstNameLabel.setText(fname);
        lastNameLabel.setText(lname);
    }

    private void editProfile() {
        Model.getInstance().getViewFactory().getUserSelection().set("EditProfile");
    }

    private void logout() {
        Stage stage = (Stage) lastNameLabel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().setModel(null);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    private void signUp() {
        try{
            if (Model.getInstance().getUser() != null){
                Model.getInstance().getUser().setVipStatus(true);
                String un = Model.getInstance().getUser().getUsername();
                String pw = Model.getInstance().getUser().getPassword();
                String first = Model.getInstance().getUser().getFirstname();
                String aut = Model.getInstance().getUser().getLastname();
                Model.getInstance().getUserDataBase().updateUser(un, pw, first, aut, true, un);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Log out and log back in to see the change");
                alert.show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void exportPost() {
        Model.getInstance().getViewFactory().getUserSelection().set("Export");
    }

    private void removePost() {
        Model.getInstance().getViewFactory().getUserSelection().set("RemovePost");
    }

    private void addPost() {
        Model.getInstance().getViewFactory().getUserSelection().set("AddPost");
    }

    private void viewPost() {
        Model.getInstance().getViewFactory().getUserSelection().set("ViewPost");
    }
}
