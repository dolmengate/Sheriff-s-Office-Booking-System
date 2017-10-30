package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.SearchModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrisonerAddController extends Controller {
	
	PrisonerSearchModel model;
	
	@Override
	public SearchModel makeSelect(SearchModel model) {
		return new PrisonerSearchModel();
	}
	
	public SearchModel makeInsert(SearchModel model) throws SQLException, NumberFormatException {
		
		this.model = (PrisonerSearchModel) model;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(
					"INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?, ?)"
			);
			stmt.setInt(1, Integer.valueOf(this.model.getPersonId()));
			stmt.setString(2, this.model.getFirstName());
			stmt.setString(3, this.model.getLastName());
			stmt.setInt(4, Integer.valueOf(this.model.getHeight()));
			stmt.setInt(5, Integer.valueOf(this.model.getWeight()));
			stmt.setString(6, this.model.getDob());
			stmt.setString(7, this.model.getRace());
			stmt.executeUpdate();
			conn.commit();
			
			stmt = conn.prepareStatement(
					"INSERT INTO Prisoner VALUES (?, ?, ?, ?, ?, ?)"
			);
			stmt.setInt(1, Integer.valueOf(this.model.getPrisonerId()));
			stmt.setString(2, this.model.getArrestDate());
			stmt.setString(3, this.model.getReleaseDate());
			stmt.setInt(4, Integer.valueOf(this.model.getPersonId()));
			stmt.setInt(5, Integer.valueOf(this.model.getBunkId()));
			stmt.setInt(6, (this.model.isReleased() ? 1 : 0 ));
			stmt.executeUpdate();
			conn.commit();
			
		} catch (SQLException | NumberFormatException ex) {
			System.err.println(ex);
			throw ex;
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException ex) {
				System.err.println(ex);
				throw ex;
			}
		}
		return new PrisonerSearchModel();
	}
	
	@Override
	public String constructStatement() {
		return "";
	}
}
