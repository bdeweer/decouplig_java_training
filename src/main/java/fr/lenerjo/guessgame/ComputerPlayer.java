package fr.lenerjo.guessgame;

import fr.lenerjo.logger.Logger;
import fr.lenerjo.logger.LoggerFactory;

import java.util.Objects;

public class ComputerPlayer implements Player {

    private final Logger logger = LoggerFactory.getLogger(ComputerPlayer.class);

    private Boolean greater = null;
    private long nextNumber = Launcher.DEFAULT_BOUNDS / 2;
    private long minPrevious = 0L;
    private long maxPrevious = Launcher.DEFAULT_BOUNDS;

    @Override
    public long askNextGuess() {

        if(Objects.nonNull(greater)){
            if(greater){
                minPrevious = nextNumber;
            }
            else {
                maxPrevious = nextNumber;
            }
        }

        nextNumber =
            Objects
                .isNull(
                    greater
                )
                ?
                nextNumber
                :
                greater //le nombre a trouver est plus grand
                    ?
                    nextNumber + ((maxPrevious-nextNumber) / 2) //oui donc y additione qqch
                    :
                    nextNumber - ((nextNumber-minPrevious) / 2) //non donc on y soustrait qqch
            ;

        return nextNumber;
    }

    @Override
    public void respond(boolean lowerOrGreater) {

        this.greater = lowerOrGreater;

        logger.log("The computer chosen number is " + (lowerOrGreater ? "lower" : "greater") + " that the number to find");
    }
}
