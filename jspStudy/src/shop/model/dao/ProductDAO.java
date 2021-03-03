package shop.model.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.survey.dto.SurveyDTO;
import shop.model.dto.ProductDTO;

public class ProductDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String PRODUCT = "product";
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}

	public int setInsert(ProductDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + PRODUCT + " VALUES (seq_product.NEXTVAL, "
					+ "?, ?, ?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getDescription());
			pstmt.setString(4, dto.getProduct_img());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getAllRowsCount(String search_option, String search_data) {
		conn = getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM " + PRODUCT + " WHERE no > 0 ";
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("name_description")) {
					sql += " and (name LIKE ? OR description LIKE ?) ";
				}
			}
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("name_description")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				}
			}
			
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

	public ArrayList<ProductDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		conn = getConn();
		ArrayList<ProductDTO> list = new ArrayList<>();
		try {
			String basic_sql = "";
			basic_sql += "SELECT * FROM " + PRODUCT + " WHERE no > 0 ";
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					basic_sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("name_description")) {
					basic_sql += " and (name LIKE ? OR description LIKE ?) ";
				}
			}
			
			basic_sql += "ORDER BY no DESC";
			
			String sql = "";
			sql += "SELECT * FROM (SELECT A.*, Rownum Rnum FROM (" + basic_sql + ") A) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("name_description")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				}
			}
			
			pstmt.setInt(++pstmtNum, startNum);
			pstmt.setInt(++pstmtNum, endNum);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setProduct_img(rs.getString("product_img"));
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
	
	public ProductDTO getView(int no) {
		conn = getConn();
		ProductDTO dto = new ProductDTO();
		try {
			String sql = "SELECT * FROM "+ PRODUCT +" WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int setUpdate(ProductDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "UPDATE " + PRODUCT + " SET "
					+ "name = ?, "
					+ "price = ?, "
					+ "description = ?, "
					+ "product_img = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getDescription());
			pstmt.setString(4, dto.getProduct_img());
			pstmt.setInt(5, dto.getNo());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return result;
	}

	public int setDelete(ProductDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM " + PRODUCT + " WHERE no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
}
