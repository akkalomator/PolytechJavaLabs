package ru.petrov.lab3.commands.append;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppendTest {

    @Test
    public void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abc");

        Append<Boolean> booleanAppend = new Append<>(builder, true);
        booleanAppend.execute();
        assertEquals("abctrue", builder.toString());

        Append<Character> characterAppend = new Append<>(builder, 'c');
        characterAppend.execute();
        assertEquals("abctruec", builder.toString());

        Append<Double> doubleAppend = new Append<>(builder, 1d);
        doubleAppend.execute();
        assertEquals("abctruec1.0", builder.toString());

        Append<Float> floatAppend = new Append<>(builder, 2f);
        floatAppend.execute();
        assertEquals("abctruec1.02.0", builder.toString());

        Append<Integer> integerAppend = new Append<>(builder, 3);
        integerAppend.execute();
        assertEquals("abctruec1.02.03", builder.toString());

        Append<Long> longAppend = new Append<>(builder, 4L);
        longAppend.execute();
        assertEquals("abctruec1.02.034", builder.toString());

        Object obj = new Object();
        Append<Object> objectAppend = new Append<>(builder, obj);
        objectAppend.execute();
        assertEquals("abctruec1.02.034" + obj.toString(), builder.toString());

        Append<String> stringAppend = new Append<>(builder, "xyz");
        stringAppend.execute();
        assertEquals("abctruec1.02.034" + obj.toString() + "xyz", builder.toString());

        Append<StringBuffer> stringBufferAppend = new Append<>(builder, new StringBuffer("buffered"));
        stringBufferAppend.execute();
        assertEquals("abctruec1.02.034" + obj.toString() + "xyzbuffered", builder.toString());
    }

    @Test
    public void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abc");
        Append<Boolean> command = new Append<>(builder, true);
        command.execute();
        command.unExecute();
        assertEquals("abc", builder.toString());
    }
}