package fr.lenerjo.guessgame;

import fr.lenerjo.logger.Logger;
import fr.lenerjo.logger.LoggerFactory;

import java.time.Duration;

public class Simulation {

    private final Logger logger = LoggerFactory.getLogger("simulation");
    private final Player player;
    private long numberToGuess;

    public Simulation(Player player) {
        this.player = player;
    }

    public void initialize(long numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    /**
     * @return true if the player have guessed the right number
     */
    private boolean nextRound() {
        long attempt = player.askNextGuess();
        boolean found = attempt == numberToGuess;
        if (found) {
            return true;
        }
        player.respond(attempt < numberToGuess);
        return false;
    }

    public void loopUntilPlayerSucceed(long maxIterations) {

        long ms = System.currentTimeMillis();

        boolean numberGuessed = false;
        int i =0;
        while (!numberGuessed && i<maxIterations) {
            numberGuessed = nextRound();
            i++;
        }

        final Duration duration = Duration.ofMillis(System.currentTimeMillis() - ms);

        logger.log(
            (i<maxIterations
                ?
                "Won! You found the solution before maximum number of iterations ("+i+"/"+maxIterations+")"
                :
                "Loose! You didn't find the solution before the maximum ("+maxIterations+")")
                +
                " - Duration : " +
                String.format(
                    "%02d:%02d:%03d",
                    duration.toMinutesPart(),
                    duration.toSecondsPart(),
                    duration.toMillisPart()
                )
                +
                ")"
        );
    }
}
