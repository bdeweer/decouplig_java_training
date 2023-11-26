package fr.lenerjo.logger;

public class LoggerFactory {

    public static Logger getLogger(String name){
        return new ConsoleLogger();
    }
}
