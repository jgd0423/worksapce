package com.test.springStudy.guestbook.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.springStudy.guestbook.model.dto.GuestbookDTO;

@Repository
public class GuestbookDAOImpl implements GuestbookDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public int setInsert(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.insert("guestbook.setInsert", map);		
		return result;
	}

	@Override
	public int getAllRowsCount(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		int result = sqlSession.selectOne("guestbook.getAllRowsCount", map);
		return result;
	}

	@Override
	public List<GuestbookDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		List<GuestbookDTO> list = sqlSession.selectList("guestbook.getPagingList", map);
		return list;
	}

	@Override
	public GuestbookDTO getView(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		
		GuestbookDTO result = sqlSession.selectOne("guestbook.getView", map);
		return result;
	}

	@Override
	public int setUpdate(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.update("guestbook.setUpdate", map);
		return result;
	}

	@Override
	public int setDelete(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.delete("guestbook.setDelete", map);
		return result;
	}

}
