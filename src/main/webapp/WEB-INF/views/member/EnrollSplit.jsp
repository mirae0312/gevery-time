<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<form action="" ></form>
    <div class="member_split"> 
        <img src="https://pics.auction.co.kr/membership/join_main_title_1.gif" alt="구매회원 가입">
        <ul>
            <li>
                <img src="https://pics.auction.co.kr/membership/join_main_text_1.gif" alt="개인 구매회원(외국인포함), 만14세 이상 가능">
                <span>
                    <a href ="<%=request.getContextPath()%>/member/memberEnroll"><img src="<%=request.getContextPath()%>/images/enroll.png "alt="회원가입"></a>
                </span>
            </li>
            <li>
                <img src="https://pics.auction.co.kr/membership/join_main_text_2.gif" alt="사업자 구매회원, 사업자등록증을 보유한 구매회원">
                <span>
                    <a href ="<%=request.getContextPath()%>/member/businessEnroll"><img src="<%=request.getContextPath()%>/images/enroll.png "alt="회원가입"></a>
                </span>
            </li>
        </ul>
    </div>
</body>
</html>