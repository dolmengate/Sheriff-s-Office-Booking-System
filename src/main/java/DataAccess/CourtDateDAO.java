package DataAccess;

import Models.CourtDateSearchModel;
import info.sroman.SOBS.Dao;
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
			
			ResultSet rs = stmt.executeQuery(
					constructSimpleSelectStatement(
							"Court_Date",
							new String[] {
								model.getCourtDateId(), 
								model.getDate(), 
								model.getVerdict(), 
								model.getPrisonerId()
							}, 
							new String[] {
								"COURT_DATE_ID", 
								"date", 
								"verdict", 
								"prisoner_id"
							}
					)
			);
						
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
}
