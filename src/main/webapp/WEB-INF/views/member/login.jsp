<%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	
	Member loginMember = (Member) session.getAttribute("loginMember");
	
	Cookie[] cookies = request.getCookies();
	String saveMemberId = null;
	if(cookies != null){
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = cookie.getValue();
			System.out.println(name + " = " + value);
			if("saveId".equals(name)){
				saveMemberId = value;
			}
		}
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/login.css" />
<body>
<script>
$(() => {

	<% if(msg != null){ %>	

		alert("<%= msg %>");
		
	<% } %>


	<% if(loginMember == null){ %>

		/**
		 * 로그인폼 유효성 검사
		 */
		$(loginFrm).submit((e) => {
			const $memberId = $(memberId);
			const $password = $(password);
			
			if(!/^\w{4,}$/.test($memberId.val())){
				alert("유효한 아이디를 입력하세요.");
				$memberId.select();
				return false;
			}
			if(!/^.{4,}$/.test($password.val())){
				alert("유효한 비밀번호를 입력하세요.");
				$password.select();
				return false;
			}
		});
		
	<% } %>

	});
</script>
    <div class="inner_login">
        <div class="login_tistory">
    
            <form method="post" id="authForm" action="<%= request.getContextPath() %>/member/login">
                <input type="hidden" name="redirectUrl" value="">
                <fieldset>
                <legend class="screen_out">로그인 정보 입력폼</legend>
			<% if(loginMember == null){ %>
                <div class="box_login">
                    <div class="inp_text">
                    <label for="loginId" class="screen_out">아이디</label>
                    <input type="text " id="memberId" name="memberId" placeholder="ID" >
                    </div>
                    <div class="inp_text">
                    <label for="loginPw" class="screen_out">비밀번호</label>
                    <input type="password" id="password" name="password" placeholder="Password" >
                    </div>
                </div>
                <button type="submit" class="btn_login"  >로그인</button>
                <div class="login_append">
                    <div class="inp_chk"> 
                   
            <span class="enroll_"> <a href= "<%=request.getContextPath()%>/member/memberEnroll">회원가입</a></span>
           <%} %>
           	<input type="checkbox" name="saveId" id="saveId" <%= saveMemberId != null ? "checked" : "" %>/>
									<label for="saveId">아이디저장</label>&nbsp;&nbsp;
            </label>
                    </div>
                    <span class="txt_find">
                    <a href="/member/find/loginId" class="link_find">아이디</a>
                        /
                    <a href="/member/find/password" class="link_find">비밀번호 찾기</a>
                    </span>
                </div>
                
                </fieldset>
            </form>
            
        </div>
    </div>

</body>
</html>