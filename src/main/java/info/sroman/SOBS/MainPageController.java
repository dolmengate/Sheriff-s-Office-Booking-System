package info.sroman.SOBS;

import info.sroman.SOBS.dbClasses.Person;
import info.sroman.SOBS.dbClasses.Prisoner;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainPageController implements Initializable {
    
	@FXML
	private TextField prisonerFirstNameField;
	@FXML
	private TextField prisonerLastNameField;
	@FXML
	private TextField prisonerHeightField;
	@FXML
	private TextField prisonerWeightField;
	@FXML
	private TextField prisonerDOBField;
	@FXML
	private TextField prisonerRaceField;
	@FXML
	private Label resultFirstName;
	@FXML
	private Label resultLastName;
	@FXML
	private Label resultHeight;
	@FXML
	private Label resultWeight;
	@FXML
	private Label resultDOB;
	@FXML
	private Label resultRace;
	@FXML
	private TableView resultsTableView;
	
    @FXML
    private void submitBtn(ActionEvent event) {
        
		ObservableList<Person> resultsList;
		
		Connection conn = null;
		Statement statement = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			statement = conn.createStatement();
			statement.setQueryTimeout(10);
			ResultSet rs = statement.executeQuery(constructStatement());
			
			while (rs.next()) {
				System.out.println("First name: " + rs.getString("first_name"));
				System.out.println("Last name: " + rs.getString("last_name"));
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
    }
	
	
	/*
	*	Check each TextField to see if it has input. If it does create a 
	*	statement that includes a WHERE clause including that field's value and
	*	which references that field's column in the table
	*/
	private String constructStatement() {
		StringBuilder baseStatement = new StringBuilder("SELECT * FROM Person WHERE ");
		StringBuilder stmt = new StringBuilder();
		TextField[] fields = {
			prisonerFirstNameField, prisonerLastNameField, prisonerHeightField,
			prisonerWeightField, prisonerDOBField, prisonerRaceField
		};
		String[] columns = {"first_name", "last_name", "height", "weight", "date_of_birth", "race"};
		
		// if the statement has multiple WHERE clauses include an "AND" between them
		for (int i = 0; i < fields.length; i++) {
			if (stmt.length() == 0)
				stmt.append(checkField(fields[i], columns[i]));
			else if (!fields[i].getText().equals(""))
				stmt.append(" AND ").append(checkField(fields[i], columns[i]));
		}
		
		baseStatement.append(stmt);
		
		return new String(baseStatement);
	}
	
	private String checkField(TextField field, String colName) {
		StringBuilder fieldWhere = new StringBuilder();
		if (!field.getText().equals(""))
			return new String(fieldWhere.append(colName).append(" = '").append(field.getText()).append("'"));
		return "";
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
