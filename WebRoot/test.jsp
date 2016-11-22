<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

	<head>

		<base href="<%=basePath%>">



		<title>My JSP 'fileUpLoad.jsp' starting page</title>



		<meta http-equiv="pragma" content="no-cache">

		<meta http-equiv="cache-control" content="no-cache">

		<meta http-equiv="expires" content="0">

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

		<meta http-equiv="description" content="This is my page">

		<!--  
  
     <link rel="stylesheet" type="text/css" href="styles.css" mce_href="styles.css">  
  
     -->



	</head>



	<body>

		<center>

			<s:form action="fileUploadAction!upload" method="POST"
				enctype="multipart/form-data">

				<s:fielderror />

				<img id="myImage0" class="img-responsive thumbnail" width="176px"
					height="220px" />

				<img id="myImage1" class="img-responsive thumbnail" width="176px"
					height="220px" />
					
				<img id="myImage2" class="img-responsive thumbnail" width="176px"
					height="220px" />

				<s:file name="myFile"  onchange="change('0');" id="myFile0" label="Image File1" />

				<s:file name="myFile"  onchange="change('1');" id="myFile1" label="Image File2" />

				<s:file name="myFile"  onchange="change('2');"  id="myFile2" label="Image File3" />

				<s:submit />

			</s:form>

			<script type="text/javascript">
															function change(index) {
															    var pic = document.getElementById("myImage"+index);
															    var file = document.getElementById("myFile"+index);
															    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader(file,index);
															     }
															     pic.alt = '图片';
															}
															 function html5Reader(file,index){
															     var file = file.files[0];
															     var reader = new FileReader();
															     reader.readAsDataURL(file);
															     reader.onload = function(e){
															         var pic = document.getElementById("myImage"+index);
															         pic.src=this.result;
															     }
															 }
												</script>

		</center>

	</body>

</html>

