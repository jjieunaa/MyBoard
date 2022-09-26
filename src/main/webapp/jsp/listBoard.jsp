<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="myboard.dao.impl.BoardListDAOImpl"%>
<%@page import="myboard.service.BoardListService"%>
<%@page import="myboard.dto.BoardDTO"%>

<% request.setCharacterEncoding("UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String bdomainParam = request.getParameter("bdomain")==null ? "" : request.getParameter("bdomain"); // 분류
	
	String searchDomainParam = request.getParameter("searchDomain")==null ? "" : request.getParameter("searchDomain");
	String searchTextParam = request.getParameter("searchText")==null ? "" : request.getParameter("searchText");
	
	Map<String, String> searchMap = new HashMap<String, String>();
	searchMap.put("searchDomain", searchDomainParam);
	searchMap.put("searchText", searchTextParam);
	
	BoardListService boardListService = new BoardListDAOImpl();
	
	pageContext.setAttribute("list", boardListService.listBoard(bdomainParam, searchMap));
	pageContext.setAttribute("bdomainParam", bdomainParam);
	pageContext.setAttribute("boardListService", boardListService);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
<script src="/MyBoard/js/common.js"></script>
</head>
<body>
	<div>
		<h3>게시물 리스트 화면</h3>
	</div>
	<div>
		<form name="searchForm" action="/MyBoard/jsp/listBoard.jsp">
			<select name="bdomainSelect" onchange="location.href='/MyBoard/jsp/listBoard.jsp?bdomain='+this.value;">
				<option value="" <c:if test = "${empty bdomainParam}">selected</c:if>>전체</option>
				<option value="notice" <c:if test = "${bdomainParam=='notice'}">selected</c:if>>공지</option>
				<option value="humor" <c:if test = "${bdomainParam=='humor'}">selected</c:if>>유머</option>
				<option value="free" <c:if test = "${bdomainParam=='free'}">selected</c:if>>자유</option>
			</select>&nbsp;&nbsp;
		
			<select name="searchDomain">
				<option value="" <c:if test = "${empty param.searchDomain}">selected</c:if>>전체</option>
				<option value="btitle" <c:if test = "${param.searchDomain=='btitle'}">selected</c:if>>제목</option>
				<option value="bwriterid" <c:if test = "${param.searchDomain=='bwriterid'}">selected</c:if>>작성자</option>
			</select>&nbsp;
			
			<input type="text" name="searchText" value="${param.searchText}"/>&nbsp;
			<input type="button" value="검색" onclick="this.form.submit();" />
		</form>
	</div>
	<div>
		<table>
			<colgroup>
				<col width="5%" />
				<col width="5%" />
				<col width="40%" />
				<col width="10%" />
				<col width="16%" />
				<col width="10%" />
				<col width="7%" />
				<col width="7%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>분류</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일시</th>
					<th>첨부파일 수</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="listSize" value="${list.size()}" />
				<c:forEach var="boardDTO" items="${list}" varStatus="stat">
					<c:set var="bno" value="${listSize-stat.count+1}" />
					<tr>
						<td>${bno}</td>
						<td>
							${boardDTO.bdomain=='notice' ? '공지' : ''}
							${boardDTO.bdomain=='humor' ? '유머' : ''}
							${boardDTO.bdomain=='free' ? '자유' : ''}
						</td>
						<td><a href="/MyBoard/jsp/viewBoard.jsp?bid=${boardDTO.bid}&bno=${bno}">${boardDTO.btitle}</a></td>
						<td>${boardDTO.bwriterid}</td>
						<td><fmt:formatDate value='${boardDTO.bwdate}' pattern='yyyy.MM.dd hh:mm' /></td>
						<td>${boardListService.countAttachFiles(boardDTO.bid)}</td>
						<td><a href="javascript:confirmCommand('/MyBoard/jsp/updateBoardForm.jsp?bid=${boardDTO.bid}','수정');">[수정]</a></td>
						<td><a href="javascript:confirmCommand('/MyBoard/jsp/deleteBoardProc.jsp?bid=${boardDTO.bid}','삭제');">[삭제]</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<input type="button" value="등록" onclick="location.href='/MyBoard/jsp/writeBoardForm.jsp';" />
		</div>
	</div>
</body>
</html>