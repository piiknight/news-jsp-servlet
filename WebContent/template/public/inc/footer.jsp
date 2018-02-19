<%@page import="model.bean.News"%>
<%@page import="model.bean.User"%>
<%@page import="constant.Define"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
</div>
			<div class="footer">
				<p>
					Copyright 2016. Designed by <a class="footer-link" target="_blank"
						href="http://vinaenter.edu.vn">VinaEnter</a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var count = 1;
		var sumGroup = 0;
		var sum = $("#page_area span a").length;
		var countGroup = <%=Define.GROUP_PAGE_PUBLIC %>;
		
		var id_news = 0;
		var countInvolve = 1;
	    $(document).ready(function(){
	    	<%
	    	int page_id = 0;
    		if (request.getAttribute("currentPage") != null){
    			page_id = (Integer)request.getAttribute("currentPage");
    		}
	    	%>
	    	var page_id = <%=page_id %>; 
	    	
	    	sumGroup = parseInt((sum - 1) / countGroup) + 1;
	    	var groupPage =  (page_id - 1) / countGroup + 1;
	    	
	    	if (page_id != 0){
	    		count = parseInt(groupPage);
	    	} else {
	    		count = 1;
	    	}
	    	myFunction(count);
	    	
	    	
	    	<%
	    	int id_news = 0;
    		if (request.getParameter("id") != null){
    			id_news = Integer.parseInt(request.getParameter("id"));
    		}
	    	%>
	    	var id_news = <%=id_news %>;
	    	if (id_news != 0) {
	    		involveFunction(1, id_news);
	    	}
	  	});
	    function myFunction(x){
	    	var start = countGroup * (x - 1) + 1;
	    	var end = x * countGroup;
	    	for (var i = 1; i <= sum; i++){
	    		var str = "#idpage" + i;
	    		if (i >= start && i <= end){
	    			$(str).css("display", "");
	    		} else {
	    			$(str).css("display", "none");
	    		}
	    	}
	    	if (count == 1){
	    		$("#backlistpage").css("display", "none");
	    	} else {
	    		$("#backlistpage").css("display", "");
	    	}
	    	if (count == sumGroup){
	    		$("#nextlistpage").css("display", "none");
	    	} else {
	    		$("#nextlistpage").css("display", "");
	    	}
	    	
	    }
	    
	    function involveFunction(x, y){
	    	var str = "#pageInvolve" + x;
	    	$('.form_involve a').attr('class', '');
	    	$(str).attr('class', 'active');
	    	countInvolve = x;
	    	id_news = y;
			$.ajax({
				url: '<%=request.getContextPath() %>/detail',
				type: 'POST', 
				cache: false,
				data: {
					idpage : x,
					id : y,
				},
				success: function(data){
					$('#items-involve').html(data);
				},
				error: function (){
					// Xử lý nếu có lỗi
					alert('loi');
				}
			});
			if (countInvolve == 1){
	    		$("#previouspageinvovle").css("display", "none");
	    	} else {
	    		$("#previouspageinvovle").css("display", "");
	    	}
	    	if (countInvolve == sum){
	    		$("#nextpageinvovle").css("display", "none");
	    	} else {
	    		$("#nextpageinvovle").css("display", "");
	    	}
	    	var groupPageInvolve =  (x - 1) / countGroup + 1;
	    	count = parseInt(groupPageInvolve);
	    	myFunction(count);
	    }
	    $("#previouspageinvovle").click( function(){
	    	countInvolve--;
	    	involveFunction(countInvolve, id_news);
	    });
	    $("#nextpageinvovle").click( function(){
	    	countInvolve++;
	    	involveFunction(countInvolve, id_news);
	    });
	    
	    
	    $("#nextlistpage").click( function(){
	    	count++;
	    	myFunction(count);
	    });
	    $("#backlistpage").click( function(){
	    	count--;
	    	myFunction(count);
	    });
    </script>
</body>
</html>