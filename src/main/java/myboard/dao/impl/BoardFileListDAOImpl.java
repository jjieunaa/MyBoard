package myboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import myboard.constants.MyboardConstants;
import myboard.dto.BoardFileDTO;

public class BoardFileListDAOImpl extends AbstractBoardFileDAO {

	@Override
	public List<BoardFileDTO> listBoardFile(int bid) throws Exception {
		
		Connection conn = getConnection();
		String sql = MyboardConstants.queries.getProperty("FILE_LIST_SQL");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bid);
		ResultSet rs = pstmt.executeQuery();
		List<BoardFileDTO> list = null;
		
		if (rs!=null) {
			list = new ArrayList<BoardFileDTO>();
			while (rs.next()) {
				BoardFileDTO boardFileDTO = new BoardFileDTO();
				boardFileDTO.setBfid(rs.getInt("bfid"));
				boardFileDTO.setBfcfn(rs.getString("bfcfn"));
				boardFileDTO.setBfsfn(rs.getString("bfsfn"));
				boardFileDTO.setBfsize(rs.getInt("bfsize"));
				boardFileDTO.setBfbid(rs.getInt("bfbid"));
				list.add(boardFileDTO);
			}
		}
		
		closeConnection(rs, pstmt, conn);
		
		return list;
		
	}

} // class
