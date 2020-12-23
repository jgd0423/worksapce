package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DbMethod {
	// Field
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Db db = new DbImplOracle();
		
		public int setInsert(HumanDTO dto) {
			conn = db.getConn();
			int result = 0;
			try {
				String sql = "insert into wallPad values ("
						+ "?, ?, ?, ?, ?, ?, current_timestamp)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getAptId());
				pstmt.setString(2, dto.getSecurity());
				pstmt.setString(3, dto.getLight());
				pstmt.setString(4, dto.getCucu());
				pstmt.setString(5, dto.getTelevision());
				pstmt.setString(6, dto.getCucu());
				
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.getConnClose();
			}
			return result;
		}
}
