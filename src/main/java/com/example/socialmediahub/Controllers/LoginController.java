package com.example.socialmediahub.Controllers;

import com.example.socialmediahub.Models.Model;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private StringProperty enterLogInUsername;

    @FXML
    private StringProperty enterLogInPassword;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonCreateAccount;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonLogIn.setOnAction(actionEvent -> {
            try{
                if(Model.getInstance().evalLogIn(getUsername().toString(), getPassword().toString())) {
                    String username = Model.getInstance().getUser().getUsername();
                    Model.getInstance().getPostDataBase().createPostDataBase(username);
                    if (!Model.getInstance().getUser().getVipStatus()) {
                        Model.getInstance().getViewFactory().showUserWindow();
                    } else {
                        Model.getInstance().getViewFactory().showVIPUserWindow();
                    }
                    Stage stage = (Stage) buttonLogIn.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);

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
                Stage stage = (Stage) buttonLogIn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
        });
    }

    public StringProperty getUsername() {
        return enterLogInUsername;
    }

    public StringProperty getPassword() {
        return enterLogInPassword;
    }

}
