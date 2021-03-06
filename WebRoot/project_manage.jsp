﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			<div class="xmconbox pd-20">
				<div class="row cl box-shadow pd-10  bk-gray radius"
					style="background-color: #FFF;">

					<div class="cl pd-5 bg-1 bk-gray mb-20">
						<s:if test="#session.userSession.userLimit==0">
							<span class="l"> <a href="javascript:;"
								onClick="deleteAllCheckedProjects();"
								class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
									批量删除</a> <a class="btn btn-primary radius"
								href="projectAction!goToAdd?areaIndex=<s:property value="areaIndex"/>"><i
									class="Hui-iconfont">&#xe600;</i> 新增项目</a> </span>
							<span class="r">共有数据：<strong><s:property
										value="totalCount" /> </strong> 条</span>
						</s:if>

						<s:elseif test="#session.userSession.userLimit==1">
							<s:if test="#session.userSession.areaIndex==areaIndex">
								<span class="l"> <a href="javascript:;"
									onClick="deleteAllCheckedProjects();"
									class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
										批量删除</a> <a class="btn btn-primary radius"
									href="projectAction!goToAdd?areaIndex=<s:property value="areaIndex"/>"><i
										class="Hui-iconfont">&#xe600;</i> 新增项目</a> </span>
								<span class="r">共有数据：<strong><s:property
											value="totalCount" /> </strong> 条</span>

							</s:if>
						</s:elseif>

						<s:elseif test="#session.userSession.userLimit==2">
							<s:if test="#session.userSession.areaIndex==areaIndex">
								<span class="l"> <a class="btn btn-primary radius"
									href="projectAction!goToAdd?areaIndex=<s:property value="areaIndex"/>"><i
										class="Hui-iconfont">&#xe600;</i> 新增项目</a> </span>
								<span class="r">共有数据：<strong><s:property
											value="totalCount" /> </strong> 条</span>
							</s:if>
						</s:elseif>



					</div>


					<div class="row" style="margin-top: 5px; margin-bottom: 5px;">
						<div class="text-c">
							<form name="projectListForm" method="post"
								action="projectAction!list" target="_self">
								<s:hidden name="areaIndex"></s:hidden>

								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 35px;">
									<tr height="35">
										<td width="21%" align="right" style="padding-right: 50px;">
											<s:select list="#{0:'选择类型',1:'项目名称',2:'监督员'}"
												cssClass="input-text" name="con" listKey="key"
												listValue="value" cssStyle="width:180px"></s:select>
										</td>
										<td width="310px;">
											<s:textfield name="convalue" id="convalue"
												cssClass="input-text"></s:textfield>
										</td>
										<td align="left" style="padding-left: 172px;">
											<button type="submit" class="btn btn-success" id="button2"
												name="" onClick="">
												<i class="Hui-iconfont">&#xe665;</i> 查询
											</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<div class="mt-20">
						<table
							class="table table-border table-bordered table-hover table-bg">
							<thead>
								<tr class="text-c">
									<th width="35">
										<input type="checkbox" name="" value="">
									</th>
									<th width="51">
										序号
									</th>
									<th width="97">
										工程分类
									</th>
									<th width="151">
										项目名称
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
									<th width="68">
										结构层次
									</th>
									<th width="68">
										单位工程
									</th>
									<th width="68">
										施工单位
									</th>
									<th width="68">
										监理单位
									</th>
									<th width="68">
										项目经理
									</th>
									<th width="68">
										工程总监
									</th>
									<th width="68">
										监督员
									</th>
									<th width="68">
										上传状态
									</th>
									<th width="103">
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<s:if test="%{projects.size()==0}">
									<td colspan="15" align="center">
										暂无任何项目
									</td>
								</s:if>
								<s:iterator value="projects" var="project" status="status">
									<tr class="text-c">
										<td>
											<input name="indexID" class="indexID" type="checkbox"
												value="<s:property value="id"/>">
										</td>
										<td>
											<s:property value="#status.index+1+(page-1)*10" />
										</td>
										<td class="text-c">
											<s:if test="projectType==0">房地产开发</s:if>
											<s:elseif test="projectType==1">安置房</s:elseif>
											<s:elseif test="projectType==2">政府投资项目</s:elseif>
											<s:elseif test="projectType==3">重点项目</s:elseif>
											<s:elseif test="projectType==4">一般项目</s:elseif>
										</td>
										<td>
											<a
												href="projectAction!bench?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>">
												<s:property value="name" /> </a>
										</td>
										<td>
											<s:property value="buildingArea" />
										</td>
										<td>
											<s:property value="buildingCost" />
										</td>
										<td>
											<s:property value="structureLevel" />
										</td>
										<td>
											<s:property value="buildingNumber" />
										</td>
										<td>
											<s:property value="constructionUnit" />
										</td>
										<td>
											<s:property value="supervisionUnit" />
										</td>
										<td>
											<s:property value="projectManager" />
										</td>
										<td>
											<s:property value="engineeringDirector" />
										</td>
										<td>
											<s:property value="supervisor" />
										</td>
										<td>
											<s:if test="isUpload==1">已上传</s:if>
											<s:else>未上传</s:else>
										</td>
										<td class="f-15 td-manage">
											<s:if test="#session.userSession.userLimit==0">

												<a style="text-decoration: none" class="ml-5"
													href="projectAction!load?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
													title="编辑"><i class="Hui-iconfont">&#xe6df;</i> </a>
												<a style="text-decoration: none" class="ml-5"
													onclick="return confirm('你确定删除该项目吗？')"
													href="projectAction!delete?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
													title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>

											</s:if>
											<s:elseif test="#session.userSession.userLimit==1">
												<s:if test="#session.userSession.areaIndex==areaIndex">
													<a style="text-decoration: none" class="ml-5"
														href="projectAction!load?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
														title="编辑"><i class="Hui-iconfont">&#xe6df;</i> </a>
													<a style="text-decoration: none" class="ml-5"
														onclick="return confirm('你确定删除该项目吗？')"
														href="projectAction!delete?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
														title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
												</s:if>
											</s:elseif>

											<s:elseif test="#session.userSession.userLimit==2">
												<s:if test="#session.userSession.id==uid">
													<a style="text-decoration: none" class="ml-5"
														href="projectAction!load?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
														title="编辑"><i class="Hui-iconfont">&#xe6df;</i> </a>
													<a style="text-decoration: none" class="ml-5"
														onclick="return confirm('你确定删除该项目吗？')"
														href="projectAction!delete?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
														title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
												</s:if>
											</s:elseif>


										</td>







									</tr>
								</s:iterator>
							</tbody>
						</table>
						<ul class="forminfo" style="line-height: 40px; font-size: 14px;">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="1" bgcolor="#93CDF3" style="margin-top: 8px">
								<tr align="right" bgcolor="#EEF4EA">
									<td height="34" align="center" bgcolor="#FFFFFF">
										&nbsp;
									</td>
									<td height="34" colspan="6" align="center" bgcolor="#FFFFFF">
										<a
											href="javascript:jumpProjectPage('projectAction!list',1,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);"
											 >首页</a>&nbsp;&nbsp;
										<a
											href="javascript:jumpProjectPage('projectAction!list',<s:property value="page-1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);">上一页</a>&nbsp;&nbsp;&nbsp;
										<a
											href="javascript:jumpProjectPage('projectAction!list',<s:property value="page+1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);">下一页</a>&nbsp;&nbsp;&nbsp;
										<a
											href="javascript:jumpProjectPage('projectAction!list',<s:property value="pageCount"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);"
											 >尾页</a>&nbsp;&nbsp;&nbsp;
										<input type='button' class="btn btn-primary radius size-S"
											onclick="jumpProjectPage('projectAction!list',document.getElementById('page').value,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);"
											value='转到' />
										&nbsp; 当前页：
										<input onpaste="return false"
											onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if (! /^\d+$/ig.test(this.value)){this.value='';}}"
											id="page" type="text" name="page"
											value="<s:property value="page" />" size="2"
											style="width: 25px; height: 20px; line-height: 18px; BORDER-RIGHT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; FONT-SIZE: 13px; BORDER-LEFT: #cccccc 1px solid; COLOR: #000000; BORDER-BOTTOM: #cccccc 1px solid; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff;" />
										/共
										<s:property value="pageCount" />
										页
									</td>
								</tr>
							</table>
						</ul>
					</div>
				</div>
	</body>
</html>