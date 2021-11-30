<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaForm.css" /> 
 
 
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
<h2>문의글 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/customer/qnaBoardEnroll" 
	method="post">
	
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" ></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value=""/>
		</td>
	</tr>
	<tr>
    	<td>분류</td>
        <td>
        <label for="category"></label> 
         <select name="category" id="category"  >
          <option value="[아이디/비밀번호 관련]">아이디/비밀번호 관련</option>
          <option value="[회원정보 관련]">회원정보 관련</option>
          <option value="[포인트 관련]">포인트 관련</option>
          <option value="[주문/결제관련]">주문/결제관련</option>
          
        </select>
       </td>
    </tr>              
	<tr>
		<th>내 용</th>
		<td><textarea name="content" id="summernote" class="summernote"></textarea></td>
	</tr>
	<th>비밀번호</th>
		<td>
			<input type="password" name="password"   />
		</td>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기">
		</th>
	</tr>
</table>
</form>
</section>

  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

 