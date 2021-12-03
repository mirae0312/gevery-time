<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="com.zea.geverytime.info.model.vo.InfoEntity"%>
<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<InfoEntity> elist = (List<InfoEntity>) request.getAttribute("elist");
	List<Info> list = (List<Info>) request.getAttribute("list");
	
%>
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
		<table>
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
<%
	for(Info info : list) {
%>	
				<tr>
					<td><%= info.getCode() %></td>
					<td><%= info.getMemberId() %></td>
					<td><%= info.getBusinessName() %></td>
					<td>
			<%-- 			<a href="<%= request.getContextPath() %>/admin/check?code=<%= info.getCode() %>" target="_blank" onclick="infoCheck();"><%= info.getHeadContent() %></a> --%>
						<a href="#"
							target="_self" onclick="window.open('<%= request.getContextPath() %>/admin/check?code=<%= info.getCode() %>', 
							'_blank', 'width=500px, height=500px, scrollbars = yes')"><%= info.getHeadContent() %>
						</a>
					</td>
					<td><%= info.getRegDate() %></td>
					<td><%= info.getRegCheck() %></td>	
				</tr>
<% } %>

			</tbody>
		</table>
		
	</ul>
</div>
<script>
<%-- 
const infoCheck() => {
    var popup = 
    	window.open('`<%= request.getContextPath() %>/admin/check?code=\${$code}`', '_blank', 'width=500px, height=500px, scrollbars = yes');
}; --%>

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>