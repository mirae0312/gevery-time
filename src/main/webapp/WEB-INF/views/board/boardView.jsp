<%@page import="com.zea.geverytime.board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="com.zea.geverytime.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    
<%
	Board board = (Board)request.getAttribute("board");
	List<Board> other = (List<Board>)request.getAttribute("otherBoard");
%>
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board-view">
		<tr>
			<th>글번호</th>
			<td><%= board.getNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= board.getTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= board.getWriter() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= board.getReadCount() %></td>
		</tr>
		
<% 
	List<Attachment> attachments = board.getAttachments();
	if (attachments != null && !attachments.isEmpty()) {
		for(int i = 0; i < attachments.size(); i++){
			Attachment attach = attachments.get(i);
%>
		<tr>
			<th>첨부파일<%= i + 1 %></th>
			<td>
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%= request.getContextPath() %>/board/fileDownload?no=<%= attach.getNo() %>"><%= attach.getOriginalFilename() %></a>
			</td>
		</tr>
<% 
		}
	} 
%>
		<tr>
			<th>내 용</th>
			<td>
				<%= board.getContent() %> 
			</td>
		</tr>
		<tr>
		<td>
		<button class="btn-board-like">좋아요 <%=board.getLikeCount()%></button>		
		</td>
		</tr>
		<% 	if(
				loginMember != null && 
				(
				  loginMember.getMemberId().equals(board.getWriter())
				  || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
				)
			){ %>
		<tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<th colspan="2">
				<button class="report" value="<%= board.getOrCode() %>">신고</button>
				<input type="button" value="수정하기" onclick="updateBoard()">
				<input type="button" value="삭제하기" onclick="deleteBoard()">
			</th>
		</tr>
		<% 	} %>
	</table>
	
	<hr style="margin-top:30px;" />	
    
	<div class="comment-container">
        <div class="comment-editor">
            <form 
            	action="<%=request.getContextPath()%>/board/boardCommentEnroll" 
            	method="post" 
            	name="boardCommentFrm">
                <input type="hidden" name="orCode" value="<%= board.getOrCode().substring(0,3) %>c" />
                <input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
                <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
				<textarea name="content" cols="60" rows="3" style="resize:none; width:80%;"></textarea>
                <button type="submit" id="btn-comment-enroll1">등록</button>
            </form>
        </div>
		
		<!--table#tbl-comment-->
<% 
	List<BoardComment> commentList = (List<BoardComment>) request.getAttribute("comment"); 
	if(commentList != null && !commentList.isEmpty()){
%>
		<table id="tbl-comment">
<%
		for(BoardComment bc : commentList){
			if(bc.getCommentLevel() == 1){
%>
			<tr class="level1">
				<td>
					<sub class="comment-writer"><%= bc.getWriter() %></sub>
					<sub class="comment-date"><%= bc.getRegDate() %></sub>
					<br />
					<%-- 댓글내용 --%>
					<%= bc.getContent() %>
				</td>
				<td>
				<button class="btn-comment-like btn" value="<%= bc.getNo() %>">좋아요 <%=bc.getLikeCount()%></button>
					<button class="btn-reply btn" value="<%= bc.getNo() %>">답글</button>
					<button class="report btn" value="<%= bc.getOrCode() %>">신고</button>					
					<% if(loginMember!= null){	
						if(loginMember.getMemberId().equals(bc.getWriter())
								|| loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE)){
					%>
					<button class="btn-deleteComment btn" value="<%= bc.getNo() %>" style ="float:right"> 삭제</button>
					<%}} %>				</td>
			</tr>
<%
			} else {
%>
			<tr class="level2">
				<td>
					<sub class="comment-writer"><%= bc.getWriter() %></sub>
					<sub class="comment-date"><%= bc.getRegDate() %></sub>
					<br />
					<%-- 대댓글내용 --%>
					<%= bc.getContent() %>
					<button class="btn-comment-like btn" value="<%= bc.getNo() %>">좋아요 <%=bc.getLikeCount()%></button>
					<button class="report btn" value="<%= bc.getOrCode() %>">신고</button>
					<% if(loginMember!= null){	
						if(loginMember.getMemberId().equals(bc.getWriter())
								|| loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE)){
					%>
					<button class="btn-deleteComment btn" value="<%= bc.getNo() %>" style ="float:right"> 삭제</button>
					<%}} %>
				</td>
				<td></td>
			</tr>
<%
			}%>
		<% }
%>
		</table>
<%
	}
%>
		
	</div>
</section>
<section class="sameWriterOtherBoardList">
<h4 id="otherList"><%=board.getWriter() %>님의 다른 게시물</h4>
<table id="otherBoardList" class="board-list-table">
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
	<div class="board-pageBar"></div>
</section>

<form
	name="boardDelFrm"
	method="POST" 
	action="<%= request.getContextPath() %>/board/boardDelete" >
	<input type="hidden" name="no" value="<%= board.getNo() %>" />
</form>

<form
	name="boardLikeFrm"
	method="POST"
	action="<%= request.getContextPath() %>/board/boardLike">
	<input type="hidden" name="boardNo" value="<%=board.getNo()%>"/>
</form>
<form
	name="commentLikeFrm"
	method="POST"
	action="<%= request.getContextPath() %>/board/commentLike">
	<input type="hidden" name="boardNo" value=""/>
</form>
<script>
//신고
$(".report").click((e) => {
	const name = "report";
	const spec = "left=500px, top=500px, width=450px, height=650px";
	const popup = open(`<%= request.getContextPath() %>/common/report?code=\${$(e.target).val()}`, name, spec);
});
	//좋아요
	$(".btn-board-like").click((e)=>{
		<%if(loginMember == null){%>
			loginAlert();
			return;
		<%}%>
		$.ajax({
			url : "<%= request.getContextPath() %>/board/boardLike",
			method:"post",
			data : {
				no : <%=board.getNo()%>,
				id : "<%=loginMember.getMemberId()%>"
			},
			success(data){ // 해당 댓글의 좋아요 수
				console.log(data)
				if(data > -1){
					$(e.target).text('좋아요 '+data);
				}
			},
			error:console.log
		});
		
	});
	$(".btn-comment-like").click((e)=>{
		<%if(loginMember == null){%>
			loginAlert();
			return;
		<%}%>
		$.ajax({
			url : "<%= request.getContextPath() %>/board/commentLike",
			method:"post",
			data : {
				no : $(e.target).val(),
				id : "<%=loginMember.getMemberId()%>"
			},
			success(data){ // 해당 댓글의 좋아요 수
				console.log(data)
				if(data > -1){
					$(e.target).text('좋아요 '+data);
				}
			},
			error:console.log
		});
		
	});
	//댓글삭제
	$(".btn-deleteComment").click((e)=>{
		if(confirm("댓글을 삭제하시겠습니까?")){
			$(document.commentDelFrm)
				.children("[name=commentNo]").val($(e.target).val())
				.parent().submit();
		}
	});
	
	/**
	 * 대댓글 입력
	 */
	$(".btn-reply").click((e) => {
	<% if(loginMember == null){ %>
		loginAlert();
		return;
	<% } %>
	
		const commentRef = $(e.target).val();
		console.log(commentRef);
		
		const tr = `<tr>
		<td colspan="2" style="text-align:left">
			<form 
				action="<%=request.getContextPath()%>/board/boardCommentEnroll" 
				method="post">
            <input type="hidden" name="orCode" value="<%= board.getOrCode().substring(0,3) %>c" />
		    <input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
		    <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
		    <input type="hidden" name="commentLevel" value="2" />
		    <input type="hidden" name="commentRef" value="\${commentRef}" />    
			<textarea name="content" cols="60" rows="2"></textarea>
		    <button type="submit" class="btn-comment-enroll2">등록</button>
		</form>
		
		</td>
	</tr>`;
		console.log(tr);
		
		// e.target인 버튼태그의 부모tr을 찾고, 다음 형제요소로 추가
		const $baseTr = $(e.target).parent().parent();
		const $tr = $(tr);
		
		$tr.insertAfter($baseTr)
			.find("form")
			.submit((e) => {
				const $content = $("[name=content]", e.target);
				if(!/^(.|\n)+$/.test($content.val())){
					alert("댓글을 작성해주세요.");
					e.preventDefault();
				}
			});
		// 클릭이벤트핸들러 제거!
		$(e.target).off("click");
	});
	
	
	//댓글달기
	$("[name=content]", document.boardCommentFrm).focus((e) => {
	
	<% if(loginMember == null){ %>
		loginAlert();
		return;
	<% } %>
	
	});
	
	$(document.boardCommentFrm).submit((e) => {
	<% if(loginMember == null){ %>
		loginAlert();
	//	e.preventDefault();
	//	return;
		return false;
	<% } %>
	
		const $content = $("[name=content]", e.target);
		if(!/^(.|\n)+$/.test($content.val())){
			alert("댓글을 작성해주세요.");
			e.preventDefault();
		}
	
	});
	
	const loginAlert = () => {
		alert("로그인후 사용가능합니다.");
		$(memberId).focus();
	};
	
	
	const deleteBoard = () => {
		if(confirm("이 게시물을 정말 삭제하시겠습니까?")){
			$(document.boardDelFrm).submit();		
		}
	};
	
	const updateBoard = () => {
		location.href = "<%= request.getContextPath() %>/board/boardUpdate?no=<%= board.getNo() %>";
	};
	
	//글쓴이의 다른 게시글 목록
	const f = function(n){
        return n<10 ? `0\${n}`:n;
    }
	$(()=>{
		selectContent(1);
	});
	$(".board-pageBar").click((e)=>{
		selectContent($(e.target).data('page'));
	})
	const selectContent = (cPage) => {
		const writer = "<%=board.getWriter()%>";
		$.ajax({
			url:"<%=request.getContextPath()%>/board/otherBoardList",
			dataType:"json",
			data:{
				cPage,
				writer
			},
			success(data){
				console.log(data);
				$("#otherBoardList tbody").empty();
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
						<td>\${date}</td>
					</tr>`
					$("#otherBoardList tbody").append(tr);
					
				})
				//pageBar부분
				console.log(data.pagebar);
				$(".board-pageBar").empty();
				$(".board-pageBar").append(data.pagebar);
			},
			error:console.log
		});	
	};
	
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>