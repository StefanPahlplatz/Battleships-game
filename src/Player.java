
public class Player {
	private String name;
	private int playerId;
	private Field field;
	
	/**
	 * Default constructor
	 * @param name of the player
	 * @param playerId player number
	 */
	public Player(String name, int playerId) {
		this.name = name;
		this.playerId = playerId;
		this.field = new Field();
	}

	/**
	 * Returns the name of the player
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the player id
	 * @return integer
	 */
	public int getPlayerId() {
		return playerId;
	}
	
	/**
	 * Returns the field the player is playing on
	 * @return Field
	 */
	public Field getField() {
		return field;
	}
}
