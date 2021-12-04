<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="com.zea.geverytime.info.model.vo.InfoEntity"%>
<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<h2>관리자 페이지</h2>
<div class="admin-container">
	<ul class="adminBar">
		<li><a href="<%= request.getContextPath() %>/admin/adminList">정보게시물 승인</a></li>
		<li><a href="#">신고사항</a></li>
	</ul>
</div>
<div class="infoCheck-container">
	<ul class="infoCheck">
		<h2>정보게시물 승인여부</h2>
		<table id="state-business-info">
			<thead>
				<tr>
					<th>게시글코드</th>
					<th>게시자</th>
					<th>상호명</th>
					<th>제목</th>
					<th>게시일</th>
					<th>승인상태</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		
	</ul>
</div>
<script>
$(() => {

});

$.ajax({
	url: "<%= request.getContextPath() %>/admin/adminList",
	dataType: "json",
	success(data){
		console.log(data);
		const $tbody = $("#infoCheck-container tbody");
	},
	error:console.log	
});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>