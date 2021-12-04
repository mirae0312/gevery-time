<%@page import="com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<%
	UsedGoodsBoard board = (UsedGoodsBoard) request.getAttribute("board");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고거래 상품 상세보기</title>
</head>
<body>
	<h1>상품 상세보기</h1>
	<table>
		<thead>
			<tr>
				<th>제목</th>
				<td><%= board.getTitle() %></td>
			</tr>
			<tr>
				<th>글 번호</th>
				<td><%= board.getNo() %></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>작성자</th>
				<td><%= board.getWriter() %></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><%= board.getPrice() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><%= board.getContent() %></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 