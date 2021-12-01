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
		<span>선택 분류만 보기</span>
		<input type="button" value="div1" class="pdtDiv"/>
		<input type="button" value="div2" class="pdtDiv"/>
		<input type="button" value="div3" class="pdtDiv"/>
	</div>

	<div id="pdtSearchOption">
		<label for="select1">판매중인 상품만 보기</label><input type="checkbox" name="" id="select1" />
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
			</tbody>
		</table>
	</div>
	
	<!-- 페이지바 -->
	<div class="pageBar"></div>
	
	<script>
		// 상품 등록하기
		$("#pdtEnroll").click((e) => {
			console.log("click");
			location.href="<%= request.getContextPath() %>/product/productForm";
		});
		
		// 상품 게시글 등록하기
		$("#pdtBoardEnroll").click((e) => {
			location.href="<%= request.getContextPath() %>/product/boardForm";
		});
		
		const f = n => n < 10 ? "0" + n : n;
		
		// 리스트 비우기
		$("#empty").click((e) => {
			$("#pdtTable tbody").empty();			
		});		
		
		$(".pdtDiv").click((e) => {
			$("#pdtTable tbody").empty();	
			console.log($(e.target).val());
			$.ajax({
				url: "<%= request.getContextPath() %>/product/getSelectDivList",
				data: {
					div: $(e.target).val()
				},
				success(data){
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
				error: console.log
			});
			
		});
		
		
		// pageBar 기능
		$(()=>{
			selectContent(1);
		});

		$(".pageBar").click((e)=>{
			selectContent($(e.target).data('page'));
		})
		
		const selectContent = (cPage) => {
			$.ajax({
				url:"<%=request.getContextPath()%>/product/productList",
				dataType:"json",
				data:{
					cPage,
				},
				success(data){
					$("#pdtTable tbody").empty();
					
					//List부분
					$(data.list).each((i, e)=>{
						console.log(e.attachments[0]);
						
						let day = new Date(e.regDate);
	                    let value = `\${day.getFullYear()}-\${f(day.getMonth() + 1)}-\${f(day.getDate())}`;
	                    
	                    let imgSrc = e.attachments[0];
						
						console.log(e.product.pdtNo);
						const tr = `			<tr>
		 					<td>\${e.boardNo}</td>
		 					<td><img src="<%= request.getContextPath() %>/upload/market/productSale/\${imgSrc}"/></td>
							<td>\${e.product.state}</td>
							<td>\${e.product.pdtDiv}</td>
							<td><a href="<%= request.getContextPath() %>/product/boardView?no=\${e.boardNo}">\${e.title}</a></td>
							<td>\${e.sellerId}</td>
							<td>\${value}</td>
						</tr>`
						$("#pdtTable tbody").append(tr);
						
					});
					
					//pagebar부분
					console.log(data.pagebar);
					$(".pageBar").empty();
					$(".pageBar").append(data.pagebar);
				},
				error:console.log
			});	
		};	
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 