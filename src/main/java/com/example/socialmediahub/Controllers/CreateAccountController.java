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
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

    private ResultSet resultSet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonCreate.setOnAction(actionEvent ->  {
            try{
                if (tfUsername.getText().isEmpty()||tfPassword.getText().isEmpty()||tfFirst.getText().isEmpty()||tfLast.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing data");
                    alert.setContentText("Please fill in all the categories");
                    alert.show();
                }
                else{
                    resultSet = Model.getInstance().getUserDataBase().checkUserExists(tfUsername.getText());
                    if(!resultSet.isBeforeFirst()){
                        Model.getInstance().getUserDataBase().addUser(tfUsername.getText(), tfPassword.getText(),tfFirst.getText(),tfLast.getText(), false);
                        Stage stage = (Stage) tfFirst.getScene().getWindow();
                        Model.getInstance().getViewFactory().closeStage(stage);
                        Model.getInstance().getViewFactory().showLoginWindow();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid username");
                        alert.setContentText("That username already exists");
                        alert.show();
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }
}
