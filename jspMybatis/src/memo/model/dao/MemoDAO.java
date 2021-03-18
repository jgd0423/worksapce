package memo.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.model.dto.MemoDTO;
import sqlmap.MybatisManager;

public class MemoDAO {
	// Method
	public int setInsert(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("memo.setInsert", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public List<MemoDTO> getSelectAll() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = session.selectList("memo.getSelectAll");
		session.close();
		return list;
	}
	
	public int setDelete(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("memo.setDelete", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int getAllRowsCount() {
		Map<String, String> map = new HashMap<>();
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("memo.getAllRowsCount", map);
		session.close();
		return result;
	}

	public List<MemoDTO> getPagingList(int startNum, int endNum) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = session.selectList("memo.getPagingList", map);
		session.close();
		return list;
	}

	public MemoDTO getSelectOne(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		MemoDTO result = session.selectOne("memo.getSelectOne", map);
		session.close();
		return result;
	}

	public int setUpdate(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("memo.setUpdate", map);
		session.commit();
		session.close();
		
		return result;
	}
}
