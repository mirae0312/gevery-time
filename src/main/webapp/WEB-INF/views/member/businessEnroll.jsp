<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
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
   <h3>회원가입</h3>
	<form  name ="businessEnrollFrm" action="" method="POST">
   <table cellpadding="5" cellspacing="0"

          bgcolor="eeeeee">

      <tr>

        <td>ID:</td>

        <td><input type="text" name="businessId" id="_businessId">

            <input type="button" value="중복확인" onclick="checkIdDuplicate();" />
            <input type="hidden" id="idValid" value="0" />

        </td>

      </tr>

      <tr>

        <td>비 번:</td>

        <td><input type="password" name="password" id="_password"> </td>

      </tr>

      <tr>

        <td>비번확인:</td>

        <td><input type="password"  name="passwordCheck" id="_passwordCheck"> </td>

      </tr>

      <tr>

        <td>이 름:</td>

        <td><input type="text" name="name" id="_name" > </td>

      </tr>
  		<tr>
      <td>이메일 : </td>
      
        <td><input type="text"  name="email" id="_email"> </td>
      
      </tr>
     

      <tr>
      <td>사업자번호 : </td>
  		<td><input type="number"  name="businessNo" id="_businessNo" oninput='handleOnInput(this, 9)'/>   
      	<select name="businessNo" id="_businessNo">
      	<option value="1">1.병원</option>
      	<option value="2">2.카페</option>
      	<option value="3">3.음식점</option>
      	<option value="4">4.펜션</option>
      	<option value="5">5.미용실</option>
      	<option value="6">6.마켓</option>
      	
      	</select></td>
      </tr>
		
		<tr>
		<td>상호명 : </td>
		<td><input type="text"  name="businessName" id="_businessName"></td>
		</tr>
		
		<tr>
            <td>사업장주소 : </td>
        <td><input type="text"  name="address" id="_address"> </td>
      </tr>
      
      <tr>
           <td> 지역 : </td>
      
        <td><input type="text"  name="location" id="_location"> </td>
      
      </tr>
     
      
      <tr>

        <td>전화번호:</td>

        <td>
        
         <select id="phone" name= "phone">
    <option value="010">010</option>
    <option value="055">055</option>
    <option value="02">055</option>
		</select>
				 -
			<input type="text" size="4" id="tel" name="_tel"> -
			<input type="text" size="4" id="tel" name="_tel"> 


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




</body>
<form 
	name="checkDuplicateFrm" 
	action="<%= request.getContextPath() %>/member/checkDuplicate" 
	method="GET">
</form>
<script>
const checkDuplicate = () => {
		const name = "checkIdDuplicatePopup"; 
		const spec = "left = 500px, top =500px, width=300px, height=250px";
		const popup = open("",name, spec); 
		
		const $memberId = $(_memberId);
		const $frm = $(document.checkDuplicateFrm);
		$frm.find("[name=memberId]").val($memberId.val());
		$frm.attr("target",name)
			.submit();
	
};
	
};
/**
 *name= memberEnrollFrm 유효성검사
 *-id/비번 영문자/숫자 4글자이상
 * -이름 한글 2글자이상
 * -전화번호 숫자확인
 *
 *
 *
 */
$(documnet.memberEnrollFrm).submit((e) => {
		
	//memberId
	const $memberId = $(_memberId);
	//아이디는 영문자/숫자 4글자이상만 허용
	if(!/^\w{4,}$/.test($memberId.val()){
		alert("아이디는 최소 4자리 이상이어야 합니다.");
		return false;
	}
	//memberId 중복검사
	const $idValid = $(idValid);
	if($idValid.val() == 0){
		alert("아이디 중복 검사해주세요.");
		return false
	}

	//password
	const $password = $(_password);
	const $passwordCheck = $(passwordCheck);
	
	if(!/^[a-zA-Z0-9!@#$]{4,}$/.test($password.val())){
		alert("유효한 패스워드를 입력하세요.");
		return false;
	}
	if($password.val() != $passwordCheck.val()){
		alert("패스워드가 일치하지 않습니다.");
		return false;
	}	
	//memberName
	const $memberName = $(memberName);
	if(!/^[가-힣]{2,}$?.test($memberName.val())){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		return false;
	}
	return true;
});





</script>
</html>