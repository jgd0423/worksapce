package model.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.board.dto.BoardDTO;
import model.board.dto.CommentDTO;
import model.member.dto.MemberDTO;

public class BoardDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String BOARD = "board";
	final String BOARD_COMMENT = "board_comment";
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}
	
	
	public int setInsert(BoardDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + BOARD + " VALUES (seq_board.NEXTVAL, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getTbl());
			pstmt.setString(3, dto.getWriter());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getPasswd());
			pstmt.setInt(8, dto.getRefNo());
			pstmt.setInt(9, dto.getStepNo());
			pstmt.setInt(10, dto.getLevelNo());
			pstmt.setInt(11, dto.getParentNo());
			pstmt.setInt(12, dto.getHit());
			pstmt.setString(13, dto.getIp());
			pstmt.setInt(14, dto.getMemberNo());
			pstmt.setInt(15, dto.getNoticeNo());
			pstmt.setString(16, dto.getSecretGubun());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getMaxNum() {
		int result = 0;
		conn = getConn();
		try {
			String sql = "SELECT NVL(MAX(num), 0) FROM " + BOARD;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int getMaxRefNo() {
		int result = 0;
		conn = getConn();
		try {
			String sql = "SELECT NVL(MAX(refNo), 0) FROM " + BOARD;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getMaxNoticeNo(String tbl) {
		int result = 0;
		conn = getConn();
		try {
			String sql = "SELECT NVL(MAX(noticeNo), 0) FROM " + BOARD + " WHERE tbl = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tbl);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getAllRowsCount(String tbl, String search_option, String search_data) {
		conn = getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM " + BOARD + " WHERE tbl = ? ";
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("writer") || search_option.equals("subject") || search_option.equals("content")) {
					sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("writer_subject_content")) {
					sql += " and (writer LIKE ? OR subject LIKE ? OR content LIKE ?) ";
				}
			}
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++pstmtNum, tbl);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("writer") || search_option.equals("subject") || search_option.equals("content")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("writer_subject_content")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				}
			}
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				allRowsCount = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return allRowsCount;
	}
	
	public ArrayList<BoardDTO> getPagingList(int startNum, int endNum, String tbl, String search_option, String search_data) {
		conn = getConn();
		ArrayList<BoardDTO> list = new ArrayList<>();
		try {
			String basic_sql = "";
			basic_sql += "SELECT t1.*, ";
			basic_sql += "(SELECT COUNT(*) FROM " + BOARD + " t2 WHERE t2.parentNo = t1.no) child_counter ";	
			basic_sql += "FROM " + BOARD + " t1 WHERE tbl = ? ";
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("writer") || search_option.equals("subject") || search_option.equals("content")) {
					basic_sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("writer_subject_content")) {
					basic_sql += " AND (writer LIKE ? OR subject LIKE ? OR content LIKE ?) ";
				}
			}
			
			basic_sql += "ORDER BY noticeNo DESC, refNo DESC, levelNo ASC";
			
			String sql = "";
			sql += "SELECT * FROM (SELECT A.*, Rownum Rnum FROM (" + basic_sql + ") A) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++pstmtNum, tbl);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("writer") || search_option.equals("subject") || search_option.equals("content")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("writer_subject_content")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				}
			}
			
			pstmt.setInt(++pstmtNum, startNum);
			pstmt.setInt(++pstmtNum, endNum);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setTbl(rs.getString("tbl"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setRefNo(rs.getInt("refNo"));
				dto.setStepNo(rs.getInt("stepNo"));
				dto.setLevelNo(rs.getInt("levelNo"));
				dto.setParentNo(rs.getInt("parentNo"));
				dto.setHit(rs.getInt("hit"));
				dto.setIp(rs.getString("ip"));
				dto.setMemberNo(rs.getInt("memberNo"));
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setSecretGubun(rs.getString("secretGubun"));
				dto.setRegiDate(rs.getDate("regiDate"));
				dto.setChild_counter(rs.getInt("child_counter"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return list;
	}

	public void setUpdateHit(int no) {
		conn = getConn();
		try {
			String sql = "UPDATE " + BOARD + " SET hit = (hit + 1) WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public BoardDTO getView(int no) {
		BoardDTO dto = new BoardDTO();
		conn = getConn();
		try {
			String sql = "";
			sql += "SELECT * FROM ";
			sql += "(";
			sql += "SELECT b.*, ";
			sql += "(SELECT COUNT(*) FROM " + BOARD + " WHERE refNo = b.refNo AND stepNo = (b.stepNo + 1) AND levelNo = (b.levelNo + 1)) child_counter, ";
			sql += "LAG(no) OVER (ORDER BY noticeNo DESC, refNo DESC, levelNo ASC) preNo, ";
			sql += "LAG(subject) OVER (ORDER BY noticeNo DESC, refNo DESC, levelNo ASC) preSubject, ";
			sql += "LEAD(no) OVER (ORDER BY noticeNo DESC, refNo DESC, levelNo ASC) nxtNo, ";
			sql += "LEAD(subject) OVER (ORDER BY noticeNo DESC, refNo DESC, levelNo ASC) nxtSubject ";
			sql += "FROM " + BOARD + " b ORDER BY noticeNo DESC, refNo DESC, levelNo ASC";
			sql += ") WHERE no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setTbl(rs.getString("tbl"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setRefNo(rs.getInt("refNo"));
				dto.setStepNo(rs.getInt("stepNo"));
				dto.setLevelNo(rs.getInt("levelNo"));
				dto.setParentNo(rs.getInt("parentNo"));
				dto.setHit(rs.getInt("hit"));
				dto.setIp(rs.getString("ip"));
				dto.setMemberNo(rs.getInt("memberNo"));
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setSecretGubun(rs.getString("secretGubun"));
				dto.setRegiDate(rs.getDate("regiDate"));
				
				dto.setChild_counter(rs.getInt("child_counter"));
				
				dto.setPreNo(rs.getInt("preNo"));
				dto.setPreSubject(rs.getString("preSubject"));
				dto.setNxtNo(rs.getInt("nxtNo"));
				dto.setNxtSubject(rs.getString("nxtSubject"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return dto;
	}

	public void setUpdateReLevel(BoardDTO dto) {
		conn = getConn();
		try {
			String sql = "UPDATE " + BOARD + " SET levelNo = (levelNo + 1) WHERE refNo = ? AND levelNo > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRefNo());
			pstmt.setInt(2, dto.getLevelNo());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
	}

	public int setUpdate(BoardDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "UPDATE " + BOARD + " SET "
					+ "writer = ?, "
					+ "email = ?, "
					+ "subject = ?, "
					+ "content = ?, "
					+ "memberNo = ?, "
					+ "noticeNo = ?, "
					+ "secretGubun = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getMemberNo());
			pstmt.setInt(6, dto.getNoticeNo());
			pstmt.setString(7, dto.getSecretGubun());
			pstmt.setInt(8, dto.getNo());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return result;
	}

	public void setNoticeNoLargerThenCurrentNoticeNo(int no) {
		conn = getConn();
		try {
			String sql = "UPDATE " + BOARD + " SET "
					+ "noticeNo = (noticeNo - 1) "
					+ "WHERE noticeNo > "
					+ "(SELECT noticeNo FROM board WHERE no = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
	}

	public int setDelete(BoardDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM board WHERE no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int setInsertComment(CommentDTO commentDto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + BOARD_COMMENT + " VALUES (seq_board_comment.NEXTVAL, "
					+ "?, ?, ?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentDto.getBoard_no());
			pstmt.setString(2, commentDto.getWriter());
			pstmt.setString(3, commentDto.getContent());
			pstmt.setString(4, commentDto.getPasswd());
			pstmt.setInt(5, commentDto.getMemberNo());
			pstmt.setString(6, commentDto.getIp());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getAllCommentRowsCount(int no) {
		conn = getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM " + BOARD_COMMENT + " WHERE board_no = ? ORDER BY comment_no DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				allRowsCount = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return allRowsCount;
	}

	public ArrayList<CommentDTO> getCommentPagingList(int no, int startNum, int endNum) {
		conn = getConn();
		ArrayList<CommentDTO> list = new ArrayList<>();
		try {
			String basic_sql = "";
			basic_sql += "SELECT * FROM " + BOARD_COMMENT + " WHERE board_no = ?";
			basic_sql += " ORDER BY comment_no DESC";
			
			String sql = "";
			sql += "SELECT * FROM ";
			sql += "(SELECT ROWNUM Rnum, a.* FROM ";
			sql += "(" + basic_sql + ") a) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setComment_no(rs.getInt("comment_no"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setMemberNo(rs.getInt("memberNo"));
				dto.setIp(rs.getString("ip"));
				dto.setRegiDate(rs.getDate("regiDate"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	
}
