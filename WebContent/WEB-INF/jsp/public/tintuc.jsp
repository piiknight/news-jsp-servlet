<%@page import="constant.Define"%>
<%@page import="util.StringLibrary"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/template/public/inc/header.jsp" %>
<div class="leftpanel">
	<%@include file="/template/public/inc/left_bar.jsp" %>  
</div>
<div class="rightpanel">
	<div class="rightbody">
		<h1 class="title">Tin tức</h1>
		<div class="items-new">
			<ul>
				<%
					ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
					if (news.size() > 0 ){
						for (News obj : news){
				%>
				<li>
					<h2>
						<a href="<%=request.getContextPath() %>/<%=StringLibrary.createSlug(obj.getCategory().getName())%>/<%=StringLibrary.createSlug(obj.getName())%>-<%=obj.getId() %>.html" title=""><%=obj.getName() %></a>
					</h2>
					<div class="item">
						<a href="<%=request.getContextPath() %>/<%=StringLibrary.createSlug(obj.getCategory().getName())%>/<%=StringLibrary.createSlug(obj.getName())%>-<%=obj.getId() %>.html" title=""><img src="<%=request.getContextPath() %>/files/<%=obj.getPicture() %>" alt="" /></a>
						<p><%=obj.getPreview_text() %></p>
						<div class="clr"></div>
					</div>
				</li>
				<%
						}
					}
				%>
			</ul>
			
			<div class="paginator">
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
					<a href="<%=request.getContextPath() %>/tin-tuc.html?page=<%=currentPage - 1 %>"><</a>
				<%
					}
				%>
				<span id="page_area">
				<%
					for (int i = 1; i <= sumPage; i++) {
						if (currentPage == i){
							active = "class='active'";
						} else {
							active = "";
						}
						if (i % Define.GROUP_PAGE_PUBLIC == 0 || i == sumPage) {
							span = "";
						} else {
							span = "|";
						}
				%>
					<span id="idpage<%=i %>">
					<a <%=active %> href="<%=request.getContextPath() %>/tin-tuc.html?page=<%=i %>">Trang <%=i %></a>
					<span class="page_span"><%=span %></span>
					</span>
				<%
					}
				%>
				</span>
				<%
					if (currentPage != sumPage) {
				%>
					<a href="<%=request.getContextPath() %>/tin-tuc.html?page=<%=currentPage + 1 %>">></a>
				<%
					}
				%>
				<a id="nextlistpage" href="javascript:;">>></a>
			</div>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="/template/public/inc/footer.jsp" %>  		
			
