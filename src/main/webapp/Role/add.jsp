<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-treeview.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="../Css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../Css/icon.css">
	<link rel="stylesheet" type="text/css" href="../Css/demo.css">
	<script type="text/javascript" src="../Js/jquery.easyui.min.js"></script>
    

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
<table class="table table-bordered table-hover m10">

    <tr>
        <td class="tableleft">名称</td>
        <td><input type="text" name="rname" id="rname"/></td>
    </tr>
        <tr>
        <td class="tableleft">菜单</td>
        <td><input id="cc" value="菜单">  

</td>
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
    url: '/powertree?pname=',
    checkbox:true,
    multiple:true,
    cascadeCheck:true,
    required: true   
});  




    
		$('#backid').click(function(){
				window.location.href="index.jsp";
		 });
		$('#seave').click(function(){
			  var t = $('#cc').combotree('tree');	// 获取树对象
			  var n = t.tree('getChecked');		// 获取选择的节点
			  var idlist=0;
			  for(var i=0;i<n.length;i++){
				  idlist+=","+n[i].id;
			  }
			  alert($("#rname").val());
			$.ajax({
				
				type: "POST",
				url:"/seaverole", 
				data:{rname:$("#rname").val(),
					idlist:idlist
					}, //要发送的是ajaxFrm表单中的数据
				
				error: function(request) {
				alert("发送请求失败！");
				},
				success: function(data) {
					alert(data)
					window.location.href="/Role/index.jsp";
			
				}
				});
	 });
		
			     
	      
   

</script>