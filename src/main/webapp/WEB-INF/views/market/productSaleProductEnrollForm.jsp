<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록하기</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/market/product/productList.css" />

</head>
<body>
	<form action="<%= request.getContextPath() %>/product/productEnroll" method="POST">
		<table id="productEnrollTable">
			<thead>
				<tr>
					<th colspan=2>상품 등록</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="pdtName" /></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" name="pdtPrice"/></td>
				</tr>
				<tr>
					<th>대분류</th>
					<td>
						<select name="pdtDiv" id="pdtDiv">
							<option value="dog">강아지</option>
							<option value="cat">고양이</option>
							<option value="goose">거위</option>
						</select>
						<!-- 멤버ID를 hidden처리해서 같이 전송 -->
						<input type="hidden" name="sellerId" value="<%= loginMember.getMemberId() %>" />
					</td>
				</tr>
			</tbody>
		</table>
		<button id="pdt-Enroll-btn">등록하기</button>
	</form>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 