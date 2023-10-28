package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.UserDataBaseController;
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

        buttonMenuLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Model.getInstance().getViewFactory().showLoginWindow();
                Stage stage = (Stage) lastNameLabel.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        });
    }

    /**
     * The lambda method for event handling is from
     * https://www.tutorialspoint.com/how-to-implement-javafx-event-handling-using-lambda-in-java
     * */
    private void listeners(){

        buttonView.setOnAction((actionEvent) -> {
            Button source = (Button) actionEvent.getSource();
            if (source.equals(buttonView)){
                viewPost();
            }
        });
        buttonAdd.setOnAction((actionEvent) -> {
            Button source = (Button) actionEvent.getSource();
            if (source.equals(buttonView)) {
                addPost();
            }
        });
        buttonRemove.setOnAction(actionEvent -> {
            Button source = (Button) actionEvent.getSource();
            if (source.equals(buttonView)) {
                removePost();
            }
        });
        buttonExport.setOnAction(actionEvent -> {
            Button source = (Button) actionEvent.getSource();
            if (source.equals(buttonView)) {
                exportPost();
            }
        });
        buttonSignUp.setOnAction(actionEvent -> {
            Button source = (Button) actionEvent.getSource();
            if (source.equals(buttonView)) {
                signUp();
            }
        });
        buttonMenuLogout.setOnAction(actionEvent -> {
            Button source = (Button) actionEvent.getSource();
            if (source.equals(buttonView)) {
                logout();
            }
        });

        buttonProfile.setOnAction(actionEvent -> {
            Button source = (Button) actionEvent.getSource();
            if (source.equals(buttonProfile)) {
                editProfile();
            }
        });
    }

    private void editProfile() {
        Model.getInstance().getViewFactory().getUserSelection().set("EditProfile");
    }

    private void logout() {
        Stage stage = (Stage) lastNameLabel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    private void signUp() {
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

    public void setWelcome(String firstname, String lastname){
        firstNameLabel.setText(firstname);
        lastNameLabel.setText(lastname);
    }
}
