package info.sroman.SOBS.CourtDate;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.Entity.CourtDate;
import info.sroman.SOBS.SearchModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourtDateSearchController extends Controller {
	
	CourtDateSearchModel model;
	
	@Override
	public SearchModel makeSelect(SearchModel model) {
		this.model = (CourtDateSearchModel) model;
		
		Connection conn = null;
		Statement statement = null;
		ObservableList<CourtDate> courtDates = FXCollections.observableArrayList();
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			statement = conn.createStatement();
			statement.setQueryTimeout(10);
			ResultSet rs = statement.executeQuery(constructStatement());
						
			while (rs.next()) {
				courtDates.add(new CourtDate(
						rs.getInt("COURT_DATE_ID"), rs.getString("date"), 
						rs.getString("verdict"), rs.getInt("prisoner_id")
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
		model.setResultsList(courtDates);
		return this.model;
	}
	
	@Override
	public String constructStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Court_Date "
						+ "WHERE "
		);
		
		// parallel arrays that associate a TextField with its relevant table column
		String[] fieldValues = {
			model.getCourtDateId(), model.getDate(), model.getVerdict(), 
			model.getPrisonerId()
		};
		
		String[] columns = {
			"COURT_DATE_ID", "date", "verdict", "prisoner_id"
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
