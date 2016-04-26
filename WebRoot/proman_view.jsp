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
<title>项目人员</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
</head>

<body>
<div class="pd-20">
  <div class="row cl bk-gray radius pd-15" style="background-color:#FFF">
    <div class="row cl mb-10">
      <label class="form-label col-3 text-r " ><span class="c-red">*</span>姓名：</label>
      <span class="form-label col-3"> <span class="formControls col-12">
        <s:property value = "proman.name"/>
        </span></span>
    <div class="row cl mb-10">
      <label class="form-label col-3 text-r " >职务：</label>
      <span class="form-label col-3">
      <div class="formControls col-12">
      <s:property value = "proman.jobTitle"/>
        </div>
      </span>
       <div class="row cl mb-10">
      <label class="form-label col-3 text-r " >证书信息：</label>
      <span class="form-label col-3">
      <div class="formControls col-12">
 		 <a href="javascript:void(0);"
			onClick="addPage('查看图片','promanAction!loadPic?id=<s:property value = "proman.id"/>','650','300')">
			<img width="45px;" height="35px;" src="images/picture.png"></img>
		</a>
        </div>
      </span>
      <label class="form-label col-3 text-r " >联系方式：</label>
      <span class="form-label col-3"> <span class="formControls col-12">
<s:property value = "proman.telphone"/>   </span></span></div>
  </div>
</div>
</body>
</html>
