package ru.petrov.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import ru.petrov.lab2.animals.Animal;
import ru.petrov.lab2.animals.Carnivore;
import ru.petrov.lab2.animals.FoodType;
import ru.petrov.lab2.animals.Herbivorous;
import ru.petrov.lab2.animals.Omnivorous;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Lab2Controller implements Initializable {

    @FXML
    public TableView<Animal> animalsTable;

    @FXML
    public TableColumn<Animal, Integer> idColumn;

    @FXML
    public TableColumn<Animal, FoodType> typeColumn;

    @FXML
    public TableColumn<Animal, String> nameColumn;

    @FXML
    public TableColumn<Animal, Integer> foodAmountColumn;

    @FXML
    public ComboBox<FoodType> foodTypeComboBox;

    @FXML
    public TextField nameField;

    @FXML
    public TextField foodAmountField;

    @FXML
    public TextField pathField;

    private List<Animal> animals;

    public Lab2Controller() {
        this.animals = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodAmountColumn.setCellValueFactory(new PropertyValueFactory<>("foodAmount"));

        foodTypeComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(FoodType object) {
                if (object == null) {
                    return "";
                }
                return object.toString();
            }

            @Override
            public FoodType fromString(String string) {
                switch (string) {
                    case "ANIMALS": {
                        return FoodType.ANIMALS;
                    }
                    case "PLANTS": {
                        return FoodType.PLANTS;
                    }
                    case "EVERYTHING": {
                        return FoodType.EVERYTHING;
                    }
                    default: {
                        throw new IllegalArgumentException("Unknown type: " + string);
                    }
                }
            }
        });
        foodTypeComboBox.getItems().setAll(EnumSet.allOf(FoodType.class));
        foodTypeComboBox.getSelectionModel().selectFirst();
    }

    public void onAddButtonClicked(ActionEvent actionEvent) {

        FoodType foodType = foodTypeComboBox.getValue();

        String name = nameField.getText();
        if (name.isBlank()) {
            showErrorDialog("Name is not valid", "Name can not be \"\"");
            return;
        }
        String foodAmountStr = foodAmountField.getText();
        if (!foodAmountStr.matches("(\\d)+")) {
            showErrorDialog("Food amount is not valid", "Food amount can not be " + foodAmountStr);
            return;
        }

        Animal animal;
        switch (foodType) {
            case PLANTS:
                animal = new Herbivorous(name, Integer.parseInt(foodAmountStr));
                break;
            case ANIMALS:
                animal = new Carnivore(name, Integer.parseInt(foodAmountStr));
                break;
            case EVERYTHING:
                animal = new Omnivorous(name, Integer.parseInt(foodAmountStr));
                break;
            default:
                throw new IllegalArgumentException("Cannot resolve type " + foodType);
        }

        animals.add(animal);
        animalsTable.getItems().add(animal);
    }

    private void showErrorDialog(String title, String content) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }

    public void onAButtonClicked(ActionEvent actionEvent) {
        animals = animals
            .stream()
            .sorted(
                Comparator.comparingInt(Animal::getFoodAmount).reversed()
                    .thenComparing(Animal::getName)
            )
            .collect(Collectors.toList());
        animalsTable.getItems().setAll(animals);
    }

    public void onBButtonClicked(ActionEvent actionEvent) {
        StringBuilder builder = new StringBuilder();
        animals
            .stream()
            .limit(5).forEach((animal) -> builder.append(animal.getName()).append('\n'));
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setHeaderText("Task A");
        infoAlert.setContentText(builder.toString());
        infoAlert.showAndWait();
    }

    public void onCButtonClicked(ActionEvent actionEvent) {
        StringBuilder builder = new StringBuilder();
        animals
            .stream()
            .skip(animals.size() - 3)
            .forEach(animal -> builder.append(animal.getId()).append('\n'));
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setHeaderText("Task A");
        infoAlert.setContentText(builder.toString());
        infoAlert.showAndWait();
    }

    public void onOpenButtonClicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(nameField.getScene().getWindow());
        if (file != null) {
            pathField.setText(file.getPath());
        }
    }

    public void onLoadButtonClicked(ActionEvent actionEvent) {
        if (pathField.getText().isBlank()) {
            return;
        }
        try (FileReader fr = new FileReader(pathField.getText())) {
            animals = ru.petrov.lab2.Main.readFromFile(fr);
            animalsTable.getItems().setAll(animals);
        } catch (IOException e) {
            showErrorDialog("Reading error", "Cannot read from file \"" + pathField.getText() + "\".\n" + e.getMessage());
        }
    }

    public void onSaveButtonClicked(ActionEvent actionEvent) {
        if (pathField.getText().isBlank()) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        animals.forEach((animal -> builder.append(animal).append('\n')));
        try {
            ru.petrov.lab2.Main.writeTo(pathField.getText(), builder.toString());
        } catch (IOException e) {
            showErrorDialog("Writing error", "Cannot write to file \"" + pathField.getText() + "\".\n" + e.getMessage());
        }
    }
}
