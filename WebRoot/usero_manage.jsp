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
		<title>人员权限管理</title>
	</head>
	<body>
		<div class="pd-20">

			<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
				<nav>
				<a class="btn btn-success radius r mr-5 f-r"
					style="line-height: 1.6em; margin-top: 3px"
					href="useroAction!index" target="_top" title="返回主菜单">返回主菜单 </a>
				<a class="btn btn-success radius r mr-5 f-r"
					style="line-height: 1.6em; margin-top: 3px"
					href="javascript:location.replace(location.href);" title="刷新当前页"><i
					class="Hui-iconfont">&#xe68f;</i> </a>
				<a class="btn btn-success radius r mr-5 f-r"
					style="line-height: 1.6em; margin-top: 3px"
					href="javascript:history.go(-1);" title="返回上一页"><i
					class="Hui-iconfont">&#xe66b;</i> </a>

				</nav>
				<p>
					<i class="Hui-iconfont">&#xe623;</i>人员权限
				</p>


			</div>
			<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
				<div class="row cl box-shadow pd-10  bk-gray radius"
					style="background-color: #FFF;">
					<div class="cl pd-5 bg-1 bk-gray mb-20">
						<span class="l"><a href="javascript:;"
							onclick="deleteAllCheckedUseros();" class="btn btn-danger radius"><i
								class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a
							class="btn btn-primary radius"
							onclick="addPage2('新增用户','useroAction!goToAdd')"
							href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增用户
						</a> </span>
						<span class="r">共有数据：<strong><s:property
									value="totalCount" /> </strong> 条</span>
					</div>
					<div class="row" style="margin-top: 5px; margin-bottom: 5px;">
						<div class="text-c">
							<form name="useroListForm" method="post"
								action="useroAction!list" target="_self">
								<s:hidden name="type"></s:hidden>
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 35px;">
									<tr height="35">
										<td width="21%" align="right" style="padding-right: 50px;">
											<s:select list="#{0:'选择类型',1:'用户姓名'}" cssClass="input-text"
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
							class="table table-border table-bordered table-hover table-bg">
							<thead>
								<tr class="text-c">
									<th width="35">
										<input type="checkbox" name="input" value="">
									</th>
									<th width="51">
										序号
									</th>
									<th width="151">
										姓名
									</th>
									<th width="151">
										片区
									</th>
									<th width="151">
										权限
									</th>
									<th width="70">
										职务
									</th>
									<th width="103">
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="useros" var="usero" status="status">
									<tr class="text-c va-m">
										<td>
											<s:if test="session.userSession.id!=id">
												<input name="indexID" class="indexID" type="checkbox"
													value="<s:property value="id"/>">
											</s:if>
										</td>
										<td>
											<s:property value="#status.index+1+(page-1)*10" />
										</td>
										<td>
											<s:property value="realname" />
										</td>
										<td>
											<s:if test="userLimit==0">所有片区</s:if>
											<s:else>
												<s:if test="areaIndex==1">宜城</s:if>
												<s:elseif test="areaIndex==2">环科园</s:elseif>
												<s:elseif test="areaIndex==3">开发区</s:elseif>
												<s:elseif test="areaIndex==4">丁蜀</s:elseif>
												<s:elseif test="areaIndex==5">和桥</s:elseif>
												<s:elseif test="areaIndex==6">官林</s:elseif>
												<s:elseif test="areaIndex==7">张渚</s:elseif>
												<s:elseif test="areaIndex==8">周铁</s:elseif>
												<s:elseif test="areaIndex==9">徐舍</s:elseif>
											</s:else>
										</td>
										<td>
											<s:if test="userLimit==0">A</s:if>
											<s:elseif test="userLimit==1">B</s:elseif>
											<s:elseif test="userLimit==2">C</s:elseif>
										</td>
										<td>
											<s:property value="jobTitle" />
										</td>
										<td class="td-manage">
											<a style="text-decoration: none" class="ml-5"
												onclick="addPage2('编辑人员','useroAction!load?id=<s:property value="id"/>')"
												href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
											</a>
											<s:if test="session.userSession.id!=id">
												<a style="text-decoration: none" class="ml-5"
													href="useroAction!delete?id=<s:property value="id" />"
													onclick="return confirm('你确定删除该信息吗？')" title="删除"><i
													class="Hui-iconfont">&#xe6e2;</i> </a>
											</s:if>


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
											href="javascript:jumpCommonPage('useroAction!list',1,<s:property value="con"/>,'<s:property value="convalue"/>');"
											 >首页</a>&nbsp;&nbsp;
										<a
											href="javascript:jumpCommonPage('useroAction!list',<s:property value="page-1"/>,<s:property value="con"/>,'<s:property value="convalue"/>');"
											 >上一页</a>&nbsp;&nbsp;&nbsp;
										<a
											href="javascript:jumpCommonPage('useroAction!list',<s:property value="page+1"/>,<s:property value="con"/>,'<s:property value="convalue"/>');"
											 >下一页</a>&nbsp;&nbsp;&nbsp;
										<a
											href="javascript:jumpCommonPage('useroAction!list',<s:property value="pageCount"/>,<s:property value="con"/>,'<s:property value="convalue"/>');"
											 >尾页</a>&nbsp;&nbsp;&nbsp;
										<input type='button' class="btn btn-primary radius size-S"
											onclick="jumpCommonPage('useroAction!list',document.getElementById('page').value,<s:property value="con"/>,'<s:property value="convalue"/>');"
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
	</body>
</html>