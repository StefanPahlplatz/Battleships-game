
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
	
	public char getLetter() {
		// The ship sank
		if (ship != null && ship.isSunk())
			return 'V';
		// No ship, shot missed
		else if (ship == null && wasShot)
			return '~';
		// The ship was hit
		else if (ship != null && wasShot)
			return '*';
		else if (ship != null && !wasShot)
			return ship.getLetter();
		// Default
		else
			return '.';
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
