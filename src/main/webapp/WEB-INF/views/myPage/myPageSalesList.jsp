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
	String id = (String) request.getAttribute("memberId");
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
				<tbody id="purchaseTbody">
				</tbody>
			</table>
			<div id="pageBar"></div>
		</li>
	</ul>
</div>
<script>

$(() => {
	purchaseList(1);
});

$("#pageBar").click((e) => {
	purchaseList($(e.target).data('page'));
});
const purchaseList = (cPage) => {
	console.log(cPage);
	console.log('<%= loginMember.getMemberId() %>');
	$.ajax({
		url: "<%= request.getContextPath() %>/myPage/purchaseList",
		dataType: "json",
		data: {
				cPage,
				'id':'<%= loginMember.getMemberId() %>'},
		success(data){
			$("#purchaseTable tbody").empty();
				
			console.log(data.list);
			$(data.list).each((i, e) => { 				
					
			// 날짜포멧
			const rd = new Date(e.regDate);
            const value = `\${rd.getFullYear()}.\${(rd.getMonth() + 1)}.\${(rd.getDate())}`;
				
			const tr = `<tr>
				<td>\${e.uid}</td>
	            <td>\${e.muid}</td>
	            <td>\${e.name}</td>
	            <td>\${e.productCount}</td>
	            <td>\${e.price}</td>
	            <td>\${value}</td>
	            </tr>`;
				
	            $("#purchaseTable tbody").append(tr);
			}),
			// pageBar
			console.log(data.pagebar);
	        $("#pageBar").empty();
	        $("#pageBar").append(data.pagebar);
		},
		error: console.log
	});
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 