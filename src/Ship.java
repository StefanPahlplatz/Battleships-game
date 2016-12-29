
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
		
		// Assign length
		if (type == Type.CARRIER) length = 5;
		else if (type == Type.BATTLESHIP) length = 4;
		else if (type == Type.SUBMARINE) length = 3;
		else if (type == Type.DESTROYER) length = 3;
		else if (type == Type.PATROL) length = 2;
	}
	
	/**
	 * Increase the hits of the ship
	 */
	public void registerHit() {
		hits++;
	}
	
	/**
	 * Returns the type of the ship
	 * @return Ship.Type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Returns whether the ship has sunk or not
	 * @return boolean
	 */
	public boolean isSunk() {
		return hits >= length;
	}
	
	/**
	 * Returns the length of the ship
	 * @return integer
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Returns the first letter in the type of the ship, return V if it sank
	 * @return char
	 */
	public char getLetter() {
		if (!isSunk())
			return type.toString().charAt(0);
		else
			return 'V';
	}
	
	/**
	 * Returns the full name of the ship
	 * @return String
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
