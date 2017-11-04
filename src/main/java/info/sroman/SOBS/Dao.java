package info.sroman.SOBS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Dao <E extends Entity, M extends SearchModel> {
	
	protected Connection conn = null;
	protected Statement stmt = null;
	protected PreparedStatement prepStmt = null;
	
	public abstract ArrayList<E> findAll(M model);
	public abstract boolean create(M model);
	public abstract boolean update(M model);
	public abstract boolean delete(M model);
	
	/*
	*	Return AND statement if TextField has input, otherwise returns an empty string
	 */
	protected String checkForAnd(String fieldText, String colName) {
		StringBuilder fieldWhere = new StringBuilder();
		if (!fieldText.equals("")) {
			return fieldWhere.append(colName).append(" = '").append(fieldText).append("'").toString();
		}
		return "";
	}
}
