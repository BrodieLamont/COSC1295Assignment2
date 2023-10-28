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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonMenuLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Model.getInstance().getViewFactory().showLoginWindow();
                Stage stage = (Stage) lastNameLabel.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        });
    }

    public void setWelcome(String firstname, String lastname){
        firstNameLabel.setText(firstname);
        lastNameLabel.setText(lastname);
    }
}
