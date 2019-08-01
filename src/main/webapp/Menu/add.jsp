<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/style.css"/>
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
<form method="post" class="definewidth m20" id=formset>

    <table class="table table-bordered table-hover m10">
        <tr>
            <td width="10%" class="tableleft">上级菜单</td>
            <td>
                <input id="cc" value="无">
            </td>
        </tr>
        <tr>
            <td class="tableleft">名称</td>
            <td><input type="text" name="pname" id="pname"/></td>
        </tr>
        <tr>
            <td class="tableleft">隶属权限</td>
            <td>
                <div id="chooseRole"></div>
            </td>
        </tr>
        <tr>
            <td class="tableleft">url</td>
            <td><input type="text" name="url" id="url"/></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button class="btn btn-primary" type="button" id="seave">保存</button> &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $.ajax({
        type:"GET",
        url:"/selectrole?rname="+encodeURI(""),
        data:{
            offset:0,
            limit:20,
        },
        error:function () {
            prompt_alert("error","未知错误");
        },
        success:function (result) {
            var box = $("#chooseRole");
            box.empty();
            // var checkbox = "<input type='checkbox'/>" + result.rows.get();
            var list = result.rows;
            var checkbox = "";
            for(var i = 0; i < list.length; i++){
                checkbox += "&nbsp;<input type='checkbox' id='checkbox" + i + "'/>&nbsp;" + list[i].rname + "&nbsp;";
            }
            box.append(checkbox);
        }
    })
    $('#cc').combotree({
        url: '/powertree?pname=',
        checkbox: true,
        multiple: false,
        cascadeCheck: true,
        required: true
    });
    $(function () {
        $('#backid').click(function () {
            window.location.href = "index.jsp";
        });
        //保存按钮点击事件
        $('#seave').click(function () {
            var t = $('#cc').combotree('tree');	// 获取树对象
            var n = t.tree('getSelected');		// 获取选择的节点
            //输入合法性检测
            if(n==null){
                prompt_alert("error","请选择上级菜单");
                return;
            }else if($('#pname').val()==""){
                prompt_alert("error","请输入菜单名");
                return;
            }
            //发送保存请求
            $.ajax({
                type: "POST",
                url: "/seavepower",
                data: {
                    pname: $("#pname").val(),
                    rid:$("#chooseRole").val(),
                    url: $("#url").val(),
                    fp_id: n.id
                }, //要发送的是ajaxFrm表单中的数据
                error: function () {
                    prompt_alert("error", "添加失败");
                },
                success: function (data) {
                    //返回值data为添加菜单的pid
                    //为对应权限添加菜单
                    var i = 0;
                    var checkbox = $("#checkbox" + i);
                    while(checkbox.length > 0){
                        console.log("test");
                        //发送添加请求
                        $.ajax({
                            type:"post",
                            url:"/saveRolePower",
                            data:{
                                rid:i,
                                pid:data
                            },
                            //添加失败
                            error:function () {
                                //del power
                                $.ajax({
                                    type:"get",
                                    url:"/delectpower?pid=" + data,
                                    error:function(){
                                        prompt_alert("error","unknown error");
                                        return;
                                    },
                                    success:function () {
                                        prompt_alert("error", "添加失败");
                                        return;
                                    }
                                })
                            },
                        });
                        i++;
                        checkbox = $("#checkbox" + i);
                    }
                    prompt_alert("success","添加成功","index.jsp");
                }
            });
        });
    });
</script>