package jspInterfaceTest.model.member;

import java.util.ArrayList;

public interface MemberDAO {
	public void getConn();
	public int setInsert(MemberDTO dto);
	public ArrayList<MemberDTO> getAllMembers();
	public MemberDTO getOneMember(String id);
	public int setUpdate(MemberDTO dto);
	public int setDelete(MemberDTO dto);
}
