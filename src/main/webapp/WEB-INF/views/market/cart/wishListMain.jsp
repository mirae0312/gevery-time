<%@page import="com.zea.geverytime.market.cart.model.vo.WishList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<WishList> list = (List<WishList>) request.getAttribute("list");
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜 목록</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/market/cart/wishList.css" />
</head>
<body>
<h1>찜 목록</h1>
	<% if(list.isEmpty()) { %>
	<h2>텅 비었습니다. </h2>
	<input type="button" value="중고상품 보러가기" onclick="location.href='<%= request.getContextPath() %>/ugGoods/main'"/>
	<% } else { %>
	<table id="wishTable">
		<thead>
			<tr>
				<th>게시글번호</th>
				<th>섬네일</th>
				<th>상태</th>
				<th>제목</th>
				<th>가격</th>
				<th>판매자</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	
	<% } %>

</body>

<script>
	$(() => {
		$.ajax({
			url: "<%= request.getContextPath() %>/ugGoods/getBoards",
			dataType: "json",
			data:{
				<%
				int countNum = 1;
				for(WishList wish : list ) {
				%>
					boardNo<%= countNum %>: "<%= wish.getBoardNo() %>",
				<%
					countNum++;
				}
				%>
				countNum : "<%= countNum %>"
			},
			success(data){
				$(data).each((i, e) => {
					let boardNo = e.no;
					let rfn;
					$(e.attachments).each((a, b) => {
						rfn = b.renamedFilename;
					});
					let state = e.state;
					let title = e.title;
					let price = e.price;
					let seller = e.writer;
					
					console.log(boardNo, rfn, title, price, seller);
					
					const tr = `
					<tr>
						<td>\${boardNo}</td>
						<td><img src="<%= request.getContextPath() %>/upload/market/UgSale/\${rfn}" style="width:100px"/></td>
						<td>\${state}</td>
						<td><a href="<%= request.getContextPath() %>/ugGoods/boardView?boardNo=\${boardNo}">\${title}</a></td>
						<td>\${price}</td>
						<td>\${seller}</td>
						<td>
							<form action="<%= request.getContextPath() %>/cart/deleteWishList" method="POST">
								<input type="hidden" name="boardNo" value="\${boardNo}" />
								<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>"/>
								<input type="submit" class="del-Btn" value="삭제하기" />
							</form>
						</td>
					</tr>
					`
					$("#wishTable").append(tr);
				});
			},
			error:console.log			
		});
		
		
		
		
		
		
	});


</script>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 