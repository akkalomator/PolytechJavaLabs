package ru.petrov.lab3;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;
import ru.petrov.lab3.commands.append.Append;
import ru.petrov.lab3.commands.append.AppendCharArray;
import ru.petrov.lab3.commands.append.AppendCharSequence;
import ru.petrov.lab3.commands.append.AppendCodePoint;
import ru.petrov.lab3.commands.delete.DeleteFromTo;
import ru.petrov.lab3.commands.insert.InsertCharArray;
import ru.petrov.lab3.commands.insert.InsertCharSequence;
import ru.petrov.lab3.commands.insert.InsertCommand;
import ru.petrov.lab3.commands.others.Replace;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImprovedStringBuilder implements Comparable<ImprovedStringBuilder>, CharSequence {

    private StringBuilder stringBuilder;
    private Deque<ImprovedStringBuilderCommand> commands;

    public ImprovedStringBuilder() {
        stringBuilder = new StringBuilder();
        commands = new ArrayDeque<>();
    }

    public ImprovedStringBuilder(int capacity) {
        stringBuilder = new StringBuilder(capacity);
        commands = new ArrayDeque<>();
    }

    public ImprovedStringBuilder(String str) {
        stringBuilder = new StringBuilder(str);
        commands = new ArrayDeque<>();
    }

    public ImprovedStringBuilder(CharSequence seq) {
        stringBuilder = new StringBuilder(seq);
        commands = new ArrayDeque<>();
    }

    public ImprovedStringBuilder append(Object obj) {
        Append<Object> command = new Append<>(stringBuilder, obj);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(String str) {
        Append<String> command = new Append<>(stringBuilder, str);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(StringBuffer sb) {
        Append<StringBuffer> command = new Append<>(stringBuilder, sb);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(CharSequence s) {
        AppendCharSequence command = new AppendCharSequence(stringBuilder, s, 0, s.length());
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(CharSequence s, int start, int end) {
        AppendCharSequence command = new AppendCharSequence(stringBuilder, s, start, end);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(char[] str) {
        AppendCharArray command = new AppendCharArray(stringBuilder, str, 0, str.length);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(char[] str, int offset, int len) {
        AppendCharArray command = new AppendCharArray(stringBuilder, str, offset, len);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(boolean b) {
        Append<Boolean> command = new Append<>(stringBuilder, b);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(char c) {
        Append<Character> command = new Append<>(stringBuilder, c);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(int i) {
        Append<Integer> command = new Append<>(stringBuilder, i);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(long lng) {
        Append<Long> command = new Append<>(stringBuilder, lng);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(float f) {
        Append<Float> command = new Append<>(stringBuilder, f);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder append(double d) {
        Append<Double> command = new Append<>(stringBuilder, d);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder appendCodePoint(int codePoint) {
        AppendCodePoint command = new AppendCodePoint(stringBuilder, codePoint);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int index, char[] str, int offset, int len) {
        InsertCharArray command = new InsertCharArray(stringBuilder, index, str, offset, len);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, Object obj) {
        InsertCommand<Object> command = new InsertCommand<>(stringBuilder, offset, obj);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, String str) {
        InsertCommand<String> command = new InsertCommand<>(stringBuilder, offset, str);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, char[] str) {
        InsertCharArray command = new InsertCharArray(stringBuilder, offset, str, 0, str.length);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int dstOffset, CharSequence s) {
        InsertCharSequence command = new InsertCharSequence(stringBuilder, dstOffset, s, 0, s.length());
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        InsertCharSequence command = new InsertCharSequence(stringBuilder, dstOffset, s, start, end);
        command.execute();
        commands.push(command);
        return this;
    }


    public ImprovedStringBuilder insert(int offset, boolean b) {
        InsertCommand<Boolean> command = new InsertCommand<>(stringBuilder, offset, b);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, char c) {
        InsertCommand<Character> command = new InsertCommand<>(stringBuilder, offset, c);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, int i) {
        InsertCommand<Integer> command = new InsertCommand<>(stringBuilder, offset, i);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, long l) {
        InsertCommand<Long> command = new InsertCommand<>(stringBuilder, offset, l);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, float f) {
        InsertCommand<Float> command = new InsertCommand<>(stringBuilder, offset, f);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, double d) {
        InsertCommand<Double> command = new InsertCommand<>(stringBuilder, offset, d);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder delete(int start, int end) {
        DeleteFromTo command = new DeleteFromTo(stringBuilder, start, end);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder deleteCharAt(int index) {
        DeleteFromTo command = new DeleteFromTo(stringBuilder, index, index + 1);
        command.execute();
        commands.push(command);
        return this;
    }

    public ImprovedStringBuilder replace(int start, int end, String str) {
        Replace command = new Replace(stringBuilder, start, end, str);
        command.execute();
        commands.push(command);
        return this;
    }

    public int indexOf(String str) {
        return stringBuilder.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return stringBuilder.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return stringBuilder.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return stringBuilder.lastIndexOf(str, fromIndex);
    }

    public ImprovedStringBuilder reverse() {
        stringBuilder.reverse();
        return this;
    }

    @Override
    public int length() {
        return stringBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return stringBuilder.subSequence(start, end);
    }

    public String toString() {
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(ImprovedStringBuilder o) {
        return stringBuilder.compareTo(o.stringBuilder);
    }

    public void undo() {
        if (commands.isEmpty()) {
            throw new IllegalStateException("No command were executed yer");
        }
        ImprovedStringBuilderCommand command = commands.pop();
        command.unExecute();
    }
}
