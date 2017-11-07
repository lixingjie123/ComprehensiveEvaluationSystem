<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/10/30
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>



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

<form class="form-inline definewidth m20" action="index.jsp" method="get">
    选择角色：
    <select id="role"></select>
    用户名称：
    <input type="text" name="uname" id="uname"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增用户</button>
</form>

<button id="onlond" ><a href="/onlondUser">下载用户信息</a></button>
<br>
<table class="table table-bordered table-hover definewidth m10" id="table">
</table>
</body>
</html>
<script>
    $(function () {
        var rid =0;
        var uname = null;
        $("#role,#username").change(function () {
            rid = $("#role").val();
            uname = $("#username").val();
            alert(rid +"    "+uname);
        });

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

                    if (${updataUser.rid ==jsonObj[j].rid}){
                        optionstring += "<option selected = \"selected\"  value=\"" + jsonObj[j].rid + "\" >" + jsonObj[j].rname + "</option>";
                    }else {
                        optionstring += "<option value=\"" + jsonObj[j].rid + "\" >" + jsonObj[j].rname + "</option>";
                    }

                }
                $("#role").html("<option value='请选择'>----请选择----</option> "+optionstring);

            },
            error: function (msg) {
                alert("出错了！");
            }
        });
/*
        $('#onlond').click(function () {
            $.ajax({
                url: "/onlondUser",
                dataType: "json",
                type: "get",
                data: data,
                success: function (data){

                        alert(data);

                },
                error: function(data){

                    alert(data);

                }
            });
        });*/

        $('#addnew').click(function(){

            window.location.href="add.html";
        });
        var $table = $('#table');


        /*接收后台JSON对象*/
        $table.bootstrapTable({
            url: "/selectPageUser",
            dataType: "json",
            singleSelect: false,
            height: 550,//高度调整
            locale:'zh-CN',//中文支持
            pagination: true,//是否开启分页（*）
            pageNumber:1,//初始化加载第一页，默认第一页
            pageSize: 5,//每页的记录行数（*）
            pageList: [5,10,11],//可供选择的每页的行数（*）

            sidePagination: "server", //服务端处理分页
            showColumns: true,//列选择按钮
            clickToSelect: true,
            toolbar: "#toolbar",//指定工具栏
            toolbarAlign: "right",//工具栏对齐方式
            detailView: false, //是否显示详情折叠
            columns: [
                {
                    title: '用户ID',
                    field: 'uid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '用户姓名',
                    field: 'uname',
                    align: 'center',
                    valign: 'middle',
                },
                {
                    title: '用户角色',
                    field: 'rid',
                    align: 'center'
                },
                {
                    title: '用户性别',
                    field: 'sex',
                    align: 'center'
                },
                {
                    title: '用户电话',
                    field: 'phone',
                    align: 'center',
                },
                {
                    title: '操作',
                    field: 'uid',
                    align: 'center',
                    formatter:function(value,row,index){
                        var e = '<a href="#"  mce_href="#" onclick="edit(\''+ row.uid + '\')">编辑</a> ';
                        var d = '<a href="#"  mce_href="#" onclick="del(\''+ row.uid +'\')">删除</a> ';
                        return e+d;
                    }
                }
            ]
        });


    });


    function edit(uid) {
        var url = "edit.html?uid="+uid;
        window.location.href=url;
    }

    function del(uid)
    {


        if(confirm("确定要删除吗？"))
        {

            $.ajax({
                url:"/deleteUser?uid="+uid,
                type:"GET",
                processData:false,
                contentType:false,
                success:function(data){
                    alert(data);
                    window.location.reload();
                },
                error:function(e){
                    alert("错误！！");
                }
            })

        }


        function getUrlParam(uid) {
            var reg = new RegExp("(^|&)" + uid + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }


    }
</script>