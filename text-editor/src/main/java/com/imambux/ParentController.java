package com.imambux;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;

public class ParentController implements Initializable {

    public MenuItem showStatusBar;
    public HBox statusBar;

    public TextArea textArea;

    public MenuItem editCut;
    public MenuItem editCopy;
    public MenuItem editPaste;
    public MenuItem editFind;

    public Label statusBarTotalCharacters;
    public Label statusBarWordsCount;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        initViewMenuEvents();
        initEditMenuEvents();
        initStatusBarEvents();
    }

    private void initStatusBarEvents() {
        statusBarTotalCharacters.setText("0");
        textArea.lengthProperty().addListener(
            (observable, oldValue, newValue) -> statusBarTotalCharacters.setText(String.valueOf(newValue))
        );
        statusBarWordsCount.setText("0");
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            int totalWords = newValue.split("[\\s\\n]+").length;
            statusBarWordsCount.setText(String.valueOf(totalWords));
        });
    }

    private void initEditMenuEvents() {
        editCut.setOnAction(event -> {
            textArea.cut();
        });
        editCopy.setOnAction(event ->{
            textArea.copy();
        });
        editPaste.setOnAction(event -> {
            textArea.paste();
        });
        editFind.setOnAction(event -> {
            TextInputDialog findTextDialog = new TextInputDialog();
            findTextDialog.setTitle("Text Finder");
            findTextDialog.setHeaderText("Enter text to find in the text area");
            findTextDialog.showAndWait();
            String result = findTextDialog.getResult();

            ObservableList<CharSequence> paragraphs = textArea.getParagraphs();
            int totalResultsFound = 0;
            for (CharSequence paragraph : paragraphs) {
                StringBuilder p = new StringBuilder(paragraph);
                int fromIndex = 0;
                while (fromIndex <= p.length() - 1) {
                    int resultIndex = p.indexOf(result, fromIndex);
                    if (resultIndex == -1) {
                        break;
                    } else {
                        totalResultsFound++;
                        fromIndex += resultIndex + 1;
                    }
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(String.format("Total results found: %d", totalResultsFound));
            alert.show();
        });
    }

    private void initViewMenuEvents() {
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
