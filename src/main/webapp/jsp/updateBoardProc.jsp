<%@page import="myboard.dao.impl.BoardFileUpdateDAOImpl"%>
<%@page import="myboard.service.BoardFileService"%>
<%@page import="java.io.File"%>
<%@page import="myboard.dao.impl.BoardFileListDAOImpl"%>
<%@page import="myboard.dto.BoardFileDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="myboard.dao.impl.BoardUpdateDAOImpl"%>
<%@page import="myboard.service.BoardUpdateService"%>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="boardDTO" class="myboard.dto.BoardDTO" />
<jsp:setProperty name="boardDTO" property="*" />
<jsp:setProperty name="boardDTO" property="bwriterid" value="anonymous" />

<%
	// 게시글 수정
	BoardUpdateService boardUpdateService = new BoardUpdateDAOImpl();
	int result = boardUpdateService.updateBoard(boardDTO);
	
	// 첨부파일 정보 수정
	int bid = Integer.parseInt(request.getParameter("bid"));
	BoardFileService boardFileService = new BoardFileUpdateDAOImpl();
	boardFileService.updateBoardFiles(bid, boardFileService.listBoardFile(bid));
	
	if (result > 0) { // write 했다면
		out.print("<script>alert('정상적으로 수정되었습니다!'); location.href='/MyBoard/jsp/listBoard.jsp'; </script>");	
	} else {
		out.print("<script>alert('수정 오류가 발생했습니다!'); location.href='/MyBoard/jsp/listBoard.jsp'; </script>");
	}
%>
