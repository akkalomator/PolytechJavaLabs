package ru.petrov.lab3;

public class ImprovedStringBuilder implements Comparable<ImprovedStringBuilder>, CharSequence {

    private StringBuilder stringBuilder;

    public ImprovedStringBuilder() {
        stringBuilder = new StringBuilder();
    }

    public ImprovedStringBuilder(int capacity) {
        stringBuilder = new StringBuilder(capacity);
    }

    public ImprovedStringBuilder(String str) {
        stringBuilder = new StringBuilder(str);
    }

    public ImprovedStringBuilder(CharSequence seq) {
        stringBuilder = new StringBuilder(seq);
    }

    public ImprovedStringBuilder append(Object obj) {
        stringBuilder.append(obj);
        return this;
    }

    public ImprovedStringBuilder append(String str) {
        stringBuilder.append(str);
        return this;
    }

    public ImprovedStringBuilder append(StringBuffer sb) {
        stringBuilder.append(sb);
        return this;
    }

    public ImprovedStringBuilder append(CharSequence s) {
        stringBuilder.append(s);
        return this;
    }

    public ImprovedStringBuilder append(CharSequence s, int start, int end) {
        stringBuilder.append(s, start, end);
        return this;
    }

    public ImprovedStringBuilder append(char[] str) {
        stringBuilder.append(str);
        return this;
    }

    public ImprovedStringBuilder append(char[] str, int offset, int len) {
        stringBuilder.append(str, offset, len);
        return this;
    }

    public ImprovedStringBuilder append(boolean b) {
        stringBuilder.append(b);
        return this;
    }

    public ImprovedStringBuilder append(char c) {
        stringBuilder.append(c);
        return this;
    }

    public ImprovedStringBuilder append(int i) {
        stringBuilder.append(i);
        return this;
    }

    public ImprovedStringBuilder append(long lng) {
        stringBuilder.append(lng);
        return this;
    }

    public ImprovedStringBuilder append(float f) {
        stringBuilder.append(f);
        return this;
    }

    public ImprovedStringBuilder append(double d) {
        stringBuilder.append(d);
        return this;
    }

    public ImprovedStringBuilder appendCodePoint(int codePoint) {
        stringBuilder.appendCodePoint(codePoint);
        return this;
    }

    public ImprovedStringBuilder insert(int index, char[] str, int offset, int len) {
        stringBuilder.insert(index, str, offset, len);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, Object obj) {
        stringBuilder.insert(offset, obj);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, char[] str) {
        stringBuilder.insert(offset, str);
        return this;
    }

    public ImprovedStringBuilder insert(int dstOffset, CharSequence s) {
        stringBuilder.insert(dstOffset, s);
        return this;
    }

    public ImprovedStringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        stringBuilder.insert(dstOffset, s, start, end);
        return this;
    }


    public ImprovedStringBuilder insert(int offset, boolean b) {
        stringBuilder.insert(offset, b);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, char c) {
        stringBuilder.insert(offset, c);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, int i) {
        stringBuilder.insert(offset, i);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, long l) {
        stringBuilder.insert(offset, l);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, float f) {
        stringBuilder.insert(offset, f);
        return this;
    }

    public ImprovedStringBuilder insert(int offset, double d) {
        stringBuilder.insert(offset, d);
        return this;
    }

    public ImprovedStringBuilder delete(int start, int end) {
        stringBuilder.delete(start, end);
        return this;
    }

    public ImprovedStringBuilder deleteCharAt(int index) {
        stringBuilder.deleteCharAt(index);
        return this;
    }

    public ImprovedStringBuilder replace(int start, int end, String str) {
        stringBuilder.replace(start, end, str);
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

    }
}
