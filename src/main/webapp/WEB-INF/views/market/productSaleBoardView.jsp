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
<% if(loginMember != null && !loginMember.getMemberId().equals(board.getSellerId())) { %>
<input type="button" id="reportBtn" value="신고하기" onclick="window.open('<%= request.getContextPath() %>/common/report?code=<%= (String)board.getOrCode() %>', 'popup', 'width=500, height=600, left=100')"/>
<% } %>
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
		
		<!-- 수정하기 / 삭제하기 : 작성자 로그인 시에만 보여짐 -->
		<% if(loginMember != null && loginMember.getMemberId().equals(board.getSellerId())) { %>
			<tr>
				<td><input type="button" value="수정하기" id="boardEdit" onclick="updateBoard();"/></td>
				<td>
					<form action="<%= request.getContextPath() %>/product/productBoardDelete" method="POST">
						<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
						<input type="submit" value="삭제하기"/>
					</form>
					
				</td>
			</tr>
		<% } %>
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
				<td><span id="saleState"><%= board.getProduct().getState() %></span></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><%= board.getProduct().getPdtPrice() %>원</td>
			</tr>
			<tr>
				<th>수량 선택</th>
				<td><input type="number" id="countOption" value="1"/></td>
			</tr>
			<!-- 구매 관련 영역 -->
			<% if(loginMember != null) {%>
			<tr>
				<td colspan=2>
					<!-- 바로 구매하기 form -->
					<form action="<%= request.getContextPath() %>/purchase/purchasePage" name="directPurchaseFrm">
						<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
						<input type="hidden" name="CountNum" value="1"/>
						<input type="hidden" name="pdtNo1" value="<%= board.getProduct().getPdtNo() %>"/>
						<input type="hidden" name="pdtBoardNo1" value="<%= board.getBoardNo() %>" />
						<input type="hidden" name="pdtPrice1" value="<%= board.getProduct().getPdtPrice() %>" />
						<input type="hidden" name="pdtCount1" id="directPurchaseCount" value="1" />
						<input type="hidden" name="pdtTitle1" value="<%= board.getTitle() %>" />
						<input type="submit" value="구매하기" />
					</form>
				</td>
			</tr>

			<tr>
				<td colspan=2>
					<!-- 장바구니 담기 form -->
					<form action="" name="addCartFrm">
						<input type="hidden" id="addCartId" name="addCartId" value="<%= loginMember.getMemberId() %>" />
						<input type="hidden" id="addCartBoardNo" name="addCartBoardNo" value="<%= board.getBoardNo() %>" />
						<input type="submit" value="장바구니 담기"/>
					</form>
				</td>
			</tr>
			<% } else { %>
			<tr>
				<td colspan=2><input type="button" value="구매하기" class="purchaseNeedLogin" /></td>
			</tr>
			<tr>
				<td colspan=2><input type="button" value="장바구니 담기" class="purchaseNeedLogin" /></td>
			</tr>
			<% } %>
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
				<% if(loginMember != null && loginMember.getMemberId().equals(board.getSellerId())) { %>
				<th>답변하기</th>
				<% } %>
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
						<!-- 작성자만 삭제버튼 노출되도록 함 -->
						<% if(loginMember != null && loginMember.getMemberId().equals(question.get("writer"))) { %>			
						<input type="submit" value="삭제하기" />
						<% } %>
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
		
		<!-- 로그인 유저가 작성자인 경우에만 답글 작성 가능하도록 함 -->
		<% if(loginMember != null && loginMember.getMemberId().equals(board.getSellerId())) { %>
				<td><input type="button" value="답글달기" class="reply"/></td>
			</tr>
			<tr class="replyTr" style="display:none;">
				<th>답글달기</th>
				<td colspan=2>
					<form action="<%= request.getContextPath() %>/product/answerEnroll" method="POST">
						<input type="hidden" name="awriter" value="<%= loginMember.getMemberId() %>" readonly /><br />
						<label for="acontent">내용</label><input type="text" name="acontent"/>
						<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
						<input type="hidden" name="commentNo" value="<%= (int)question.get("no") %>"/>
						<input type="submit" value="등록" />
					</form>
				</td>
			</tr>
		<% } %>
					
		<%	
					}
				}
			}
		%>
			<tr>
				<th>입력하기</th>
				<td colspan=2>
					<form action="<%= request.getContextPath() %>/product/questionEnroll" method="POST" name="commentEnrollFrm">
						<input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" readonly/><br />
						<label for="qtitle">제목</label><input type="text" name="qtitle"/>
						<label for="qcontent">내용</label><input type="text" name="qcontent"/>
						<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
						<input type="submit" value="등록" />
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	<br />
	<input type="button" value="목록으로 돌아가기" onclick="location.href='<%= request.getContextPath() %>/product/main'"/>
	
	<script>
		// 답글달기
		$(".reply").click((e) => {
			$(e.target).parent().parent().next().toggle(300, function(){
			});
		});	
		
		// 게시글 수정하기
		const updateBoard = () => {
			if(confirm("수정하시겠습니까?\n수정 시 기존에 등록된 이미지들은 모두 삭제됩니다.")){
				location.href = "<%= request.getContextPath() %>/product/productBoardUpdateForm?no=<%= board.getBoardNo() %>";
			}
		}
		
		// submit null 방지 : 미로그인 회원, 미입력 시
		$(document.commentEnrollFrm).submit((e) => {
			<% if(loginMember == null) {%>
				alert("로그인 후 이용 가능합니다.");
				return false;
			<% } %>
			if(!/^(.|\n)+$/.test($("[name=qtitle]").val())){
				alert("제목을 입력하세요");
				e.preventDefault();
			}else if (!/^(.|\n)+$/.test($("[name=qcontent]").val())){
				alert("내용을 입력하세요");
				e.preventDefault();
			};
		});
		
		// 비로그인 상태로 구매하기, 장바구니, 찜하기 클릭 시 얼럿
		$(".purchaseNeedLogin").click((e) => {
			alert("로그인 후 이용 가능합니다.");
		});
		
		// 장바구니 담기 비동기 처리
		$(document.addCartFrm).submit((e) =>{
			e.preventDefault();
			if($("#saleState").html() == "품절" || $("#saleState").html() == "판매중지"){
				alert("판매중인 상품이 아닙니다.");
				return false;
			};
			let reqmemberId = $("#addCartId").val();
			let reqboardNo = $("#addCartBoardNo").val();
			console.log(reqmemberId, reqboardNo);
			$.ajax({
				url : "<%= request.getContextPath() %>/cart/addCart",
				method : "POST",
				data : {
					memberId: reqmemberId,
					boardNo: reqboardNo
				},
				success(data){
					console.log(data);
					if(confirm(data.msg)){
						<% if(loginMember != null) { %>
						location.href="<%= request.getContextPath() %>/cart/main?memberId=<%= loginMember.getMemberId() %>";
						<% } %>
					}
				},
				error : console.log
			});
		});
		
		$("#countOption").change((e) =>{
			if($(e.target).val() < 1){
				alert("최소 수량은 1개 입니다.");
				$(e.target).val(1);
			};
			let countNum = $("#countOption").val();
			$("#directPurchaseCount").val(countNum);
		});
		
		$(document.directPurchaseFrm).submit((e) => {
			e.preventDefault();
			if($("#saleState").html() == "품절" || $("#saleState").html() == "판매중지"){
				alert("판매중인 상품이 아닙니다.");
				return false;
			}
			$(e.target).unbind();
			$(e.target).submit();
		})
		
		$(() => {
			// 신고 버튼 잠그기
			$.ajax({
				url: "<%= request.getContextPath() %>/common/reportCheck",
				data:{
					user: "<%= loginMember.getMemberId() %>",
					reportCode: "<%= board.getOrCode() %>"
				},
				success(data){
					if(data.result == 0){
						$("#reportBtn").prop("disabled", "disabled");
					}
				},
				error:console.log
			});
		})
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 
