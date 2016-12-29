import java.util.ArrayList;
import java.util.Scanner;

public class SeaBattle {
	public static final boolean	CHEAT = true;
	private static final String VALID_COORD = "^[A-J]([1-9]$|10$)";
	
	private ArrayList<Player> players;
	private int turn;
	private int startTurn;
	private Scanner sc;
	
	/**
	 * Default constructor
	 */
	public SeaBattle() {
		players = new ArrayList<>();
		turn = 0;
		startTurn = 1;
		sc = new Scanner(System.in);
	}
	
	/**
	 * Starts the game
	 */
	public void play() {
		boolean playing = true;
		while (playing) {
			// Change turns for each game
			if (players.size() > 1)
				startTurn ^= 1;
			turn = startTurn;
			
			while (!players.get(turn).getField().areAllShipsSunk()) {
				// Print whos turn is it
				System.out.println(String.format("\n*** %s's turn ***", players.get(turn).getName()));
				
				String shootInput;
				do {
					// Draw the field
					players.get(turn).getField().print();
	
					// Ask for the coordinate to shoot
					do {
						System.out.print(String.format("%s, enter the location to shoot: ", players.get(turn).getName()));
			 			shootInput = Input.getString(sc);
					} while (!shootInput.matches(VALID_COORD));
				} while (!players.get(turn).getField().fire(shootInput) && !players.get(turn).getField().areAllShipsSunk());
				
				// Next turn
				if (players.size() > 1 && !players.get(turn).getField().areAllShipsSunk())
					turn ^= 1;
			}
			
			// A player won
			System.out.print("You sank all ships " + players.get(turn).getName() + 
					"!\nDo you want to play again? (yes/no): ");
			
			// Get input
			playing = Input.getYesNo(sc);
			
			// Reset players fields
			for(Player player : players) {
				player.reset();
			}
		}
		System.out.println("Thank you for playing!");
	}
	
	/**
	 * Adds a player to the game
	 * @param player to add
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
}
