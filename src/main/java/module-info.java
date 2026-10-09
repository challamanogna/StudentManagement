module com.example.placements {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens com.example.placements to javafx.fxml;
    exports com.example.placements;
}