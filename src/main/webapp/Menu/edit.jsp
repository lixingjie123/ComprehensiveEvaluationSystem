<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

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
<form  method="post" class="definewidth m20" id=formset>
<input type="hidden" name="pid" value=""  id="pid"/>
<table class="table table-bordered table-hover m10">
    <tr>
        <td width="10%" class="tableleft">上级</td>
        <td>
            <select name="fp_id" id="power">
            </select>
        </td>
    </tr>
    <tr>
        <td class="tableleft">名称</td>
        <td><input type="text" name="pname" id="panme"/></td>
    </tr>
    <tr>
        <td class="tableleft">url</td>
        <td><input type="text" name="url" id="url"/></td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button  class="btn btn-primary" type="button" id="seave">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="index.jsp";
		 });
		//原数据
		$.ajax({  
            url: "/poweroption",    //后台webservice里的方法名称  
            type: "get",  
            dataType: "json",  
            contentType: "application/json",  
            traditional: true,  
            success: function (data) {  
                
                    var jsonObj =data;  
                    var optionstring = "";
                    var i=0;
                    for (var j = 0; j < jsonObj.length; j++) {
                    	if(jsonObj[j].pid==getUrlParam("pid")){
                    		i=j;
                    	}
                    	
                    }
                    for(var j=0 ;j<jsonObj.length;j++){
                    	if(jsonObj[i].fp_id==jsonObj[j].pid){
                    		optionstring += "<option value=\"" + jsonObj[j].pid + "\" selected = 'selected' >" + jsonObj[j].pname + "</option>";  
                    	}else{
                        optionstring += "<option value=\"" + jsonObj[j].pid + "\" >" + jsonObj[j].pname + "</option>"; } 
                    }
                    $("#power").html("<option value='0'>根节点</option> "+optionstring);
                    alert($("#panme"))
                    $("#panme").val(jsonObj[i].pname);
                    $("#url").val(jsonObj[i].url);
                    $("#pid").val(jsonObj[i].pid);
                    
                 
            },  
            error: function (msg) {  
                alert("出错了！");  
            }  
        });
		//修改数据
$('#seave').click(function(){
			
			$.ajax({
				
				type: "POST",
				url:"/updatapower", 
				data:$('#formset').serialize(), //要发送的是ajaxFrm表单中的数据
				
				error: function(request) {
				alert("发送请求失败！");
				},
				success: function(data) {
					alert(data)
					window.location.href="/Menu/index.jsp";
			
				}
				});
	 });
		

    });
    
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
</script>