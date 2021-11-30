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
		<input type="button" value="div1" class="pdtDiv"/>
		<input type="button" value="div2" class="pdtDiv"/>
		<input type="button" value="div3" class="pdtDiv"/>
	</div>

	<div id="pdtSearchOption">
		<label for="select1">조회기능1</label><input type="checkbox" name="" id="select1" />
		<label for="select2">조회기능2</label><input type="checkbox" name="" id="select2" />
		<label for="select3">조회기능3</label><input type="checkbox" name="" id="select3" />
	</div>
	
	<button id="pdtBoardEnroll">등록하기</button>

	
	<div id="pdtList">
		<table id="pdtTable">
			<thead>
				<tr>
					<th>no</th>
					<th>섬네일</th>
					<th>상태</th>
					<th>분류</th>
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
					<td><%= pb.getProduct().getState() %></td>
					<td><%= pb.getProduct().getPdtDiv() %></td>
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
<<<<<<< HEAD
=======
	const f = function(n){
	    return n<10 ? `0\${n}`:n;
	}
		// 상품 등록하기
		$("#pdtEnroll").click((e) => {
			console.log("click");
			location.href="<%= request.getContextPath() %>/product/productForm";
		});
		
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
		// 상품 게시글 등록하기
		$("#pdtBoardEnroll").click((e) => {
			location.href="<%= request.getContextPath() %>/product/boardForm";
		});
		
		const f = n => n < 10 ? "0" + n : n;

		
		$(".pdtDiv").click((e) => {
			$("#pdtTable tbody").empty();	
			console.log($(e.target).val());
			
			
			$.ajax({
				url: "<%= request.getContextPath() %>/product/getSelectDivList",
				data: {
					div: $(e.target).val()
				},
				success(data){
<<<<<<< HEAD
                    $(data).each((index, {boardNo, title, regDate, sellerId, product}) => {                        
                        let day = new Date(regDate);
                        console.log(day);
                        let value = `\${day.getFullYear()}-\${f(day.getMonth() + 1)}-\${f(day.getDate())}`;
                        
                        const tr = `
                            <tr>
                                <td>\${boardNo}</th>
                                <td>섬네일 예정</td>
                                <td>\${product.state}</td>
                                <td>\${product.pdtDiv}</td>
                                <td>\${title}</td>
                                <td>\${sellerId}</td>
                                <td>\${value}</td>
                            </tr>
                        `;
                        $("#pdtTable tbody").append(tr);
                    })
                },
=======
					$(data).each((index, {boardNo, title, regDate, sellerId, product}) => {
						const d = new Date(regDate);
						const date = `\${d.getFullYear()}-\${f(d.getMonth())}-\${f(d.getDate())}`
						const tr = `
							<tr>
								<td>\${boardNo}</th>
								<td>섬네일 예정</td>
								<td>\${product.state}</td>
								<td>\${product.pdtDiv}</td>
								<td>\${title}</td>
								<td>\${sellerId}</td>
								<td>\${date}</td>
							</tr>
						`;
						$("#pdtTable tbody").append(tr);
					})
				},
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
				error: console.log
			});
			
		});
		
		
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 