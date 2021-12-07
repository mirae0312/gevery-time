<%@page import="com.zea.geverytime.member.model.service.MemberService"%>
<%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@page import="com.zea.geverytime.member.model.vo.Business"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	
	Member loginMember = (Member) session.getAttribute("loginMember");
	
%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/summernote/summernote-lite.css">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/summernote-lite.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/lang/summernote-ko-KR.js"></script>

</head>
<body>
	<div class="go-up-btn">TOP</div>
	<select name="write" id="write-board">
		<option hidden="" selected disabled>게시글작성</option>
		<option value="/board/boardForm">자유게시판 글쓰기</option>
		<% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
		<option value="/info/Enroll">정보게시판 글쓰기</option>
		<option value="/product/boardForm">마켓 글쓰기</option>
		<option value="/ugGoods/boardForm">중고거래 글쓰기</option>
		<% } %>
	</select>
    <div id="container">
        <section id="header" >
           <div class="wrapper">
               <div class="title"><a href="<%=request.getContextPath() %>/">개브리타임</a></div>
               <ul class="lists" >
                   <li id="info"><a href="<%= request.getContextPath() %>/info/allList?board=<%= "info" %>">정보</a></li>
                   <li id="board"><a href="<%=request.getContextPath() %>/board/freeBoardList">게시판</a></li>
                   <li id="market"><a href="#">마켓</a></li>
               </ul>
               <ul>
                   <!--관리자페이지표시 안 보이게 할 때 아래 한 줄만 처리해야 흐트러지지 않음 -->
                 <%  if(loginMember != null && MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())){ %>
                   <li id="admin"><a href="<%= request.getContextPath() %>/admin/adminList?board=<%= "info" %>" style="color: red">관리자페이지</a></li>
               	<% } %>
               </ul>
               
             <nav>
               <ul class="navs">
				<%if(loginMember == null){ %>
               <li class="nav" id="login" ><a href="<%=request.getContextPath()%>/member/login">로그인</a></li>
              	<% } %>
               <% if(loginMember != null){ %>
               <li class="nav"><%= loginMember.getMemberName() %>님</li>
               <li class="nav" id="logout"><a href="<%= request.getContextPath() %>/member/logout">로그아웃</a></li>
               <li class="nav" id="mypage"><a href="<%= request.getContextPath() %>/myPage/myPageMain">마이페이지</a></li>	              
               <%}%>
               <li class="nav" id="dm"><a href="<%= request.getContextPath() %>/chat/chatroom">DM</a></li>				
               <li class="nav"id="qna"><a href="<%= request.getContextPath() %>/customer/qnaBoardList">고객센터</a></li>
               </ul>
            </nav>
           </div>
        </section>
        <div class="navbox-container">
	        <div class="info-navsbox">
	            <ul class="info-pagenavs">
	                <li class="info-pagenav" id="hospital"><a href="<%= request.getContextPath() %>/info/allList?board=<%= "hospital" %>">동물병원</a></li>
	                <li class="info-pagenav" id="cafe"><a href="<%= request.getContextPath() %>/info/allList?board=<%= "cafe" %>">카페</a></li>
	                <li class="info-pagenav" id="food"><a href="<%= request.getContextPath() %>/info/allList?board=<%= "restaurant" %>">음식점</a></li>
	                <li class="info-pagenav" id="pension"><a href="<%= request.getContextPath() %>/info/allList?board=<%= "pension" %>">펜션</a></li>
	                <li class="info-pagenav" id="salon"><a href="<%= request.getContextPath() %>/info/allList?board=<%= "salon" %>">미용실</a></li>
	            </ul>
	        </div> 
	        <div class="board-navsbox">
	            <ul class="board-pagenavs">
	                <li class="board-pagenav" id="board"><a href="<%=request.getContextPath() %>/board/freeBoardList">자유게시판</a></li>
	                <li class="board-pagenav" id="review"><a href="<%=request.getContextPath() %>/board/reviewBoardList">후기</a></li>
	            </ul>
	        </div> 
	        <div class="market-navsbox">
	            <ul class="market-pagenavs">
	                <!-- <li class="pagenav3" id="point"><a href="#">내 Point 확인</a></li> -->
	                <li class="market-pagenav" id="store"><a href="<%= request.getContextPath() %>/product/main?div=all">일반상점</a></li>
	                <li class="market-pagenav" id="used"><a href="<%= request.getContextPath() %>/ugGoods/main">중고 매매</a></li>
	                <% if(loginMember != null) { %>
	                <li class="market-pagenav" id="list"><a href="<%= request.getContextPath() %>/wishList/main?memberId=<%= loginMember.getMemberId() %>">찜 목록</a></li>
	                <li class="market-pagenav" id="cart"><a href="<%= request.getContextPath() %>/cart/main?memberId=<%= loginMember.getMemberId() %>">장바구니</a></li>
	                <% } %>
	                <% if(loginMember != null && loginMember.getMemberType().equals("B")) { %>
	                <li class="market-pagenav" id="product"><a href="<%= request.getContextPath() %>/product/onsaleProduct?sellerId=<%= loginMember.getMemberId() %>">상품 관리</a></li>
	                <% } %>
	            </ul>
	        </div>
        </div>
    </div>
    <section id="content">
    
    <script>
    // alert msg
    $(()=>{
    	<% if(msg != null && "로그인후 이용하세요".equals(msg)) {%>
				if(confirm("로그인이 필요한 서비스입니다. 로그인 페이지로 이동하시겠습니까?")){
					location.href="<%= request.getContextPath() %>/member/login";
				}
    	<% } else if(msg != null && !"로그인후 이용하세요".equals(msg)) { %>
    	        alert("<%= msg %>");    	
    	<% } %>
    });

    //중복코드제거(+이벤트버블링활용)
    $(".lists").mouseover((e) =>{
    	const id = $(e.target).parent().attr('id');
    	$(".navbox-container").children().hide();
        $(`.\${id}-navsbox`).show();
	});
    
    // 일반상점 이동
    $("#store").click((e) => {
    	location.href="<%= request.getContextPath() %>/product/main?div=%%";
    });
    
    // 상단 이동
    $(".go-up-btn").click((e) => {
    	$('html, body').animate({scrollTop:0}, 300);
    });
    
 	// 게시물 등록
 	$("#write-board").change((e) => {
 		const value = $("#write-board").val();
 		console.log(value);
 		location.href=`<%= request.getContextPath() %>\${value}`;
 	});
 	

	</script>