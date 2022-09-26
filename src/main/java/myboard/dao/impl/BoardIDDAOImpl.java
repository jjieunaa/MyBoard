package myboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import myboard.constants.MyboardConstants;
import myboard.dao.BoardIDDAO;

public class BoardIDDAOImpl extends BoardDAOImpl implements BoardIDDAO {

	@Override
	public int getBID() throws Exception {
		
		Connection conn = getConnection();
		String sql = MyboardConstants.queries.getProperty("BID_SQL");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		int result = 0;
		if (rs.next()) {
			result = rs.getInt("bid");
		}
		
		closeConnection(rs, pstmt, conn);
		return result;
	}

}
