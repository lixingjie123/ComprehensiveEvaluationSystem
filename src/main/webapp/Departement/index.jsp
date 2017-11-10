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
<script>
    $(function () {
		$('#addnew').click(function(){
				window.location.href="add.html";
		 });
		 var $table = $('#dtable');
         $table.bootstrapTable({
         url: "/selectPageDepart",
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
             title: '系部id',
             field: 'dept_id',
             align: 'center',
             valign: 'middle'
         },
         {
             title: '系部名称',
             field: 'dept_name',
             align: 'center',
             valign: 'middle', 
         },
         {
             title: '操作',
             field: 'dept_id',
             align: 'center',
             formatter:function(value,row,index){
                 var e = '<a href="#" mce_href="#" onclick="edit(\''+ row.dept_id + '\')">编辑</a> ';
                 var d = '<a href="#" mce_href="#" onclick="del(\''+ row.dept_id +'\')">删除</a> ';
                 return e+d;
             }
         }
     ]
 });
    });

	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = "index.html";
			
			window.location.href=url;		
		
		}
	}
</script>
</head>
<body>
<form class="form-inline definewidth m20" action="index.html" method="get">    
    用户名称：
    <input type="text" name="username" id="username"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增系部</button>
</form>
<table class="table table-bordered table-hover definewidth m10" id="dtable">
</table>
</body>
</html>
