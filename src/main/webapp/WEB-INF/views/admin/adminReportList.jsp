<%@page import="com.zea.geverytime.customer.model.vo.ReportBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminMain.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminReport.css" />
<h2>관리자 페이지</h2>
<div id="admin-container">
	<ul class="adminBar">
		<li><a href="<%= request.getContextPath() %>/admin/adminList">정보게시물</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/reportList">신고사항</a></li>
	</ul>
</div>
<div id="report-container">
	<ul>
		<li>
			<h1>신고사항</h1>
			<table id="reportList">
				<thead>
					<tr>
						<th>신고글 코드</th>
						<th>신고글 제목</th>
						<th>신고자</th>
						<th>신고 내용</th>
						<th>작성일자</th>
						<th>처리상태</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div id="pageBar"></div>
		</li>
	</ul>
</div>
<script>

$(() => {
	reportList(1);
});
$("#pageBar").click((e) => {
	reportList($(e.target).data('page'));
});
const reportList = (cPage) => {
	console.log(cPage);
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/reportCheckList",
		dataType: "json",
		data: {cPage},
		success(data){
			console.log(data);
			$("#reportList tbody").empty();
			
			$(data.list).each((i, e) => {
				console.log(e);
				let rd = new Date(e.regDate);
				let value = `\${rd.getFullYear()}.\${(rd.getMonth() + 1)}.\${(rd.getDate())}`;
				
				var state = "상태";
				
				if(e.deleteCheck === "A")
					state = "삭제";
				if(e.reportCheck === "C" && e.deleteCheck === "d")
					state = "처리완료";
				if(e.reportCheck === "U" && e.deleteCheck === "d")
					state = "처리중" 
				
				const tr = `<tr>
				<td>\${e.reportCode}</td>
				<td><a href="#" target="_self", onclick="window.open('<%= request.getContextPath() %>/admin/reportCheck?code=\${e.reportCode}', 
					'_blank', 'width=500px, height=200px, scrollbars = yes')" >\${e.title}</a>
				</td>
				<td>\${e.memberId}</td>
				<td>\${e.content}</td>
				<td>\${value}</td>
				<td>\${state}</td>
				</tr>`;
					
				$("#reportList tbody").append(tr);
			}),
			console.log(data.pagebar);
            $("#pageBar").empty();
            $("#pageBar").append(data.pagebar);
		},
		error:console.log
		
	});
};

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>