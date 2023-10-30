package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Controllers.DirectoryChooserController;
import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.Models.Post;
import com.opencsv.CSVWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Directory Chooser from here https://jenkov.com/tutorials/javafx/directorychooser.html
 */
public class ExportController implements Initializable {
    @FXML
    private Button buttonExport;
    @FXML
    private TextField tfFileName;
    @FXML
    private TextField tfPostID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonExport.setOnAction(actionEvent -> {
            DirectoryChooserController dc = new DirectoryChooserController();
            String location = (dc.returnPath() + "/" + tfFileName.getText() + ".csv");

            Integer x = Integer.parseInt(tfPostID.getText());

            writeDataAtOnce(location, x);
        });
    }

    /**
    *OpenCSV Writer code from https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
     */
    public static void writeDataAtOnce(String filePath, Integer x)
    {

        File file = new File(filePath);

        try {

            FileWriter outputfile = new FileWriter(file);


            CSVWriter writer = new CSVWriter(outputfile);

            Post post = Model.getInstance().getPostDataBase().getPost(x);

            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");


            List<String[]> exportData = new ArrayList<String[]>();
            String id = String.valueOf(post.getID());
            String author = post.getAuthor();
            String likes = post.getLikes().toString();
            String shares = post.getShares().toString();
            String day = date.format(post.getDateTime());
            String content = post.getContent();

            exportData.add(new String [] {id, author, likes, shares, day, content});

            writer.writeAll(exportData);


            writer.close();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }
}
