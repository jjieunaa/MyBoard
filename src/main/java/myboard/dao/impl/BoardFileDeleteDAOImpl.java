package myboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import myboard.constants.MyboardConstants;

public class BoardFileDeleteDAOImpl extends AbstractBoardFileDAO {

	@Override
	public int deleteBoardFile(int bfid) throws Exception {
		
		Connection conn = getConnection();
		String sql = MyboardConstants.queries.getProperty("FILE_DELETE_SQL");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, bfid);			
		
		int result = pstmt.executeUpdate();
		
		closeConnection(pstmt, conn);
		
		return result;
		
	} // deleteBoardFile(int)
	
	@Override
	public int deleteBoardFiles(int bid) throws Exception {
		
		Connection conn = getConnection();
		String sql = MyboardConstants.queries.getProperty("FILES_DELETE_SQL");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, bid);			
		
		int result = pstmt.executeUpdate();
		
		closeConnection(pstmt, conn);
		
		return result;
	}
}
