<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> <%@
taglib uri="/struts-tags" prefix="s"%> <% String path =
request.getContextPath(); String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="skin/default/skin.css" rel="stylesheet" type="text/css"
			id="skin" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<title>宜兴</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
	</head>
	<body>
		<header class="Hui-header cl">
		<div style="width: 200px; padding-top: 9px;">
		</div>
		<ul class="Hui-userbar">
			<li class="mr-10">
				你好，${session.userSession.realname}！
			</li>
			<li class="mr-5">
				<a href="javascript:;"
					onClick="ypgj('修改密码','useroAction!loadPassword','500px','300px')"
					title="修改密码"><img src="images/an01.png" width="79" height="26">
				</a>
			</li>
			<s:if test="session.userSession.userLimit==0">
			<li class="mr-5">
					<a href="useroAction!index2" target="_top" title="权限管理"><img src="images/an03.png"
						width="79" height="26"> </a>
			</li>
			</s:if>
			<li>
				<a href="javascript:;" onClick="location.href='useroAction!logout'"><img
						src="images/an02.png" width="63" height="26"> </a>
			</li>
		</ul>
		<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
		</header>


		<section class="Hui-article-box">
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="useroAction!list"></iframe>
			</div>
		</div>
		</section>
	</body>
</html>