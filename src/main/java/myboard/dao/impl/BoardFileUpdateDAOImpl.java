package myboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import myboard.constants.MyboardConstants;
import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;

public class BoardFileUpdateDAOImpl extends AbstractBoardFileDAO implements BoardFileService {

	@Override
	public int updateBoardFiles(int bid, List<BoardFileDTO> list) throws Exception {
		Connection conn = getConnection();
		String sql = MyboardConstants.queries.getProperty("FILE_UPDATE_SQL");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		int result = 0;
		
		if (list!=null) {
			for (BoardFileDTO boardFileDTO : list) {
				pstmt.setString(1, boardFileDTO.getBfcfn());
				pstmt.setString(2, boardFileDTO.getBfsfn());
				pstmt.setInt(3, boardFileDTO.getBfsize());
				pstmt.setString(4, boardFileDTO.getBfcontenttype());
				pstmt.setInt(5, bid);
				result = pstmt.executeUpdate();
				result = result * result;
			}
		}
				
		closeConnection(pstmt, conn);
		
		return result;
	}

}
