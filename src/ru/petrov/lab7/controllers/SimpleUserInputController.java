package ru.petrov.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SimpleUserInputController {

    @FXML
    public Label titleLabel;

    @FXML
    public TextField userInputTextField;

    private String userInput;

    private boolean ok;

    String getUserInput() {
        return userInput;
    }

    void setTitle(String title) {
        titleLabel.setText(title);
    }

    boolean isOk() {
        return ok;
    }

    public void onCancelClicked(ActionEvent actionEvent) {
        ok = false;
        ((Stage) titleLabel.getScene().getWindow()).close();
    }

    public void onOkClicked(ActionEvent actionEvent) {
        ok = true;
        userInput = userInputTextField.getText();
        ((Stage) titleLabel.getScene().getWindow()).close();
    }
}
