<%@page import="com.zea.geverytime.myPage.model.vo.Purchase"%>
<%@page import="com.zea.geverytime.market.purchase.model.vo.PurchaseHistory"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.zea.geverytime.market.productsale.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<style>
    table {
        border: 1px solid #000;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #000;
        text-align: center;
        vertical-align: center;
    }
</style>
<%
	Map<String, Object> delCartMap = (Map<String, Object>) new HashMap<String, Object>();
%>
	<h1>구매한 상품목록</h1>	
	<table>
		<thead>
			<tr>
				<th>결제 고유 ID</th>
				<th>상점 거래 ID</th>
				<th>상품 명</th>
				<th>구매 수량</th>
				<th>총 결제금액</th>
				<th>구매 일</th>
			</tr>
		</thead>
		<tbody>

			<tr>
<%
	List<Purchase> list = (List<Purchase>) request.getAttribute("list");
	for( Purchase pc : list) { 
%>
				<td><%= pc.getUid() %></td>
				<td><%= pc.getMuid() %></td>
				<td><%= pc.getName() %></td>
				<td><%= pc.getProductCount() %></td>
				<td><%= pc.getPrice() %></td>
				<td><%= pc.getRegDate() %></td>
			</tr>
<% } %>	
		</tbody>
	</table>
<script>
	$("#searchDiv").change((e) => {
	
	});		
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 