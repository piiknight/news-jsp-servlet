<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/public/inc/header.jsp" %>
<div class="leftpanel">
	<%@include file="/template/public/inc/left_bar.jsp" %>  
</div>
<%
	News news = (News) request.getAttribute("news");
%>
<div class="rightpanel">
	<div class="rightbody">
		<h1 class="title"><%=news.getName() %></h1>
		<div class="items-new">
			<div class="new-detail">
				<%=news.getDetail_text() %>
			</div>
		</div>
		
		<h2 class="title" style="margin-top:30px;color:#BBB">Tin tức liên quan</h2>
		<div class="items-new">
			<ul>
				<li>
					<h2>
						<a href="chitiet.jsp" title="Trung Quốc điều thêm 17 tàu đến khu vực giàn khoan">Trung Quốc điều thêm 17 tàu đến khu vực giàn khoan</a>
					</h2>
					<div class="item">
						<a href="chitiet.jsp" title="Trung Quốc điều thêm 17 tàu đến khu vực giàn khoan"><img src="images/tmp/h1.jpg" alt="Trung Quốc điều thêm 17 tàu đến khu vực giàn khoan"></a>
						<p>Để bảo vệ giàn khoan, Trung Quốc đã điều thêm 17 tàu các loại so với hôm trước, sẵn sàng đâm va vào tàu Việt Nam.</p>
						<div class="clr"></div>
					</div>
				</li>
				<li>
					<h2>
						<a href="chitiet.jsp" title="Trọng tài - vết đen của kỳ World Cup sôi động ">Trọng tài - vết đen của kỳ World Cup sôi động </a>
					</h2>
					<div class="item">
						<a href="chitiet.jsp" title="Trọng tài - vết đen của kỳ World Cup sôi động "><img src="images/tmp/h2.jpg" alt="Trọng tài - vết đen của kỳ World Cup sôi động "></a>
						<p>World Cup 2014 chưa đi hết lượt đầu vòng bảng nhưng các trọng tài đẳng cấp FIFA đã có tới bốn trận bị chỉ trích dữ dội.</p>
						<div class="clr"></div>
					</div>
				</li>
			</ul>
			<div class="paginator">
				<span>Trang:</span> 
				<a id="nextlistpage" href="javascript:;">>></a>
			</div>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="/template/public/inc/footer.jsp" %>  