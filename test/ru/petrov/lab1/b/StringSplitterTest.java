package ru.petrov.lab1.b;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringSplitterTest {

    @Test
    void splitAndReplace_ThrowsOnStringIsNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitter.splitAndReplace(null, 3, 0)
        );
    }

    @Test
    void splitAndReplace_ThrowsOnGroupLengthLessThanOne() {
        assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitter.splitAndReplace("dsf", -1, 0)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitter.splitAndReplace("dsf", 0, 0)
        );
    }

    @Test
    void splitAndReplace_ThrowsOnReplaceIndexLessThanZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitter.splitAndReplace("dsf", 3, -1)
        );
    }

    @Test
    void splitAndReplace_ThrowsOnReplaceIndexExceedsGroupLength() {
        assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitter.splitAndReplace("dsf", 3, 3)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> StringSplitter.splitAndReplace("dsf", 3, 4)
        );
    }

    @Test
    void splitAndReplace_ReturnsListOfSingleEmptyStringWhenEmptyStringIsPassed() {
        assertEquals(
            List.of(""),
            StringSplitter.splitAndReplace("", 1, 0)
        );
    }


    /**
     * abcdefg -> a?c d?f g
     */
    @Test
    void splitAndReplace_WorksCorrectly_abcdefg() {
        String s = "abcdefg";
        List<String> result = StringSplitter.splitAndReplace(s, 3, 1);
        assertEquals(3, result.size());

        String first = result.get(0);
        assertEquals(3, first.length());
        assertEquals('a', first.charAt(0));
        assertFalse("abc".contains(String.valueOf(first.charAt(1))));
        assertEquals('c', first.charAt(2));

        String second = result.get(1);
        assertEquals(3, second.length());
        assertEquals('d', second.charAt(0));
        assertFalse("def".contains(String.valueOf(second.charAt(1))));
        assertEquals('f', second.charAt(2));

        String third = result.get(2);
        assertEquals(1, third.length());
        assertEquals('g', third.charAt(0));
    }

    /**
     * abcdefgh -> a?c d?f g?
     */
    @Test
    void splitAndReplace_WorksCorrectly_abcdefgh() {
        String s = "abcdefgh";
        List<String> result = StringSplitter.splitAndReplace(s, 3, 1);
        assertEquals(3, result.size());

        String first = result.get(0);
        assertEquals(3, first.length());
        assertEquals('a', first.charAt(0));
        assertFalse("abc".contains(String.valueOf(first.charAt(1))));
        assertEquals('c', first.charAt(2));

        String second = result.get(1);
        assertEquals(3, second.length());
        assertEquals('d', second.charAt(0));
        assertFalse("def".contains(String.valueOf(second.charAt(1))));
        assertEquals('f', second.charAt(2));

        String third = result.get(2);
        assertEquals(2, third.length());
        assertEquals('g', third.charAt(0));
        assertFalse("gh".contains(String.valueOf(second.charAt(1))));
    }

    /**
     * abcdefghi -> a?c d?f g?i
     */
    @Test
    void splitAndReplace_WorksCorrectly_abcdefghi() {
        String s = "abcdefghi";
        List<String> result = StringSplitter.splitAndReplace(s, 3, 1);
        assertEquals(3, result.size());

        String first = result.get(0);
        assertEquals(3, first.length());
        assertEquals('a', first.charAt(0));
        assertFalse("abc".contains(String.valueOf(first.charAt(1))));
        assertEquals('c', first.charAt(2));

        String second = result.get(1);
        assertEquals(3, second.length());
        assertEquals('d', second.charAt(0));
        assertFalse("def".contains(String.valueOf(second.charAt(1))));
        assertEquals('f', second.charAt(2));

        String third = result.get(2);
        assertEquals(3, third.length());
        assertEquals('g', third.charAt(0));
        assertFalse("gh".contains(String.valueOf(second.charAt(1))));
        assertEquals('i', third.charAt(2));
    }

    /**
     * abczefoh -> a?c o? z?f
     */
    @Test
    void labTask() {
        String s = "abczefoh";
        List<String> result = StringSplitter.labTask(s);

        String first = result.get(0);
        assertEquals(3, first.length());
        assertEquals('a', first.charAt(0));
        assertFalse("abc".contains(String.valueOf(first.charAt(1))));
        assertEquals('c', first.charAt(2));

        String second = result.get(1);
        assertEquals(2, second.length());
        assertEquals('o', second.charAt(0));
        assertFalse("oh".contains(String.valueOf(second.charAt(1))));

        String third = result.get(2);
        assertEquals(3, third.length());
        assertEquals('z', third.charAt(0));
        assertFalse("zef".contains(String.valueOf(second.charAt(1))));
        assertEquals('f', third.charAt(2));
    }
}