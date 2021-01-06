package test_loop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import db.Db;
import db.DbImplOracle;

public class TransactionDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	
	public void getConn() {
		Db db = new DbImplOracle();
		conn = db.dbConn();
	}
	
	public void getConnClose() {
		Db db = new DbImplOracle();
		db.dbConnClose();
	}
	
	public void insert() {
		getConn();
		try {
			long start_time = System.currentTimeMillis();
			conn.setAutoCommit(false); // 자동 커밋 해제
			
			Savepoint savePoint = null;
			for (int i = 1; i <= 100000; i++) {
				if (i == 1000) {
					savePoint = conn.setSavepoint("S1");
				}
				String sql = "insert into transactionTBL values (?, ?, CURRENT_TIMESTAMP)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.setString(2, "N" + i);
				pstmt.executeUpdate();
				pstmt.close();
			}
			conn.rollback(savePoint);
			
			conn.commit(); // 수동 커밋
			conn.setAutoCommit(true); // 자동 커밋 설정
			long end_time = System.currentTimeMillis();
			System.out.println("시작시간 : " + start_time);
			System.out.println("종료시간 : " + end_time);
			System.out.println("소요시간 : " + ((end_time - start_time) / 1000) + "초 소요됨");
 		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}
	
	public void insertBatch() {
		getConn();
		try {
			long start_time = System.currentTimeMillis();
			conn.setAutoCommit(false); // 자동 커밋 해제
			
			String sql = "insert into transactionTBL values (?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);

			for (int i = 1; i <= 100000; i++) {
				pstmt.setInt(1, i);
				pstmt.setString(2, "N" + i);
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			conn.commit(); // 수동 커밋
			conn.setAutoCommit(true); // 자동 커밋 설정
			long end_time = System.currentTimeMillis();
			System.out.println("시작시간 : " + start_time);
			System.out.println("종료시간 : " + end_time);
			System.out.println("소요시간 : " + ((end_time - start_time) / 1000) + "초 소요됨");
 		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}
}













