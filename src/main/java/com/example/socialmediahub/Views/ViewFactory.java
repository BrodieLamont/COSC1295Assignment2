package com.example.socialmediahub.Views;

import com.example.socialmediahub.Controllers.CreateAccountController;
import com.example.socialmediahub.Controllers.Users.UserController;
import com.example.socialmediahub.Controllers.Users.VipUserController;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/*
This class was developed using code modified from https://www.youtube.com/watch?v=lkov5shwRQs
 */

public class ViewFactory {
    //User Views

    private AnchorPane dashboardView;
    private AnchorPane postView;
    private AnchorPane graphView;
    private AnchorPane exportView;
    private AnchorPane importView;
    private AnchorPane addPost;
    private AnchorPane removePost;
    private AnchorPane editProfileView;
    private BorderPane directoryWindow;
    private StringProperty userSelection;


    public ViewFactory() {
        this.userSelection = new SimpleStringProperty("");
    }

    public StringProperty getUserSelection() {
        return userSelection;
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

    public AnchorPane getPostView() {
        if (postView == null) {
            try {
                postView = new FXMLLoader(getClass().getResource("/Fxml/Users/ViewPost.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return postView;
    }

    public AnchorPane getEditProfileView() {
        if (editProfileView == null) {
            try {
                editProfileView = new FXMLLoader(getClass().getResource("/Fxml/Users/EditProfile.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return editProfileView;
    }

    public AnchorPane getGraphView() {
        if (graphView == null) {
            try {
                graphView = new FXMLLoader(getClass().getResource("/Fxml/Users/Graph.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return graphView;
    }

    public AnchorPane getAddPost() {
        if (addPost == null) {
            try {
                addPost = new FXMLLoader(getClass().getResource("/Fxml/Users/AddPosts.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return addPost;
    }

    public AnchorPane getRemovePost() {
        if (removePost == null) {
            try {
                removePost = new FXMLLoader(getClass().getResource("/Fxml/Users/RemovePosts.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return removePost;
    }

    public AnchorPane getImportView() {
        if (importView == null) {
            try {
                importView = new FXMLLoader(getClass().getResource("/Fxml/Users/Import.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return importView;
    }

    public AnchorPane getExportView() {
        if (exportView == null) {
            try {
                exportView = new FXMLLoader(getClass().getResource("/Fxml/Users/Export.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return exportView;
    }

    public BorderPane getDirectoryWindow() {
        if (directoryWindow == null) {
            try {
                directoryWindow = new FXMLLoader(getClass().getResource("/Fxml/Users/DirectoryChooser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return directoryWindow;
    }


    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        createStage(loader);
    }

    public void showDirectoryWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/DirectoryChooser.fxml"));
        createStage(loader);
    }

    public void showUserWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Users/User.fxml"));
        UserController userController = new UserController();
        loader.setController(userController);
        createStage(loader);
    }

    public void showVIPUserWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Users/User.fxml"));
        VipUserController vipController = new VipUserController();
        loader.setController(vipController);
        createStage(loader);
    }

    public void showCreateAccountWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CreateAccount.fxml"));
        CreateAccountController createAccountController = new CreateAccountController();
        loader.setController(createAccountController);
        createStage(loader);
    }

    public void showPostView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Users/PostView.fxml"));
        CreateAccountController createAccountController = new CreateAccountController();
        loader.setController(createAccountController);
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

    public void closeStage(Stage stage) {
        stage.close();
    }

}
