
public class Ship {
	private int length;
	private Type type;
	private int hits;
	
	public enum Type {
		CARRIER, 
		BATTLESHIP, 
		SUBMARINE, 
		DESTROYER, 
		PATROL
	}
	
	/**
	 * Default constructor
	 * @param type of the ship
	 */
	public Ship(Type type) {
		this.type = type;
		
		// Assign the right length
		switch (type) {
			case CARRIER:
				length = 5;
				break;
			case BATTLESHIP:
				length = 4;
				break;
			case SUBMARINE:
				length = 3;
			case DESTROYER:
				length = 3;
				break;
			case PATROL:
				length = 2;
		}
	}
	
	/**
	 * Increase the hits of the ship
	 */
	public void registerHit() {
		hits++;
	}
	
	/**
	 * Returns the type of the ship
	 * @return Ship.Type of the ship
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Returns whether the ship has sunk or not
	 * @return true if it has sunk, otherwise false
	 */
	public boolean isSunk() {
		return hits >= length;
	}
	
	/**
	 * Returns the length of the ship
	 * @return the length of the ship
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * If cheats are on, returns the first letter in the type of the ship, return V if it sank
	 * If cheats are off, returns . if the ship hasn't sank, otherwise return V
	 * @return the letter of the ship
	 */
	@SuppressWarnings("unused")
	public char getLetter() {
		if (!isSunk() && SeaBattle.CHEAT)
			return type.toString().charAt(0);
		else if (!isSunk() && !SeaBattle.CHEAT)
			return '.';
		else
			return 'V';
	}
	
	/**
	 * Returns the full name of the ship
	 * @return the name of the ship
	 */
	public String getFullName() {
		switch(type) {
			case CARRIER:
				return "Aircraft Carrier";
			case BATTLESHIP:
				return "Battleship";
			case SUBMARINE:
				return "Submarine";
			case DESTROYER:
				return "Destroyer";
			case PATROL:
				return "Patrol Boat";
			default:
				return "Error";
		}
	}
}
