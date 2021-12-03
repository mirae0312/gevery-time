<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% List<Board> list = (List<Board>)request.getAttribute("list"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%=request.getContextPath()%>/board/boardForm'" />
	<select name="animal" id="animal">
		<option value="Free">선택</option>
		<option value="Dog">강아지</option>
		<option value="Cat">고양이</option>
	</select>
	<select name="sort" id="sort">
		<option value="No">최신순</option>
		<option value="Like">추천순</option>
		<option value="Read">조회순</option>
	</select>
	<table id="boardList">
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
	<div class="pageBar">
</div>
<script>
$(()=>{
	selectContent(1);
});
$(animal).change((e)=>{
	const cPage = $(".pageBar .cPage").html();
	selectContent(cPage);
});
$(sort).change((e)=>{
	const cPage = $(".pageBar .cPage").html();
	selectContent(cPage);
});
$(".pageBar").click((e)=>{
	selectContent($(e.target).data('page'));
})
const selectContent = (cPage) => {
	const boardSelect = $("#animal option:selected").val();
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
				console.log($(e.no));
				let img = "";
				let commentCount = "";
				if(e.attachCount>0){
					img = "<img src=\"<%=request.getContextPath() %>/images/file.png\"  width=\"16px\"/>";
				}
				if(e.commentCount>0){
					console.log(e.commentCount);
					commentCount = '('+e.commentCount+')';
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
					<td>\${e.regDate}</td>
				</tr>`
				console.log($("#boardList tbody"));
				$("#boardList tbody").append(tr);
				
			})
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