package info.sroman.SOBS.dbClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prisoner extends Person {
	
	private final int PRISONER_ID = (int)(Math.random() * 100);					// TODO implement new ID assignment system
	private final int PERSON_ID;
	private final String ARREST_DATE;
	private String releaseDate;
	private int bunkID;
	
	public Prisoner(String firstName, String lastName, Integer weight, Integer height, String DOB, String race, String arrestDate, String releaseDate, Integer bunkID) {
		super(firstName, lastName, weight, height, DOB, race);
		
		this.PERSON_ID = super.getPERSON_ID();
		this.bunkID = bunkID;
		this.ARREST_DATE = arrestDate;
		this.releaseDate = releaseDate;
		
		Connection conn = null;
		PreparedStatement createPrisoner = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			conn.setAutoCommit(false);
			createPrisoner = conn.prepareStatement(
					"INSERT INTO Prisoner VALUES (?, ?, ?, ?, ?)"
			);
			createPrisoner.setInt(1, this.PRISONER_ID);
			createPrisoner.setString(2, this.ARREST_DATE);
			createPrisoner.setString(3, this.releaseDate);
			createPrisoner.setInt(4, this.PERSON_ID);
			createPrisoner.setInt(5, this.bunkID);
			createPrisoner.executeUpdate();
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
	
	// Getters
	public int getPRISONER_ID() { return this.PRISONER_ID; }
	@Override
	public int getPERSON_ID() { return this.PERSON_ID; }
	public String getArrestDate() { return this.ARREST_DATE; }
	public String getReleaseDate() { return this.releaseDate; }
	public int getBunkID() { return this.bunkID; }
	
	// Setters
	public void setReleaseDate(String date) {		// TODO update DB when set
		this.releaseDate = date;
	}	
}
