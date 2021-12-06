<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% List<Board> list = (List<Board>)request.getAttribute("list"); 
List<Board> popularList = (List<Board>)request.getAttribute("popularList"); 
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%if(popularList != null && !popularList.isEmpty()){%>
<h2 id="popularList"> 한 주의 인기게시물</h2>
<table id="popularBoardList" class="board-list-table">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>첨부파일</th>
				<th>조회수</th>
				<th>추천수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
		<%for(Board board : popularList) {%>
		<tr>
				<td><%=board.getNo()%></td>
				<td>
					<a href="<%= request.getContextPath()%>/board/boardView?no=<%=board.getNo()%>">
						<%=board.getTitle()%> <%=board.getCommentCount()>0? "<span class='comment-count'>("+board.getCommentCount()+")</span>":""%>
					</a>
				</td>
				<td><%=board.getWriter()%></td>
				<td>
<% if(board.getAttachCount()>0){ %>
					<img src="<%=request.getContextPath() %>/images/file.png" alt="" width="16px" />
<%} %>
				</td>
				<td><%=board.getReadCount()%></td>
				<td><%=board.getLikeCount()%></td>
				<td><%=board.getRegDate()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<%} %>
<%-- 	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%=request.getContextPath()%>/board/boardForm'" />
 --%>	<div class="select-container">
	<select name="select" id="select">
		<option value="Review">선택</option>
		<option value="Go">가는거</option>
		<option value="Eat">먹는거</option>
		<option value="Use">쓰는거</option>
	</select>
	<select name="sort" id="sort">
		<option value="No">최신순</option>
		<option value="Like">추천순</option>
		<option value="Read">조회순</option>
	</select>
	</div>
	<table id="boardList" class="board-list-table">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>첨부파일</th>
				<th>조회수</th>
				<th>추천수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div class="board-pageBar">
</div>
<script>
const f = function(n){
    return n<10 ? `0\${n}`:n;
}
$(()=>{
	selectContent(1);
});
$(select).change((e)=>{
	const cPage = $(".board-pageBar .cPage").html();
	selectContent(cPage);
});
$(sort).change((e)=>{
	const cPage = $(".board-pageBar .cPage").html();
	selectContent(cPage);
});
$(".board-pageBar").click((e)=>{
	selectContent($(e.target).data('page'));
})
const selectContent = (cPage) => {
	const boardSelect = $("#select option:selected").val();
	console.log(boardSelect);
	const sort = $("#sort option:selected").val();
	console.log(sort);
	$.ajax({
		url:"<%=request.getContextPath()%>/board/boardList",
		dataType:"json",
		data:{
			cPage,
			boardSelect,
			sort
		},
		success(data){
			console.log(data);
			$("#boardList tbody").empty();
			//boardList부분
			$(data.list).each((i,e)=>{
				const d = new Date(e.regDate);
				const date = `\${d.getFullYear()}-\${f(d.getMonth())}-\${f(d.getDate())}`
				console.log($(e.no));
				let img = "";
				let commentCount = "";
				if(e.attachCount>0){
					img = "<img src=\"<%=request.getContextPath() %>/images/file.png\"  width=\"16px\"/>";
				}
				if(e.commentCount>0){
					console.log(e.commentCount);
					commentCount = "<span class='comment-count'>("+e.commentCount+")</span>";
					console.log(commentCount);
				}
				const tr = `			<tr>
 					<td>\${e.no}</td>
 					<td><a href="<%=request.getContextPath()%>/board/boardView?no=\${e.no}">\${e.title}</a> \${commentCount}</td>
					<td>\${e.writer}</td>
					<td>
 						\${img}
 					</td>
					<td>\${e.readCount}</td>
					<td>\${e.likeCount}</td>
					<td>\${date}</td>
				</tr>`
				console.log($("#boardList tbody"));
				$("#boardList tbody").append(tr);
				
			})
			//pagebar부분
			console.log(data.pagebar);
			$(".board-pageBar").empty();
			$(".board-pageBar").append(data.pagebar);
		},
		error:console.log
	});	
};



</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>