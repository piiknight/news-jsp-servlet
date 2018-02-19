<%@page import="constant.Define"%>
<%@page import="model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath() %>/admin/users/add" class="button">
			<span>Thêm user <img src="<%=request.getContextPath() %>/template/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>
<%
	String msg = request.getParameter("msg");
	String err = request.getParameter("err");
	String error = request.getParameter("error");
	if ("1".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Thêm người dùng thành công</div>");
	} else if ("2".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Xóa người dùng thành công</div>");
	} else if ("3".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Sửa người dùng thành công</div>");
	}
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Thêm người dùng thất bại</div>");
	} else if ("2".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không tìm thấy ID</div>");
	} else if ("3".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Xóa người dùng thất bại</div>");
	} else if ("4".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không cho phép xóa Admin</div>");
	} else if ("5".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Sửa người dùng thất bại</div>");
	}
	if ("1".equals(error)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không cho phép sửa</div>");
	} else if ("2".equals(error)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không cho phép xóa</div>");
	} else if ("3".equals(error)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không tìm thấy trang</div>");
	}
%> 
<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách User</span></h2>
		<div class="module-table-body">
			<form action="">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th style="width:35%">Username</th>
						<th style="width:50%; text-align: center;">Fullname</th>
						<th text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
				<%
					User userInfo = (User) session.getAttribute("userLogin");
					ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
					if (users.size() > 0){
						for(User user:users){
				%>
					<tr>
						<td class="align-center"><%=user.getId() %></td>
						<td><%=user.getUsername() %></td>
						<td><%=user.getFullname() %></td>
						<%
							if ("admin".equals(userInfo.getUsername())) {
						%>
						<td align="center">
							<a href="<%=request.getContextPath() %>/admin/users/edit?id=<%=user.getId() %>">Sửa <img src="<%=request.getContextPath() %>/template/admin/images/pencil.gif" alt="edit" /></a>
							<a href="<%=request.getContextPath() %>/admin/users/del?id=<%=user.getId() %>">Xóa <img src="<%=request.getContextPath() %>/template/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
						</td>
						<%
							} else {
						%>
						<td align="center">
							<%
								if (userInfo.getId() == user.getId()) {
							%>
							<a href="<%=request.getContextPath() %>/admin/users/edit?id=<%=user.getId() %>">Sửa <img src="<%=request.getContextPath() %>/template/admin/images/pencil.gif" alt="edit" /></a>
							<%
								}
							%>
						</td>
						<%
							}
						%>
					</tr>
				<%
						}
					}
				%>
				</tbody>
			</table>
			</form>
		 </div> <!-- End .module-table-body -->
	</div> <!-- End .module -->
		 <div class="pagination">           
			<div class="numbers">
				<span>Trang:</span> 
				<%
					int sumPage = (Integer)request.getAttribute("sumPage");
					int currentPage = (Integer)request.getAttribute("currentPage");
					String active, span;
				%>
				
				<a id="backlistpage" href="javascript:;"><<</a>
				
				<%
					if (currentPage != 1) {
				%>
					<a href="<%=request.getContextPath() %>/admin/users?page=<%=currentPage - 1 %>"><</a>
				<%
					}
				%>
				<%
					for (int i = 1; i <= sumPage; i++) {
						if (currentPage == i){
							active = "class='current'";
						} else {
							active = "";
						}
						if (i % Define.GROUP_PAGE_ADMIN == 0 || i == sumPage) {
							span = "";
						} else {
							span = "|";
						}
				%>
					<span id="idpage<%=i %>">
					<a <%=active %> href="<%=request.getContextPath() %>/admin/users?page=<%=i %>"><%=i %></a>
					<span class="page_span"><%=span %></span>
					</span>
				<%
					}
				%>
				<%
					if (currentPage != sumPage) {
				%>
					<a href="<%=request.getContextPath() %>/admin/users?page=<%=currentPage + 1 %>">></a>
				<%
					}
				%>
				<a id="nextlistpage" href="javascript:;">>></a>
			</div> 
			<div style="clear: both;"></div> 
	</div>
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 