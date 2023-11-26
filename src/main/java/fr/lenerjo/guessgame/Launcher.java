package fr.lenerjo.guessgame;

import fr.lenerjo.logger.Logger;
import fr.lenerjo.logger.LoggerFactory;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Scanner;

public class Launcher {

    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);
    public static final Integer DEFAULT_BOUNDS = 100;
    private static final Long COMPUTER_MAX_ITERATIONS = 1000L;

    public static void main(String[] args) {

        if(args.length == 0){
            manualStart();
        }

        else if(Objects.equals(args[0], Mode.INTERACTIVE.getName())){
            autoStart(
                new HumanPlayer(),
                new SecureRandom().nextInt(DEFAULT_BOUNDS),
                Long.MAX_VALUE
            );
        }else if(args.length == 2 && Objects.equals(args[0], Mode.AUTO.getName())){
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
        int numberToGuess,
        long maxIterations
    )
    {
        Simulation
            .start(
                player,
                numberToGuess,
                maxIterations
            )
        ;
    }

    private static void manualStart(){

        var s = new Scanner(System.in);
        logger.log("Type 1 for Human player game or 2 for Computer player game");
        int choose = s.nextInt();

        //TODO Mieux gérer les saisies ...
        if(choose == 1){

            Simulation
                .start(
                    new HumanPlayer(),
                    new SecureRandom().nextInt(100),
                    Long.MAX_VALUE
                )
            ;
        }
        else {
            logger.log("Choose the number that the computer must guess : ");

            Simulation
                .start(
                    new ComputerPlayer(),
                    s.nextInt(),
                    COMPUTER_MAX_ITERATIONS
                )
            ;
        }
    }
}
