package fr.lenerjo.guessgame;

import java.security.SecureRandom;

public class Launcher {

    public static void main(String[] args) {

        var simulation = new Simulation(new HumanPlayer());
        simulation.initialize(new SecureRandom().nextInt(100));
        simulation.loopUntilPlayerSucceed();
    }
}
