 <%@page import = "com.zea.geverytime.customer.model.vo.ReportBoard" %>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/reportList.css" /> 
 <section id="qnaBoard-container">
 
        <h3>신고내역</h3>
 <div id="leftbox">
             <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
        </div>

       
        <div class="board_list_wrap">
            <table class="board_list">
                    <tr>
                        <th colspan="7">제목</th>
                        <th>날짜</th>
                    </tr>
       
<%
	List<ReportBoard> list = (List<ReportBoard>) request.getAttribute("list");
	for(ReportBoard reportBoard : list){
%> 
  
<% 	if(
				loginMember != null && 
				(
				  loginMember.getMemberId().equals(reportBoard.getMemberId())
				  || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
				)
			){ %>
                 <%if(reportBoard.getReportCheck().equals("C")){  %>
		     <tr>
                     <td class="tit" colspan="7">
                     <a href="<%= request.getContextPath() %>/customer/reportBoardView?no=<%= reportBoard.getReportNo() %>"><span style="color:red";>[처리완료]</span> <%= reportBoard.getTitle() %></a>
                      </td>
                      <%}else{ %> 
                        <td class="tit" colspan="7">
                       <a href="<%= request.getContextPath() %>/customer/reportBoardView?no=<%= reportBoard.getReportNo() %>"><span style="color:red";>[처리중]</span> <%= reportBoard.getTitle() %></a>
                        </td>
                        <%} %> 
                        <td><%= reportBoard.getRegDate()%></td>
                  </tr>   
				 
				     <%} %> 
				     
				     
				     
				     
				    
    <%} %> 
			</table>
       </div>
 
  
</section>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>
 
