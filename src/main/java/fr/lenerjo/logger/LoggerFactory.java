package fr.lenerjo.logger;

import fr.lenerjo.guessgame.Simulation;

public class LoggerFactory {

    public static Logger getLogger(Class clazz) {

        return
            new CompositeLogger(
                new ContextualLogger(clazz.getName(), new ConsoleLogger()),
                new FilteredLogger(
                    new FileLogger(
                        "/Users/bertranddeweer/dev/workspace/decouplig_java_training/src/main/resources/decoupling_java_training.log",
                        clazz.getName()
                    )
                    ,
                    xx -> clazz.equals(Simulation.class)
                )
            );
    }
}
