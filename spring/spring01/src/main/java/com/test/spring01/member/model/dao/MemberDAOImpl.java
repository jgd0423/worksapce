package com.test.spring01.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.spring01.member.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public List<MemberDTO> getList() {
		return sqlSession.selectList("member.getList");
	}

	@Override
	public MemberDTO getView(String id) {
		return sqlSession.selectOne("member.getView", id);
	}

	@Override
	public void setInsert(MemberDTO dto) {
		sqlSession.insert("member.setInsert", dto);
	}

	@Override
	public void setUpdate(MemberDTO dto) {
		sqlSession.update("member.setUpdate", dto);
	}

	@Override
	public void setDelete(String id) {
		sqlSession.delete("member.setDelete", id);
	}

	@Override
	public int checkPasswd(String id, String passwd) {
		int result = 0;
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("passwd", passwd);
		result = sqlSession.selectOne("member.checkPasswd", map);
		return result;
	}

}
