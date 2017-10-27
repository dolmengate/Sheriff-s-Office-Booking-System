package info.sroman.SOBS.Entities;

import info.sroman.SOBS.Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourtDate extends Entity {
	
	private final int COURT_DATE_ID;
	private String date;
	private String verdict;
	private final int PRISONER_ID;

	public CourtDate(int courtDateId, String date, String verdict, int prisonerId) {
		this.COURT_DATE_ID = courtDateId;
		this.date = date;
		this.verdict = verdict;
		this.PRISONER_ID = prisonerId;
	}

	public int getCOURT_DATE_ID() {
		return COURT_DATE_ID;
	}

	public String getDate() {
		return date;
	}

	public String getVerdict() {
		return verdict;
	}

	public int getPRISONER_ID() {
		return PRISONER_ID;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}

	@Override
	public boolean createDBEntry() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(
					"INSERT INTO Court_Date VALUES (?, ?, ?, ?)"
			);
			stmt.setInt(1, getCOURT_DATE_ID());
			stmt.setString(2, getDate());
			stmt.setString(3, getVerdict());
			stmt.setInt(4, getPRISONER_ID());
			stmt.executeUpdate();
			conn.commit();
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException ex) {
				System.err.println(ex);
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "COURT_DATE_ID: " + getCOURT_DATE_ID()
				+ ", date: " + getDate()
				+ ", verdict: " + getVerdict()
				+ ", Prisoner ID: " + getPRISONER_ID();
	}
}
