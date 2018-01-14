<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排班类型—新增排班类型</title>
<link rel="stylesheet" type="text/css" href="../plugs/layui/css/layui.css" />
<script src="../plugs/layui/layui.js"></script>
<style>
	body{background-color:#F1F6FF;}
	 #xgjb-bigdiv{border:0px solid red;padding: 10px;}
	
	td{border:1px solid #e6e6e6;}
	#add_name,#add_notes,#add_week{width:100px;text-align:right;color:#666666;font-weight:bold;}
	#add_week{height:60px;}
	#add_notes_td{height:60px;}
	 #add_notes_td input{width:550px;} 
	#bottom-div{margin-top:20px;text-align:center;} 
	.checkboxtd{width:650px;}
	#div_input{margin-left:9px;}
	#add_name_div,#add_notes_div{margin-left:10px;width:200px;}
	#add_name_div{margin-top:13px;}
	#add_shtRemark{width:550px;}
</style>
</head>
<body>
	
	<div id="xgjb-bigdiv">
		<div >
			<form class="layui-form" id="add_Scheduletype_form" >
				<table id="xgjb-table">
					<tbody>
						<tr>
							<td style="display:none;"><input type="text" name="shtId" value=""/></td>
							<td style="display:none;"><input type="text" name="shtStatus" value=""/></td>
							<td id="add_name">名称:</td>
							<td >
								<div class="layui-input-block layui-form-item" id="add_name_div">
      								<input type="text" name="shtName" required  lay-verify="required" placeholder="请输入排班类型名称" autocomplete="off" class="layui-input">
      							</div>
      						</td>
						</tr>
						<tr>
							<td id="add_week">工作日:</td>
							<td class="checkboxtd">
								<div id="div_input" >
							        <input type="checkbox" name="shtW1" title="星期一" lay-skin="primary" >
									<input type="checkbox" name="shtW2" title="星期二" lay-skin="primary"> 
									<input type="checkbox" name="shtW3" title="星期三" lay-skin="primary"> 
									<input type="checkbox" name="shtW4" title="星期四" lay-skin="primary"> 
									<input type="checkbox" name="shtW5" title="星期五" lay-skin="primary"> 
									<input type="checkbox" name="shtW6" title="星期六" lay-skin="primary"> 
									<input type="checkbox" name="shtW7" title="星期日" lay-skin="primary"> 
							    </div>
							</td>
						</tr>
						<tr>
							<td id="add_notes">备注:</td>
							<td id="add_notes_td">
								<div class="layui-input-block" id="add_notes_div">
									<textarea name="shtRemark" id="add_shtRemark" lay-verify="required" placeholder="请输入备注内容" autocomplete="off" class="layui-input"></textarea>
	      							<!-- <input type="textarea" name="shtRemark" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input"> -->
	      						</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="bottom-div">
					  <button class="layui-btn" id="add_type_submit" lay-submit  lay-filter="formDemo">保存</button>
					<a class="layui-btn" id="add_cancel">取消</a>
	 				
				</div>
			</form>
			
		</div>
	</div>
<script>
layui.use(['form','layer'], function(){
	var form = layui.form;
	 var layer=layui.layer;
	 var $=layui.$;
	 //监听提交
	   form.on('submit(formDemo)', function(data){
	     
     	var url='';
     	
     	 	//判断表单是新增还是修改
	        if($('input[name=shtId]').val()==''){
	        	url='/to_addType';
	        }else{
	       		url='/to_editScheduletype';
	        } 
     	//用post提交表单
         $.post(url,data.field,function (da) {
        	
        	parent.layer.closeAll('iframe');
             
         });
	        return false;
	 }); 
	 //点击取消按钮关闭弹窗 
	   $("#add_cancel").click(function() {
      	 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
      	 parent.layer.close(index); //再执行关闭   
       })
});
</script>
</body>
</html>