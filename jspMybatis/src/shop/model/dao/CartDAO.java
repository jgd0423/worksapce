package shop.model.dao;

import java.util.ArrayList;
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
	
	public List<CartDTO> getListCartProductGroup() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("mall.getListCartProductGroup");
		session.close();
		return list;
		
//		List<CartDTO> list = new ArrayList<>();
		
		
//		conn = getConn();
//		try {
//			String sql = "";
//			sql += "SELECT p.name product_name, SUM(c.amount * p.price) buy_money ";
//			sql += "FROM cart c INNER JOIN product p ON c.productNo = p.no ";
//			sql += "GROUP BY p.name ";
//			sql += "ORDER BY product_name ASC";
//			
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				CartDTO dto = new CartDTO();
//				dto.setProduct_name(rs.getString("product_name"));
//				dto.setBuy_money(rs.getInt("buy_money"));
//				list.add(dto);
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			getConnClose(rs, pstmt, conn);
//		}
//		return list;
	}
}
