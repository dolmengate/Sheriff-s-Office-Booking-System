package info.sroman.SOBS.dbClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prisoner extends Person {
	
	private final IntegerProperty PRISONER_ID;
	private final StringProperty ARREST_DATE;
	private StringProperty releaseDate;
	private IntegerProperty bunkID;
	
	/*
		Constructor
	*/
	public Prisoner(String firstName, String lastName, Integer weight, Integer height, String DOB, String race, String arrestDate, String releaseDate, Integer bunkID) {
		super(firstName, lastName, weight, height, DOB, race);
		
		this.PRISONER_ID = new SimpleIntegerProperty((int)(Math.random() * 100));	// TODO implement new ID assignment system
		this.bunkID = new SimpleIntegerProperty(bunkID);
		this.ARREST_DATE = new SimpleStringProperty(arrestDate);
		this.releaseDate = new SimpleStringProperty(releaseDate);
	}
	
	/*
		Getter Methods
	*/
	public int getPRISONER_ID() { return PRISONER_IDProperty().getValue(); }
	public String getARREST_DATE() { return ARREST_DATEProperty().getValue(); }
	public String getReleaseDate() { return releaseDateProperty().getValue(); }
	public int getBunkID() { return bunkIDProperty().getValue(); }
	
	/*
		Setter Methods
	*/
	public void setBunkID(int value) {				// TODO update DB when setters invoked
		bunkIDProperty().set(value);
	}
	public void setReleaseDate(String value) {
		releaseDateProperty().set(value);
	}	
	
	/*
		Property Getter Methods
	*/
	public IntegerProperty PRISONER_IDProperty() {
		return this.PRISONER_ID;
	}
	public IntegerProperty bunkIDProperty() {
		if (bunkID == null) bunkID = new SimpleIntegerProperty(this, "bunkID");
		return this.bunkID;
	}
	public StringProperty ARREST_DATEProperty() {
		return this.ARREST_DATE;
	}
	public StringProperty releaseDateProperty() {
		if (releaseDate == null) releaseDate = new SimpleStringProperty(this, "releaseDate");
		return this.releaseDate;
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
					"INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?)"
			);
			stmt.setInt(1, PERSON_IDProperty().getValue());
			stmt.setString(2, firstNameProperty().getValue());
			stmt.setString(3, lastNameProperty().getValue());
			stmt.setInt(4, HEIGHTProperty().getValue());
			stmt.setInt(5, weightProperty().getValue());
			stmt.setString(6, DOBProperty().getValue());
			stmt.setString(7, RACEProperty().getValue());
			stmt.executeUpdate();
			conn.commit();
			
			stmt = conn.prepareStatement(
					"INSERT INTO Prisoner VALUES (?, ?, ?, ?, ?)"
			);
			stmt.setInt(1, PRISONER_IDProperty().getValue());
			stmt.setString(2, ARREST_DATEProperty().getValue());
			stmt.setString(3, releaseDateProperty().getValue());
			stmt.setInt(4, PERSON_IDProperty().getValue());
			stmt.setInt(5, bunkIDProperty().getValue());
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
		return "PERSON_ID: " + PERSON_IDProperty().getValue()
				+ ", First Name: " + firstNameProperty().getValue()
				+ ", Last Name: " + lastNameProperty().getValue()
				+ ", HEIGHT: " + HEIGHTProperty().getValue()
				+ ", Weight: " + weightProperty().getValue()
				+ ", DOB: " + DOBProperty().getValue()
				+ ", Race: " + RACEProperty().getValue()
				+ ", PRISONER_ID: " + PRISONER_IDProperty().getValue()
				+ ", ARREST_DATE: " + ARREST_DATEProperty().getValue()
				+ ", releaseDate: " + releaseDateProperty().getValue()
				+ ", Bunk ID: " + bunkIDProperty().getValue();
	}
}
