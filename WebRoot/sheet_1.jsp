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
		<title>工程质量行为资料监督抽查记录</title>
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
						<s:property value="pageName" />
					</p>
				</div>
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<div class="row cl bk-gray radius pd-10"
						style="background-color: #FFF">
						<!--表格内容-->
						<div class="row cl">
							<div class="col-12 mb-0  f-16"
								style="border-bottom: solid 2px #2DABF7; line-height: 43px;">
								日常巡查信息
							</div>
							<div class="row cl mb-10">
								<span class="btn-upload form-group mt-10 "> <a href=""
									class="btn btn-primary radius upload-btn"><i
										class="Hui-iconfont">&#xe652;</i> 打印表格</a> </span>
							</div>
							<div class=Section1 style='layout-grid: 15.6pt'>

								<p class=MsoNormal align=center
									style='margin-top: 18.0pt; text-align: center'>
									<span
										style='font-size: 18.0pt; mso-bidi-font-size: 10.0pt; font-family: 黑体; mso-hansi-font-family: 宋体; letter-spacing: 2.0pt'>工程监督抽查（巡查）记录<span><o:p></o:p>
									</span> </span>
								</p>

								<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0
									style='border-collapse: collapse; border: none; mso-border-alt: solid windowtext .5pt; mso-padding-alt: 0cm 5.4pt 0cm 5.4pt; mso-border-insideh: .5pt solid windowtext; mso-border-insidev: .5pt solid windowtext'>
									<tr style='height: 35.95pt'>
										<td width=77
											style='width: 57.45pt; border: solid windowtext 1.0pt; mso-border-top-alt: .75pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .5pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.95pt'>
											<p class=MsoNormal>
												<span style='font-family: 宋体'>工程名称<span ><span
														style='mso-spacerun: yes'> </span> <o:p></o:p> </span> </span>
											</p>
										</td>
										<td width=262 colspan=4
											style='width: 196.5pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; mso-border-top-alt: solid windowtext .75pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.95pt'>
											<p class=MsoNormal>
												<span ><o:p>&nbsp;</o:p> </span>
											</p>
										</td>
										<td width=74 colspan=2
											style='width: 55.5pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; mso-border-top-alt: solid windowtext .75pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.95pt'>
											<p class=MsoNormal>
												<span
													style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>建设单位</span>
											</p>
										</td>
										<td width=201 colspan=2
											style='width: 150.45pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-top-alt: .75pt; mso-border-left-alt: .5pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.95pt'>
											<p class=MsoNormal
												style='text-indent: 5.25pt; mso-char-indent-count: .5'>
												<span ><o:p>&nbsp;</o:p> </span>
											</p>
										</td>
									</tr>
									<tr style='height: 34.1pt'>
										<td width=77
											style='width: 57.45pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .75pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 34.1pt'>
											<p class=MsoNormal>
												<span
													style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>施工单位</span>
											</p>
										</td>
										<td width=262 colspan=4
											style='width: 196.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 34.1pt'>
											<p class=MsoNormal>
												<span ><o:p>&nbsp;</o:p> </span>
											</p>
										</td>
										<td width=74 colspan=2
											style='width: 55.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 34.1pt'>
											<p class=MsoBodyText
												style='margin-top: 6.0pt; margin-right: 0cm; margin-bottom: 6.0pt; margin-left: 0cm; line-height: normal; word-break: break-all'>
												<span style='font-family: 宋体; letter-spacing: 0pt'>监理单位<span
													><o:p></o:p> </span> </span>
											</p>
										</td>
										<td width=201 colspan=2
											style='width: 150.45pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; mso-border-right-alt: solid windowtext .75pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 34.1pt'>
											<p class=MsoNormal
												style='text-indent: 5.25pt; mso-char-indent-count: .5'>
												<span ><o:p>&nbsp;</o:p> </span>
											</p>
										</td>
									</tr>
									<tr style='height: 31.45pt'>
										<td width=613 colspan=9
											style='width: 459.9pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 31.45pt'>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span style='font-family: 宋体'>抽<span ><span
														style='mso-spacerun: yes'>&nbsp; </span> </span>查 （ 巡<span
													><span style='mso-spacerun: yes'>&nbsp;
													</span> </span>查 ）<span ><span style='mso-spacerun: yes'>&nbsp;
													</span> </span>记<span ><span style='mso-spacerun: yes'>&nbsp;
													</span> </span>录<span ><o:p></o:p> </span> </span>
											</p>
										</td>
									</tr>
									<tr style='height: 392.4pt'>
										<td width=613 colspan=9
											style='width: 459.9pt; border-top: none; border-left: solid windowtext 1.0pt; border-bottom: none; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-right-alt: solid windowtext .75pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 392.4pt'>
											<p class=MsoNormal style='word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal style='word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal style='word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal style='word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal style='word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<u><span  style='font-family: 宋体'><o:p>
															<span style='text-decoration: none'>&nbsp;</span>
														</o:p> </span> </u>
											</p>
											<p class=MsoNormal align=right
												style='text-align: right; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=right style='text-align: right'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal align=right style='text-align: right'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
											<p class=MsoNormal style='word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
										</td>
									</tr>
									<tr style='height: 48.55pt'>
										<td width=613 colspan=9
											style='width: 459.9pt; border: solid windowtext 1.0pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 48.55pt'>
											<p class=MsoNormal style='word-break: break-all'>
												<span  style='font-family: 宋体'><span
													style='mso-spacerun: yes'>&nbsp;</span> </span><span
													style='font-family: 宋体; color: black'>监督员： </span><span
													 style='font-family: 宋体'><span
													style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</span><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
													style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
												</span><span style='font-family: 宋体'>年<span ><span
														style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span> </span>月<span
													><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;
													</span> </span>日 <span ><o:p></o:p> </span> </span>
											</p>
										</td>
									</tr>
									<tr style='mso-yfti-lastrow: yes; height: 44.7pt'>
										<td width=102 colspan=2
											style='width: 76.65pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 44.7pt'>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span style='font-family: 宋体'>建设单位代表<span ><o:p></o:p>
												</span> </span>
											</p>
										</td>
										<td width=102
											style='width: 76.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .75pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 44.7pt'>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
										</td>
										<td width=102
											style='width: 76.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .75pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 44.7pt'>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span style='font-family: 宋体'>监理单位代表<span ><o:p></o:p>
												</span> </span>
											</p>
										</td>
										<td width=102 colspan=2
											style='width: 76.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .75pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 44.7pt'>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
										</td>
										<td width=102 colspan=2
											style='width: 76.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .75pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 44.7pt'>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span style='font-family: 宋体'>施工单位代表<span ><o:p></o:p>
												</span> </span>
											</p>
										</td>
										<td width=102
											style='width: 76.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .75pt; mso-border-top-alt: .5pt; mso-border-left-alt: .75pt; mso-border-bottom-alt: .5pt; mso-border-right-alt: .75pt; mso-border-color-alt: windowtext; mso-border-style-alt: solid; padding: 0cm 5.4pt 0cm 5.4pt; height: 44.7pt'>
											<p class=MsoNormal align=center
												style='text-align: center; word-break: break-all'>
												<span  style='font-family: 宋体'><o:p>&nbsp;</o:p>
												</span>
											</p>
										</td>
									</tr>
									<![if !supportMisalignedColumns]>
									<tr height=0>
										<td width=77 style='border: none'></td>
										<td width=26 style='border: none'></td>
										<td width=102 style='border: none'></td>
										<td width=102 style='border: none'></td>
										<td width=32 style='border: none'></td>
										<td width=70 style='border: none'></td>
										<td width=4 style='border: none'></td>
										<td width=98 style='border: none'></td>
										<td width=102 style='border: none'></td>
									</tr>
									<![endif]>
								</table>

								<p class=MsoNormal>
									<span ><o:p>&nbsp;</o:p> </span>
								</p>

							</div>
							<div class="row cl">
								<label class="form-label col-1">
									图片上传：
								</label>
								<div class="formControls col-11">
									<div class="uploader-thum-container">
										<div id="fileList" class="uploader-list"></div>
										<div id="filePicker">
											选择图片
										</div>
									</div>
								</div>
							</div>


							<img src="images/003.jpg" width="600px" height="800px">
							<div class="row cl mb-10">
								<label class="form-label col-1">
								</label>
								<span class="btn-upload form-group mt-10 "> <a href=""
									class="btn btn-primary radius upload-btn"><i
										class="Hui-iconfont">&#xe652;</i> 打印图片</a> </span>
							</div>
							<div class="row cl mb-10">
								<div class="col-10 col-offset-5 pt-10 pb-10">
									<button onClick="article_save();"
										class="btn btn-primary radius" type="button">
										<i class="Hui-iconfont">&#xe632;</i> 保存
									</button>
									<button onClick="layer_close();"
										class="btn btn-secondary radius" type="button">
										&nbsp;&nbsp;取消&nbsp;&nbsp;
									</button>
								</div>
							</div>
						</div>
					</div>

				</div>
				<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
				<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
				<script type="text/javascript"
					src="lib/My97DatePicker/WdatePicker.js"></script>
				<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
				<script type="text/javascript"
					src="lib/Validform/5.3.2/Validform.min.js"></script>
				<script type="text/javascript"
					src="lib/webuploader/0.1.5/webuploader.min.js"></script>
				<script type="text/javascript" src="js/H-ui.js"></script>
				<script type="text/javascript" src="js/H-ui.admin.js"></script>
				<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$list = $("#fileList"),
	$btn = $("#btn-star"),
	state = "pending",
	uploader;

	var uploader = WebUploader.create({
		auto: true,
		swf: 'lib/webuploader/0.1.5/Uploader.swf',
	
		// 文件接收服务端。
		server: 'http://lib.h-ui.net/webuploader/0.1.5/server/fileupload.php',
	
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#filePicker',
	
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		// 只允许选择图片文件。
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		}
	});
	uploader.on( 'fileQueued', function( file ) {
		var $li = $(
			'<div id="' + file.id + '" class="item">' +
				'<div class="pic-box"><img></div>'+
				'<div class="info">' + file.name + '</div>' +
				'<p class="state">等待上传...</p>'+
			'</div>'
		),
		$img = $li.find('img');
		$list.append( $li );
	
		// 创建缩略图
		// 如果为非图片文件，可以不用调用此方法。
		// thumbnailWidth x thumbnailHeight 为 100 x 100
		uploader.makeThumb( file, function( error, src ) {
			if ( error ) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
	
			$img.attr( 'src', src );
		}, thumbnailWidth, thumbnailHeight );
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
		var $li = $( '#'+file.id ),
			$percent = $li.find('.progress-box .sr-only');
	
		// 避免重复创建
		if ( !$percent.length ) {
			$percent = $('<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>').appendTo( $li ).find('.sr-only');
		}
		$li.find(".state").text("上传中");
		$percent.css( 'width', percentage * 100 + '%' );
	});
	
	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file ) {
		$( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
	});
	
	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
		$( '#'+file.id ).addClass('upload-state-error').find(".state").text("上传出错");
	});
	
	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
		$( '#'+file.id ).find('.progress-box').fadeOut();
	});
	uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });

    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });

});

(function( $ ){
    // 当domReady的时候开始初始化
    $(function() {
        var $wrap = $('.uploader-list-container'),

            // 图片容器
            $queue = $( '<ul class="filelist"></ul>' )
                .appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
            $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $wrap.find( '.uploadBtn' ),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find( '.placeholder' ),

            $progress = $statusBar.find( '.progress' ).hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = ( function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if( this.width != 1 || this.height != 1 ) {
                        support = false;
                    }
                }
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            } )(),

            // 检测是否已经安装flash，检测flash的版本
            flashVersion = ( function() {
                var version;

                try {
                    version = navigator.plugins[ 'Shockwave Flash' ];
                    version = version.description;
                } catch ( ex ) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                .GetVariable('$version');
                    } catch ( ex2 ) {
                        version = '0.0';
                    }
                }
                version = version.match( /\d+/g );
                return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
            } )(),

            supportTransition = (function(){
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                s = null;
                return r;
            })(),

            // WebUploader实例
            uploader;

        if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function(container) {
                    window['expressinstallcallback'] = function( state ) {
                        switch(state) {
                            case 'Download.Cancelled':
                                alert('您取消了更新！')
                                break;

                            case 'Download.Failed':
                                alert('安装失败')
                                break;

                            default:
                                alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = 'expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' +  swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">'  +
                        '<param name="movie" value="' + swf + '" />' +
                        '<param name="wmode" value="transparent" />' +
                        '<param name="allowscriptaccess" value="always" />' +
                    '</object>';

                    container.html(html);

                })($wrap);

            // 压根就没有安转。
            } else {
                $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            alert( 'Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker-2',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: 'lib/webuploader/0.1.5/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: 'http://lib.h-ui.net/webuploader/0.1.5/server/fileupload.php',
            // runtimeOrder: 'flash',

            // accept: {
            //     title: 'Images',
            //     extensions: 'gif,jpg,jpeg,bmp,png',
            //     mimeTypes: 'image/*'
            // },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });

        // 拖拽时不接受 js, txt 文件。
        uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });

        uploader.on('dialogOpen', function() {
            console.log('here');
        });

        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        uploader.on('ready', function() {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
                $info = $('<p class="error"></p>'),

                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        img = $('<img src="'+src+'">');
                        $wrap.empty().append( img );
                    } else {
                        $.ajax('lib/webuploader/0.1.5/server/preview.php', {
                            method: 'POST',
                            data: src,
                            dataType:'json'
                        }).done(function( response ) {
                            if (response.result) {
                                img = $('<img src="'+response.result+'">');
                                $wrap.empty().append( img );
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                    // use jquery animate to rotation
                    // $({
                    //     rotation: rotation
                    // }).animate({
                    //     rotation: file.rotation
                    // }, {
                    //     easing: 'linear',
                    //     step: function( now ) {
                    //         now = now * Math.PI / 180;

                    //         var cos = Math.cos( now ),
                    //             sin = Math.sin( now );

                    //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                    //     }
                    // });
                }


            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;


            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $( '#filePicker2' ).removeClass( 'element-invisible' );
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        alert( '上传成功' );
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });

        uploader.onError = function( code ) {
            alert( 'Eroor: ' + code );
        };

        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();
    });

})( jQuery );

$(function(){
	var ue = UE.getEditor('editor');
});
</script>
	</body>
</html>