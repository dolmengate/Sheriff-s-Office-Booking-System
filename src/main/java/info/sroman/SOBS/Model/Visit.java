package info.sroman.SOBS.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Visit {
	
	private final int VISIT_ID;
	private final String START_TIME;
	private final String END_TIME;
	private String notes;
	private final int VISITOR_ID;
	private final int PRISONER_ID;
	
	public Visit(int VISIT_ID, String START_TIME, String END_TIME, String notes, 
			int VISITOR_ID, int PRISONER_ID) {
		
		this.VISIT_ID = VISIT_ID;
		this.START_TIME = START_TIME;
		this.END_TIME = END_TIME;
		this.notes = notes;
		this.VISITOR_ID = VISITOR_ID;
		this.PRISONER_ID = PRISONER_ID;
	}

	public int getVISIT_ID() {
		return VISIT_ID;
	}

	public String getSTART_TIME() {
		return START_TIME;
	}

	public String getEND_TIME() {
		return END_TIME;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public int getVISITOR_ID() {
		return VISITOR_ID;
	}

	public int getPRISONER_ID() {
		return PRISONER_ID;
	}
	
	public void createDBEntry() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(
					"INSERT INTO Visit VALUES (?, ?, ?, ?, ?, ?)"
			);
			stmt.setInt(1, getVISIT_ID());
			stmt.setString(2, getSTART_TIME());
			stmt.setString(3, getEND_TIME());
			stmt.setString(4, getNotes());
			stmt.setInt(5, getVISITOR_ID());
			stmt.setInt(6, getPRISONER_ID());
			stmt.executeUpdate();
			conn.commit();
			
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
	}
	
	@Override
	public String toString() {
		return "Visit ID: " + getVISIT_ID() + 
				", Start Time: " + getSTART_TIME() + 
				", End Time: " + getEND_TIME() + 
				", Notes: " + getNotes() + 
				", Visitor ID: " + getVISITOR_ID() + 
				", Prisoner ID: " + getPRISONER_ID();
				
	}
}
