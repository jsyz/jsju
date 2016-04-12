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
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<title>消息提示页面</title>
		<script type="text/javascript">     
		function countDown(secs,surl){     
		 //alert(surl);     
		 surl = surl.replace("&amp;","&");
		 var jumpTo = document.getElementById('jumpTo');
		 jumpTo.innerHTML=secs;  
		 if(--secs>0){
		     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
		     }
		 else{       
		     location.href=surl;     
		     }     
		 }     
		</script>
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<p
						style="line-height: 35px; margin-bottom: 0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);">
						<i class="Hui-iconfont">&#xe64b;</i> 当前页面：消息提示页面
					</p>
				</div>
			</div>
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<div class="row cl box-shadow pd-10  bk-gray radius"
						style="background-color: #FFF;">
						<div class="pd-20">
							<div class="cl pd-5 bg-1 bk-gray Huialert Huialert-info">
								<table class="table table-border table-bordered table-bg ">
									<thead>
										<tr>
											<th>
												<span>用户操作消息:</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												恭喜您，操作成功！&nbsp;
												<span id="jumpTo">5</span>秒后跳转到
												<strong><u><a class="btn btn-primary radius"
														href="<s:property value="arg[0]"  />"><s:property
																value="arg[1]" />页面</a> </u> &nbsp;</strong>
											</td>
											<script type="text/javascript">countDown(5,'<s:property value="arg[0]"/>');</script>
										</tr>
									</tbody>
								</table>

								<table class="table  table-bg ">
									<thead>
										<tr bgcolor="#EEF4EA">
											<th colspan="2">
												<span>系统基本信息</span>
											</th>
										</tr>
									</thead>
									<tr bgcolor="#FFFFFF">
										<td bgcolor="#FFFFFF">
											感谢您的使用：
										</td>
										<td bgcolor="#FFFFFF">
											${session.userSession.realname}
										</td>
									</tr>
									<tr bgcolor="#FFFFFF">
										<td>
											系统版本信息：
										</td>
										<td>
											<span>宜兴建设局工程建设管理平台v1.0</span>
										</td>
									</tr>
								</table>
							</div>
						</div>
	</body>
</html>