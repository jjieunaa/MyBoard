package myboard.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static Connection getConnection() {
		try {
			// JDBC 드라이버 클래스를 메모리에 로딩
			Class.forName("core.log.jdbc.driver.MysqlDriver");
		} catch (ClassNotFoundException cnfe) {
			// 클래스가 없으면 익셉션
			cnfe.printStackTrace();
		}
		// 2. Connection 획득
		// JDBC URL: 데이터베이스 연결할 경로
		String JDBC_URL = "jdbc:mysql://localhost:3306/board?useUnicode=true&characterEncoding=utf8";
		String JDBC_USER = "jieun";
		String JDBC_PASS = "1234";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	} // getConnection()
	
	public static void closeConnection(Connection conn) {
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	// 오버로딩: 클래스나 인터페이스 안에서
	// 오버라이딩: 상속 관계 안에서
	public static void closeConnection(Statement stmt, Connection conn) {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		closeConnection(conn);
	} // Connection만 닫음
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		closeConnection(stmt, conn);
	} // Statement와 Connection 닫음
	
} // class
