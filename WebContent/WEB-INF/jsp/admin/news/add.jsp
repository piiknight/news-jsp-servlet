<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">
<%
	String err = request.getParameter("err");
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Hãy nhập file ảnh</div>");
	} else if ("2".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Vui lòng chọn hình ảnh</div>");
	} else if ("3".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Có lỗi khi thêm danh sách</div>");
	}
%>
<%
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
	String catId = request.getParameter("category");
	if (catId == null){
		catId = "";
	}
	String name = request.getParameter("name");
	if (name == null){
		name = "";
	}
	String preview = request.getParameter("preview");
	if (preview == null){
		preview = "";
	}
	String detail = request.getParameter("detail");
	if (detail == null){
		detail = "";
	}
%>
	<div class="module">
		 <h2><span>Thêm tin tức</span></h2>
			
		 <div class="module-body">
			<form id="form-add" action="" enctype="multipart/form-data" method="post">
				<p>
					<label>Tên tin</label>
					<input type="text" name="name" value="<%=name %>" class="input-medium" />
				</p>
				<p>
					<label>Danh mục tin</label>
					<select  name="category" class="input-short">
						<% 
							for (Category category : categories) {
						%>
						<option value="<%=category.getId() %>" <%if (catId.equals(String.valueOf(category.getId()))) out.print("selected"); %>><%=category.getName() %></option>
						<%
							}
						%>
					</select>
				</p>
				<p>
					<label>Hình ảnh</label>
					<input type="file"  name="picture" value="" />
				</p>
				<p>
					<label>Mô tả</label>
					<textarea name="preview" rows="7" cols="90" class="input-medium"><%=preview %></textarea>
				</p>
				<p>
					<label>Chi tiết</label>
					<textarea  name="detail" rows="7" cols="90" class="input-long" id="editor"><%=detail %></textarea>
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>	
			<script>
				var editor = CKEDITOR.replace('editor');
				CKFinder.setupCKEditor(editor, '<%=request.getContextPath() %>/template/admin/js/ckfinder');
			</script>
			<script type="text/javascript">
				$(document).ready(function (){
					$("#form-add").validate({
						rules:{
							name:{
								required: true,
							},
							preview:{
								required: true,
							},
							detail:{
								required: true,
							},
						},
						messages:{
							name:{
								required: "Hãy nhập tên truy cập",
							},
							preview:{
								required: "Không được để trống",
							},
							detail:{
								required: "Không được để trống",
							},
						},
						ignore: []
					});			
				});
			</script>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 