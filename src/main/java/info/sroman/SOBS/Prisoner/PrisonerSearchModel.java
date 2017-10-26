package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.SearchModel;

public class PrisonerSearchModel extends SearchModel {
	
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
	private boolean released;
		
	public PrisonerSearchModel() {}
	
	public PrisonerSearchModel(String personId, String firstName, String lastName, String height, 
			String weight, String dob, String race, String prisonerId, String arrestDate, String releaseDate, String bunkId, boolean released) {
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
	
	public boolean isReleased() {
		return released;
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

	public void setReleased(boolean released) {
		this.released = released;
	}

	public String getBunkId() {
		return bunkId;
	}

	public void setBunkId(String bunkId) {
		this.bunkId = bunkId;
	}
}
