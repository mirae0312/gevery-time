<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.zea.geverytime.member.model.vo.Member"%>
   <%@ include file="/WEB-INF/views/common/header.jsp" %>


<!DOCTYPE html>

<html>
<meta   charset="UTF-8">

<head>
<script>
const checkIdDuplicate = () => {
		const name = "checkIdDuplicatePopup"; 
		const spec = "left = 500px, top =500px, width=300px, height=250px";
		const popup = open("",name, spec); 
		const $memberId = $(Id);
		const $frm = $(document.checkIdDuplicateFrm);
		$frm.find("[name=memberId]").val($memberId.val());
		$frm.attr("target",name)
			.submit();
	
};

$().change(() => {
	$(idValid).val(0);
});
</script>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>


<style>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button 
{
    -webkit-appearance: none;
    margin: 0;
	size:4;
	
}
</style>
<script>

function handleOnInput(el, maxlength) {
	  if(el.value.length > maxlength)  {
	    el.value 
	      = el.value.substr(0, maxlength);
	  }
	}
</script>


<title>회원가입</title>

<script>
var regType1 =var regType1 = /^[A-Za-z0-9+]{4,12}$/; 
if (regType1.test(document.getElementById('Id').value)) { alert('아이디가 조건에 맞지 않습니다(4~12자영한혼용)'); }
</script>
<script>
function chkPW(){

	 var pw = $("#password").val();
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	 if(pw.length < 8 || pw.length > 20){

	  alert("8자리 ~ 20자리 이내로 입력해주세요.");
	  return false;
	 }else if(pw.search(/\s/) != -1){
	  alert("비밀번호는 공백 없이 입력해주세요.");
	  return false;
	 }else if(num < 0 || eng < 0 || spe < 0 ){
	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
	  return false;
	 }else {
		console.log("통과"); 
	    return true;
	 }

	}

	
</script>
</head>
<body>
<section id =enroll-container>
<content>
<center>
   <h3>회원가입</h3>
	<form  name ="memberEnrollFrm" action="" method="POST">
   <table cellpadding="5" cellspacing="0"

         bgcolor="eeeeee">

      <tr>

        <td>ID:</td>

        <td><input type="text" name="Id" id="Id" >
        
        	<input type="button" value="아이디중복검사"  name="duplicate" onclick ="checkIdDuplicate();"/>
            
            <input type="hidden" id="idValid" value="0" />

        </td>

      </tr>

      <tr>

        <td>비 번:</td>

        <td><input type="password" name="password" id="_password"> </td>

      </tr>

      <tr>

        <td>비번확인:</td>

        <td><input type="password" id="_passwordCheck" name="passwordCheck"> </td>

      </tr>

      <tr>

        <td>이 름:</td>

        <td><input type="text" id="memberName" name="name"> </td>

      </tr>

   

      <tr>

        <td>전화번호:</td>

        <td>
        <select id="phone1" name= "phone1">
    <option value="010">010</option>
    <option value="055">055</option>
		</select>
				 -

            <input type="number"  id="phone" name="phone2" style="width:4em"   oninput='handleOnInput(this, 4)'/> -

            <input type="number"  id="phone" name="phone3"  style="width:4em"  oninput='handleOnInput(this, 4)'/>

        </td>

      </tr>

      <tr>

        <td>주 소:</td>

        <td><input type="text"  id="address" name="address"> </td>

      </tr>
      <tr>
      <td>이메일 : </td>
      	<td>
	         <input type="text" name="email01" id="email01" style="width:100px"> @
			<input type="text" name="email02" id="email02" style="width:100px;" disabled value="naver.com">
	 <select style="width:100px;margin-right:10px" name="selectEmail" id="selectEmail" >
			 <%-- <option value= <%request.getParameter("email02"); %>>직접입력</option>--%> 
			 <option value="naver.com" selected>naver.com</option>
			 <option value="hanmail.net">hanmail.net</option>
		</select>
			</td>
      
      </tr>
	<tr>
      <td> 생년월일</td>
      <td>
        <p><input type="date" id="birthday" name="birthday"></p>
    	</td>
    </tr>        
      <tr align="center">
        <td colspan="2">
           <input type="submit" value="등록">
           <input type="reset"   onclick="history.back()"  value="취소"/>
        </td>       
      </tr>
   </table>
	</form>
	</center>
	</content>
</section>

<form
	name="checkIdDuplicateFrm" 
	action="<%= request.getContextPath() %>/member/checkDuplicate" 
	method="GET">
	<input type="hidden" name="memberId"/>
</form>
	
<script src ="<%= request.getContextPath() %>/js/email/email.js" > </script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script> 







</body>
</html>