<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/jquery.form.js"></script>
    
<title>Insert title here</title>
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
<form action="/updateDepartment" method="post" class="definewidth m20" id="updateDepartmentFrom">
<input type="hidden" name="clid" id="clid" />
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">系部名称</td>
            <td><input type="text" name="dept_name" id="dept_name"/></td>
        </tr>

        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="fettle" id="fettle1" value="1"/> 启用
              <input type="radio" name="fettle" id="fettle0" value="0"/> 禁用
            </td>
        </tr>

        <tr>
            <td class="tableleft"></td>
            <td>
                <button id="updateDepartmentBtn" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {

        //接收上一个页面传值
        function getUrlParam(uid) {
            var reg = new RegExp("(^|&)" + uid + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }

        $("#updateDepartmentBtn").click(function () {
            $("#updateDepartmentFrom").ajaxSubmit(function (data) {
                var url = "index.jsp";
                window.location.href=url;
            });
        });

        $.ajax({
            url:"/selectDepartmentByDeptid?dept_id="+getUrlParam("dept_id"),
            type:"GET",
            processData:false,
            contentType:false,
            success:function(data){
                $("#dept_id").val(data.dept_id);
                $("#dept_name").val(data.dept_name);
                var fettle = data.fettle;
                if(fettle==0){
                    $("#fettle0").attr('checked', 'checked');
                }else
                {
                    $("#fettle1").attr('checked', 'checked');
                }
            },
            error:function(e){
                alert("错误！！");
            }
        });

		$('#backid').click(function(){
				window.location.href="{:U('User/index')}";
		 });

    });
</script>