package board;

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

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Context init;
	private DataSource ds;
	
	private void close() {
		try { if (rs != null)      rs.close();    } catch(Exception e) {}
	    try { if (pstmt != null)   pstmt.close();  } catch(Exception e) {}
	    try { if (conn != null)    conn.close();    } catch(Exception e) {}
	}
	
	private void connect(String sql) throws SQLException {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
	}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private BoardDAO() {
		try {
			init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private BoardDTO mapping(ResultSet rs) throws SQLException {
		BoardDTO dto = new BoardDTO();
		dto.setIdx(rs.getInt("idx"));
		dto.setTitle(rs.getString("title"));
		dto.setWriter(rs.getInt("writer"));
		dto.setContent(rs.getString("content"));
		dto.setFolder(rs.getString("folder"));
		dto.setFileName(rs.getString("fileName"));
		dto.setWriteDate(rs.getDate("writeDate"));
		dto.setIpaddr(rs.getString("ipaddr"));
		
		return dto;
	}
	
	public List<BoardDTO> select(String column, String search, int writer) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select M.userName, B.* from board B" + 
					 " join member M" + 
					 " on M.idx = B.writer"
					 + " where B.writer = " + writer;
		String like = " and %s like '%%' || ? || '%%'";
		like = String.format(like, "B." + column);
		
		String order = " order by B.idx desc";
		boolean flag = "".equals(column) == false && "".equals(search) == false;
		sql += flag ? like + order : order;
		try {
			connect(sql);
			if(flag) {
				pstmt.setString(1, search);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		
		return list;
	}
}
