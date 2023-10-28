package com.rbl.socialmediahub.Controllers.Users;

import com.rbl.socialmediahub.UserDataBaseController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {

    @FXML
    protected Label firstNameLabel;

    @FXML
    protected Label lastNameLabel;

    @FXML
    protected Button buttonMenuLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonMenuLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserDataBaseController.changeScene(actionEvent, "Log In", null, "login.fxml");
            }
        });
    }

    public void setWelcome(String firstname, String lastname){
        firstNameLabel.setText(firstname);
        lastNameLabel.setText(lastname);
    }
}
