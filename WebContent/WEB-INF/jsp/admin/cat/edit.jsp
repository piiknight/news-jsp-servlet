<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">
<%
Category category = (Category) request.getAttribute("category");
	String err = request.getParameter("err");
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Có lỗi khi sửa danh mục!</div>");
	}
	String name = request.getParameter("name");
	if (name == null){
		name = category.getName();
	}
	
%>
	<div class="module">
		 <h2><span>Sửa danh mục</span></h2>
			
		 <div class="module-body">
			<form method="post">
				<p>
					<label>Tên danh mục</label>
					<input type="text" name="name" value="<%=name %>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="sua" type="submit" value="Sửa" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 