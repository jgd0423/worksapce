package com.test.springStudy.member.model.dao;

import java.util.List;
import java.util.Map;

import com.test.springStudy.member.model.dto.MemberDTO;

public interface MemberDAO {
	final String MEMBER = "member";
	public int setInsert(MemberDTO dto);
	public int setUpdate(MemberDTO dto);
	public int setDelete(MemberDTO dto);
	public MemberDTO getLogin(MemberDTO dto);
	public int getIdCheck(String id);
	public int getAllRowsCount(String search_option, String search_data);
	public List<MemberDTO> getPagingList(int startNum, int endNum, String search_option, String search_data);
	public String getIdCheckWin(String id);
	public Map<String, Object> getTotalTablesCount();
	public MemberDTO getSelectOne(int no, String search_option, String search_data);
}
