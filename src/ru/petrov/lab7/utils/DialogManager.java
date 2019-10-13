package ru.petrov.lab7.utils;

import javafx.scene.control.Alert;

public class DialogManager {

    public static void showDialog(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
