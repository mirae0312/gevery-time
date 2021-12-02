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
		width : 1000px;
		border : 1px solid black;
		border-collapse : collapse;
	}
	#pdtTable tr{
		border : 1px solid black;
		height : 50px;
	}
	#pdtTable th{
		border : 1px solid black;
		height : 50px;
	}
	#pdtTable td{
		border : 1px solid black;
		height : 50px;
	}
	.thumbnailImg{
		width:100px;
	}
</style>
</head>
<body>
<<<<<<< HEAD
	<div id="pdtDivOption">
		<span>선택 분류만 보기</span>
		<input type="button" value="div1" class="pdtDiv"/>
		<input type="button" value="div2" class="pdtDiv"/>
		<input type="button" value="div3" class="pdtDiv"/>
	</div>

=======
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
	<div id="pdtSearchOption">
<<<<<<< HEAD
		<label for="select1">판매중인 상품만 보기</label><input type="checkbox" name="" id="select1" />
=======
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
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
	</div>
	
	<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
	<button id="pdtBoardEnroll">등록하기</button>
<<<<<<< HEAD

=======
	<% } %>
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
	
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
	
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
	<!-- 페이지바 -->
	<div class="pageBar"></div>
	
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
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
		 					<td class="thumbnailImg"><img src="<%= request.getContextPath() %>/upload/market/productSale/\${imgSrc}" style="width:100px; height:50px;"/></td>
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
<<<<<<< HEAD
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
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
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
						
						let day = new Date(e.regDate);
	                    let value = `\${day.getFullYear()}-\${f(day.getMonth() + 1)}-\${f(day.getDate())}`;
						
						console.log(e.product.pdtNo);
						const tr = `			<tr>
		 					<td>\${e.boardNo}</td>
		 					<td>섬네일 예정</td>
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
=======
>>>>>>> branch 'master' of https://github.com/wkrud/semi_geverytime.git
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 