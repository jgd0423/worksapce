package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbMysqlImpl implements Db {
	Connection conn = null;

	@Override
	public Connection dbConn() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String dbUrl = "jdbc:mysql://localhost:3306/jspInterface";
			String dbId = "jspInterface";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- MySQL 접속 성공 --");					
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("-- MySQL 접속 실패 --");
		}
		return conn;
	}
}
