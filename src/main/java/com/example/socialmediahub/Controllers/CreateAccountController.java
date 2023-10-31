package com.example.socialmediahub.Controllers;

import com.example.socialmediahub.Models.Model;
import javafx.beans.property.StringProperty;
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
    private StringProperty tfUsername;

    @FXML
    private StringProperty tfPassword;

    @FXML
    private StringProperty tfFirst;

    @FXML
    private StringProperty tfLast;

    @FXML
    private Button buttonCreate;

    private ResultSet resultSet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonCreate.setOnAction(actionEvent ->  {
            try{
                if (tfUsernameProperty().toString().isEmpty()||tfPasswordProperty().toString().isEmpty()||tfFirstProperty().toString().isEmpty()||tfLastProperty().toString().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing data");
                    alert.setContentText("Please fill in all the categories");
                    alert.show();
                }
                else{
                    resultSet = Model.getInstance().getUserDataBase().checkUserExists(tfUsernameProperty().toString());
                    if(!resultSet.isBeforeFirst()){
                        Model.getInstance().getUserDataBase().addUser(tfUsernameProperty().toString(), tfPasswordProperty().toString(),tfFirstProperty().toString(),tfLastProperty().toString(), 0);
                        Stage stage = (Stage) buttonCreate.getScene().getWindow();
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

    public StringProperty tfUsernameProperty() {
        return tfUsername;
    }

    public StringProperty tfPasswordProperty() {
        return tfPassword;
    }

    public StringProperty tfFirstProperty() {
        return tfFirst;
    }

    public StringProperty tfLastProperty() {
        return tfLast;
    }
}
