package employees.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import employees.dto.EmployeesDTO;


public class EmployeesDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "hr";
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
	
	public EmployeesDTO getSelectOne(String firstName, String lastName) {
		getConn();
		EmployeesDTO dto = new EmployeesDTO();
		try {
			String sql = "SELECT * FROM employees WHERE first_name = ? AND last_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setEmployee_id(rs.getInt("employee_id"));
				dto.setFirst_name(rs.getString("first_name"));
				dto.setLast_name(rs.getString("last_name"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone_number(rs.getString("phone_number"));
				dto.setHire_date(rs.getDate("hire_date"));
				dto.setJob_id(rs.getString("job_id"));
				dto.setSalary(rs.getInt("salary"));
				dto.setCommission_pct(rs.getInt("commission_pct"));
				dto.setManager_id(rs.getInt("manager_id"));
				dto.setDepartment_id(rs.getInt("department_id"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
}
