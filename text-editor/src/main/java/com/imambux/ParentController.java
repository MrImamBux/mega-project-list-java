package com.imambux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ParentController implements Initializable {

    public static Stage stage;

    public MenuItem showStatusBar;
    public HBox statusBar;

    public TextArea textArea;

    public MenuItem editCut;
    public MenuItem editCopy;
    public MenuItem editPaste;
    public MenuItem editFind;

    public Label statusBarTotalCharacters;
    public Label statusBarWordsCount;

    public MenuItem fileOpen;
    public MenuItem fileNew;
    public MenuItem fileSave;
    public MenuItem fileExit;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        initFileMenuEvents();
        initViewMenuEvents();
        initEditMenuEvents();
        initStatusBarEvents();
    }

    private void initFileMenuEvents() {
        fileNew.setOnAction(event -> {
            stage.setTitle("Text Editor");
            textArea.clear();
        });
        fileOpen.setOnAction(event -> {
            FileChooser fileOpen = new FileChooser();
            fileOpen.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT files", "*.txt"),
                new FileChooser.ExtensionFilter("CSV files", "*.csv")
            );
            File selectedFile = fileOpen.showOpenDialog(stage);
            try {
                stage.setTitle(String.format("Text Editor - %s (%s)", selectedFile.getName(), selectedFile.getAbsolutePath()));
                BufferedReader fileReader = new BufferedReader(new FileReader(selectedFile));
                fileReader.lines().forEach(line -> textArea.appendText(String.format("%s\n", line)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        fileSave.setOnAction(event -> {
            FileChooser fileSave = new FileChooser();
            fileSave.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT files", "*.txt"),
                new FileChooser.ExtensionFilter("CSV files", "*.csv")
            );
            File result = fileSave.showSaveDialog(stage);
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(result));
                writer.print(textArea.getText());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileExit.setOnAction(event -> stage.close());
    }

    private void initStatusBarEvents() {
        statusBarTotalCharacters.setText("0");
        textArea.lengthProperty().addListener(
            (observable, oldValue, newValue) -> statusBarTotalCharacters.setText(String.valueOf(newValue))
        );
        statusBarWordsCount.setText("0");
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            int totalWords = textArea.getLength() == 0 ? 0 : newValue.split("[\\s\\n]+").length;
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
