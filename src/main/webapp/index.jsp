<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/10/18
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师综合评教系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="header" id="header">

    <div class="dl-title">
        <span>教师综合评教系统</span>
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${userinfo.uname}</span><a href="/quit" title="退出系统"
                                                                                 class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform">
            <div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div>
        </div>
        <ul id="J_Nav" class="nav-list ks-clear">
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-home">菜单栏</div>
            </li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>
    $(function () {
        $.ajax({
            url: "/selectPowerByRid?rid=" +${userinfo.rid},    //后台webservice里的方法名称
            type: "GET",
            processData: false,
            contentType: false,
            success: function (data) {
                var config = eval(data);
                BUI.use('common/main', function () {
                    new PageUtil.MainPage({
                        modulesConfig: config
                    });
                });
            },
            error: function (msg) {
                prompt_alert("error", msg);
            }
        });
    });
</script>
<div style="text-align:center;">

</div>
</body>
</html>