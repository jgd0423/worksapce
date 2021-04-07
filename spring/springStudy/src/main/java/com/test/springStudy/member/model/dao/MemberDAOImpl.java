package com.test.springStudy.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.springStudy.member.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public int setInsert(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		int result = sqlSession.insert("member.setInsert", map);
		return result;
	}

	@Override
	public MemberDTO getSelectOne(int no, String search_option, String search_data) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("MEMBER", MEMBER);
		
		MemberDTO result = sqlSession.selectOne("member.getSelectOne", map);
		return result;
	}

	@Override
	public int setUpdate(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		int result = sqlSession.update("member.setUpdate", map);
		return result;
	}

	@Override
	public int setDelete(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		int result = sqlSession.delete("member.setDelete", map);
		return result;
	}

	@Override
	public MemberDTO getLogin(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		MemberDTO result = sqlSession.selectOne("member.getLogin", map);
		return result;
	}

	@Override
	public int getIdCheck(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("MEMBER", MEMBER);
		
		int result = sqlSession.selectOne("member.getIdCheck", map);
		return result;
	}

	@Override
	public int getAllRowsCount(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("MEMBER", MEMBER);
		
		int result = sqlSession.selectOne("member.getAllRowsCount", map);
		return result;
	}

	@Override
	public List<MemberDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("MEMBER", MEMBER);
		
		List<MemberDTO> list = sqlSession.selectList("member.getPagingList", map);
		return list;
	}

	@Override
	public String getIdCheckWin(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("MEMBER", MEMBER);
		
		String result = sqlSession.selectOne("member.getIdCheckWin", map);
		return result;
	}

	@Override
	public Map<String, Object> getTotalTablesCount() {
		Map<String, Object> map = new HashMap<>();
		
		Map<String, Object> totalTablesCountMap = sqlSession.selectOne("member.getTotalTablesCount", map);
		return totalTablesCountMap;
	}

}
