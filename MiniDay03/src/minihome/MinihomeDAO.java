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
	
	// 동영 작성
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
				int todayCount = rs.getInt("todayVisit");
				int totalCount = rs.getInt("totalVisit");
				
				// 마지막 접속일자 lastConnect에 저장
				Date lastConnect = rs.getDate("lastConnect");
				
				// 현재 접속일자를 DB에 업데이트
				String sql2 = "update minihome set lastConnect = sysdate where idx = " + idx;
				connect(sql2);
				pstmt.executeUpdate();
				
				// 현재 접속일자와 마지막 접속일자가 다르면 오늘의 조회수 0으로 설정
				if(lastConnect != rs.getDate("lastConnect")) {
					todayCount = 0;
				}
				
				// 조회수 1씩 증가
				todayCount++;
				totalCount++;
				
				// 증가된 조회수를 dto에 담는다
				dto.setTodayVisit(todayCount);
				dto.setTotalVisit(totalCount);
				
				// 조정된 조회수를 DB에 업데이트
				countUpdate("todayVisit", todayCount, rs.getInt("idx"));
				countUpdate("totalVisit", totalCount, rs.getInt("idx"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		
		return dto;
	}
	
	// 동영 작성
	// 방문자수 DB 업데이트
	private void countUpdate(String type, int count, int idx) {
		String sql = "update minihome set ? = ? where idx = ?";
		try {
			connect(sql);
			pstmt.setString(1, type);
			pstmt.setInt(2, count);
			pstmt.setInt(3, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
	}
}

