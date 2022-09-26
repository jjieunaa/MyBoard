package myboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import myboard.constants.MyboardConstants;
import myboard.dto.BoardFileDTO;

public class BoardFileViewDAOImpl extends AbstractBoardFileDAO {

	@Override
	public BoardFileDTO viewBoardFile(int bfid) throws Exception {
		
			Connection conn = getConnection();
			String sql = MyboardConstants.queries.getProperty("FILE_VIEW_SQL");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bfid);
			ResultSet rs = pstmt.executeQuery();
			BoardFileDTO boardFileDTO = null;
			
			if (rs!=null && rs.next()) {
				boardFileDTO = new BoardFileDTO();
				boardFileDTO.setBfid(rs.getInt("bfid"));
				boardFileDTO.setBfcfn(rs.getString("bfcfn"));
				boardFileDTO.setBfsfn(rs.getString("bfsfn"));
				boardFileDTO.setBfsize(rs.getInt("bfsize"));
				boardFileDTO.setBfbid(rs.getInt("bfbid"));
				}
			
			closeConnection(rs, pstmt, conn);
			
			return boardFileDTO;
			
		}

	} // class
