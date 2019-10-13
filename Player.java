
/**
 * This class is a player for a connect four game. 
 * It creates a Player that has a name, keeps track of the games won, and their symbol
 * This program has methods to set, and get the player information.
 * This program assumes that the players will input which column they want their pieces in by entering a integer
 *
 * @author (Victor Ly, Jose Palomera)
 * @version (10/13/19)
 */
public class Player {
    //Instance variables
    private String name;
    private int gamesWon;
    private char signal;

    /**
     * No arg constructor for objects of class Player
     */
    public Player() {

        name = "Player One";
        gamesWon = 0;

    }

    /**
     * Constructor for objects of class Player that takes in a string
     * 
     * @param name      The name of the player. String
     */
    public Player(String name) {

        this.name = name;
    }

    /**
     * Increments the amount of wins the player has.
     */
    public void wonAGame() {
        gamesWon++;
    }

    /**
     * Returns the amount of games the player has won
     * 
     * @return gamesWon     The amount of games the amount of games the player has won. int
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Returns the name of the player.
     * 
     * @return this.name     The name of the player. String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the symbol for the player
     * 
     * @param signal     The symbol of the player will have
     */
    public void setSignal(char signal) {
        this.signal = signal;
    }

    /**
     * Returns the the symbol of the player.
     * 
     * @return this.signal     The symbol of the player. char
     */
    public char getSignal() {
        return this.signal;
    }

    @Override
    /**
     * Prints out the name of the player
     * 
     * @return this.name     The name of the player. String
     * 
     * Example:
     * If the player was named "Jose Ly"
     * 
     * then this.name would return "Jose Ly"
     */
    public String toString() {
        return this.name;
    }

}
