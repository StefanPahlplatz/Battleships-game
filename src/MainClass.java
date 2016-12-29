import java.util.Scanner;

public class MainClass {
	/**
	 * Entry point
	 * @param args starting parameters (leave blank)
	 */
	public static void main(String[] args) {
		SeaBattle seaBattle = new SeaBattle();
		Scanner sc = new Scanner(System.in);
		
		// Ask for the amount of players
		System.out.print("Welcome to Battleships!\nPlease enter the amount of players (1/2): ");
		int players = Input.getInt(sc, 1, 2);
		
		// Ask for the players names and add them to the game
		for (int i = 0; i < players; i++) {
			System.out.print("Please enter the name of player " + (i + 1) + ": ");
			String name = Input.getString(sc);
			seaBattle.addPlayer(new Player(name, i + 1));
		}
	
		// Start the battle
		seaBattle.play();
	}
}
