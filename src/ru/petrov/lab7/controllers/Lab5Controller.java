package ru.petrov.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import ru.petrov.lab5.PropertiesParser;
import ru.petrov.lab7.utils.DialogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Lab5Controller implements Initializable {

    public static class KeyValuePair<TKey, TValue> {

        private TKey key;

        private TValue value;

        private KeyValuePair(TKey key, TValue value) {
            this.key = key;
            this.value = value;
        }

        public TKey getKey() {
            return key;
        }

        public TValue getValue() {
            return value;
        }

    }

    @FXML
    public TableView<KeyValuePair<String, String>> keyValueTableView;

    @FXML
    public TextField pathTextField;

    @FXML
    public TableColumn<KeyValuePair<String, String>, String> keyColumn;

    @FXML
    public TableColumn<KeyValuePair<String, String>, String> valueColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    public void onOpenButtonClicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(pathTextField.getScene().getWindow());
        if (file != null) {
            pathTextField.setText(file.getPath());
        }
    }

    public void onLoadButtonClicked(ActionEvent actionEvent) {
        try (FileInputStream stream = new FileInputStream(pathTextField.getText())) {
            PropertiesParser parser = new PropertiesParser(stream);
            parser.parse();

            keyValueTableView.getItems().setAll(
                parser.getProperties()
                    .entrySet()
                    .stream()
                    .map(entry -> {
                        return new KeyValuePair<>(entry.getKey(), entry.getValue());
                    })
                .collect(Collectors.toList())
            );

        } catch (IOException e) {
            DialogManager.showDialog(
                "Error",
                "File not found:\n" + pathTextField.getText(),
                Alert.AlertType.ERROR
            );
        } catch (ParseException e) {
            DialogManager.showDialog(
                "Error",
                "Malformed data\n" + e.getMessage(),
                Alert.AlertType.ERROR
            );
        }
    }
}
