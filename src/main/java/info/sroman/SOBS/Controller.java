package info.sroman.SOBS;

import java.sql.SQLException;

public abstract class Controller <M extends SearchModel> {

	protected final Dao dao;
	
	protected Controller(Dao dao) {
		this.dao = dao;
	}
	
	public abstract M search(M model) throws SQLException;
	public abstract boolean edit(M model) throws SQLException;
	public abstract boolean add(M model) throws SQLException;
	public abstract boolean remove(M model) throws SQLException;

}
