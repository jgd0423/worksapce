package jspInterfaceImplExam.model.resume;

import java.util.ArrayList;

public class ResumeExample {
	ResumeDAO dao = new ResumeDAOImplOracle();
	//ResumeDAO dao = new ResumeDAOImplMySQL(); 
	
	public void getConn() {
		dao.getConn();
	}
	
	public int setInsert(ResumeDTO dto) {
		return dao.setInsert(dto);
	}
	
	public ArrayList<ResumeDTO> getListAll() {
		return dao.getListAll();
	}
	
	public ResumeDTO getSelectOne(int no) {
		return dao.getSelectOne(no);
	}
	
	public int setUpdate(ResumeDTO dto) {
		return dao.setUpdate(dto);
	}
	
	public int setDelete(ResumeDTO dto) {
		return dao.setDelete(dto);
	}
}
