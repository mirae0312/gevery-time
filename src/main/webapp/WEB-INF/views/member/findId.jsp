<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<title>Insert title here</title>
	<style>
		*{
			text-align : center;
		}
	</style>
</head>
	<section>
<body>
	
	<form name="searchFrm">
		<input type="hidden" name="name">
		<input type="hidden" name="email">
		<input type="hidden" name="phone">
	</form>
	
	
	<div id="div1">
	<h3>아이디 찾기</h3>
	
	<table class ="table" style="margin:0 auto;width:700px;">
		<tr>
			<td><input type="text" name="Name" id="name" class="form-control" placeholder="이름을 입력하세요"></td>
		</tr>
		<br>
		<tr>
			<td><input type="text" name="Email" id="email"class="form-control" placeholder="이메일을 입력하세요"></td>
		</tr>
		<br>
		<tr>
			<td><input type="text" name="Phone" id="phone" class="form-control" placeholder="전화번호를 입력하세요 (- 제외)"></td>
		</tr>
	</table>
	<button type="button" id="searchBtn" class= "btn btn-outline-info btn sm" onclick="searchId()">검색</button>

	</div>
	</section>
	
	<script>
      function searchId(){
         var name = document.getElementById("name").value;
         var email = document.getElementById("email").value;
         var phone = document.getElementById("phone").value;
         if(name == "" && email == "" && phone == ""){
            alert("모든 정보를 입력해주세요.");
            return;
         }
         
         var url = "/member/findId"; // 요청 서블릿 url
         
         var title ="searchId"; //윈도우 창 이름
         
         var status = "left=500px, top=100px, width=300px, height=200px, menubar-no, status=no, scrollbar=yes";
         
         
         var popup = window.open("",title,status); //빈창 오픈
         
         searchFrm.name.value=name;
         searchFrm.email.value=email;
         searchFrm.phone.value=phone;
         
         searchFrm.target = title;//popup창과 form태그를 연결
         //action,method설정 후 form태그 submit
         searchFrm.action = url;
         searchFrm.method="post";
         
         searchFrm.submit();
      }
   </script>
	
	
</body>
</html>