package model;

import java.util.ArrayList;

public class Result {
    private int ID;
    private int[] scores;
    private Competitor winner;

    public Result(int ID, int[] scores, Competitor winner) {
        this.ID = ID;
        this.scores = scores;
        this.winner = winner;
    }

    // Getter and Setter methods

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public Competitor getWinner() {
        return winner;
    }

    public void setWinner(Competitor winner) {
        this.winner = winner;
    }
}
