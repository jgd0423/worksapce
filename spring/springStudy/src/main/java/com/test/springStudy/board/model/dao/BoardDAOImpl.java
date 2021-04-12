package com.test.springStudy.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.springStudy.board.model.dto.BoardDTO;
import com.test.springStudy.board.model.dto.CommentDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public int setInsert(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.insert("board.setInsert", map);
		return result;
	}

	@Override
	public int getMaxNum(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);

		int result = sqlSession.selectOne("board.getMaxNum", map);
		return result;
	}

	@Override
	public int getMaxRefNo(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		
		int result = sqlSession.selectOne("board.getMaxRefNo", map);
		return result;
	}

	@Override
	public int getMaxNoticeNo(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		
		int result = sqlSession.selectOne("board.getMaxNoticeNo", map);
		return result;
	}

	@Override
	public int getAllRowsCount(String tbl, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		int result = sqlSession.selectOne("board.getAllRowsCount", map);
		return result;
	}

	@Override
	public List<BoardDTO> getPagingList(int startNum, int endNum, String tbl, String search_option,
			String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("tbl", tbl);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		List<BoardDTO> list = sqlSession.selectList("board.getPagingList", map);
		return list;
	}

	@Override
	public void setUpdateHit(int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("no", no);
		
		int result = sqlSession.update("board.setUpdateHit", map);
	}

	@Override
	public BoardDTO getView(int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("no", no);
		
		BoardDTO result = sqlSession.selectOne("board.getView", map);
		return result;
	}

	@Override
	public void setUpdateReLevel(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.update("board.setUpdateReLevel", map);
	}

	@Override
	public int setUpdate(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.update("board.setUpdate", map);
		return result;
	}

	@Override
	public void setNoticeNoLargerThenCurrentNoticeNo(int no, String tbl) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tbl", tbl);
		
		int result = sqlSession.update("board.setNoticeNoLargerThenCurrentNoticeNo", map);
	}

	@Override
	public int setDelete(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.delete("board.setDelete", map);
		return result;
	}

	@Override
	public int setInsertComment(CommentDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.insert("board.setInsertComment", map);
		return result;
	}

	@Override
	public int getAllCommentRowsCount(int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("no", no);
		
		int result = sqlSession.selectOne("board.getAllCommentRowsCount", map);
		return result;
	}

	@Override
	public List<CommentDTO> getCommentPagingList(int startNum, int endNum, int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("no", no);
		
		List<CommentDTO> list = sqlSession.selectList("board.getCommentPagingList", map);
		return list;
	}

	@Override
	public Map<String, Object> isUsingTable(String tbl) {
		Map<String, String> map = new HashMap<>();
		map.put("tbl", tbl);
		System.out.println("before");
		Map<String, Object> tblStatusMap = sqlSession.selectOne("board.isUsingTable", map);
		System.out.println("after");
		
		if (tblStatusMap == null) {
			tblStatusMap = new HashMap<>();
			tblStatusMap.put("SERVICEGUBUN", "F");
			tblStatusMap.put("TBLNAME", "none");
		}
		
		return tblStatusMap;
	}

	@Override
	public int setUpdateComment(CommentDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.update("board.setUpdateComment", map);
		return result;
	}

	@Override
	public int setDeleteComment(int comment_no, String passwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("comment_no", comment_no);
		map.put("passwd", passwd);
		
		int result = sqlSession.delete("board.setDeleteComment", map);
		return result;
	}

}
