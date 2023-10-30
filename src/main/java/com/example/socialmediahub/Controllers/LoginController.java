package com.example.socialmediahub.Controllers;

import com.example.socialmediahub.Models.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
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

        buttonLogIn.setOnAction(actionEvent -> {
            try{
                if(Model.getInstance().evalLogIn(enterLogInUsername.getText(), enterLogInPassword.getText())) {
                    if (!Model.getInstance().getVIPCheck()) {
                        Model.getInstance().getViewFactory().showUserWindow();
                    } else {
                        Model.getInstance().getViewFactory().showVIPUserWindow();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The username or password you provided are incorrect");
                    alert.show();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        });

        buttonCreateAccount.setOnAction(actionEvent ->  {
                Model.getInstance().getViewFactory().showCreateAccountWindow();
                Stage stage = (Stage) enterLogInUsername.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
        });
    }
}
