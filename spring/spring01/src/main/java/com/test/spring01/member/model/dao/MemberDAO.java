package com.test.spring01.member.model.dao;

import java.util.List;

import com.test.spring01.member.model.dto.MemberDTO;

public interface MemberDAO {
	public List<MemberDTO> getList();
	public MemberDTO getView(String id);
	public void setInsert(MemberDTO dto);
	public void setUpdate(MemberDTO dto);
	public void setDelete(MemberDTO dto);
}
