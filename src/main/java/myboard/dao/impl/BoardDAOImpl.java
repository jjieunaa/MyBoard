package myboard.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import myboard.dao.BoardDAO;
import myboard.util.ConnectionManager;

public class BoardDAOImpl implements BoardDAO {
	
	@Override
	public Connection getConnection() {
		return ConnectionManager.getConnection();
	}
	
	@Override
	public void closeConnection(Connection conn) {
		ConnectionManager.closeConnection(conn);
	}
	
	@Override
	public void closeConnection(Statement stmt, Connection conn) {
		ConnectionManager.closeConnection(stmt, conn);
	}
	
	@Override
	public void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
		ConnectionManager.closeConnection(rs, stmt, conn);
	}

}
