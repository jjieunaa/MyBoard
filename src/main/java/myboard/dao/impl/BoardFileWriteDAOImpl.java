package myboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import myboard.constants.MyboardConstants;
import myboard.dto.BoardFileDTO;

public class BoardFileWriteDAOImpl extends AbstractBoardFileDAO {

	@Override
	public int writeBoardFile(int bid, BoardFileDTO boardFileDTO) throws Exception {
		
		Connection conn = getConnection();
		String sql = MyboardConstants.queries.getProperty("FILE_WRITE_SQL");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, boardFileDTO.getBfcfn());
		pstmt.setString(2, boardFileDTO.getBfsfn());
		pstmt.setInt(3, boardFileDTO.getBfsize());
		pstmt.setInt(4, bid);
		pstmt.setString(5, boardFileDTO.getBfcontenttype());
		
		int result = pstmt.executeUpdate();
		
		closeConnection(pstmt, conn);
		
		return result;
			
	} // writeBoardFile(BoardFileDTO)
	
} // class
