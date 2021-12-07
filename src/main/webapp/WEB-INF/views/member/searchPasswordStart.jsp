<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
  <%@ include file="/WEB-INF/views/common/header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<title>Insert title here</title>
	<style>
		*{
			text-align : center;
		}
	</style>
</head>
<body>
	<center>
	
	
	<div id="div1">
	<h3>비밀번호 찾기</h3>
		<form action="<%=request.getContextPath() %>/member/FindPwServletView" id="authform" method="post">
		<table class ="table" style="margin:0 auto;width:700px;">
			<tr>
				<td><input type="text" name="memberId" id="memberId" class="form-control" placeholder="아아디을 입력하세요"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" id="email" class="form-control" placeholder="이메일을 입력하세요">
				</td>
			</tr>
			
			<tr></tr>
			<tr></tr>
			<tr></tr>
	
			<tr>
			
				<td><button type="submit" id="searchPwBtn" class= "btn btn-outline-info btn sm"  >확인</button></td>
			</tr>
		
		</table>
		</form>
	
	
	</div>	
</center>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>