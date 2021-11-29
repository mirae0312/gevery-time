<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="/css/summernote/summernote-lite.css">

 <script>
 $(document).ready(function() { $('#summernote').summernote(); }); </script>

<section id="board-container">
<h2>게시판 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/customer/qnaBoardEnroll" 
	method="post">
	
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" required></td>
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
			<input type="password" name="password" value="" />
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

 