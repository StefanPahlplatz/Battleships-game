
public class Square {
	private boolean wasShot;
	private Ship ship;
	private String coords;
	
	public enum State {
		ALREADY_SHOT, SHIP_HIT, MISS
	}
	
	/**
	 * Default constructor
	 * @param coords String e.g. F8
	 */
	public Square(String coords) {
		this.coords = coords;
		ship = null;
	}
	
	/**
	 * Shoots the square
	 * @return Enum State based on the condition of the square after being shot
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
	 * Gets the right letter that should be displayed on the board
	 * @return Depending on the state of the square:
	 *		V: Ship, 	it sank
	 *		~: No ship, shot
	 *		*: Ship,	shot
	 *		.: No ship,	nothing happened
	 *		otherwise: the letter of the ship at the position
	 */
	public char getLetter() {
		if (ship != null && ship.isSunk()) return 'V';
		else if (!hasShip() && wasShot) return '~';
		else if (hasShip() && wasShot) return '*';
		else if (hasShip() && !wasShot) return ship.getLetter();
		else return '.';
	}
	
	public String getShipName() {
		return ship.getFullName();
	}
	
	public int getShipTypeAsInt() {
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
	 * @return boolean
	 */
	public boolean hasShip() {
		return ship != null;
	}
	
	/**
	 * Sets the ship of the current square
	 * @param ship Ship
	 */
	public void setShip(Ship ship) {
		this.ship = ship;
	}
}
