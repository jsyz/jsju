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
		<title>新增用户</title>
	</head>

	<body style="background: #fff;">
		<div class="pd-20">
			<form action="useroAction!add" method="post"
				class="form form-horizontal" id="form-article-add">
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>用户名：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <s:textfield id="username"
								cssClass="input-text radius size-M" cssStyle="width:200px;"
								name="usero.username"/> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>密码：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> 
								<s:password id="password" 
								cssClass="input-text radius size-M" cssStyle="width:200px;"
								name="usero.password"></s:password>
								</span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>真实姓名：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <input type="text"
								class="input-text" value="" placeholder="" id="input2"
								name="input2" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="th2">所属片区</span>：
					</label>
					<td>
						<s:select list="#{0:'A',1:'B',2:'B'}" cssClass="input-text"
							name="person.sex" listKey="key" listValue="value"
							cssStyle="width:180px"></s:select>
					</td>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="th2">权限等级</span>：
					</label>
					<td>
						<s:select list="#{0:'A',1:'B',2:'B'}" cssClass="input-text"
							name="person.sex" listKey="key" listValue="value"
							cssStyle="width:180px"></s:select>
					</td>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						联系电话：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <input type="text"
								class="input-text" value="" placeholder="" id="input3"
								name="input3" /> </span> </span>
				</div>
				<div class="row cl">
					<label class="form-label col-2">
						<span class="c-red">*</span>所在部门：
					</label>
					<span class="form-label col-3"><span
						class="formControls col-10"> <input type="text"
								class="input-text" value="" placeholder="" id="input2"
								name="input2" /> </span> </span>
				</div>
				<div class="row cl">
					<div class="col-10 col-offset-2">
						<button onClick="article_save_submit();"
							class="btn btn-primary radius" type="submit">
							保存并提交
						</button>
						<button onClick="layer_close();" class="btn btn-default radius"
							type="button">
							&nbsp;&nbsp;取消&nbsp;&nbsp;
						</button>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
