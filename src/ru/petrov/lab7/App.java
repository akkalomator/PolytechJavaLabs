package ru.petrov.lab7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {

    public App() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Java labs");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("resources/lab7/layout/main-menu.fxml").toURL());
        TabPane gridPane = loader.load();

        Scene scene = new Scene(gridPane);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
