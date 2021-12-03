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
</head>
<body>
	<h1>장바구니</h1>
<% if(!cartlist.isEmpty()) { %>
	<table>
		<thead>
			<tr>
				<th>선택</th>
				<th>상품 번호</th>
				<th>게시글 번호</th>
				<th>상품명</th>
				<th>제목</th>				
				<th>가격</th>
				<th>상품 상태</th>
				<th>선택 수량</th>
				<th>상품 총 가격</th>
			</tr>
		</thead>

		<tbody>
		<%
		/* 별도의 리스트 별 총 가격을 계산하기 위해, countNum을 추가함 */
		int countNum = 1;
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
					<%= cart.getPdtBoard().getProduct().getPdtName() %>
				</td>
				<td>
					<%= cart.getPdtBoard().getTitle() %>
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
			</tr>
		<%
		countNum++;
		}
		%>
			<tr>
				<td colspan=8>
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
		<button>주문하기</button></br>
		<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>"/>
	</form>
<% } else { %>
	<h3>텅 비었어요</h3>
	<input type="button" value="쇼핑하러 가기" onclick="location.href='<%= request.getContextPath() %>/product/main'" />
<% } %>	
	

		
	<script>
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
					
					let inputNo = `<input type="text" name="pdtNo\${orderNum}" value="\${no}"/></br>`;
					let inputBoardNo = `<input type="text" name="pdtBoardNo\${orderNum}" value="\${boardNo}"/></br>`;
					let inputPrice = `<input type="text" name="pdtPrice\${orderNum}" value="\${price}"/></br>`;
					let inputCount = `<input type="text" name="pdtCount\${orderNum}" value="\${count}"></br>`;
					let inputTitle = `<input type="text" name="pdtTitle\${orderNum}" value="\${title}"></br>`;
					
					$("#purchaseFrm").append(inputNo);
					$("#purchaseFrm").append(inputBoardNo);
					$("#purchaseFrm").append(inputPrice);
					$("#purchaseFrm").append(inputCount);
					$("#purchaseFrm").append(inputTitle);
					console.log(orderNum);
					orderNum++;
				}
			}			
			let inputOrderCount = `<input type="text" name="CountNum" value="\${orderNum-1}" />`;
			if(orderNum == 1){
				alert("선택된 상품이 없습니다. 상품을 담아주세요.");
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
				alert("정상적인 실행이 아닙니다.");
				return false;
			}			
		};
		$(document.purchaseFrm).submit(orderFrm);
		
	</script>

</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>