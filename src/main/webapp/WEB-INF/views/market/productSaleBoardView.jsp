<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.market.productsale.model.vo.ProductBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	  
<%
	ProductBoard board = (ProductBoard) request.getAttribute("board");
	List<Map<String, Object>> questions = (List<Map<String, Object>>) request.getAttribute("questions");
	
	List<Attachment> attachments = board.getAttachments();
	
	String thumbnailImg = "";
	String firstImg = "";
	String secondImg = "";
	String thirdImg = "";
	int imgArr = 1;
	for(Attachment attach : attachments){
		if(imgArr == 1){
			thumbnailImg = (String) attach.getRenamedFilename();
			imgArr++;
		} else if(attach != null && imgArr == 2){
			firstImg = (String) attach.getRenamedFilename();
			imgArr++;
		} else if(attach != null && imgArr == 3) {
			secondImg = (String) attach.getRenamedFilename();
			imgArr++;
		} else if(attach != null && imgArr == 4){
			thirdImg = (String) attach.getRenamedFilename();
			imgArr++;
		}
		
	}
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세목록</title>
</head>
<body>
<script>
$(() => {
	const fimg = `<tr>
		<td>img1</td>
		<td><img src="<%= request.getContextPath() %>/upload/market/productSale/<%= firstImg %>" style="width:300px;"></td>
	</tr>`; 
	const simg = `<tr>
		<td>img2</td>
		<td><img src="<%= request.getContextPath() %>/upload/market/productSale/<%= secondImg %>" style="width:300px;"></td>
	</tr>`;			
	const timg = `<tr>
		<td>img3</td>
		<td><img src="<%= request.getContextPath() %>/upload/market/productSale/<%= thirdImg %>" style="width:300px;"></td>
	</tr>`;
	
	switch(<%= imgArr %>){
	case 2 : 
		$("#board tbody").append("이미지 없음");
		break;
	case 3 : 
		$("#board tbody").append(fimg);
		break;
	case 4 :
		$("#board tbody").append(fimg);
		$("#board tbody").append(simg);
		break;
	case 5 :
		$("#board tbody").append(fimg);
		$("#board tbody").append(simg);
		$("#board tbody").append(timg);
		break;
	};
})
</script>
	<table id="board">
		<thead>
			<tr>
				<th>판매게시글</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="button" value="수정하기" id="boardEdit" onclick="updateBoard();"/></td>
			</tr>
			<tr>
				<th>섬네일</th>
				<td><img src="<%= request.getContextPath() %>/upload/market/productSale/<%= thumbnailImg %>" style="width:300px;"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%= board.getTitle() %></td>
			<tr>
				<th>작성자</th>
				<td><%= board.getSellerId() %></td>
			</tr>
			<tr>
				<th>판매상태</th>
				<td><%= board.getProduct().getState() %></td>
			</tr>
			<tr>
				<th colspan=2>내용</th>
			</tr>
			<tr>
				<td colspan=2><%= board.getContent() %></td>
			</tr>
			<tr>
				<th colspan=2>이미지</th>
			</tr>
		</tbody>	
	</table>
	
	<table>
		<thead>
			<tr>
				<th colspan=3>문의글목록</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>답변하기</th>
			</tr>
		<%
			int listNum = 1;
			for(Map<String, Object> question : questions) {
				if((int)question.get("qaLevel") == 1) {
		%>
		
			<tr>
				<td><%= listNum %>번</td>
				<td><%= question.get("title") %></td>
				<td><%= question.get("content") %></td>
				<td><%= question.get("writer") %></td>
				<td>
					<form action="<%= request.getContextPath() %>/product/qaDelete" method="POST">
						<input type="hidden" name="delCommentNo" value="<%= question.get("no") %>" />	
						<input type="hidden" name="delCommentBoardNo" value="<%= board.getBoardNo() %>" />				
						<input type="submit" value="삭제하기" />
					</form>
				</td>
					
		<%
					int checkNum = 0;
					for(Map<String, Object> answer : questions) {
						if((int) answer.get("refNo") == (int)question.get("no")){
							checkNum = -1;
		%>
				<td></td>
			</tr>
			<tr>
							<td>(답글)</td>
							<td><%= answer.get("title") %></td>
							<td><%= answer.get("content") %></td>
							<td><%= answer.get("writer") %></td>
							<td>
								<form action="<%= request.getContextPath() %>/product/qaDelete" method="POST">
									<input type="hidden" name="delCommentNo" value="<%= answer.get("no") %>" />
									<input type="hidden" name="delCommentBoardNo" value="<%= board.getBoardNo() %>" />					
									<input type="submit" value="삭제하기" />
								</form>
							</td>
			</tr>
		<%
							break;
						}
					}
					listNum++;
					if(checkNum == 0){
		%>
				<td><input type="button" value="답글달기" class="reply"/></td>
			</tr>
			<tr class="replyTr" style="display:none;">
				<th>답글달기</th>
				<td colspan=2>
					<form action="<%= request.getContextPath() %>/product/answerEnroll" method="POST">
						<label for="awriter">작성자</label><input type="text" name="awriter"/><br />
						<label for="acontent">내용</label><input type="text" name="acontent"/><br />
						<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
						<input type="hidden" name="commentNo" value="<%= (int)question.get("no") %>"/>
						<input type="submit" value="등록" />
					</form>
				</td>
			</tr>
		<%	
					}
				}
			}
		%>
			<tr>
				<th>입력하기</th>
				<td colspan=2>
					<form action="<%= request.getContextPath() %>/product/questionEnroll" method="POST">
						<label for="writer">작성자</label><input type="text" name="writer"/><br />
						<label for="qtitle">제목</label><input type="text" name="qtitle"/>
						<label for="qcontent">내용</label><input type="text" name="qcontent"/><br />
						<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
						<input type="submit" value="등록" />
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	<script>
		// 답글달기
		$(".reply").click((e) => {
			$(e.target).parent().parent().next().toggle(300, function(){
			});
		});	
		
		// 게시글 수정하기
		const updateBoard = () => {
			if(confirm("수정하시겠습니까?")){
				location.href = "<%= request.getContextPath() %>/product/productBoardUpdate?no=<%= board.getBoardNo() %>";
			}
		}
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 
