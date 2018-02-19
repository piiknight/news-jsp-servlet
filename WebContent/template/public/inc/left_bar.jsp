<%@page import="util.StringLibrary"%>
<%@page import="model.bean.Category"%>
<%@page import="model.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Danh mục tin</h2>
<ul>
	<%
		CategoryDAO categoryDAO = new CategoryDAO();
		for (Category category : categoryDAO.getItems()){
	%>
	<li><a href="<%=request.getContextPath() %>/<%=StringLibrary.createSlug(category.getName()) %>-<%=category.getId() %>.html"><%=category.getName() %></a></li>
	<% 
		}
	%>
</ul>
