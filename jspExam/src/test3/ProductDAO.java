package test3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;

public class ProductDAO {
	// Field
	Connection conn = Db.getConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public ProductDAO() {}
	
	// Method		
	public void getConnClose() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int setInsert(ProductDTO dto) {
		int result = 0;
		try {
			String sql = "insert into product values " + 
					"(seq_product.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setDouble(3, getSaledPrice(dto));
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	private double getSaledPrice(ProductDTO dto) {
		double saledPrice = 0;
		int dtoPrice = dto.getPrice();
		int[] salingPrice = { 10000, 20000, 30000 };
		for (int i = 0; i < salingPrice.length; i++) {
			if (salingPrice[i] == dtoPrice) {
				saledPrice = dtoPrice - (dtoPrice * 0.1);
			} else {
				saledPrice = dtoPrice;
			}
		}
		
		return saledPrice;
	}
	
	public ArrayList<ProductDTO> getListAll() {
		ArrayList<ProductDTO> productsList = new ArrayList<>();
		try {
			String sql = "select * from product order by no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSaledPrice(rs.getDouble("saledPrice"));

				productsList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return productsList;
	}
}
