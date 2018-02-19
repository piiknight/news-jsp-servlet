<%@page import="util.StringLibrary"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<News> listInvolve = (ArrayList<News>) request.getAttribute("listInvolve");
%>
<%
	for (News newsInv : listInvolve) {
%>
<li>
	<h2>
		<a href="<%=request.getContextPath() %>/<%=StringLibrary.createSlug(newsInv.getCategory().getName())%>/<%=StringLibrary.createSlug(newsInv.getName())%>-<%=newsInv.getId() %>.html"><%=newsInv.getName() %></a>
	</h2>
	<div class="item">
		<a href="<%=request.getContextPath() %>/<%=StringLibrary.createSlug(newsInv.getCategory().getName())%>/<%=StringLibrary.createSlug(newsInv.getName())%>-<%=newsInv.getId() %>.html" title="<%=newsInv.getName() %>"><img src="<%=request.getContextPath() %>/files/<%=newsInv.getPicture() %>" alt="<%=newsInv.getName() %>"></a>
		<p><%=newsInv.getPreview_text() %></p>
		<div class="clr"></div>
	</div>
</li>
<%
	}
%>