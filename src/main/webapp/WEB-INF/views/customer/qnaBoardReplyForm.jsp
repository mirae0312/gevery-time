<%@page import="com.zea.geverytime.customer.model.vo.QnaBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaForm.css" /> 
<%
 QnaBoard qnaBoard = (QnaBoard) request.getAttribute("qnaBoard");
%>
<%
 int no = (Integer) request.getAttribute("no");
%>
 

 
  <script>
  $(document).ready(function() {
		$('#summernote').summernote({
			  height: 300,                 // 에디터 높이
			  minHeight: null,             // 최소 높이
			  maxHeight: null,             // 최대 높이
			  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
			  lang: "ko-KR",					// 한글 설정
			  placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
			  disableResizeEditor: true
		});
	});
  </script>

<section id="board-container">
<br><br><br>
<h3>답변글 작성</h3>
<form
	name="boardReplyEnrollFrm"
	action="<%=request.getContextPath() %>/customer/qnaBoardReplyEnroll" 
	method="post">
	 <!-- <input type="hidden" name="no" value="<%= no %>" />  -->
    <input type="hidden" name="replyLevel" value="2" />
     <input type="hidden" name="replyRef" value="<%= no %>" />  
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input class="inputBox" type="text" name="title" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input class="inputBox" type="text" name="writer" value="<%= loginMember.getMemberId() %>" readonly/>
		</td>
	</tr>
	<tr>
    	<th>분류</th>
        <td>
        <label for="category"></label> 
         <select class="inputBox" name="category" id="category"  >
          <option value="[답변]">답변</option>
        
        </select>
       </td>
    </tr>              
	<tr>
		<th>내 용</th>
		<td><textarea name="content" id="summernote" class="summernote"></textarea></td>
	</tr>
	<!-- <th>비밀번호</th>
		<td>
			<input class="inputBox" type="password" name="password" value="" />
		</td>
	<tr> -->
		<th colspan="2">
			<div id=box><input class="submit" type="submit" value="등록하기"></div>
		</th>
	</tr>
</table>
</form>
</section>

  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
