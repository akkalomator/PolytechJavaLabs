package ru.petrov.lab5;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesParserTest {

    @Test
    public void parse_WorksCorrectly() throws FileNotFoundException {
        PropertiesParser parser = new PropertiesParser(new FileInputStream("resources/lab5/prop.properties"));
        parser.parse();
        Map<String, String> resultProperties = parser.getProperties();
        Map<String, String> expectedProperties = Map.of(
            "website", "https://en.wikipedia.org/",
            "language", "English",
            "message", "Welcome to Wikipedia!",
            "key with spaces", "This is the value that could be looked up with the key \"key with spaces\".",
            "tab", "\u0009",
            "path", "c:\\wiki\\templates"
        );
        assertEquals(expectedProperties, resultProperties);
    }

}