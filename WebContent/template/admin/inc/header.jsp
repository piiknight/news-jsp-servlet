<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>VinaEnter EDU - Admin template</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/admin/css/styles.css" media="screen" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/template/admin/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/template/admin/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/template/admin/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/template/admin/js/ckfinder/ckfinder.js"></script>
	</head>
	<body>
    	<!-- Header -->
        <div id="header">
            <!-- Header. Status part -->
            <div id="header-status">
                <div class="container_12">
                    <div class="grid_4">
                    	<ul class="user-pro">
                    	<%
                    		User userLogin = (User) session.getAttribute("userLogin");
                    		if (userLogin == null) {
                    	%>
							<li><a href="<%=request.getContextPath() %>/auth/login">Login</a></li>
							<li>Chào, <a href="#">Khách</a></li>
						<%
                    		} else {
						%>
							<li><a href="<%=request.getContextPath() %>/auth/logout">Logout</a></li>
							<li>Chào, <a href="<%=request.getContextPath() %>/admin/users/edit?id=<%=userLogin.getId() %>"><%=userLogin.getFullname() %></a></li>
						<%
                    		}
						%>
                    	</ul>
                    </div>
                </div>
                <div style="clear:both;"></div>
            </div> <!-- End #header-status -->
            
            <!-- Header. Main part -->
            <div id="header-main">
                <div class="container_12">
                    <div class="grid_12">
                        <div id="logo">
                            <ul id="nav">
                                <li id="current"><a href="<%=request.getContextPath() %>/admin/">Quản trị</a></li>
                                <%
		                    		if (userLogin == null) {
		                    	%>
		                    			<li><a href="#">Tài khoản</a></li>
		                    	<%	
		                    		} else {
								%>
                                <li class="tagli"><a href="<%=request.getContextPath() %>/admin/users/edit?id=<%=userLogin.getId() %>">Tài khoản</a></li>
                           		<%
		                    		}
                           		%>
                            </ul>
                        </div><!-- End. #Logo -->
                    </div><!-- End. .grid_12-->
                    <div style="clear: both;"></div>
                </div><!-- End. .container_12 -->
            </div> <!-- End #header-main -->
            <div style="clear: both;"></div>
            <!-- Sub navigation -->
            <div id="subnav">
                <div class="container_12">
                    <div class="grid_12">
                        <ul>
                            <li><a href="<%=request.getContextPath() %>/admin/news">Tin tức</a></li>
                            <li><a href="<%=request.getContextPath() %>/admin/cats">Danh mục</a></li>
                            <li><a href="<%=request.getContextPath() %>/admin/users">User</a></li>
                        </ul>
                        
                    </div><!-- End. .grid_12-->
                </div><!-- End. .container_12 -->
                <div style="clear: both;"></div>
            </div> <!-- End #subnav -->
        </div> <!-- End #header -->
        
		<div class="container_12">