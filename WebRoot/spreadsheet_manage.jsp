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
		<title>表格管理</title>
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
					<p style="font-size: 18px;">
						<i class="Hui-iconfont">&#xe623;</i>
						<s:property value="project.name" />
						-
						<s:property value="pageName" />
					</p>
				</div>
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<div class="row cl box-shadow pd-10  bk-gray radius"
						style="background-color: #FFF;">
						<div class="cl pd-5 bg-1 bk-gray mb-20">
							<span class="l"> <s:if
									test="#session.userSession.userLimit==0">
									<a href="javascript:;"
										onclick="deleteAllCheckedSpreadsheets();"
										class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
										批量删除</a>
									<s:iterator value="sheetVOs" var="sheetVO" status="status">

										<a class="btn btn-primary radius"
											href="spreadsheetAction!goToAdd?sheetType=<s:property value="sheetType"/>&pid=<s:property value="pid"/>&pageType=<s:property value="pageType"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>"><i
											class="Hui-iconfont">&#xe600;</i> 新增<s:property
												value="sheetName" /> </a>

									</s:iterator>
								</s:if> <s:elseif test="#session.userSession.userLimit==1">
									<s:if test="#session.userSession.areaIndex==#session.areaVO.index">
										<a href="javascript:;"
											onclick="deleteAllCheckedSpreadsheets();"
											class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
											批量删除</a>
										<s:iterator value="sheetVOs" var="sheetVO" status="status">

											<a class="btn btn-primary radius"
												href="spreadsheetAction!goToAdd?sheetType=<s:property value="sheetType"/>&pid=<s:property value="pid"/>&pageType=<s:property value="pageType"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>"><i
												class="Hui-iconfont">&#xe600;</i> 新增<s:property
													value="sheetName" /> </a>

										</s:iterator>
									</s:if>
								</s:elseif> <s:elseif test="#session.userSession.userLimit==2">
									<s:if test="#session.userSession.id==project.uid">
										<a href="javascript:;"
											onclick="deleteAllCheckedSpreadsheets();"
											class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
											批量删除</a>
										<s:iterator value="sheetVOs" var="sheetVO" status="status">

											<a class="btn btn-primary radius"
												href="spreadsheetAction!goToAdd?sheetType=<s:property value="sheetType"/>&pid=<s:property value="pid"/>&pageType=<s:property value="pageType"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>"><i
												class="Hui-iconfont">&#xe600;</i> 新增<s:property
													value="sheetName" /> </a>

										</s:iterator>
									</s:if>
								</s:elseif> </span>

							<span class="r">共有数据：<strong><s:property
										value="totalCount" /> </strong> 条</span>
						</div>
						<div class="row" style="margin-top: 5px; margin-bottom: 5px;">
							<div class="text-c">
								<form name="projectListForm" method="post"
									action="spreadsheetAction!list" target="_self">
									<s:hidden name="pid"></s:hidden>
									<s:hidden name="sheetTypeStr"></s:hidden>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="line-height: 35px;">
										<tr height="35">
											<td width="21%" align="right" style="padding-right: 50px;">
												<s:select list="#{0:'选择类型',1:'表格名称'}" cssClass="input-text"
													name="con" listKey="key" listValue="value"
													cssStyle="width:180px"></s:select>
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
								class="table table-border table-bordered table-bg table-hover table-sort">
								<thead>
									<tr class="text-c">
										<th width="35">
											<input type="checkbox" name="input" value="">
										</th>
										<th width="51">
											序号
										</th>
										<th width="151">
											表格名称
										</th>
										<th width="151">
											查看图片
										</th>
										<th width="151">
											上传时间
										</th>
										<s:if test="viewChangedConent==1">
											<th width="151">
												检查日期
											</th>
											<th width="151">
												整改到期时间
											</th>
											<th width="103">
												是否闭合
											</th>
											<th width="103">
												闭合时间
											</th>
										</s:if>
										<th width="103">
											操作
										</th>
									</tr>
								</thead>
								<tbody>
									<s:if test="%{spreadsheets.size()==0}">
										<td colspan="10" align="center">
											暂无记录
										</td>
									</s:if>
									<s:iterator value="spreadsheets" var="spreadsheet"
										status="status">
										<tr class="text-c">
											<td>
												<input name="indexID" class="indexID" type="checkbox"
													value="<s:property value="id"/>">
											</td>
											<td>
												<s:property value="#status.index+1+(page-1)*10" />
											</td>
											<td>
												<s:property value="sheetName" />
											</td>
											<td>
												<s:if test="sheetImg!=null&&sheetImg!=''">
													<a href="javascript:void(0);"
														onClick="addPage('查看上传图片','spreadsheetAction!view?id=<s:property value="id"/>','650','300')"><img
															width="45px;" height="35px;" src="images/picture.png"></img>
													</a>
												</s:if>
												<s:else>
															暂无上传图片
														</s:else>
											</td>
											<td>
												<s:property value="updateTime" />
											</td>
											<s:if test="viewChangedConent==1">
												<td>
													<s:property value="checkTime" />
												</td>
												<td>
													<s:property value="expireTime" />
												</td>
												<td class="f-14 td-manage">
													<s:if test="isClose==1">是</s:if>
													<s:else>否</s:else>
												</td>
												<td class="f-14 td-manage">
													<s:property value="closeTime" />
												</td>
											</s:if>
											<td class="f-14 td-manage">
												<s:if test="#session.userSession.userLimit==0">
													<s:if test="viewChangedConent==1">
														<a style="text-decoration: none" class="ml-5"
															href="spreadsheetAction!load?id=<s:property value="id"/>&pid=<s:property value="pid"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>"
															title="编辑"><i class="Hui-iconfont">&#xe6df;</i> </a>
													</s:if>
													<a style="text-decoration: none" class="ml-5"
														onclick="return confirm('你确定删除该记录吗？')"
														href="spreadsheetAction!delete?id=<s:property value="id"/>&pid=<s:property value="pid"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>&pageType=<s:property value="pageType"/>"
														title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
												</s:if>

												<s:elseif test="#session.userSession.userLimit==1">
													<s:if test="#session.userSession.areaIndex==#session.areaVO.index">
														<s:if test="viewChangedConent==1">
															<a style="text-decoration: none" class="ml-5"
																href="spreadsheetAction!load?id=<s:property value="id"/>&pid=<s:property value="pid"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>"
																title="编辑"><i class="Hui-iconfont">&#xe6df;</i> </a>
														</s:if>
														<a style="text-decoration: none" class="ml-5"
															onclick="return confirm('你确定删除该记录吗？')"
															href="spreadsheetAction!delete?id=<s:property value="id"/>&pid=<s:property value="pid"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>&pageType=<s:property value="pageType"/>"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
													</s:if>
												</s:elseif>

												<s:elseif test="#session.userSession.userLimit==2">
													<s:if test="#session.userSession.id==project.uid">
														<s:if test="viewChangedConent==1">
															<a style="text-decoration: none" class="ml-5"
																href="spreadsheetAction!load?id=<s:property value="id"/>&pid=<s:property value="pid"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>"
																title="编辑"><i class="Hui-iconfont">&#xe6df;</i> </a>
														</s:if>
														<a style="text-decoration: none" class="ml-5"
															onclick="return confirm('你确定删除该记录吗？')"
															href="spreadsheetAction!delete?id=<s:property value="id"/>&pid=<s:property value="pid"/>&sheetTypeStr=<s:property value="sheetTypeStr"/>&pageType=<s:property value="pageType"/>"
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
												href="javascript:jumpSheetPage('spreadsheetAction!list',1,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="pid"/>,'<s:property value="sheetTypeStr"/>');"
												target="rightFrame">首页</a>&nbsp;&nbsp;
											<a
												href="javascript:jumpSheetPage('spreadsheetAction!list',<s:property value="page-1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="pid"/>,'<s:property value="sheetTypeStr"/>');"
												target="rightFrame">上一页</a>&nbsp;&nbsp;&nbsp;
											<a
												href="javascript:jumpSheetPage('spreadsheetAction!list',<s:property value="page+1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="pid"/>,'<s:property value="sheetTypeStr"/>');"
												target="rightFrame">下一页</a>&nbsp;&nbsp;&nbsp;
											<a
												href="javascript:jumpSheetPage('spreadsheetAction!list',<s:property value="pageCount"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="pid"/>,'<s:property value="sheetTypeStr"/>');"
												target="rightFrame">尾页</a>&nbsp;&nbsp;&nbsp;
											<input type='button' class="btn btn-primary radius size-S"
												onclick="jumpSheetPage('spreadsheetAction!list',document.getElementById('page').value,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="pid"/>,'<s:property value="sheetTypeStr"/>');"
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

				</div>
			</div>

		</div>
	</body>
</html>
