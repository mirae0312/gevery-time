<%@page import="com.zea.geverytime.customer.model.vo.ReportBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<div class="reportCheck-container">
	<h1>신 고</h1>
	<div>
	<ul>
		<li>
 			<input type="hidden" id="code" name="code" value="" /> 
			<button id="falseButton">반려</button>
			<button id="deleteButton">삭제</button>
		</li>
	</ul>
</div>
<script>
// url에서 ?code='code값'추출
var getUrlParameter = function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	sURLVariables = sPageURL.split('&'),
	sParameterName, i;
	
	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
};

var reportCode = getUrlParameter('code');
console.log(reportCode);
$('input[name=code]').attr('value', reportCode);
$("#code").val(reportCode);
console.log($("#code").val());
//반려
$("#falseButton").click((e) => {
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/reportCheck",
		method: "POST",
		data: {
			code: $("#code").val(),
			delCode: "A",
			output: "C"
		},
		success(data){
			console.log(data);
			opener.location.reload();		
			close();
		},
		error:console.log
	});
});

//반려
$("#deleteButton").click((e) => {
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/reportCheck",
		method: "POST",
		data: {
			code: $("#code").val(),
			delCode: "d",
			output: "C"
		},
		success(data){
			console.log(data);
			opener.location.reload();		
			close();
		},
		error:console.log
	});
});
/* $(() => {
	reportList();
}); */

<%-- const reportList = () => {
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
				<td>\${title}</td>
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
}; --%>

</script>
</body>
</html>