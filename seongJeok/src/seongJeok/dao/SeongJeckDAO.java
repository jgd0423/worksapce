package seongJeok.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import seongJeok.dto.SeongJeokDTO;


public class SeongJeckDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public SeongJeckDAO() {}
		
	// Method
	public Connection getConn() {
		Connection conn = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "srv_pg_impl";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- 오라클 접속 성공 --");					
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		}
		return conn;
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
	
	public int setInsert(SeongJeokDTO dto) {
		conn = getConn();
		int result = 0;
		String[] isAnswer = isAnswer(dto);
		int score = calcScore(isAnswer);
		try {
			String sql = "insert into sj values ("
					+ "?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?,"
					+ "?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSname());
			pstmt.setInt(3, dto.getMun_1());
			pstmt.setInt(4, dto.getMun_2());
			pstmt.setInt(5, dto.getMun_3());
			pstmt.setInt(6, dto.getMun_4());
			pstmt.setInt(7, dto.getMun_5());
			pstmt.setString(8, isAnswer[0]);
			pstmt.setString(9, isAnswer[1]);
			pstmt.setString(10, isAnswer[2]);
			pstmt.setString(11, isAnswer[3]);
			pstmt.setString(12, isAnswer[4]);
			pstmt.setInt(13, score);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	private String[] isAnswer(SeongJeokDTO dto) {
		String[] jungdab = { "1", "2", "3", "4", "3" };
		String[] dtoAnswer = { Integer.toString(dto.getMun_1()), 
							   Integer.toString(dto.getMun_2()),
							   Integer.toString(dto.getMun_3()),
							   Integer.toString(dto.getMun_4()),
							   Integer.toString(dto.getMun_5()) };
		String[] isAnswer = new String[5];
		for (int i = 0; i < jungdab.length; i++) {
			if (jungdab[i].equals(dtoAnswer[i])) {
				isAnswer[i] = "O";
			} else {
				isAnswer[i] = "X";
			}			
		}
		return isAnswer;
	}
	
	private int calcScore(String[] isAnswer) {
		int score = 0;
		for (int i = 0; i < isAnswer.length; i++) {
			if (isAnswer[i].equals("O")) {
				score += 20;
			}
		}
		return score;
	}
	
	public ArrayList<SeongJeokDTO> getListAll() {
		conn = getConn();
		ArrayList<SeongJeokDTO> sjList = new ArrayList<>();
		try {
			String sql = "select * from sj";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SeongJeokDTO dto = new SeongJeokDTO();
				dto.setName(rs.getString("name"));
				dto.setSname(rs.getString("sname"));
				dto.setMun_1(rs.getInt("mun_1"));
				dto.setMun_2(rs.getInt("mun_2"));
				dto.setMun_3(rs.getInt("mun_3"));
				dto.setMun_4(rs.getInt("mun_4"));
				dto.setMun_5(rs.getInt("mun_5"));
				dto.setMun_ox_1(rs.getString("mun_ox_1"));
				dto.setMun_ox_2(rs.getString("mun_ox_2"));
				dto.setMun_ox_3(rs.getString("mun_ox_3"));
				dto.setMun_ox_4(rs.getString("mun_ox_4"));
				dto.setMun_ox_5(rs.getString("mun_ox_5"));
				dto.setJumsu(rs.getInt("jumsu"));
				sjList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return sjList;
	}
}
