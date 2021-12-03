<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String uid = (String) request.getAttribute("uid");
	String muid = (String) request.getAttribute("muid");
	String amount = (String) request.getAttribute("amount");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 완료</title>
</head>
<body>
	<h1>결제 완료 화면</h1>
	<p>주문이 완료 되었습니다.</p>
	<table>
		<thead>
			<tr>
				<th>주문 상세 내역</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>결제 고유 ID</th>
				<td><%= uid %></td>
			</tr>
			<tr>
				<th>상점 거래 ID</th>
				<td><%= muid %></td>
			</tr>
			<tr>
				<th>총 가격</th>
				<td><%= amount %>원</td>
			</tr>
			
		</tbody>
	</table>
	
	<button onclick="location.href='<%= request.getContextPath() %>/product/main'">쇼핑 계속하기</button>
	
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>