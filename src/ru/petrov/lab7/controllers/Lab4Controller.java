package ru.petrov.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.petrov.lab4.utils.Explorer;
import ru.petrov.lab4.utils.FileEditor;
import ru.petrov.lab4.utils.SaveMode;
import ru.petrov.lab7.utils.DialogManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Lab4Controller implements Initializable {

    @FXML
    public ListView<String> itemsListView;

    @FXML
    public TextArea fileContentTextArea;

    @FXML
    public Button saveButton;

    @FXML
    public Button closeButton;

    @FXML
    public Label pathLabel;

    private Explorer explorer;
    private FileEditor editor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itemsListView.setOnKeyPressed(onKeyPressed());

        explorer = new Explorer();
        itemsListView.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setOnMouseClicked(onListItemClicked(cell));
            return cell;
        });
        fillItemListWithPaths();
        pathLabel.setText(explorer.getCurrentPath().toString());
        setEditorEnabled(false);
    }

    private EventHandler<? super KeyEvent> onKeyPressed() {
        return event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String path = itemsListView.getSelectionModel().getSelectedItem();
                updateState(path);
            }
        };
    }

    private EventHandler<MouseEvent> onListItemClicked(ListCell<String> cell) {
        return event -> {
            if (!cell.isEmpty() && event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                updateState(cell.getText());
            }
        };
    }

    private void updateState(String path) {
        if (Files.isDirectory(explorer.getCurrentPath().resolve(Path.of(path)))) {
            explorer.moveTo(path);
            fillItemListWithPaths();
            pathLabel.setText(explorer.getCurrentPath().toString());
        } else {
            editor = new FileEditor(explorer.getCurrentPath().resolve(path));
            try {
                editor.open();
                StringBuilder builder = new StringBuilder();
                editor.readContent().forEach(line -> builder.append(line).append('\n'));
                fileContentTextArea.setText(builder.toString());
                setEditorEnabled(true);
            } catch (FileNotFoundException e) {
                DialogManager.showDialog(
                    "Cannot perform operation",
                    e.getMessage(),
                    Alert.AlertType.ERROR
                );
            }
        }
    }

    private void setEditorEnabled(boolean enabled) {
        fileContentTextArea.setEditable(enabled);
        closeButton.setDisable(!enabled);
        saveButton.setDisable(!enabled);
    }

    private void fillItemListWithPaths() {
        try {
            List<String> items = new ArrayList<>();
            items.add("..");
            items.addAll(
                explorer
                    .getEverythingInDirectory()
                    .stream()
                    .map(Path::toString)
                    .collect(Collectors.toList()
                    )
            );
            itemsListView.getItems().setAll(items);

        } catch (IOException e) {
            DialogManager.showDialog(
                "Cannot perform operation",
                e.getMessage(),
                Alert.AlertType.ERROR
            );
        }
    }

    public void onCreateDirButtonClicked(ActionEvent actionEvent) {
        String dirName = askForDetails();
        if (dirName != null) {
            try {
                explorer.createDir(dirName);
            } catch (IOException e) {
                DialogManager.showDialog(
                    "Creation failed",
                    "Can not create directory",
                    Alert.AlertType.ERROR
                );
                return;
            }
            fillItemListWithPaths();
        }
    }

    public void onCreateFileButtonClicked(ActionEvent actionEvent) {
        String fileName = askForDetails();
        if (fileName != null) {
            try {
                explorer.createFile(fileName);
            } catch (IOException e) {
                DialogManager.showDialog(
                    "Creation failed",
                    "Can not create file",
                    Alert.AlertType.ERROR
                );
                return;
            }
        }
        fillItemListWithPaths();
    }

    private String askForDetails() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter name");
        dialog.setHeaderText("Please, enter name");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isBlank()) {
            return result.get();
        } else {
            return null;
        }
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {

        Path path = explorer.getCurrentPath().resolve(itemsListView.getSelectionModel().getSelectedItem());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Are you sure you want to delete \"" + itemsListView.getSelectionModel().getSelectedItem() + "\"");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                explorer.delete(path.toFile());
                fillItemListWithPaths();
            } catch (IOException e) {
                DialogManager.showDialog(
                    "Cannot perform operation",
                    e.getMessage(),
                    Alert.AlertType.ERROR
                );
            }
        }
    }

    public void onSaveButtonClicked(ActionEvent actionEvent) {
        try {
            editor.appendData(fileContentTextArea.getText());
            editor.save(SaveMode.REWRITE);
            onCloseButtonClicked(actionEvent);
        } catch (IOException e) {
            DialogManager.showDialog(
                "Cannot perform operation",
                e.getMessage(),
                Alert.AlertType.ERROR
            );
        }
    }

    public void onCloseButtonClicked(ActionEvent actionEvent) {
        try {
            editor.close();
        } catch (IOException e) {
            DialogManager.showDialog(
                "Cannot perform operation",
                e.getMessage(),
                Alert.AlertType.ERROR
            );
        }
        fileContentTextArea.clear();
        setEditorEnabled(false);
    }
}
