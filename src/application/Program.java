package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		/* TRANSAÇÃO */
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();

			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rowsAffected1 = st.executeUpdate("UPDATE seller set BaseSalary = 2090 WHERE DepartmentId = 1");
			
//			int x = 1;
//			if (x < 2) {
//				throw new SQLException("Fake error");
//			}
			
			int rowsAffected2 = st.executeUpdate("UPDATE seller set BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("Done! Rows Affected: " + rowsAffected1 + " / " + rowsAffected2);
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying rollback! Caused by: " + e.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
		/* EXCLUIR DADOS
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			st.setInt(1, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		*/
		
		// ---
		
		/* ATUALIZAR DADOS 
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE DepartmentID = ?");
			st.setDouble(1, 200.0);
			st.setInt(2, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		*/
		
		// ---
		
		/* INSERINDO DADOS 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentID) VALUES(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmai.com");
			st.setDate(3, new java.sql.Date(sdf.parse("04/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affected!");
			}
			
			//System.out.println("Done! Rows Affected: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		*/

		// ---
		
		/* CONSULTANDO DADOS 
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from department");

			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
		*/
		
	}

}
