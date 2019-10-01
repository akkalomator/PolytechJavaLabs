package ru.petrov.lab1.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringSplitter {

    public static List<String> splitAndReplace(String s, int groupLength, int replaceIndex) {

        if (s == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        if (groupLength < 1) {
            throw new IllegalArgumentException("Group length must be greater than zero");
        }
        if (replaceIndex < 0) {
            throw new IllegalArgumentException("Replace index must be non-negative");
        }
        if (replaceIndex >= groupLength) {
            throw new IllegalArgumentException("Replace index must be less than group length");
        }
        if (s.isEmpty()) {
            return List.of("");
        }

        List<String> splitted = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < s.length(); i += groupLength) {
            int length = Math.min(groupLength, s.length() - i);
            String newString = s.substring(i, i + length);
            char[] chars = newString.toCharArray();
            if (chars.length > replaceIndex) {
                char charToReplace = chars[replaceIndex];
                while (newString.contains(String.valueOf(charToReplace))) {
                    charToReplace = (char) random.nextInt(Character.MAX_CODE_POINT);
                }
                chars[replaceIndex] = charToReplace;
            }
            splitted.add(String.valueOf(chars));
        }
        return splitted;
    }

    static List<String> labTask(String s) {
        List<String> strings = splitAndReplace(s, 3, 1);
        strings.sort(String::compareTo);
        return strings;
    }
}
