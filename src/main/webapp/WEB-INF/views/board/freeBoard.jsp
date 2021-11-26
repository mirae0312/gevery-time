<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%=request.getContextPath()%>/board/boardForm'" />
	<select name="animal" id="">
		<option value="dog">강아지</option>
		<option value="cat">고양이</option>
	</select>
	<select name="sort" id="">
		<option value="latest">최신순</option>
		<option value="like">추천순</option>
		<option value="read">조회순</option>
	</select>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>