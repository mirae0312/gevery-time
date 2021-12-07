<%@page import="com.zea.geverytime.myPage.model.vo.Purchase"%>
<%@page import="com.zea.geverytime.market.purchase.model.vo.PurchaseHistory"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.zea.geverytime.market.productsale.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myPageMain.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myPagePurchase.css" />
<%
	Map<String, Object> delCartMap = (Map<String, Object>) new HashMap<String, Object>();
	Business businessMember  =  (Business)session.getAttribute("businessMember");
%>
<div id="myPage-container">
	<ul class="myPageBar">
		<% if(loginMember != null && loginMember.getMemberType().equals("N")) { %>
		<li id="memberInfo"><a href="<%=request.getContextPath() %>/myPage/myPageMain">내정보(개인)</a></li>
		<% } %>
		<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
		<li id="businessInfo"><a href="<%=request.getContextPath() %>/myPage/myPageMain">내정보(사업자)</a></li> 
		<% } %>
		<li id="buyList"><a href="<%=request.getContextPath() %>/myPage/PurchaseHistory?memberId=<%= loginMember.getMemberId() %>">나의 구매내역</a></li>
		<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
		<li id="InfoPost"><a href="<%=request.getContextPath() %>/myPage/business?id=<%= businessMember.getMemberId() %>">정보게시물</a></li>
		<% } %>
	</ul>
</div>
<div id="purchase-container">
	<ul>
		<li>
			<h1>구매한 상품목록</h1>
			<table id="purchaseTable">
				<thead id="thead">
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
						for (Purchase pc : list) {
						%>
						<td><%=pc.getUid()%></td>
						<td><%=pc.getMuid()%></td>
						<td><%=pc.getName()%></td>
						<td><%=pc.getProductCount()%></td>
						<td><%=pc.getPrice()%></td>
						<td><%=pc.getRegDate()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</li>
	</ul>
</div>
<script>
	$("#searchDiv").change((e) => {
	
	});		
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 