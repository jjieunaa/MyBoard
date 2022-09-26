<%@page import="myboard.dao.impl.BoardIDDAOImpl"%>
<%@page import="myboard.service.BoardIDService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="myboard.dto.BoardFileDTO"%>
<%@page import="myboard.dao.impl.BoardFileWriteDAOImpl"%>
<%@page import="myboard.service.BoardFileService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="myboard.dao.impl.BoardWriteDAOImpl"%>
<%@page import="myboard.service.BoardWriteService"%>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="boardDTO" class="myboard.dto.BoardDTO" />
<jsp:setProperty name="boardDTO" property="*" />
<jsp:setProperty name="boardDTO" property="bwriterid" value="anonymous" />

<%
	// 게시물 정보를 테이블에 등록
	BoardWriteService boardWriteService = new BoardWriteDAOImpl();
	int resultBoard = boardWriteService.writeBoard(boardDTO);
	
	// 등록된 게시물의 id 획득
	BoardIDService boardIDService = new BoardIDDAOImpl();
	int bid = boardIDService.getBID();
	
	// 첨부파일 정보를 테이블에 등록
	BoardFileService boardFileService = new BoardFileWriteDAOImpl();
	Collection <Part> parts = request.getParts();
	List<String> sfnList = (List<String>)request.getAttribute("sfnList");
	List<String> contenttypeList = (List<String>)request.getAttribute("contenttypeList");
	
	int listIndex = 0;
	for (Part part : parts) {
		if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize()>0) {
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setBfcfn(part.getSubmittedFileName());
			boardFileDTO.setBfsfn(sfnList.get(listIndex));
			boardFileDTO.setBfsize((int)part.getSize());
			boardFileDTO.setBfcontenttype(contenttypeList.get(listIndex));
			boardFileService.writeBoardFile(bid, boardFileDTO);
			listIndex++;
		}
	}
	
	if (resultBoard > 0) { // write 했다면
		out.print("<script>alert('정상적으로 입력되었습니다!');</script>");	
	} else {
		out.print("<script>alert('입력 오류가 발생했습니다!');</script>");
	}
	response.sendRedirect("/MyBoard/jsp/listBoard.jsp");
%>
