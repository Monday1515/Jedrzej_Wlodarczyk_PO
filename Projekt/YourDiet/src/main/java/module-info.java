module com.example.yourdiet {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires jakarta.mail;

    opens com.example.yourdiet to javafx.fxml;
    exports com.example.yourdiet;
}