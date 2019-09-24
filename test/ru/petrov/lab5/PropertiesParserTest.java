package ru.petrov.lab5;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesParserTest {

    @Test
    public void parse_ThrowsOnMalformedInput() throws FileNotFoundException {
        PropertiesParser parser = new PropertiesParser(new FileInputStream("resources/lab5/malformed1.properties"));
        assertThrows(ParseException.class, parser::parse);

        parser = new PropertiesParser(new FileInputStream("resources/lab5/malformed2.properties"));
        assertThrows(ParseException.class, parser::parse);

        parser = new PropertiesParser(new FileInputStream("resources/lab5/malformed3.properties"));
        assertThrows(ParseException.class, parser::parse);
    }

    @Test
    public void parse_WorksCorrectly() throws IOException, ParseException {
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

    @Test
    public void getProperties_ThrowsOnPropertiesHasNotBeenRead() throws FileNotFoundException {
        PropertiesParser parser = new PropertiesParser(new FileInputStream("resources/lab5/prop.properties"));
        assertThrows(IllegalStateException.class, parser::getProperties);
    }

    @Test
    public void get_ThrowsOnPropertiesHasNotBeenRead() throws FileNotFoundException {
        PropertiesParser parser = new PropertiesParser(new FileInputStream("resources/lab5/prop.properties"));
        assertThrows(IllegalStateException.class, () -> parser.get("tab"));
    }

    @Test
    public void get_ThrowsOnNoSuchProperty() throws IOException, ParseException {
        PropertiesParser parser = new PropertiesParser(new FileInputStream("resources/lab5/prop.properties"));
        parser.parse();
        assertThrows(IllegalArgumentException.class, () -> parser.get("non existent"));
    }

    @Test
    public void get_WorksCorrectly() throws IOException, ParseException {
        PropertiesParser parser = new PropertiesParser(new FileInputStream("resources/lab5/prop.properties"));
        parser.parse();
        assertEquals("https://en.wikipedia.org/", parser.get("website"));
        assertEquals("This is the value that could be looked up with the key \"key with spaces\".", parser.get("key with spaces"));
        assertEquals("\u0009", parser.get("tab"));
    }
}