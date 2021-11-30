<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.market.productsale.model.vo.ProductBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<%
	ProductBoard board = (ProductBoard) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매매글 작성</title>
</head>
 
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
<body>

	<form action="<%= request.getContextPath() %>/product/boardUpdate" name="productEnrollFrm" enctype="multipart/form-data" method="POST">
		<table>
			<tr>
				<td><input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>"/></td>
				<td><input type="hidden" name="orCode" value="<%= board.getOrCode() %>"/></td>
				
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title" value="<%= board.getTitle() %>"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="author" id="author" value="<%= board.getSellerId() %>"/></td>
			</tr>
			<tr>
				<th>상품분류</th>
				<td>
					<input type="text" name="pdtDiv" id="pdtDiv" value="<%= board.getProduct().getPdtDiv() %>" readonly>
				</td>
			</tr>
			<tr>
				<th>상품번호</th>
				<td>
					<input type="text" name="pdtNo" id="pdtNo" value="<%= board.getProduct().getPdtNo() %>" readonly/>
				</td>
			</tr>
			<tr>
				<th>상품이름</th>
				<td>
					<input type="text" name="pdtName" id="pdtName" value="<%= board.getProduct().getPdtName() %>" readonly/>
				</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>
					<input type="text" name="pdtPrice" id="pdtPrice" value="<%= board.getProduct().getPdtPrice() %>" readonly/>
				</td>
			</tr>
			<tr>
				<th>섬네일 이미지 등록</th>
				<td>
					<input type="file" name="pdtThumbnail" id="pdtThumbnail" required/>
				</td>
			</tr>
			<tr>
				<th>사진 이미지 등록(최대 3장)</th>
				<td>
					<input type="file" name="pdtImage1"/><br />
					<input type="file" name="pdtImage2"/><br />
					<input type="file" name="pdtImage3"/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="summernote" id="summernote" class="summernote"><%= board.getContent() %></textarea></td>		
			</tr>
			<tr>
				<th colspan="2">
					<button>제출하기</button>
				</th>
			</tr>
		</table>
	</form>
	
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 