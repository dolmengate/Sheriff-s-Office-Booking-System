package info.sroman.SOBS;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RowPerson {
	
	protected final IntegerProperty PERSON_ID;
	protected StringProperty firstName;
	protected StringProperty lastName;
	protected final IntegerProperty HEIGHT;
	protected IntegerProperty weight;
	protected final StringProperty DOB;
	protected final StringProperty RACE;
	private final IntegerProperty PRISONER_ID;
	private final StringProperty ARREST_DATE;
	private StringProperty releaseDate;
	private IntegerProperty bunkID;
	
	/*
		Constructor
	*/
	public RowPerson(Integer personID, String firstName, String lastName, Integer weight, Integer height, 
			String DOB, String race, Integer prisonerID, String arrestDate, String releaseDate, Integer bunkID) {
		
		this.PERSON_ID = new SimpleIntegerProperty(this, "PERSON_ID", personID);
		this.firstName = new SimpleStringProperty(this, "firstName", firstName);
		this.lastName = new SimpleStringProperty(this, "lastName", lastName);
		this.HEIGHT = new SimpleIntegerProperty(this, "HEIGHT", height);
		this.weight = new SimpleIntegerProperty(this, "weight", weight);
		this.DOB = new SimpleStringProperty(this, "DOB", DOB);
		this.RACE = new SimpleStringProperty(this, "RACE", race);
		this.PRISONER_ID = new SimpleIntegerProperty(this, "PRISONER_ID", prisonerID);	// TODO implement new ID assignment system
		this.bunkID = new SimpleIntegerProperty(bunkID);
		this.ARREST_DATE = new SimpleStringProperty(arrestDate);
		this.releaseDate = new SimpleStringProperty(releaseDate);
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
	public String getReleaseDate() { return releaseDateProperty().getValue(); }
	public int getBunkID() { return bunkIDProperty().getValue(); }
	
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