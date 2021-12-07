<%@page import="com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고 거래</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/market/ugGoods/ugGoodsMain.css" />
</head>
<body>
	<h1>중고 거래</h1>
<%-- 	<% if(loginMember != null) { %>
	<input type="button" value="등록하기" id="boardEnroll"/>
	<% } %> --%>
	<div id="searchOpt">
		<select name="searchSelect" id="searchSelect">
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="searchName" id="searchName" /><input type="button" value="검색" id="searchNameBtn" onclick="selectContent(1)"/>
	</div>
	<div id="boardList">
		<table id="boardTable">
			<thead>
				<tr>
					<th colspan=8><span id="sumContent"></span></th>
				</tr>
				<tr>
					<th>게시글번호</th>
					<th>섬네일</th>
					<th>상태</th>
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
		$("#boardEnroll").click((e) => {
			location.href="<%= request.getContextPath() %>/ugGoods/boardForm";
		});
		
		// 리스트 불러오기 
		$(() => {
			selectContent(1);
		});
 		$(".pageBar").click((e)=>{
			selectContent($(e.target).data('page'));
		});
		
		// 날짜 format 함수
		const f = n => n < 10 ? "0" + n : n;
		
		const selectContent = (cPage) => {	
			const searchKeyword = $("#searchName").val();
			const searchType = $("#searchSelect").val();
			$.ajax({
				url:"<%=request.getContextPath()%>/ugGoods/boardList",
				dataType:"json",
				data:{
					cPage,
					searchKeyword,
					searchType
				},
				success(data){
					$("#boardTable tbody").empty();
					$("#sumContent").html('조회된 게시물 수 : '+ data.totalContent);
					
					//List부분
					$(data.list).each((i, e)=>{						
						let day = new Date(e.regDate);
	                    let value = `\${day.getFullYear()}-\${f(day.getMonth() + 1)}-\${f(day.getDate())}`;
	                    let imgSrc = e.attachments[0].renamedFilename;

	                    
						const tr = `			<tr>
		 					<td>\${e.no}</td>
		 					<td class="thumbnailImg"><img src="<%= request.getContextPath() %>/upload/market/UgSale/\${imgSrc}" style="width:100px; height:50px;"/></td>
							<td>\${e.state}</td>
							<td><a href="<%= request.getContextPath() %>/ugGoods/boardView?boardNo=\${e.no}">\${e.title}</a></td>
							<td>\${e.price}원</td>
							<td>\${e.writer}</td>
							<td>\${value}</td>
						</tr>`
						$("#boardTable tbody").append(tr);
						
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