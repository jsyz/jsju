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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="css/ncss.css" rel="stylesheet" type="text/css" />
		<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet"
			type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
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
		<title>添加设备</title>
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
						class="Hui-iconfont">&#xe68f;</i> </a><a
						href="javascript:history.go(-1);" title="返回" target="_parent"
						class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"><i
						class="Hui-iconfont">&#xe66b;</i> </a>
					</nav>
					<p
						style="line-height: 35px; margin-bottom: 0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);">
						<i class="Hui-iconfont">&#xe64b;</i> 当前片区：
						<s:property value="#session.areaVO.areaName" />
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
						- 设备管理
					</p>
				</div>
				<form action="deviceAction!add" method="post">
					<input hidden name="device.project.id"
						value="<s:property value="pid"/>" />
					<s:hidden name="pid" />
					<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
						<div class="row cl bk-gray radius pd-10"
							style="background-color: #FFF">
							<div class="row cl mb-10">

								<label class="form-label col-2 text-r ">
									<span class="c-red">*</span>设备名称：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <input type="text"
											class="input-text" value="" placeholder="" id="input2"
											name="device.name" width="45%" /> </span> </span>
							</div>
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									设备产权证号：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <input type="text"
											class="input-text" value="" placeholder="" id="input2"
											name="device.propertyCardNumber" width="45%" /> </span> </span>
							</div>
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									安装告知日期：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <input type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',Date:'#F{$dp.$D(\'max\')||\'%y-%M-%d\'}'})"
											class="input-text Wdate" value="" placeholder="" id="input3"
											name="device.installTime" width="45%" /> </span> </span>
							</div>
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									检测日期：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <input type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',Date:'#F{$dp.$D(\'max\')||\'%y-%M-%d\'}'})"
											id="datemin2" class="input-text Wdate" width="45%"
											name="device.checkTime" /> </span> </span>
							</div>
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									是否办理使用登记证：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <!--  <input type="text" class="input-text" value="" placeholder="" id="input2" name="device.isDealUsecard" width="45%" /> -->
										<s:radio list="#{'1':'是','0':'否'}" name="device.isDealUsecard"
											value="1" /> </span> </span>
							</div>
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									拆卸告知日期：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <input type="text"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',Date:'#F{$dp.$D(\'max\')||\'%y-%M-%d\'}'})"
											id="datemin2" class="input-text Wdate" width="45%"
											name="device.removeTime" /> </span> </span>
							</div>

							<label class="form-label col-2 text-r ">
								<span class="c-red"></span>使用登记证到期日期：
							</label>
							<span class="form-label col-3"> <span
								class="formControls col-12"> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',Date:'#F{$dp.$D(\'max\')||\'%y-%M-%d\'}'})"
										class="input-text Wdate" value="" placeholder="" id="input2"
										name="device.usecardExpireTime" width="45%" /> </span> </span>
						</div>
						<div class="row cl mb-10">
							<div class="col-10 col-offset-5 pt-10 pb-10">
								<input class="btn btn-primary radius" type="submit" value="保存"></input>
							</div>

						</div>
						<s:token />
					</div>
				</form>
			</div>

		</div>

	</body>

</html>
