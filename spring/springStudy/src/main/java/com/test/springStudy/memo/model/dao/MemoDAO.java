package com.test.springStudy.memo.model.dao;

import java.util.List;

import com.test.springStudy.memo.model.dto.MemoDTO;

public interface MemoDAO {
	public int setInsert(MemoDTO dto);
	public List<MemoDTO> getSelectAll();
	public int setDelete(MemoDTO dto);
	public int getAllRowsCount();
	public List<MemoDTO> getPagingList(int startNum, int endNum);
	public MemoDTO getSelectOne(int no);
	public int setUpdate(MemoDTO dto);
}
