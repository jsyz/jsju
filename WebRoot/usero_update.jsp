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
		<title>修改用户</title>
	</head>

	<body style="background: #fff;">
		<div class="pd-20">
			<form action="useroAction!update" method="post"
				class="form form-horizontal"">
				<s:hidden name="usero.id"></s:hidden>
				<s:hidden name="usero.username"></s:hidden>
				<s:hidden name="usero.password"></s:hidden>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>真实姓名：
					</label>
					<span class="formControls col-10"> <s:textfield
							id="realname" cssClass="input-text radius size-M"
							cssStyle="width:200px;" name="usero.realname" /> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span><span class="th2">权限等级</span>：
					</label>
					<td>
						<s:select list="#{0:'A',1:'B',2:'C'}"
							cssClass="input-text radius size-M" name="usero.userLimit"
							listKey="key" listValue="value" cssStyle="width:200px"
							onchange=""></s:select>
					</td>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span><span class="th2">所属片区</span>：
					</label>
					<td>
						<s:select
							list="#{1:'宜城',2:'环科园',3:'开发区',4:'丁蜀',5:'和桥',6:'官林',7:'张渚',8:'周铁',9:'徐舍'}"
							cssClass="input-text radius size-M" name="usero.areaIndex"
							listKey="key" listValue="value" cssStyle="width:200px"></s:select>
					</td>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>联系电话：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"><s:textfield id="telphone"
								cssClass="input-text radius size-M" cssStyle="width:200px;"
								name="usero.telphone" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						职务：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <s:textfield id="jobTitle"
								cssClass="input-text radius size-M" cssStyle="width:200px;"
								name="usero.jobTitle" /> </span> </span>
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
