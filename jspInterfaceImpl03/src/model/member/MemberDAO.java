package model.member;

import java.util.ArrayList;

public interface MemberDAO {
	public void getConn();
	public int setInsert(MemberDTO dto);
	public ArrayList<MemberDTO> getListAll();
	public MemberDTO getSelectOne(String id);
	public int setUpdate(MemberDTO dto);
	public int setDelete(MemberDTO dto);
}
