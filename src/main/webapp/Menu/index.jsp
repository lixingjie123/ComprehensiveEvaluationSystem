<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/jquery.treegrid.css"/>
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/prompt.alert.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>

    <link rel="stylesheet" href="../Css/TreeGrid.css"/>

    <script type="text/javascript" src="../Js/TreeGrid.js"></script>

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

<form class="form-inline definewidth m20" action="index.jsp" method="get" id="form">
    菜单名称：
    <input type="text" name="pname" id="pname" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button class="btn btn-primary" id=query type="submit">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="addnew">新增菜单</button>
</form>
<table class="table table-bordered table-hover definewidth m10" id="tb">

</table>
<div id="div1"></div>

</body>
</html>
<script>
    $(function () {

        $("#form").submit(function () {
            $("#pname").val(encodeURI($("#pname").val()));
        })

        $('#addnew').click(function () {
            window.location.href = "/Menu/add.jsp";
        });
        $.ajax({
            type: "post",
            url: "/powertree?pname=" + encodeURI(getUrlParam("pname")),
            error: function (request) {
                alert("发送请求失败！");
            },
            success: function (data) {
                var resot = JSON.parse(data)
                var config = {
                    id: "tg1",
                    width: "100%",
                    renderTo: "tb",
                    headerAlign: "left",
                    headerHeight: "40",
                    dataAlign: "left",
                    indentation: "20",
                    folderOpenIcon: "../Images/folderOpen.png",
                    folderCloseIcon: "../Images/folderClose.png",
                    defaultLeafIcon: "../Images/defaultLeaf.gif",
                    hoverRowBackground: "false",
                    folderColumnIndex: "1",
                    itemClick: "itemClickEvent",
                    columns: [{
                        headerText: "",
                        headerAlign: "center",
                        dataAlign: "center",
                        width: "20"
                    },
                        {
                            headerText: "菜单名称",
                            dataField: "text",
                            headerAlign: "center",
                            width: "200"
                        },
                        {
                            headerText: "Url",
                            dataField: "attributes",
                            headerAlign: "center",
                            handler: "attributesUrl",
                            dataAlign: "center",
                        },
                        {
                            headerText: "操作",
                            dataField: "id",
                            headerAlign: "center",
                            dataAlign: "center",
                            width: "100",
                            handler: "customLook",
                        }],
                    data: resot
                };
                var treeGrid = new TreeGrid(config);
                treeGrid.show()
            }
        });
    })

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    function del(id) {

        if (confirm("确定要删除吗？")) {
            $.ajax({

                type: "get",
                url: "/delectpower?pid=" + id,

                error: function (request) {
                    prompt_alert("error", "错误！！");
                },
                success: function (data) {
                    prompt_alert("success", data, "index.jsp");

                }
            });
        }
    }

    function edit(id) {
        alert
        window.location.href = "/Menu/edit.jsp?pid=" + id;
    }
</script>