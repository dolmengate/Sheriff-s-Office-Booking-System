package info.sroman.SOBS.Entities;

import info.sroman.SOBS.Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cell extends Entity {

	private final int CELL_ID;
	private final String TYPE;

	public Cell(int CELL_ID, String TYPE) {
		this.CELL_ID = CELL_ID;
		this.TYPE = TYPE;
	}

	public int getCELL_ID() {
		return CELL_ID;
	}

	public String getTYPE() {
		return TYPE;
	}
	
	@Override
	public boolean createDBEntry() {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:sqlite:./src/main/resources/db/SOBS.db"
			);
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(
					"INSERT INTO Cell VALUES (?, ?)"
			);
			stmt.setInt(1, getCELL_ID());
			stmt.setString(2, getTYPE());
			stmt.executeUpdate();
			conn.commit();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.err.println(ex);
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cell ID: " + getCELL_ID()
				+ ", Type: " + getTYPE();
	}
}
