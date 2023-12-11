package fr.eni.ecole.got.bo;

import java.util.Random;

public class Dice {
    private int nbFaces;
    private int currentFace;
    private Random random;
    private final static int NB_FACES_DEFAULT = 6;

    public Dice(
        int nbFaces
    ) {
        this.setNbFaces(nbFaces);
        this.setRandom(new Random());
    }

    public Dice(
    ) {
        this(Dice.NB_FACES_DEFAULT);
    }

    public int roll(
    ) {
        this.setCurrentFace(1 + this.getRandom().nextInt(this.getNbFaces()));

        return this.getCurrentFace();
    }

    public int getNbFaces() { return nbFaces; }
    public int getCurrentFace() { return currentFace; }

    private void setNbFaces(int nbFaces) { this.nbFaces = nbFaces; }
    private void setCurrentFace(int currentFace) { this.currentFace = currentFace; }
    private Random getRandom() { return random; }
    private void setRandom(Random random) { this.random = random; }
}
