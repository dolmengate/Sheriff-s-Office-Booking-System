package info.sroman.SOBS;

import java.sql.SQLException;

public abstract class Controller <M extends SearchModel> {

	// injected Data Access Object
	protected final Dao dao;
	
	protected Controller(Dao dao) {
		this.dao = dao;
	}
	
	/** 
	 * Returns true or false based on whether the search for an existing record 
	 * was successful or not. The model parameter must be a valid SearchModel.
	 * @param model			SearchModel to be used as parameters for the query.
	 * @return				the model containing the query results
	 * @throws SQLException 
	 */
	public abstract M search(M model) throws SQLException;
	
	/** 
	 * Returns true or false based on whether the edit of an existing record 
	 * was successful or not. The model parameter must be a valid SearchModel.
	 * @param model			SearchModel to be used as parameters for the query.
	 * @return				boolean value based on success or failure of the edit
	 * @throws SQLException 
	 */
	public abstract boolean edit(M model) throws SQLException;
	
	/** 
	 * Returns true or false based on whether the addition of a new record 
	 * was successful or not. The model parameter must be a valid SearchModel.
	 * @param model			SearchModel to be used as parameters for the query.
	 * @return				boolean value based on success or failure of the addition
	 * @throws SQLException 
	 */
	public abstract boolean add(M model) throws SQLException;
	
	/** 
	 * Returns true or false based on whether the removal of an existing record 
	 * was successful or not. The model parameter must be a valid SearchModel.
	 * @param model			SearchModel to be used as parameters for the query.
	 * @return				boolean value based on success or failure of the removal
	 * @throws SQLException 
	 */
	public abstract boolean remove(M model) throws SQLException;

}
