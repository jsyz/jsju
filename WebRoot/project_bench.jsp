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
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<link href="css/city.css" rel="stylesheet" type="text/css" />
		<title>项目视图</title>
	</head>
	<body>
		<div class="cityWraper">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<nav>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新当前页"><i
						class="Hui-iconfont">&#xe68f;</i> </a><a
						class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:history.go(-1);" title="返回"><i
						class="Hui-iconfont">&#xe66b;</i> </a>
					</nav>
					<p
						style="line-height: 35px; margin-bottom: 0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);">
						<i class="Hui-iconfont">&#xe64b;</i> 当前片区：
						<s:property value="areaVO.areaName" />
						<span class="pipe">|</span>【项目总数
						<s:property value="areaVO.projectNumberTotal" />
						建筑面积
						<s:property value="areaVO.buildingAreaTotal" />
						m
						<sup>
							2
						</sup>
						造价
						<s:property value="areaVO.buildingCostTotal" />
						万 】
						<a href="yxareaAction!list"><span
							class="label label-warning radius">片区切换</span> </a>
					</p>
				</div>
			</div>
			<div class="xmbox">
				<h1 class="text-c c-white text-shadow pb-20">
					<s:property  value="project.name"/>
				</h1>
				<img src="images/xm.png" width="965" height="355" usemap="#Map">
				<map name="Map">
					<area shape="rect" coords="295,202,378,285" href="xm-jk.html">
					<area shape="rect" coords="438,202,532,285" href="xm-map.html">
					<area shape="rect" coords="582,202,660,283" href="xm-ryxx.html">
					<area shape="rect" coords="39,21,167,151" href="projectAction!view?id=<s:property value="project.id"/>&areaIndex=<s:property value="project.yxarea.areaIndex"/>">
					<area shape="rect" coords="187,15,320,151" href="daymanageAction!view?pid=<s:property value="project.id"/>&areaIndex=<s:property value="project.yxarea.areaIndex"/>">
					<area shape="rect" coords="340,16,470,147" href="xm-sbgl.html">
					<area shape="rect" coords="187,15,320,151" href="xm-rcjg_gl.html">
					<area shape="rect" coords="340,16,470,147" href="deviceAction!list?project_id=<s:property value="project.id"/>&areaIndex=<s:property value="project.yxarea.areaIndex"/>">
					<area shape="rect" coords="492,16,620,147" href="xm-wmsg.html">
					<area shape="rect" coords="647,18,775,153" href="xmpj.html">
					<area shape="rect" coords="798,22,925,153" href="xm-da.html">
				</map>
			</div>
		</div>
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
	</body>
</html>