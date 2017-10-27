package info.sroman.SOBS.Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Visitor extends Person {
	
	private final int VISITOR_ID;
	private final int SSN;
	
	public Visitor(int PERSON_ID, String firstName, String lastName, int height, 
			int weight, String DOB, String race, int VISITOR_ID, int ssn) {
		
		super(PERSON_ID, firstName, lastName, height, weight, DOB, race);
		this.VISITOR_ID = VISITOR_ID;
		this.SSN = ssn;
	}

	public int getVISITOR_ID() {
		return VISITOR_ID;
	}

	public int getSSN() {
		return SSN;
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
					"INSERT INTO Visitor VALUES (?, ?, ?)"
			);
			stmt.setInt(1, getVISITOR_ID());
			stmt.setInt(2, getSSN());
			stmt.setInt(3, getPERSON_ID());
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
		return "PERSON_ID: " + getPERSON_ID()
				+ ", First Name: " + getFirstName()
				+ ", Last Name: " + getLastName()
				+ ", HEIGHT: " + getHEIGHT()
				+ ", Weight: " + getWeight()
				+ ", DOB: " + getDOB()
				+ ", Race: " + getRACE()
				+ ", VISITOR_ID: " + getVISITOR_ID()
				+ ", SSN: " + getSSN();
	}
	
}
