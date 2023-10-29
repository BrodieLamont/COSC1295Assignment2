package com.example.socialmediahub.Views;

import com.example.socialmediahub.Controllers.Users.PostCellController;
import com.example.socialmediahub.Models.Post;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class PostCellFactory extends ListCell<Post> {
    @Override
    protected void updateItem(Post post, boolean empty){
        super.updateItem(post, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Users/PostCell.fxml"));
            PostCellController postCellController = new PostCellController(post);
            loader.setController(postCellController);
            setText(null);
            try{
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
