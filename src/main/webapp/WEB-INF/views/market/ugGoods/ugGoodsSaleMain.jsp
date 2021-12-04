<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고 거래</title>
</head>
<body>
	<% if(loginMember != null) { %>
	<input type="button" value="등록하기" id="boardEnroll"/>
	<% } %>
	<div id="boardList">
		<table id="boardTable">
			<thead>
				<tr>
					<th colspan=8><span id="sumContent"></span></th>
				</tr>
				<tr>
					<th>섬네일</th>
					<th>상태</th>
					<th>분류</th>
					<th>제목</th>
					<th>가격</th>
					<th>판매자</th>
					<th>게시일</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	<!-- 페이지바 -->
	<div class="pageBar"></div>
	
	<script>
		$("#boardEnroll").click((e) => {
			location.href="<%= request.getContextPath() %>/ugGoods/boardForm";
		});
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 