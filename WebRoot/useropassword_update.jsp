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
		<title>密码修改</title>
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
	</head>

	<body>
		<div class="pd-20">
			<form action="useroAction!updatePassword" method="post" class="form form-horizontal" id=" "
				onclick="">
				<div class="row cl">
					<label class="form-label col-3">
						<span class="c-red">*</span>原密码：
					</label>
					<span class="form-label col-9"> <input type="password"
							class="input-text" value="" placeholder="" id="password1"
							name="password1" /> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-3">
						<span class="c-red">*</span>新密码：
					</label>
					<span class="form-label col-9"> <input type="password"
							class="input-text" value="" placeholder="" id="password2"
							name="password2" /> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-3">
						<span class="c-red">*</span>确认密码：
					</label>
					<span class="form-label col-9"> <input type="password"
							class="input-text" value="" placeholder="" id="password3"
							name="password3" /> </span>
				</div>
				<div class="row cl">
					<div class="col-9 col-offset-3">
						<input type="submit" class="btn btn-primary radius" value="保存并提交"/>
						<input type="button" onClick="childPage_close();" class="btn btn-primary radius" value="取消"/>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
