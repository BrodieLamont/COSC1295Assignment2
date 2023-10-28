package com.example.socialmediahub.Views;

import com.example.socialmediahub.Controllers.Users.UserController;
import com.example.socialmediahub.Controllers.Users.VipUserController;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/*
This class was developed using code modified from https://www.youtube.com/watch?v=lkov5shwRQs
 */

public class ViewFactory {
    //User Views

    private AnchorPane dashboardView;

    public ViewFactory() {
    }

    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/User/UserDashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        createStage(loader);
    }

    public void showUserWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Users/User.fxml"));
        UserController userController = new UserController();
        loader.setController(userController);
        createStage(loader);
    }

    public void showVIPUserWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Users/VipUser.fxml"));
        VipUserController vipController = new VipUserController();
        loader.setController(vipController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Social Media Hub");
        stage.show();
    }

    public void showCreateAccountWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CreateAccount.fxml"));
        VipUserController vipController = new VipUserController();
        loader.setController(vipController);
        createStage(loader);
    }
}
