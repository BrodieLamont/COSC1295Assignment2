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

        buttonCreate.setOnAction(actionEvent ->  {

                if (tfUsername.getText().isEmpty()||tfPassword.getText().isEmpty()||tfFirst.getText().isEmpty()||tfLast.getText().isEmpty()){
                    System.out.println("Missing data");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing data");
                    alert.setContentText("Please fill in all the categories");
                    alert.show();
                } else{
                    try{
                        Model.getInstance().getUserDataBase().addUser(tfUsername.getText(), tfPassword.getText(),tfFirst.getText(),tfLast.getText(), false);
                        Stage stage = (Stage) tfFirst.getScene().getWindow();
                        Model.getInstance().getViewFactory().closeStage(stage);
                        Model.getInstance().getViewFactory().showLoginWindow();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
        });
    }
}
