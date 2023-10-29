package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Controllers.DirectoryChooserController;
import com.example.socialmediahub.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Directory Chooser from here https://jenkov.com/tutorials/javafx/directorychooser.html
 */
public class ExportController implements Initializable {
    @FXML
    private Button buttonExport;
    @FXML
    private TextField tfFileName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonExport.setOnAction(actionEvent -> {
            DirectoryChooserController dc = new DirectoryChooserController();
            String x = dc.returnPath();
            System.out.println(x);
        });
    }
}
