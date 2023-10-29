package com.example.socialmediahub.Controllers;

import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DirectoryChooserController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        returnDirectory(stage, directoryChooser);
    }

    public String returnDirectory(Stage stage, DirectoryChooser dc){


        File selectedDirectory = dc.showDialog(stage);

        return selectedDirectory.getAbsolutePath();
    }
}