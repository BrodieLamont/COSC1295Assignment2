package com.example.socialmediahub.Controllers.Users;

import com.example.socialmediahub.Models.Model;
import com.example.socialmediahub.Models.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GraphController implements Initializable {
    private int x;
    private int y;
    private int z;

    private Stage primaryStage;;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Post> database = Model.getInstance().getPostDataBase().getDatabase();
        getValues(database);


        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("0-99", x),
                new PieChart.Data("100-999",y),
                new PieChart.Data("1000+",z)
        );



        PieChart pChart = new PieChart(pieData);

        Group root = new Group(pChart);
        Scene scene = new Scene(root, 600,400);
        pChart.setTitle("Distribution of Shares");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void getValues(ArrayList<Post> database){
        int low = 0;
        int med = 0;
        int high = 0;
        for (Post p: database){
            if (p.getShares() < 100){
                low += 1;
            }
            if (99 < p.getShares() && p.getShares() < 1000){
                med += 1;
            }

            if (999 < p.getShares()){
                high += 1;
            }
        }
        setX(low);
        setY(med);
        setZ(high);

    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }

    public void setX(int newx){
        this.x = newx;
    }

    public void setY(int newy){
        this.y = newy;
    }
    public void setZ(int newz){
        this.z = newz;
    }
}
