
public class Player {
	private String name;
	private int playerId;
	private Field field;
	
	public Player(String name, int playerId) {
		this.name = name;
		this.playerId = playerId;
		this.field = new Field();
	}

	public String getName() {
		return name;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public Field getField() {
		return field;
	}
}
