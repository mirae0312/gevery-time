<%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<script>
function check_input() {
    if (!document.authForm.id_val.value)
    // login_form 이름을 가진 form 안의 id_val 의 value가 없으면
    {
        alert("아이디를 입력하세요!");
        document.login_form.id_val.focus();
        // 화면 커서 이동
        return;
    }
    if (!document.login_form.pw_val.value)
    {
        alert("비밀번호를 입력하세요!");
        // 화면 커서 이동
        return;
    }
    document.authForm.submit();
    // 모두 확인 후 submit()
 }
</script>
<title>Insert title here</title>
</head>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/login.css" />
<body>

<section>
    <div class="inner_login">
        <div class="login_tistory">
    
            <form method="post" id="authForm" action="<%= request.getContextPath() %>/member/login">
                <input type="hidden" name="redirectUrl" value="">
                <fieldset>
                <legend class="screen_out">로그인 정보 입력폼</legend>
    
                <div class="box_login">
                    <div class="inp_text">
                    <label for="loginId" class="screen_out">아이디</label>
                    <input type="text" id="memberId" name="memberId" placeholder="ID" >
                    </div>
                    <div class="inp_text">
                    <label for="loginPw" class="screen_out">비밀번호</label>
                    <input type="password" id="password" name="password" placeholder="Password" >
                    </div>
                </div>
                <button type="submit" class="btn_login"  >로그인</button>
                <div class="login_append">
                    <div class="inp_chk"> 
                   
            <span class="enroll_"> <a href= "<%=request.getContextPath()%>/member/EnrollSplit">회원가입</a></span>
          
          
            </label>
                    </div>
                    <span class="txt_find">
                    <a href="<%=request.getContextPath()%>/member/findId" class="link_find">아이디</a>
                        /
                    <a href="<%=request.getContextPath()%>/member/FindPwServletView">비밀번호 찾기</a>
                    </span>
                </div>
                
                </fieldset>
            </form>
            
        </div>
    </div>
</section>
</body>
</html>