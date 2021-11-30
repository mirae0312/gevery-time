<%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	
	Member loginMember = (Member) session.getAttribute("loginMember");
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/login.css" />
<body>
<script>
$(() => {

	<% if(msg != null){ %>	

		alert("<%= msg %>");
		
	<% } %>

	<%-- 로그인하지 않은 경우만 출력 --%>
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
    
            <form method="post" id="authForm" action="">
                <input type="hidden" name="redirectUrl" value="">
                <fieldset>
                <legend class="screen_out">로그인 정보 입력폼</legend>
                <div class="box_login">
                    <div class="inp_text">
                    <label for="loginId" class="screen_out">아이디</label>
                    <input type="text " id="loginId" name="loginId" placeholder="ID" >
                    </div>
                    <div class="inp_text">
                    <label for="loginPw" class="screen_out">비밀번호</label>
                    <input type="password" id="loginPw" name="password" placeholder="Password" >
                    </div>
                </div>
                <button type="submit" class="btn_login"  >로그인</button>
                <div class="login_append">
                    <div class="inp_chk"> 
                   
            <span class="enroll_"> <a href="<%= request.getContextPath() %>/member/memberEnroll">회원가입</a></span>
           
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