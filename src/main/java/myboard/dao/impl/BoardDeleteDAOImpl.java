package myboard.dao.impl;

import java.sql.PreparedStatement;

import myboard.constants.MyboardConstants;
import myboard.dao.BoardDeleteDAO;
import myboard.util.ConnectionManager;

public class BoardDeleteDAOImpl
	extends BoardDAOImpl
		implements BoardDeleteDAO {

	@Override
	public int deleteBoard(int bid) throws Exception {

		PreparedStatement pstmt = null;
		
		pstmt = getConnection().prepareStatement(MyboardConstants.queries.getProperty("DELETE_SQL"));
		pstmt.setInt(1, bid);			
		
		int result = pstmt.executeUpdate();
		ConnectionManager.closeConnection(pstmt, getConnection());
		return result;
		
	} // deleteBoard

} // class
