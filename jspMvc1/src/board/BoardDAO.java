package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public BoardDAO() {}
	
	// Method
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "jspMvc1";
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
	
	public int setInsert(BoardDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into board values (seq_board.nextval, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPasswd());
			pstmt.setInt(7, dto.getRefNo());
			pstmt.setInt(8, dto.getStepNo());
			pstmt.setInt(9, dto.getLevelNo());
			pstmt.setInt(10, dto.getHit());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int getMaxNum() {
		getConn();
		int result = 0;
		try {
			String sql = "select nvl(max(num, 0)) as maxNum from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1); // 컬럼의 순서 입력해도 된다.
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<BoardDTO> getSelectAll() {
		getConn();
		ArrayList<BoardDTO> arrayList = new ArrayList<>();
		try {
			String sql = "select * from board order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setRefNo(rs.getInt("refNo"));
				dto.setStepNo(rs.getInt("stepNo"));
				dto.setLevelNo(rs.getInt("levelNo"));
				dto.setHit(rs.getInt("hit"));
				dto.setRegiDate(rs.getDate("regiDate"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return arrayList;
	}
	
	public BoardDTO getSelectOne(int no) {
		getConn();
		BoardDTO dto = new BoardDTO();
		try {
			String sql = "select * from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setRefNo(rs.getInt("refNo"));
				dto.setStepNo(rs.getInt("stepNo"));
				dto.setLevelNo(rs.getInt("levelNo"));
				dto.setHit(rs.getInt("hit"));
				dto.setRegiDate(rs.getDate("regiDate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public void setUpdateHit(int no) {
		getConn();
		try {
			String sql = "update board set hit = (hit + 1) where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}
	
	public int setUpdate(BoardDTO dto) {
		
		int result = 0;
		try {
			String sql = "update board set subject = ?, email = ?, content = ? where no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		
		return result;
	}
	
	public int deletePost(BoardDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "delete from board where no = ? ";
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













