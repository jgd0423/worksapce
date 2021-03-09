package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			basic_sql += "SELECT cart.no cart_no, product.no productNo, product.product_img, "
					+ "product.name, product.price, cart.amount, "
					+ "(product.price * cart.amount) buy_money, cart.regi_date "
					+ "FROM " + CART + " LEFT OUTER JOIN " + PRODUCT + " ON cart.productNo = product.no ";
			basic_sql += "ORDER BY cart_no DESC";
			
			String sql = "";
			sql += "SELECT * FROM (SELECT A.*, Rownum Rnum FROM (" + basic_sql + ") A) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setNo(rs.getInt("cart_no"));
				dto.setProductNo(rs.getInt("productNo"));
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

	public boolean setDeleteBatch(String[] chkNoArray) {
		int[] count = new int[chkNoArray.length];
		conn = getConn();
		try {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM " + CART + " WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < chkNoArray.length; i++) {
				if (chkNoArray[i].equals("on")) {
					continue;
				}
				pstmt.setInt(1, Integer.parseInt(chkNoArray[i]));
				pstmt.addBatch();
			}
			count = pstmt.executeBatch();
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			getConnClose(rs, pstmt, conn);
		}
		
		boolean result = true;
		
		// 리턴값 -2는 성공은 했지만, 변경된 row의 개수를 알 수 없을 때 리턴되는 값이다.
		for (int i = 0; i < count.length; i++) {
			System.out.println(i + ". " + count[i]);
			if (count[i] != -2) {
				result = false;
				break;
			}
		}

		return false;
	}

	public ArrayList<CartDTO> getListCartProductGroup() {
		ArrayList<CartDTO> list = new ArrayList<>();
		conn = getConn();
		try {
			String sql = "";
			sql += "SELECT p.name product_name, SUM(c.amount * p.price) buy_money ";
			sql += "FROM cart c INNER JOIN product p ON c.productNo = p.no ";
			sql += "GROUP BY p.name ";
			sql += "ORDER BY product_name ASC";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setProduct_name(rs.getString("product_name"));
				dto.setBuy_money(rs.getInt("buy_money"));
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return list;
	}
}
