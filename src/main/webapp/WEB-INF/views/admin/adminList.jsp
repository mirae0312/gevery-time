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
		<li>
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
				</tbody>
			</table>
		</li>
		
	</ul>
</div>
<script>
$(() => {
	infoList();
});

const infoList = () => {
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/adminList",
		dataType: "json",
		method: "post",
		success(data){
			//console.log(data);
			
			const $tbody = $("#state-business-info tbody");
			
			
			$(data).each((i, {code, memberId, regCheck, regDate, businessName, headContent, deleteCheck}) => {							
				//console.log(code, memberId, regCheck, regDate, businessName, headContent, deleteCheck);
				
				// 날짜포멧
				let rd = new Date(regDate);
				let value = `\${rd.getFullYear()}.\${(rd.getMonth() + 1)}.\${(rd.getDate())}`;
				
				// 상태
				var state = "상태";
				
				if(deleteCheck === "A")
					state = "삭제";
				if(regCheck === "O" && deleteCheck === "D")
					state = "보류";
				if(regCheck === "I" && deleteCheck === "D")
					state = "게시"					
				
				const tr = `<tr>
				<td>\${code}</td>
				<td>\${memberId}</td>
				<td><a href="#" target="_self" onclick="window.open('<%= request.getContextPath() %>/admin/check?code=\${code}', 
							'_blank', 'width=500px, height=500px, scrollbars = yes')" >\${businessName}</a></td>
				<td>\${headContent}</td>
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