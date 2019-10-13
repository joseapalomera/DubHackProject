package justAProject;


public class Player {

	private String name;
	private int gamesWon;
	private char signal;
	
	public Player() {
		
		name = "Player One";
		gamesWon = 0;
		
	}
	
	public Player(String name) {
		
		this.name = name;
	}
	
	public void wonAGame() {
		gamesWon++;
	}
	
	public int getGamesWon() {
		return gamesWon;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setSignal(char signal) {
		this.signal = signal;
	}
	
	public char getSignal() {
		return this.signal;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
