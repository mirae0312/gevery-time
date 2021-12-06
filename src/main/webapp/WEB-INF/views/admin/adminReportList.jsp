<%@page import="com.zea.geverytime.customer.model.vo.ReportBoard"%>
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
<h2>관리자 페이지</h2>
<div class="admin-container">
	<ul class="adminBar">
		<li><a href="<%= request.getContextPath() %>/admin/adminList">정보게시물 승인</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/reportList">신고사항</a></li>
	</ul>
</div>
<div>
	<ul>
		<li>
			<h2>신고사항</h2>
			<table id="refort-container">
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
		</li>
	</ul>
</div>
<script>

$(() => {
	reportList();
});

const reportList = () => {
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/reportList",
		dataType: "json",
		method: "post",
		success(data){
			const $tbody = $("#refort-container tbody");
			
			$(data).each((i, {reportCode, title, memberId, content, regDate, reportCheck, deleteCheck}) => {
				console.log(reportCode, title, memberId, content, regDate, reportCheck);
				let rd = new Date(regDate);
				let value = `\${rd.getFullYear()}.\${(rd.getMonth() + 1)}.\${(rd.getDate())}`;
				
				var state = "상태";
				
				if(deleteCheck === "A")
					state = "삭제";
				if(reportCheck === "C" && deleteCheck === "d")
					state = "처리완료";
				if(reportCheck === "U" && deleteCheck === "d")
					state = "처리중" 
				
				const tr = `<tr>
				<td>\${reportCode}</td>
				<td><a href="#" target="_self", onclick="window.open('<%= request.getContextPath() %>/admin/reportCheck?code=\${reportCode}', 
					'_blank', 'width=500px, height=200px, scrollbars = yes')" >\${title}</a>
				</td>
				<td>\${memberId}</td>
				<td>\${content}</td>
				<td>\${value}</td>
				<td>\${state}</td>
				</tr>`;
					
				$tbody.append(tr);
			});
		},
		error:console.log
		
	});
};

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>