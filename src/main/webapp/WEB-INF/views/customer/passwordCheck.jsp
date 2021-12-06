<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/passwordForm.css" /> 
<%
 int no = (Integer) request.getAttribute("no");
%>
  
 
  
<section id="password-container">
<br><br> 
<h3>비밀번호를 입력해주세요.</h3>
<form
	name="boardPasswordCheckFrm"
	action="<%=request.getContextPath() %>/customer/PasswordFinalCheck" 
	method="post">
	
	<table id="tbl-password-view">
	  <input type="hidden" name="no" value="<%= no %>" />  
	 
	<tr>
	  <th>비밀번호</th> 
		<td>
			<input class="inputBox" type="password" name="password"   />
		</td>
 
	</tr>
		<th colspan="2">
			<div id=box><input class="submit" type="submit" value="입력"></div>
		</th>
	</tr>
</table>
</form>
</section>
 

  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>