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
		<title>查看项目</title>
		<link rel="stylesheet" href="css/bootstrap.css" type="text/css"></link>

	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<nav>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新当前页"><i
						class="Hui-iconfont">&#xe68f;</i> </a>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:history.go(-1);" title="返回上一页"><i
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
						<a href="yxareaAction!list" target="_parent"><span
							class="label label-warning radius">片区切换</span> </a>
					</p>
				</div>

				<div class="xmconbox pd-20">
					<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
						<p>
							<i class="Hui-iconfont">&#xe606;</i>
							<s:property value="project.name" />
							进度 ：
						</p>
						<s:if test="project.engineeringType==0">
							<s:if test="project.graphicProgress==0">
								<div class="five steps">
									<span class="active step">基础</span>
									<span class="disabled step">主体</span>
									<span class="disabled step">装饰</span>
									<span class="disabled step">完工待验</span>
									<span class="disabled step">竣工</span>
								</div>
							</s:if>
							<s:elseif test="project.graphicProgress==1">
								<div class="five steps">
									<span class="active step">基础</span>
									<span class="active step">主体</span>
									<span class="disabled step">装饰</span>
									<span class="disabled step">完工待验</span>
									<span class="disabled step">竣工</span>
								</div>
							</s:elseif>
							<s:elseif test="project.graphicProgress==2">
								<div class="five steps">
									<span class="active step">基础</span>
									<span class="active step">主体</span>
									<span class="active step">装饰</span>
									<span class="disabled step">完工待验</span>
									<span class="disabled step">竣工</span>
								</div>
							</s:elseif>
							<s:elseif test="project.graphicProgress==3">
								<div class="five steps">
									<span class="active step">基础</span>
									<span class="active step">主体</span>
									<span class="active step">装饰</span>
									<span class="active step">完工待验</span>
									<span class="disabled step">竣工</span>
								</div>
							</s:elseif>
							<s:elseif test="project.graphicProgress==4">
								<div class="five steps">
									<span class="active step">基础</span>
									<span class="active step">主体</span>
									<span class="active step">装饰</span>
									<span class="active step">完工待验</span>
									<span class="active step">竣工</span>
								</div>
							</s:elseif>
						</s:if>
						<s:else>
							<div class="progress">
								<s:if test="project.graphicProgress=0">
									<div class="progress-bar" role="progressbar" aria-valuenow="20"
										aria-valuemin="0" aria-valuemax="100" style="width: 20%;">
										20%
									</div>
								</s:if>
								<s:elseif test="project.graphicProgress=1">
									<div class="progress-bar" role="progressbar" aria-valuenow="40"
										aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
										40%
									</div>
								</s:elseif>
								<s:elseif test="project.graphicProgress=2">
									<div class="progress-bar" role="progressbar" aria-valuenow="60"
										aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
										60%
									</div>
								</s:elseif>
								<s:elseif test="project.graphicProgress=3">
									<div class="progress-bar" role="progressbar" aria-valuenow="80"
										aria-valuemin="0" aria-valuemax="100" style="width: 80%;">
										80%
									</div>
								</s:elseif>
								<s:elseif test="project.graphicProgress=3">
									<div class="progress-bar" role="progressbar"
										aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
										style="width: 100%;">
										100%
									</div>
								</s:elseif>
							</div>
						</s:else>
					</div>
				</div>

				<div class="xmconbox pd-20">
					<div class="row cl box-shadow pd-10  bk-gray radius"
						style="background-color: #FFF;">
						<h1
							style="font-size: 38px; line-height: 45px; width: 100%; margin-bottom: 15px; text-align: center;">
							建筑工程项目概况
						</h1>

						<table id="gcgk" cellspacing="0" class="bk-gray radius box-shadow">
							<tbody>
								<tr class="odd-row">

									<th class="th2 first">
										工程分类
									</th>
									<td>
										<s:if test="project.engineeringType==0">土建</s:if>
										<s:elseif test="project.engineeringType==1">装饰</s:elseif>
										<s:elseif test="project.engineeringType==2">政府投资项目</s:elseif>
										<s:elseif test="project.engineeringType==3">绿化</s:elseif>
										<s:elseif test="project.engineeringType==4">照明亮化</s:elseif>
									</td>
									<th class="th2">
										监督员
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.supervisor" /> </span>
									</td>
								</tr>
								<tr class="odd-row">

									<th class="th2 first">
										项目分类
									</th>
									<td>
										<s:if test="project.projectType==0">房地产开发</s:if>
										<s:elseif test="project.projectType==1">安置房</s:elseif>
										<s:elseif test="project.projectType==2">政府投资项目</s:elseif>
										<s:elseif test="project.projectType==3">政府投资项目</s:elseif>
										<s:elseif test="project.projectType==4">一般项目</s:elseif>
									</td>
									<th class="th2 first">
										建筑分类
									</th>
									<td>
										<s:if test="project.buildingType==0">住宅</s:if>
										<s:elseif test="project.buildingType==1">公共建筑</s:elseif>
										<s:elseif test="project.buildingType==2">工业建筑</s:elseif>
									</td>
								</tr>
								<tr>

									<th class="th2 first">
										工程名称
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.name" /> </span>
									</td>
									<th class="th2">
										工程地点
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.engineeringPlace" /> </span>
									</td>
								</tr>
								<tr>
									<th class="th2 first">
										项目经理
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.projectManager" /> </span>
									</td>
									<th class="th2">
										工程总监
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.engineeringDirector" /> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										开工日期
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.startDate" /> </span>
									</td>
									<th class="th2">
										竣工日期
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.planendDate" /> </span>
									</td>
								</tr>
								<tr>
									<th class="th2 first">
										建筑总面积（平方米）
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.buildingArea" /> </span>
									</td>
									<th class="th2">
										形象进度
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:if
												test="project.engineeringType==0">
												<s:if test="project.graphicProgress==0">基础</s:if>
												<s:elseif test="project.graphicProgress==1">主体</s:elseif>
												<s:elseif test="project.graphicProgress==2">装饰</s:elseif>
												<s:elseif test="project.graphicProgress==3">完工待验</s:elseif>
												<s:elseif test="project.graphicProgress==4">竣工</s:elseif>

											</s:if> <s:else>
												<s:if test="project.graphicProgress==0">20%</s:if>
												<s:elseif test="project.graphicProgress==1">40%</s:elseif>
												<s:elseif test="project.graphicProgress==2">60%</s:elseif>
												<s:elseif test="project.graphicProgress==3">80%</s:elseif>
												<s:elseif test="project.graphicProgress==4">100%</s:elseif>
											</s:else> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										结构层次
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.structureLevel" /> </span>
									</td>
									<th class="th2">
										单位工程
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.buildingNumber" /> </span>
									</td>
								</tr>
								<tr>
									<th class="th2 first">
										施工许可证办理日期
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.constructionPermitDate" /> </span>

									</td>
									<th class="th2">
										施工许可证证号
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.constructionPermitNumber" /> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										工程投资金额（万元）
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.buildingCost" /> </span>
									</td>
									<th class="th2">
										投资来源
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.sourcesOfInvestment" /> </span>
									</td>
								</tr>
								<tr>

									<th class="th2 first">
										清欠负责人
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.clearPrincipal" /> </span>
									</td>
									<th class="th2">
										清欠负责人联系电话
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.clearPrincipalTelphone" /> </span>
									</td>
								</tr>
								<tr>

									<th class="th2 first">
										是否本月开工
									</th>
									<td>
										<span class="mini-textbox-border"> <s:if
												test="project.isNewProjectMonth==0">否</s:if> <s:elseif
												test="project.isNewProjectMonth==1">是</s:elseif> </span>
									</td>
									<th class="th2">
										创优目标
									</th>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.excellenceGoals" /> </span>
									</td>
								</tr>
							</tbody>
						</table>

						<table id="gcgk" cellspacing="0" class="bk-gray radius box-shadow"
							style="margin-top: 20px;">
							<tbody>
								<tr class="odd-row">
									<th class="th2 first last" colspan="5">
										各方主体和有关机构
									</th>
								</tr>
								<tr>
									<th class="th2">
										项目
									</th>
									<th class="th2">
										单位名称
									</th>
									<th class="th2">
										项目负责人
									</th>
									<th class="th2 last">
										联系电话
									</th>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										建设单位
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.buildUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.buildUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.buildUnittelphone" /> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										勘察单位
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.reconnaissanceUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.reconnaissanceUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.reconnaissanceUnitPrincipalTelphone" /> </span>
									</td>
								</tr>
								<tr>
									<th class="th2 first">
										设计单位
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.designUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.designUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.designUnitPrincipalTelphone" /> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										施工总包单位
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.constructionUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.constructionUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.constructionUnitPrincipalTelphone" /> </span>
									</td>
								</tr>
								<tr>
									<th class="th2 first">
										监理单位
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.supervisionUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.supervisionUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.supervisionUnitPrincipalTelphone" /> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										检测机构
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.detectionUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.detectionUnit" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.detectionUnits" /> </span>
									</td>
								</tr>
								<tr>
									<th class="th2 first">
										商品砼生产单位
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.concreteUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.concreteUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.concreteUnitPrincipal" /> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										预拌砂浆生产单位
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.concreteUnitPrincipal" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.mortarUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.mortarUnitPrincipalTelphone" /> </span>
									</td>
								</tr>
								<tr>
									<th class="th2 first">
										施工图审查机构
									</th>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.constructionDrawingUnit" /> </span>
									</td>
									<td>
										<span class="mini-textbox-border"> <s:property
												value="project.constructionDrawingUnitPrincipal" /> </span>
									</td>
									<td class="last">
										<span class="mini-textbox-border"> <s:property
												value="project.constructionDrawingUnitPrincipalTelphone" />
										</span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first" colspan="1">
										备注
									</th>
									<td colspan="3">
										<span class="mini-textbox-border"> <s:property
												value="project.premarks" /> </span>
									</td>
								</tr>
								<tr class="odd-row">
									<th class="th2 first">
										施工分包单位
									</th>
									<td colspan="3">
										<div class="row">
											<div class="col-md-4">
												单位名称
											</div>
											<div class="col-md-2">
												项目负责人
											</div>
											<div class="col-md-4">
												负责人联系电话
											</div>
											<div class="col-md-2">
												是否办理进市登记
											</div>
										</div>
									</td>
								</tr>
								<s:if test="%{project.subunits.size()==0}">
									<td colspan="4" align="center">
										暂无任何分包单位
									</td>
								</s:if>
								<s:iterator value="project.subunits" var="subunit"
									status="status">
								<tr class="odd-row">
									<th class="th2 first">
									</th>
									<td colspan="3">
										<div class="row">
											<div class="col-md-4">
												<s:property value="unitName" />
											</div>
											<div class="col-md-2">
											<s:property value="unitLeader" />
											</div>
											<div class="col-md-4">
												<s:property value="leaderPhone" />
											</div>
											<div class="col-md-2">
													<s:if test="isInCity==0">否</s:if>
													<s:else>是</s:else>
											</div>
										</div>
									</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
	</body>
</html>