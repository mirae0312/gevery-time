<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaForm.css" /> 
<%
 int no = (Integer) request.getAttribute("no");
%>
  
 
  
<section id="board-container">
<br><br> 
<h3>문의글 작성</h3>
<form
	name="boardPasswordCheckFrm"
	action="<%=request.getContextPath() %>/customer/PasswordFinalCheck" 
	method="post">
	
	<table id="tbl-board-view">
	  <input type="hidden" name="no" value="<%= no %>" />  
	 
	<tr>
	  <th>비밀번호</th> 
		<td>
			<input class="inputBox" type="password" name="password"   />
		</td>
 
	</tr>
		<th colspan="2">
			<div id=box><input class="submit" type="submit" value="등록하기"></div>
		</th>
	</tr>
</table>
</form>
</section>
 

  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>