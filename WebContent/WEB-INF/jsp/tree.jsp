<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/themes/default/easyui.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/themes/icon.css"/>">

<style>
ul, li {
	padding: 0px;
	margin: 0px;
	list-style: none;
}
</style>

<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.easyui.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/easyui-lang-zh_CN.js"/>"></script>

<script type="text/javascript">
	$(function() {
		/*动态数据加载树*/
		$("#loads").tree({
			url : 'findDeptAndEmpTree',
			animate : true,
			method : 'get',
			lines : true,
			animate:true,
			iconCls:'icon1',
			checkbox:true,
		})
		$('#loads').tree({
			onClick: function(node){
				alert(node.id);  // 在用户点击的时候提示
				 $.ajax({
						type : "POST",//请求方式
						url : "to_findEmpGroupByDeptTree",//请求URL
						data : {
							deptId : node.id,
							
						},//设置参数
						success : function(msg) {//访问成功回调方法						
							alert(msg.deptId);
						}
				});
			}
		});
	});
</script>

</head>
<body>
	
	<hr/>
	<h2>动态数据填充树</h2>
	<ul id="loads"></ul>
</body>

</html>
























