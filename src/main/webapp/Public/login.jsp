<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>教师综合评教系统</title>
	<meta charset="UTF-8">
   <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/jquery.form.js"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            float: right;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
        .container{

            width:60%;
            background-image: url("../Images/login_pic.png");
            background-repeat: no-repeat;
            margin: 0 auto;
        }
        .title{
            font-size: 30px;
            text-align: center;
            margin: 30px;
            font-style: inherit;
        }
        .abc input-default,.input-block-level{
        width:230px;
        }

    </style>
</head>
<body>
<div class="title"><p>教师综合评教系统</p></div>
<div class="container">
    
    <form class="form-signin" method="post" action="/login" id="login">
        <h2 class="form-signin-heading">登录教师评教系统</h2>
        身份： <select name="rid" id="rid" class="abc input-default"></select><br>
        账号：<input type="text" name="uid" class="input-block-level"><br>
        密码：<input type="password" name="pwd" class="input-block-level">

        <p><button class="btn btn-large btn-primary" id="loginBtn" type="button">登录</button></p>
    </form>

</div>
</body>
</html>
<script>
$(function(){
	$.ajax({
        url: "/selectRoleOption",    //后台webservice里的方法名称
        type: "get",
        dataType: "json",
        contentType: "application/json",
        traditional: true,
        success: function (data) {

            var jsonObj =data;
            var optionstring = "";
            for (var j = 0; j < jsonObj.length; j++) {
                optionstring += "<option value=\"" + jsonObj[j].rid + "\" >" + jsonObj[j].rname + "</option>";
               
            }
            $("#rid").html("<option value='请选择'>----请选择----</option> "+optionstring);
            $("#rid").val(0);
        },
        error: function (msg) {
            alert("出错了！");
        }
    });
	
/* 	$("#rid").change(function() {
		alert($("#rid").val());
	}); */
	
	$("#loginBtn").click(function() {

            $("#login").ajaxSubmit(function(data){
                if(data.p==0){
                    alert(data.msg);
                }else{
                    alert(data.msg);
                    window.location.href="../index.jsp";
                }
            });
	   /* alert($('#login').serialize());
		$.ajax({
	        url: "/login",    //后台webservice里的方法名称
	        type: "post",
	        dataType: "json",
	        data:$('#login').serialize(),
	        contentType: "application/json",
	        traditional: true,
	        success: function (data) {
	        	alert("11");
	        },
	        error: function (msg) {
	            alert("出错了！");
	        }
	    });*/
		
	});
})

</script>