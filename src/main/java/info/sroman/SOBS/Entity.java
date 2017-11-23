package info.sroman.SOBS;

public abstract class Entity {
	
	/**
	 * INSERT this entity into the database.
	 * @return true or false based on the success or failure of the insertion
	 */
	public abstract boolean createDBEntry();
	
}
