package info.sroman.SOBS;

import info.sroman.SOBS.CourtDate.CourtDateSearchModel;
import info.sroman.SOBS.Entities.CourtDate;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourtDateDAO extends Dao<CourtDate, CourtDateSearchModel> {
	
	private CourtDateSearchModel model;
	
	@Override
	public ArrayList<CourtDate> findAll(CourtDateSearchModel model) {
		
		this.model = model;
		
		ArrayList<CourtDate> courtDates = new ArrayList<>();
		
		try {
			
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			stmt = conn.createStatement();
			stmt.setQueryTimeout(10);
			ResultSet rs = stmt.executeQuery(constructStatement());
						
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
		return courtDates;
	}
	
	@Override
	public boolean create(CourtDateSearchModel model) {
		return false;
	}
	
	@Override
	public boolean update(CourtDateSearchModel model) {
		return false;
	}
	
	@Override
	public boolean delete(CourtDateSearchModel model) {
		return false;
	}
	
	private String constructStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Court_Date "
						+ "WHERE "
		);
		
		// parallel arrays that associate a TextField with its relevant table column
		String[] fieldValues = {
			model.getCourtDateId(), 
			model.getDate(), 
			model.getVerdict(), 
			model.getPrisonerId()
		};
		
		String[] columns = {
			"COURT_DATE_ID", 
			"date", 
			"verdict", 
			"prisoner_id"
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
