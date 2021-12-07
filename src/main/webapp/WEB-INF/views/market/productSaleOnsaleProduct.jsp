<%@page import="com.zea.geverytime.market.productsale.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<%
	List<Product> list = (List<Product>) request.getAttribute("list");
	String MemberId = (String) loginMember.getMemberId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/market/product/productList.css" />

</head>
<body>
	<h1><%= MemberId %>님의 판매중인 상품 목록</h1>
	<button id="pdtEnroll">상품 등록하기</button>
	
	<%
	if(list.isEmpty() || list == null) {
	%>
		<h2>텅 비었어요. 등록해주세요</h2>
	<%
	} else {
	%>
	<table id="productListTable">
		<thead>
			<tr>
				<th>상품번호</th>
				<th>이름</th>
				<th>가격</th>
				<th>분류</th>
				<th>판매상태</th>
			</tr>
		</thead>
		<tbody>

		<%
		for(Product pdt : list){
		%>
			<tr>
				<td><%= pdt.getPdtNo() %></td>
				<td><%= pdt.getPdtName() %></td>
				<td><%= pdt.getPdtPrice() %></td>
				<td>
					<select class="optionChange" data-pdtno="<%= pdt.getPdtNo() %>" data-colname="div" name="div">
						<option value="dog" <%= pdt.getPdtDiv().equals("dog") ? "selected" : "" %> >강아지</option>
						<option value="cat" <%= pdt.getPdtDiv().equals("cat") ? "selected" : "" %> >고양이</option>
						<option value="goose" <%= pdt.getPdtDiv().equals("goose") ? "selected" : "" %> >거위</option>
					</select>
				</td>
				<td>
					<select class="optionChange" data-pdtno="<%= pdt.getPdtNo() %>" data-colname="state" name="state">
						<option value="판매중" <%= pdt.getState().equals("판매중") ? "selected" : "" %> >판매중</option>
						<option value="판매중지" <%= pdt.getState().equals("판매중지") ? "selected" : "" %> >판매중지</option>
						<option value="품절" <%= pdt.getState().equals("품절") ? "selected" : "" %> >품절</option>
					</select>
				</td>
			</tr>
		<%
		}
		%>
		</tbody>
	</table>
	
	<% } %>
	<script>
		$("#searchDiv").change((e) => {
			
		});
	
		// 상품 option 변경 > DB 저장 반영
		$(".optionChange").change((e) => {
			if(!confirm("변경하시겠습니까?")){
				return;
			}
			console.log(e.target.dataset.pdtno);
			const reqpdtno = e.target.dataset.pdtno;
			const reqcolname = e.target.dataset.colname;
			const reqval = $(e.target).val();
			
			$.ajax({
				url: "<%= request.getContextPath() %>/product/onsaleProductOptionChange",
				method: "POST",
				data: {
					pdtNo: reqpdtno,
					colname: reqcolname,
					val: reqval
				},
				success(data){
					alert("변경에 성공했습니다.");
				},
				error : console.log
			});
		});
	
		// 상품 등록하기
		$("#pdtEnroll").click((e) => {
			location.href="<%= request.getContextPath() %>/product/productForm";
		});
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 