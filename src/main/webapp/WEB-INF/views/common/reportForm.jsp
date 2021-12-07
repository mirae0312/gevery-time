<%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	String code = (String) request.getAttribute("code");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/summernote/summernote-lite.css">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/summernote-lite.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/lang/summernote-ko-KR.js"></script>
</head>
<body>

<div class="report-wrapper">
	<form name="commonReportFrm">
		<h1>신고</h1>
		<input type="text" class="report-head" name="head" placeholder="제목을 입력하세요." />
		<p>작성자 : <%= loginMember.getMemberId() %></p>
		<textarea name="body" id="report-body" cols="30" rows="10"></textarea>
		<input type="hidden" name="code" value="<%=code%>" />
		<input type="hidden" name="code" value="<%= code %>" />
		<input type="hidden" name="writer" value="<%= loginMember.getMemberId() %>" /><br />
		<input type="button" id="report-submit" value="등록" />
	</form>
</div>

<script>
$("#report-submit").click((e) => {
	if($(".report-head").val() != "" && $("#report-body").val() != ""){
		var $frm = $(document.commonReportFrm).serialize();
		$.ajax({
			url: "<%= request.getContextPath() %>/common/report",
			method: "post",
			data: $frm,
			success(data){
				alert("신고 등록 성공");
				opener.location.reload();
				close();
			},
			error:console.log
		});		
	}else{
		alert("제목과 내용을 입력하세요.");
	}
});

$(document).ready(function() {
	$('#report-body').summernote({
		height: 300,
		focus: false,
		disableResizeEditor: true,
		toolbar: [
			['style', ['bold', 'italic', 'underline', 'clear']],
			['font', ['strikethrough', 'superscript', 'subscript']],
			['fontsize', ['fontsize']],
			['color', ['color']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
		]
	});	
});
</script>

</body>
</html>