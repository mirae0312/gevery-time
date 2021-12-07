<%@page import="com.zea.geverytime.customer.model.vo.QnaBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %> 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaForm.css" />
   <script>
  $(document).ready(function() {
		$('#summernote').summernote({
			toolbar: [
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			  ],
			  disableResizeEditor: true,
			  height: 300,
			  width:650
			});
	});
 
  </script>
 <%
	QnaBoard qnaBoard = (QnaBoard)request.getAttribute("qnaBoard");
%> 
 <div id="leftbox">
            <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
 </div>
<section id="board-container">
<span style="margin-left:18px;">게시글 수정하기</span>
<br><br> 
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/customer/qnaBoardUpdate" 
	method="post">
	 <!-- 이대로 폼 제출하면 no값이 없다. no 보내야 한다. hidden 써주면 됨 --> 
   <input type="hidden" name ="no" value="<%= qnaBoard.getNo()%>" />
	<table id="tbl-board-view">
	<tr>
		<td>제 목</td>
		<td><input type="text" name="title"  class="inputBox" value="<%=qnaBoard.getTitle() %>" required></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>
			<input type="text" name="writer"   class="inputBox" value="<%=qnaBoard.getWriter() %>"/>
		</td>
	</tr>
	<tr>
    	<td>분류</td>
        <td>
        <label for="category"></label> 
         <select class="inputBox" name="category" id="category" >
          <option id="member" value="[회원정보 관련]" <%="[회원정보 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>회원정보 관련</option>
          <option id="point" value="[포인트 관련]" <%="[포인트 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>포인트 관련</option>
          <option id="order" value="[주문/결제관련]" <%="[주문/결제관련]".equals(qnaBoard.getCategory())? "selected":"" %>>주문/결제관련</option>
          <option id="board" value="[게시글 관련]" <%="[게시글 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>게시글 관련</option>
          <option id="report" value="[신고 관련]" <%="[신고 관련]".equals(qnaBoard.getCategory())? "selected":"" %>>게시글 관련</option>
        </select>
       </td>
    </tr>              
	<tr>
		<td>내 용</td>
		 
		<td><textarea type ="text" name="content" id="summernote" class="summernote" value="<%=qnaBoard.getContent() %>"></textarea></td>
	<tr>
		<th colspan="2">
			<input type="submit" class="submit" value="수정하기">
		</th>
	</tr>
</table>
</form>
</section>
 
  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

 