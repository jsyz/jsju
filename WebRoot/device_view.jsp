<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>项目设备</title>
		<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
		<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
			type="text/css" />
		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>

	</head>

	<body>
		<div class="pd-20">
			<div class="row cl bk-gray radius pd-15"
				style="background-color: #FFF">
				<div class="row cl mb-10">
					<label class="form-label col-3 text-r ">
						<span class="c-red">*</span>设备名称：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <s:property
								value="device.name" /> </span>
					</span>
					<label class="form-label col-3 text-r ">
						设备产权证号：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <s:property
								value="device.propertyCardNumber" /> </span>
					</span>
				</div>
				<div class="row cl mb-10">
					<label class="form-label col-3 text-r ">
						安装告知日期：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <s:property
								value="device.installTime" /> </span>
					</span>
					<label class="form-label col-3 text-r ">
						检测日期：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <s:property
								value="device.checkTime" /> </span>
					</span>
				</div>
				<div class="row cl mb-10">
					<label class="form-label col-3 text-r ">
						是否办理使用登记证：
					</label>
					<span class="form-label col-3">
						<div class="formControls col-12">
							<s:if test="device.isDealUsecard == 1">
			是
			</s:if>
							<s:else>
			否
			</s:else>
						</div> </span>
					<label class="form-label col-3 text-r ">
						拆卸告知日期：
					</label>
					<span class="form-label col-3"> <span
						class="formControls col-12"> <s:property
								value="device.removeTime" /> </span>
					</span>
				</div>
			</div>
		</div>
	</body>
</html>
