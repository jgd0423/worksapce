package com.test.springStudy.memo.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.springStudy.memo.model.dto.MemoDTO;

@Repository
public class MemoDAOImpl implements MemoDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public int setInsert(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.insert("memo.setInsert", map);
		return result;
	}

	@Override
	public List<MemoDTO> getSelectAll() {
		List<MemoDTO> list = sqlSession.selectList("memo.getSelectAll");
		return list;
	}

	@Override
	public int setDelete(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.delete("memo.setDelete", map);
		return result;
	}

	@Override
	public int getAllRowsCount() {	
		int result = sqlSession.selectOne("memo.getAllRowsCount");
		return result;
	}

	@Override
	public List<MemoDTO> getPagingList(int startNum, int endNum) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		
		List<MemoDTO> list = sqlSession.selectList("memo.getPagingList", map);
		return list;
	}

	@Override
	public MemoDTO getSelectOne(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);

		MemoDTO result = sqlSession.selectOne("memo.getSelectOne", map);
		return result;
	}

	@Override
	public int setUpdate(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.update("memo.setUpdate", map);
		return result;
	}

}
