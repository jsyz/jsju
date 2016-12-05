<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/H-ui.admin.css">
   <script src="js/jquery.min.js"></script>
   <script src="js/bootstrap.min.js"></script>
<style type="text/css">
<!--
body {
	background-image: url(images/admin-login-bg.jpg);
	background-position: bottom;
}
-->
</style></head>
<body >
<div class="container">

 <div class="center-block"  style=" margin-top:200px; width:1180px;"> <img src="images/main_01.png" width="1180" height="429" border="0"  usemap="#Map">
<map name="Map">
  <area shape="rect" coords="895,7,1160,203" href="xmlist_gl5.html">
<area shape="rect" coords="608,7,864,203" href="enterpriselibrary_manage.jsp">
<area shape="rect" coords="19,6,276,202" href="yxareaAction!list">
<area shape="rect" coords="312,220,568,413" href="integratedQuery_excel.jsp">
<area shape="rect" coords="608,220,863,412" href="projectAction!count">
<area shape="rect" coords="310,6,571,202" href="citylist.html">

</map></div>


  
   
</div>



	
</body>  
</html>