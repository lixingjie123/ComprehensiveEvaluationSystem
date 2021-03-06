<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
        <link rel="stylesheet" type="text/css" href="../Css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../Css/icon.css">
	<link rel="stylesheet" type="text/css" href="../Css/demo.css">
	<script type="text/javascript" src="../Js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../Js/prompt.alert.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form  method="post" class="definewidth m20" id=formset>
<input type="hidden" name="index_id" value=""  id="index_id"/>
<table class="table table-bordered table-hover m10">
    <tr>
        <td width="10%" class="tableleft">上级</td>
        <td>
            <input id="cc" value="指标">  
            
        </td>
    </tr>
    <tr>
        <td class="tableleft">名称</td>
        <td><input type="text" name="index_name" id="index_name"/></td>
    </tr>
    <tr>
        <td class="tableleft">权重</td>
        <td><input type="text" name="weight" id="weight"/></td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button  class="btn btn-primary" type="button" id="seave">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
$('#cc').combotree({    
    url: '/indextree?index_name=',
    checkbox:true,
    multiple:false,
    cascadeCheck:true,
    required: true   
});  
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index.jsp";
		 });
		//原数据
		$.ajax({  
            url: "/querindexbyid?index_id="+getUrlParam("index_id"),    
            type: "get",  
            dataType: "json",  
            contentType: "application/json",  
            traditional: true,  
            success: function (data) {  
                
                    
                    $("#index_name").val(data.index_name);
                    $("#weight").val(data.weight);
                    $("#index_id").val(data.index_id);
                    $('#cc').combotree('setValue', data.parent_id);


                    
                    
                 
            },  
            error: function (msg) {  
                alert("出错了！");  
            }  
        });
		//修改数据
$('#seave').click(function(){
	var t = $('#cc').combotree('tree');	// 获取树对象
	  var n = t.tree('getSelected');		// 获取选择的节点
			$.ajax({
				
				type: "POST",
				url:"/updateindex", 
				data:	{index_name:$("#index_name").val(),
					weight:$("#weight").val(),
					index_id:$("#index_id").val(),
                    parent_id:n.id},//要发送的是ajaxFrm表单中的数据
				
				error: function(request) {
					prompt_alert("error","错误！！");
				},
				success: function(data) {
					alert(data)
					prompt_alert("success",data,"index.jsp");
			
				}
				});
	 });
		

    });
    
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
</script>