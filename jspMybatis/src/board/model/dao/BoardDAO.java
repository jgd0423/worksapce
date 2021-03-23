package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.model.dto.BoardDTO;
import board.model.dto.CommentDTO;
import db.DbExample;
import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;

public class BoardDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String BOARD = "board";
	final String BOARD_COMMENT = "board_comment";
	
	// Method
	public Connection getConn(String methodName) {
		conn = DbExample.getConn();
		System.out.println("methodName: " + methodName);
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}
	
	public int setInsert(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("board.setInsert", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int getMaxNum(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxNum", map);
		session.close();
		return result;
	}
	
	public int getMaxRefNo(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxRefNo", map);
		session.close();
		return result;
	}

	public int getMaxNoticeNo(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxNoticeNo", map);
		session.close();
		return result;
	}

	public int getAllRowsCount(String tbl, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getAllRowsCount", map);
		session.close();
		return result;
	}
	
	public List<BoardDTO> getPagingList(int startNum, int endNum, String tbl, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("tbl", tbl);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<BoardDTO> list = session.selectList("board.getPagingList", map);
		session.close();
		return list;
	}

	public void setUpdateHit(int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdateHit", map);
		session.commit();
		session.close();
	}

	public BoardDTO getView(int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		BoardDTO result = session.selectOne("board.getView", map);
		session.close();
		return result;
	}

	public void setUpdateReLevel(BoardDTO dto) {
		conn = getConn("setUpdateReLevel");
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
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdate", map);
		session.commit();
		session.close();
		
		return result;
	}

	public void setNoticeNoLargerThenCurrentNoticeNo(int no, String tbl) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tbl", tbl);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setNoticeNoLargerThenCurrentNoticeNo", map);
		session.commit();
		session.close();
	}

	public int setDelete(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("board.setDelete", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int setInsertComment(CommentDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("board.setInsertComment", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int getAllCommentRowsCount(int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getAllCommentRowsCount", map);
		session.close();
		return result;
	}

	public List<CommentDTO> getCommentPagingList(int startNum, int endNum, int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CommentDTO> list = session.selectList("board.getCommentPagingList", map);
		session.close();
		return list;
	}

	public Map<String, Object> isUsingTable(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> tblStatusMap = session.selectOne("board.isUsingTable", map);
		
		if (tblStatusMap == null) {
			tblStatusMap = new HashMap<>();
			tblStatusMap.put("SERVICEGUBUN", "F");
			tblStatusMap.put("TBLNAME", "none");
		}
		
		session.close();
		return tblStatusMap;
	}

	public int setUpdateComment(CommentDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdateComment", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int setDeleteComment(int comment_no, String passwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("comment_no", comment_no);
		map.put("passwd", passwd);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("board.setDeleteComment", map);
		session.commit();
		session.close();
		
		return result;
	}
}
