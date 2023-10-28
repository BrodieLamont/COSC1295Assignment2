package com.example.socialmediahub.Controllers;

import com.example.socialmediahub.UserDataBaseController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    protected TextField enterLogInUsername;

    @FXML
    protected TextField enterLogInPassword;

    @FXML
    protected Button buttonLogIn;

    @FXML
    protected Button buttonCreateAccount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonLogIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserDataBaseController.logIn(actionEvent, enterLogInUsername.getText(), enterLogInPassword.getText());
            }
        });

        buttonCreateAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserDataBaseController.changeScene(actionEvent, "Create Account", null, "CreateAccount.fxml");
            }
        });

    }
}
