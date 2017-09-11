package info.sroman.SOBS.dbClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Person {
	
	protected final IntegerProperty PERSON_ID;
	protected StringProperty firstName;
	protected StringProperty lastName;
	protected final IntegerProperty HEIGHT;
	protected IntegerProperty weight;
	protected final StringProperty DOB;
	protected final StringProperty RACE;
	
	/*
		Constructor
	*/
	Person (String firstName, String lastName, int height,
			int weight, String DOB, String race) {
		
		this.PERSON_ID = new SimpleIntegerProperty(					// TODO implement new ID assignment system
			this, "PERSON_ID", (int)(Math.random() * 100)
		);
		this.firstName = new SimpleStringProperty(this, "firstName", firstName);
		this.lastName = new SimpleStringProperty(this, "lastName", lastName);
		this.HEIGHT = new SimpleIntegerProperty(this, "HEIGHT", height);
		this.weight = new SimpleIntegerProperty(this, "weight", weight);
		this.DOB = new SimpleStringProperty(this, "DOB", DOB);
		this.RACE = new SimpleStringProperty(this, "RACE", race);
	}
	
	/*
		Setter methods						TODO update db on setter invokation
	*/
	public void setFirstname(String value) {
		firstNameProperty().set(value);
	}
	public void setLastName(String value) {
		lastNameProperty().set(value);
	}
	public void setWeight(int value) {
		weightProperty().set(value);
	}
	 
	/*
		Getter Methods
	*/
	public int getPERSON_ID() { return PERSON_IDProperty().getValue(); }
	public String getFirstName() { return firstNameProperty().getValue(); }
	public String getLastName() { return lastNameProperty().getValue(); }
	public int getHeight() { return HEIGHTProperty().getValue(); }
	public int getWeight() { return weightProperty().getValue(); }
	public String getDOB() { return DOBProperty().getValue(); }
	public String getRace() { return RACEProperty().getValue(); }
	
	/*
		Property Getter Methods
	*/
	public IntegerProperty PERSON_IDProperty() {
		return this.PERSON_ID;
	}
	public StringProperty firstNameProperty() {
		if (this.firstName == null) firstName = new SimpleStringProperty(this, "firstName");
		return firstName;
	}
	public StringProperty lastNameProperty() {
		if (this.lastName == null) lastName = new SimpleStringProperty(this, "lastName");
		return lastName;
	}
	public IntegerProperty HEIGHTProperty() {
		return HEIGHT;
	}
	public IntegerProperty weightProperty() {
		if (this.weight == null) weight = new SimpleIntegerProperty(this, "weight");
		return weight;
	}
	public StringProperty DOBProperty() {
		return DOB;
	}
	public StringProperty RACEProperty() {
		return RACE;
	}

	@Override
	public String toString() {
		return "PERSON_ID: " + PERSON_IDProperty().getValue()
				+ ", First Name: " + firstNameProperty().getValue()
				+ ", Last Name: " + lastNameProperty().getValue()
				+ ", HEIGHT: " + HEIGHTProperty().getValue()
				+ ", Weight: " + weightProperty().getValue()
				+ ", DOB: " + DOBProperty().getValue()
				+ ", Race: " + RACEProperty().getValue();
	}
}