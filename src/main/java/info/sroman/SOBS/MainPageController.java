package info.sroman.SOBS;

import info.sroman.SOBS.Model.Prisoner;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainPageController implements Initializable {
		
	@FXML
	private TextField prisonerPersonIDField;
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
	private TextField prisonerPrisonerIDField;
	@FXML
	private TextField prisonerArrestDateField;
	@FXML
	private TextField prisonerReleaseDateField;
	@FXML
	private TextField prisonerBunkIDField;
	
	@FXML
	private TableView resultsTableView;
	
    @FXML
    private void submitBtn(ActionEvent event) {
        
		Connection conn = null;
		Statement statement = null;
		ObservableList<Prisoner> rowPeople = FXCollections.observableArrayList();
    
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			statement = conn.createStatement();
			statement.setQueryTimeout(10);
			ResultSet rs = statement.executeQuery(constructStatement());
			
			ArrayList<Prisoner> al = new ArrayList<>();
			
			while (rs.next()) {
				al.add(new Prisoner(
						rs.getInt("PERSON_ID"), rs.getString("first_name"), 
						rs.getString("last_name"), rs.getInt("weight"), rs.getInt("height"), 
						rs.getString("date_of_birth"), rs.getString("race"), rs.getInt("PRISONER_ID"),
						rs.getString("arrest_date"), rs.getString("release_date"), rs.getInt("bunk_ID")
					)
				);
			}
			rowPeople = FXCollections.observableArrayList(al);
						
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
		resultsTableView.setItems(rowPeople);
    }
	
	/*
	*	Check each TextField to see if it has input. If it does create a 
	*	statement that includes a WHERE clause including that field's value and
	*	which references that field's column in the table
	*/
	private String constructStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Person "
						+ "INNER JOIN "
							+ "Prisoner ON Person.PERSON_ID = Prisoner.PERSON_ID "
						+ "WHERE "
		);
		
		
		// parallel arrays that associate a TextField with its relevant table column
		TextField[] fields = {
			prisonerPersonIDField, prisonerFirstNameField, prisonerLastNameField, 
			prisonerHeightField, prisonerWeightField, prisonerDOBField, prisonerRaceField, 
			prisonerPrisonerIDField, prisonerArrestDateField, prisonerReleaseDateField, 
			prisonerBunkIDField
		};
		
		String[] columns = {
			"PERSON_ID", "first_name", "last_name", "height", "weight", "date_of_birth", 
			"race", "PRISONER_ID", "arrest_date", "release_date", "bunk_id"
		};
		
		StringBuilder stmt = new StringBuilder();
		
		// if the statement has multiple WHERE clauses include an "AND" between them
		for (int i = 0; i < fields.length; i++) {
			if (stmt.length() == 0)
				stmt.append(checkField(fields[i], columns[i]));
			else if (!fields[i].getText().equals(""))
				stmt.append(" AND ").append(checkField(fields[i], columns[i]));
		}
		
		baseStatement.append(stmt);	
		
		// Prevent "Ambugious column" error by adding Table name
		if (!prisonerPersonIDField.getText().equals(""))
			baseStatement.insert(baseStatement.indexOf(" PERSON_ID ") + 1, "Person.", 0, 7);
		
		String completedStatement = new String(baseStatement);
		System.out.println(completedStatement);

		return completedStatement;
	}
	
	/*
	*	Return AND statement if TextField has input, otherwise returns an empty string
	*/
	private String checkField(TextField field, String colName) {
		StringBuilder fieldWhere = new StringBuilder();
		if (!field.getText().equals(""))
			return new String(fieldWhere.append(colName).append(" = '").append(field.getText()).append("'"));
		return "";
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<Prisoner,String> personIDCol = new TableColumn<>("Person ID");
		personIDCol.setCellValueFactory(new PropertyValueFactory("PERSON_ID"));
		TableColumn<Prisoner,String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		TableColumn<Prisoner,String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		TableColumn<Prisoner,Integer> heightCol = new TableColumn<>("Height");
		heightCol.setCellValueFactory(new PropertyValueFactory("HEIGHT"));
		TableColumn<Prisoner,Integer> weightCol = new TableColumn<>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		TableColumn<Prisoner,String> DOBCol = new TableColumn<>("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory("DOB"));
		TableColumn<Prisoner,String> raceCol = new TableColumn<>("Race");
		raceCol.setCellValueFactory(new PropertyValueFactory("RACE"));
		TableColumn<Prisoner,String> prisonerIDCol = new TableColumn<>("Prisoner ID");
		prisonerIDCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		TableColumn<Prisoner,String> bunkIDCol = new TableColumn<>("Bunk ID");
		bunkIDCol.setCellValueFactory(new PropertyValueFactory("bunkID"));
		TableColumn<Prisoner,String> arrestDateCol = new TableColumn<>("Arrest Date");
		arrestDateCol.setCellValueFactory(new PropertyValueFactory("ARREST_DATE"));
		TableColumn<Prisoner,String> releaseDateCol = new TableColumn<>("Release Date");
		releaseDateCol.setCellValueFactory(new PropertyValueFactory("releaseDate"));
		
		resultsTableView.getColumns().setAll(personIDCol, firstNameCol, lastNameCol, heightCol, weightCol, DOBCol, raceCol, prisonerIDCol, bunkIDCol, arrestDateCol, releaseDateCol);
    }    
}
