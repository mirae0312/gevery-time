<%@page import="com.zea.geverytime.customer.model.vo.QnaBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %> 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaForm.css" />
 <%
	QnaBoard qnaBoard = (QnaBoard)request.getAttribute("qnaBoard");
%> 

<section id="board-container">
<h2>게시판 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/customer/qnaBoardUpdate" 
	method="post">
	 <!-- 이대로 폼 제출하면 no값이 없다. no 보내야 한다. hidden 써주면 됨 --> 
   <input type="hidden" name ="no" value="<%= qnaBoard.getNo()%>" />
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" value="<%=qnaBoard.getTitle() %>" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value="<%=qnaBoard.getWriter() %>"/>
		</td>
	</tr>
	<tr>
    	<td>분류</td>
        <td>
        <label for="category"></label> 
         <select name="category" id="category" >
          <option id="member" value="[회원정보 관련]" <%="[회원정보 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>회원정보 관련</option>
          <option id="point" value="[포인트 관련]" <%="[포인트 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>포인트 관련</option>
          <option id="order" value="[주문/결제관련]" <%="[주문/결제관련]".equals(qnaBoard.getCategory())? "selected":"" %>>주문/결제관련</option>
          <option id="board" value="[게시글 관련]" <%="[게시글 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>게시글 관련</option>
          <option id="report" value="[신고 관련]" <%="[신고 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>게시글 관련</option>
        </select>
       </td>
    </tr>              
	<tr>
		<th>내 용</th>
		<td><input type ="text" name="content" value="<%=qnaBoard.getContent() %>"></input></td>
	<tr>
		<th colspan="2">
			<input type="submit" value="수정하기">
		</th>
	</tr>
</table>
</form>
</section>
 
  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

 