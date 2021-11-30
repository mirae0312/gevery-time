<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매매글 작성</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/product/boardEnroll" name="productEnrollFrm" method="POST">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="author" id="author" /></td>
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
					<input type="text" name="pdtDiv" id="pdtDiv" readonly>
				</td>
			</tr>
			<tr>
				<th>상품번호</th>
				<td>
					<input type="text" name="pdtNo" id="pdtName" readonly/>
				</td>
			</tr>
			<tr>
				<th>상품이름</th>
				<td>
					<input type="text" name="pdtName" id="pdtName" readonly/>
				</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>
					<input type="text" name="pdtPrice" id="pdtPrice" readonly/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" id="content" cols="30" rows="10"></textarea></td>		
			</tr>
			<tr>
				<th colspan="2">
					<button>제출하기</button>
				</th>
			</tr>
		</table>
	</form>
	
	<form action="<%= request.getContextPath() %>/productSale/getProduct" name="getProductFrm" method="GET">
		<input type="hidden" name="sellerId" />
	</form>

	<script>
		let getProduct = () => {
			const name = "getProductPopup";
			const spec = "width=400, height=400, left=400, top=400";
			const popup = open("", name, spec);
			
			$("[name=sellerId]").val($("#author").val());
			let $frm = $(document.getProductFrm);
			$frm.attr("target", name).submit();
		};
	</script>
	<div id="pageBar">
	</div>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 