package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Controllers.DirectoryChooserController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportController implements Initializable {

    @FXML
    private Button buttonImport;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonImport.setOnAction(actionEvent -> {
            DirectoryChooserController dc = new DirectoryChooserController();
            String x = dc.returnPath();
            System.out.println(x);
        });
    }
}
