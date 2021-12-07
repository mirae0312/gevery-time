<%@page import="java.util.Map"%>
<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<%
	UsedGoodsBoard board = (UsedGoodsBoard) request.getAttribute("board");
	List<Attachment> attachments = (List<Attachment>) board.getAttachments();
	String state = (String) request.getAttribute("state");
	List<Map<String, Object>> reqUsers = (List<Map<String, Object>>) request.getAttribute("reqUsers");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고거래 상품 상세보기</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/market/ugGoods/ugGoodsView.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<% if(loginMember != null && !loginMember.getMemberId().equals(board.getWriter())) { %>
	<input type="button" id="reportBtn" value="신고하기" onclick="window.open('<%= request.getContextPath() %>/common/report?code=<%= board.getOrCode() %>', 'popup', 'width=500, height=600, left=100')"/>
<% } %>
	<h1>물품 상세보기</h1>
	<!-- 작성자에게만 수정/삭제 버튼이 노출되도록 함 -->
	<% if(loginMember != null && loginMember.getMemberId().equals(board.getWriter())) { %>
	<input type="button" value="수정하기" id="boardUpdate"/>
	<input type="button" value="삭제하기" id="boardDelete"/>
	<form action="<%= request.getContextPath() %>/ugGoods/deleteBoard" name="boardDeleteFrm" method="POST">
		<input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
	</form>
	<% } %>
	<table id="goodsInfoTable">
		<thead>
			<tr><th colspan=2><%= board.getTitle() %></th></tr>
		</thead>
		<tbody>
			<tr>
				<th>글 번호</th>
				<td><%= board.getNo() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= board.getWriter() %></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><%= board.getPrice() %>원</td>
			</tr>
			
			<!-- 상태 -->
			<tr>
				<th>상태</th>
				<%
				String stateStatus = "";
				if(state.equals("판매중")){
					stateStatus = "OnSale";
				} else if(state.equals("거래중")){
					stateStatus = "OnTrade";
				} else if(state.equals("결제완료")){
					stateStatus = "PurchaseDone";
				} else {
					stateStatus = "TradeDone";
				}
				%>
				<td><span class="showState" id="state-<%= stateStatus %>"><%= state %></span></td>
			</tr>
		</tbody>
	</table>
	
	<table id="trade-area">
		<tbody>			
			<!-- 찜하기 -->
			<% if(loginMember != null && !loginMember.getMemberId().equals(board.getWriter()) && !state.equals("판매완료")) { %>
			<tr>
				<td>
					<input type="hidden" id="wishListId" name="memberId" value="<%= loginMember.getMemberId() %>" />
					<input type="hidden" id="wishListNo" name="boardNo" value="<%= board.getNo() %>" />
					<input type="button" id="wishListAdd" value="찜하기" />
				</td>
			<% } %>
			
		<% if(state.equals("판매중")) {%>
			<!-- 구매자 > 구매요청 : value는 비동기로 처리 -->
			<% if(loginMember != null && !loginMember.getMemberId().equals(board.getWriter())) { %>
				<td>
					<div id="requestArea">
						<input type="button" value="구매요청" id="requestBtn"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan=2>
						<span id="userReq" style="display:none">
							<input type="text" id="reqContent" placeholder="요청사항을 입력하세요"/>
							<input type="button" value="보내기" id="requestSubmit"/>
						</span>
				
				</td>
			</tr>
			<%
				}
		} else if(state.equals("거래중")) {
		%>
		<%
				int selCheckNum = 0;
				if(loginMember != null && !loginMember.getMemberId().equals(board.getWriter())) {
					for(Map<String, Object> user : reqUsers){
						String userId = (String) user.get("user");
						String selected = (String) user.get("selected");
						System.out.println("userId, selected : "+userId+selected);
						if(userId.equals(loginMember.getMemberId()) && selected.equals("선택")){
							selCheckNum++;
							System.out.println("selCheckNum++");
						}
					}
					if(selCheckNum == 1){
		%>
						<!-- <tr> -->
							<td>
							<!-- 카카오페이를 통해 결제 진행 -->
							<form action="<%= request.getContextPath() %>/ugGoods/afterPurchase" name="ugGoodsPurchaseFrm">
								<input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
							</form>
							<button id="purchaseBtn" onclick="ugGoodsPurchase()">결제 진행</button>
							</td>
						</tr>
		<%
					} else {
		%>
						<!-- <tr>
							<td>
								<input type="button" value="거래 중" />
							</td>
						</tr>	 -->					
		<%							
					}
			}
		} else if(state.equals("결제완료")) {
		%>
					<%
				int selCheckNum = 0;
				if(loginMember != null && !loginMember.getMemberId().equals(board.getWriter())) {
					for(Map<String, Object> user : reqUsers){
						String userId = (String) user.get("user");
						String selected = (String) user.get("selected");
						System.out.println("userId, selected : "+userId+selected);
						if(userId.equals(loginMember.getMemberId()) && selected.equals("선택")){
							selCheckNum++;
							System.out.println("selCheckNum++");
						}
					}
					if(selCheckNum == 1){
		%>
<!-- 						<tr> -->
							<td>
							<input type="button" id="pcDoneBtn" value="결제가 완료되었습니다." disabled="disabled" />
							</td>
						</tr>
		<%
					} else {
		%>
						<tr>
							<td>
								<input type="button" value="결제 진행중인 건 입니다." />
							</td>
						</tr>						
		<%							
					}
			}
		%>		
		<%
		}
		%>
		
		
			
			<!-- 판매자 > 요청확인 : 비동기로 처리  -->
			<% if(loginMember != null && loginMember.getMemberId().equals(board.getWriter())) { %>
				<% if(!reqUsers.isEmpty() && state.equals("판매중")) { %>
					<tr>
						<th colspan=3 id="notiMessage">* 요청중인 건이 있습니다. 아래 버튼을 눌러서 확인하세요. *</th>
					</tr>
					<tr>
						<td colspan=3>
							<input type="button" value="요청 확인" id="responseBtn"/>
						</td>
					</tr>
				<%
				for(Map<String, Object> user : reqUsers) {
					if(user.get("selected").equals("미선택")) {
				%>
					<tr class="getReqContent" style="display:none">
						<td>
							<div class="userReqConent">
								<span>[<%= user.get("user") %>] : </span>						
							</div>
						</td>
						<td>
							<span><%= user.get("content") %></span>
						</td>
 						<td>
							<form action="<%= request.getContextPath() %>/ugGoods/tradeReqAccept" name="reqAccetpFrm" method="POST">
								<input type="submit" value="선택하기" class="selectReqUser"/>
								<input type="hidden" name="userId" value="<%= user.get("user") %>"/>
								<input type="hidden" name="thisBoardNo" value="<%= board.getNo() %>" />
								<br />
							</form> 						
						</td>						
					</tr>
					<%		
						}
					}
				} else if(!reqUsers.isEmpty() && state.equals("거래중")) {
				%>
					<tr>
						<th id="notiMessage">[구매자의 결제가 진행중입니다.]</th>
					</tr>
			<%
				} else if(!reqUsers.isEmpty() && state.equals("결제완료")){
			%>
					<tr>
						<td>
							<input type="button" value="판매 완료 처리" id="tradeDone"/>
						</td>
					</tr>
			<%		
				} else if(!reqUsers.isEmpty() && state.equals("판매완료")){
			%>
					<tr>
						<td colspan=2>
							<span id="notiMessage">판매가 완료 되었습니다! 배송 스팟에 상품을 놓아주세요.</span>
						</td>
					</tr>
			<%					
				}
			}
			%>
			</tbody>
	</table>
	
	
	<table id="img-area">
		<tbody>	
			<tr>
				<th class="board-content">이미지</th>
			</tr>
		<%
		int imgNum = 1;
		for(Attachment attach : attachments) {
		%>
			<tr>
				<td>
					<img src="<%= request.getContextPath() %>/upload/market/UgSale/<%= attach.getRenamedFilename() %>" alt="" />
				</td>			
			</tr>	
		<%
		imgNum++;
		}
		%>
		</tbody>
	</table>
	
	<table id="content-area">
		<tbody>	
			<tr>
				<th class="board-content">내용</th>			
			</tr>
			<tr>
				<td><%= board.getContent() %></td>
			</tr>
		</tbody>
	</table>
	
	<br />
	<input type="button" id="returnList" value="목록으로 돌아가기" onclick="location.href='<%= request.getContextPath() %>/ugGoods/main'"/>
	
	<script>
		$("#boardUpdate").click((e) => {
			location.href="<%= request.getContextPath() %>/ugGoods/boardUpdateForm?boardNo=<%= board.getNo() %>";
		});
		$("#boardDelete").click((e) => {
			$(document.boardDeleteFrm).submit();
		});
		
		<% if(loginMember != null) { %>		
		$(() => {
			// request 영역 onload 함수
			let checkNum = 0;
			<%
			if(!reqUsers.isEmpty()) {
				for(Map<String, Object> user : reqUsers) {
					if(user.get("user").equals(loginMember.getMemberId())){
			%>
					checkNum = checkNum + 1;
			<%
					}
				}
			}
			%>
			if(checkNum == 1){
				$("#requestBtn").val('구매요청중');
				$("#requestBtn").prop("disabled", 'disabled');
			}			
			
			<% if(loginMember != null) { %>
			// 신고하기 버튼 잠그기
			$.ajax({
				url: "<%= request.getContextPath() %>/common/reportCheck",
				data:{
					user: "<%= loginMember.getMemberId() %>",
					reportCode: "<%= board.getOrCode() %>"
				},
				success(data){
					console.log(data);
					if(data.result == 0){
						$("#reportBtn").prop("disabled", "disabled");
					}
				},
				error:console.log
			});
			
			// 찜하기 버튼 잠그기
			$.ajax({
				url: "<%= request.getContextPath() %>/wishList/check",
				data:{
					memberId: "<%= loginMember.getMemberId() %>",
					boardNo: "<%= board.getNo() %>"
				},
				success(data){
					console.log(data);
					console.log(data.result);
					if(data.result == 0){
						$("#wishListAdd").prop("disabled", "disabled");
					}
				},
				error: console.log
			});
			<% } %>
		});
		
		// [구매자] 요청 클릭 시 폼 노출
 		$("#requestBtn").click((e) => {
			if("<%= state %>" == "판매중"){
				/* $("#userReq").css("display", ""); */
				$("#userReq").toggle(100);
			}
		});
		
		// [판매자] 요청 확인 시 요청목록 노출
		$("#responseBtn").click((e) => {
			/* $(".getReqContent").css("display", ""); */
			$(".getReqContent").toggle(100);
		})
		
		// [거래상태] 구매요청 수락 시 state 변경
		const changeState = () => {
			$.ajax({
				url: "<%= request.getContextPath() %>/ugGoods/changeBoardState",
				method: "POST",
				data: {
					boardNo: "<%= board.getNo() %>"
				},
				success(data){
					console.log(data);
				},
				error:console.log
			});
		};
		

		// [구매자] 구매요청 등록
		$("#requestSubmit").click((e) =>{
			$.ajax({
				url: "<%= request.getContextPath() %>/ugGoods/requestAdd",
				method: "POST",
				data:{
					boardNo: "<%= board.getNo() %>",
					memberId: "<%= loginMember.getMemberId() %>",
					content: $("#reqContent").val()
				},
				success(data){
					alert(data.msg);
					$("#userReq").css("display", "none");
					$("#requestBtn").val('구매요청중');
					$("#requestBtn").prop("disabled", 'disabled');
				},
				error:console.log
			});
		});

		
		// [구매자] 결제 후 처리
		const ugGoodsPurchase = () => {
			requestPay();
		};
		
		// [판매자] 판매완료 처리
		$("#tradeDone").click((e) => {
			tradeDone();
		});
		
		// 카카오페이 연동
		var IMP = window.IMP; 
		IMP.init("imp00307901");
		
	    function requestPay() {
	    	const date = new Date();
			let day = String(date.getDate());
			let hours = String(date.getHours());
			let minute = String(date.getMinutes());
			let second = String(date.getSeconds());
			
			const totalPrice = <%= board.getPrice() %>
			
	        // IMP.request_pay(param, callback) 결제창 호출
	        IMP.request_pay({ // param
	            pg: "kakaopay",
	            pay_method: "card",
	            merchant_uid: "<%= loginMember.getMemberId() %>" + day+hours+minute+second,
	            name: "geverytime 중고거래 결제",
	            amount: totalPrice,
	            buyer_email: "<%= loginMember.getEmail() %>",
	            buyer_name: "<%= loginMember.getMemberName() %>",
	            buyer_tel: "<%= loginMember.getPhone() %>",
	            buyer_addr: "<%= loginMember.getAddress() %>",
	            buyer_postcode: "010101"
	        }, function (rsp) { // callback
	            if (rsp.success) {
	            	const impUid = rsp.imp_uid;
	            	const merchantUid = rsp.merchant_uid;
	            	let amount = totalPrice;
	            	$.ajax({
	            		url: "<%= request.getContextPath() %>/purchase/payments",
	            		method: "POST",
	            		dataType: "json",
	            		data: {
	            			imp_uid: impUid,
		            		merchant_uid: merchantUid,
		            		amountToBePaid: amount
	            		},
	            		success(data){
	            			console.log(data.msg);
	            			
	            			var msg = data.msg;
	                    	msg += "\n구매자 : " + "<%= loginMember.getMemberName() %>";
	                    	msg += "고유ID : " + rsp.imp_uid;
	                    	msg += "상점 거래 ID : " + rsp.merchant_uid;
	                    	msg += "결제 금액 : " + rsp.paid_amount;
	                        // 결제 성공 시 로직,
	                        addPurchaseHistory("<%= loginMember.getMemberId() %>", totalPrice, rsp.imp_uid, rsp.merchant_uid);
	                        deliverState();
	                        addSellerPoint('I', "<%= board.getOrCode() %>", rsp.imp_uid);
	                        alert(msg);
	            			purchaseDone(rsp.imp_uid, rsp.merchant_uid, rsp.paid_amount);
	            		},
	            		error : console.log
	            	});
	            } else {
	                alert("결제 실패")
	                // 결제 실패 시 로직, 
	            }
	        });
	      }
	    
	    const deliverState = () => {
	    	const reqboardNo = <%= board.getNo() %>;
	    	$.ajax({
	    		url: "<%= request.getContextPath() %>/ugGoods/boardStateChange",
	    		method: "POST",
	    		data: {
	    			boardNo: reqboardNo,
	    			state: "결제완료"
	    		},
	    		success(data){
	    			console.log(data);
	    		},
	    		error: console.log
	    	});
	    };
	    
	    const tradeDone = () => {
	    	if(confirm("판매완료 처리하시겠습니까?")){
		    	const reqboardNo = <%= board.getNo() %>;
		    	$.ajax({
		    		url: "<%= request.getContextPath() %>/ugGoods/boardStateChange",
		    		method: "POST",
		    		data: {
		    			boardNo: reqboardNo,
		    			state: "판매완료"
		    		},
		    		success(data){
		    			console.log(data);
		    			location.reload();
		    		},
		    		error: console.log
		    	});	    		
	    	};
	    };
	    
	    const addSellerPoint = (reqDiv, reqHistory, reqUid) => {
	    	const reqsellerId = "<%= board.getWriter() %>";
	    	const reqpointVal = "<%= board.getPrice() %>";
	    	$.ajax({
	    		url: "<%= request.getContextPath() %>/point/addPointHistory",
	    		method: "POST",
	    		data: {
	    			memberId: reqsellerId,
	    			pointVal: reqpointVal,
	    			div : reqDiv,
	    			history : reqHistory,
	    			purchaseUid : reqUid
	    		},
	    		success(data){
	    			console.log(data);
	    		},
	    		error: console.log
	    	});
	    };
	    
		const purchaseDone = (a, b, c) => {
			location.href=`<%= request.getContextPath() %>/purchase/Complete?uid=\${a}&muid=\${b}&amount=\${c}`;
		};
		
		const addPurchaseHistory = (reqMemberId, reqTotPrice, reqUid, reqMuid) => {
			// (회원 id, 총 가격, uid, muid), 상품 번호, 상품 개수
			$.ajax({
				url: "<%= request.getContextPath() %>/purchase/addHistory",
				method: "POST",
				data:{
					pdtNo1: "1",
					pdtCount1: "0",
					memberId: reqMemberId,
					totalPrice: reqTotPrice,
					uid: reqUid,
					muid: reqMuid,
					countNum: "2",
					state: "결제"
				},
				success(data){
					console.log(data);
				},
				error: console.log
			})
		};
		
		const wishListAdd = () => {
			const memberId = $("#wishListId").val();
			const boardNo = $("#wishListNo").val();
			$.ajax({
				url: "<%= request.getContextPath() %>/wishList/add",
				data:{
					memberId,
					boardNo
				},
				success(data){
					if(data.result == 1)
						location.reload();
				},
				error: console.log
			})
		}		
		
		$("#wishListAdd").click((e) => {
			wishListAdd();
		});
		<% } %>
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 