package fr.eni.ecole.got.bo;

public class Player {
    private String name;
    private int score;
    private final static int INITIAL_SCORE = 0;
    private boolean playing;

    public Player(
        String name
    ) {
        this.setName(name);
        this.setScore(Player.INITIAL_SCORE);
        this.setPlaying(true);
    }

    public void stopped(
    ) {
        this.setPlaying(false);
    }

    public void lost(
    ) {
        this.setScore(INITIAL_SCORE - 1);
        this.setPlaying(false);
    }

    public void addPoints(
        int points
    ) {
        this.setScore(this.getScore() + points);
    }

    public String getName() { return name; }
    private void setName(String name) { this.name = name; }
    public int getScore() { return score; }
    private void setScore(int score) { this.score = score; }
    public boolean getPlaying() { return this.playing; }
    private void setPlaying(boolean playing) { this.playing = playing; }
}
