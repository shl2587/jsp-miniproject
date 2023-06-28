package minihome;

import java.sql.Connection;
import java.sql.Date;
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
			e.printStackTrace();
		}
	}
	
	private void close() {
		try { if (rs != null)      rs.close();    } catch(Exception e) {}
	    try { if (pstmt != null)   pstmt.close();  } catch(Exception e) {}
	    try { if (conn != null)    conn.close();    } catch(Exception e) {}
	}
	
	private void connect(String sql) throws SQLException {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	public MinihomeDTO selectOne(int idx) {
		MinihomeDTO dto = null;
		String sql = "select * from minihome where idx = " + idx; 
		try {
			connect(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MinihomeDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setBoard(rs.getInt("board"));
				dto.setReply(rs.getInt("reply"));
				dto.setContent(rs.getString("content"));
				dto.setTitle(rs.getString("title"));
				dto.setTodayVisit(rs.getInt("todayVisit") + 1);
				dto.setTotalVisit(rs.getInt("totalVisit") + 1);
				dto.setLastConnect(rs.getDate("lastConnect"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		
		if(dto != null) visit(dto);
		
		return dto;
	}
	
	// 방문자 수 DB 업데이트
	private void countUpdate(String type, int count, int idx) {
		String sql = null;
		if(type.equals("todayVisit")) {
			sql = "update minihome set todayVisit = ? where idx = ?";
		}
		if(type.equals("totalVisit")) {
			sql = "update minihome set totalVisit = ? where idx = ?";
		}
		try {
			connect(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, idx);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }

		
	}
	
	private void visit(MinihomeDTO dto) {

		// 현재 접속일자를 DB에 업데이트
		String sql1 = "update minihome set lastConnect = (select to_char(sysdate, 'yyyy/MM/dd') from dual) where idx = ?";
		try {
			connect(sql1);
			pstmt.setInt(1, dto.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
		finally { close(); }

		// 현재 접속일자를 가져온다
		String sql2 = "select lastConnect from minihome where idx = ?";
		Date currentConnect = dto.getLastConnect();
		try {
			connect(sql2);
			pstmt.setInt(1, dto.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				currentConnect = rs.getDate("lastConnect");				
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { close(); }

		// 현재 접속일자와 마지막 접속일자가 다르면 오늘의 조회수 1으로 설정
		if(!currentConnect.equals(dto.getLastConnect())) { 
			dto.setTodayVisit(1);
		}
		
		// dto에 현재 접속일자를 덮어 씌운다
		dto.setLastConnect(currentConnect);
		
		// 조정된 조회수를 DB에 업데이트
		countUpdate("todayVisit", dto.getTodayVisit(), dto.getIdx());
		countUpdate("totalVisit", dto.getTotalVisit(), dto.getIdx());
	}
	

	public int createHome(String userid, String title) {
		int row = 0;
		String sql = "insert into minihome (idx, title)"
				+ " values((select idx from member where userid=?), ?)";
		
		try {
			connect(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, title);
			
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
