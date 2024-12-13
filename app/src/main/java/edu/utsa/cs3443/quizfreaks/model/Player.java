package edu.utsa.cs3443.quizfreaks.model;

public class Player implements Comparable<Player>{
    /**
     * this is the edu.utsa.cs3443.quizfreaks.model.Player class for holding player objects
     * @author Raymond Harmon
     */

    private String name;
    private int score;

    /**
     * The constructor for the player class
     * @param name the name of the player
     * @param score the players score
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * gets the name of the player
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the player
     * @param name input parameter for the player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the score of the player
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * sets the score of the player
     * @param score the input parameter for the player's score
     */
    public void setScore(int score) {
        this.score = score;
    }


    /**
     * Overrides the compare to method to compare the scores of both players
     * @param o the other player to be compared.
     * @return an int to help with the sort method
     */
    @Override
    public int compareTo(Player o) {
        if (this.getScore()<o.getScore())
            return 1;
        if(this.getScore()>o.getScore())
            return -1;
        else
        {
            return this.getName().compareTo(o.getName());
        }

    }
}
