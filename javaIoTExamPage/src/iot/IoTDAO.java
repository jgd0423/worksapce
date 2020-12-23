package iot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class IoTDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Method
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "javaIoTExam";
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
	
	public ArrayList<IoTDTO> getAllStatus() {
		getConn();
		ArrayList<IoTDTO> statusList = new ArrayList<>();
		try {
			String sql = "select * from wallpad order by regidate desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IoTDTO dto = new IoTDTO();
				dto.setAptId(rs.getString("aptId"));
				dto.setSecurity(rs.getString("security"));
				dto.setLight(rs.getString("light"));
				dto.setAircondition(rs.getString("aircondition"));
				dto.setTelevision(rs.getString("television"));
				dto.setCucu(rs.getString("cucu"));
				statusList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return statusList;
	}
}
