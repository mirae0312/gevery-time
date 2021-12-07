<%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String referer = (String) request.getAttribute("referer");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
$(() => {
	<% if(msg != null){ %>	
		alert("<%= msg %>");
		
	<% } %>
	
	
	});


function check_input() {
    if (!document.authForm.memberId.value)
    // login_form 이름을 가진 form 안의 id_val 의 value가 없으면
    {
        alert("아이디를 입력하세요!");
        document.authForm.memberId.focus();
        // 화면 커서 이동
        return;
    }
    if (!document.authForm.password.value)
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
    
            <form method="post" id="authForm" name="authForm" action="<%= request.getContextPath() %>/member/login">
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
                
                <!-- referer set -->
                <input type="hidden" name="referer" value="<%= referer %>" />
            </form>
            
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>