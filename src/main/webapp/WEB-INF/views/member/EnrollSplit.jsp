<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member/loginsplit.css" />

    <title>Document</title>
</head>
<style>
.selfAccrdt .txt1.normalMem {
    background-img: url(/images/com_man.png) no-repeat center 60px;
}
</style>

<body>
   <div class="choKind">
        <div class="join1Div">
            <div class="selfAccrdt">
            <img src="<%=request.getContextPath()%>/images/cat.png" alt= />
                <p class="txt1 normalMem">일반회원</p>
           
                <p class="txt2">
                    일반회원으로 가입합니다. <br>
                </p>
            </div>
            <p class="bts2">
             <a href ="<%=request.getContextPath()%>/member/memberEnroll"><img src="<%=request.getContextPath()%>/images/enroll.png "alt="회원가입"></a>
            
            </p>
        </div>
        <div class="join1Div">
            <div class="selfAccrdt">
                  <img src="<%=request.getContextPath()%>/images/dog.png" alt= />
                <p class="txt1 comMem">사업자회원</p>
                <p class="txt2">
                    사업자회원으로 가입합니다. <br>
                    (사업자회원은 사업자 등록번호가 있는 대표자만 가입가능합니다.) <br>
                    <span>* 사업자회원 가입후 일반회원으로 전환도 가능합니다.</span>
                </p>
            </div>
            <p class="bts2">
                <a href ="<%=request.getContextPath()%>/member/businessEnroll"><img src="<%=request.getContextPath()%>/images/enroll.png "alt="회원가입"></a>

            </p>
        </div>
        </div>
                                </div>
                            
                    </div>
    
               </div>  



</body>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
