package fr.lenerjo.guessgame;

import fr.lenerjo.logger.Logger;
import fr.lenerjo.logger.LoggerFactory;

import java.util.Scanner;

public class HumanPlayer implements Player{

    private final Logger logger = LoggerFactory.getLogger("player");

    @Override
    public long askNextGuess() {

        var s = new Scanner(System.in);
        logger.log("Please a number between 0 (include) and 100 (exclude)");
        return s.nextLong();
    }

    @Override
    public void respond(boolean lowerOrGreater) {
        logger.log("Your number is " + (lowerOrGreater ? "lower" : "greater") + " that the number to find");
    }
}
