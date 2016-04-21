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
		<title>档案管理</title>
	</head>
	<body>
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

				<div id="tab-system" class="HuiTab">
					<div class="tabBar cl">
						<span>质 监</span><span>安 监</span>
					</div>
					<div class="tabCon">
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
										工程质量监督工作计划
									</td>
									<td>
										合计：
										<s:property value="sheetNumber.sheet19" />
										次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=19"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										2
									</td>
									<td>
										工程质量行为资料监督抽查记录
									</td>
									<td>
										合计：
										<s:property value="sheetNumber.sheet1" />
										次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=1"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										3
									</td>
									<td>
										工程质量监督抽查（巡查）记录
									</td>
									<td>
										合计：
										<s:property value="sheetNumber.sheet3" />
										次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=3"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										4
									</td>
									<td>
										工程实体质量抽测记录
									</td>
									<td>
										合计：<s:property value="sheetNumber.entityQualityNumber" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=8,9,10,11,12"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										5
									</td>
									<td>
										工程质量监督抽检通知书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet22" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=22"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										6
									</td>
									<td>
										工程质量整改完成报告
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet23" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=23"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										7
									</td>
									<td>
										工程局部停工（暂停）通知书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet24" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=24"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										8
									</td>
									<td>
										工程复工申请报告
									</td>
									<td>
										合计：<s:property value="archivesSheet.sheet25" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=25"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										9
									</td>
									<td>
										工程复工通知书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet26" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=26"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										10
									</td>
									<td>
										工程质量不良行为记录表
									</td>
									<td>
										合计：<s:property value="sheetNumber.badBehaviorNumber" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=13"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										11
									</td>
									<td>
										工程质量事故处理监督记录
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet27" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=27"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										12
									</td>
									<td>
										建设工程质量申请行政处罚报告
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet27" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=28"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										13
									</td>
									<td>
										单位（子单位）工程质量竣工验收监督记录
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet29" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=29"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										14
									</td>
									<td>
										建设工程质量监督报告
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet30" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=30"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										15
									</td>
									<td>
										工程质量监督人员情况一览表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet31" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=31"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										16
									</td>
									<td>
										其它资料
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet32" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=32"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										17
									</td>
									<td>
										中止施工申请书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet33" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=33"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										18
									</td>
									<td>
										恢复施工安全监督申请书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet34" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=34"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="tabCon">
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
										建设工程安全监督通知书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet35" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=35"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										2
									</td>
									<td>
										建设工程安全生产监督告知书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet36" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=36"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										3
									</td>
									<td>
										建设工程安全监督交底记录
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet37" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=37"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										4
									</td>
									<td>
										建设单位安全生产行为监督检查表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet6" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=6"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										5
									</td>
									<td>
										监理单位安全生产行为监督检查表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet5" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=5"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										6
									</td>
									<td>
										施工单位安全生产行为监督检查表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet2" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=2"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										7
									</td>
									<td>
										建设工程安全生产监督抽查记录表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet4" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=4"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										8
									</td>
									<td>
										建设工程安全生产约谈记录
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet38" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=38"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										9
									</td>
									<td>
										建设工程施工安全隐患整改通知书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet39" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=39"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										10
									</td>
									<td>
										建设工程施工安全隐患整改完成报告书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet40" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=40"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										11
									</td>
									<td>
										建设工程施工复工申请书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet41" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=41"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										12
									</td>
									<td>
										建设工程施工复工通知书
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet42" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=42"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										13
									</td>
									<td>
										建设工程施工阶段安全自检评定表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet43" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=43"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										14
									</td>
									<td>
										行政处罚记录表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet15" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=15"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										15
									</td>
									<td>
										建设工程安全事故快报表
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet44" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=44"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										16
									</td>
									<td>
										建设工程安全监督报告
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet45" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=45"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										17
									</td>
									<td>
										其它资料
									</td>
									<td>
										合计：<s:property value="sheetNumber.sheet46" />次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=46"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
		<script type="text/javascript"
			src="lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript"
			src="lib/webuploader/0.1.5/webuploader.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>

		<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
});

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '10%'
	});
});

/*案例-疑难*/
function difficult_start(title,url,w,h){
	var index = layer.open({
		type: 2,
		area: ['800px', '500px'],
		title: title,
		content: url
	});
	
}

</script>
	</body>
</html>