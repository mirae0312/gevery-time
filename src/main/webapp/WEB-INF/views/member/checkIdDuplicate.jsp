<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = request.getParameter("memberId");
	System.out.println("memberId = " + memberId);
	boolean available = (boolean) request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<style>
div#checkId-container{text-align:center; padding-top:50px;}
span#duplicated{color:red; font-weight:bold;}
</style>
</head>
<body>
	<div id="checkId-container">
<% if(available){ %>
	[<span><%= memberId %></span>]는 사용가능합니다.
	<br /><br />
	<button onclick="popupClose();">닫기</button>
<% } else { %>
	[<span id="duplicated"><%= memberId %></span>]는 이미 사용중입니다.
	<form 
		name="checkIdDuplicateFrm" 
		action="<%= request.getContextPath() %>/member/checkDuplicate"
		method="GET">
		<input type="text" name="memberId" placeholder="아이디를 입력하세요."/>
		<input type="submit" value="아이디중복검사" />
	</form>
	
<% } %>
	</div>
<script>
const popupClose = () => {
	// opener는 popup창을 생성한 페이지의 window객체
	const $frm = $(opener.document.memberEnrollFrm);
	$frm.find("[name=memberId]").val("<%= memberId %>");
	$frm.find("#idValid").val(1);
	close();
};
</script>
</body>
</html>






