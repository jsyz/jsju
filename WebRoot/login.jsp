<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<link href="css/H-ui.login.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/miniui.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<title>宜兴市建设局工程建设管理平台</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
	</head>
	<body>
	  <form name="userForm"  action="useroAction!login" method="post">
		<div class="loginWraper">
			<div class="minmi-fit"
				style="position: absolute; top: 27%; left: 32%">
				<div style="margin: 0 auto; width: 463px; margin-top: 2%;">
					<img src="images/title1.png" width="560">
					<span style="color:#ff0000;font-family:Arial,Verdana,Sans-serif;font-size:16px;margin-left:30px;">${loginFail }</span><br />
					<br>
					<img src="images/title21.png" width="560" height="35">
					<div
						style="width: 560px; height: 102px; background: url(images/dengl_bg_2.png) no-repeat;">
						
						
						<div id="login_Container"
							style="width: 515px; height: 53px; position: relative; top: 23px; left: 24px;">
							<div style="margin-right: 5px; margin-left: 10px;">
								<span style="border-width: 0px;"
									class="mini-textbox login_input user mini-required"><span
									class="mini-textbox-border"><input name="username"
											placeholder="登陆名" class="mini-textbox-input"
											autocomplete="off" type="text">
								</span>
								</span>
							</div>
							<div style="margin-right: 5px">
								<span style="border-width: 0px;"
									class="mini-textbox mini-password login_input pwd mini-required"><span
									class="mini-textbox-border"><input name="password"
											placeholder="密码" class="mini-textbox-input" autocomplete="off"
											type="password">
								</span>
								</span>
							</div>
							<div>
								<input type="submit" value=""  class="mini-button login_button" />
							</div>
						</div>
					</div>
					<div style="width: 560px; text-align: center; color: #fff;">
						Copyright©2005-2012 宜兴市建设局 jsj.yixing.gov.cn All rights reserved
					</div>
				</div>
			</div>
		</div>
		</form>
	</body>
</html>