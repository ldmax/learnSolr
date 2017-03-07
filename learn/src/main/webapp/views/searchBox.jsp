<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>搜索框</title>
		<!--  引入JS-->
		<script src="${ctxStatic}/js/jquery.js"></script>
		<!--  引入bootstrap table插件js-->
		<script src="${ctxStatic}/js/bootstrap-table.js"></script>
		<script src="${ctxStatic}/js/bootstrap-table-zh-CN.js"></script>
	</head>
	<style type="text/css">
		#center{ 
			position:absolute; 
			top:50%; 
			left:50%;
			margin-top:-300px; 
			margin-left:-800px;      
			width:200px;   
			height:100px;  
		} 
		.search{
			display:inline;
		}
		#searchInput{
			width:500px; 
			height:40px;
		}
	</style>
	<body background="${ctxStatic}/pics/background.jpg">
		<div id="center">
			<div class="search" id="searchBox">
				<input type="text" id="searchInput" placeholder="请输入你要搜索的关键词"/>
			</div>
			<div class="search">
				<!--input type="image" src="${ctxStatic}/pics/searchButton.png" title="搜索" id="button"/-->
			</div>	
	    </div>
	</body>
	<script type="text/javascript">
		// 点击搜索图标进行搜索
		$("#button").click(function(){
			$.ajax({  
				type: "GET",
				url: "${ctx}/richText/search",
				data: {
			    	//封装数据
			    	searchInput : $("#searchInput").val()
				},
				success:function(data){
					
				}
		    });
		});
		
		// 回车进行搜索
		$("#searchInput").bind("keypress", function(event){
			if(event.keyCode == "13"){
				$.ajax({  
					type: "GET",
					url: "${ctx}/richText/search",
					data: {
				    	//封装数据
				    	searchInput : $("#searchInput").val()
					},
					success:function(data){
						console.log(data);
						//window.location.href="${ctx}/richText/toRichTextPage?data=" + data;
					}
			    });
			}
		})
	</script>
</html>