<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>搜索结果</title>
		<!--  引入JS-->
		<script src="${ctxStatic}/js/jquery.js"></script>
		<!--  引入bootstrap table插件js-->
		<script src="${ctxStatic}/js/bootstrap-table.js"></script>
		<script src="${ctxStatic}/js/bootstrap-table-zh-CN.js"></script>
	</head>
	
	<body>
		<c:forEach var="item" items="${resultList}">
			<a href="${item.url}">${item.title}</a>
		</c:forEach>
	</body>
</html>