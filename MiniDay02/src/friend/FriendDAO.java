package friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.MemberDTO;


public class FriendDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Context init;
	private DataSource ds;
	
	private static FriendDAO instance = new FriendDAO();
	
	public static FriendDAO getInstance() {
		return instance;
	}
	
	private FriendDAO() {
		try {
			init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			System.err.println("FriendDAO 생성자 오류 : " + e);
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
	
	private MemberDTO mapping(ResultSet rs) throws SQLException {
		
		MemberDTO dto = new MemberDTO();
		
		dto.setBirth(rs.getString("birth"));
		dto.setEmail(rs.getString("email"));
		dto.setGender(rs.getString("gender"));
		dto.setIdx(rs.getInt("idx"));
		dto.setNickname(rs.getString("nickname"));
		dto.setUserid(rs.getString("userid"));
		dto.setUsername(rs.getString("username"));
		dto.setUserpw(rs.getString("userpw"));
		
		return dto;
	}
	
//	public MemberDTO addFriend(int myIdx, int frIdx) {
//		int row = 0;
//		MemberDTO dto = null;
//		String sql1 = "select * from member where idx=?";
//		String sql2 = "insert into friend(member, usernum) values (?, ?)";
//		
//		try {
//			connect(sql1);
//			pstmt.setInt(1, frIdx);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				dto = mapping(rs);
//			}
//				
//		} catch (SQLException e) {
//			System.err.println("FriendDAO addFriend() 메서드 오류 : " + e);
////			e.printStackTrace();
//		} finally {
//			close();
//		}
//		
//		
//		try {
//			connect(sql2);
//			pstmt.setInt(1, myIdx);
//			pstmt.setInt(2, frIdx);
//			
//			row = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.err.println("FriendDAO addFriend() 메서드 insert 쿼리 오류 : " + e);
////			e.printStackTrace();
//		} finally {
//			close();
//		}
//		
//		
//		return dto;
//	}
	
	public int addFriend(int myIdx, int frIdx) {
		int row = 0;
		String sql = "insert into friend(member, usernum) values (?, ?)";
		
		try {
			connect(sql);
			pstmt.setInt(1, myIdx);
			pstmt.setInt(2, frIdx);
			
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("FriendDAO addFriend() 메서드 오류 : " + e);
//			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return row;
	}
	
	public MemberDTO selectOne(int myIdx) {		
		MemberDTO dto = null;
		String sql = "select * from member where idx=?";
		
		try {
			connect(sql);
			pstmt.setInt(1, myIdx);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = mapping(rs);
			}
			
		} catch (SQLException e) {
			System.err.println("FriendDAO addFriend() 메서드 오류 : " + e);
	//		e.printStackTrace();
		} finally {
			close();
		}
		
		return dto;
	}
	
}
