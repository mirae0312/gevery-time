<%@page import="com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<%
	UsedGoodsBoard board = (UsedGoodsBoard) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<body>
	<!-- summernote editor -->
	<script>
	  $(document).ready(function() {
	        $('#summernote').summernote({
	              height: 300,                 // 에디터 높이
	              minHeight: null,             // 최소 높이
	              maxHeight: null,             // 최대 높이
	              focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
	              lang: "ko-KR",                    // 한글 설정
	              placeholder: '예쁜말만 써요',    //placeholder 설정
	              disableResizeEditor: true
	        });
	    });
	 </script>
	 <form action="<%= request.getContextPath() %>/ugGoods/boardUpdate" method="POST" enctype="multipart/form-data">
	 	<table>
	 		<input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title" value="<%= board.getTitle() %>"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" id="writer" value="<%= loginMember.getMemberId() %>" readonly /></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="text" name="price" id="price" value="<%= board.getPrice() %>"/></td>
			</tr>
			<tr>
				<th>사진 이미지 등록(최대 4장)</th>
				<td>
					<input type="file" name="pdtImage1" required/><br />
					<input type="file" name="pdtImage2"/><br />
					<input type="file" name="pdtImage3"/><br />
					<input type="file" name="pdtImage4"/><br />
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="summernote" id="summernote" class="summernote" ><%= board.getContent() %></textarea></td>
			</tr>
			<tr>
				<th colspan=2>
					<button>제출하기</button>
				</th>
			</tr>
		</table>
	</form>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 