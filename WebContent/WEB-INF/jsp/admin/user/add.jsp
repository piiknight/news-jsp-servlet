<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->
<%
	String err = request.getParameter("err");
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Người dùng đã tồn tại</div>");
	} else if ("2".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Không thể thêm danh sách</div>");
	}
	String username = request.getParameter("username");
	String fullname = request.getParameter("fullname");
	if (username == null){
		username = "";
	}
	if (fullname == null){
		fullname = "";
	}
%>  
<div class="grid_12">

	<div class="module">
		 <h2><span>Thêm người dùng</span></h2>
			
		 <div class="module-body">
			<form id="form-add" action="" method="post">
				<p>
					<label>Username:</label>
					<input type="text" name="username" value="<%=username %>" class="input-medium" />
				</p>
				<p>
					<label>Password:</label>
					<input type="password" name="password" value="" class="input-medium" />
				</p>
				<p>
					<label>Fullname:</label>
					<input type="text" name="fullname" value="<%=fullname %>" class="input-medium" />
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
							username:{
								required: true,
							},
							password:{
								required: true,
							},
							fullname:{
								required: true,
							},
						},
						messages:{
							username:{
								required: "Hãy nhập Username",
							},
							password:{
								required: "Hãy nhập Password",
							},
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