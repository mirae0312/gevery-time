<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String code = (String) request.getAttribute("code");
%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="report-wrapper">
	<form name="commonReportFrm">
		<input type="text" name="head" placeholder="제목을 입력하세요." />
		<p>작성자 : <%= loginMember.getMemberId() %></p>
		<textarea name="" id="" cols="30" rows="10"></textarea>
		<input type="hidden" name="code" value="code" />
		<input type="hidden" name="writer" value="<%= loginMember.getMemberId() %>" />
		<input type="button" id="report-submit" value="등록" />
	</form>
</div>

<script>
$("#report-submit").click((e) => {
	var $frm = $(document.commonReportFrm).serialize();
	$.ajax({
		url: "<%= request.getContextPath() %>/common/report",
		method: "post",
		data: $frm,
		success(data){
			close();
		},
		error:console.log
	});
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>