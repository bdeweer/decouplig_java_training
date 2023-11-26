package fr.lenerjo.logger;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {
    private final Path path;
    private final String callerClass;

    public FileLogger(final String pathAsString, final String callerClass) {
        path = Paths.get(pathAsString).toAbsolutePath();
        this.callerClass = callerClass;
    }

    public void log(String message) {
        try {
            Files.write(path, (LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + " " + callerClass + " "  + message + "\n").getBytes(), APPEND, CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write log message to file [" + path + "]", e);
        }
    }
}
