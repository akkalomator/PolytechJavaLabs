package ru.petrov.lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImprovedStringBuilderTest {

    @Test
    public void ImprovedStringBuilder_WorksAsRegular() {

        // Constructors
        StringBuilder stringBuilder = new StringBuilder();
        ImprovedStringBuilder improvedStringBuilder = new ImprovedStringBuilder();
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder = new StringBuilder(20);
        improvedStringBuilder = new ImprovedStringBuilder(20);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder = new StringBuilder("abba");
        improvedStringBuilder = new ImprovedStringBuilder("abba");
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        CharSequence cs = "abba";
        stringBuilder = new StringBuilder(cs);
        improvedStringBuilder = new ImprovedStringBuilder(cs);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());


        // append
        stringBuilder = new StringBuilder();
        improvedStringBuilder = new ImprovedStringBuilder();
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        Object o = new Object();
        stringBuilder.append(o);
        improvedStringBuilder.append(o);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        String s = "12345abcde";
        stringBuilder.append(s);
        improvedStringBuilder.append(s);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        StringBuffer sb = new StringBuffer("12345");
        sb.append("abcde");
        stringBuilder.append(sb);
        improvedStringBuilder.append(sb);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(cs);
        improvedStringBuilder.append(cs);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(cs, 1, 3);
        improvedStringBuilder.append(cs, 1, 3);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        char[] chars = {'q', 'w', 'e', 'r', 't', 'y'};
        stringBuilder.append(chars);
        improvedStringBuilder.append(chars);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(chars, 3, 2);
        improvedStringBuilder.append(chars, 3, 2);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(true);
        improvedStringBuilder.append(true);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append('z');
        improvedStringBuilder.append('z');
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(0);
        improvedStringBuilder.append(0);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(0L);
        improvedStringBuilder.append(0L);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(0f);
        improvedStringBuilder.append(0f);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.append(0d);
        improvedStringBuilder.append(0d);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.appendCodePoint(65);
        improvedStringBuilder.appendCodePoint(65);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());


        // Insert
        stringBuilder.insert(5, chars, 1, 3);
        improvedStringBuilder.insert(5, chars, 1, 3);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, o);
        improvedStringBuilder.insert(5, o);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, "str");
        improvedStringBuilder.insert(5, "str");
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, chars);
        improvedStringBuilder.insert(5, chars);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, cs);
        improvedStringBuilder.insert(5, cs);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, cs, 1, 3);
        improvedStringBuilder.insert(5, cs, 1, 3);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, true);
        improvedStringBuilder.insert(5, true);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, 'y');
        improvedStringBuilder.insert(5, 'y');
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, 3);
        improvedStringBuilder.insert(5, 3);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, 3L);
        improvedStringBuilder.insert(5, 3L);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, 3f);
        improvedStringBuilder.insert(5, 3f);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.insert(5, 3d);
        improvedStringBuilder.insert(5, 3d);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());


        //Others
        stringBuilder.delete(5, 10);
        improvedStringBuilder.delete(5, 10);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.deleteCharAt(5);
        improvedStringBuilder.deleteCharAt(5);
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        stringBuilder.replace(3, 5, "lol");
        improvedStringBuilder.replace(3, 5, "lol");
        assertEquals(stringBuilder.toString(), improvedStringBuilder.toString());

        assertEquals(stringBuilder.indexOf("lol"), improvedStringBuilder.indexOf("lol"));
        assertEquals(stringBuilder.indexOf("str", 4), improvedStringBuilder.indexOf("str", 4));

        assertEquals(stringBuilder.lastIndexOf("lol"), improvedStringBuilder.lastIndexOf("lol"));
        assertEquals(stringBuilder.lastIndexOf("str", 4), improvedStringBuilder.lastIndexOf("str", 4));

        assertEquals(stringBuilder.reverse().toString(), improvedStringBuilder.reverse().toString());

        assertEquals(stringBuilder.length(), improvedStringBuilder.length());

        assertEquals(stringBuilder.charAt(3), improvedStringBuilder.charAt(3));

        assertEquals(stringBuilder.subSequence(4, 8), improvedStringBuilder.subSequence(4, 8));
    }

    @Test
    public void undo_ThrowsWhenNothingToUndo() {
        ImprovedStringBuilder improvedStringBuilder = new ImprovedStringBuilder();
        assertThrows(IllegalStateException.class, improvedStringBuilder::undo);

        improvedStringBuilder.append("dfsf");
        improvedStringBuilder.undo();
        assertThrows(IllegalStateException.class, improvedStringBuilder::undo);
    }

    @Test
    public void undo_WorksCorrectly() {
        ImprovedStringBuilder improvedStringBuilder = new ImprovedStringBuilder();

        String str = "sdfdsf";
        improvedStringBuilder.append(str).append(14);
        improvedStringBuilder.undo();
        assertEquals(str, improvedStringBuilder.toString());

        improvedStringBuilder.insert(2, "adasd");
        improvedStringBuilder.undo();
        assertEquals(str, improvedStringBuilder.toString());

    }
}