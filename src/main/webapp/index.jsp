<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="body-wrapper">
	
	<%-- 사진영역 --%>
	<h1>사진</h1>
	<div class="photo-wrapper">
		
	</div><hr />
	
	<%-- 게시글영역 --%>
	<h1>게시글</h1>
	<div class="board-wrapper">
		<div class="board-left-wrap">
		  <table>
		  <tbody>
		  </tbody>
		  </table>
		</div>
		<div class="board-right-wrap">
		<table>
		  <tbody>
		  </tbody>
		  </table>
		</div>
	</div><hr />
	
	<%-- 인포영역 --%>
	<h1>정보</h1>
	<div class="info-board-wrapper">
		<h1>한 주간의 인기 SPOT</h1>
		
			
	</div><hr />
	
	<%-- 마켓영역 --%>
	<h1>마켓</h1>
	<div class="market-wrapper">
		<table id="market-board-table">
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div><br />
</div>
<script>
$(()=>{
	getBoardPopularList();
	console.log("hi");
});
const f = function(n){
    return n<10 ? `0\${n}`:n; 
}
const getBoardPopularList = () => {
	$.ajax({
		url:"<%=request.getContextPath()%>/common/mainBoardPopularList",
		success(data){
			//console.log(data);
			//console.log(data.freeList);
			$(".board-left-wrap table tbody").empty();
			$(".board-right-wrap table tbody").empty();
			$(data.freeList).each((i,e)=>{
				const d = new Date(e.regDate);
				const date = `\${d.getFullYear()}-\${f(d.getMonth())}-\${f(d.getDate())}`
				let commentCount = "";
				if(e.commentCount>0){
					//console.log(e.commentCount);
					commentCount = '('+e.commentCount+')';
					//console.log(commentCount);
				}
				const tr = `			<tr>
 					<td><a href="<%=request.getContextPath()%>/board/boardView?no=\${e.no}">\${e.title}</a> \${commentCount}</td>
					<td>\${date}</td>
				</tr>`
				$(".board-left-wrap table tbody").append(tr);
				
			})
			$(data.reviewList).each((i,e)=>{
				const d = new Date(e.regDate);
				const date = `\${d.getFullYear()}-\${f(d.getMonth())}-\${f(d.getDate())}`
				let commentCount = "";
				if(e.commentCount>0){
					//console.log(e.commentCount);
					commentCount = '('+e.commentCount+')';
					//console.log(commentCount);
				}
				const tr = `			<tr>
 					<td><a href="<%=request.getContextPath()%>/board/boardView?no=\${e.no}">\${e.title}</a> \${commentCount}</td>
					<td>\${date}</td>
				</tr>`
				$(".board-right-wrap table tbody").append(tr);
				
			})

		},
		error:console.log
	});
	
	$.ajax({
		url:"<%= request.getContextPath() %>/main/infoList",
		data:{"board":"info"},
		dataType:"json",
		success(data){
			console.log(data);
		},
		error:console.log
	});
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>