package info.sroman.SOBS.dbClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Person {
	
	private final int PERSON_ID = (int)(Math.random() * 100);					// TODO implement new ID assignment system
	private String firstName;
	private String lastName;
	private final int HEIGHT;
	private int weight;
	private final String DOB;
	private final String RACE;
	
	// Constructor
	// Also generates database entry for constructed person
	Person (String firstName, String lastName, int height,
			int weight, String DOB, String ethnicity) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.HEIGHT = height;
		this.weight = weight;
		this.DOB = DOB;
		this.RACE = ethnicity;
		
		Connection conn = null;
		PreparedStatement createPerson = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			conn.setAutoCommit(false);
			createPerson = conn.prepareStatement(
					"INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?)"
			);
			createPerson.setInt(1, this.PERSON_ID);
			createPerson.setString(2, this.firstName);
			createPerson.setString(3, this.lastName);
			createPerson.setInt(4, this.HEIGHT);
			createPerson.setInt(5, this.weight);
			createPerson.setString(6, this.DOB);
			createPerson.setString(7, this.RACE);
			createPerson.executeUpdate();
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
	protected int getPERSON_ID() { return this.PERSON_ID; }
	protected String getFirstName() { return this.firstName; }
	protected String getLastName() { return this.lastName; }
	protected int getHeight() { return this.HEIGHT; }
	protected int getWeight() { return this.weight; }
	protected String getRace() { return this.RACE; }
	
	// Setters								// TODO update DB when setters invoked
	public void setFirstname(String fn) {
		this.firstName = fn;
	}
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}