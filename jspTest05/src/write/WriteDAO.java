package write;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import write.WriteDTO;

public class WriteDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public WriteDAO() {}
	
	// Method
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "jspWriteTest";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- 오라클 접속 성공 --");
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		}
	}
	
	public int getTotalNumberOfWrittenArticles () {
		int result = 0;
		getConn();
		try {
			String sql = "select nvl(max(num), 0) as maxNum from writeTBL01";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("maxNum");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertBoardInfo(WriteDTO dto) {
		getConn();
		int totalWrittenArticles = getTotalNumberOfWrittenArticles();
		int result = 0;
		try {
			String sql = "INSERT INTO writetbl01 values";
			sql += "(seq_writeTBL_01.nextval, ";
			sql += "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, totalWrittenArticles + 1);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPasswd());
			pstmt.setInt(7, totalWrittenArticles + 1);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
