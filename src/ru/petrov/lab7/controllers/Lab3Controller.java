package ru.petrov.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import ru.petrov.lab3.ImprovedStringBuilder;
import ru.petrov.lab7.utils.DialogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Lab3Controller implements Initializable {

    @FXML
    public TextArea contentTextArea;

    @FXML
    public TextField appendTextField;

    @FXML
    public TextField insertTextTextField;

    @FXML
    public TextField insertOffsetTextField;

    @FXML
    public TextField deleteIndexTextField;

    @FXML
    public TextField deleteFromTextField;

    @FXML
    public TextField deleteToTextField;

    @FXML
    public TextField replaceTextTextField;

    @FXML
    public TextField replaceFromTextField;

    @FXML
    public TextField replaceToTextField;

    private ImprovedStringBuilder builder;

    private String previousText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        builder = new ImprovedStringBuilder();
        previousText = "";
    }

    public void onNumericFieldKeyReleased(KeyEvent keyEvent) {
        TextField field = (TextField) keyEvent.getSource();
        String text = field.getText();
        try {
            int n = Integer.parseInt(text);
            previousText = text;
        } catch (NumberFormatException e) {
            field.setText(previousText);
        }
    }

    public void onAppendButtonClicked(ActionEvent actionEvent) {
        builder.append(appendTextField.getText());
        afterUpdate();
    }

    public void onInsertButtonClicked(ActionEvent actionEvent) {
        try {
            builder.insert(Integer.parseInt(insertOffsetTextField.getText()), insertTextTextField.getText());
            afterUpdate();
        } catch (IndexOutOfBoundsException e) {
            DialogManager.showDialog(
                "Cannot insert",
                "Offset is out of bounds",
                Alert.AlertType.ERROR
            );
        } catch (NumberFormatException e) {
            DialogManager.showDialog(
                "Cannot insert",
                "Invalid offset",
                Alert.AlertType.ERROR
            );
        }
    }

    public void onDeleteAtButtonClicked(ActionEvent actionEvent) {
        try {
            builder.deleteCharAt(Integer.parseInt(deleteIndexTextField.getText()));
            afterUpdate();
        } catch (IndexOutOfBoundsException e) {
            DialogManager.showDialog(
                "Cannot delete symbol",
                "Index is out of bounds",
                Alert.AlertType.ERROR
            );
        }
    }

    public void onDeleteFromToButtonClicked(ActionEvent actionEvent) {

        try {
            builder.delete(Integer.parseInt(deleteFromTextField.getText()), Integer.parseInt(deleteToTextField.getText()));
            afterUpdate();
        } catch (IndexOutOfBoundsException e) {
            DialogManager.showDialog(
                "Cannot delete symbol",
                "Indexes are out of bounds",
                Alert.AlertType.ERROR
            );
        }
    }

    public void onReplaceButtonClicked(ActionEvent actionEvent) {
        try {
            builder.replace(Integer.parseInt(replaceFromTextField.getText()), Integer.parseInt(replaceToTextField.getText()), replaceTextTextField.getText());
            afterUpdate();
        } catch (IndexOutOfBoundsException e) {
            DialogManager.showDialog(
                "Cannot replace",
                "Indexes are out of bounds",
                Alert.AlertType.ERROR
            );
        }
    }

    public void onReverseButtonCLicked(ActionEvent actionEvent) {
        builder.reverse();
        afterUpdate();
    }

    public void onUndoButtonClicked(ActionEvent actionEvent) {
        try {
            builder.undo();
        } catch (IllegalStateException e) {
            DialogManager.showDialog(
                "Cannot undo command",
                "Nothing has been done",
                Alert.AlertType.INFORMATION
            );
        }
        afterUpdate();
    }

    private void afterUpdate() {
        contentTextArea.setText(builder.toString());
    }
}

