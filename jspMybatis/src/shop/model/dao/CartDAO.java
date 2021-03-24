package shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.model.dto.CartDTO;
import sqlmap.MybatisManager;

public class CartDAO {
	// Method
	public int getAllRowsCount() {
		Map<String, String> map = new HashMap<>();
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("mall.getAllRowsCount", map);
		session.close();
		return result;
	}

	public List<CartDTO> getPagingList(int startNum, int endNum) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("mall.getPagingList", map);
		session.close();
		return list;
	}

	public int setInsert(CartDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("mall.setInsert", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int setDeleteBatch(String[] chkNoArray) {
		Map<String, Object> map = new HashMap<>();
		map.put("array", chkNoArray);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("mall.setDeleteBatch", map);
		session.commit();
		session.close();
		return result;
		
//		Map<String, Object> map = new HashMap<>();
//		map.put("array", chkNoArray);
//		
//		SqlSession session = MybatisManager.getInstance().openSession();
//		int result = session.delete("cart.setDeleteBatch", map);
//		session.commit();
//		session.close();
	}
}
