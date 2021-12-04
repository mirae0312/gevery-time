<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%  	
   String msg = (String) request.getAttribute("msg");
	%>
	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
$(()=>{
	<% if(msg != null) {%>
	        alert("<%= msg %>");
	<% } %>
});
</script>

</body>
</html>