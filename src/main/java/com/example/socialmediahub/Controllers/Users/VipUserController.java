package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VipUserController implements Initializable {

    public BorderPane user_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getUserSelection().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "ViewPost" -> user_parent.setCenter(Model.getInstance().getViewFactory().getPostView());
                case "AddPost" -> user_parent.setCenter(Model.getInstance().getViewFactory().getAddPost());
                case "RemovePost" -> user_parent.setCenter(Model.getInstance().getViewFactory().getRemovePost());
                case "Export" -> user_parent.setCenter(Model.getInstance().getViewFactory().getExportView());
                //case "Import" -> user_parent.setCenter(Model.getInstance().getViewFactory().getImportView());
                case "Graph" -> user_parent.setCenter(Model.getInstance().getViewFactory().getGraphView());
                case "EditProfile" -> user_parent.setCenter(Model.getInstance().getViewFactory().getEditProfileView());
                default -> user_parent.setCenter(Model.getInstance().getViewFactory().getVipdashboardView());
            }
        });
    }
}
