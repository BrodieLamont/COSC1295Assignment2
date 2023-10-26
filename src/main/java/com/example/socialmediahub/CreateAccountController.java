package com.example.socialmediahub;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfFirst;

    @FXML
    private TextField tfLast;

    @FXML
    private Button buttonCreate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
