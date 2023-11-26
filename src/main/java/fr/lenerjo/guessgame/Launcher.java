package fr.lenerjo.guessgame;

import fr.lenerjo.logger.Logger;
import fr.lenerjo.logger.LoggerFactory;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Scanner;

public class Launcher {

    private static final Logger logger = LoggerFactory.getLogger("simulation");
    private static final String INTERACTIVE_ARG = "-interactive"; //better to do an enum
    private static final String AUTO_ARG = "-auto"; //better to do an enum
    public static final Integer DEFAULT_BOUNDS = 100;
    private static final Long COMPUTER_MAX_ITERATIONS = 1000L;

    public static void main(String[] args) {

        if(args.length == 0){
            manualStart();
        }

        else if(Objects.equals(args[0], INTERACTIVE_ARG)){
            autoStart(
                new HumanPlayer(),
                new SecureRandom().nextInt(DEFAULT_BOUNDS),
                Long.MAX_VALUE
            );
        }else if(args.length == 2 && Objects.equals(args[0], AUTO_ARG)){
            try {
                autoStart(
                    new ComputerPlayer(),
                    Integer.parseInt(args[1]),
                    COMPUTER_MAX_ITERATIONS
                );

            } catch (NumberFormatException nfe) {
                manualStart();
            }
        }
        else {
            manualStart();
        }
    }

    private static void autoStart(
        final Player player,
        int numberToFind,
        long maxIterations
    )
    {
        var simulation = new Simulation(player);
        simulation.initialize(numberToFind);
        simulation.loopUntilPlayerSucceed(maxIterations);

    }

    private static void manualStart(){

        var s = new Scanner(System.in);
        logger.log("Type 1 for Human player game or 2 for Computer player game");
        int choose = s.nextInt();

        //TODO Mieux gérer les saisies ...
        if(choose == 1){
            var simulation = new Simulation(new HumanPlayer());
            simulation.initialize(new SecureRandom().nextInt(100));
            simulation.loopUntilPlayerSucceed(Long.MAX_VALUE);
        }
        else {
            var simulation = new Simulation(new ComputerPlayer());
            logger.log("Choose the number that the computer has to guess : ");
            simulation.initialize(s.nextInt());
            simulation.loopUntilPlayerSucceed(1000L);
        }
    }
}
