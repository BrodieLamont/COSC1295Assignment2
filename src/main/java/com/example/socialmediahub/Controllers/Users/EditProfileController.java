package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.Models.VIPUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfFirst;
    @FXML
    private TextField tfLast;
    @FXML
    private Button buttonSave;

    private ResultSet resultSet;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonSave.setOnAction(actionEvent -> {
            try{
                if (tfUsername.getText().isEmpty() &&
                        tfPassword.getText().isEmpty() &&
                        tfFirst.getText().isEmpty() &&
                        tfLast.getText().isEmpty()){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing Data");
                    alert.setContentText("Please enter the fields you wish to change");
                    alert.show();

                }
                else{
                    if(!tfUsername.getText().isEmpty()){
                        resultSet = Model.getInstance().getUserDataBase().checkUserExists(tfUsername.getText());
                        if(!resultSet.isBeforeFirst()){
                            Model.getInstance().getUser().setUsername(tfUsername.getText());
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid username");
                            alert.setContentText("That username already exists");
                            alert.show();
                        }

                    }
                    if(!tfPassword.getText().isEmpty()){
                        Model.getInstance().getUser().setPassword(tfPassword.getText());
                    }
                    if(!tfFirst.getText().isEmpty()){
                        Model.getInstance().getUser().setFirstname(tfFirst.getText());
                    }
                    if(!tfLast.getText().isEmpty()){
                        Model.getInstance().getUser().setLastname(tfLast.getText());
                    }

                }

            }catch(SQLException e){
                e.printStackTrace();

            }
        });

    }
}
