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
		<title>修改分包单位</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
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
		<title>新增分包单位</title>
	</head>

	<body>
		<div class="pd-20">
			<form action="subunitAction!update" method="post" class="form form-horizontal"
				id="form-article-add">
				<s:hidden name="subunit.id"></s:hidden>
				<s:hidden name="subunit.project.id"></s:hidden>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>分包单位名称：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <s:textfield
									cssClass="input-text radius size-M" cssStyle="width:200px;" name="subunit.unitName" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>项目负责人：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <s:textfield
									cssClass="input-text radius size-M" cssStyle="width:200px;" name="subunit.unitLeader" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="th2">是否办理进市登记核验</span>：
					</label>
					<td>
						<s:select list="#{0:'否',1:'是'}" 	cssClass="input-text radius size-M" 
							name="subunit.isInCity" listKey="key" listValue="value"
							cssStyle="width:200px"></s:select>
					</td>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						联系电话：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <s:textfield
								cssClass="input-text radius size-M" cssStyle="width:200px;"name="subunit.leaderPhone" /> </span> </span>
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
