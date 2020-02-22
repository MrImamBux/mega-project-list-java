module com.imambux {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.imambux to javafx.fxml;
    exports com.imambux;
}
