<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/11/8
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
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
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/prompt.alert.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
    <title>Insert title here</title>
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media ( max-width : 980px) {
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
    班级名称：
    <input type="text" name="clname" id="clname"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增班级</button>
</form>
<table class="table table-bordered table-hover definewidth m10" id="table">
</table>
</body>
</html>
<script>
    $(function () {
        var $table = $('#table');
        //接收上一个页面传值
        function getUrlParam(uid) {
            var reg = new RegExp("(^|&)" + uid + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }


        /*接收后台JSON对象*/
        $table.bootstrapTable({
            url: "/selectPageByClname?clname="+getUrlParam("clname"),
            dataType: "json",
            singleSelect: false,
            locale:'zh-CN',//中文支持
            pagination: true,//是否开启分页（*）
            pageNumber:1,//初始化加载第一页，默认第一页
            pageSize: 10,//每页的记录行数（*）
            pageList: [5,10,11],//可供选择的每页的行数（*）

            sidePagination: "server", //服务端处理分页
            showColumns: true,//列选择按钮
            clickToSelect: true,
            toolbar: "#toolbar",//指定工具栏
            toolbarAlign: "right",//工具栏对齐方式
            detailView: false, //是否显示详情折叠
            columns: [
                {
                    title: '班级ID',
                    field: 'clid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '班级名称',
                    field: 'clname',
                    align: 'center',
                    valign: 'middle',
                },
                {
                    title: '班级状态',
                    field: 'status',
                    align: 'center',
                    formatter:function (value,row,index) {
                        if (row.fettle!=0){
                            return "启用";
                        }else {
                            return "禁用";
                        }
                    }
                },
                {
                    title: '操作',
                    field: 'clid',
                    align: 'center',
                    formatter:function(value,row,index){
                        var e = '<a href="#"  mce_href="#" onclick="edit(\''+ row.clid + '\')">编辑</a> ';
                        var d ;
                        if (row.fettle!=0){
                            d='<a href="#"  mce_href="#"   onclick="mode(\''+ row.clid + '\')">禁用班级</a> ';
                        }else {
                            d='<a href="#"  mce_href="#" onclick="mode(\''+ row.clid + '\')">启用班级</a> ';
                        }
                        return e+d;
                    }
                }
            ]
        });

        $('#addnew').click(function(){

            window.location.href="add.html";
        });


    });

    function edit(clid)
    {
        var url = "edit.html?clid="+clid;

        window.location.href=url;
    }

    function mode(clid){
        var url = "/updateStatus?clid="+clid;

        $.ajax({
            url:url,
            type:"GET",
            processData:false,
            contentType:false,
            success:function(data){
                prompt_alert("success",data,"index.jsp");

            },
            error:function(e){
                prompt_alert("error","错误！！");
            }
        })
    }
</script>
