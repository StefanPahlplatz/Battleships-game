
public class Ship {
	private int length;
	private Type type;
	private int hits;
	
	public enum Type {
		CARRIER, BATTLESHIP, SUBMARINE, DESTROYER, PATROL
	}
	
	public Ship(Type type) {
		this.type = type;
		
		// Assign length
		if (type == Type.CARRIER) length = 5;
		else if (type == Type.BATTLESHIP) length = 4;
		else if (type == Type.SUBMARINE) length = 3;
		else if (type == Type.DESTROYER) length = 3;
		else if (type == Type.PATROL) length = 2;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean isSunk() {
		return false;
	}
	
	public int getLength() {
		return length;
	}
	
	public char getLetter() {
		return type.toString().charAt(0);
	}
}
