package info.sroman.SOBS.Visit;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.Entity.Visit;
import info.sroman.SOBS.SearchModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VisitSearchController extends Controller {
	
	VisitSearchModel model;
	
	@Override
	public SearchModel makeSelect(SearchModel model) {
		
        this.model = (VisitSearchModel) model;
		
		Connection conn = null;
		Statement statement = null;
		ObservableList<Visit> visits = FXCollections.observableArrayList();
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			statement = conn.createStatement();
			statement.setQueryTimeout(10);
			ResultSet rs = statement.executeQuery(constructStatement());
						
			while (rs.next()) {
				visits.add(new Visit(
						rs.getInt("VISIT_ID"), rs.getString("start_time"), 
						rs.getString("end_time"), rs.getString("notes"),
						rs.getInt("visitor_id"), rs.getInt("prisoner_id")
					)
				);
			}
						
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException ex) {
				System.err.println(ex);
			}
		}
		model.setResultsList(visits);
		return this.model;
    }
	
	@Override
	public String constructStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Visit "
						+ "WHERE "
		);
		
		
		// parallel arrays that associate a TextField with its relevant table column
		String[] fieldValues = {
			model.getVisitId(), model.getStartTime(), model.getEndTime(), 
			model.getNotes(), model.getVisitorId(), model.getPrisonerId()
		};
		
		String[] columns = {
			"VISIT_ID", "start_time", "end_time", "notes", "visitor_id", "prisoner_id"
		};
		
		StringBuilder stmt = new StringBuilder();
		
		// if the statement has multiple WHERE clauses include an "AND" between them
		for (int i = 0; i < fieldValues.length; i++) {
			if (stmt.length() == 0)
				stmt.append(checkForAnd(fieldValues[i], columns[i]));
			else if (!fieldValues[i].equals(""))
				stmt.append(" AND ").append(checkForAnd(fieldValues[i], columns[i]));
		}
		
		baseStatement.append(stmt);	
		
		System.out.println(baseStatement);

		return baseStatement.toString();
	}
}
