package info.sroman.SOBS;

import info.sroman.SOBS.Model.Visitor;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class VisitorSearchController implements Initializable {
		
	VisitorSearchModel model;
	
    public VisitorSearchModel submitBtn(VisitorSearchModel model, ActionEvent event) {
		
        this.model = model;
		
		Connection conn = null;
		Statement statement = null;
		ObservableList<Visitor> visitors = FXCollections.observableArrayList();
    
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			statement = conn.createStatement();
			statement.setQueryTimeout(10);
			ResultSet rs = statement.executeQuery(constructStatement());
						
			while (rs.next()) {
				visitors.add(new Visitor(
						rs.getInt("PERSON_ID"), rs.getString("first_name"), 
						rs.getString("last_name"), rs.getInt("height"), rs.getInt("weight"), 
						rs.getString("date_of_birth"), rs.getString("race"), rs.getInt("VISITOR_ID"),
						rs.getInt("ssn")
					)
				);
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
		model.setResultsList(visitors);
		return this.model;
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
							+ "Visitor ON Person.PERSON_ID = Visitor.PERSON_ID "
						+ "WHERE "
		);
		
		
		// parallel arrays that associate a TextField with its relevant table column
		String[] fieldValues = {
			model.getPersonId(), model.getFirstName(), model.getLastName(), 
			model.getHeight(), model.getWeight(), model.getDob(), model.getRace(), 
			model.getVisitorId(), model.getSsn()
		};
		
		String[] columns = {
			"PERSON_ID", "first_name", "last_name", "height", "weight", "date_of_birth", 
			"race", "VISITOR_ID", "ssn"
		};
		
		StringBuilder stmt = new StringBuilder();
		
		// if the statement has multiple WHERE clauses include an "AND" between them
		for (int i = 0; i < fieldValues.length; i++) {
			if (stmt.length() == 0)
				stmt.append(checkField(fieldValues[i], columns[i]));
			else if (!fieldValues[i].equals(""))
				stmt.append(" AND ").append(checkField(fieldValues[i], columns[i]));
		}
		
		baseStatement.append(stmt);	
		
		// Prevent "Ambugious column" error by adding Table name
		if (!model.getPersonId().equals(""))
			baseStatement.insert(baseStatement.indexOf(" PERSON_ID ") + 1, "Person.", 0, 7);
		
		String completedStatement = new String(baseStatement);
		System.out.println(completedStatement);

		return completedStatement;
	}
	
	/*
	*	Return AND statement if TextField has input, otherwise returns an empty string
	*/
	private String checkField(String fieldText, String colName) {
		StringBuilder fieldWhere = new StringBuilder();
		if (!fieldText.equals(""))
			return new String(fieldWhere.append(colName).append(" = '").append(fieldText).append("'"));
		return "";
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
