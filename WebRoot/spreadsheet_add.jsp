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
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
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
		<script type="text/javascript" src="js/pageKit.js"></script>
		<title>表格记录</title>
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新当前页"><i
						class="Hui-iconfont">&#xe68f;</i> </a>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:history.go(-1);" title="返回上一页"><i
						class="Hui-iconfont">&#xe66b;</i> </a>
					</nav>
					<p
						style="line-height: 35px; margin-bottom: 0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);">
						<i class="Hui-iconfont">&#xe64b;</i> 当前片区：
						<s:property value="areaVO.areaName" />
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
						<a href="yxareaAction!list" target="_parent"><span
							class="label label-warning radius">片区切换</span> </a>
					</p>
				</div>
			</div>
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<p>
						<i class="Hui-iconfont">&#xe623;</i>
						<s:property value="project.name" />
						-
						<s:property value="sheetName" />
					</p>
				</div>
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<div class="row cl bk-gray radius pd-10"
						style="background-color: #FFF">
						<!--表格内容-->
						<form action="spreadsheetAction!add" method="post"
							class="form form-horizontal" id="form-article-add"
							enctype="multipart/form-data">
							<s:hidden name="pid"></s:hidden>
							<s:hidden name="sheetTypeStr"></s:hidden>
							<s:hidden name="pageType"></s:hidden>
							<input name="spreadsheet.project.id"
								value="<s:property value="pid" />" type="hidden" />
							<input name="spreadsheet.sheetName"
								value="<s:property value="sheetName" />" type="hidden" />
							<input name="spreadsheet.sheetType"
								value="<s:property value="sheetType" />" type="hidden" />
							<div class="col-12 mb-0  f-16"
								style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
								<s:property value="sheetName" />
							</div>
							<div class="row cl" style="margin-top: 20px;">
								<label class="form-label col-2">
									<span class="c-red">*</span>上传日期：
								</label>
								<span class="form-label col-3"><span
									class="formControls col-10"> <input type="text"
											width="45%" class="input-text radius size-M Wdate"
											name="spreadsheet.updateTime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
											id="logmin" class="input-text Wdate" /> </span> </span>
							</div>
							<s:if test="sheetType==7">
								<div class="row cl" style="margin-top: 20px;">
									<label class="form-label col-2">
										<span class="c-red">*</span>检查日期：
									</label>
									<span class="form-label col-3"><span
										class="formControls col-10"> <input type="text"
												width="45%" class="input-text radius size-M Wdate"
												name="spreadsheet.checkTime"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" /> </span> </span>
								</div>
								<div class="row cl" style="margin-top: 20px;">
									<label class="form-label col-2">
										<span class="c-red">*</span>整改到期时间：
									</label>
									<span class="form-label col-3"><span
										class="formControls col-10"> <input type="text"
												width="45%" class="input-text radius size-M Wdate"
												name="spreadsheet.expireTime"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" /> </span> </span>
								</div>
								<div class="row cl" style="margin-top: 20px;">
									<label class="form-label col-2">
										<span class="c-red">*</span>是否闭合：
									</label>
									<span class="form-label col-3"> <span
										class="formControls col-10"> <s:select
												list="#{0:'否',1:'是'}" cssClass="input-text"
												name="spreadsheet.isClose" listKey="key" listValue="value"
												width="45%"></s:select> </span> </span>
								</div>
								<div class="row cl" style="margin-top: 20px;">
									<label class="form-label col-2">
										<span class="c-red">*</span>闭合时间：
									</label>
									<span class="form-label col-3"><span
										class="formControls col-10"> <input type="text"
												width="45%" class="input-text radius size-M Wdate"
												name="spreadsheet.closeTime"
												onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
												id="logmin" class="input-text Wdate" /> </span> </span>
								</div>
							</s:if>

							<div class="row cl">
								<label class="form-label col-2">
									<span class="c-red">*</span>上传图片：
								</label>
								<div class="col-3" style="margin-bottom: 20px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<img id="myimage1" class="img-responsive thumbnail"
													width="400px" height="180px;" alt="添加图片"/>
												<script type="text/javascript">
															function change1() {
															    var pic1 = document.getElementById("myimage1"),
															        file1 = document.getElementById("myfile1");
															    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext1!='png'&&ext1!='jpg'&&ext1!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file1.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic1.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic1.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic1.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader1(file1);
															     }
															     pic1.alt = '图片';
															}
															 function html5Reader1(file1){
															     var file1 = file1.files[0];
															     var reader1 = new FileReader();
															     reader1.readAsDataURL(file1);
															     reader1.onload = function(e){
															         var pic1 = document.getElementById("myimage1");
															         pic1.src=this.result;
															     }
															 }
												</script>
											</td>
										</tr>
										<tr>
											<td align="center">
												&nbsp;
												<s:file name="picture1"
													accept="image/jpeg,image/png,image/jpg"
													onchange="change1();" id="myfile1"></s:file>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-2">
									<span class="c-red">*</span>上传图片2：
								</label>
								<div class="col-3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<img id="myimage2" class="img-responsive thumbnail"
													width="400px" height="180px;" />
												<script type="text/javascript">
															function change2() {
															    var pic2 = document.getElementById("myimage2"),
															        file2 = document.getElementById("myfile2");
															    var ext2=file2.value.substring(file2.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext2!='png'&&ext2!='jpg'&&ext2!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file2.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic2.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic2.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic2.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader2(file2);
															     }
															     pic2.alt = '图片';
															}
															 function html5Reader2(file2){
															     var file2 = file2.files[0];
															     var reader2 = new FileReader();
															     reader2.readAsDataURL(file2);
															     reader2.onload = function(e){
															         var pic2 = document.getElementById("myimage2");
															         pic2.src=this.result;
															     }
															 }
												</script>
											</td>
										</tr>
										<tr>
											<td align="center">
												<s:file name="picture2"
													accept="image/jpeg,image/png,image/jpg"
													onchange="change2();" id="myfile2"></s:file>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-2">
									<span class="c-red">*</span>上传图片3：
								</label>
								<div class="col-3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="center">
												<img id="myimage3" class="img-responsive thumbnail"
													width="400px" height="180px;" />
												<script type="text/javascript">
															function change3() {
															    var pic3 = document.getElementById("myimage3"),
															        file3 = document.getElementById("myfile3");
															    var ext3=file3.value.substring(file3.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext3!='png'&&ext3!='jpg'&&ext3!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file3.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic3.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic3.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic3.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader3(file3);
															     }
															     pic3.alt = '图片';
															}
															 function html5Reader3(file3){
															     var file3 = file3.files[0];
															     var reader3 = new FileReader();
															     reader3.readAsDataURL(file3);
															     reader3.onload = function(e){
															         var pic3 = document.getElementById("myimage3");
															         pic3.src=this.result;
															     }
															 }
															 </script>
											</td>
										</tr>
										<tr>
											<td align="center">
												<s:file name="picture3"
													accept="image/jpeg,image/png,image/jpg"
													onchange="change3();" id="myfile3"></s:file>
											</td>
										</tr>
									</table>
								</div>
							</div>

							<div class="row cl mb-10">
								<span class="btn-upload form-group mt-10 "> <a
									href="sheethtml/<s:property value="sheetType"/>.htm"
									target="_blank" class="btn btn-primary radius upload-btn"><i
										class="Hui-iconfont">&#xe652;</i> 查看并打印表格</a> </span>
							</div>

							<div class="row cl" style="margin-top: 20px;">
								<div class="col-10 col-offset-2">
									<s:token></s:token>
									<input type="submit" class="btn btn-primary radius"
										value="保存并提交" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
	</body>
</html>