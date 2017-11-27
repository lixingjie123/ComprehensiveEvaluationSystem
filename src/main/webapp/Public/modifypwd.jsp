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
    <script type="text/javascript" src="../Js/jquery.form.js"></script>
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
<h3>修改密码</h3>
<form  method="post" class="definewidth m20" id=formset action="/updatepwd">
<table class="table table-bordered table-hover m10">
    <tr>
        <input type="hidden" name="uid" value="${userinfo.uid}" >
        <td width="10%" class="tableleft">旧密码</td>
        <td>
           <input type="password" name="opwd" id="opwd">*请输入旧密码
        </td>
    </tr>
    
        <tr>
        <td width="10%" class="tableleft">新密码</td>
        <td>
           <input type="password" name="npwd" id="npwd" >*请输入新密码
        </td>
    </tr>
   
    <tr>
        <td class="tableleft"></td>
        <td>
           <button type="button" class="btn btn-default" name="backid" id="backid">返回</button>
            &nbsp;&nbsp;<button type="button" class="btn btn-success" name="sava" id="sava">保存</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
$(function(){ 
	$("#sava").click(function(){
	   $("#formset").ajaxSubmit(function(data){
		   prompt_alert("warn",data,"modifypwd.jsp");
	   });
	});
})
</script>