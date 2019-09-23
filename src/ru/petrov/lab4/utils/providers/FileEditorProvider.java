package ru.petrov.lab4.utils.providers;

import ru.petrov.lab4.Controller;
import ru.petrov.lab4.utils.FileEditor;
import ru.petrov.lab4.utils.SaveMode;
import ru.petrov.lab4.utils.io.Reader;
import ru.petrov.lab4.utils.io.Writer;

import java.io.IOException;

public class FileEditorProvider {

    private static final String INTERLINE_DELIMITER = "--------------------------------------------";
    private static final String OPTION_YES = "y";
    private static final String OPTION_NO = "n";
    private static final String OPTION_REWRITE = "r";
    private static final String OPTION_APPEND = "a";

    private final FileEditor editor;
    private final Reader reader;
    private final Writer writer;

    public FileEditorProvider(FileEditor editor, Reader reader, Writer writer) {
        this.editor = editor;
        this.reader = reader;
        this.writer = writer;
    }

    public void editorMenu() throws Exception {
        writer.writeString("Editor\n");
        writer.writeString("File: " + editor.getName() + "\n");
        writer.writeString(INTERLINE_DELIMITER + "\n");

        editor.open();
        editor.readContent().forEach(line -> {
            try {
                writer.writeString(line + "\n");
            } catch (IOException e) {
                writer.printError("\n");
            }
        });
        writer.writeString(INTERLINE_DELIMITER + "\n");

        while (true) {
            String line = reader.getLine();
            if (line.equalsIgnoreCase(":" + Controller.QUIT_COMMAND)) {
                break;
            }
            editor.appendData(line);
        }

        String line = reader.getUserInputOutOfOptions(writer, "Save file?", OPTION_YES, OPTION_NO);
        if (line.equalsIgnoreCase(OPTION_YES)) {
            line = reader.getUserInputOutOfOptions(writer, "Rewrite file or append?", OPTION_REWRITE, OPTION_APPEND);
            if (line.equalsIgnoreCase(OPTION_REWRITE)) {
                editor.save(SaveMode.REWRITE);
            } else if (line.equalsIgnoreCase(OPTION_APPEND)) {
                editor.save(SaveMode.APPEND);
            } else {
                writer.printError("Internal error. Your files may not be saved");
            }
        }

        editor.close();
    }
}
