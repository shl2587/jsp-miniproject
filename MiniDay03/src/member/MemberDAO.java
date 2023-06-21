package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Context init;
	private DataSource ds;
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	private MemberDAO() {
		try {
			init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			System.err.println("MemberDAO 생성자 오류 : " + e);
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
	
	public MemberDTO login(MemberDTO member) {
		MemberDTO dto = null;
		String sql = "select * from member where userid=? and userpw=?";
		if (member.getUserid().contains("@")) {
			sql = "select * from member where email=? and userpw=?";
		}
		try {
			connect(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setUserid(rs.getString("userid"));
				dto.setUserpw(rs.getString("userpw"));
				dto.setUsername(rs.getString("username"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setBirth(rs.getString("birth"));
			}
		} catch (SQLException e) {
			System.err.println("MemberDAO login() 메서드 오류 : " + e);
//			e.printStackTrace();
		} finally { close(); }
		
		return dto;
	}

	public int insert(MemberDTO dto) {
		int row = 0;
		String sql = "insert into member (userid, userpw, username, nickname, email, gender, birth) " + 
				"    values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connect(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUserpw());
			pstmt.setString(3, dto.getUsername());
			pstmt.setString(4, dto.getNickname());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getGender());
			pstmt.setString(7, dto.getBirth());
			
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("MemberDAO insert() 메서드 오류 : " + e);
//			e.printStackTrace();
		} finally {
			close();
		}
		
		return row;
	}
	
	public int duplicationcheck(String check, String user) {
		int row = 0;
		String sql = "select * from member where userid=?";
		if ("email".equals(check)) {
			sql = "select * from member where email=?";
		}
		if ("nickname".equals(check)) {
			sql = "select * from member where nickname=?";
		}
		try {
			connect(sql);
			
			pstmt.setString(1, user);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				row = 1;
			}
			
		} catch (SQLException e) {
			System.err.println("MemberDAO duplicationcheck() 메서드 오류 : " + e);
//			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return row;
	}
	
}
