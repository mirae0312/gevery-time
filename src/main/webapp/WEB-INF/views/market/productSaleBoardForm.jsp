<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매매글 작성</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/market/product/productForm.css" />
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
	              disableResizeEditor: true,
	              height: 310,
	              width: 700
	        });
	    });
	 
	 </script>
	<h1>판매글 작성하기</h1>
	<div id="form-area">
	<form action="<%= request.getContextPath() %>/product/boardEnroll" name="productEnrollFrm" enctype="multipart/form-data" method="POST">
		<table id="productBoardTable">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" class="noBorder" name="author" id="author" value="<%= loginMember.getMemberId() %>" readonly /></td>
			</tr>
			<tr>
				<th>상품 가져오기</th>
				<td>
					<input type="button" value="가져오기" onclick="getProduct();"/>
				</td>
			</tr>
			<tr>
				<th>상품분류</th>
				<td>
					<input type="text" class="noBorder" name="pdtDiv" id="pdtDiv" readonly>
				</td>
			</tr>
			<tr>
				<th>상품번호</th>
				<td>
					<input type="text" class="noBorder" name="pdtNo" id="pdtNo" readonly/>
				</td>
			</tr>
			<tr>
				<th>상품이름</th>
				<td>
					<input type="text" class="noBorder" name="pdtName" id="pdtName" readonly/>
				</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>
					<input type="text" class="noBorder" name="pdtPrice" id="pdtPrice" readonly/>
				</td>
			</tr>
			<tr>
				<th>섬네일 등록</th>
				<td>
					<input type="file" name="pdtThumbnail" id="pdtThumbnail" required/><span id="notiReq"> **섬네일은 필수 등록 입니다.</span>
				</td>
			</tr>
			<tr>
				<th>사진 등록<br>(최대 3장)</th>
				<td>
					<input type="file" name="pdtImage1"/><br />
					<input type="file" name="pdtImage2"/><br />
					<input type="file" name="pdtImage3"/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td id="summernoteArea"><textarea name="summernote" id="summernote" class="summernote"></textarea></td>		
			</tr>
		</table>
		<button id="submit-btn">제출하기</button>
	</form>
	</div>
	
	<form action="<%= request.getContextPath() %>/productSale/getProduct" name="getProductFrm" method="GET">
		<input type="hidden" name="sellerId" />
	</form>

	<script>
		let getProduct = () => {
			const name = "getProductPopup";
			const spec = "width=400, height=600, left=400, top=400";
			const popup = open("", name, spec);
			
			$("[name=sellerId]").val($("#author").val());
			let $frm = $(document.getProductFrm);
			$frm.attr("target", name).submit();
		};
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 