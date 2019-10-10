package ru.petrov.lab7.statemachine;

public abstract class State {

    protected StateMachine machine;

    public State(StateMachine machine) {
        this.machine = machine;
    }

    public abstract void update();
}
