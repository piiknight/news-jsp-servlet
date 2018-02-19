<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/admin/inc/header.jsp" %>
<!-- Form elements -->    
<%
	String err = request.getParameter("err");
	if ("1".equals(err)){
		out.print("<div style='text-align: center; font-weight: bold; color: red; background: yellow'>Sai tài khoản hoặc mật khẩu</div>");
	}
%>
<div class="grid_12">

	<div class="module">
		 <h2><span>Đăng nhập</span></h2>
			
		 <div class="module-body">
			<form method="post">
				<p>
					<label>Tên đăng nhập</label>
					<input type="text" name="username" value="" class="input-medium" />
				</p>
				<p>
					<label>Mật khẩu</label>
					<input type="password" name="password" value="" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="login" type="submit" value="Đăng nhập" /> 
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/template/admin/inc/footer.jsp" %> 