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
				<th>제목</th>
				<th>작성자</th>
				<th>판매상태</th>
				<th>내용</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td><%= board.getTitle() %></td>
				<td><%= board.getSellerId() %></td>
				<td><%= board.getProductState() %></td>
				<td><%= board.getContent() %></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 