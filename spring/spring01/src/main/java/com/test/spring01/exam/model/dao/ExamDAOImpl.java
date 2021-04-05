package com.test.spring01.exam.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.spring01.exam.model.dto.ExamDTO;

@Repository
public class ExamDAOImpl implements ExamDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public List<ExamDTO> getList() {
		return sqlSession.selectList("exam.getList");
	}

	@Override
	public void setInsert(ExamDTO dto) {
		sqlSession.insert("exam.setInsert", dto);
	}

	@Override
	public void setUpdate(ExamDTO dto) {
		sqlSession.update("exam.setUpdate", dto);
	}

	@Override
	public void setDelete(int no) {
		sqlSession.delete("exam.setDelete", no);
	}

	@Override
	public ExamDTO getView(int no) {
		return sqlSession.selectOne("exam.getView", no);
	}

}
