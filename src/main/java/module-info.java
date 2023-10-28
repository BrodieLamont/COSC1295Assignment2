module com.example.socialmediahub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.rbl.socialmediahub to javafx.fxml;
    exports com.rbl.socialmediahub;
    exports com.rbl.socialmediahub.Controllers;
    opens com.rbl.socialmediahub.Controllers to javafx.fxml;
}