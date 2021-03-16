package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import member.model.dto.MemberDTO;

public class _MemberDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}
	
	public int setInsert(MemberDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO member VALUES (seq_member.NEXTVAL, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setInt(5, dto.getBornYear());
			pstmt.setString(6, dto.getPostcode());
			pstmt.setString(7, dto.getAddress());
			pstmt.setString(8, dto.getDetailAddress());
			pstmt.setString(9, dto.getExtraAddress());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public ArrayList<MemberDTO> getSelectAll() {
		conn = getConn();
		ArrayList<MemberDTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM member ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return arrayList;
	}
	
	public MemberDTO getSelectOne(int no) {
		conn = getConn();
		MemberDTO dto = new MemberDTO();
		try {
			String sql = "SELECT * FROM member WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public int setUpdate(MemberDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "UPDATE member SET "
					+ "bornYear = ?, "
					+ "postcode = ?, "
					+ "address = ?, "
					+ "detailAddress = ?,"
					+ "extraAddress = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBornYear());
			pstmt.setString(2, dto.getPostcode());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getDetailAddress());
			pstmt.setString(5, dto.getExtraAddress());
			pstmt.setInt(6, dto.getNo());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return result;
	}
	
	public int setDelete(MemberDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM member WHERE no = ? ";
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
	
	public MemberDTO getLogin(MemberDTO dto) {
		conn = getConn();
		MemberDTO resultDto = new MemberDTO();
		try {
			String sql = "SELECT * FROM member WHERE id = ? AND passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultDto.setNo(rs.getInt("no"));
				resultDto.setId(rs.getString("id"));
				resultDto.setPasswd(rs.getString("passwd"));
				resultDto.setName(rs.getString("name"));
				resultDto.setGender(rs.getString("gender"));
				resultDto.setBornYear(rs.getInt("bornYear"));
				resultDto.setRegiDate(rs.getTimestamp("regiDate"));
			}
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return resultDto;
	}

	public int getIdCheck(String id) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
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
			String sql = "SELECT COUNT(*) FROM member WHERE no > 0";
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("id_name_gender")) {
					sql += " and (id LIKE ? OR name LIKE ? OR gender LIKE ?) ";
				}
			}
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("id_name_gender")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
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

	public ArrayList<MemberDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		conn = getConn();
		ArrayList<MemberDTO> list = new ArrayList<>();
		try {
			String basic_sql = ""; 
			basic_sql += "SELECT * FROM member WHERE no > 0";	
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					basic_sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("id_name_gender")) {
					basic_sql += " and (id LIKE ? OR name LIKE ? OR gender LIKE ?) ";
				}
			}
			
			basic_sql += " ORDER BY no DESC";
			
			String sql = "";
			sql += "SELECT * FROM ";
			sql += "(SELECT ROWNUM Rnum, a.* FROM ";
			sql += "(" + basic_sql + ") a) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("id_name_gender")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				}
			}
			
			pstmt.setInt(++pstmtNum, startNum);
			pstmt.setInt(++pstmtNum, endNum);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return list;
	}

	public String getIdCheckWin(String id) {
		String result = "";
		conn = getConn();
		try {
			String sql = "SELECT id FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
