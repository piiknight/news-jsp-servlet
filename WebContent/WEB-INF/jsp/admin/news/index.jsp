<%@page import="constant.Define"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath() %>/admin/news/add" class="button">
			<span>Thêm tin <img src="<%=request.getContextPath() %>/template/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>
<%
	String msg = request.getParameter("msg");
	String err = request.getParameter("err");
	String error = request.getParameter("error");
	if ("1".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Thêm tin tức thành công</div>");
	} else if ("2".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Xóa tin tức thành công</div>");
	} else if ("3".equals(msg)){
		out.print("<div style='text-align: center; font-weight: bold; color: green; background: yellow'>Sửa tin tức thành công</div>");
	}
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Thêm tin tức thất bại</div>");
	} else if ("2".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Xóa tin tức thất bại</div>");
	} else if ("3".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Sửa tin tức thất bại</div>");
	}
	if ("1".equals(error)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không tìm thấy ID</div>");
	} else if ("2".equals(error)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không tìm trang</div>");
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
						<th>Tên</th>
						<th style="width:20%">Danh mục</th>
						<th style="width:16%; text-align: center;">Hình ảnh</th>
						<th style="width:11%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<News> listNews = (ArrayList<News>) request.getAttribute("listNews");
						if (listNews.size() > 0){
							for (News news:listNews){
					%>
					
					<tr>
						<td class="align-center"><%=news.getId() %></td>
						<td><a href="<%=request.getContextPath() %>/admin/news/edit?id="><%=news.getName() %></a></td>
						<td><%=news.getCategory().getName() %></td>
						<td align="center"><img src="<%=request.getContextPath() %>/files/<%=news.getPicture() %>" class="hoa" /></td>
						<td align="center">
							<a href="<%=request.getContextPath() %>/admin/news/edit?id=<%=news.getId() %>">Sửa <img src="<%=request.getContextPath() %>/template/admin/images/pencil.gif" alt="edit" /></a>
							<a onclick="return confirm('Bạn có muốn xóa?')" href="<%=request.getContextPath() %>/admin/news/del?id=<%=news.getId() %>">Xóa <img src="<%=request.getContextPath() %>/template/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
						</td>
					</tr>
					<%
							}
						} else {
					%>
						<tr>
							<td colspan="5">Chưa có tin tức nào !</td>
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
					<a href="<%=request.getContextPath() %>/admin/news?page=<%=currentPage - 1 %>"><</a>
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
					<a <%=active %> href="<%=request.getContextPath() %>/admin/news?page=<%=i %>"><%=i %></a>
					<span class="page_span"><%=span %></span>
					</span>
				<%
					}
				%>
				<%
					if (currentPage != sumPage) {
				%>
					<a href="<%=request.getContextPath() %>/admin/news?page=<%=currentPage + 1 %>">></a>
				<%
					}
				%>
				<a id="nextlistpage" href="javascript:;">>></a>
			</div> 
			<div style="clear: both;"></div> 
		 </div>
	
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 