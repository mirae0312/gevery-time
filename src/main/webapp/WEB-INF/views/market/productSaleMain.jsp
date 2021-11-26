<%@page import="com.zea.geverytime.market.productsale.model.vo.ProductBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<%
	List<ProductBoard> list = (List<ProductBoard>) request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 상점</title>
<style>
	#pdtSearchOption {
		width : 500px;
		border : 1px solid black;
		margin : 10px;
	}
	#pdtList {
		margin : 10px
	}
	#pdtTable{
		width : 500px;
		border : 1px solid black;
		border-collapse : collapse;
	}
	#pdtTable tr, td, th{
		border : 1px solid black;
	}
	
</style>
</head>
<body>
	<div id="pdtDivOption">
		<input type="button" value="대분류1" id="pdtDiv1"/>
		<input type="button" value="대분류2" id="pdtDiv2"/>
		<input type="button" value="대분류3" id="pdtDiv3"/>
	</div>

	<div id="pdtSearchOption">
		<label for="select1">조회기능1</label><input type="checkbox" name="" id="select1" />
		<label for="select2">조회기능2</label><input type="checkbox" name="" id="select2" />
		<label for="select3">조회기능3</label><input type="checkbox" name="" id="select3" />
	</div>
	
	<button id="pdtBoardWrite">등록하기</button>
	
	<div id="pdtList">
		<table id="pdtTable">
			<thead>
				<tr>
					<th>no</th>
					<th>섬네일</th>
					<th>상태</th>
					<th>제목</th>
					<th>판매자</th>
					<th>게시일</th>
				</tr>
			</thead>
			<tbody>
			<%
			int no = 1;
			for(ProductBoard pb : list) {
			%>
				<tr>
					<td><%= no %></td>
					<td>섬네일 예정</td>
					<td><%= pb.getProductState() %></td>
					<td><a href="<%= request.getContextPath() %>/product/boardView?no=<%= pb.getBoardNo() %>"><%= pb.getTitle() %></a></td>
					<td><%= pb.getSellerId() %></td>
					<td><%= pb.getRegDate() %></td>
				</tr>
			<%
				no++;
			}
			%>
			</tbody>
		</table>
	</div>
	
	<script>
		$("#pdtBoardWrite").click((e) => {
			location.href="<%= request.getContextPath() %>/productSale/boardForm";
		})
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 