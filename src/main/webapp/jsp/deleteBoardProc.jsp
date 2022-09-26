<%@page import="java.io.File"%>
<%@page import="myboard.dto.BoardFileDTO"%>
<%@page import="java.util.List"%>
<%@page import="myboard.dao.impl.BoardFileListDAOImpl"%>
<%@page import="myboard.dao.impl.BoardFileDeleteDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="myboard.dao.impl.BoardDeleteDAOImpl"%>

<%
	// 삭제할 게시물 bid
	Integer bid = Integer.parseInt(request.getParameter("bid")==null ? "": request.getParameter("bid"));
	// 첨부파일 삭제
	List<BoardFileDTO> boardFileDTOList = new BoardFileListDAOImpl().listBoardFile(bid);
	for (BoardFileDTO boardFileDTO : boardFileDTOList) {
		File file = new File(boardFileDTO.getBfsfn());
		if (file.exists()) {
			file.delete();
		}
	}
	// 첨부파일 데이터 삭제
	new BoardFileDeleteDAOImpl().deleteBoardFiles(bid);
	// 게시물 데이터 삭제
	new BoardDeleteDAOImpl().deleteBoard(bid);
	response.sendRedirect("/MyBoard/jsp/listBoard.jsp");
%>
