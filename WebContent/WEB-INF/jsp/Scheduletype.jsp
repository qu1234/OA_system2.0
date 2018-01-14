<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排班类型主界面</title>
<link rel="stylesheet" type="text/css" href="../plugs/layui/css/layui.css">
<script src="../plugs/layui/layui.js"></script>
<script src="../plugs/easyui/jquery.min.js"></script>
<style>
	#schedule_add_div{margin-top:20px;margin-left:950px;}
	#schedule_table_div{margin-left:20px;}
	
</style>
</head>
<body>
	<div>
		<div id="schedule_add_div"><button class="layui-btn" id="Schedule_button">增加</button></div>
		<div id="schedule_table_div">
			<table id="schedule_table" lay-filter="test" ></table>
		</div>
	</div>	
<script type="text/javascript">
	
layui.use('table', function(){
	var table = layui.table;
	scheduletypeTable();
	addScheduleType();
	 function scheduletypeTable(){
		 table.render({
			  elem: '#schedule_table'
			  ,height: 500
			  ,url: 'findScheduletTypeList' //数据接口
			  ,limit:10
			  ,page: true //开启分页
			  ,cols: [[ //表头
			  	{field: 'shtName', title: '排班类型名称', width:120,align:'center'}
			  	,{field: 'shtWeek', title: '工作日', width:280,align:'center'}
				,{field: 'shtRemark', title: '备注', width:250,align:'center'}
				,{field: 'shtStatus', title: '状态', width:95,align:'center'}
				,{field: 'schedulet_caozuo', title: '操作', width:253,align:'center',toolbar: '#schedule_barDemo'}
			 ]]
		});
	}
	 
	 //点击添加调用的方法
	 function addScheduleType(){
		 $("#Schedule_button").click(function() {
			 layui.use('layer', function(){
					layer.open({
	  					type: 2, 
	  					id:'layer',
	  					content: 'to_AddScheduletypeFrom', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	  					area: ['770px', '320px'],
	  					skin:'layui-layer-molv',
	  					title: ['编辑类型', 'font-size:15px;'],
	  					shade: [0.8, '#393D49'],
	  					anim: 4,
	  					closeBtn: 1,
	  					resize:false,
	  					 end: function () {
	  		                location.reload();
	  		            } 
					});
				});	
			 
		});
		
		
	 }

	 
		//监听工具条
	 table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	   var data = obj.data; //获得当前行数据
	   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	   var tr = obj.tr; //获得当前行 tr 的DOM对象
	   var tishi="真的要删除";
	   tishi+=data.shtName;
	   tishi+="吗";
	   if(layEvent == 'type_add'){ //设置人员 

	   } else if(layEvent == 'type_detail'){ //删除 
		   layer.confirm(tishi, function(index){
			      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
			      layer.close(index);
			      //向服务端发送删除指令
			      $.ajax({
						type : "POST",//请求方式
						url : "to_updatetypeState",//请求URL
						data : {
							shtId : data.shtId,
						},//设置参数
						success : function(msg) {//访问成功回调方法	
							if(msg==true){
								layer.msg('删除成功', {icon: 6}); 
							}else{
								layer.msg('删除失败', {icon: 5}); 
							}
							
							
						}
					});
			});
		  
	   } else if(layEvent == 'type_edit'){ //编辑
		   $.ajax({
				type : "POST",//请求方式
				url : "ObtainScheduletypeVo",//请求URL
				data:{
					shtId:data.shtId,
				},
				success : function(msg) {//访问成功回调方法	
					if(msg!=null){
						layer.open({
		  					type: 2, 
		  					id:'layer',
		  					content: 'to_AddScheduletypeFrom', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  					area: ['770px', '320px'],
		  					skin:'layui-layer-molv',
		  					title: ['编辑类型', 'font-size:15px;'],
		  					shade: [0.8, '#393D49'],
		  					anim: 4,
		  					closeBtn: 1,
		  					resize:false,
		  					success:function(layero,index){
		  					  	var body = layer.getChildFrame('body', index);
		  				    	body.find('input[name=shtId]').val(msg.shtId);
		  				    	body.find('input[name=shtName]').val(msg.shtName);
		  				    	body.find('textarea[name=shtRemark]').val(msg.shtRemark);
		  				    	body.find('input[name=shtStatus]').val(msg.shtStatus);
		  				    	if(msg.shtW1==1){
		  				    		body.find('input[name=shtW1]').attr("checked","checked");
		  				    	}
		  				    	if(msg.shtW2==1){
		  				    		body.find('input[name=shtW2]').attr("checked","checked");
		  				    	}
		  				    	if(msg.shtW3==1){
		  				    		body.find('input[name=shtW3]').attr("checked","checked");
		  				    	}
		  				    	if(msg.shtW4==1){
		  				    		body.find('input[name=shtW4]').attr("checked","checked");
		  				    	}
		  				    	if(msg.shtW5==1){
		  				    		body.find('input[name=shtW5]').attr("checked","checked");
		  				    	}
		  				    	if(msg.shtW6==1){
		  				    		body.find('input[name=shtW6]').attr("checked","checked");
		  				    	}
		  				    	if(msg.shtW7==1){
		  				    		body.find('input[name=shtW7]').attr("checked","checked");
		  				    	}
		  					},
		  					end: function () {
		  		                location.reload();
		  		            } 
						});
					}
				}
			});
	    } 
	 });

}); 
	
</script>
<script type="text/html" id="schedule_barDemo">
  <a class="layui-btn  layui-btn-xs"  lay-event="type_edit">编辑</a>
  <a class="layui-btn  layui-btn-xs" lay-event="type_detail">删除</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="type_add">设置人员</a>
</script>
</body>
</html>