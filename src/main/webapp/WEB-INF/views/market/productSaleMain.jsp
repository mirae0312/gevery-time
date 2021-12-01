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
	<div id="pdtSearchOption">
		<span>선택 분류만 보기</span>
		<select name="" id="divSelect">
			<option value="%%" selected>선택하기</option>
			<option value="div1">대분류1</option>
			<option value="div2">대분류2</option>
			<option value="div3">대분류3</option>
		</select>
		<br />
		<label for="selectOnSale">판매중인 상품만 보기</label><input type="checkbox" name="" id="selectOnSale" />
		<br />
		<button onclick="selectContent(1);">조회하기</button>
	</div>
	
	<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
	<button id="pdtBoardEnroll">등록하기</button>
	<% } %>
	
	<div id="pdtList">
		<table id="pdtTable">
			<thead>
				<tr>
					<th colspan=8><span id="sumContent"></span></th>
				</tr>
				<tr>
					<th>상품번호</th>
					<th>섬네일</th>
					<th>상태</th>
					<th>분류</th>
					<th>제목</th>
					<th>가격</th>
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
		
		// 날짜 format 함수
		const f = n => n < 10 ? "0" + n : n;
	
		// pageBar 기능
		$(() => {
			selectContent(1);
		});

		$(".pageBar").click((e)=>{
			selectContent($(e.target).data('page'));
		})
		
		const selectContent = (cPage) => {
			console.log($("#divSelect").val());
			console.log($("#selectOnSale").prop("checked"));
			
			const selectedDiv = $("#divSelect").val();
			const selectedOnSale = $("#selectOnSale").prop("checked");
			
			$.ajax({
				url:"<%=request.getContextPath()%>/product/productList",
				dataType:"json",
				data:{
					cPage,
					selectedDiv,
					selectedOnSale
				},
				success(data){
					$("#pdtTable tbody").empty();
					console.log(data.totalContent);
					$("#sumContent").html('조회된 게시물 수 : '+ data.totalContent);
					
					//List부분
					$(data.list).each((i, e)=>{						
						let day = new Date(e.regDate);
	                    let value = `\${day.getFullYear()}-\${f(day.getMonth() + 1)}-\${f(day.getDate())}`;
	                    
	                    let imgSrc = e.attachments[0].renamedFilename;
						
						const tr = `			<tr>
		 					<td>\${e.boardNo}</td>
		 					<td><img src="<%= request.getContextPath() %>/upload/market/productSale/\${imgSrc}" style="width:150px"/></td>
							<td>\${e.product.state}</td>
							<td>\${e.product.pdtDiv}</td>
							<td><a href="<%= request.getContextPath() %>/product/boardView?no=\${e.boardNo}">\${e.title}</a></td>
							<td>\${e.product.pdtPrice}원</td>
							<td>\${e.sellerId}</td>
							<td>\${value}</td>
						</tr>`
						$("#pdtTable tbody").append(tr);
						
					});
					
					//pagebar부분
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