<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>회원가입</title>

</head>

<!-- join_form.html -->

<body>

   
	
<center>
   <h3>회원가입</h3>
	<form id="memberFrm" action="" method="post">
   <table cellpadding="5" cellspacing="0"

          bgcolor="eeeeee">

      <tr>

        <td>ID:</td>

        <td><input type="text">

            <input type="button" value="중복확인">

        </td>

      </tr>

      <tr>

        <td>비 번:</td>

        <td><input type="password"> </td>

      </tr>

      <tr>

        <td>비번확인:</td>

        <td><input type="password"> </td>

      </tr>

      <tr>

        <td>이 름:</td>

        <td><input type="text"> </td>

      </tr>

     

      </tr>

      <tr>

        <td>전화번호:</td>

        <td><input type="text" size="4"> -

            <input type="text" size="4"> -

            <input type="text" size="4">

        </td>

      </tr>

      <tr>

        <td>주 소:</td>

        <td><input type="text"> </td>

      </tr>
	<tr>
      <td> 생년월일</td>
      <td>
        <p><input type="date"></p>
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

 </center>

<script type="text/javascript"> 

    function email_change(){
    
    if(document.join.email.options[document.join.email.selectedIndex].value == '0'){
    
     document.join.email2.disabled = true;
    
     document.join.email2.value = "";
    
    }
    
    if(document.join.email.options[document.join.email.selectedIndex].value == '9'){
    
     document.join.email2.disabled = false;
    
     document.join.email2.value = "";
    
     document.join.email2.focus();
    
    } else{
    
     document.join.email2.disabled = true;
    
     document.join.email2.value = document.join.email.options[document.join.email.selectedIndex].value;
    
    }
    
    }
    
    </script>
</body>

</html>