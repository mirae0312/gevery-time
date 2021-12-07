 <%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%

    Member member = (Member)request.getAttribute("member");    
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>

	*{
		margin : 0 auto;
		padding : 0;
	}
	#button{
		margin-left : 70px;
		margin-top : 20px;
	}
	#userName{
		color:#1f4e5f;
		text-align : center;
		font-size:15px;
	}
	#userId{
		text-align : center;
	}
</style>
</head>
	<body>
	<center>
		<div class="wrap">
	
						
		<div id="userName">[<%=member.getMemberName()%>]님의 아이디</div><br>
		<div id="userId">[<%=member.getMemberId() %>] 입니다.</div>
		<div class="button"><button id="button" class="btn btn-outline-info " onclick = "window.close()"; >확인</button></div>
		
		</div>
	</center>
	</body>
</html>