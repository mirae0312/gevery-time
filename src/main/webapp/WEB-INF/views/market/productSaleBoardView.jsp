<%@page import="com.zea.geverytime.market.productsale.model.vo.ProductBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	  
<%
	ProductBoard board = (ProductBoard) request.getAttribute("board");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세목록</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>판매게시글</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>제목</th>
				<td><%= board.getTitle() %></td>
			<tr>
				<th>작성자</th>
				<td><%= board.getSellerId() %></td>
			</tr>
			<tr>
				<th>판매상태</th>
				<td><%= board.getProduct().getState() %></td>
			</tr>
			<tr>
				<th colspan=2>내용</th>
			</tr>
			<tr>
				<td colspan=2><%= board.getContent() %></td>
			</tr>
		</tbody>
		
	</table>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 
