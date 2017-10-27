package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.Entities.Prisoner;
import info.sroman.SOBS.SearchModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrisonerSearchController extends Controller {
	
	PrisonerSearchModel model;
	
	@Override
    public SearchModel makeSelect(SearchModel model) {
		
        this.model = (PrisonerSearchModel) model;
		
		Connection conn = null;
		Statement statement = null;
		ObservableList<Prisoner> prisoners = FXCollections.observableArrayList();
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			statement = conn.createStatement();
			statement.setQueryTimeout(10);
			ResultSet rs = statement.executeQuery(constructStatement());
						
			while (rs.next()) {
				Prisoner p  = new Prisoner(
						rs.getInt("PERSON_ID"), rs.getString("first_name"), 
						rs.getString("last_name"), rs.getInt("height"), 
						rs.getInt("weight"), rs.getString("date_of_birth"), 
						rs.getString("race"), rs.getInt("PRISONER_ID"),
						rs.getString("arrest_date"), rs.getString("release_date"), 
						rs.getInt("bunk_ID"), (rs.getInt("is_released") == 1)
				);
				
				p.setCellBlock(rs.getString("type"));
				
				prisoners.add(p);
			}
		
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
		model.setResultsList(prisoners);
		return this.model;
    }
	
	/*
	*	Check each TextField to see if it has input. If it does create a 
	*	statement that includes a WHERE clause including that field's value and
	*	which references that field's column in the table
	*/
	@Override
	public String constructStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Person "
						+ "LEFT JOIN "
							+ "Prisoner ON Person.PERSON_ID = Prisoner.PERSON_ID "
						+ "LEFT JOIN "
							+ "Bunk ON Prisoner.bunk_id = Bunk.BUNK_ID "
						+ "LEFT JOIN "
							+ "Cell ON Bunk.cell_id = Cell.CELL_ID "
		);
		
		
		// parallel arrays that associate a TextField with its relevant table column
		String[] fieldValues = {
			model.getPersonId(), model.getFirstName(), model.getLastName(), 
			model.getHeight(), model.getWeight(), model.getDob(), model.getRace(), 
			model.getPrisonerId(), model.getArrestDate(), model.getReleaseDate(), 
			model.getBunkId(), model.getCellType()
		};
		
		String[] columns = {
			"PERSON_ID", "first_name", "last_name", "height", "weight", "date_of_birth", 
			"race", "PRISONER_ID", "arrest_date", "release_date", "bunk_id", "Cell.type"
		};
		
		
		// return just the base statement if no parameters were specified
		// (i.e. SELECT all Prisoners with no other qualifications
		StringBuilder emptyTest = new StringBuilder();
		
		for (int i = 0; i < fieldValues.length; i++) {
			emptyTest.append(fieldValues[i]);
			if (emptyTest.length() == 0)
				return baseStatement.append(" WHERE Prisoner.PRISONER_ID NOT NULL").toString();
		}
		
		StringBuilder whereClauses = new StringBuilder("WHERE ");
		
		// if the statement has multiple WHERE clauses include an "AND" between them
		for (int i = 0; i < fieldValues.length; i++) {
			if (whereClauses.length() == 0)
				whereClauses.append(checkForAnd(fieldValues[i], columns[i]));
			else if (!fieldValues[i].equals(""))
				whereClauses.append(" AND ").append(checkForAnd(fieldValues[i], columns[i]));
		}
		
		baseStatement.append(whereClauses);	
		
		// Prevent "Ambugious column" error by adding Table name
		if (!model.getPersonId().equals(""))
			baseStatement.insert(baseStatement.indexOf(" PERSON_ID ") + 1, "Person.", 0, 7);
		
		// only return Prisoners not released
		baseStatement.append(" AND is_released = '0' ");
				
		System.out.println(baseStatement);

		return baseStatement.toString();
	}
}
