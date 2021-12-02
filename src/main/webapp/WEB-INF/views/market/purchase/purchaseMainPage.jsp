<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매페이지</title>
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<h1>결제페이지</h1>
	<table>
		<thead>
			<tr>
				<th>상품번호</th>
				<th>게시글제목</th>
				<th>가격</th>
				<th>선택수량</th>
			</tr>
		</thead>
		<tbody>
		<%
		int countNum = 1;
		for(Map<String, Object> pdt : list) {
		%>
			<tr>
				<td>
					<%= pdt.get("no") %>
					<input type="hidden" id="pdtNo<%= countNum %>" value="<%= pdt.get("no") %>"/>
				</td>
				<td>
					<%= pdt.get("title") %>
					<input type="hidden" id="pdtTitle<%= countNum %>" value="<%= pdt.get("title") %>" />
				</td>
				<td>
					<%= pdt.get("price") %>
					<input type="hidden" id="pdtPrice<%= countNum %>" value="<%= pdt.get("price") %>" />
				</td>
				<td>
					<%= pdt.get("count") %>
					<input type="hidden" id="pdtCount<%= countNum %>" value="<%= pdt.get("count") %>" />
				</td>
			</tr>
		<%
		countNum++;
		}
		%>
			<tr>
				<td>
				 <span>결제 금액은</span>
				 <span id="totalPricePlace"></span>
				 <span>원 입니다.</span>
				</td>
			</tr>
		</tbody>
	</table>
	
	<button onclick="requestPay()">결제하기</button>
	
	<input type="hidden" id="countNum" value="<%= countNum - 1 %>"/>
	
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
			console.log(price, count, TotalPrice);
		}			
		$("#totalPricePlace").html(TotalPrice);
	};
	
	// 카카오페이 연동
	var IMP = window.IMP; 
	IMP.init("imp00307901");
	
    function requestPay() {
    	const date = new Date();
		let day = String(date.getDate());
		let hours = String(date.getHours());
		let minute = String(date.getMinutes());
		let second = String(date.getSeconds());
		
		const totalPrice = $("#totalPricePlace").html();
		console.log(totalPrice);
		
        // IMP.request_pay(param, callback) 결제창 호출
        IMP.request_pay({ // param
            pg: "kakaopay",
            pay_method: "card",
            merchant_uid: "<%= loginMember.getMemberId() %>" + day+hours+minute+second,
            name: "geverytime결제",
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
	
	const purchaseDone = (a, b, c) => {
		console.log(a, b, c);
		location.href=`<%= request.getContextPath() %>/purchase/Complete?uid=\${a}&muid=\${b}&amount=\${c}`;
	};
	
	
</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>