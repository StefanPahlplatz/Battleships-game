import java.util.ArrayList;
import java.util.Random;

public class Field {
	private static final String alphabet = "ABCDEFGHIJ";
	private static final int BOARD_SIZE = 10;
	private static final int SHIP_AMOUNT = 5;

	
	private int countShipsSunk;				// Amount of ships that have sunk
	private Square[][] board;				// Player board
	private ArrayList<Ship> ships;			// All the ships on the board
	
	/**
	 * Default constructor
	 */
	public Field() {
		// Initialize board
		board = new Square[BOARD_SIZE][BOARD_SIZE];
		
		ships = new ArrayList<>();
		ships.add(new Ship(Ship.Type.CARRIER));
		ships.add(new Ship(Ship.Type.BATTLESHIP));
		ships.add(new Ship(Ship.Type.SUBMARINE));
		ships.add(new Ship(Ship.Type.DESTROYER));
		ships.add(new Ship(Ship.Type.PATROL));
		
		generateBoard();
		
		generateShips();
	}
	
	/**
	 * Prints the field to the console
	 */
	public void print() {
		// Print the board
		for (int i = BOARD_SIZE - 1; i >= 0; i--) {
			String line = String.format("%2s"  + " ", Integer.toString(i + 1)); 
			for (int j = 0; j < BOARD_SIZE; j++) {
				char c = findRightLetter(board[i][j].getLetter());
				line += c + " ";
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
	 * Shoots at the specified location
	 * @param atPosition the position to shoot, e.g. A3
	 * @return False if the square was already hit, True if the square has not been hit yet
	 */
	public boolean fire(String atPosition) {
		switch (getCoord(atPosition).shoot()) {
		case ALREADY_SHOT:
			System.out.println("*** You already shot this square ***");
			return false;
		case SHIP_HIT:
			System.out.println("You hit a ship!");
			
			// Increase hits on the ship
			ships.get(getCoord(atPosition).getShipTypeAsInt()).registerHit();
			
			// Check if the ship sank after the hit
			if (ships.get(getCoord(atPosition).getShipTypeAsInt()).isSunk()) {
				countShipsSunk++;
				System.out.println("Congratulations! You sank the " + getCoord(atPosition).getShipName());
			}
			return false;
		case MISS:
			System.out.println("You missed!");
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * Returns whether all ships are sunk
	 * @return true if all ships are sunk, otherwise false
	 */
	public boolean areAllShipsSunk() {
		if (SeaBattle.CHEAT) {
			if (countShipsSunk == 1) {
				System.out.println("Because cheat mode is on the game end now!");
				return true;
			} else 
				return false;
		}
		else return countShipsSunk >= SHIP_AMOUNT;
	}
	
	/**
	 * Returns the Square at the passed position
	 * @param coordinate, e.g. A5
	 * @return the square corresponding to the given coordinate
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
	
	/**
	 * Gives every square on the board the correct coordinate ranging from A1 to J10
	 */
	private void generateBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) 
			for (int j = 0; j < BOARD_SIZE; j++) 
				board[i][j] = new Square(alphabet.charAt(j) + Integer.toString(i + 1)); 	// Increment by 1 to make A1 the bottom row, not A0
	}
	
	/**
	 * Generates the different ships on the board
	 */
	private void generateShips() {
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
	 * Finds the right letter that should be displayed on the board, 
	 * depending on which ship it is, if it has been hit and if the ship has sunk
	 * @param c character from Square.getLetter()
	 * @return the right letter to be displayed
	 */
	private char findRightLetter(char c) {
		switch (c) {
		case '0':
			c = ships.get(0).getLetter();
			break;
		case '1':
			c = ships.get(1).getLetter();
			break;
		case '2':
			c = ships.get(2).getLetter();
			break;
		case '3':
			c = ships.get(3).getLetter();
			break;
		case '4':
			c = ships.get(4).getLetter();
			break;
		case '5':
			c = ships.get(0).isSunk() ? 'V' : '*';
			break;
		case '6':
			c = ships.get(1).isSunk() ? 'V' : '*';
			break;
		case '7':
			c = ships.get(2).isSunk() ? 'V' : '*';
			break;
		case '8':
			c = ships.get(3).isSunk() ? 'V' : '*';
			break;
		case '9':
			c = ships.get(4).isSunk() ? 'V' : '*';
		default:
			break;
		}
		return c;
	}
}
