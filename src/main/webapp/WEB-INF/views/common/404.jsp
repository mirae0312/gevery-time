<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    
<%
	String msg = "페이지를 찾을 수 없습니다.";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

</head>
<body>
<script>
$(()=>{
	alert("<%=msg%>");
	location.href="<%=request.getContextPath()%>";
});
</script>
</body>
</html>