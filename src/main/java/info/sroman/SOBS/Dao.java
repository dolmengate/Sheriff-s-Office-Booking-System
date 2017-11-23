package info.sroman.SOBS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author stephen
 * @param <E>	the Entity associated with this DAO
 * @param <M>	a SearchModel for the given Entity
 */
public abstract class Dao <E extends Entity, M extends SearchModel> {
	
	protected Connection conn = null;
	protected Statement stmt = null;
	protected PreparedStatement prepStmt = null;
	
	/**
	 * Returns an ArrayList of the given Entity type based on search parameters
	 * supplied by model.
	 * @param model	SearchModel used as parameters for the query
	 * @return		an ArrayList containing the matches
	 */
	public abstract ArrayList<E> findAll(M model);
	
	/**
	 * INSERTs a new record into the relevant table.
	 * @param model SearchModel used as parameters for the insertion of the new record
	 * @return		true or false based on success or failure of the insertion
	 */
	public abstract boolean create(M model);
	
	/**
	 * UPDATEs an existing record in the relevant table.
	 * @param model	SearchModel used as parameters for the update of an existing record
	 * @return		true or false based on success or failure of the update
	 */
	public abstract boolean update(M model);
	
	/**
	 * DELETEs an existing record in the relevant table.
	 * @param model	SearchModel used as parameters for the deletion of an existing record
	 * @return 
	 */
	public abstract boolean delete(M model);
	
	/**
	 * Returns a relevant SQL AND statement if there is input in a TextField, 
	 * otherwise returns an empty String.
	 * @param fieldText	text input by the user in the TextField
	 * @param colName	name of the associated column in the database table
	 * @return			an appropriate AND statement or an empty String
	 */
	protected String checkForAnd(String fieldText, String colName) {
		StringBuilder andStatement = new StringBuilder();
		if (!fieldText.equals("")) {
			return andStatement.append(colName).append(" = '").append(fieldText).append("'").toString();
		}
		return "";
	}
}
