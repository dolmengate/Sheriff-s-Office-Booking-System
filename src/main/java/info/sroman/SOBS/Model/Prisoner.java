package info.sroman.SOBS.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prisoner extends Person {
	
	private final int PRISONER_ID;
	private final String ARREST_DATE;
	private String releaseDate;
	private int bunkID;
	private boolean released;
	
	/*
		Constructor
	*/
	
	public Prisoner(int PERSON_ID, String firstName, String lastName, int height,
			int weight, String dob, String race, int PRISONER_ID, String arrestDate, 
			String releaseDate, int bunkId, boolean released) {
		super (PERSON_ID, firstName, lastName, height, weight, dob, race);
		this.PRISONER_ID = PRISONER_ID;
		this.ARREST_DATE = arrestDate;
		this.releaseDate = releaseDate;
		this.bunkID = bunkId;
		this.released = released;
	}
	
	/*
		S E T T E R    M E T H O D S
	*/

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setBunkID(int bunkID) {
		this.bunkID = bunkID;
	}
	
	public void setReleased(boolean released) {
		this.released = released;
	} 
	
	
	/*
		G E T T E R    M E T H O D S 
	*/

	public int getPRISONER_ID() {
		return PRISONER_ID;
	}

	public String getARREST_DATE() {
		return ARREST_DATE;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public int getBunkID() {
		return bunkID;
	}
	
	public boolean isReleased() {
		return released;
	}
	
	@Override
	public void createDBEntry() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(
					"INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?)"
			);
			stmt.setInt(1, getPERSON_ID());
			stmt.setString(2, getFirstName());
			stmt.setString(3, getLastName());
			stmt.setInt(4, getHEIGHT());
			stmt.setInt(5, getWeight());
			stmt.setString(6, getDOB());
			stmt.setString(7, getRACE());
			stmt.executeUpdate();
			conn.commit();
			
			stmt = conn.prepareStatement(
					"INSERT INTO Prisoner VALUES (?, ?, ?, ?, ?, ?)"
			);
			stmt.setInt(1, getPRISONER_ID());
			stmt.setString(2, getARREST_DATE());
			stmt.setString(3, getReleaseDate());
			stmt.setInt(4, getPERSON_ID());
			stmt.setInt(5, getBunkID());
			stmt.setInt(6, (isReleased()) ? 1 : 0 );
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
		return "PERSON_ID: " + getPERSON_ID()
				+ ", First Name: " + getFirstName()
				+ ", Last Name: " + getLastName()
				+ ", HEIGHT: " + getHEIGHT()
				+ ", Weight: " + getWeight()
				+ ", DOB: " + getDOB()
				+ ", Race: " + getRACE()
				+ ", PRISONER_ID: " + getPRISONER_ID()
				+ ", ARREST_DATE: " + getARREST_DATE()
				+ ", releaseDate: " + getReleaseDate()
				+ ", Bunk ID: " + getBunkID()
				+ ", Released?: " + isReleased();
	}
}
