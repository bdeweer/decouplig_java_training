package fr.lenerjo.guessgame;

public enum Mode {
    AUTO("-auto"), INTERACTIVE("-interactive");

    private final String name;

    Mode(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
