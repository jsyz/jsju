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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新增专家论证</title>
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
		<title>新增专家论证</title>
	</head>

	<body style="background: #fff;">
		<div class="pd-20">
			<form action="dangerargumentAction!add" method="post"
				class="form form-horizontal" id="form-article-add">
				<input type="hidden" name="dangerargument.daymanage.id"
					value="<s:property value="dayid"/>" />
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>专家认证类型：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <s:textfield
								cssClass="input-text radius size-M" cssStyle="width:200px;"
								name="dangerargument.argTypeName" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>认证时间：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <input type="text" style="width:200px;"
								class="input-text radius size-M Wdate" name="dangerargument.argTime"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
								id="logmin" class="input-text Wdate" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>实施时间：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <input type="text" style="width:200px;"
								class="input-text radius size-M Wdate" name="dangerargument.implTime"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
								id="logmin" class="input-text Wdate" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>完成时间：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <input type="text" style="width:200px;"
								class="input-text radius size-M Wdate" name="dangerargument.finishTime"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
								id="logmin" class="input-text Wdate" /> </span> </span>
				</div>
				<div class="row cl">
					<div class="col-10 col-offset-2">
						<s:token></s:token>
						<input type="submit" class="btn btn-primary radius" value="保存并提交" />
						<input type="button" onClick="childPage_close();"
							class="btn btn-primary radius" value="取消" />
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
