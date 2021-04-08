package com.test.spring01.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.spring01.member.model.dao.MemberDAO;
import com.test.spring01.member.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO memberDao;
	
	@Override
	public List<MemberDTO> getList() {
		return memberDao.getList();
	}

	@Override
	public MemberDTO getView(String id) {
		return memberDao.getView(id);
	}

	@Override
	public void setInsert(MemberDTO dto) {
		memberDao.setInsert(dto);
	}

	@Override
	public void setUpdate(MemberDTO dto) {
		memberDao.setUpdate(dto);
		
	}

	@Override
	public void setDelete(String id) {
		memberDao.setDelete(id);
		
	}

	@Override
	public int checkPasswd(String id, String passwd) {
		return memberDao.checkPasswd(id, passwd);
	}

}
