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
	 * @return		true or false based on success or failure of the deletion
	 */
	
	public abstract boolean delete(M model);
	
	/**
	 * Returns a relevant SQL AND statement if there is input in a TextField, 
	 * otherwise returns an empty String.
	 * @param fieldText	text input by the user in the TextField
	 * @param colName	name of the associated column in the database table
	 * @return			an appropriate AND statement or an empty String
	 */
	private String checkForAnd(String fieldText, String colName) {
		StringBuilder andStatement = new StringBuilder();
		if (!fieldText.equals("")) {
			return andStatement.append(colName).append(" = '").append(fieldText).append("'").toString();
		}
		return "";
	}
	
	/**
	 * Check if the values of the given fields contain any input (i.e. if their
	 * combined lengths are longer than 0).
	 * @param fieldValues array of Strings: values of the fields to be checked
	 * @return true or false based on the aggregate emptyness of the fields provided
	 */
	protected boolean fieldsAreEmpty(String[] fieldValues) {
		StringBuilder emptyTest = new StringBuilder();

		for (String fieldValue : fieldValues) {
			emptyTest.append(fieldValue);
		}
			return emptyTest.length() == 0;
	}
	
	/**
	 * Generate a series of WHERE clauses from the values of input fields and
	 * their corresponding table columns. Arguments must be parallel arrays.
	 * @param fieldValues String array of values from user input fields
	 * @param columnNames String array of column names for 
	 * @return			  a String containing the required WHERE clauses separated
	 *					  by ANDs
	 */
	protected String constructWhereClauses(String[] fieldValues, String[] columnNames) {
		StringBuilder whereClauses = new StringBuilder();
		for (int i = 0; i < fieldValues.length; i++) {
			if (whereClauses.length() == 0) {	// if it's the first WHERE to be added don't include the "AND"
				whereClauses.append(checkForAnd(fieldValues[i], columnNames[i]));
			} else if (!fieldValues[i].equals("")) {	// otherwise add the "AND" before adding the additional statement
				whereClauses.append(" AND ").append(checkForAnd(fieldValues[i], columnNames[i]));
			}
		}
		return whereClauses.toString();
	}
}
