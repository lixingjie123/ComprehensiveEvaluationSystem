<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <link rel="stylesheet" type="text/css" href="../Css/jquery.treegrid.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/prompt.alert.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.css" rel="stylesheet"/>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>

    <script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
           

	<link rel="stylesheet" href="../Css/TreeGrid.css" />

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
    问卷：
     <select name="qid" id="question" class="abc input-default"></select>
    
</form>
<table class="table table-bordered table-hover definewidth m10" id="table">
	    
</table>


</body>
</html>
<script>
    $(function () {
    	
    	 $.ajax({
            url: "/selectqOption?uid=20000001",    
            type: "get",
            dataType: "json",
            contentType: "application/json",
            traditional: true,
            success: function (data) {
            	var jsonObj =data;
                var optionstring = "";
                for (var j = 0; j < jsonObj.length; j++) {

                 
                        optionstring += "<option value=\"" + jsonObj[j].qid + "\" >" + jsonObj[j].qname + "</option>";
                  

                }
                alert(optionstring)
                $("#question").append("<option selected = \"selected\" value=''>----请选择----</option> "+optionstring);

            	
            }})
            $("#question").change(function(){
            	var $table = $('#table');
                $table.bootstrapTable({
                url: "/selectteacher?qid="+$("#question").val()+"&uid=20000001",
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
                cache:false,
                
                
             

                   
                   
                    columns: [
                {
                    title: '课程编号',
                    field: 'cid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '课程名称',
                    field: 'cname',
                    align: 'center',
                    valign: 'middle',
                },
                {
                    title: '操作',
                    field: 'cid',
                    align: 'center',
                    formatter:function(value,row,index){
                        var e = '<a href="#" mce_href="#" onclick="edit(\''+ row.cid + '\',\' ' +row.cname+ '\')">编辑</a> ';
                        var d = '<a href="#" mce_href="#" onclick="del(\''+ row.cid +'\')">删除</a> ';
                        return e+d;
                    }
                }
            ]
        });

            })
        

		
 
    })


	
	        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
	function del(id)
	{
		
		
		if(confirm("确定要删除吗？"))
		{
		
               $.ajax({
				
				type: "get",
				url:"/delectpower?pid="+id, 
				
				
				error: function(request) {
					prompt_alert("error","错误！！");
				},
				success: function(data) {
					prompt_alert("success",data,"index.jsp");
			
				}
				});	
		
		}

	
	
	
	
	}
	function edit(id){
		alert
		window.location.href="/Menu/edit.jsp?pid="+id;
	}
</script>