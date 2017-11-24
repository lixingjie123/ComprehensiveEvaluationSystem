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
    <script type="text/javascript" src="../Js/prompt.alert.js"></script>
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
        账号：<input type="text" name="uid" class="input-block-level"><br>
        密码：<input type="password" name="pwd" class="input-block-level">

        <p><button class="btn btn-large btn-primary" id="loginBtn" type="button">登录</button></p>
    </form>

</div>
</body>
</html>
<script>
$(function(){

	
	$("#loginBtn").click(function() {

            $("#login").ajaxSubmit(function(data){
                if(data.p==0){
                    prompt_alert("error",data.msg);
                }else{
                    prompt_alert("success",data.msg,"../index.jsp");
                }
            });
	});
})

</script>