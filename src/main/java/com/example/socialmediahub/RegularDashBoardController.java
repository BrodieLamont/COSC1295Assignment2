package com.example.socialmediahub;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RegularDashBoardController implements Initializable {

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setWelcome(String firstname, String lastname) {
        firstNameLabel.setText(firstname);
        lastNameLabel.setText(lastname);
    }
}
