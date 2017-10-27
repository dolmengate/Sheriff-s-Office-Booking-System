package info.sroman.SOBS.Entities;

import info.sroman.SOBS.Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cell extends Entity {

	private final int CELL_ID;
	private final String TYPE;
	private final int LOWER_BUNK;
	private final Integer UPPER_BUNK;

	public Cell(int CELL_ID, String TYPE, int LOWER_BUNK, Integer UPPER_BUNK) {
		this.CELL_ID = CELL_ID;
		this.TYPE = TYPE;
		this.LOWER_BUNK = LOWER_BUNK;
		this.UPPER_BUNK = UPPER_BUNK;
	}

	public int getCELL_ID() {
		return CELL_ID;
	}

	public String getTYPE() {
		return TYPE;
	}

	public int getLOWER_BUNK() {
		return LOWER_BUNK;
	}

	public int getUPPER_BUNK() {
		return UPPER_BUNK;
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
					"INSERT INTO Cell VALUES (?, ?, ?, ?)"
			);
			stmt.setInt(1, getCELL_ID());
			stmt.setString(2, getTYPE());
			stmt.setInt(3, getLOWER_BUNK());
			stmt.setInt(4, getUPPER_BUNK());
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
				+ ", Type: " + getTYPE()
				+ ", Lower Bunk: " + getLOWER_BUNK()
				+ ", Upper Bunk: " + getUPPER_BUNK();
	}
}
