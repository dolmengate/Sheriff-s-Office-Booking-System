package DataAccess;

import info.sroman.SOBS.Entities.Visitor;
import Models.VisitorSearchModel;
import info.sroman.SOBS.Dao;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitorDAO extends Dao<Visitor, VisitorSearchModel> {
	
	private VisitorSearchModel model;
	
	@Override
	public ArrayList<Visitor> findAll(VisitorSearchModel model) {
		
		// give createSelectStatement method access to model to create query
		this.model = model;
		
		ArrayList<Visitor> visitors = new ArrayList<>();
    
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			stmt = conn.createStatement();
			stmt.setQueryTimeout(10);
			ResultSet rs = stmt.executeQuery(constructSelectStatement());
			
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
	
	private String constructSelectStatement() {
		
		StringBuilder baseStatement = new StringBuilder(
				"SELECT * FROM Person "
						+ "INNER JOIN "
							+ "Visitor ON Person.PERSON_ID = Visitor.PERSON_ID "
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
		
		// complete the statement if the user entered no search criteria
		if (fieldsAreEmpty(fieldValues)) {
//			baseStatement.append("Visitor.VISITOR_ID NOT NULL");
			System.out.println(baseStatement.toString());
			return baseStatement.toString();
		}
		
		if (fieldsAreEmpty(fieldValues)){
			System.out.println(baseStatement.toString());
			return baseStatement.toString();
		}
		
		// otherwise continue building the statement

		baseStatement.append("WHERE ");
		baseStatement.append(constructWhereClauses(fieldValues, columns));	
		
		// Prevent "Ambugious column" error by adding Table name
		if (!model.getPersonId().equals(""))
			baseStatement.insert(baseStatement.indexOf(" PERSON_ID ") + 1, "Person.", 0, 7);
		
		System.out.println(baseStatement.toString());

		return baseStatement.toString();
	}
}
