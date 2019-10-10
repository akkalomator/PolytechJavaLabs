package ru.petrov.lab7.statemachine;

import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;

public class StateMachine {

    private Deque<State> states;
    private Stage primaryStage;

    public StateMachine(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.states = new ArrayDeque<>();
    }

    public void addState(State state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        states.push(state);
    }

    public State getCurrentState() {
        if (states.isEmpty()) {
            throw new IllegalStateException("No states left");
        }
        return states.peek();
    }

    public void deleteTopState() {
        if (states.isEmpty()) {
            throw new IllegalStateException("No states left");
        }
        states.pop();
    }

    public void update() {
        if (states.isEmpty()) {
            throw new IllegalStateException("No states left");
        }
        states.peek().update();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
