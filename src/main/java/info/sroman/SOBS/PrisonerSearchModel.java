package info.sroman.SOBS;

import javafx.collections.ObservableList;

public class PrisonerSearchModel {
	
	private String personId;
	private String firstName;
	private String lastName;
	private String height;
	private String weight;
	private String dob;
	private String race;
	private String prisonerId;
	private String arrestDate;
	private String releaseDate;
	private String bunkId;
	
	private ObservableList resultsList;
	
	public PrisonerSearchModel() {}
	
	public PrisonerSearchModel(String personId, String firstName, String lastName, String height, 
			String weight, String dob, String race, String prisonerId, String arrestDate, String releaseDate, String bunkId) {
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.height = height;
		this.weight = weight;
		this.dob = dob;
		this.race = race;
		this.prisonerId = prisonerId;
		this.arrestDate = arrestDate;
		this.releaseDate = releaseDate;
		this.bunkId = bunkId;
	}

//	public String getPersonId() {
//		return personId.getValue();
//	}
//	
//	public String getFirstName() {
//		return firstName.getValue();
//	}
//	
//	public String getLastName() {
//		return lastName.getValue();
//	}
//	
//	public String getHeight() {
//		return height.getValue();
//	}
//	
//	public String getWeight() {
//		return weight.getValue();
//	}
//	
//	public String getDob() {
//		return dob.getValue();
//	}
//	
//	public String getRace() {
//		return race.getValue();
//	}
//	
//	public String getPrisonerId() {
//		return prisonerId.getValue();
//	}
//	
//	public String getArrestDate() {
//		return arrestDate.getValue();
//	}
//	
//	public String getReleaseDate() {
//		return releaseDate.getValue();
//	}
//	
//	public String getBunkId() {
//		return bunkId.getValue();
//	}
//	
//	public ObservableList getResultsList() {
//		return this.resultsList;
//	}
//	
//	public void setPersonId(String value) {
//		this.personId.setValue(value);
//	}
//	
//	public void setFirstName(String value) {
//		this.firstName.setValue(value);
//	}
//	
//	public void setLastName(String value) {
//		this.lastName.setValue(value);
//	}
//	
//	public void setHeight(String value) {
//		this.height.setValue(value);
//	}
//	
//	public void setWeight(String value) {
//		this.weight.setValue(value);
//	}
//	
//	public void setDob(String value) {
//		this.dob.setValue(value);
//	}
//	
//	public void setRace(String value) {
//		this.race.setValue(value);
//	}
//	
//	public void setPrisonerId(String value) {
//		this.prisonerId.setValue(value);
//	}
//	
//	public void setArrestDate(String value) {
//		this.arrestDate.setValue(value);
//	}
//	
//	public void setReleaseDate(String value) {
//		this.releaseDate.setValue(value);
//	}
//	
//	public void setBunkId(String value) {
//		this.bunkId.setValue(value);
//	}
//	
//	public void setResultsList(ObservableList value) {
//		this.resultsList = value;
//	}
//	
//	public StringProperty firstNameProperty() {
//		return this.firstName;
//	}
//	
//	public StringProperty lastNameProperty() {
//		return this.lastName;
//	}
//	
//	public StringProperty heightProperty() {
//		return this.height;
//	}
//	
//	public StringProperty weightProperty() {
//		return this.weight;
//	}
//	
//	public StringProperty dobProperty() {
//		return this.dob;
//	}
//	
//	public StringProperty raceProperty() {
//		return this.race;
//	}
//	
//	public StringProperty prisonerIdProperty() {
//		return this.prisonerId;
//	}
//	
//	public StringProperty arrestDateProperty() {
//		return this.arrestDate;
//	}
//	
//	public StringProperty releaseDateProperty() {
//		return this.releaseDate;
//	}
//	
//	public StringProperty bunkIdProperty() {
//		return this.bunkId;
//	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getPrisonerId() {
		return prisonerId;
	}

	public void setPrisonerId(String prisonerId) {
		this.prisonerId = prisonerId;
	}

	public String getArrestDate() {
		return arrestDate;
	}

	public void setArrestDate(String arrestDate) {
		this.arrestDate = arrestDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getBunkId() {
		return bunkId;
	}

	public void setBunkId(String bunkId) {
		this.bunkId = bunkId;
	}

	public ObservableList getResultsList() {
		return resultsList;
	}

	public void setResultsList(ObservableList resultsList) {
		this.resultsList = resultsList;
	}
}
