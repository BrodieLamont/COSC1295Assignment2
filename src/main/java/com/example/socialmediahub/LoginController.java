package com.example.socialmediahub;

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
    private TextField enterLogInUsername;

    @FXML
    private TextField enterLogInPassword;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonCreateAccount;

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
