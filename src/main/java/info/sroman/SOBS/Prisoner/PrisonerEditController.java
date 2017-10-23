package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.SearchModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;

public class PrisonerEditController extends Controller {

	PrisonerSearchModel model;
	
	// TODO if either update statement fails, cancel both 

	public void makeUpdate(SearchModel model) {

		this.model = (PrisonerSearchModel) model;

		Connection conn = null;
		Statement statement = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);

			statement = conn.createStatement();
			statement.setQueryTimeout(10);
			statement.executeUpdate(constructPersonStatement());
			statement.executeUpdate(constructPrisonerStatement());

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.err.println(ex);
			}
		}
	}
	
	@Override 
	public SearchModel makeSelect(SearchModel model) {
		return new PrisonerSearchModel();
	}
	
	@Override
	public String constructStatement() {
		return "";
	}
	
	/*
		Person and Prisoner must be joined to update the Prisoner record completely
		SQlite 3.2 does not support WITH clauses, therefore I am forced to update
		each table in a separate statement.
	*/

	private String constructPersonStatement() {
		StringBuilder baseStatement = new StringBuilder(
				"UPDATE Person SET "
		);

		String[] personValues = {
			model.getPersonId(), model.getFirstName(), model.getLastName(),
			model.getHeight(), model.getWeight(), model.getDob(), model.getRace()
		};

		String[] personColumns = {
			"PERSON_ID", "first_name", "last_name", "height", "weight", "date_of_birth",
			"race"
		};

		for (int i = 0; i < personValues.length; i++) {
			baseStatement.append(personColumns[i]).append(" = '").append(personValues[i]).append("'");
			if (i != personValues.length - 1)
				baseStatement.append(", ");
		}

		baseStatement.append(" WHERE Person.PERSON_ID = ").append(model.getPersonId());

		System.out.println(baseStatement.toString());

		return baseStatement.toString();
	}
	
	private String constructPrisonerStatement() {
		StringBuilder baseStatement = new StringBuilder(
				"UPDATE Prisoner SET "
		);
		
		String[] prisonerValues = {
			model.getPrisonerId(), model.getArrestDate(), model.getReleaseDate(), model.getBunkId()
		};
		String[] prisonerColumns = {
			"PRISONER_ID", "arrest_date", "release_date", "bunk_id"
		};
		
		for (int i = 0; i < prisonerValues.length; i++) {
			baseStatement.append(prisonerColumns[i]).append(" = '").append(prisonerValues[i]).append("'");
			if (i != prisonerValues.length - 1)
				baseStatement.append(", ");
		}
		
		baseStatement.append(" WHERE Prisoner.PRISONER_ID = ").append(model.getPrisonerId());

		System.out.println(baseStatement.toString());
		
		return baseStatement.toString();
	}
}
