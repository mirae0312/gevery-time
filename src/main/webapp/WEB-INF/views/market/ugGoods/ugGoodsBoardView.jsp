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
			<th>제목</th>
			<td><%= board.getTitle() %></td>
		</thead>
	</table>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 