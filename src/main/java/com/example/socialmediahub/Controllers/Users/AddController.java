package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.Models.Post;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddController implements Initializable {

    @FXML
    private TextField tfPostID;
    @FXML
    private TextField tfContent;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfLikes;
    @FXML
    private TextField tfShares;
    @FXML
    private TextField tfDate;
    @FXML
    private Button buttonAddPost;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonAddPost.setOnAction(actionEvent -> {
            try{
                if (missingData(tfPostID,tfContent,tfAuthor,tfLikes,tfShares,tfDate)){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing Data");
                    alert.setContentText("Please enter the fields you wish to change");
                    alert.show();
                }
                else{
                    String username = Model.getInstance().getUser().getUsername();
                    ResultSet rs = Model.getInstance().getPostDataBase().checkPostExists(Integer.parseInt(tfPostID.getText()), username);

                    if(badDate(tfDate)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setContentText("Please add date in the following format: dd-MM-yyyy hh:mm:ss");
                        alert.show();
                    }
                    if(rs.isBeforeFirst()) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setContentText("That post already exists");
                        alert.show();
                    }
                    else{
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                        Date date = formatter.parse(tfDate.getText());
                        System.out.println(date);
                        Post newPost = new Post(Integer.parseInt(tfPostID.getText()),
                                tfContent.getText(),
                                tfAuthor.getText(),
                                Integer.parseInt(tfLikes.getText()),
                                Integer.parseInt(tfShares.getText()),
                                date);
                        Model.getInstance().getPostDataBase().addnewPost(newPost);

                        if (!Model.getInstance().getUser().getVipStatus()) {
                            Model.getInstance().getViewFactory().showUserWindow();
                        } else {
                            Model.getInstance().getViewFactory().showVIPUserWindow();
                        }

                        Stage stage = (Stage) tfDate.getScene().getWindow();
                        Model.getInstance().getViewFactory().closeStage(stage);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Done");
                        alert.setContentText("Post added to database");
                        alert.show();
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        });
    }

    public boolean badDate(TextField tfDate) {
        boolean bool = false;
        if(tfDate.getText().length() != 19){
            bool = true;
        }
        return bool;
    }

    public boolean missingData(TextField id, TextField cont, TextField auth, TextField likes, TextField shares, TextField date){
        boolean bool = false;
        if(id.getText().isEmpty() ||
        cont.getText().isEmpty() ||
        auth.getText().isEmpty() ||
        likes.getText().isEmpty() ||
        shares.getText().isEmpty() ||
        date.getText().isEmpty()){
            bool = true;
        }
        return bool;
    }
}
