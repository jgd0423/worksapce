package shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.model.dto.ProductDTO;
import sqlmap.MybatisManager;

public class ProductDAO {
	// Method
	public int setInsert(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("product.setInsert", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int getAllRowsCount(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("product.getAllRowsCount", map);
		session.close();
		return result;
	}

	public List<ProductDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<ProductDTO> list = session.selectList("product.getPagingList", map);
		session.close();
		return list;
	}
	
	public ProductDTO getView(int no) {
		Map<String, Integer> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		ProductDTO result = session.selectOne("product.getView", map);
		session.close();
		return result;
	}

	public int setUpdate(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("product.setUpdate", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int setDelete(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("product.setDelete", map);
		session.commit();
		session.close();
		
		return result;
	}
}
