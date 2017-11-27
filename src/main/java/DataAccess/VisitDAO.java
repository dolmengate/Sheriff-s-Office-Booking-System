package DataAccess;

import info.sroman.SOBS.Entities.Visit;
import Models.VisitSearchModel;
import info.sroman.SOBS.Dao;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitDAO extends Dao<Visit, VisitSearchModel> {
	
	private VisitSearchModel model;
	
	@Override
	public ArrayList<Visit> findAll(VisitSearchModel model) {
		
		this.model = model;
		
		ArrayList<Visit> visits = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			stmt = conn.createStatement();
			stmt.setQueryTimeout(10);
			ResultSet rs = stmt.executeQuery(constructSelectStatement());
			
			while (rs.next()) {
				visits.add(
					new Visit(
						rs.getInt("VISIT_ID"),
						rs.getString("start_time"), 
						rs.getString("end_time"), 
						rs.getString("notes"),
						rs.getInt("visitor_id"), 
						rs.getInt("prisoner_id")
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
		return visits;
	}
	
	@Override
	public boolean create(VisitSearchModel model) {
		return false;
	}
	
	@Override
	public boolean update(VisitSearchModel model) {
		return false;
	}
	
	@Override
	public boolean delete(VisitSearchModel model) {
		return false;
	}
	
	private String constructSelectStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Visit "
		);
		
		// parallel arrays that associate a TextField with its relevant table column
		String[] fieldValues = {
			model.getVisitId(), 
			model.getStartTime(), 
			model.getEndTime(), 
			model.getNotes(), 
			model.getPrisonerId(), 
			model.getPrisonerId()
		};
		
		String[] columns = {
			"VISIT_ID", "start_time", "end_time", "notes", "visitor_id", "prisoner_id"
		};
		
		if (fieldsAreEmpty(fieldValues)) {
			System.out.println(baseStatement.toString());
			return baseStatement.toString();
		}
		
		baseStatement.append("WHERE ");
		baseStatement.append(constructWhereClauses(fieldValues, columns));	
		
		System.out.println(baseStatement);

		return baseStatement.toString();
	}
	
}
