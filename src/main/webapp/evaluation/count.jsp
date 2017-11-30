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
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/prompt.alert.js"></script>
    <script type="text/javascript" src="../Js/echarts.js"></script>
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
        #show{
            background-color: #ffffff;
            box-shadow: 5px 5px 5px 10px rgba(132, 132, 132, 0.6);
            width: 420px;
            padding: 0 10px 10px;
            display: none;
            position: fixed;
            z-index: 11;
        }
        #table{
            z-index: 0;
        }

    </style>
</head>
<body>

<table class="table table-bordered table-hover definewidth m10" id="table"></table>

<div id="show">
    <div id="seeDetails" style="height: 300px;width: 400px"></div>
    <div style="width: 400px">
        <button type="button" class="btn btn-success right" id="close">关闭</button>
    </div>
</div>

</body>
</html>
<script type="text/javascript">
    $(function () {


        var $table = $('#table');
        $table.bootstrapTable({
            url: "/findAllTeacherScore",
            dataType: "json",
            singleSelect: false,
            locale: 'zh-CN',//中文支持
           //定义排序字段
            pagination: true,//是否开启分页（*）
            pageNumber: 1,//初始化加载第一页，默认第一页
            pageSize: 5,//每页的记录行数（*）
            pageList: [5, 10, 11],//可供选择的每页的行数（*）
            search:true,//启用搜索框
            searchOnEnterKey:true,//回车时执行搜索
            sidePagination: "client", //客户端处理分页
            showColumns: true,//列选择按钮
            clickToSelect: true,
            toolbar: "#toolbar",//指定工具栏
            toolbarAlign: "right",//工具栏对齐方式
            detailView: false, //是否显示详情折叠
            cache: false,
            columns: [

                {
                    title: '教师名称',
                    field: 'tname',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '教师编号',
                    field: 'tid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '评教总分',
                    field: 'score',
                    align: 'center',
                    valign: 'middle',
                    sortable:true

                },{
                    title: '操作',
                    field: 'tid',
                    align: 'center',
                    formatter:function(value,row,index) {

                            return '<a href="#" mce_href="#" onclick="seeDetails(\'' + row.tid + '\')">查看详情</a> ';

                        }
                    }
            ]
        });

    });

    function seeDetails(tid) {
        var divw = $("#show").width();
        var divh  = $("#show").height();
        var winw = $(document).width();
        var winh = $(document).height();
        $.ajax({
            url: "/seeDetaill?tid="+tid,    //后台webservice里的方法名称
            type: "get",
            contentType: "application/json",
            traditional: true,
            success: function (data) {
                var myChert = echarts.init(document.getElementById("seeDetails"));
                var option ={
                    title: {
                        text: '评价详情：'+data.tname
                    },
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : data.qnames,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'评教得分',
                            type:'bar',
                            barWidth: '60%',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'inside'
                                }
                            },
                            data:data.scores
                        }
                    ]
                };
                myChert.setOption(option);
            },
            error: function (msg) {
                prompt_alert("error","出错了！");
            }
        });

        $("#show").css({
            left:(winw - divw)/2,
            top:(winh - divh)/2
        }).fadeIn(500);
    }

    $("#close").click(function () {
        $("#show").fadeOut(500);
    });

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

</script>