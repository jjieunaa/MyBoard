package myboard.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import myboard.constants.MyboardConstants;
import myboard.dao.BoardListDAO;
import myboard.dto.BoardDTO;

public class BoardListDAOImpl
	extends BoardDAOImpl
		implements BoardListDAO {
	
	@Override
	public List<BoardDTO> listBoard(String bdomain, Map<String, String> searchCriteria) throws Exception {
		
		String prependSQL = MyboardConstants.queries.getProperty("LIST_SEARCH_SQL_PREPEND");
		String appendSQL = MyboardConstants.queries.getProperty("LIST_SEARCH_SQL_APPEND");
		String whereSQL = "";
		StringBuffer whereSQLBuffer = new StringBuffer();

		if (bdomain.equals("")) whereSQLBuffer.append(" where 1=1 ");
		else {
			whereSQLBuffer.append(" where bdomain='");
			whereSQLBuffer.append(bdomain);
			whereSQLBuffer.append("' ");
		}
		
		String searchDomainValue = searchCriteria.get("searchDomain");
		String searchTextValue = searchCriteria.get("searchText");
		
		if (searchDomainValue!=null && !searchDomainValue.equals("") && searchTextValue!=null) {
			whereSQLBuffer.append(" and ");
			whereSQLBuffer.append(searchDomainValue);
			whereSQLBuffer.append(" like '%");
			whereSQLBuffer.append(searchTextValue);
			whereSQLBuffer.append("%' ");
		}
		
		whereSQL = whereSQLBuffer.toString();	// 버퍼가 스트링으로 한 번에 감
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(prependSQL + whereSQL + appendSQL);
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		while (rs.next()) {
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setBid(rs.getInt("bid"));
			boardDTO.setBtitle(rs.getString("btitle"));
			boardDTO.setBcontent(rs.getString("bcontent"));
			boardDTO.setBwdate(rs.getTimestamp("bwdate"));
			boardDTO.setBdomain(rs.getString("bdomain"));
			boardDTO.setBwriterid(rs.getString("bwriterid"));
			
			list.add(boardDTO);
		}
		
		closeConnection(rs, pstmt, conn);
		return list;
		
		// System.out.println(searchSQL);
		// return null;
		
	} // listBoard
	
	@Override
	public int countAttachFiles(int bid) throws Exception {
		
		Connection conn = getConnection();
		
		// CallableStatement 문법
		// 프로시저: { call 프로시저명(?,?...) }
		// 함수: { ? = call 함수명(?,?...) }
		// 데이터베이스 > 자바로 전달되는 변수는 out parameter로 등록 (registerOutParameter(인자번호, 디비타입))
		// 자바 > 데이터베이스로 전달되는 변수는 CallableStatement의 setter를 호출
		// execute()로 실행
		// 결과는 CallableStatement의 getter를 호출
		CallableStatement cstmt = conn.prepareCall("{ ? = call attachCount(?) }");	// 첫번째 ?: outparameter, 두번째 ?: inparameter
		cstmt.registerOutParameter(1, java.sql.Types.INTEGER);	// dbint가 javaint 아니니까 타입 맞춰줌
		cstmt.setInt(2, bid);	// 값 주기
		cstmt.execute();	// 실행
		
		int result = cstmt.getInt(1);	// 타입이 int니까 int로 받음
		
		closeConnection(cstmt, conn);
		
		return result;
		
	}
	
} // class
