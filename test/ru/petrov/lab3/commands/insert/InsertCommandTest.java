package ru.petrov.lab3.commands.insert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertCommandTest {

    @Test
    public void execute_worksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");
        StringBuilder control = new StringBuilder("abcdef");

        InsertCommand<Boolean> booleanInsertCommand = new InsertCommand<>(builder, 3, true);
        booleanInsertCommand.execute();
        control.insert(3, true);
        assertEquals(control.toString(), builder.toString());

        InsertCommand<Character> characterInsertCommand = new InsertCommand<>(builder, 3, 'c');
        characterInsertCommand.execute();
        control.insert(3, 'c');
        assertEquals(control.toString(), builder.toString());

        InsertCommand<Double> doubleInsertCommand = new InsertCommand<>(builder, 3, 1d);
        doubleInsertCommand.execute();
        control.insert(3, 1d);
        assertEquals(control.toString(), builder.toString());

        InsertCommand<Float> floatInsertCommand = new InsertCommand<>(builder, 3, 2f);
        floatInsertCommand.execute();
        control.insert(3, 2f);
        assertEquals(control.toString(), builder.toString());

        InsertCommand<Integer> integerInsertCommand = new InsertCommand<>(builder, 3, 3);
        integerInsertCommand.execute();
        control.insert(3, 3);
        assertEquals(control.toString(), builder.toString());

        InsertCommand<Long> longInsertCommand = new InsertCommand<>(builder, 3, 4L);
        longInsertCommand.execute();
        control.insert(3, 4L);
        assertEquals(control.toString(), builder.toString());

        Object obj = new Object();
        InsertCommand<Object> objectInsertCommand = new InsertCommand<>(builder, 3, obj);
        objectInsertCommand.execute();
        control.insert(3, obj);
        assertEquals(control.toString(), builder.toString());

        InsertCommand<String> stringInsertCommand = new InsertCommand<>(builder, 3, "lol");
        stringInsertCommand.execute();
        control.insert(3, "lol");
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    public void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");

        InsertCommand<Boolean> booleanInsertCommand = new InsertCommand<>(builder, 3, true);
        booleanInsertCommand.execute();
        booleanInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());

        InsertCommand<Character> characterInsertCommand = new InsertCommand<>(builder, 3, 'c');
        characterInsertCommand.execute();
        characterInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());

        InsertCommand<Double> doubleInsertCommand = new InsertCommand<>(builder, 3, 1d);
        doubleInsertCommand.execute();
        doubleInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());

        InsertCommand<Float> floatInsertCommand = new InsertCommand<>(builder, 3, 2f);
        floatInsertCommand.execute();
        floatInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());

        InsertCommand<Integer> integerInsertCommand = new InsertCommand<>(builder, 3, 3);
        integerInsertCommand.execute();
        integerInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());

        InsertCommand<Long> longInsertCommand = new InsertCommand<>(builder, 3, 4L);
        longInsertCommand.execute();
        longInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());

        Object obj = new Object();
        InsertCommand<Object> objectInsertCommand = new InsertCommand<>(builder, 3, obj);
        objectInsertCommand.execute();
        objectInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());

        InsertCommand<String> stringInsertCommand = new InsertCommand<>(builder, 3, "lol");
        stringInsertCommand.execute();
        stringInsertCommand.unExecute();
        assertEquals("abcdef", builder.toString());
    }
}