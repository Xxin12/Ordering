<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>餐厅管理系统</title>

	<link href="${manageBaseUrl}/resources/plugins/fullPage/jquery.fullPage.css" rel="stylesheet"/>
	<link href="${manageBaseUrl}/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${manageBaseUrl}/resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${manageBaseUrl}/resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="${manageBaseUrl}/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
	<link href="${manageBaseUrl}/resources/css/admin.css" rel="stylesheet"/>
	<style>
	/** skins **/
	#zheng-upms-server #header {background: #29A176;}
	#zheng-upms-server .content_tab{background: #29A176;}
	#zheng-upms-server .s-profile>a{background: url(resources/images/zheng-upms.png) left top no-repeat;}
	
	#zheng-cms-admin #header {background: #455EC5;}
	#zheng-cms-admin .content_tab{background: #455EC5;}
	#zheng-cms-admin .s-profile>a{background: url(resources/images/zheng-cms.png) left top no-repeat;}
	
	#zheng-pay-admin #header {background: #F06292;}
	#zheng-pay-admin .content_tab{background: #F06292;}
	#zheng-pay-admin .s-profile>a{background: url(resources/images/zheng-pay.png) left top no-repeat;}
	
	#zheng-ucenter-home #header {background: #6539B4;}
	#zheng-ucenter-home .content_tab{background: #6539B4;}
	#zheng-ucenter-home .s-profile>a{background: url(resources/images/zheng-ucenter.png) left top no-repeat;}
	
	#zheng-oss-web #header {background: #0B8DE5;}
	#zheng-oss-web .content_tab{background: #0B8DE5;}
	#zheng-oss-web .s-profile>a{background: url(resources/images/zheng-oss.png) left top no-repeat;}
	
	#test #header {background: test;}
	#test .content_tab{background: test;}
	#test .s-profile>a{background: url(test) left top no-repeat;}
	</style>
</head>
<body>
<header id="header">
	<ul id="menu">
		<li id="guide" class="line-trigger">
			<div class="line-wrap">
				<div class="line top"></div>
				<div class="line center"></div>
				<div class="line bottom"></div>
			</div>
		</li>
		<li id="logo" class="hidden-xs">
			<span id="system_title">餐厅管理系统</span>
		</li>
		<li class="pull-right">
			<ul class="hi-menu">
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-more-vert"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="hidden-xs">
							<a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
						</li>
						<li>
							<a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:;"><i class="zmdi zmdi-delete"></i> 清除缓存</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-run"></i> 退出登录</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</header>
<section id="main">
	<!-- 左侧导航区 -->
	<aside id="sidebar">
		<!-- 个人资料区 -->
		<div class="s-profile">
			<a class="waves-effect waves-light" href="javascript:;">
				<div class="sp-pic">
					<img src="resources/images/avatar.jpg"/>
				</div>
			</a>
		</div>
		<!-- /个人资料区 -->
		<!-- 菜单区 -->
		<ul class="main-menu">
			<li>
			<a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a>
			</li>
			<li>
			<a class="waves-effect" href="javascript:Tab.addTab('餐桌管理', '${manageBaseUrl}/dinner_table.html');"><i class="zmdi zmdi-accounts-list"></i> 餐桌管理</a>
			</li>
            <li>
				<a class="waves-effect" href="javascript:Tab.addTab('食品分类', '${manageBaseUrl}/food_type.html');"><i class="zmdi zmdi-accounts-list"></i> 食品分类</a>
            </li>
            <li>
                <a class="waves-effect" href="javascript:Tab.addTab('食品管理', '${manageBaseUrl}/food.html');"><i class="zmdi zmdi-accounts-list"></i> 食品管理</a>
            </li>
            <li class="sub-menu system_menus system_1 0">
                <a class="waves-effect" href="javascript:Tab.addTab('#');"><i class="zmdi zmdi-view-list-alt"></i> 临时订单</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('未完成临时订单', '${manageBaseUrl}/temporary_order.html');"> 未完成临时订单</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('已完成临时订单', '${manageBaseUrl}/temporary_order2.html');"> 已完成临时订单</a></li>
                </ul>
            </li>
            <li class="sub-menu system_menus system_1 0">
                <a class="waves-effect" href="javascript:Tab.addTab('#');"><i class="zmdi zmdi-view-list-alt"></i> 餐桌订单</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('未结账餐桌订单', '${manageBaseUrl}/_order.html');"> 未结账餐桌订单</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('已结账餐桌订单', '${manageBaseUrl}/_order2.html');"> 已结账餐桌订单</a></li>
                </ul>
            </li>
			<li>
			<div class="upms-version">
				&copy; Ordering V1.0.0
			</div>
			</li>
		</ul>
		<!-- /菜单区 -->
	</aside>
	<!-- /左侧导航区 -->
	<section id="content">
		<div class="content_tab">
			<div class="tab_left">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
			</div>
			<div class="tab_right">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
			</div>
			<ul id="tabs" class="tabs">
				<li id="tab_home" data-index="home" data-closeable="false" class="cur">
					<a class="waves-effect waves-light">首页</a>
				</li>
			</ul>
		</div>
		<div class="content_main">
			<div id="iframe_home" class="iframe cur">
				<p><h4>餐厅点餐系统 <i style="color:#c00"></i></h4></p>
				<p><b>演示地址</b>：<a href="http://localhost:8080/ordering/manage/index.html" target="_blank">后台管理界面</a></p>
                <p><b>演示地址</b>：<a href="http://localhost:8080/ordering/index.html" target="_blank">点餐界面</a></p>
				<p><b>系统简介</b>：本系统是基于</p><br/>
				<p><h4>系统功能概述：</h4></p>
			</div>
		</div>
	</section>
</section>
<footer id="footer"></footer>

<script src="${manageBaseUrl}/resources/plugins/jquery.1.12.4.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/BootstrapMenu.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/device.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/fullPage/jquery.fullPage.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/fullPage/jquery.jdirk.min.js"></script>
<script src="${manageBaseUrl}/resources/plugins/jquery.cookie.js"></script>

<script src="${manageBaseUrl}/resources/js/admin.js"></script>
</body>
</html>