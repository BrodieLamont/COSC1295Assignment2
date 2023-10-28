package com.rbl.socialmediahub.Controllers;

import com.rbl.socialmediahub.UserDataBaseController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML
    protected TextField tfUsername;

    @FXML
    protected TextField tfPassword;

    @FXML
    protected TextField tfFirst;

    @FXML
    protected TextField tfLast;

    @FXML
    protected Button buttonCreate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String firstname = tfFirst.getText();
        String lastname = tfLast.getText();

        buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty()){
                    System.out.println("Missing data");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all the categories");
                    alert.show();
                } else{
                    UserDataBaseController.createUser(actionEvent, username, password, firstname, lastname);
                }
            }
        });
    }
}