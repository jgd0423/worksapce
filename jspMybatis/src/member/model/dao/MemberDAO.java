package member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;

public class MemberDAO {
	// Field
	final String MEMBER = "member";
	
	// Method
	public int setInsert(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("member.setInsert", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public MemberDTO getSelectOne(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO result = session.selectOne("member.getSelectOne", map);
		session.close();
		return result;
	}
	
	public int setUpdate(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("member.setUpdate", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int setDelete(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("member.setDelete", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public MemberDTO getLogin(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO result = session.selectOne("member.getLogin", map);
		session.close();
		return result;
	}

	public int getIdCheck(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getIdCheck", map);   // namespace.id, 변수가 담긴 map
		session.close();
		return result;
	}
	
	public int getAllRowsCount(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getAllRowsCount", map);
		session.close();
		return result;
	}

	public List<MemberDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list = session.selectList("member.getPagingList", map);
		session.close();
		return list;
	}

	public String getIdCheckWin(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		String result = session.selectOne("member.getIdCheckWin", map);
		session.close();
		return result;
	}
	
	public Map<String, Object> getTotalTablesCount() {
		Map<String, Object> map = new HashMap<>();
		
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> totalTablesCountMap = session.selectOne("member.getTotalTablesCount", map);
		session.close();
		return totalTablesCountMap;
	}
}
