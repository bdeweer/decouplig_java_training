package fr.lenerjo.logger;

public class CompositeLogger implements Logger{

    private final Logger logger1;
    private final Logger logger2;
    public CompositeLogger(Logger logger1, Logger logger2){
        this.logger1 = logger1;
        this.logger2 = logger2;
    }

    @Override
    public void log(String message) {

        logger1.log(message);
        logger2.log(message);
    }
}
