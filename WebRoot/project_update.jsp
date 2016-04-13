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
		<title>编辑项目</title>
	</head>
	<body>
		<form action="projectAction!updateProject" method="post"
			class="form form-horizontal" onsubmit="return checkProject();">
			<s:hidden name="project.id"></s:hidden>
			<s:hidden name="project.yxarea.id"></s:hidden>
			<s:hidden name="areaIndex"></s:hidden>
			<div class="xmWraper ">
				<div class="xmconbox pd-20">
					<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
						<nav>
						<input type="submit" class="btn btn-success radius r mr-5 f-r"
							style="line-height: 1.6em; margin-top: 3px" value="保存项目" />
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
							<a href="yxareaAction!list" target="_parent"><span
								class="label label-warning radius">片区切换</span> </a>
						</p>
					</div>
					<div class="xmconbox pd-20">
						<div class="row cl box-shadow pd-10  bk-gray radius"
							style="background-color: #FFF;">
							<h1
								style="font-size: 38px; line-height: 45px; width: 100%; margin-bottom: 15px; text-align: center;">
								建筑工程项目概况
							</h1>

							<table id="gcgk" cellspacing="0"
								class="bk-gray radius box-shadow">
								<tbody>
									<tr class="odd-row">

										<th class="th2 first">
											工程分类
										</th>
										<td>
											<s:select list="#{0:'土建',1:'装饰',2:'绿化',3:'照明亮化'}"
												cssClass="select" name="project.engineeringType"
												listKey="key" listValue="value" cssStyle="width:200px"></s:select>
										</td>
										<th class="th2">
											监督员
										</th>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input" name="project.supervisor" />
											</span>
										</td>
									</tr>
									<tr class="odd-row">

										<th class="th2 first">
											项目分类
										</th>
										<td>
											<s:select
												list="#{0:'房地产开发',1:'安置房',2:'政府投资项目',3:'重点项目',4:'一般项目'}"
												cssClass="select" name="project.projectType" listKey="key"
												listValue="value" cssStyle="width:200px"></s:select>
										</td>
										<th class="th2 first">
											建筑分类
										</th>
										<td>
											<s:select list="#{0:'住宅',1:'公共建筑',2:'工业建筑'}"
												cssClass="select" name="project.buildingType" listKey="key"
												listValue="value" cssStyle="width:200px"></s:select>
										</td>
									</tr>
									<tr>

										<th class="th2 first">
											工程名称
										</th>
										<td>
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.name" /> </span>
										</td>
										<th class="th2">
											工程地点
										</th>
										<td class="last">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input"
													name="project.engineeringPlace" /> </span>
										</td>
									</tr>
									<tr>
										<th class="th2 first">
											项目经理
										</th>
										<td>
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.projectManager" />
											</span>
										</td>
										<th class="th2">
											工程总监
										</th>
										<td class="last">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input"
													name="project.engineeringDirector" /> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											开工日期
										</th>
										<td>
											<input type="text" class="mini-textbox-input"
												name="project.startDate"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												value="<s:property value="project.startDate"/>" id="logmin"
												class="input-text Wdate">
										</td>
										<th class="th2">
											竣工日期
										</th>
										<td class="last">
											<span class="mini-textbox-border"><input type="text"
													class="mini-textbox-input" name="project.planendDate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
													value="<s:property value="project.planendDate"/>"
													id="logmin" class="input-text Wdate"> </span>
										</td>
									</tr>
									<tr>
										<th class="th2 first">
											建筑总面积（平方米）
										</th>
										<td>
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.buildingArea" />
											</span>
										</td>
										<th class="th2">
											形象进度
										</th>
										<td class="last">
											<span class="mini-textbox-border"> <s:if
													test="project.engineeringType==0">
													<s:select list="#{0:'基础',1:'主体',2:'装饰',3:'完工待验',4:'竣工'}"
														cssClass="select" name="project.graphicProgress"
														listKey="key" listValue="value" cssStyle="width:200px"></s:select>

												</s:if> <s:else>
													<s:select
														list="#{0:'20%',1:'40%',2:'60%',3:'80%',4:'100%'}"
														cssClass="select" name="project.graphicProgress"
														listKey="key" listValue="value" cssStyle="width:200px"></s:select>
												</s:else> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											结构层次
										</th>
										<td>
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.structureLevel" />
											</span>
										</td>
										<th class="th2">
											单位工程
										</th>
										<td class="last">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.buildingNumber" />
											</span>
										</td>
									</tr>
									<tr>
										<th class="th2 first">
											施工许可证办理日期
										</th>
										<td>
											<span class="mini-textbox-border"><input type="text"
													class="mini-textbox-input"
													name="project.constructionPermitDate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
													value="<s:property value="project.constructionPermitDate"/>"
													id="logmin" class="input-text Wdate"> </span>
										</td>
										<th class="th2">
											施工许可证证号
										</th>
										<td class="last">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input"
													name="project.constructionPermitNumber" /> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											工程投资金额（万元）
										</th>
										<td>
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.buildingCost" />
											</span>
										</td>
										<th class="th2">
											投资来源
										</th>
										<td class="last">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input"
													name="project.sourcesOfInvestment" /> </span>
										</td>
									</tr>
									<tr>

										<th class="th2 first">
											清欠负责人
										</th>
										<td>
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.clearPrincipal" />
											</span>
										</td>
										<th class="th2">
											清欠负责人联系电话
										</th>
										<td class="last">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input"
													name="project.clearPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr>

										<th class="th2 first">
											是否本月开工
										</th>
										<td>
											<span class="mini-textbox-border"><s:select
													list="#{0:'否',1:'是'}" cssClass="select"
													name="project.isNewProjectMonth" listKey="key"
													listValue="value" cssStyle="width:200px"></s:select> </span>
										</td>
										<th class="th2">
											创优目标
										</th>
										<td class="last">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input"
													name="project.excellenceGoals" /> </span>
										</td>
									</tr>
								</tbody>
							</table>

							<table id="gcgk" cellspacing="0"
								class="bk-gray radius box-shadow" style="margin-top: 20px;">
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
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input" name="project.buildUnit" />
											</span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.buildUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.buildUnittelphone" /> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											勘察单位
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.reconnaissanceUnit" /> </span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.reconnaissanceUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.reconnaissanceUnitPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr>
										<th class="th2 first">
											设计单位
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input" name="project.designUnit" />
											</span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.designUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.designUnitPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											施工总包单位
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.constructionUnit" /> </span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.constructionUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.constructionUnitPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr>
										<th class="th2 first">
											监理单位
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.supervisionUnit" /> </span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.supervisionUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.supervisionUnitPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											检测机构
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input" name="project.detectionUnit" />
											</span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.detectionUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.detectionUnitPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr>
										<th class="th2 first">
											商品砼生产单位
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input" name="project.concreteUnit" />
											</span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.concreteUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.concreteUnitPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											预拌砂浆生产单位
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input" name="project.mortarUnit" />
											</span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.mortarUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.mortarUnitPrincipalTelphone" /> </span>
										</td>
									</tr>
									<tr>
										<th class="th2 first">
											施工图审查机构
										</th>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.constructionDrawingUnit" /> </span>
										</td>
										<td>
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.constructionDrawingUnitPrincipal" /> </span>
										</td>
										<td class="last">
											<span class="mini-textbox-border"> <s:textfield
													cssClass="mini-textbox-input"
													name="project.constructionDrawingUnitPrincipalTelphone" />
											</span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first" colspan="1">
											备注
										</th>
										<td colspan="3">
											<span class="mini-textbox-border"><s:textfield
													cssClass="mini-textbox-input" name="project.premarks" /> </span>
										</td>
									</tr>
									<tr class="odd-row">
										<th class="th2 first">
											施工分包单位
										</th>
										<td colspan="3">
											<div class="row">
												<div class="col-md-2">
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
												<div class="col-md-2">
													<button type="button" class="btn btn-success radius"
														id="button" name=""
														onClick="addPage('新增分包单位','subunitAction!goToAdd?pid=<s:property value="project.id"/>','650','300')">
														<i class="Hui-iconfont">&#xe600;</i>新增分包单位
													</button>
												</div>
											</div>
										</td>
									</tr>
									
									<tr>
										<th class="th2 first">
										</th>
										<td colspan="3">
											<div class="row">
												<div class="col-md-2">
													单位名称
												</div>
												<div class="col-md-2">
													项目负责人
												</div>
												<div class="col-md-4">
													负责人联系电话
												</div>
												<div class="col-md-2">
													是
												</div>
												<div class="col-md-2">
													<a style="text-decoration: none" class="ml-5"
														onclick="addPage('编辑查证信息','judgeAction!load?jid=<s:property value="id" />','500','300')"
														href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
													</a>
													<a style="text-decoration: none" class="ml-5"
														href="javascript:;"
														onclick="deleteJudge(<s:property value="id" />);"
														title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
												</div>
											</div>
										</td>
									</tr>
									
									
									<tr>
										<th class="th2 first">
										</th>
										<td colspan="3">
											<div class="row">
												<div class="col-md-2">
													单位名称
												</div>
												<div class="col-md-2">
													项目负责人
												</div>
												<div class="col-md-4">
													负责人联系电话
												</div>
												<div class="col-md-2">
													是
												</div>
												<div class="col-md-2">
													<a style="text-decoration: none" class="ml-5"
														onclick="addPage('编辑查证信息','judgeAction!load?jid=<s:property value="id" />','500','300')"
														href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
													</a>
													<a style="text-decoration: none" class="ml-5"
														href="javascript:;"
														onclick="deleteJudge(<s:property value="id" />);"
														title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
												</div>
											</div>

										</td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
		</form>
	</body>
</html>