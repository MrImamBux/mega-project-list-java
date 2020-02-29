package com.imambux;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class ParentController implements Initializable {

    public MenuItem showStatusBar;
    public HBox statusBar;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        initViewOptions();
    }

    private void initViewOptions() {
        showStatusBar.setOnAction(event -> {
            if (statusBar.isVisible()) {
                statusBar.setVisible(false);
            } else {
                statusBar.setVisible(true);
            }
            statusBar.managedProperty().bind(statusBar.visibleProperty());
        });
    }
}
