<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaForm.css" /> 
 
 
  <script>
  $(document).ready(function() {
		$('#summernote').summernote({
			toolbar: [
			    // [groupName, [list of button]]b 
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			  ],
			  disableResizeEditor: true,
			  height: 300
			});
	});
 
  </script>

<section id="board-container">
<br><br> 
<h3>문의글 작성</h3>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/customer/qnaBoardEnroll" 
	method="post">
	
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input class="inputBox" type="text" name="title" ></td>
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
          <option value="[결제문의]">결제문의</option>
          <option value="[포인트문의]">포인트문의</option>
          <option value="[회원정보문의]">회원정보문의</option>
          <option value="[신고문의]">신고문의</option>
          <option value="[기타문의]">기타문의</option>
          
        </select>
       </td>
    </tr>              
	<tr>
		<th>내 용</th>
		<td><textarea name="content" id="summernote" class="summernote"></textarea></td>
	</tr>
	<tr>
	  <th>비밀번호</th> 
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


 