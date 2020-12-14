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
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
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
			pstmt.setString(11, dto.getIsDelete());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int getMaxValue(String columnName) {
		getConn();
		int result = 0;
		try {
			String sql = "select nvl(max(" + columnName + "), 0) from board";
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
			String sql = "select * from board where isdelete = '0' order by refNo desc, levelNo asc";
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
	
	public void setUpdateReLevel(BoardDTO dto) {
		getConn();
		try {
			String sql = "update board set levelNo = (levelNo + 1) where refNo = ? and levelNo > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRefNo());
			pstmt.setInt(2, dto.getLevelNo());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}
	
	public int setUpdate(BoardDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update board set subject = ?, email = ?, content = ? where no = ?";
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
	
	public boolean isSoloContent(BoardDTO dto) {
		getConn();
		boolean result = false;
		int refNo = dto.getRefNo();
		int refNoCount = 0;
		try {
			String sql = "select count(*) from board where refNo = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, refNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				refNoCount = rs.getInt(1);
			}
			if (refNoCount == 1) {
				result = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int findSameRefNoMaxStepNo(BoardDTO dto) {
		getConn();
		int result = 0;
		int refNo = dto.getRefNo();
		try {
			String sql = "select max(stepNo) from board where refNo = ? and isDelete = '0'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, refNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public boolean isLastChild(BoardDTO dto) {
		getConn();
		boolean result = false;
		int refNo = dto.getRefNo();
		int maxStepNo = findSameRefNoMaxStepNo(dto);
		ArrayList<Integer> lastChildlist = new ArrayList<>();
		try {
			String sql = "select * from board where refNo = ? and isDelete = '0' and ((levelNo != stepNo) or (stepNo = ?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, refNo);
			pstmt.setInt(2, maxStepNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lastChildlist.add(rs.getInt("no"));
			}
			
			if (lastChildlist.contains(dto.getNo())) {
				result = true;
			}
			
			System.out.println(refNo);
			System.out.println(findSameRefNoMaxStepNo(dto));
			
			
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
	
	public int setIsDeleteTrue(BoardDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update board set isdelete = 1 where no = ?";
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













