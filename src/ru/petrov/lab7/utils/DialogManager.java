package ru.petrov.lab7.utils;

import javafx.scene.control.Alert;

public class DialogManager {

    public static void showDialog(String title, String content, Alert.AlertType type) {
        Alert errorAlert = new Alert(type);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }
}
