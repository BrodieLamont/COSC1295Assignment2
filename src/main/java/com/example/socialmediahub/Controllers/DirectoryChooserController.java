package com.example.socialmediahub.Controllers;

import com.example.socialmediahub.Models.Model;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DirectoryChooserController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().showDirectoryWindow();
        returnPath();
    }

    public String returnPath(){
        Stage stage = new Stage();
        DirectoryChooser dc = new DirectoryChooser();
        File selectedFile = dc.showDialog(stage);

        return selectedFile.getAbsolutePath();

    }

}