<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->
  
<div class="grid_12">
<%
	User user = (User) request.getAttribute("user");
	String err = request.getParameter("err");
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Có lỗi khi sửa người dùng!</div>");
	}
	
%>
	<div class="module">
		 <h2><span>Sửa người dùng</span></h2>
			
		 <div class="module-body">
			<form id="form-add" action="" method="post">
				<p>
					<label>Username:</label>
					<input type="text" name="username" value="<%=user.getUsername() %>" class="input-medium" disabled />
				</p>
				<p>
					<label>Password:</label>
					<input type="password" name="password" value="" class="input-medium" />
				</p>
				<p>
					<label>Fullname:</label>
					<input type="text" name="fullname" value="<%=user.getFullname() %>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="sua" type="submit" value="Sửa" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
			<script type="text/javascript">
				$(document).ready(function (){
					$("#form-add").validate({
						rules:{
							fullname:{
								required: true,
							},
						},
						messages:{
							fullname:{
								required: "Hãy nhập Fullname",
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