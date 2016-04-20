<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@
taglib uri="/struts-tags" prefix="s"%>
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
		<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
			type="text/css" />
		<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
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
		<title>项目评价表格管理</title>
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<nav>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="projectAction!bench?id=<s:property value="project.id"/>&areaIndex=<s:property value="project.yxarea.areaIndex"/>"
						target="_self" title="返回项目工作台">返回项目工作台 </a>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新当前页"><i
						class="Hui-iconfont">&#xe68f;</i> </a>

					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:history.go(-1);" title="返回"><i
						class="Hui-iconfont">&#xe66b;</i> </a>
					</nav>
					<p
						style="line-height: 35px; margin-bottom: 0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);">
						<i class="Hui-iconfont">&#xe64b;</i> 当前片区：
						<s:property value="areaVO.areaName" />
						<span class="pipe">|</span>【项目总数
						<s:property value="#session.areaVO.projectNumberTotal" />
						建筑面积
						<s:property value="#session.areaVO.buildingAreaTotal" />
						m
						<sup>
							2
						</sup>
						造价
						<s:property value="#session.areaVO.buildingCostTotal" />
						万 】
						<a href="yxareaAction!list"><span
							class="label label-warning radius">片区切换</span> </a>
					</p>
				</div>
			</div>
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<p>
						<i class="Hui-iconfont">&#xe623;</i>
						<s:property value="project.name" />
						-
						<s:property value="pageName" />
					</p>
				</div>
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<table class="table table-border table-bordered radius table-bg "
						style="background-color: #FFF">
						<thead class="text-c">
							<tr>
								<th width="5%">
									序号
								</th>
								<th width="37%">
									内容
								</th>
								<th width="33%">
									状态
								</th>
								<th width="25%">
									详情
								</th>
							</tr>
						</thead>
						<tbody>
							<tr class="text-c">
								<td class="text-c">
									1
								</td>
								<td>
									工程质量不良行为记录表
								</td>
								<td>
									合计：<s:property value="projectEvaluateSheet.badBehaviorNumber"/>次
								</td>
								<td>
									<a
										href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=13"
										class="btn btn-success radius">详细内容</a>
								</td>
							</tr>
							<tr class="text-c">
								<td class="text-c">
									2
								</td>
								<td>
									项目经理扣分情况记录表
								</td>
								<td>
									合计：<s:property value="projectEvaluateSheet.managerPointNumber"/>次
								</td>
								<td>
									<a
										href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=14"
										class="btn btn-success radius">详细内容</a>
								</td>
							</tr>
							<tr class="text-c">
								<td class="text-c">
									3
								</td>
								<td>
									行政处罚情况记录表
								</td>
								<td>
									合计：<s:property value="projectEvaluateSheet.administrativeSanctionNumber"/>次
								</td>
								<td>
									<a
										href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=15"
										class="btn btn-success radius">详细内容</a>
								</td>
							</tr>
							<tr class="text-c">
								<td class="text-c">
									4
								</td>
								<td>
									奖惩情况记录表
								</td>
								<td>
									合计：<s:property value="projectEvaluateSheet.rewardsAndPenaltiesNumber"/>次
								</td>
								<td>
									<a
										href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=16"
										class="btn btn-success radius">详细内容</a>
								</td>
							</tr>
							<tr class="text-c">
								<td class="text-c">
									5
								</td>
								<td>
									民工工资投诉处理记录表
								</td>
								<td>
									合计：<s:property value="projectEvaluateSheet.workerWageNumber"/>次
								</td>
								<td>
									<a
										href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=17"
										class="btn btn-success radius">详细内容</a>
								</td>
							</tr>
							<tr class="text-c">
								<td class="text-c">
									6
								</td>
								<td>
									安全生产事故情况记录表
								</td>
								<td>
									合计：<s:property value="projectEvaluateSheet.safeProductionNumber"/>次
								</td>
								<td>
									<a
										href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=18"
										class="btn btn-success radius">详细内容</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>