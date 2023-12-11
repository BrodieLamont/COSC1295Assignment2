package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Controllers.DirectoryChooserController;
import com.example.socialmediahub.Models.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VipUserMenuController implements Initializable {
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
    private Button buttonProfile;

    @FXML
    private Button buttonGraph;

    @FXML
    private Button buttonImport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fname = Model.getInstance().getUser().getFirstname();
        String lname = Model.getInstance().getUser().getLastname();
        listeners();
        //setWelcome(fname, lname);

        buttonMenuLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Model.getInstance().getViewFactory().showLoginWindow();
                Stage stage = (Stage) lastNameLabel.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        });
    }

    private void setWelcome(String fname, String lname) {
        firstNameLabel.setText(fname);
        lastNameLabel.setText(lname);
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
        buttonGraph.setOnAction(actionEvent -> graph());
        buttonMenuLogout.setOnAction(actionEvent -> logout());
        buttonProfile.setOnAction(actionEvent -> editProfile());

        buttonImport.setOnAction((actionEvent) -> {
            DirectoryChooserController dc = new DirectoryChooserController();
            String x = dc.returnPath();
            System.out.println(x);
        });
    }

    private void graph() {
    }

    //private void importPosts() {Model.getInstance().getViewFactory().getUserSelection().set("Import");}

    private void editProfile() {
        Model.getInstance().getViewFactory().getUserSelection().set("EditProfile");
    }

    private void logout() {
        Stage stage = (Stage) lastNameLabel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().setModel(null);
        Model.getInstance().getViewFactory().showLoginWindow();
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
