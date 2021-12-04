<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	
	Member loginMember = (Member) session.getAttribute("loginMember");
	
	
	%>
<!DOCTYPE html>

<html>
<meta   charset="UTF-8">

<head>
<script>
const checkIdDuplicate = (Id) => {
		const name = "checkIdDuplicatePopup"; 
		const spec = "left = 500px, top =500px, width=300px, height=250px";
		const popup = open("",name, spec); 
		const $memberId = $(Id);
		const $frm = $(document.checkIdDuplicateFrm);
		$frm.find("[name=memberId]").val($memberId.val());
		$frm.attr("target",name)
			.submit();
	
};

$(Id).change(() => {
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


</head>
<body>
<section id =enroll-container>
   <h3>회원가입</h3>
	<form  name ="memberEnrollFrm" action="" method="POST">
   <table cellpadding="5" cellspacing="0"

         bgcolor="eeeeee">

      <tr>

        <td>ID:</td>

        <td><input type="text" name="Id" id="Id" >
        	
        	<input type="button" value="아이디중복검사" onclick ="checkIdDuplicate(Id);"/>
            
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
			 <option  name= "email03" value= <%request.getParameter("email02"); %>>직접입력</option>
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
           <input type="reset" value="취소">
        </td>       
      </tr>
   </table>
	</form>
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