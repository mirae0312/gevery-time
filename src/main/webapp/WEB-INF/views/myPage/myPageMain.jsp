<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="myPage-container">
	<ul class="myPageBar">
		<li id="memberInfo"><a href="#">내정보(개인)</a></li>
		<li id="businessInfo"><a href="#">내정보(사업자)</a></li>
		<li id="point"><a href="<%=request.getContextPath() %>/myPage/point">나의 Point</a></li>
		<li id="salesList"><a href="<%=request.getContextPath() %>/myPage/salesList">나의 판매내역</a></li>
		<li id="buyList"><a href="<%=request.getContextPath() %>/myPage/buyList">나의 구매내역</a></li>
		<li id="InfoPost"><a href="<%=request.getContextPath() %>/myPage/InfoPost">정보게시물 승인</a></li>
	</ul>
</div>
<session>
	<form id="memberUpdateFrm" method="POST">
		<table>
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value="<%= loginMember.getMemberName() %>"  required><br>
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" id="memberId" value="<%= loginMember.getMemberId() %>" readonly> <!-- 읽기만가능 -->
				</td>															<!-- 사용자가 입력했던 정보가져오기 -->	
			</tr>
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= loginMember.getEmail() %>"><br>
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
		</table>
        <input type="button" onclick="updateMember();" value="수정"/>
        <input type="button" onclick="updatePassword();" value="비밀번호변경"/>
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
	<form id="BusinessUpdateFrm" method="POST">
		<table>
			<tr>
				<th>사업자 번호</th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value="<%= loginMember.getMemberName() %>"  required><br>
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" id="memberId" value="<%= loginMember.getMemberId() %>" readonly> <!-- 읽기만가능 -->
				</td>															<!-- 사용자가 입력했던 정보가져오기 -->	
			</tr>
			<tr>
				<th>회사명</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= loginMember.getEmail() %>"><br>
				</td>
			</tr>
			<tr>
				<th>사업자 전화번호</th>
				<td>	
					<input type="tel" placeholder="(-제외)01012345678" name="phone" id="phone" maxlength="11" value="<%= loginMember.getPhone() %>" required><br>
				</td>
			</tr>
			<tr>
				<th>사업장 지역</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address" value="<%= loginMember.getAddress() %>"><br>
				</td>
			</tr>
		</table>
        <input type="button" onclick="updateMember();" value="수정"/>
        <input type="button" onclick="updatePassword();" value="비밀번호변경"/>
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
</session>
<!-- 회원탈퇴폼 -->
<form name="memberDelFrm" action="<%= request.getContextPath() %>/myPage/memberDelete" method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
</form>
<script>
//비밀번호 변경
const updatePassword = () => location.href = "<%= request.getContextPath() %>/myPage/updatePassword";

// 회원탈퇴
const deleteMember = () => {
	if(confirm("정말로 탈퇴하시겠습니까?")){
		$(memberUpdateFrm).submit();
	}
});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>