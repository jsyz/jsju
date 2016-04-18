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
						<s:property value="project.name" />-<s:property value="pageName" />
					</p>
				</div>
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<div class="row cl box-shadow pd-10  bk-gray radius"
						style="background-color: #FFF;">
						<div class="cl pd-5 bg-1 bk-gray mb-20">
							<span class="l">
							
							 <a href="javascript:;"
								onclick="deleteAllCheckedSpreadsheets();"
								class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
									批量删除</a> 
									
									
									<a class="btn btn-primary radius"
								onclick="article_add('新增日常巡查','rcxc-add.html')"
								href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>
									新增巡查记录</a> 
									
									
									</span>

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
											更新时间
										</th>
										<th width="103">
											操作
										</th>
									</tr>
								</thead>
								<tbody>
									<tr class="text-c">
										<td>
											<input name="indexID" class="indexID" type="checkbox"
												value="<s:property value="id"/>">
										</td>
										<td>
											<s:property value="#status.index+page*10+1" />
										</td>
										<td>
											<a href="rcxc-xxshow_3.html">巡查记录表</a>
										</td>
										<td>
											2016-01-02
										</td>
										<td class="f-14 td-manage">
											<a style="text-decoration: none" class="ml-5"
												href="projectAction!load?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
												title="编辑"><i class="Hui-iconfont">&#xe6df;</i> </a>
											<a style="text-decoration: none" class="ml-5"
												onclick="return confirm('你确定删除该项目吗？')"
												href="projectAction!delete?id=<s:property value="id"/>&areaIndex=<s:property value="areaIndex"/>"
												title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
										</td>
									</tr>
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
												target="rightFrame">首页</a>&nbsp;&nbsp;
											<a
												href="javascript:jumpProjectPage('projectAction!list',<s:property value="page-1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);"
												target="rightFrame">上一页</a>&nbsp;&nbsp;&nbsp;
											<a
												href="javascript:jumpProjectPage('projectAction!list',<s:property value="page+1"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);"
												target="rightFrame">下一页</a>&nbsp;&nbsp;&nbsp;
											<a
												href="javascript:jumpProjectPage('projectAction!list',<s:property value="pageCount"/>,<s:property value="con"/>,'<s:property value="convalue"/>',<s:property value="areaIndex"/>);"
												target="rightFrame">尾页</a>&nbsp;&nbsp;&nbsp;
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

				</div>
			</div>

		</div>

	</body>
</html>