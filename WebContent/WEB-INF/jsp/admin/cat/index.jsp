<%@page import="constant.Define"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath() %>/admin/cats/add" class="button">
			<span>Thêm danh mục <img src="<%=request.getContextPath() %>/template/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>
<%
	String msg = request.getParameter("msg");
	String err = request.getParameter("err");
	String error = request.getParameter("error");
	if ("1".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Thêm danh mục thành công</div>");
	} else if ("2".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Xóa danh mục thành công</div>");
	} else if ("3".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Sửa danh mục thành công</div>");
	}
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Id không hợp lệ</div>");
	} else if ("2".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Xóa danh mục không thành công</div>");
	} else if ("3".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Sửa danh mục không thành công</div>");
	}
	if ("1".equals(error)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không tìm thấy trang</div>");
	}
%>
<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách tin</span></h2>
		
		<div class="module-table-body">
			<form action="">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th>Tên danh mục</th>
						<th style="width:11%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
						if (categories.size() > 0){
						for (Category category:categories){
					%>
					<tr>
						<td class="align-center"><%=category.getId() %></td>
						<td><a href="<%=request.getContextPath() %>/admin/cats/edit?id=<%=category.getId() %>"><%=category.getName() %></a></td>
						<td align="center">
							<a href="<%=request.getContextPath() %>/admin/cats/edit?id=<%=category.getId() %>">Sửa <img src="<%=request.getContextPath() %>/template/admin/images/pencil.gif" alt="edit" /></a>
							<a href="<%=request.getContextPath() %>/admin/cats/del?id=<%=category.getId() %>">Xóa <img src="<%=request.getContextPath() %>/template/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
						</td>
					</tr>
					<%
						}} else {
					%>
					<tr>
						<td colspan="3">Chưa có dữ liệu</td>
					</tr>
				   <%
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
					<a href="<%=request.getContextPath() %>/admin/cats?page=<%=currentPage - 1 %>"><</a>
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
					<a <%=active %> href="<%=request.getContextPath() %>/admin/cats?page=<%=i %>"><%=i %></a>
					<span class="page_span"><%=span %></span>
					</span>
				<%
					}
				%>
				<%
					if (currentPage != sumPage) {
				%>
					<a href="<%=request.getContextPath() %>/admin/cats?page=<%=currentPage + 1 %>">></a>
				<%
					}
				%>
				<a id="nextlistpage" href="javascript:;">>></a>
			</div> 
			<div style="clear: both;"></div> 
		 </div>
	
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 