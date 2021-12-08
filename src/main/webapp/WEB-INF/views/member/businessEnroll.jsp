<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>

<html>
<meta charset="UTF-8">

<head>
<style>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button 
{
    -webkit-appearance: none;
    margin: 0;
	
	
}
</style>
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
<script src="<%= request.getContextPath() %>/js/Businesslocation/location.js"></script>
<script src ="<%= request.getContextPath() %>/js/checkDuplicate/checkDuplicate.js" > </script>
<title>회원가입</title>
<script>

function handleOnInput(el, maxlength) {
	  if(el.value.length > maxlength)  {
	    el.value 
	      = el.value.substr(0, maxlength);
	  }
	}
</script>

</head>

<body>


   
	
<section>
<content>
	<center>
   <h3>회원가입</h3>
	<form  Id ="memberEnrollFrm" action="" method="POST">
   <table cellpadding="5" cellspacing="0"

          bgcolor="eeeeee">

      <tr>

        <td>ID:</td>

        <td><input type="text" name="Id" id="Id"  required>

           <input type="button" value="아이디중복검사" onclick ="checkIdDuplicate();"/>
          	
          	
            <input type="hidden" id="idValid" value="0" />
			
        </td>

      </tr>

      <tr>

        <td>비밀번호:</td>

        <td><input type="password" name="password" id="password" required placeholder="4자리이상"> </td>

      </tr>

      <tr>

        <td>비밀번호확인:</td>

        <td><input type="password"  name="passwordCheck" id="passwordCheck" required> </td>

      </tr>

      <tr>

        <td>이 름:</td>

        <td><input type="text" name="name" id="name" required  placeholder="2자리이상"> </td>
				
      </tr>
  	    <tr>
      <td>이메일 : </td>
      	<td>
	         <input type="text" name="email01" id="email01" style="width:100px" required> @
			<input type="text" name="email02" id="email02" style="width:100px;" disabled value="naver.com">
	 <select style="width:100px;margin-right:10px" name="selectEmail" id="selectEmail" >
			<%-- <option value= <%request.getParameter("email02"); %>>직접입력</option>--%> 
			 <option value="naver.com" selected>naver.com</option>
			 <option value="hanmail.net">hanmail.net</option>
			 <option value="gmail.com">gmail.com</option>
		</select>
			</td>
      
      </tr>
     

      <tr>
      <td>사업자번호 : </td>
  		<td><input type="number"  name="businessNo1" id="businessNo1" oninput='handleOnInput(this, 2)'style="width:4em" placeholder="2자리" /> 
  			<input type="number"  name="businessNo2" id="businessNo2" oninput='handleOnInput(this, 3)'style="width:4em" placeholder="3자리"/>   
  			<input type="number"  name="businessNo3" id="businessNo3" oninput='handleOnInput(this, 4)'style="width:4em" placeholder="5자리"/>   
      		<select name="businessNo4" id="businessNo4" onchange= this.value>
      		<option value="1">1.병원</option>
      		<option value="2">2.카페</option>
	      	<option value="3">3.음식점</option>
	      	<option value="4">4.펜션</option>
	      	<option value="5">5.미용실</option>
	      	<option value="6">6.마켓</option>
	      	</select>
      	</td>
      </tr>
      	
		
		<tr>
		<td>상호명 : </td>
		<td><input type="text"  name="businessName" id="businessName" required></td>
		</tr>
		
		<tr>
            <td>사업장주소 : </td>
        <td><input type="text"  name="baddress" id="baddress" required> </td>
      </tr>
      
      <tr>
           <td> 지역 : </td>
				   <td >
				
				<select name="location1" id="location"  onchange= this.value></select>
				<select name="location2" id="location" onchange=this.value ></select>
					
				</td>
						
				     </tr>
     
      
      <tr>

        <td>전화번호:</td>

        <td>
        
         <select id="tel" name= "tel1" onchange=this.value>
    <option value="010-">010</option>
    <option value="055-">055</option>
    <option value="02-">02</option>
		</select>
				 -
			<input type="number" size="4" id="tel" name="tel2" style="width:4em"   oninput='handleOnInput(this, 4)'/> -
			<input type="number" size="4" id="tel" name="tel3" style="width:4em"  oninput='handleOnInput(this, 4)'/> 


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


<script>
/**
 * 중복검사이후 아이디를 변경한 경우, 다시 중복검사해야 한다.
 */
 console.log("Id");
 
$("#Id").change(() => {
	$(idValid).val(1);
});


/**
 * name=memberEnrollFrm 유효성검사
 * - id/비번 영문자/숫자 4글자이상
 * - 이름 한글 2글자 이상
 * - 전화번호 숫자확인
 */
 
$("#memberEnrollFrm").submit((e) => {
	console.log("Id");
	//memberId
	const $memberId = $("#Id");
	//아이디는 영문자/숫자  4글자이상만 허용 
	if(!/^\w{4,}$/.test($memberId.val())){
		alert("아이디는 최소 4자리이상이어야 합니다.");
		return false;
	}
	
	//memberId 중복검사
	const $idValid = $("#idValid");
	if($idValid.val() == 0){
		alert("아이디 중복 검사해주세요.");
		return false;
	}
	
	//password
	const $password = $("#password");
	const $passwordCheck = $("#passwordCheck");
	
	if(!/^[a-zA-Z0-9!@#$]{4,}$/.test($password.val())){
		alert("유효한 패스워드를 입력하세요.");
		return false;
	}
	if($password.val() != $passwordCheck.val()){
		alert("패스워드가 일치하지 않습니다.");
		return false;
	}
	
	//memberName
	const $memberName = $("#name");
	if(!/^[가-힣]{2,}$/.test($memberName.val())){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		return false;
	}
	
	return true;
});

</script>


</body>
<form
	name="checkIdDuplicateFrm" 
	action="<%= request.getContextPath() %>/member/checkDuplicate" 
	method="GET">
	<input type="hidden" name="memberId"/>
</form>

<script src ="<%= request.getContextPath() %>/js/email/email.js" > </script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script> 

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</html>