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
  
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/b.selectpage.js"></script>
    <script type="text/javascript" src="../Js/demo.js"></script>
    <script type="text/javascript" src="../Js/selectpage.js"></script>
    
	<link rel="stylesheet" type="text/css" href="../Css/icon.css">
	<link rel="stylesheet" type="text/css" href="../Css/demo.css">
	<link rel="stylesheet" type="text/css" href="../Css/demo2.css">
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	<link rel="stylesheet" type="text/css" href="../Css/selectpage.css">
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
<table class="table table-bordered table-hover m10">

    <tr>
        <td class="tableleft">课程</td>
        <td><input type="text" id="cname" class="input-block-level"></td>
    </tr>
     <tr>
        <td class="tableleft">教师</td>
        <td><input type="text" id="uname" class="input-block-level"></td>
    </tr>
     <tr>
        <td class="tableleft">班级</td>
        <td><input type="text" id="clname" class="input-block-level"></td>
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


$.ajax({
	
	type: "get",
	url:"/selectallcourse", 
	data:{
		
		
		}, //要发送的是ajaxFrm表单中的数据
	
	error: function(request) {
	alert("发送请求失败！");
	},
	success: function(data) {
		$('#cname').bSelectPage({
		    showField : 'cname',
		    keyField : 'cid',
		    pageSize : 5,
		    data : data,
		   
		});

	}
	});
$.ajax({
	
	type: "get",
	url:"/selectteacher", 
	data:{
		
		
		}, //要发送的是ajaxFrm表单中的数据
	
	error: function(request) {
	alert("发送请求失败！");
	},
	success: function(data) {
		$('#uname').bSelectPage({
		    showField : 'uname',
		    keyField : 'tid',
		    pageSize : 5,
		    data : data,
		   
		});

	}
	});
$.ajax({
	
	type: "get",
	url:"/selectClass", 
	data:{
		
		
		}, //要发送的是ajaxFrm表单中的数据
	
	error: function(request) {
	alert("发送请求失败！");
	},
	success: function(data) {
		$('#clname').bSelectPage({
		    showField : 'clname',
		    keyField : 'clid',
		    pageSize : 5,
		    data : data,
		   
		});

	}
	});





    
		$('#backid').click(function(){
				window.location.href="index.jsp";
		 });
		$('#seave').click(function(){
			
			
			$.ajax({
				
				type: "POST",
				url:"/seavegivecourse", 
				data:{cid:$("#cname").val(),
					tid:$("#uname").val(),
					clid:$("#clname").val(),
					
					}, //要发送的是ajaxFrm表单中的数据
				
				error: function(request) {
					prompt_alert("error","错误！！");
				},
				success: function(data) {
					prompt_alert("success",data,"index.jsp");
			
				}
				});
	 });
		
			     
	      
   

</script>