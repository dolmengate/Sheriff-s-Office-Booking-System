package info.sroman.SOBS;

import info.sroman.SOBS.Model.Prisoner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static int personID = 100;
	private static int prisonerID = 400;
	private static int bunkID = 800;
	
	public static void genDB() {
			try {
				
				Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/db/SOBS.db");
				Statement statement = conn.createStatement();
				statement.setQueryTimeout(10);
				
				// create Person table
				statement.executeUpdate(
					"CREATE TABLE Person ("
						+ "PERSON_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "first_name STRING CONSTRAINT Person_first_name_NN NOT NULL, "
						+ "last_name STRING CONSTRAINT Person_last_name_NN NOT NULL, "
						+ "height INTEGER CONSTRAINT Person_height_NN NOT NULL, "
						+ "weight INTEGER CONSTRAINT Person_weight_NN NOT NULL, "
						+ "date_of_birth STRING CONSTRAINT Person_dob_NN NOT NULL, " // CONSTRAINT Person_dob_lt_now_CK CHECK (date_of_birth < date('now')		TODO add date formatting
						+ "race STRING CONSTRAINT Person_race_NN NOT NULL CONSTRAINT Person_race_wbh_CK CHECK (race = 'White' OR race = 'Black' OR race = 'Hispanic')"
					+ ")"
				);
				
				// create Visitor table
				statement.executeUpdate(
					"CREATE TABLE Visitor ("
						+ "VISITOR_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "ssn INTEGER CONSTRAINT Visitor_ssn_UK UNIQUE, "
						+ "person_id INTEGER CONSTRAINT Visitor_person_id_NN NOT NULL, "
						+ "FOREIGN KEY(person_id) REFERENCES Person(PERSON_ID)"
					+ ")"
				);
				
				// create Bunk table
				statement.executeUpdate(
					"CREATE TABLE Bunk ("
						+ "BUNK_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "position STRING CONSTRAINT Bunk_position_CK CHECK (position = 'top' OR position = 'bottom') CONSTRAINT Bunk_position_NN NOT NULL, "
						+ "cell_id INTEGER CONSTRAINT Bunk_cell_id_NN NOT NULL, "
						+ "prisoner_id INTEGER, "
						+ "FOREIGN KEY(cell_id) REFERENCES Cell(CELL_ID), "
						+ "FOREIGN KEY(prisoner_id) REFERENCES Prisoner(PRISONER_ID)"
					+ ")"
				);
				
				// create Prisoner table
				statement.executeUpdate(
					"CREATE TABLE Prisoner ("
						+ "PRISONER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "arrest_date STRING, "	// CONSTRAINT Prisoner_arrest_date_lt_now_CK CHECK (arrest_date < date('now')		TODO add date formatting
						+ "release_date STRING CONSTRAINT Prisoner_release_date_gt_arrest_date_CK CHECK (release_date > arrest_date), "
						+ "person_id INTEGER CONSTRAINT Prisoner_person_id_NN NOT NULL, "
						+ "bunk_id INTEGER CONSTRAINT Prisoner_bunk_id_NN NOT NULL, "
						+ "FOREIGN KEY(person_id) REFERENCES Person(PERSON_ID), "
						+ "FOREIGN KEY(bunk_id) REFERENCES Bunk(BUNK_ID)"
					+ ")"
				);
				
				// create Visit table
				statement.executeUpdate(
					"CREATE TABLE Visit ("
						+ "VISIT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "start_time STRING CONSTRAINT Visit_start_time_NN NOT NULL, "		// CONSTRAINT Visit_start_time_lt_now_CK CHECK (start_time < date('now')		 TODO add date formatting
						+ "end_time STRING CONSTRAINT Visit_end_time_gt_start_time_CK CHECK (end_time > start_time) CONSTRAINT Visit_end_time_NN NOT NULL, "
						+ "notes STRING, "
						+ "visitor_id INTEGER CONSTRAINT Visit_visitor_id_NN NOT NULL, "
						+ "prisoner_id INTEGER CONSTRAINT Visit_prisoner_id_NN NOT NULL, "
						+ "FOREIGN KEY(visitor_id) REFERENCES Visitor(VISITOR_ID), "
						+ "FOREIGN KEY(prisoner_id) REFERENCES Prisoner(PRISONER_ID)"
					+ ")"
				);
				
				
				// create Cell table
				statement.executeUpdate(
					"CREATE TABLE Cell ("
						+ "CELL_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "type STRING CONSTRAINT Cell_type_CK CHECK (type = 'MinSec' OR type = 'MaxSec' OR type = 'Hospital' OR type = 'Isolation'), "
						+ "lower_bunk INTEGER CONSTRAINT Cell_lower_bunk_NN NOT NULL, "
						+ "upper_bunk INTEGER, "
						+ "FOREIGN KEY(lower_bunk) REFERENCES Bunk(BUNK_ID), "
						+ "FOREIGN KEY(upper_bunk) REFERENCES Bunk(BUNK_ID)"
					+ ")"
				);
				
				// create Court_Date table
				statement.executeUpdate(
					"CREATE TABLE Court_Date ("
						+ "COURT_DATE_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "verdict STRING CONSTRAINT Court_Date_verdict_g_ng_CK CHECK (verdict = 'guilty' OR verdict = 'not guilty'), "
						+ "prisoner_id INTEGER, "
						+ "FOREIGN KEY(prisoner_id) REFERENCES Prisoner(PRISONER_ID)"
					+ ")"
				);				
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
	}
	
	public static void createPrisoner() {
		Prisoner p = new Prisoner(assignPersonID(), randomFirstName(), randomLastName(), 
				randomHeight(), randomWeight(), randomDOB(), randomRace(), assignPrisonerID(), 
				randomDate(), randomDate(), assignBunkID());
		p.createDBEntry();
		System.out.println("Prisoner created:\n" + p.toString());
		
	}
	
	private static String randomFirstName() {
		String[] FIRST_NAMES = {
			"Felix", 
			"Alfonso",
			"Roland",
			"Jonathon",
			"Shane",
			"Jay",
			"Garry",
			"Chris",
			"Allan",
			"Jamie",
			"Maurice",
			"Bennie",
			"Orlando",
			"Gilberto",
			"Stewart",
			"Brent",
			"Santiago",
			"Bob",
			"Kevin",
			"Willie",
			"Courtney",
			"Garrett",
			"Jermaine",
			"Jan",
			"Rudy",
			"Adrian",
			"Wade",
			"Ira",
			"Curtis"
		};
		
		return FIRST_NAMES[(int)(Math.random() * FIRST_NAMES.length)];
	}
	
	public static String randomLastName() {
		
		String[] LAST_NAMES = {
			"Vargas",
			"Burns",
			"Bass",
			"Hernandez",
			"Reyes",
			"Gilbert",
			"Knight",
			"Robinson",
			"Marshall",
			"Jenkins",
			"Mendez",
			"Garza",
			"Nash",
			"Wood",
			"Maldonado",
			"Fernandez",
			"Baldwin",
			"Harvey",
			"Sutton",
			"Mason",
			"Pearson",
			"Hall",
			"Green",
			"May",
			"Lawrence",
			"Schmidt",
			"Thompson"
		};
		
		return LAST_NAMES[(int)(Math.random() * LAST_NAMES.length)];
	}
	
	public static int randomHeight() {
		return (int)(Math.random() * 72) + 60;
	}
	
	public static int randomWeight() {
		return (int)(Math.random() * 250) + 150;
	}
	
	// Temporary placeholder until Date implementation
	public static String randomDOB() {
		StringBuilder date = new StringBuilder();
		date.append(Integer.toString((int)(Math.random() * 12) + 1));
		date.append("/");
		date.append(Integer.toString((int)(Math.random() * 31) + 1));
		date.append("/19");
		date.append(Integer.toString((int)(Math.random() * 99) + 30));
		return new String(date);
	}
	
	public static String randomRace() {
		String[] RACES = {
			"White",
			"Black",
			"Hispanic"
		};
		return RACES[(int)(Math.random() * 2)];
	}
	
		// Temporary placeholder until Date implementation
	public static String randomDate() {
		StringBuilder date = new StringBuilder();
		date.append(Integer.toString((int)(Math.random() * 12) + 1));
		date.append("/");
		date.append(Integer.toString((int)(Math.random() * 31) + 1));
		date.append("/20");
		date.append(Integer.toString((int)(Math.random() * 17) + 30));
		return new String(date);
	}
	
	public static int assignPersonID() {
		++Database.personID;
		return personID;
	}
	
	public static int assignPrisonerID() {
		++Database.prisonerID;
		return prisonerID;
	}
	
	public static int assignBunkID() {
		++Database.bunkID;
		return bunkID;
	}
}
