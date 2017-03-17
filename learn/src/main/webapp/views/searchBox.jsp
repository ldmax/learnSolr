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
	<body>
		<div id="center">
			<div class="search" id="searchBox">
				<input type="text" id="searchInput" placeholder="请输入你要搜索的关键词"/>
			</div>
			<div class="search">
				<!--input type="image" src="${ctxStatic}/pics/searchButton.png" title="搜索" id="button"/-->
			</div>	
	    </div>
	    
	    <!--  用于展示查到的数据-->
	    <div id="dataTable"></div>
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
					type: "POST",
					url: "${ctx}/crawler/search",
					data: {
				    	//封装数据
				    	searchInput : $("#searchInput").val()
					},
					success:function(data){
						console.log(data.data);
						if(data.success == true){  // 如果成功查询到数据
							$("#searchInput").hide();  // 隐藏搜索框
							// 展示数据
							for(var i=0; i<data.data.length; i++){
								var url_temp = data.data[i]["url"];
								var url = url_temp.substring(1, url_temp.length-1);
								// 去掉前后的中括号，成为一个真正的url
								$("#dataTable").append('<a href=' + url + '>' + data.data[i]["title"] + '</a><br>');
								// 网页内容中截取带关键词的一段展示在标题下方
								var content = data.data[i]["content"];  // 网页文本内容
								if(content.indexOf($("#searchInput").val())){
									alert(content.indexOf($("#searchInput").val()));
								}
								$("#dataTable").append('<div>' + data.data[i]["content"] + '</div>');
							}
						}
					}
			    });
			}
		});
		
	</script>
</html>