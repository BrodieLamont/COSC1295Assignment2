module com.example.socialmediahub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.socialmediahub to javafx.fxml;
    exports com.example.socialmediahub;
}