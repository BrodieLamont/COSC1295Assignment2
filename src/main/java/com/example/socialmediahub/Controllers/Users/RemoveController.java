package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.Models.Post;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveController implements Initializable {
    @FXML
    private TextField tfPostID;
    @FXML
    private Button buttonRemove;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonRemove.setOnAction(actionEvent -> {
            try{
                String username = Model.getInstance().getUser().getUsername();
                ResultSet rs =  Model.getInstance().getPostDataBase().checkPostExists(Integer.parseInt(tfPostID.getText()), username);

                if (rs.isBeforeFirst()){
                    Post deadPost = Model.getInstance().getPostDataBase().getPost(Integer.parseInt(tfPostID.getText()));
                    Model.getInstance().getPostDataBase().removePost(deadPost);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Post deleted");
                    alert.setContentText("Post successfully deleted");

                    if (!Model.getInstance().getUser().getVipStatus()) {
                        Model.getInstance().getViewFactory().showUserWindow();
                    } else {
                        Model.getInstance().getViewFactory().showVIPUserWindow();
                    }
                    Stage stage = (Stage) tfPostID.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setContentText("That post does not exist");
                    alert.show();
                }

            }catch(NullPointerException e){
                throw new NullPointerException();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
