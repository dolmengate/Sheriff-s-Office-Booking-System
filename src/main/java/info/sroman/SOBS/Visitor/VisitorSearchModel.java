package info.sroman.SOBS.Visitor;

import info.sroman.SOBS.SearchModel;

public class VisitorSearchModel extends SearchModel {
	
	private String personId;
	private String firstName;
	private String lastName;
	private String height;
	private String weight;
	private String dob;
	private String race;
	private String visitorId;
	private String ssn;
	
	public VisitorSearchModel() {}
	
	public VisitorSearchModel(String personId, String firstName, String lastName, String height, 
			String weight, String dob, String race, String visitorId, String ssn) {
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.height = height;
		this.weight = weight;
		this.dob = dob;
		this.race = race;
		this.visitorId = visitorId;
		this.ssn = ssn;
	}
	

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

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
}
