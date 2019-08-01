<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/easyui.css">
    <link rel="stylesheet" type="text/css" href="../Css/icon.css">
    <link rel="stylesheet" type="text/css" href="../Css/demo.css">
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
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
<form method="post" class="definewidth m20" id=formset>
    <input type="hidden" name="pid" value="" id="pid"/>
    <table class="table table-bordered table-hover m10">
        <tr>
            <td width="10%" class="tableleft">上级</td>
            <td>
                <input id="cc" value="无">
            </td>
        </tr>
        <tr>
            <td class="tableleft">名称</td>
            <td><input type="text" name="pname" id="panme"/></td>
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
        type: "GET",
        url: "/selectrole?rname=" + encodeURI(""),
        data: {
            offset: 0,
            limit: 20,
        },
        error: function () {
            prompt_alert("error", "未知错误");
        },
        success: function (result) {
            var box = $("#chooseRole");
            box.empty();
            var list = result.rows;
            var checkbox = "";
            for (var i = 0; i < list.length; i++) {
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
        //原数据
        $.ajax({
            url: "/querypowerbyid?pid=" + getUrlParam("pid"),
            type: "get",
            dataType: "json",
            contentType: "application/json",
            traditional: true,
            success: function (data) {
                $("#panme").val(data.pname);
                $("#url").val(data.url);
                $("#pid").val(data.pid);
                var t = $('#cc').combotree('tree');
                t.tree("check", data.fp_id)
            },
            error: function (msg) {
                alert("出错了！");
            }
        });
        //修改数据
        $('#seave').click(function () {

            $.ajax({
                type: "Post",
                url: "/updatapower",
                data: $('#formset').serialize(), //要发送的是ajaxFrm表单中的数据
                error: function (request) {
                    prompt_alert("error", "错误！！");
                },
                success: function (data) {
                    prompt_alert("success", data, "index.jsp");
                }
            });
        });
    });

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }
</script>