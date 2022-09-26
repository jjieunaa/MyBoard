package myboard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import myboard.constants.MyboardConstants;
import myboard.dao.BoardViewDAO;
import myboard.dto.BoardDTO;

public class BoardViewDAOImpl
	extends BoardDAOImpl
		implements BoardViewDAO {

	@Override
	public BoardDTO viewBoard(int bid) throws Exception {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(MyboardConstants.queries.getProperty("VIEW_SQL"));
		pstmt.setInt(1, bid);
		ResultSet rs = pstmt.executeQuery();
		BoardDTO boardDTO = new BoardDTO();
		
		if (rs!=null && rs.next()) {
			boardDTO.setBid(rs.getInt("bid"));
			boardDTO.setBtitle(rs.getString("btitle"));
			boardDTO.setBcontent(rs.getString("bcontent"));
			boardDTO.setBwdate(rs.getTimestamp("bwdate"));
			boardDTO.setBdomain(rs.getString("bdomain"));
			boardDTO.setBwriterid(rs.getString("bwriterid"));
		}
		
		closeConnection(rs, pstmt, conn);
		
		System.out.println(boardDTO);
		
		return boardDTO;
	
	} // viewBoard

} // class
