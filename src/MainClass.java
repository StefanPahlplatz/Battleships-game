import java.util.Scanner;

public class MainClass {
	/**
	 * Entry point
	 */
	public static void main(String[] args) {
		SeaBattle seaBattle = new SeaBattle();
		Scanner sc = new Scanner(System.in);
		
		// Ask for the amount of players
		int players = getPlayerAmount(sc);
		
		// Ask for the players names and add them to the game
		for (int i = 0; i < players; i++) {
			String name = getPlayerName(sc, i + 1);
			seaBattle.addPlayer(new Player(name, i + 1));
		}
	
		// Start the battle
		seaBattle.play();
	}
	
	/**
	 * Asks the user for the amount of players
	 * @param sc Scanner
	 * @return integer that is either 1 or 2
	 */
	private static int getPlayerAmount(Scanner sc) {
		System.out.print("Welcome to Battleships!\nPlease enter the amount of players (1/2): ");
		return Input.getInt(sc, 1, 2);
	}
	
	/**
	 * Asks the user for the player name
	 * @param sc Scanner
	 * @return String
	 */
	private static String getPlayerName(Scanner sc, int playerNumber) {
		System.out.print("Please enter the name of " + playerNumber + ": ");
		return Input.getString(sc);
	}
}
