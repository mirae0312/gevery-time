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
	<table id="tbl-board-view">
		<tr class="board-view-title">
			<td><%= board.getTitle() %></td>
		</tr>
		<tr class="board-view-writer">
			<td><%= board.getWriter() %></td>
		</tr>
		
<% 
	List<Attachment> attachments = board.getAttachments();
	if (attachments != null && !attachments.isEmpty()) {
		for(int i = 0; i < attachments.size(); i++){
			Attachment attach = attachments.get(i);
%>

		<tr class="board-view-attachment">
			<td style="padding-right:20px;">
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%= request.getContextPath() %>/board/fileDownload?no=<%= attach.getNo() %>"><%= attach.getOriginalFilename() %></a>
			</td>
		</tr>
<% 
		}
	} 
%>
		<tr class="board-view-content">
			<td>
				<%= board.getContent() %> 
			</td>
		</tr>
		<tr>
		<td>
		</td>
		</tr>
		<tr class = "like-and-report">
			<th colspan="2">
					<button class="btn-board-like board-btn">좋아요 <%=board.getLikeCount()%></button>		
				<button class="report board-btn" value="<%= board.getOrCode() %>">신고</button>
			</th>
		</tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
		<% 	if(
				loginMember != null && 
				(
				  loginMember.getMemberId().equals(board.getWriter())
				  || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
				)
			){ %>
		
		<tr class="delete-or-update">
			<td>
			<input type="button" value="수정하기" onclick="updateBoard()" class="board-btn delete-or-update-btn">
				<input type="button" value="삭제하기" onclick="deleteBoard()" class="board-btn delete-or-update-btn">
			
			</td>
			
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
                <button type="submit" id="btn-comment-enroll1" class="board-comment-btn comment-submit">등록</button>
            </form>
        </div>
		
		<!--table#tbl-comment-->
<% 
	List<BoardComment> commentList = (List<BoardComment>) request.getAttribute("comment"); 
	if(commentList != null && !commentList.isEmpty()){
%>
		<table id="tbl-comment" class="board-comment">
<%
		for(BoardComment bc : commentList){
			if(bc.getCommentLevel() == 1){
%>
			<tr class="level1">
				<td class="comment-content">
					<sub class="comment-writer"><%= bc.getWriter() %></sub>
					<sub class="comment-date"><%= bc.getRegDate() %></sub>
					<br />
					<br />

					<%-- 댓글내용 --%>
					<%= bc.getContent() %>
				</td>
				<td class="comment-btns">
				<button class="btn-comment-like board-comment-btn" value="<%= bc.getNo() %>">좋아요 <%=bc.getLikeCount()%></button>
					<button class="btn-reply board-comment-btn" value="<%= bc.getNo() %>">답글</button>
					<button class="report board-comment-btn" value="<%= bc.getOrCode() %>">신고</button>					
					<% if(loginMember!= null){	
						if(loginMember.getMemberId().equals(bc.getWriter())
								|| loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE)){
					%>
					<button class="btn-deleteComment board-comment-btn" value="<%= bc.getNo() %>" > 삭제</button>
					<%}} %>				</td>
			</tr>
<%
			} else {
%>
			<tr class="level2">
				<td class="comment-content">
					<sub class="comment-writer"><%= bc.getWriter() %></sub>
					<sub class="comment-date"><%= bc.getRegDate() %></sub>
					<br />
					<br />
					<%-- 대댓글내용 --%>
					<%= bc.getContent() %>
				</td>
				<td class="comment-btns">	
					<button class="btn-comment-like board-comment-btn" value="<%= bc.getNo() %>">좋아요 <%=bc.getLikeCount()%></button>
					<button class="report board-comment-btn" value="<%= bc.getOrCode() %>">신고</button>
					<% if(loginMember!= null){	
						if(loginMember.getMemberId().equals(bc.getWriter())
								|| loginMember.getMemberRole().equals(MemberService.ADMIN_ROLE)){
					%>
					<button class="btn-deleteComment board-comment-btn" value="<%= bc.getNo() %>"> 삭제</button>
					<%}} %>
				</td>
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
<h4 id="otherList"><%=board.getWriter() %>님의 게시물</h4>
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
	name="commentDelFrm"
	method="POST" 
	action="<%= request.getContextPath() %>/board/boardCommentDelete" >
	<input type="hidden" name="commentNo" value="" />
	<input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
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
//댓글삭제
$(".btn-deleteComment").click((e)=>{
	if(confirm("댓글을 삭제하시겠습니까?")){
		$(document.commentDelFrm)
			.children("[name=commentNo]").val($(e.target).val())
			.parent().submit();
	}
});
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
			<textarea name="content" cols="60" rows="3" style="resize:none; width:80%;"></textarea>
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
						commentCount = '<span class="comment-count">('+e.commentCount+')</span>';
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