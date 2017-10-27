package info.sroman.SOBS.Entities;

import info.sroman.SOBS.Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bunk extends Entity {

	private final int BUNK_ID;
	private final String POSITION;
	private final int CELL_ID;
	private int prisoner_Id;

	public Bunk(int BUNK_ID, String position, int cellId, int prisoner_Id) {
		this.BUNK_ID = BUNK_ID;
		this.POSITION = position;
		this.CELL_ID = cellId;
		this.prisoner_Id = prisoner_Id;
	}

	public int getBUNK_ID() {
		return BUNK_ID;
	}

	public String getPOSITION() {
		return POSITION;
	}

	public int getCELL_ID() {
		return CELL_ID;
	}

	public int getPrisoner_Id() {
		return prisoner_Id;
	}

	public void setPrisoner_Id(int prisoner_Id) {
		this.prisoner_Id = prisoner_Id;
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
					"INSERT INTO Bunk VALUES (?, ?, ?, ?)"
			);
			stmt.setInt(1, getBUNK_ID());
			stmt.setString(2, getPOSITION());
			stmt.setInt(3, getCELL_ID());
			stmt.setInt(4, getPrisoner_Id());
			stmt.executeUpdate();
			conn.commit();
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException ex) {
				System.err.println(ex);
			}
		}
		return true;
	}
}
