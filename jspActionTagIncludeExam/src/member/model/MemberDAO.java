package member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "jspActionTagIncludeExam";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- 오라클 접속 성공 --");					
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		}
	}
	
	public void getConnClose() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int setInsert(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into member values ("
					+ "seq_member.nextval, "
					+ "?, ?, ?, ?, ?, "
					+ "default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setInt(5, dto.getBornYear());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int getLogin(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "select no from member where id = ? and passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("no");
			}
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<MemberDTO> getListAll() {
		getConn();
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "select * from member order by no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				memberList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return memberList;
	}
	
	public ArrayList<MemberDTO> getPagingList(int[] pageRowsAndPageNum) {
		getConn();
		
		final int ONE_PAGE_ROWS = pageRowsAndPageNum[0];
		int pageNum = pageRowsAndPageNum[1];
		int endNum = pageNum * ONE_PAGE_ROWS;
		int startNum = endNum - ONE_PAGE_ROWS + 1;
		
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM "
					+ "(SELECT ROW_NUMBER() OVER (ORDER BY no) ORDER_NUM, member.* FROM member) "
					+ "WHERE ORDER_NUM BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				memberList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return memberList;
	}
	
	public int getAllRowsCount() {
		getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				allRowsCount = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally { 
			getConnClose();
		}
		
		return allRowsCount;
	}
	
	public MemberDTO getSelectOne(int no) {
		getConn();
		MemberDTO dto = new MemberDTO();
		try {
			String sql = "select * from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public int setUpdate(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update member set bornYear = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBornYear());
			pstmt.setInt(2, dto.getNo());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int setDelete(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "delete from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	
}
