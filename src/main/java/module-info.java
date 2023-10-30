module com.example.socialmediahub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.opencsv;


    opens com.example.socialmediahub to javafx.fxml;
    exports com.example.socialmediahub;
    exports com.example.socialmediahub.Controllers;
    exports com.example.socialmediahub.Controllers.Users;
    exports com.example.socialmediahub.Models;
    exports com.example.socialmediahub.Views;
    opens com.example.socialmediahub.Controllers to javafx.fxml;
    opens com.example.socialmediahub.Controllers.Users to javafx.fxml;
    opens com.example.socialmediahub.Models to javafx.fxml;
}