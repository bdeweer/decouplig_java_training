package fr.lenerjo.guessgame;

import fr.lenerjo.logger.Logger;
import fr.lenerjo.logger.LoggerFactory;

public class Simulation {

    private final Logger logger = LoggerFactory.getLogger("simulation");
    private final Player player;
    private long numberToGuess;

    public Simulation(Player player) {
        this.player = player;
    }

    public void initialize(long numberToGuess) {
        this.numberToGuess=numberToGuess;
    }

    /**
     * @return true if the player have guessed the right number
     */
    private boolean nextRound() {
        long attempt = player.askNextGuess();
        boolean found = attempt == numberToGuess;
        if(found){
            return true;
        }
        player.respond(attempt < numberToGuess);
        return false;
    }

    public void loopUntilPlayerSucceed() {

        boolean numberGuessed = false;
        while(!numberGuessed){
            numberGuessed = nextRound();
        }
        logger.log("you won !");
    }
}
