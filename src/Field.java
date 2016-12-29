import java.util.Random;

public class Field {
	private static final String alphabet = "ABCDEFGHIJ";
	private static final int BOARD_SIZE = 10;
	private static final int SHIP_AMOUNT = 5;
	
	private int countShipsSunk;
	private Square[][] board;
	
	/**
	 * Default constructor
	 */
	public Field() {
		// Initialize board
		board = new Square[BOARD_SIZE][BOARD_SIZE];
		
		// Fill the board with squares from 1a to 10j
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new Square(alphabet.charAt(j) + Integer.toString(i + 1)); 	// Increment by 1 to make A1 the bottom row, not A0
			}
		}
		
		// Generate ships
		Random r = new Random();
		for (int i = 0; i < SHIP_AMOUNT; i++) {
			boolean placedShip = false;
			do {
				// Create variables
				boolean isHorizontal = r.nextBoolean();
				boolean overlap = false;
				int x = r.nextInt(BOARD_SIZE);
				int y = r.nextInt(BOARD_SIZE);
				
				// Create ship
				Ship ship = new Ship(Ship.Type.values()[i]);
				
				// Check if the ship isn't outside the board
				if ((isHorizontal && x + ship.getLength() < BOARD_SIZE) || (!isHorizontal && y + ship.getLength() < BOARD_SIZE)) {
					// Check if ships don't overlap
					for (int j = 0; j < ship.getLength(); j++) {
						if (board[isHorizontal ? y : y + j][isHorizontal ? x + j : x].hasShip())
							overlap = true;
						else
							continue;
					}
					
					// Place ship
					if (!overlap) {
						for (int j = 0; j < ship.getLength(); j++) {
							board[isHorizontal ? y : y + j][isHorizontal ? x + j : x].setShip(ship);;
							placedShip = true;
						}
					}
				}
			} while (!placedShip);
		}
	}
	
	/**
	 * Prints the field to the console
	 */
	public void print() {
		// Print the board
		for (int i = BOARD_SIZE - 1; i >= 0; i--) {
			String line = String.format("%2s"  + " ", Integer.toString(i + 1)); 
			for (int j = 0; j < BOARD_SIZE; j++) {
				line += board[i][j].getLetter() + " ";
			}
			System.out.println(line);
		}
		
		// Print the last line with the letters
		String letters = String.format("%3s", "");
		for (int j = 0; j < BOARD_SIZE; j++) {
			letters += alphabet.charAt(j) + " ";
		}
		System.out.println(letters + "\n");
	}
	
	/**
	 * Fires at the specified location
	 * @param atPosition String e.g. 7a
	 */
	public boolean fire(String atPosition) {
		Square s = getCoord(atPosition);
		if (s != null) {
			s.shoot();
			return true;
		} else {
			System.out.println("Wrong coordinate!");
			return false;
		}
	}
	
	/**
	 * Returns whether all ships are sunk
	 * @return boolean
	 */
	public boolean areAllShipsSunk() {
		return countShipsSunk >= SHIP_AMOUNT;
	}
	
	/**
	 * Returns the Square at the passed position
	 * @param coord String e.g. 6a
	 * @return Square object
	 */
	private Square getCoord(String coord) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				// If the square has the search coordinate
				if (board[i][j].getCoord().equals(coord))
					return board[i][j];
			}
		}
		return null;
	}
}
