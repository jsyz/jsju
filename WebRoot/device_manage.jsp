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
		<LINK rel="Bookmark" href="/favicon.ico">
		<LINK rel="Shortcut Icon" href="/favicon.ico" />
		<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
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
		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
		<title>设备管理</title>
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<nav>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新当前页"><i
						class="Hui-iconfont">&#xe68f;</i>
					</a><a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:history.go(-1);" title="返回"><i
						class="Hui-iconfont">&#xe66b;</i>
					</a>
					</nav>
					<p
						style="line-height: 35px; margin-bottom: 0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);">
						<i class="Hui-iconfont">&#xe64b;</i> 当前片区：<s:property value="areaVO.areaName"/>
						<span class="pipe">|</span>【项目总数 <s:property value="areaVO.projectNumberTotal" /> 建筑面积 <s:property value="areaVO.buildingAreaTotal" />
						<sup>
							2
						</sup>
						造价
						<s:property value="areaVO.buildingCostTotal" />万 】
						<a href="yxareaAction!list"><span
							class="label label-warning radius">片区切换</span>
						</a>
					</p>
				</div>
			</div>
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<p>
						<i class="Hui-iconfont">&#xe623;</i> <s:property value= "project.name"/>- 设备管理
					</p>
				</div>
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<div class="row cl box-shadow pd-10  bk-gray radius"
						style="background-color: #FFF;">
						<div class="cl pd-5 bg-1 bk-gray mb-20">
							<span class="l"> <a href="javascript:;"
								onClick="deleteAllCheckedDevices();"
								class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
									批量删除</a> <a class="btn btn-primary radius"
								href="deviceAction!goToAdd?projectId=<s:property value="projectId"/>&areaIndex=<s:property value="areaIndex"/>"><i class="Hui-iconfont">&#xe600;</i>
									新增设备</a>
							</span>
							<span class="r">共有数据：<strong><s:property value="totalCount"/></strong> 条</span>
						</div>
						<div class="row" style="margin-top: 5px; margin-bottom: 5px;">
						<div class="text-c">
							<form name="deviceListForm" method="post"
								action="deviceAction!list" target="_self">
								<s:hidden name="areaIndex"></s:hidden>
								<s:hidden name="projectId"></s:hidden>
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="line-height: 35px;">
									<tr height="35">
										<td width="21%" align="right" style="padding-right: 50px;">
											<s:select list="#{0:'选择类型',1:'设备名称',2:'设备产权证号'}"
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
											设备名称
										</th>
										<th width="151">
											设备产权证号
										</th>
										<th width="71">
											安装告知日期
										</th>
										<th width="70">
											检测日期
										</th>
										<th width="68">
											是否办理使用登记证
										</th>
										<th width="68">
											登记证到期时间
										</th>
										<th width="68">
											拆卸告知日期
										</th>
										<th width="103">
											操作
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="devices" var="device" status="index">
										<tr class="text-c">
											<td>
												<input name="indexID" class="indexID" type="checkbox"
													value="<s:property value="id"/>">

											</td>
											<td>
												<s:property value="#status.index+1+(page-1)*10" />
											</td>
											<td>
												<a
													onClick="xmsb_show('设备','deviceAction!view?id=<s:property value="id"/>','10001')"
													href="javascript:;"><s:property value="name" />
												</a>
											</td>
											<td>
												<s:property value="propertyCardNumber" />
											</td>
											<td>
												<s:property value="installTime" />
											</td>
											<td>
												<s:property value="checkTime" />
											</td>
											<td>
												<s:if test="isDealUsecard == 1">
              		是
              		</s:if>
												<s:else>
              		否
              		</s:else>
											</td>
											<td>
												<s:property value="usecardExpireTime" />
											</td>
											<td>
												<s:property value="removeTime" />
											</td>
											<td class="f-14 td-manage">
												<a style="text-decoration: none" class="ml-5"
													onClick="article_edit('编辑','deviceAction!load?id=<s:property value="id"/>&projectId=<s:property value="projectId"/>&areaIndex=<s:property value="areaIndex"/>','10001')"
													href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
												</a>

												<a style="text-decoration: none" class="ml-5"
													onClick="return confirm('你确定删除该信息吗？')" title="删除"
													href="deviceAction!delete?id=<s:property value="id"/>"
													title="删除"><i class="Hui-iconfont">&#xe6e2;</i>
												</a>
											</td>
										</tr>
									</s:iterator>
									<!-- 
            <tr class="text-c">
              <td ><input type="checkbox" value="" name="input"></td>
              <td>002</td>
              <td><a onClick="xmsb_show('桩机','xmsbshow.html','10001')" href="javascript:;">施工升降机</a></td>
              <td>苏B9S1234</td>
              <td>2014-11-15</td>
              <td>2014-11-30</td>
              <td>是</td>
               <td>2016-8-30</td>
              <td>2015-8-30</td>
              <td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑','sb-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr> -->
								</tbody>
							</table>
						</div>
					</div>

				</div>
			</div>

		</div>
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript">


/*资讯-添加*/
function article_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}
/*项目设备详细*/
function xmsb_show(title,url,w,h){
	layer.open({
    type: 2,
    area: ['800px', '500px'],
    fix: false, //不固定
	title:title,
    maxmin: true,
    content: url
});
}
</script>

	</body>
</html>