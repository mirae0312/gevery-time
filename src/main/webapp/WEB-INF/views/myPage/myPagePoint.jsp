<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="myPage-container">
    <ul class="myPageBar">
        <% if(loginMember != null && loginMember.getMemberType().equals("N")) { %>
        <li id="memberInfo"><a href="<%=request.getContextPath() %>/myPage/myPageMain">내정보(개인)</a></li>
        <% } %>
        <% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
        <li id="businessInfo"><a href="<%=request.getContextPath() %>/myPage/myPageMain">내정보(사업자)</a></li> 
        <% } %>
        <li id="point"><a href="<%=request.getContextPath() %>/myPage/myPagePoint">나의 Point</a></li>
        <li id="salesList"><a href="<%=request.getContextPath() %>/myPage/salesList">나의 판매내역</a></li>
        <li id="buyList"><a href="<%=request.getContextPath() %>/myPage/buyList">나의 구매내역</a></li>
        <% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
        <li id="InfoPost"><a href="<%=request.getContextPath() %>/myPage/InfoPost">정보게시물 승인</a></li>
        <% } %>
    </ul>
</div>
<h2>나의 Point</h2>
<div class="point-container">
    <ul>
        <li><a href="<%= request.getContextPath() %>/myPage/myPagePoint">포인트 정책 보기</a></li>
        <li><a href="#">상세내역</a></li>
        <li><a href="#">환전하기</a></li>
    </ul>
</div>
<div class="pointPolicy">
    <table>
        <thead>
            <tr>
                <th>게시글</th>
                <th>댓글</th>
                <th>리뷰</th>
                <th></th>
            </tr>
        </thead>
    </table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>