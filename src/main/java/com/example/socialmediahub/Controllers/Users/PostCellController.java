package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Post;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PostCellController implements Initializable {

    @FXML
    private Label labelPostID;
    @FXML
    private Label labelAuthor;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelLikes;
    @FXML
    private Label labelShares;
    @FXML
    private TextField tfContent;

    private final Post post;

    public PostCellController (Post post){
        this.post = post;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
