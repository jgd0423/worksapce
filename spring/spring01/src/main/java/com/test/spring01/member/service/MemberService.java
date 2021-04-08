package com.test.spring01.member.service;

import java.util.List;

import com.test.spring01.member.model.dto.MemberDTO;

public interface MemberService {
	public List<MemberDTO> getList();
	public MemberDTO getView(String id);
	public void setInsert(MemberDTO dto);
	public void setUpdate(MemberDTO dto);
	public void setDelete(String id);
	public int checkPasswd(String id, String passwd);
}
