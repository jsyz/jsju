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
		<title>空白页</title>
	</head>
	<body>
		<div class="xmWraper ">
		<form method="post" action="concreterecordAction!add"
						enctype="multipart/form-data">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<nav>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新当前页"><i
						class="Hui-iconfont">&#xe68f;</i>
					</a><input class="btn btn-success radius r mr-5 f-r" type="submit"
						style="line-height: 1.6em; margin-top: 3px" href="#" title="保存项目"><i
						class="Hui-iconfont">&#xe632;</i> 
					</nav>

				</div>
			</div>
			<div class="xmconbox pd-20">
				<div class="row cl box-shadow pd-10  bk-gray radius"
					style="background-color: #FFF;">
					<h1
						style="font-size: 38px; line-height: 45px; width: 100%; margin-bottom: 15px; text-align: center;">
						所需提供的材料
					</h1>
					
						
						<table id="gcgk" cellspacing="0" class="bk-gray radius box-shadow"
							style="margin-top: 20px;">

							<tbody>


								<tr class="odd-row">
									<th colspan="2" class="th2 first">
										单位名称
									</th>
									<td colspan="2">
										<span style="border-width: 0px;" class="mini-textbox _input"><span
											class="mini-textbox-border"><input
													name="concreterecord.name" style="width: 80%"
													placeholder="" class="mini-textbox-input"
													autocomplete="off" type="text">
										</span>
										</span><span class="mini-textbox _input" style="border-width: 0px;">
										</span>
								</tr>
								<tr>
									<th colspan="2" class="th2 first">
										预拌混凝土、砂浆项目合同备案表
										<br>
									</th>
									<td colspan="2">
										<span style="border-width: 0px;" class="mini-textbox _input"><span
											class="mini-textbox-border"></span>
										<input type="hidden">
										</span>
										<s:file name="picture" accept="image/jpeg,image/png,image/jpg"
											id="myfile1"></s:file>
									</td>
								</tr>
								<tr class="odd-row">
									<th colspan="2" class="th2 first">
										建筑材料及构配件项目负责人质量终身责任承诺书
										<br>
									</th>
									<td colspan="2">
										<span style="border-width: 0px;" class="mini-textbox _input"><span
											class="mini-textbox-border"></span>
										</span><span class="mini-textbox _input" style="border-width: 0px;">
										</span>
										<s:file name="picture1"
											accept="image/jpeg,image/png,image/jpg" id="myfile1"></s:file>
									</td>
								</tr>
								<tr>
									<th colspan="2" class="th2 first">
										法定代表人授权书
									</th>
									<td colspan="2">
										<span style="border-width: 0px;" class="mini-textbox _input"><span
											class="mini-textbox-border"></span> </span>
										<s:file name="picture2"
											accept="image/jpeg,image/png,image/jpg" id="myfile2"></s:file>
									</td>
								</tr>
								<tr class="odd-row">
									<th colspan="2" class="th2 first">
										法定代表人身份证复印件
									</th>
									<td colspan="2">
										<span style="border-width: 0px;" class="mini-textbox _input"><span
											class="mini-textbox-border"></span>
										</span><span class="mini-textbox _input" style="border-width: 0px;">
										</span>
										<s:file name="picture3"
											accept="image/jpeg,image/png,image/jpg" id="myfile3"></s:file>
									</td>
								</tr>
								<tr>
									<th colspan="2" class="th2 first">
										项目负责人身份证复印件
									</th>
									<td colspan="2">
										<span style="border-width: 0px;" class="mini-textbox _input"><span
											class="mini-textbox-border"></span> </span>
										<s:file name="picture4"
											accept="image/jpeg,image/png,image/jpg" id="myfile4"></s:file>
									</td>
								</tr>
								<tr class="odd-row">
									<th colspan="2" class="th2 first">
										合同复印件
									</th>
									<td colspan="2">
										<span style="border-width: 0px;" class="mini-textbox _input"><span
											class="mini-textbox-border"></span>
										</span><span class="mini-textbox _input" style="border-width: 0px;">
											<input type="hidden"> </span>
										<s:file name="picture5"
											accept="image/jpeg,image/png,image/jpg" id="myfile5"></s:file>
									</td>
								</tr>

							</tbody>

						</table>
				
				</div>
			</div>
				<s:token /></form>
		</div>

	</body>
</html>