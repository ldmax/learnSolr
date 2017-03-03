<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>搜索页面</title>
	<!--  引入JS-->
	<script src="${ctxStatic}/js/jquery.js"></script>
	<!--  引入bootstrap table插件js-->
	<script src="${ctxStatic}/js/bootstrap-table.js"></script>
	<script src="${ctxStatic}/js/bootstrap-table-zh-CN.js"></script>
</head>
<body>
	<table>
		<tr>
			<td>
				<span>姓名：</span>
			</td>
			<td>
				<input type="text" id="name" placeholder="请输入姓名"/>
			</td>
			<td>
				<select id="name_connector">
					<option value="">--请选择--</option>
					<option value="AND">AND</option>
					<option value="OR">OR</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<span>母亲姓名：</span>
			</td>
			<td>
				<input type="text" id="motherName" placeholder="请输入母亲姓名"/>
			</td>
			<td>
				<select id="motherName_connector">
					<option value="">--请选择--</option>
					<option value="AND">AND</option>
					<option value="OR">OR</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<span>父亲姓名：</span>
			</td>
			<td>
				<input type="text" id="fatherName" placeholder="请输入父亲姓名"/>
			</td>
			<td>
				<select id="fatherName_connector">
					<option value="">--请选择--</option>
					<option value="AND">AND</option>
					<option value="OR">OR</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<span>现住地：</span>
			</td>
			<td>
				<input type="text" id="regionDetail" placeholder="请输入现住地"/>
			</td>
		</tr>
	</table>
	<input type="button" id="search" value="查询"/>
	
	<span id="data"></span>
</body>
<script type="text/javascript">
	function getQueryString(){
		var queryString = "";
		if($("#name").val() == ""){
			queryString += "name:* ";
		}else{
			queryString += "name:" + $("#name").val() + " ";
		}
		queryString += $("#name_connector").val() + " ";
		if($("#motherName").val() == ""){
			queryString += "mother_name:* ";
		}else{
			queryString += "mother_name:" + $("#motherName").val() + " ";
		}
		queryString += $("#motherName_connector").val() + " ";
		if($("#fatherName").val() == ""){
			queryString += "father_name:* ";
		}else{
			queryString += "father_name:" + $("#fatherName").val() + " ";
		}
		queryString += $("#fatherName_connector").val() + " ";
		if($("#regionDetail").val() == ""){
			queryString += "region_detail:* ";
		}else{
			queryString += "region_detail:" + $("#regionDetail").val();
		}
		console.log(queryString);
		return queryString;
	}
	
	// 点击查询按钮查询
	$("#search").click(function(){
		$.ajax({
	    	type:"GET",
	    	url:"${ctx}/kid/list",
	    	data: {
	    		queryString: getQueryString()
	    	},
	    	success:function(data){
	    		console.log(data);
	    	},
	    	error:function(){
	    	}
	    });
	});
</script>
</html>