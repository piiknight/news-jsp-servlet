<%@page import="constant.Define"%>
<%@page import="model.bean.User"%>
</div>    
        <div style="clear:both;"></div>
        <!-- Footer -->
        <div id="footer">
        	<div class="container_12">
            	<div class="grid_12">
                	<p>Copyright &copy; 2016. <a href="http://vinaenter.edu.vn" title="Đã học là làm được">VinaEnter EDU</a></p>
        		</div>
            </div>
            <div style="clear:both;"></div>
        </div> <!-- End #footer -->
	</body>
	<script type="text/javascript">
		var count = 0;
		var sumGroup = 0;
		var sum = 0;
		var countGroup = <%=Define.GROUP_PAGE_ADMIN %>;
	    $(document).ready(function(){
	    	var path = window.location.pathname;
	    	var split = path.split("/");
	    	var active = split[4];
	    	<%
	    	int id = 0;
	    	int currentId = 0;
	    	if (request.getParameter("id") != null){
	    		id = Integer.parseInt(request.getParameter("id"));
	    	}
	    	if (session.getAttribute("userLogin") != null){
	    		User currentUser = (User) session.getAttribute("userLogin");
	    		currentId = currentUser.getId();
	    	}
	    	%>
	    	var id = <%=id %>;
	    	var currentId = <%=currentId %>;
	    	if (id == currentId && active == "edit"){
	    		$('#current').attr('id','');
	    		$('.tagli').attr('id', 'current');
	    	}
	    	
	    	<%
	    	int page_id = 0;
    		if (request.getAttribute("currentPage") != null){
    			page_id = (Integer)request.getAttribute("currentPage");
    		}
	    	%>
	    	var page_id = <%=page_id %>; 
	    	<%	
    		int sum = 0;
    		if (request.getAttribute("sumPage") != null){
    			sum = (Integer)request.getAttribute("sumPage");
    		}
	    	%>
	    	sum = <%=sum %>;
	    	sumGroup = parseInt((sum - 1) / countGroup) + 1;
	    	var groupPage =  (page_id - 1) / countGroup + 1;
	    	count = parseInt(groupPage);
	    	myFunction(count);
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
	    $("#nextlistpage").click( function(){
	    	count++;
	    	myFunction(count);
	    });
	    $("#backlistpage").click( function(){
	    	count--;
	    	myFunction(count);
	    });
    </script>
</html>