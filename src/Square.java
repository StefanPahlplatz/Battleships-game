
public class Square {
	private boolean wasShot;
	private Ship ship;
	private String coords;
	
	public Square(String coords) {
		this.coords = coords;
		ship = null;
	}
	
	public void shoot() {
		wasShot = true;
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
		else if (ship == null && wasShot) return '~';
		else if (ship != null && wasShot) return '*';
		else if (ship != null && !wasShot) return ship.getLetter();
		else return '.';
	}
	
	public String getCoord() {
		return coords;
	}
	
	public boolean hasShip() {
		return ship != null;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
}
