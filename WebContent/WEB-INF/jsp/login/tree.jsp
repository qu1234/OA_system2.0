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
		
		
		$("#easyui_tree").tree({
			url : 'js/tree_data1.json',
			animate : true,
			method : 'get'
		});
		
		/*树的每一个节点前面都加上了复选框 */
		$("#checkboxtree").tree({
			url : 'js/tree_data1.json',
			animate : true,
			method : 'get',
			checkbox : true
		});
		
		$("#linetree").tree({
			url : 'js/tree_data1.json',
			animate : true,
			method : 'get',
			lines : true,

		});

		$("#treeimg").tree({
			url : 'js/tree_data2.json',
			animate : true,
			method : 'get',
			lines : true,

		});

		
		$("#getValue").linkbutton({
			onClick : function() {
				var nodes = $('#checkboxtree').tree('getChecked');
				var s = '';
				for (var i = 0; i < nodes.length; i++) {
					if (s != ''){
						s += ',';
					}
					s += nodes[i].text;
				}
				alert(s);
			}
		})
		
		/*填充树*/
		$("#treeaccess").tree({
			url : 'js/tree_data1.json',
			animate : true,
			method : 'get',
			lines : true,
			
		})
		/*收缩树的所有节点*/
		$("#collapseAll").linkbutton({
			onClick:function(){
				$("#treeaccess").tree('collapseAll');
			}
			
		})
		/*展开树的所有节点*/
		$("#expandAll").linkbutton({
			onClick:function(){
				$("#treeaccess").tree('expandAll');
			}
			
		})
		
		/*展开树到某个节点*/
		$("#expandTo").linkbutton({
			onClick:function(){
				var node=$('#treeaccess').tree('find',113);
				$("#treeaccess").tree('expandTo',node.target).tree('select',node.target);
			}
		})
		
		/*获取选择的节点*/
		$("#getSelected").linkbutton({
			onClick:function(){
				var node=$("#treeaccess").tree('getSelected');
				if(node){
					 var s = node.text;
					 if (node.attributes){
						 s += ","+node.attributes.p1+","+node.attributes.p2;
					 }
					alert(s);
				}else{
					alert("请选择");
				}
			}
		})
		/*树右键菜单*/
		$("#menutree").tree({
			url : 'js/tree_data1.json',
			animate : true,
			method : 'get',
			lines : true,
			onContextMenu:function(e,node){
				e.preventDefault();
				$(this).tree('select',node.target);
				$('#me').menu('show',{
					left:e.pageX,
					top:e.pageY
				})
			}
		})
		/*树右键菜单添加事件*/
		$("#append").linkbutton({
			onClick:function(){
				var node=$("#menutree").tree('getSelected');
				$("#menutree").tree('append',{
					parent:(node?node.target:null),
					data:[
					      {text:'new item1'},
					      {text:'new item2'}
					]
				})
			}
		})
		
		/*树右键菜单删除事件*/
		$("#remove").linkbutton({
			onClick:function(){
				var node=$("#menutree").tree('getSelected');
				$("#menutree").tree('remove',node.target);
			}
		})
		
		/*树右键菜单展开事件*/
		$("#expand").linkbutton({
			onClick:function(){
				var node=$("#menutree").tree('getSelected');
				$("#menutree").tree('expand',node.target);
			}
		})
		/*树右键菜单收缩事件*/
		$("#collapse").linkbutton({
			onClick:function(){
				var node=$("#menutree").tree('getSelected');
				$("#menutree").tree('collapse',node.target);
			}
		})
		
		/*可拖放树*/
		$("#dragtree").tree({
			url : 'js/tree_data1.json',
			animate : true,
			method : 'get',
			lines : true,
			dnd:true
			
		})
		/*可编辑树*/
		$("#edittree").tree({
			url : 'js/tree_data1.json',
			animate : true,
			method : 'get',
			lines : true,
			onDblClick: function(node){
				$(this).tree('beginEdit',node.target);
			}
		})
		

		/*动态数据加载树*/
		$("#loads").tree({
			url : 'initTree',
			animate : true,
			method : 'get',
			lines : true,
			
		})
	});
</script>

</head>
<body>
	
	<hr/>
	
	
	<ul id="tt" class="easyui-tree">
		<li><span>Folder</span>
			<ul>
				<li><span>Sub Folder 1</span>
					<ul>
						<li><span><a href="#">File 11</a></span></li>
						<li><span>File 12</span></li>
						<li><span>File 13</span></li>
					</ul></li>
				<li><span>File 2</span></li>
				<li><span>File 3</span></li>
			</ul></li>
		<li><span>File21</span></li>
	</ul>


	<hr />
	<ul id="bb" class="easyui-tree">
		<li><span>文件夹</span>
			<ul>
				<li>子文件夹
					<ul>
						<li>ss</li>
						<li>ss</li>
					</ul>
				</li>
				<li>ss</li>
				<li>ss</li>
			</ul></li>
		<li>ss</li>
		<li>ss</li>
	</ul>
	
	
	<hr />
	<h2>基本树组件ddddd</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>点击左边箭头来展开或者收缩节点.</div>
	</div>
	
	

	<div style="margin: 10px 0;"></div>
	 <ul class="easyui-tree" data-options="animate:true">
        <li><span>My Documents</span>
            <ul>
                <li data-options="state:'closed'">
                    <span>Photos</span>
                    <ul>
                        <li><span>Friend</span></li>
                        <li><span>Wife</span></li>
                        <li><span>Company</span></li>
                    </ul>
                </li>
                <li><span>Program Files</span>
                    <ul>
                        <li>Intel</li>
                        <li>Java</li>
                        <li>Microsoft Office</li>
                        <li>Games</li>
                    </ul>
                </li>
                <li>index.html</li>
                <li>about.html</li>
                <li>welcome.html</li>
            </ul>

        </li>

    </ul>
    
    <hr/>
    <h1>使用脚本填充树</h1>
	<ul id="easyui_tree"></ul>
	<hr />
	
	<h2>带复选框的树节点</h2>
	<div style="margin: 10px 0;">
		<a id="getValue" class="easyui-linkbutton">获取选中值</a> <br /> <input
			type="checkbox" checked
			onchange="$('#tt').tree({cascadeCheck:$(this).is(':checked')})">级联选中
		<input type="checkbox"
			onchange="$('#tt').tree({onlyLeafCheck:$(this).is(':checked')})">只有叶子节点有复选框
	</div>

	<ul id="checkboxtree"></ul>

	<hr />
	<h2>线型树组件</h2>
	<ul id="linetree"></ul>
	
	
	<hr />
	<h2>树节点图标</h2>
	<ul id="treeimg"></ul>
	<hr />
	<h2>树的相关操作</h2>
	<div>
		<a id="collapseAll" href="#" class="easyui-linkbutton">收缩所有</a>
		<a id="expandAll" href="#" class="easyui-linkbutton">展开所有</a>
		<a id="expandTo" href="#" class="easyui-linkbutton">展开到</a>
		<a id="getSelected" href="#" class="easyui-linkbutton">获取选中节点</a>
	</div>
	<ul id="treeaccess"></ul>
	<hr />
	<h2>树右键菜单</h2>
	<div id="me" class="easyui-menu" style="width:120px">
		<div id="append"  data-options="iconCls:'icon-add'">添加</div>
		<div id="remove"  data-options="iconCls:'icon-remove'">删除</div>
		<div class="menu-sep"></div>
		<div id="expand">展开</div>
		<div id="collapse">收缩</div>
	</div>
	<ul id="menutree"></ul>
	
	<hr/>
	<h2>拖放树节点</h2>
	<ul id="dragtree"></ul>
	
	<hr/>
	<h2>可编辑树</h2>
	<ul id="edittree"></ul>
	
	<hr/>
	<h2>动态数据填充树</h2>
	<ul id="loads"></ul>
</body>

</html>
























