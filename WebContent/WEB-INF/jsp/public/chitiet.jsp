<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/public/inc/header.jsp" %>
<div class="leftpanel">
	<%@include file="/template/public/inc/left_bar.jsp" %>  
</div>
<%
	News news = (News) request.getAttribute("news");
	ArrayList<News> newsInvolve = (ArrayList<News>) request.getAttribute("newsInvolve");
	int sumPageInvolve = (int) request.getAttribute("sumPageInvolve");
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
			<ul id="items-involve">
				
			</ul>
			<div class="paginator">
				<span>Trang:</span> 
				<a id="backlistpage" href="javascript:;"><<</a>
				<a id="previouspageinvovle" href="javascript:;"><</a>
				
				<span id="page_area">
					<form class="form_involve">
					<%	
						String span = "";
						for (int k = 1; k <= sumPageInvolve; k++) {
							if (k % Define.GROUP_PAGE_PUBLIC == 0 || k == sumPageInvolve) {
								span = "";
							} else {
								span = "|";
							}
					%>
							<span id="idpage<%=k %>">
								<a id="pageInvolve<%=k %>" onclick="involveFunction(<%=k %>, <%=news.getId() %>);" href="javascript:;">Trang <%=k %></a>
								<span class="page_span"><%=span %></span>
							</span>	
					<%
						}
					%>
					</form>
				</span>
				<a id="nextpageinvovle" href="javascript:;">></a>
				<a id="nextlistpage" href="javascript:;">>></a>
			</div>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="/template/public/inc/footer.jsp" %>  