package friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
//	private MemberDTO mapping(ResultSet rs) throws SQLException {
//		
//		MemberDTO dto = new MemberDTO();
//		
//		dto.setBirth(rs.getString("birth"));
//		dto.setEmail(rs.getString("email"));
//		dto.setGender(rs.getString("gender"));
//		dto.setIdx(rs.getInt("idx"));
//		dto.setNickname(rs.getString("nickname"));
//		dto.setUserid(rs.getString("userid"));
//		dto.setUsername(rs.getString("username"));
//		dto.setUserpw(rs.getString("userpw"));
//		
//		return dto;
//	}
	
	
	public int addFriend(int myIdx, int frIdx) {
		int row = 0;
		String sql = "insert into friend (member, usernum) "
						+ "select ?, ? from dual where not exists (select * from friend where member=? and usernum=?)";
		
		try {
			connect(sql);
			pstmt.setInt(1, myIdx);
			pstmt.setInt(2, frIdx);
			pstmt.setInt(3, myIdx);
			pstmt.setInt(4, frIdx);
			
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("FriendDAO addFriend() 메서드 오류 : " + e);
//			e.printStackTrace();
		} finally {
			close();
		}
		return row;
	}
	
	public FriendDTO selectOne(int myIdx, String idxStr) {		
		FriendDTO dto = null;
		String sql = "select * from friend where member=?";
		
		if(idxStr.equals("frIdx")) {
			sql = "select * from friend where usernum=?";
		}
		
		try {
			connect(sql);
			pstmt.setInt(1, myIdx);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new FriendDTO();
				
				dto.setMember(myIdx);
				dto.setUsernum(rs.getInt("usernum"));
				dto.setStatement(rs.getInt("statement"));
				
			}
			
		} catch (SQLException e) {
			System.err.println("FriendDAO selectOne() 메서드 오류 : " + e);
	//		e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	public List<FriendDTO> friendRe(int myIdx, String idxStr) {
		ArrayList<FriendDTO> list = new ArrayList<>();
		String sql = "select * from friend where member=?";
		if (idxStr.equals("frIdx")) {
			sql = "select * from friend where usernum=?";
		}
		
		try {
			connect(sql);
			pstmt.setInt(1, myIdx);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FriendDTO fr = new FriendDTO();
				fr.setMember(rs.getInt("member"));
				fr.setUsernum(rs.getInt("usernum"));
				fr.setStatement(rs.getInt("statement"));
				
				list.add(fr);
			}
		} catch (SQLException e) {
			System.err.println("FriendDAO friendRe() 메서드 오류 : " + e);
//			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
//	public boolean requestCheck(int myIdx, int frIdx) {
//		boolean flag = false;
//		ArrayList<FriendDTO> list = (ArrayList<FriendDTO>)friendRe(myIdx);
//		
//		for (FriendDTO dto : list) {
//			if (dto.getUsernum() == frIdx) {
//				flag = true;
//				break;
//			}
//		}
//		return flag;
//	}
	
	public List<MemberDTO> requestList(int myIdx, String idxStr) {
		ArrayList<MemberDTO> list = new ArrayList<>();
		String sql = "select M.nickName, F.* from friend F "
						+ "join member M on M.idx=F.usernum "
						+ "where F.statement=0 and F.member=?";
		System.out.println(idxStr);
		if ("frIdx".equals(idxStr)) {
			sql = "select M.nickName, F.* from friend F "
					+ "join member M on M.idx=F.member "
					+ "where F.statement=0 and F.usernum=?";
			System.out.println(1);
		}
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myIdx);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNickname(rs.getString("nickname"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int acceptResult(int idx, int statement) {
		int row = 0;
		String sql = "update friend set statement=? where usernum=?";
		if (statement != 1) {
			sql = "delete from friend where idx=?";
			try {
				connect(sql);
				pstmt.setInt(1, idx);
				
				row = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		} 
		else {
			try {
				connect(sql);
				pstmt.setInt(1, statement);
				pstmt.setInt(2, idx);
				
				row = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		
		return row;
	}
}
