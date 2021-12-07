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

<section id="board-container">
 <div id="leftbox">
            <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
 </div>
<br><br>
<span style="margin-left:13px;">문의글 작성하기</span> 
<br><br>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/customer/qnaBoardEnroll" 
	method="post">
	
	<table id="tbl-board-view">
	<tr>
		<td>제 목</td>
		<td><input class="inputBox" type="text" name="title" ></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>
			<input class="inputBox" type="text" name="writer" value="<%= loginMember.getMemberId() %>" readonly/>
		</td>
	</tr>
	<tr>
    	<td>분류</td>
        <td>
        <label for="category"></label> 
         <select class="inputBox" name="category" id="category"  >
          <option value="[결제문의]">결제문의</option>
          <option value="[포인트문의]">포인트문의</option>
          <option value="[회원정보문의]">회원정보문의</option>
          <option value="[신고문의]">신고문의</option>
          <option value="[기타문의]">기타문의</option>
          
        </select>
       </td>
    </tr>              
	<tr>
		<td>내 용</td>
		<td><textarea name="content" id="summernote" class="summernote"></textarea></td>
	</tr>
	<tr>
	  <td>비밀번호</td>
		<td>
			<input class="inputBox" type="password" name="password" maxlength="20"/>
		</td>
	 </tr>
	<tr>
		<th colspan="2">
			<div id=box><input class="submit" type="submit" value="등록하기"></div>
		</th>
	</tr>
</table>
</form>
</section>

  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>


 