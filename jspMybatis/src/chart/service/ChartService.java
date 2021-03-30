package chart.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.dao.MemberDAO;
import oracle.net.aso.k;
import shop.model.dao.CartDAO;
import shop.model.dto.CartDTO;

public class ChartService {
	public JSONObject getTotalTablesCountData() {
		MemberDAO dao = new MemberDAO();
		Map<String, Object> totalTablesCountMap = dao.getTotalTablesCount();
		JSONObject data = new JSONObject();
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray();
		
		col1.put("label", "tableName");
		col1.put("type", "string");
		col2.put("label", "record count");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		data.put("cols", title);   // 칼럼에 객체 추가
		
		String[] keys = { "MEMBER", "MEMO", "GUESTBOOK", "SURVEY", "FREEBOARD", "CODINGBOARD", "PRODUCT", "CART", "NONMEMBERCART" };
	
		
		JSONArray body = new JSONArray();   // 실제 데이터
		for (String key : keys) {
			JSONObject tableName = new JSONObject();
			tableName.put("v", key);
			JSONObject totalCount = new JSONObject();
			totalCount.put("v", ((BigDecimal) totalTablesCountMap.get(key)).intValue());
			JSONArray row = new JSONArray();
			row.add(tableName);
			row.add(totalCount);
			JSONObject cell = new JSONObject();
			cell.put("c", row);   // 셀 추가
			body.add(cell);   // row(레코드) 1개 추가
		}
		data.put("rows", body);
		return data;
	}
	
	
	public JSONObject getChartData() {
		CartDAO cartDao = new CartDAO();
		List<CartDTO> items = cartDao.getListCartProductGroup();
		JSONObject data = new JSONObject();
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray();
		
		col1.put("label", "product");
		col1.put("type", "string");
		col2.put("label", "price");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		data.put("cols", title);   // 칼럼에 객체 추가
		
		JSONArray body = new JSONArray();   // 실제 데이터
		for (CartDTO dto : items) {
			JSONObject name = new JSONObject();
			name.put("v", dto.getProduct_name());
			JSONObject money = new JSONObject();
			money.put("v", dto.getBuy_money());
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell = new JSONObject();
			cell.put("c", row);   // 셀 추가
			body.add(cell);   // row(레코드) 1개 추가
		}
		data.put("rows", body);
		return data;
		
	}
}
