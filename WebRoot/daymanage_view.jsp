<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<title>日常监管</title>
	</head>
	<body>
		<form action="daymanageAction!updateDaymanage" method="post"
			class="form form-horizontal">
			<s:hidden name="pid"></s:hidden>
			<s:hidden name="areaIndex"></s:hidden>
			<s:hidden name="daymanage.id"></s:hidden>
			<div class="xmWraper ">
				<div class="xmconbox pd-20">
					<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
						<nav>
						<a class="btn btn-success radius r mr-5 f-r"
							style="line-height: 1.6em; margin-top: 3px"
							href="projectAction!bench?id=<s:property value="project.id"/>&areaIndex=<s:property value="project.yxarea.areaIndex"/>"
							target="_self" title="返回项目工作台">返回项目工作台 </a>
						<input type="submit" value="保存"
							style="line-height: 1.6em; margin-top: 3px"
							class="btn btn-success radius r mr-5 f-r" />
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
							- 日常监管
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
										五方责任书是否签订
									</td>
									<td>
										<div class="formControls col-12">
											<s:radio theme="simple" id="isFiveSigned"
												cssStyle="width:36px" cssClass="check-box"
												list='#{0:"否",1:"是"}' name="daymanage.isFiveSigned" />
										</div>
									</td>
									<td>
										<span class="formControls col-4">签订日期：</span>
										<span class="formControls col-8"> <input type="text"
												width="45%" class="input-text radius size-M Wdate"
												name="daymanage.signTime"
												value="<s:property value="daymanage.signTime"/>"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" /> </span>

									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										2
									</td>
									<td>
										质量安全告知
									</td>
									<td>
										<div class="formControls col-12">
											<s:radio theme="simple" id="isMassSafeNotify"
												cssStyle="width:36px" cssClass="check-box"
												list='#{0:"否",1:"是"}' name="daymanage.isMassSafeNotify" />
										</div>
									</td>
									<td>
										<span class="formControls col-4">告知日期：</span>
										<span class="formControls col-8"> <input type="text"
												width="45%" class="input-text radius size-M Wdate"
												name="daymanage.notifyTime"
												value="<s:property value="daymanage.notifyTime"/>"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" /> </span>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										3
									</td>
									<td>
										三级教育开展情况
									</td>
									<td>
										<div class="formControls col-12">
											<s:radio theme="simple" id="isEducationLaunch"
												cssStyle="width:36px" cssClass="check-box"
												list='#{0:"未开展",1:"已开展"}' name="daymanage.isEducationLaunch" />
										</div>
									</td>
									<td>
										<div class="skin-minimal ">
											<s:checkboxlist theme="simple" cssStyle="width:36px"
												name="daymanage.launchContent" list="{'纸质','图片','VCR'}"
												value="launchContents" />
											<button type="button" class="btn btn-success radius"
												id="button" name=""
												onClick="addPage('新增教育图片','educationpicAction!goToAdd?dayid=<s:property value="daymanage.id"/>','650','300')">
												新增教育图片
											</button>
										</div>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										&nbsp;
									</td>
									<td colspan="5">
										<table
											class="table table-bordered table-striped table-hover col-12">
											<tr>
												<td>
													图片名称
												</td>
												<td>
													查看图片
												</td>
												<td>
													操作
												</td>

											</tr>
											<s:if test="%{daymanage.educationpics.size()==0}">
												<td colspan="3" align="center">
													暂无教育图片
												</td>
											</s:if>
											<s:iterator value="daymanage.educationpics"
												var="educationpic" status="status">
												<tr>
													<td width="20%">
														<s:property value="picName" />
													</td>
													<td width="20%">
														<s:if test="picDir!=null&&picDir!=''">
															<a href="javascript:void(0);"
																onClick="addPage('查看教育图片','educationpicAction!view?id=<s:property value="id"/>','650','300')"><img
																	width="45px;" height="35px;" src="images/picture.png"></img>
															</a>
														</s:if>
														<s:else>
															暂无教育图片
														</s:else>

													</td>
													<td width="20%">
														<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
													</td>
												</tr>
											</s:iterator>

										</table>

									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										4
									</td>
									<td>
										行为监督抽查
									</td>
									<td>
										合计：
										<s:property value="behaviorsheetNumber" />
										次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=1,2,5,6"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										5
									</td>
									<td>
										超过一定规模危险性较大分部分项工程专家论证情况
									</td>
									<td>
										<div class="formControls col-12">
											<s:radio theme="simple" id="isDangerArgument"
												cssStyle="width:36px" cssClass="check-box"
												list='#{0:"未论证",1:"已论证"}' name="daymanage.isDangerArgument" />
										</div>
									</td>
									<td>
										<button type="button" class="btn btn-success radius"
											id="button" name=""
											onClick="addPage('新增论证情况','dangerargumentAction!goToAdd?dayid=<s:property value="daymanage.id"/>','650','300')">
											新增论证情况
										</button>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										&nbsp;
									</td>
									<td colspan="5">
										<table
											class="table table-bordered table-striped table-hover col-12">
											<tr>
												<td width="20%">
													专家认证类型
												</td>
												<td width="20%">
													认证时间
												</td>
												<td width="20%">
													实施时间
												</td>
												<td width="20%">
													完成时间
												</td>
												<td width="20%">
													操作
												</td>

											</tr>
											<s:if test="%{daymanage.dangerarguments.size()==0}">
												<td colspan="4" align="center">
													暂无任何专家论证具体情况
												</td>
											</s:if>
											<s:iterator value="daymanage.dangerarguments"
												var="dangerargument" status="status">
												<tr>
													<td width="20%">
														<s:property value="argTypeName" />
													</td>
													<td width="20%">
														<s:property value="argTime" />
													</td>
													<td width="20%">
														<s:property value="implTime" />
													</td>
													<td width="20%">
														<s:property value="finishTime" />
													</td>
													<td width="20%">
														<a style="text-decoration: none" class="ml-5"
															onclick="addPage('编辑专家论证','dangerargumentAction!load?danid=<s:property value="id" />','500','300')"
															href="javascript:;" title="编辑"><i
															class="Hui-iconfont">&#xe6df;</i> </a>
														<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteDangerargument(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
													</td>
												</tr>
											</s:iterator>

										</table>

									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										6
									</td>
									<td>
										日常巡查
									</td>
									<td>
										合计：
										<s:property value="daysheetNumber" />
										次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=3,4"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										7
									</td>
									<td>
										检查及整改情况
									</td>
									<td>
										合计：
										<s:property value="checksheetNumber" />
										次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=7"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										8
									</td>
									<td>
										抽测抽查
									</td>
									<td>
										合计：
										<s:property value="choucesheetNumber" />
										次
									</td>
									<td>
										<a
											href="spreadsheetAction!list?pid=<s:property value="pid"/>&sheetTypeStr=8,9,10,11,12"
											class="btn btn-success radius">详细内容</a>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										9
									</td>
									<td>
										永久性铭牌安装落实情况
									</td>
									<td>
										<div class="formControls col-12">
											<s:radio theme="simple" id="isNameplateInstall"
												cssStyle="width:36px" cssClass="check-box"
												list='#{0:"未落实",1:"已落实"}'
												name="daymanage.isNameplateInstall" />
										</div>
									</td>
									<td>
										<span class="formControls col-4">落实日期：</span>
										<span class="formControls col-8"> <input type="text"
												width="45%" class="input-text radius size-M Wdate"
												name="daymanage.installTime"
												value="<s:property value="daymanage.installTime"/>"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" /> </span>
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										10
									</td>
									<td>
										预拌砂浆用量是否达标
									</td>
									<td>
										<div class="formControls col-12">
											<s:radio theme="simple" id="isMortarQualified"
												cssStyle="width:36px" cssClass="check-box"
												list='#{0:"否",1:"是"}' name="daymanage.isMortarQualified" />
										</div>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr class="text-c">
									<td class="text-c">
										11
									</td>
									<td>
										竣工验收情况
									</td>
									<td>
										<div class="formControls col-12">
											<div class="formControls col-12">
												<s:radio theme="simple" id="isCompleted"
													cssStyle="width:36px" cssClass="check-box"
													list='#{0:"否",1:"是"}' name="daymanage.isCompleted" />
											</div>
									</td>
									<td>
										<span class="formControls col-4">竣工日期：</span>
										<span class="formControls col-8"> <input type="text"
												width="45%" class="input-text radius size-M Wdate"
												name="daymanage.completedTime"
												value="<s:property value="daymanage.completedTime"/>"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" /> </span>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>

			</div>
		</form>
	</body>
</html>