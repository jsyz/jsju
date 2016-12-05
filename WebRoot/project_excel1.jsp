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
		<title>项目管理</title>
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<nav>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="useroAction!index" target="_top" title="返回主菜单">返回主菜单 </a>
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
						<i class="Hui-iconfont">&#xe64b;</i> 当前页面：项目报表导出
						<span class="pipe">|</span>【项目总数
						<s:property value="projectNumberTotal" />
						建筑面积
						<s:property value="buildingAreaTotal" />
						m
						<sup>
							2
						</sup>
						造价
						<s:property value="buildingCostTotal" />
						万 】
					</p>
				</div>
			</div>
			<div class="xmconbox pd-20">
				<div class="row cl box-shadow pd-10  bk-gray radius"
					style="background-color: #FFF;">

					<div class="cl pd-5 bg-1 bk-gray mb-20">
						<span class="l"> <a class="btn btn-primary radius"
							href="projectAction!count?excelPageType=0"> 项目列表格式1</a> <a
							class="btn btn-primary radius"
							href="projectAction!count?excelPageType=1"> 项目列表格式2</a> </span>

					</div>
					<a href="projectAction!outputExcel1" class="btn btn-success">导出</a>

					<div class="mt-20">
						<table
							class="table table-border table-bordered table-hover table-bg">
							<thead>
								<tr class="text-c">
									<th width="51">
										序号
									</th>
									<th width="97">
										总分类
									</th>
									<th width="151">
										具体分类
									</th>
									<th width="151">
										项目数
									</th>
									<th width="71">
										面积m
										<sup>
											2
										</sup>
									</th>
									<th width="70">
										造价(万)
									</th>
								</tr>
							</thead>
							<tbody>
								<s:if test="%{projectClassifys.size()==0}">
									<td colspan="14" align="center">
										暂无任何项目
									</td>
								</s:if>
								<s:iterator value="projectClassifys" var="projectClassify"
									status="status">
									<tr class="text-c">
										<td>
											<s:property value="#status.index+1" />
										</td>
										<td class="text-c">
											<s:property value="totalClassifyName" />
										</td>
										<td>
											<s:property value="classifyName" />
										</td>
										<td>
											<s:property value="projectNumberTotal" />
										</td>
										<td>
											<s:property value="buildingAreaTotal" />
										</td>
										<td>
											<s:property value="buildingCostTotal" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
	</body>
</html>