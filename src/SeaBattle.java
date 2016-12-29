import java.util.ArrayList;
import java.util.Scanner;

public class SeaBattle {
	public static final boolean	CHEAT = false;
	private static final String VALID_COORD = "^[A-J]([1-9]$|10$)";
	
	private ArrayList<Player> players;
	private int turn;
	private Scanner sc;
	
	/**
	 * Default constructor
	 */
	public SeaBattle() {
		players = new ArrayList<>();
		turn = 0;
		sc = new Scanner(System.in);
	}
	
	/**
	 * Starts the game
	 */
	public void play() {
		while (!players.get(turn).getField().areAllShipsSunk()) {
			// Who's turn is it
			System.out.println(String.format("\n*** %s's turn ***", players.get(turn).getName()));
			
			// Draw the field
			players.get(turn).getField().print();
			
			// Ask for the input
			String shootInput;
			do {
				do {
					System.out.print(String.format("%s, enter the location to shoot: ", players.get(turn).getName()));
		 			shootInput = Input.getString(sc);
				} while (!shootInput.matches(VALID_COORD));
			} while (!players.get(turn).getField().fire(shootInput) && !players.get(turn).getField().areAllShipsSunk());
			
			// Next turn
			if (players.size() > 1 && !players.get(turn).getField().areAllShipsSunk())
				turn ^= 1;
		}
		
		// Player with current turn won
		System.out.println("ALL SHIPS SUNK - " + players.get(turn).getName() + " WON!");
	}
	
	/**
	 * Adds a player to the game
	 * @param player object
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
}
