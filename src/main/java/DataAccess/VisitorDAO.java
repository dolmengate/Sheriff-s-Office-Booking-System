package info.sroman.SOBS;

import info.sroman.SOBS.Entities.Visitor;
import info.sroman.SOBS.Visitor.VisitorSearchModel;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorDAO extends Dao<Visitor, VisitorSearchModel> {
	
	private VisitorSearchModel model;
	
	@Override
	public ArrayList<Visitor> findAll(VisitorSearchModel model) {
		
		this.model = model;
		
		ArrayList<Visitor> visitors = new ArrayList<>();
    
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			stmt = conn.createStatement();
			stmt.setQueryTimeout(10);
			ResultSet rs = stmt.executeQuery(constructStatement());
			
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
		return visitors;
	}

	@Override
	public boolean create(VisitorSearchModel model) {
		return false;
	}
	
	@Override
	public boolean update(VisitorSearchModel model) {
		return false;
	}
	
	@Override
	public boolean delete(VisitorSearchModel model) {
		return false;
	}
	
	private String constructStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Person "
						+ "INNER JOIN "
							+ "Visitor ON Person.PERSON_ID = Visitor.PERSON_ID "
						+ "WHERE "
		);
		
		// parallel arrays that associate a TextField with its relevant table column
		String[] fieldValues = {
			model.getPersonId(), 
			model.getFirstName(), 
			model.getLastName(), 
			model.getHeight(), 
			model.getWeight(), 
			model.getDob(), 
			model.getRace(), 
			model.getVisitorId(), 
			model.getSsn()
		};
		
		String[] columns = {
			"PERSON_ID", "first_name", "last_name", "height", "weight", "date_of_birth", 
			"race", "VISITOR_ID", "ssn"
		};
		
		StringBuilder stmt = new StringBuilder();
		
		// if the statement has multiple WHERE clauses include an "AND" between them
		for (int i = 0; i < fieldValues.length; i++) {
			if (stmt.length() == 0)
				stmt.append(checkForAnd(fieldValues[i], columns[i]));
			else if (!fieldValues[i].equals(""))
				stmt.append(" AND ").append(checkForAnd(fieldValues[i], columns[i]));
		}
		
		baseStatement.append(stmt);	
		
		// Prevent "Ambugious column" error by adding Table name
		if (!model.getPersonId().equals(""))
			baseStatement.insert(baseStatement.indexOf(" PERSON_ID ") + 1, "Person.", 0, 7);
		
		String completedStatement = new String(baseStatement);
		System.out.println(completedStatement);

		return completedStatement;
	}
}
