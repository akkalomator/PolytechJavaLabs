package ru.petrov.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class PropertiesParser {
    private final InputStream inputStream;
    private Map<String, String> properties;
    private boolean precedesWithBackSlash;
    private String storedKey;
    private StringBuilder valueBuilder;

    public PropertiesParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() throws ParseException, IOException {
        try (
            InputStreamReader r = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(r)
        ) {
            properties = new HashMap<>();
            valueBuilder = new StringBuilder();
            try {
                reader.lines().forEach(rawLine -> {
                    try {
                        parseLine(rawLine);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (RuntimeException e) {
                if (e.getCause() instanceof ParseException) {
                    throw (ParseException) e.getCause();
                } else {
                    throw e;
                }
            }
        }
    }

    private void parseLine(String rawLine) throws ParseException {
        String line = rawLine.trim();

        if (line.startsWith("!") || line.startsWith("#") || line.isBlank()) {
            return;
        }

        if (precedesWithBackSlash) {
            precedesWithBackSlash = false;
            valueBuilder.append(parseValue(line, 0));
            if (precedesWithBackSlash) {
                return;
            }
            properties.put(storedKey, valueBuilder.toString());
            return;
        }

        if (valueBuilder.length() != 0) {
            valueBuilder = new StringBuilder();
        }

        int lastIndex;
        for (lastIndex = 0; lastIndex < line.length(); lastIndex++) {
            char c = line.charAt(lastIndex);
            if (c == ':' || c == '=') {
                break;
            }
            if (c == '\\') {
                precedesWithBackSlash = true;
                continue;
            }
            if (c == ' ' && precedesWithBackSlash) {
                precedesWithBackSlash = false;
                continue;
            }
        }

        if (lastIndex == line.length()) {
            throw new ParseException("No value found at line " + line, 0);
        }

        String key = parseKey(line, lastIndex);
        String value = parseValue(line, lastIndex + 1);

        if (precedesWithBackSlash) {
            storedKey = key;
            valueBuilder.append(value).deleteCharAt(valueBuilder.length() - 1);
        }
        properties.put(key, value);
    }

    private String parseKey(String line, int keyLastIndex) throws ParseException {
        String key = line.substring(0, keyLastIndex).trim();
        if (!key.contains(" ")) {
            return key;
        }
        StringBuilder keyBuilder = new StringBuilder(key);
        for (int i = 0; i < keyBuilder.length(); i++) {
            if (keyBuilder.charAt(i) == ' ') {
                if (keyBuilder.charAt(i - 1) != '\\') {
                    throw new ParseException("Invalid key at line " + line, i);
                }
                keyBuilder.deleteCharAt(i - 1);
            }
        }
        return keyBuilder.toString();

    }

    private String parseValue(String line, int valueFirstIndex) throws ParseException {
        StringBuilder builder = new StringBuilder(line.substring(valueFirstIndex).trim());
        precedesWithBackSlash = false;
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '\\') {
                if (precedesWithBackSlash) {
                    builder.deleteCharAt(i);
                    precedesWithBackSlash = false;
                    continue;
                }
                precedesWithBackSlash = true;
                continue;
            }
            if (builder.charAt(i) == 'u' && precedesWithBackSlash) {
                readCharValue(builder, i + 1);
                precedesWithBackSlash = false;
                continue;
            }
        }
        return builder.toString();
    }

    private void readCharValue(StringBuilder builder, int i) throws ParseException {
        String symbol = builder.substring(i, i + 4);
        short sh;
        try {
            sh = Short.valueOf(symbol);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid ASCII symbol: " + symbol, i);
        }
        char c = (char) sh;
        builder.replace(i - 2, i + 4, String.valueOf(c));
    }

    public Map<String, String> getProperties() {
        if (properties == null) {
            throw new IllegalStateException("Properties has not been read yet");
        }
        return properties;
    }

    public String get(String key) {
        if (properties == null) {
            throw new IllegalStateException("Properties has not been read yet");
        }
        if (!properties.containsKey(key)) {
            throw new IllegalArgumentException("No such key: " + key);
        }
        return properties.get(key);
    }
}
