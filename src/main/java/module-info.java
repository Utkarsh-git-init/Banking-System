module com.example.bankingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;


    opens com.example.bankingsystem to javafx.fxml;
    exports com.example.bankingsystem;
}