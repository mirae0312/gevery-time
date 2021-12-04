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
   <h3>회원가입</h3>
	<form  name ="memberEnrollFrm" action="" method="POST">
   <table cellpadding="5" cellspacing="0"

          bgcolor="eeeeee">

      <tr>

        <td>ID:</td>

        <td><input type="text" name="Id" id="Id"  required>

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

        <td><input type="password"  name="passwordCheck" id="_passwordCheck"> </td>

      </tr>

      <tr>

        <td>이 름:</td>

        <td><input type="text" name="name" id="_name" > </td>

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
      <td>사업자번호 : </td>
  		<td ><input type="number"  name="businessNo1" id="_businessNo" oninput='handleOnInput(this, 9)'/>   
      	<select name="businessNo2" id="_businessNo" onchange= this.value>
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
        <td><input type="text"  name="baddress" id="baddress"> </td>
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
           <input type="reset" value="취소">
        </td>       
      </tr>
   </table>
	</form>
</section>




</body>
<form
	name="checkIdDuplicateFrm" 
	action="<%= request.getContextPath() %>/member/checkDuplicate" 
	method="GET">
	<input type="hidden" name="memberId"/>
</form>

<script src ="<%= request.getContextPath() %>/js/email/email.js" > </script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script> 



</html>