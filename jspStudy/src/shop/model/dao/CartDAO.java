package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import shop.model.dto.CartDTO;
import shop.model.dto.ProductDTO;

public class CartDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String CART = "cart";
	final String PRODUCT = "product";
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}

	public int getAllRowsCount() {
		conn = getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM " + CART + " LEFT OUTER JOIN " + PRODUCT + " ON cart.productNo = product.no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				allRowsCount = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return allRowsCount;
	}

	public ArrayList<CartDTO> getPagingList(int startNum, int endNum) {
		conn = getConn();
		ArrayList<CartDTO> list = new ArrayList<>();
		try {
			String basic_sql = "";
			basic_sql += "SELECT cart.no, product.product_img, "
					+ "product.name, product.price, cart.amount, "
					+ "(product.price * cart.amount) buy_money, cart.regi_date "
					+ "FROM " + CART + " LEFT OUTER JOIN " + PRODUCT + " ON cart.productNo = product.no ";
			basic_sql += "ORDER BY no DESC";
			
			String sql = "";
			sql += "SELECT * FROM (SELECT A.*, Rownum Rnum FROM (" + basic_sql + ") A) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setNo(rs.getInt("no"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_name(rs.getString("name"));
				dto.setProduct_price(rs.getInt("price"));
				dto.setAmount(rs.getInt("amount"));
				dto.setBuy_money(rs.getInt("buy_money"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return list;
	}

	public int setInsert(CartDTO cartDto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + CART + " VALUES (seq_cart.NEXTVAL, "
					+ "?, ?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartDto.getMemberNo());
			pstmt.setInt(2, cartDto.getProductNo());
			pstmt.setInt(3, cartDto.getAmount());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
}
