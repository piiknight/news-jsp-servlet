<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">
<%
	String err = request.getParameter("err");
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Có lỗi khi thêm danh mục!</div>");
	}
	String name = request.getParameter("name");
	if (name == null){
		name = "";
	}
%>
	<div class="module">
		 <h2><span>Thêm danh mục</span></h2>
			
		 <div class="module-body">
			<form id="form-add" method="post">
				<p>
					<label>Tên danh mục</label>
					<input type="text" name="name" value="<%=name %>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
			<script type="text/javascript">
				$(document).ready(function (){
					$("#form-add").validate({
						rules:{
							name:{
								required: true,
							},
						},
						messages:{
							name:{
								required: "Hãy nhập Tên danh mục",
							},
						}
						
					});			
				});
			</script>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 