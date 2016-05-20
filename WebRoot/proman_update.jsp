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
		<title>添加人员信息</title>
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
						- 人员管理
					</p>
				</div>
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<div class="row cl bk-gray radius pd-10"
						style="background-color: #FFF">
						<form method="post" action="promanAction!update">
							<s:hidden name="proman.id" />
							<input hidden name="proman.project.id"
								value="<s:property value="pid"/>" />
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									<span class="c-red">*</span>姓名：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <input type="text"
											class="input-text" value="<s:property value="proman.name"/>" placeholder="" id="input2"
											name="proman.name" width="45%" /> </span> </span>
							</div>
							<div class="row cl mb-10">

								<span class="form-label col-2 text-r "><span
									class="c-red">*</span>职务：</span><span class="form-label col-3">
									<span class="formControls col-12"> <input type="text"
											class="input-text" value="<s:property value="proman.jobTitle"/>" placeholder="" id="input2"
											name="proman.jobTitle" width="45%" /> </span> </span>
							</div>
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									联系电话：
								</label>
								<span class="form-label col-3"> <span
									class="formControls col-12"> <input type="text"
											class="input-text" value="<s:property value="proman.telphone"/>" placeholder="" id="input3"
											name="proman.telphone" width="45%" /> </span> </span>
							</div>
							<div class="row cl mb-10">
								<label class="form-label col-2 text-r ">
									证书信息：
								</label>
							<s:hidden name="construction.washSetImg" />
							<a href="javascript:void(0);"
												onClick="addPage('查看图片','promanAction!loadPic?id=<s:property value = "proman.id"/>','650','300')"><img
													width="45px;" height="35px;" src="images/picture.png"></img>
											</a>
							</div>
							<div class="row cl mb-10">
								<div class="col-10 col-offset-5 pt-10 pb-10">
									<input class="btn btn-primary radius" type="submit" value="保存"></input>
									<button onClick="layer_close();"
										class="btn btn-secondary radius" type="button">
										&nbsp;&nbsp;取消&nbsp;&nbsp;
									</button>
								</div>
								<s:token />
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>