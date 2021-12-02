<%@page import="com.zea.geverytime.member.model.vo.Business"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Business loginBusiness = (Business) session.getAttribute("loginBusiness");
%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="myPage-container">
	<ul class="myPageBar">
		<% if(loginMember != null && loginMember.getMemberType().equals("N")) { %>
		<li id="memberInfo"><a href="#">내정보(개인)</a></li>
		<% } %>
		<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
		<li id="businessInfo"><a href="#">내정보(사업자)</a></li> 
		<% } %>
		<li id="point"><a href="<%=request.getContextPath() %>/myPage/myPagePoint">나의 Point</a></li>
		<li id="salesList"><a href="<%=request.getContextPath() %>/myPage/salesList">나의 판매내역</a></li>
		<li id="buyList"><a href="<%=request.getContextPath() %>/myPage/buyList">나의 구매내역</a></li>
		<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
		<li id="InfoPost"><a href="<%=request.getContextPath() %>/myPage/InfoPost">정보게시물 승인</a></li>
		<% } %>
	</ul>
</div>
<session>
 <% if(loginMember != null && loginMember.getMemberType().equals("N")) { %>
	<form id="memberUpdateFrm" method="POST">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" id="memberId" value="<%= loginMember.getMemberId() %>" readonly> <!-- 읽기만가능 -->
				</td>															<!-- 사용자가 입력했던 정보가져오기 -->	
			</tr>
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value="<%= loginMember.getMemberName() %>" readonly><br>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@gmail.com" name="email" id="email" value="<%= loginMember.getEmail() %>"><br>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>	
					<input type="tel" placeholder="(-제외)01012345678" name="phone" id="phone" maxlength="11" value="<%= loginMember.getPhone() %>" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address" value="<%= loginMember.getAddress() %>"><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="<%= loginMember.getBirthday() %>"><br>
				</td>
			</tr> 
		</table>
        <input type="button" onclick="updateMember();" value="수정"/>
        <input type="button" onclick="location.href='<%=request.getContextPath()%>/myPage/updatePassword'" value="비밀번호변경"/>
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
	<% } %>
	<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
	<form id="BusinessUpdateFrm" method="POST">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="businessId" id="businessId" value="<%= loginMember.getMemberId() %>" readonly> 
				</td>												
			</tr>
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value="<%= loginMember.getMemberName() %>" readonly><br>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@gmail.com" name="email" id="email" value="<%= loginMember.getEmail() %>"><br>
				</td>
			</tr>
			<tr>
				<th>사업자 번호</th>
				<td>	
				<input type="text"  name="businessNo" id="businessNo" value="<%= loginBusiness.getBusinessNo() %>" readonly><br>
				</td>
			</tr>
			<tr>
				<th>상호명</th>
				<td>	
					<input type="text" placeholder="회사명" name="bName" id="bName" value="<%= loginBusiness.getbName() %>"><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address" value="<%= loginMember.getAddress() %>"><br>
				</td>
			</tr>
			<tr>
				<th>지역</th>
				<td>	
					<input type="text" placeholder="" name="baddress" id="baddress" value="<%= loginBusiness.getbAddress() %>"><br>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>	
					<input type="tel" placeholder="(-제외)01012345678" name="phone" id="phone" maxlength="11" value="<%= loginBusiness.getbTel() %>" required><br>
				</td>
			</tr>
		</table>
        <input type="button" onclick="updateBusiness();" value="수정"/>
        <input type="button" onclick="location.href='<%=request.getContextPath()%>/myPage/updatePassword'"/>
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
	<% } %>
</session>
<!-- 회원탈퇴폼 -->
<form name="memberDelFrm" action="<%= request.getContextPath() %>/myPage/memberDelete" method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
</form>
<script>
// 회원수정

const updateMember = () => {
	// 폼의 action값 할당 후 제출
	//console.log("회원정보 수정");
	$(memberUpdateFrm)
		.attr("action", "<%= request.getContextPath() %>/myPage/memberUpdate")
		.submit();
};

//유효성검사
$(memberUpdateFrm).submit((e) => {
	
	//phone
	const $phone = $(phone);
	if(!/^010[0-9]{8}$/.test($phone.val())){
		alert("유효한 전화번호가 아닙니다.");
		return false;
	}
	return true;
});


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>