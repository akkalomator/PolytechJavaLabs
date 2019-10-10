package ru.petrov.lab7.statemachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateMachineTest {

    private StateMachine machine;
    private int variable;

    class StateImpl extends State {

        private int num;

        StateImpl(StateMachine machine, int num) {
            super(machine);
            this.num = num;
        }

        @Override
        public void update() {
            variable = num;
            machine.deleteTopState();
        }
    }

    @BeforeEach
    void initialize() {
        machine = new StateMachine(null);
        variable = 0;
    }

    @Test
    void getCurrentState_ThrowsOnNoStatesInMachine() {
        assertThrows(IllegalStateException.class, () -> machine.getCurrentState());

        State state = new StateImpl(machine, 42);
        machine.addState(state);
        machine.deleteTopState();

        assertThrows(IllegalStateException.class, () -> machine.getCurrentState());
    }

    @Test
    void getCurrentState_WorksCorrectly() {
        State state1 = new StateImpl(machine, 42);
        machine.addState(state1);
        assertEquals(state1, machine.getCurrentState());

        State state2 = new StateImpl(machine, 42);
        machine.addState(state2);
        assertEquals(state2, machine.getCurrentState());
    }

    @Test
    void addState_ThrowsOnStateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> machine.addState(null));
    }

    @Test
    void addState_WorksCorrectly() {
        State state = new StateImpl(machine, 42);
        machine.addState(state);
        assertEquals(state, machine.getCurrentState());
    }

    @Test
    void deleteState_ThrowsOnNoStatesInMachine() {
        assertThrows(IllegalStateException.class, () -> machine.deleteTopState());

        State state = new StateImpl(machine, 42);
        machine.addState(state);
        machine.deleteTopState();

        assertThrows(IllegalStateException.class, () -> machine.deleteTopState());
    }

    @Test
    void deleteState_WorksCorrectly() {
        State state1 = new StateImpl(machine, 42);
        machine.addState(state1);
        machine.deleteTopState();
        assertThrows(IllegalStateException.class, () -> machine.getCurrentState());

        State state2 = new StateImpl(machine, 42);
        machine.addState(state2);
        State state3 = new StateImpl(machine, 42);
        machine.addState(state3);

        machine.deleteTopState();

        assertEquals(state2, machine.getCurrentState());
    }

    @Test
    void update_ThrowsOnNoStatesInMachine() {
        assertThrows(IllegalStateException.class, () -> machine.update());

        State state1 = new StateImpl(machine, 42);
        machine.addState(state1);
        machine.deleteTopState();

        assertThrows(IllegalStateException.class, () -> machine.update());
    }

    @Test
    void update_WorksCorrectly() {
        State state1 = new StateImpl(machine, 42);
        machine.addState(state1);
        machine.update();
        assertEquals(42, variable);
        State state2 = new StateImpl(machine, 1337);
        machine.addState(state2);
        State state3 = new StateImpl(machine, 146);
        machine.addState(state3);
        machine.update();
        assertEquals(146, variable);
    }
}