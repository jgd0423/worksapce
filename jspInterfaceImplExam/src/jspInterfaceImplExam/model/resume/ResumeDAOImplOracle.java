package jspInterfaceImplExam.model.resume;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ResumeDAOImplOracle implements ResumeDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Method
	@Override
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "jspInterfaceImplExam";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- 오라클 접속 성공 --");					
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		}
	}
	
	public void getConnClose() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int setInsert(ResumeDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into resume values ("
					+ "seq_resume.nextval, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?,"
					+ "?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPic());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getAddress());
			pstmt.setInt(6, dto.getToeic());
			pstmt.setInt(7, dto.getToefl());
			pstmt.setInt(8, dto.getJapan());
			pstmt.setInt(9, dto.getChina());
			pstmt.setString(10, dto.getGigan1());
			pstmt.setString(11, dto.getSchool1());
			pstmt.setString(12, dto.getJeongong1());
			pstmt.setString(13, dto.getGigan2());
			pstmt.setString(14, dto.getSchool2());
			pstmt.setString(15, dto.getJeongong2());
			pstmt.setString(16, dto.getGigan3());
			pstmt.setString(17, dto.getSchool3());
			pstmt.setString(18, dto.getJeongong3());
			pstmt.setString(19, dto.getGigan4());
			pstmt.setString(20, dto.getSchool4());
			pstmt.setString(21, dto.getJeongong4());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}

	@Override
	public ArrayList<ResumeDTO> getListAll() {
		getConn();
		ArrayList<ResumeDTO> resumeList = new ArrayList<>();
		try {
			String sql = "select * from resume order by no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ResumeDTO dto = new ResumeDTO();
				dto.setNo(rs.getInt("no"));
				dto.setPic(rs.getString("pic"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
				dto.setToeic(rs.getInt("toeic"));
				dto.setToefl(rs.getInt("toefl"));
				dto.setJapan(rs.getInt("japan"));
				dto.setChina(rs.getInt("china"));
				dto.setGigan1(rs.getString("gigan1"));
				dto.setSchool1(rs.getString("school1"));
				dto.setJeongong1(rs.getString("jeongong1"));
				dto.setGigan2(rs.getString("gigan2"));
				dto.setSchool2(rs.getString("school2"));
				dto.setJeongong2(rs.getString("jeongong2"));
				dto.setGigan3(rs.getString("gigan3"));
				dto.setSchool3(rs.getString("school3"));
				dto.setJeongong3(rs.getString("jeongong3"));
				dto.setGigan4(rs.getString("gigan4"));
				dto.setSchool4(rs.getString("school4"));
				dto.setJeongong4(rs.getString("jeongong4"));
				dto.setWdate(rs.getDate("wdate"));
				
				resumeList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return resumeList;
	}

	@Override
	public ResumeDTO getSelectOne(int no) {
		getConn();
		ResumeDTO dto = new ResumeDTO();
		try {
			String sql = "select * from resume where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setPic(rs.getString("pic"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
				dto.setToeic(rs.getInt("toeic"));
				dto.setToefl(rs.getInt("toefl"));
				dto.setJapan(rs.getInt("japan"));
				dto.setChina(rs.getInt("china"));
				dto.setGigan1(rs.getString("gigan1"));
				dto.setSchool1(rs.getString("school1"));
				dto.setJeongong1(rs.getString("jeongong1"));
				dto.setGigan2(rs.getString("gigan2"));
				dto.setSchool2(rs.getString("school2"));
				dto.setJeongong2(rs.getString("jeongong2"));
				dto.setGigan3(rs.getString("gigan3"));
				dto.setSchool3(rs.getString("school3"));
				dto.setJeongong3(rs.getString("jeongong3"));
				dto.setGigan4(rs.getString("gigan4"));
				dto.setSchool4(rs.getString("school4"));
				dto.setJeongong4(rs.getString("jeongong4"));
				dto.setWdate(rs.getDate("wdate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}

	@Override
	public int setUpdate(ResumeDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update resume set "
					+ "pic = ?, "
					+ "name = ?, "
					+ "email = ?, "
					+ "phone = ?, "
					+ "address = ?, "
					+ "toeic = ?, "
					+ "toefl = ?, "
					+ "japan = ?, "
					+ "china = ?, "
					+ "gigan1 = ?, "
					+ "school1 = ?, "
					+ "jeongong1 = ?, "
					+ "gigan2 = ?, "
					+ "school2 = ?, "
					+ "jeongong2 = ?, "
					+ "gigan3 = ?, "
					+ "school3 = ?, "
					+ "jeongong3 = ?, "
					+ "gigan4 = ?, "
					+ "school4 = ?, "
					+ "jeongong4 = ? "
					+ "where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPic());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getAddress());
			pstmt.setInt(6, dto.getToeic());
			pstmt.setInt(7, dto.getToefl());
			pstmt.setInt(8, dto.getJapan());
			pstmt.setInt(9, dto.getChina());
			pstmt.setString(10, dto.getGigan1());
			pstmt.setString(11, dto.getSchool1());
			pstmt.setString(12, dto.getJeongong1());
			pstmt.setString(13, dto.getGigan2());
			pstmt.setString(14, dto.getSchool2());
			pstmt.setString(15, dto.getJeongong2());
			pstmt.setString(16, dto.getGigan3());
			pstmt.setString(17, dto.getSchool3());
			pstmt.setString(18, dto.getJeongong3());
			pstmt.setString(19, dto.getGigan4());
			pstmt.setString(20, dto.getSchool4());
			pstmt.setString(21, dto.getJeongong4());
			pstmt.setInt(22, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}

	@Override
	public int setDelete(ResumeDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "delete from resume where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
}
