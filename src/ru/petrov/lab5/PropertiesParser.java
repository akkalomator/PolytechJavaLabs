package ru.petrov.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PropertiesParser {
    private final InputStream inputStream;
    private Map<String, String> properties;

    public PropertiesParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() throws IOException {
        try (
            InputStreamReader r = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(r)
        ) {
            properties = new HashMap<>();

            final StringBuilder[] multiLineValue = {new StringBuilder()};
            final boolean[] isPreviousLineFinished = {true};
            final String[] key = {null};

            reader.lines().forEach(

                rawLine -> {
                    String line = rawLine.trim();
                    if (!(line.startsWith("!") || line.startsWith("#") || line.isBlank())) {
                        if (isPreviousLineFinished[0]) {
                            String[] tk = line.split("[=:]",2);
                            key[0] = tk[0].trim().replace("\\ ", " ");
                            tk[1] = tk[1].trim();

                            if (tk[1].endsWith("\\")) {
                                isPreviousLineFinished[0] = false;
                                multiLineValue[0]
                                    .append(tk[1].replace("\\\\", "\\"))
                                    .deleteCharAt(multiLineValue[0].length() - 1);
                            } else {
                                isPreviousLineFinished[0] = true;
                                if (tk[1].charAt(0) == '\\' && tk[1].charAt(1) == 'u') {

                                    String codeString = tk[1].substring(2);
                                    short code = Short.valueOf(codeString);
                                    char c = (char) code;

                                    properties.put(key[0], String.valueOf(c));

                                } else {
                                    properties.put(key[0], multiLineValue[0].toString() + tk[1].replace("\\\\", "\\"));
                                }

                                if (multiLineValue[0].length() != 0) {
                                    multiLineValue[0] = new StringBuilder();
                                }
                            }
                        } else {
                            if (line.endsWith("\\")) {
                                isPreviousLineFinished[0] = false;
                                multiLineValue[0]
                                    .append(line.replace("\\\\", "\\"))
                                    .deleteCharAt(multiLineValue[0].length() - 1);
                            } else {
                                isPreviousLineFinished[0] = true;
                                properties.put(key[0], multiLineValue[0].toString() + line.replace("\\\\", "\\"));
                                if (multiLineValue[0].length() != 0) {
                                    multiLineValue[0] = new StringBuilder();
                                }
                            }
                        }
                    }
                }
            );
        }
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}
