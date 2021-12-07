<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String) request.getAttribute("memberId");
	Business businessMember  =  (Business)session.getAttribute("businessMember");
%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myPageMain.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myPageInfo.css" />
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
<div id="myInfo-container">
	<ul>
		<h1>정보게시물</h3>
		<table id="myPageBusinessInfo">
			<thead id="thead">
				<tr>
					<th>게시글 코드</th>
					<th>게시자</th>
					<th>상호명</th>
					<th>제목</th>
					<th>게시일</th>
					<th>승인상태</th>
				</tr>
			</thead>
			<tbody id="myPageTbody"></tbody>
		</table>
		<div id="pageBar"></div>
	</ul>
</div>

<script>
$(() => {
	checkYN(1);
});

$("#pageBar").click((e) => {
	checkYN($(e.target).data('page'));
});
const checkYN = (cPage) => {
	console.log(cPage);
	$.ajax({
		url: "<%= request.getContextPath() %>/myPage/businessList",
		dataType: "json",
		data: {
				cPage,
				'id':'<%= id %>'},
		success(data){
			$("#myPageBusinessInfo tbody").empty();
			
			console.log(data.businessName);
			$(data.list).each((i, e) => { 				
				
				// 날짜포멧
				let rd = new Date(data.regDate);
				let value = `\${rd.getFullYear()}.\${(rd.getMonth() + 1)}.\${(rd.getDate())}`;
			
				// 상태
				var state = "상태";
			
				if(data.deleteCheck === "A")
					state = "삭제";
				if(data.regCheck === "O" && data.deleteCheck === "D")
					state = "보류";
				if(data.regCheck === "I" && data.deleteCheck === "D")
					state = "게시"									
			
			const tr = `<tr>
				<td>\${e.code}</td>
                <td>\${e.memberId}</td>
                <td>\${e.businessName}</td>
                <td>\${e.headContent}</td>
                <td>\${value}</td>
                <td>\${state}</td>
                </tr>`;
			
                $("#myPageBusinessInfo tbody").append(tr);
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