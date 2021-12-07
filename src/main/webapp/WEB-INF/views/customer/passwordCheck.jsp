<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/passwordForm.css" /> 
<%
 int no = (Integer) request.getAttribute("no");
%>
  
 <div id="leftbox">
            <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
 </div> 
  
<section id="password-container">
<br><br> 
<h4>비밀번호를 입력해주세요.</h4>
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
			<input class="submit1" type="submit" value="입력"> 
		</th>
	</tr>
</table>
</form>
</section>
 

  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>