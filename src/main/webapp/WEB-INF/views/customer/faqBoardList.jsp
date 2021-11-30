<%@page import = "com.zea.geverytime.customer.model.vo.FaqBoard" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
	List<FaqBoard> list = (List<FaqBoard>) request.getAttribute("list");
	for(FaqBoard faqBoard : list){
%>
	<span><%=faqBoard.getContent() %></span>
	ddddxxxxxxxx
<%
	}
%>