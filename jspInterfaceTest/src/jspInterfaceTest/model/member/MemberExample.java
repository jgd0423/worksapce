package jspInterfaceTest.model.member;

import java.util.ArrayList;

public class MemberExample {
	MemberDAO dao = new MemberDAOImplOracle();
	//MemberDAO dao = new MemberDAOImplMySQL(); 
	
	public void getConn() {
		dao.getConn();
	}
	
	public int setInsert(MemberDTO dto) {
		return dao.setInsert(dto);
	}
	
	public ArrayList<MemberDTO> getAllMembers() {
		return dao.getAllMembers();
	}
	
	public MemberDTO getOneMember(String id) {
		return dao.getOneMember(id);
	}
	
	public int setUpdate(MemberDTO dto) {
		return dao.setUpdate(dto);
	}
	
	public int setDelete(MemberDTO dto) {
		return dao.setDelete(dto);
	}
}
