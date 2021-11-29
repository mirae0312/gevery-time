<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록하기</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/product/productEnroll" method="POST">
		<table>
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
							<option value="div1">대분류1</option>
							<option value="div2">대분류2</option>
							<option value="div3">대분류3</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		<br />
		<button>등록하기</button>
	</form>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 