<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 보기</title>
</head>
<body>
	<div>
		<h3>게시물 보기</h3>
	</div>
	<div>
		번호: ${param.bno}<br />
		분류: ${boardDTO.bdomain=='notice' ? '공지' : ''}
			 ${boardDTO.bdomain=='humor' ? '유머' : ''}
			 ${boardDTO.bdomain=='free' ? '자유' : ''}<br />
		작성자: ${boardDTO.bwriterid}<br />
		작성일시: <fmt:formatDate value='${boardDTO.bwdate}' pattern='yyyy.MM.dd hh:mm' /><br />
		제목: ${boardDTO.btitle}<br />
		내용: <textarea readonly>${boardDTO.bcontent}</textarea><br />
		<c:forEach var="boardFileDTO" items="${boardFileDTOList}" varStatus="stat">
			첨부파일${stat.count}: ${boardFileDTO.bfcfn} (${boardFileDTO.bfsize} bytes)
			<input type="button" value="다운로드" onclick="location.href='/MyBoard/filedownload?bfid=${boardFileDTO.bfid}';"/><br />
			<% // TODO 다운로드 구현해야 함 %>
		</c:forEach>
	</div>
	<div>
		<input type="button" value="목록" onclick="location.href='/MyBoard/boardlist.do';" />
	</div>
</body>
</html>