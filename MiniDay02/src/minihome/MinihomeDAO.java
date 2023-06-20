package minihome;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MinihomeDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Context init;
	private DataSource ds;
	
	
	private static MinihomeDAO instance = new MinihomeDAO();
	
	public static MinihomeDAO getInstance() {
		return instance;
	}
	
	private MinihomeDAO() {
		try {
			init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			System.err.println("MinihomeDAO 생성자 오류 : " + e);
//			e.printStackTrace();
		}
	}
	
	
	
	
	private void connect(String sql) throws SQLException {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	private void close() {
		try { if (rs != null)		rs.close(); 	} catch(Exception e) {}
		try { if (pstmt != null)	pstmt.close();  } catch(Exception e) {}
		try { if (conn != null) 	conn.close(); 	} catch(Exception e) {}
	}
	
	public int createHome(String userid, String title) {
		int row = 0;
		String sql = "insert into minihome (idx, title, todayvisit, totalvisit)"
				+ " values((select idx from member where userid=?), ?, ?, ?)";
		
		try {
			connect(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, title);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("MinihomeDAO createHome() 메서드 오류 : " + e);
//			e.printStackTrace();
		} finally {
			close();
		}
		
		return row;
	}
}
