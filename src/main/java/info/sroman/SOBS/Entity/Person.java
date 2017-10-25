package info.sroman.SOBS.Entity;

public abstract class Person {
	
	protected final int PERSON_ID;
	protected String firstName;
	protected String lastName;
	protected final int HEIGHT;
	protected int weight;
	protected final String DOB;
	protected final String RACE;
	
	/*
		Constructor
	*/
	Person(int PERSON_ID, String firstName, String lastName, int height,
			int weight, String DOB, String race) {
		this.PERSON_ID = PERSON_ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.HEIGHT = height;
		this.weight = weight;
		this.DOB = DOB;
		this.RACE = race;
	}
	
	/*
		S E T T E R    M E T H O D S 
	*/
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	/*
		G E T T E R   M E T H O D S
	*/

	public int getPERSON_ID() {
		return PERSON_ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWeight() {
		return weight;
	}

	public String getDOB() {
		return DOB;
	}

	public String getRACE() {
		return RACE;
	}
	
	public abstract void createDBEntry();
	
	@Override
	public String toString() {
		return "PERSON_ID: " + getPERSON_ID()
				+ ", First Name: " + getFirstName()
				+ ", Last Name: " + getLastName()
				+ ", HEIGHT: " + getHEIGHT()
				+ ", Weight: " + getWeight()
				+ ", DOB: " + getDOB()
				+ ", Race: " + getRACE();
	}
}