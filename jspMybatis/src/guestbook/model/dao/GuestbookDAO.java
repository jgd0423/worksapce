package guestbook.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import guestbook.model.dto.GuestbookDTO;
import sqlmap.MybatisManager;

public class GuestbookDAO {
	public int setInsert(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("guestbook.setInsert", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int getAllRowsCount(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("guestbook.getAllRowsCount", map);
		session.close();
		return result;
	}
	

	public List<GuestbookDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<GuestbookDTO> list = session.selectList("guestbook.getPagingList", map);
		session.close();
		return list;
	}

	public GuestbookDTO getView(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		GuestbookDTO result = session.selectOne("guestbook.getView", map);
		session.close();
		return result;
	}

	public int setUpdate(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("guestbook.setUpdate", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int setDelete(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("guestbook.setDelete", map);
		session.commit();
		session.close();
		
		return result;
	}
}
