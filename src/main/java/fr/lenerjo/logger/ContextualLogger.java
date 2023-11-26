package fr.lenerjo.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContextualLogger implements Logger{

    private final String callerClass;
    private final Logger delegateLogger;

    public ContextualLogger(final String callerClass, final Logger delegateLogger){
        this.callerClass = callerClass;
        this.delegateLogger = delegateLogger;
    }
    @Override
    public void log(String message) {

        delegateLogger.log(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + " " + callerClass + " " + message);
    }
}
