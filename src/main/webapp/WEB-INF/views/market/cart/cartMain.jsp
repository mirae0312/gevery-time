<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="com.zea.geverytime.market.cart.model.vo.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Cart> cartlist = (List<Cart>) request.getAttribute("cartlist");
%>	  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/market/cart/cart.css" />

</head>
<body>
	<h1>장바구니</h1>
	<input type="button" value="전체 선택/해제하기" id="checkAllBtn"/>
<%
	int countNum = 1;
	if(!cartlist.isEmpty()) {
%>
	<table id="cartTable">
		<thead>
			<tr>
				<th>선택</th>
				<th>상품번호</th>
				<th>게시글번호</th>
				<th>섬네일</th>
				<th>상품명</th>
				<th>제목</th>				
				<th>가격</th>
				<th>상품상태</th>
				<th>선택수량</th>
				<th>상품총가격</th>
				<th>삭제하기</th>
			</tr>
		</thead>

		<tbody>
		<%
		/* 별도의 리스트 별 총 가격을 계산하기 위해, countNum을 추가함 */
		for(Cart cart : cartlist) {
		%>
			<tr>
				<td>
					<input type="checkbox" id="pdtCheck<%= countNum %>" class="selectCheckBox" />
				</td>
				<td>
					<%= cart.getPdtBoard().getProduct().getPdtNo() %>
					<input type="hidden" id="pdtNo<%= countNum %>" value="<%= cart.getPdtBoard().getProduct().getPdtNo() %>"/>
				</td>
				<td>
					<%= cart.getProductboardNo() %>
					<input type="hidden" id="pdtBoardNo<%= countNum %>" value="<%= cart.getProductboardNo() %>" />
				</td>
				<td>
					<%
					List<Attachment> list = cart.getPdtBoard().getAttachments();
					String rfn = "";
					for(Attachment attach : list){
						rfn = attach.getRenamedFilename();
						break;
					}
					%>
					<img src="<%= request.getContextPath() %>/upload/market/productSale/<%= rfn %>" alt="" style="width:100px"/>
				</td>
				<td>
					<%= cart.getPdtBoard().getProduct().getPdtName() %>
				</td>
				<td>
					<a href="<%= request.getContextPath() %>/product/boardView?no=<%= cart.getProductboardNo() %>"><%= cart.getPdtBoard().getTitle() %></a>
					<input type="hidden" id="pdtTitle<%= countNum %>" value="<%= cart.getPdtBoard().getTitle() %>"/>	
				</td>				
				<td>
					<%= cart.getPdtBoard().getProduct().getPdtPrice() %>
					<input type="hidden" id="pdtPrice<%= countNum %>" value="<%= cart.getPdtBoard().getProduct().getPdtPrice() %>"/>
				</td>
				<td><%= cart.getPdtBoard().getProduct().getState() %></td>
				<td>
					<input type="number" name="count" class="pdtCount" id="pdtCount<%= countNum %>" value="1"/>
				</td>
				<td>
					<span id="pdtTotalVal<%= countNum %>"></span>
				</td>
				<td>
					<input type="button" value="삭제하기" class="deleteFromCart" id="delete<%= countNum %>"/>
				</td>			
			</tr>
		<%
		countNum++;
		}
		%>
			<tr>
				<td colspan=11>
					<b><span>총 가격은 </span>
					<span id="totalPricePlace"></span>
					<span>원 입니다.</span></b>
				</td>
			</tr>
		</tbody>
	</table>
	<!-- 합계 처리 위한 영역 -->
	<input type="hidden" id="countNum" value="<%= countNum - 1 %>"/>
	

	<!-- 전달할 form -->
	<form action="<%= request.getContextPath() %>/purchase/purchasePage" name="purchaseFrm" id="purchaseFrm" method="GET">
		<button>선택 상품 주문하기</button></br>
		<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>"/>
	</form>
<% } else { %>
	<h1>텅 비었어요</h1>
	<input type="button" value="쇼핑하러 가기" onclick="location.href='<%= request.getContextPath() %>/product/main?div=all'" />
<% } %>	
		
	<script>
		$("#checkAllBtn").click((e) => {
			if($(".selectCheckBox").prop("checked") == true){
				$(".selectCheckBox").prop("checked", false);				
			} else{
				$(".selectCheckBox").prop("checked", true);
			}
		});
		
		$(() => {
			priceCalculate();
		});
	
		// 금액 계산 함수
		const priceCalculate = () => {
			let countNum = $("#countNum").val();
			
			let TotalPrice = 0;
			for(let i = 1; i <= countNum; i++){
				let price = parseInt($(`#pdtPrice\${i}`).val());
				let count = $(`#pdtCount\${i}`).val();
				$(`#pdtTotalVal\${i}`).html(price*count);
				TotalPrice += price*count;
			}			
			$("#totalPricePlace").html(TotalPrice);
		};
		
		$(".pdtCount").change((e) => {
			if($(e.target).val() < 0){
				alert("0이하는 불가합니다.");
				$(e.target).val(0);
			}
			priceCalculate();
		});
		
		const appendSelectPdt = () => {
			let countNum = $("#countNum").val();
			
			let orderNum = 1;
			
			for(let i = 1; i <= countNum; i++){
				if($(`#pdtCheck\${i}`).prop("checked") == true){
					console.log("true");
					let no = $(`#pdtNo\${i}`).val();
					let boardNo = $(`#pdtBoardNo\${i}`).val();
					let price = parseInt($(`#pdtPrice\${i}`).val());
					let count = $(`#pdtCount\${i}`).val();
					let title = $(`#pdtTitle\${i}`).val();
					
					let inputNo = `<input type="hidden" name="pdtNo\${orderNum}" value="\${no}"/></br>`;
					let inputBoardNo = `<input type="hidden" name="pdtBoardNo\${orderNum}" value="\${boardNo}"/></br>`;
					let inputPrice = `<input type="hidden" name="pdtPrice\${orderNum}" value="\${price}"/></br>`;
					let inputCount = `<input type="hidden" name="pdtCount\${orderNum}" value="\${count}"></br>`;
					let inputTitle = `<input type="hidden" name="pdtTitle\${orderNum}" value="\${title}"></br>`;
					
					$("#purchaseFrm").append(inputNo);
					$("#purchaseFrm").append(inputBoardNo);
					$("#purchaseFrm").append(inputPrice);
					$("#purchaseFrm").append(inputCount);
					$("#purchaseFrm").append(inputTitle);
					console.log(orderNum);
					orderNum++;
				}
			}			
			let inputOrderCount = `<input type="hidden" name="CountNum" value="\${orderNum-1}" />`;
			if(orderNum == 1){
				alert("선택된 상품이 없습니다. 상품을 선택해주세요.");
				return false;
			}
			$("#purchaseFrm").append(inputOrderCount);
			return true;
		};
		
		const orderFrm = () => {
			const check = appendSelectPdt();
			if(check == true){
				confirm("결제페이지로 이동 하시겠습니까?");	
			} else{
				return false;
			}			
		};
		$(document.purchaseFrm).submit(orderFrm);
		
		
		const delCart = (boardNo) => {
 			$.ajax({
				url: "<%= request.getContextPath() %>/cart/deleteCart",
				method: "POST",
				data:{
						delCart1: boardNo,
						delCartLoginMember: "<%= loginMember.getMemberId() %>",
						delCartCountNum: "1"
				},
				success(data){
					console.log("장바구니 지워짐");
					reloadCart();
				},
				error: console.log
			});
		};
		
		$(".deleteFromCart").click((e) => {
			if(confirm("삭제하시겠습니까?")){
				let number = $(e.target).prop("id").substring(6);
				let boardNo = $(`#pdtBoardNo\${number}`).val();
				delCart(boardNo);				
			}
		});
		
		const reloadCart = () => {
			location.href="<%= request.getContextPath() %>/cart/main?memberId=<%= loginMember.getMemberId() %>";
		};
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>