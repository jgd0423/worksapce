package com.test.spring01.exam.model.dao;

import java.util.List;

import com.test.spring01.exam.model.dto.ExamDTO;

public interface ExamDAO {
	public List<ExamDTO> getList();
	public ExamDTO getView(int no);
	public void setInsert(ExamDTO dto);
	public void setUpdate(ExamDTO dto);
	public void setDelete(int no);
}
