package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.Models.RegularUser;
import com.example.socialmediahub.Models.VIPUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
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

        listeners();
        /*
        buttonMenuLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Model.getInstance().getViewFactory().showLoginWindow();
                Stage stage = (Stage) lastNameLabel.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        });
         */
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
        if (Model.getInstance().getUser() != null){
            Model.getInstance().getUser().setVipStatus(true);
        }
        Model.getInstance().getViewFactory().showSignUpWindow();
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
