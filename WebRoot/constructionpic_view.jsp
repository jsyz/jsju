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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>查看教育图片</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/ncss.css" rel="stylesheet" type="text/css" />
		<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
			type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		<title>查看教育图片</title>
	</head>
	<body style="background: #fff;">
	<div style="margin: 0 auto;padding: 0px;width: 600px;">
		<s:if test="pic_row == 1">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.washSetImg}"/> 
		</s:if>
		<s:if test="pic_row == 2">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.waterClearImg}"/> 
		</s:if>
		<s:if test="pic_row == 3">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.doorConformImg}"/> 
		</s:if>
		<s:if test="pic_row == 4">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.guardConformImg}"/> 
		</s:if>
		<s:if test="pic_row == 5">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.adConformImg}"/> 
		</s:if>
		<s:if test="pic_row == 6">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.roadHardenImg}"/> 
		</s:if>
		<s:if test="pic_row == 7">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.roadClearImg}"/> 
		</s:if>
		<s:if test="pic_row == 8">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.labelCompleteImg}"/> 
		</s:if>
		<s:if test="pic_row == 9">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.layoutRationalImg}"/> 
		</s:if>
		<s:if test="pic_row == 10">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.meetFireImg}"/> 
		</s:if>
		<s:if test="pic_row == 11">
			<img width="640px;" height="320px;" src="<%=basePath%>${construction.measurePlaceImg}"/> 
		</s:if>
	</div>
	</body>
	<s:debug></s:debug>
</html>
