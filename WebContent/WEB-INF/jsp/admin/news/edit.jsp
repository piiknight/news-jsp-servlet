<%@page import="model.bean.News"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->  
<%
	String err = request.getParameter("err");
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Hãy nhập file ảnh</div>");
	} else if ("2".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Sửa tin tức bị lỗi</div>");
	}
%> 
<%
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
	News news = (News) request.getAttribute("news");
%> 
<div class="grid_12">

	<div class="module">
		 <h2><span>Sửa tin tức</span></h2>
			
		 <div class="module-body">
			<form id="form-add" action="" enctype="multipart/form-data" method="post">
				<p>
					<label>Tên tin</label>
					<input type="text" name="name" value="<%=news.getName() %>" class="input-medium" />
				</p>
				<p>
					<label>Danh mục tin</label>
					<select  name="categories" class="input-short">
						<%
							for (Category category : categories){
						%>
						<option value="<%=category.getId() %>" <%if (news.getCategory().getId() == category.getId()) out.print("selected"); %>><%=category.getName() %></option>
						<%
							}
						%>
					</select>
				</p>
				<p>
					<label>Hình ảnh</label>
					<input type="file"  name="picture" value="" />
					<%
						if (!"".equals(news.getPicture())) {
					%>
					<img src="<%=request.getContextPath() %>/files/<%=news.getPicture() %>" alt="<%=news.getName() %>" />
					<%
						}
					%>
				</p>
				<p>
					<label>Mô tả</label>
					<textarea name="preview" value="" rows="7" cols="90" class="input-medium"><%=news.getPreview_text() %></textarea>
				</p>
				<p>
					<label>Chi tiết</label>
					<textarea  name="detail" value="" rows="7" cols="90" class="input-long" id="editor"><%=news.getDetail_text() %></textarea>
				</p>
				<fieldset>
					<input class="submit-green" name="sua" type="submit" value="Sửa" /> 
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