package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import db.DbExample;
import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;

public class MemberDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	final String MEMBER = "member";
	
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
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getIdCheck", map);   // namespace.id, 변수가 담긴 map
		session.close();
		return result;
	}
	
	public int getAllRowsCount(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getAllRowsCount", map);
		session.close();
		return result;
	}

	public List<MemberDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list = session.selectList("member.getPagingList", map);
		session.close();
		return list;
	}

	public String getIdCheckWin(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("MEMBER", MEMBER);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		String result = session.selectOne("member.getIdCheckWin", map);
		session.close();
		return result;
	}
}