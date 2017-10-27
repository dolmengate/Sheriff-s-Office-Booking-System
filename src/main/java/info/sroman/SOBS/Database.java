package info.sroman.SOBS;

import info.sroman.SOBS.Entities.Bunk;
import info.sroman.SOBS.Entities.Cell;
import info.sroman.SOBS.Entities.CourtDate;
import info.sroman.SOBS.Entities.Prisoner;
import info.sroman.SOBS.Entities.Visit;
import info.sroman.SOBS.Entities.Visitor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Database {

	private static int personID = 100;
	private static int prisonerID = 400;
	private static int visitorID = 600;
	private static int visitID = 900;
	private static int bunkID = 0;
	private static int courtDateID = 700;
	private static int cellId = 200;
	
	public static void genDB() {
		try {
			
			// TODO integer length constraints

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
					+ "position STRING CONSTRAINT Bunk_position_CK CHECK (position = 'Top' OR position = 'Bottom') CONSTRAINT Bunk_position_NN NOT NULL, "
					+ "cell_id INTEGER CONSTRAINT Bunk_cell_id_NN NOT NULL, "
					+ "prisoner_id INTEGER CONSTRAINT Bunk_prisoner_id_UK UNIQUE, "
					+ "FOREIGN KEY(cell_id) REFERENCES Cell(CELL_ID), "
					+ "FOREIGN KEY(prisoner_id) REFERENCES Prisoner(PRISONER_ID)"
					+ ")"
			);

			// create Prisoner table
			statement.executeUpdate(
					"CREATE TABLE Prisoner ("
					+ "PRISONER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "arrest_date STRING, " // CONSTRAINT Prisoner_arrest_date_lt_now_CK CHECK (arrest_date < date('now')		TODO add date formatting
					+ "release_date STRING CONSTRAINT Prisoner_release_date_gt_arrest_date_CK CHECK (release_date > arrest_date), "
					+ "person_id INTEGER CONSTRAINT Prisoner_person_id_NN NOT NULL, "
					+ "bunk_id INTEGER CONSTRAINT Prisoner_bunk_id_NN NOT NULL, "
					+ "is_released INTEGER CONSTRAINT Prisoner_is_released_NN NOT NULL CONSTRAINT Prisoner_is_released_0_1_CK CHECK (is_released = '0' OR is_released = '1'), "
					+ "FOREIGN KEY(person_id) REFERENCES Person(PERSON_ID), "
					+ "FOREIGN KEY(bunk_id) REFERENCES Bunk(BUNK_ID)"
					+ ")"
			);

			// create Visit table
			statement.executeUpdate(
					"CREATE TABLE Visit ("
					+ "VISIT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "start_time STRING CONSTRAINT Visit_start_time_NN NOT NULL, " // CONSTRAINT Visit_start_time_lt_now_CK CHECK (start_time < date('now')		 TODO add date formatting
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
					+ "type STRING CONSTRAINT Cell_type_CK CHECK (type = 'MinSec' OR type = 'MaxSec' OR type = 'Hospital' OR type = 'Isolation')"
					+ ")"
			);

			// create Court_Date table
			statement.executeUpdate(
					"CREATE TABLE Court_Date ("
					+ "COURT_DATE_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "date STRING CONSTRAINT Court_Date_date_NN NOT NULL,  "	
					+ "verdict STRING CONSTRAINT Court_Date_verdict_g_ng_CK CHECK (verdict = 'Guilty' OR verdict = 'Not Guilty' OR verdict = 'Pending'), "
					+ "prisoner_id INTEGER, "
					+ "FOREIGN KEY(prisoner_id) REFERENCES Prisoner(PRISONER_ID)"
					+ ")"
			);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	public static void createPrisoner(int bunkId) {
		Prisoner p = new Prisoner(assignPersonID(), randomFirstName(), randomLastName(),
				randomHeight(), randomWeight(), randomDOB(), randomRace(), assignPrisonerID(),
				randomDateString(), randomDateString(), bunkId, false);
		insertAndPrintStatus(p);
	}

	public static void createVisitor() {
		Visitor v = new Visitor(assignPersonID(), randomFirstName(), randomLastName(),
				randomHeight(), randomWeight(), randomDOB(), randomRace(), assignVisitorID(), randomSSN());
		insertAndPrintStatus(v);
	}

	public static void createVisit() {
		LocalDateTime startTime = randomStartTime();
		Visit v = new Visit(assignVisitID(), startTime.toString(), randomEndTime(startTime).toString(), "",
				randomInRange(900, visitID), randomInRange(400, prisonerID));

		insertAndPrintStatus(v);
	}
	
	public static void createCourtDate() {
		CourtDate cd = new CourtDate(
				assignCourtDateID(), randomDateString(), "Pending", randomInRange(400, prisonerID)
		);
		insertAndPrintStatus(cd);
	}
	
	public static void createBunk(int bunkId, int cellId) {
		Bunk b = new Bunk(
				bunkId, (Database.bunkID % 2 == 0 ? "Top" : "Bottom"),
				cellId,
				randomInRange(400, Database.prisonerID)
		);
		insertAndPrintStatus(b);
	}
	
	public static Cell createCell(String type) {
		Cell c = new Cell(assignCellId(), type);
		insertAndPrintStatus(c);
		return c;
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

		return FIRST_NAMES[(int) (Math.random() * FIRST_NAMES.length)];
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

		return LAST_NAMES[(int) (Math.random() * LAST_NAMES.length)];
	}

	public static int randomHeight() {
		String feet = Integer.toString((int) (Math.random() * 6) + 4);
		String inches = Integer.toString((int) (Math.random() * 12));
		return Integer.valueOf(feet.concat(inches));
	}

	public static int randomWeight() {
		return (int) (Math.random() * 250) + 150;
	}

	public static String randomDOB() {
		return randomDateString();
	}

	public static String randomRace() {
		String[] RACES = {
			"White",
			"Black",
			"Hispanic"
		};
		return RACES[(int) (Math.random() * 2)];
	}

	public static String randomDateString() {
		try {
			return LocalDate.of(
					randomInRange(1950, 2017),
					randomInRange(1, 12),
					randomInRange(1, 29)
			).toString();
		} catch (DateTimeException ex) {
			System.err.println(ex.getMessage());
			System.err.println(
					"Invalid DateTime created (bad day created on non-leapyear, "
					+ "trying again.");
		}
		return randomDateString();
	}

	public static LocalDateTime randomStartTime() {
		return genLocalDateTime();
	}

	public static LocalDateTime randomEndTime(LocalDateTime startTime) {
		LocalDateTime time = genLocalDateTime();
		while (time.isBefore(startTime)) {
			time = genLocalDateTime();
		}
		return time;
	}

	private static LocalDateTime genLocalDateTime() {
		try {
			LocalDate date = LocalDate.of(
					randomInRange(2000, 2017),
					randomInRange(1, 12),
					randomInRange(1, 29)
			);
			LocalDateTime time = date.atTime(LocalTime.of(
					randomInRange(0, 23),
					randomInRange(0, 59),
					randomInRange(0, 59)
			)
			);
			return time;
		} catch (DateTimeException ex) {
			System.err.println(ex.getMessage());
			System.err.println(
					"Invalid DateTime created (bad day created on non-leapyear, "
					+ "trying again.");
		}
		return genLocalDateTime();
	}

	public static int assignPersonID() {
		return ++Database.personID;
	}

	public static int assignPrisonerID() {
		return ++Database.prisonerID;
	}

	public static int assignBunkID() {
		return ++Database.bunkID;
	}

	public static int assignVisitorID() {
		return ++Database.visitorID;
	}

	public static int assignVisitID() {
		return ++Database.visitID;
	}
	
	public static int assignCourtDateID() {
		return ++Database.courtDateID;
	}
	
	public static int assignCellId() {
		return ++Database.cellId;
	}

	private static int randomInRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	private static int randomSSN() {
		return randomInRange(100000000, 999999999);
	}
	
	private static <E extends Entity> void insertAndPrintStatus(E entity) {
		if(entity.createDBEntry())
			System.out.println(entity.getClass().getName() + " created:\n" + entity.toString());
		else
			System.err.println(entity.getClass().getName() + " creation FAILED");
		}
}
