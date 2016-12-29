
public class Square {
	private boolean wasShot;
	private Ship ship;
	private String coords;
	
	public enum State {
		ALREADY_SHOT, SHIP_HIT, MISS
	}
	
	/**
	 * Default constructor
	 * @param coords e.g. F8
	 */
	public Square(String coords) {
		this.coords = coords;
		ship = null;
	}
	
	/**
	 * Shoots the square
	 * @return an enum Square.State based on the condition of the square after being shot
	 */
	public State shoot() {
		if (!wasShot && hasShip()) {
			wasShot = true;
			return State.SHIP_HIT;
		} else if (wasShot) {
			return State.ALREADY_SHOT;
		} else if (!wasShot && !hasShip()) {
			wasShot = true;
			return State.MISS;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a character that should be parsed by Field.findRightLetter to get the right character
	 * @return a character depending on the state of the square
	 */
	public char getLetter() {
		if (!hasShip() && wasShot) return '~';
		else if (hasShip() && wasShot) return Character.forDigit(getShipTypeAsInt() + 5, 10);
		else if (hasShip() && !wasShot) return Character.forDigit(getShipTypeAsInt(), 10);
		else return '.';
	}
	
	/**
	 * Returns the full name of the ship e.g. "Patrol Boat"
	 * @return the name of the ship on the square
	 */
	public String getShipName() {
		return ship.getFullName();
	}
	
	/**
	 * Returns the integer value of the ship type
	 * @return integer ranging from 0 to 4, if there is no ship return -1
	 */
	public int getShipTypeAsInt() {
		if (ship != null) {
			switch (ship.getType()) {
				case CARRIER:
					return 0;
				case BATTLESHIP:
					return 1;
				case SUBMARINE:
					return 2;
				case DESTROYER:
					return 3;
				case PATROL:
					return 4;
				default:
					return -1;
			}
		} else {
			return -1;
		}
	}
	
	/**
	 * Get's the coordinate of the current square
	 * @return String
	 */
	public String getCoord() {
		return coords;
	}
	
	/**
	 * Returns whether the square has a ship on it or not
	 * @return true if it has a ship, otherwise false
	 */
	public boolean hasShip() {
		return ship != null;
	}
	
	/**
	 * Sets the ship of the current square
	 * @param ship Ship object
	 */
	public void setShip(Ship ship) {
		this.ship = ship;
	}
}
