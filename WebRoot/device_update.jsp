<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="css/city.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>更新设备</title>
</head>
<body>
<div class="xmWraper ">
   <div class="xmconbox pd-20">
   <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
   <nav><a class="btn btn-success radius r mr-5 f-r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新当前页" ><i class="Hui-iconfont">&#xe68f;</i></a><a href="xm-sbgl.html" title="返回" target="_parent" class="btn btn-success radius r mr-5 f-r" style="line-height:1.6em;margin-top:3px" ><i class="Hui-iconfont">&#xe66b;</i></a></nav>
   <p style="line-height:35px; margin-bottom:0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);"><i class="Hui-iconfont">&#xe64b;</i> 当前片区：宜城片区<span class="pipe">|</span>【项目总数  20　　建筑面积 80000m<sup>2</sup>     　　造价  500000万 】<a href="citylist.html"><span class="label label-warning radius">片区切换</span></a></p>
   </div>
   </div>
   <div class="xmconbox pd-20">
   <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
   <p><i class="Hui-iconfont">&#xe623;</i> 盛润园林绿化- 设备管理</p>
   </div>
   <form method="post" action="deviceAction!update">
  <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
    <div class="row cl bk-gray radius pd-10" style="background-color:#FFF">
      <div class="row cl mb-10">
        <label class="form-label col-2 text-r " ><span class="c-red">*</span>设备名称：</label>
        <span class="form-label col-3"> <span class="formControls col-12">
          <input type="text" class="input-text" value="" placeholder="" id="input2" name="device.name" width="45%" />
          </span></span>
        <label class="form-label col-2 text-r " >设备产权证号：</label>
        <span class="form-label col-3"> <span class="formControls col-12">
          <input type="text" class="input-text" value="" placeholder="" id="input2" name="device.propertyCardNumber" width="45%" />
        </span></span></div>
      <div class="row cl mb-10">
        <label class="form-label col-2 text-r " >安装告知日期：</label>
        <span class="form-label col-3"> <span class="formControls col-12">
          <input type="text" class="input-text" value="" placeholder="" id="input3" name="device.installTime" width="45%" />
          </span></span>
        <label class="form-label col-2 text-r " >检测日期：</label>
        <span class="form-label col-3"> <span class="formControls col-12">
          <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',Date:'#F{$dp.$D(\'max\')||\'%y-%M-%d\'}'})" id="datemin2" class="input-text Wdate"  width="45%" name ="device.checkTime"/>
      </span></span></div>
        <div class="row cl mb-10">
        <label class="form-label col-2 text-r " >是否办理使用登记证：</label>
       <span class="form-label col-3"> <span class="formControls col-12">
          <input type="text" class="input-text" value="" placeholder="" id="input2" name="device.isDealUsecard" width="45%" />
          </span></span>
        <label class="form-label col-2 text-r " >拆卸告知日期：</label>
        <span class="form-label col-3"> <span class="formControls col-12">
          <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',Date:'#F{$dp.$D(\'max\')||\'%y-%M-%d\'}'})" id="datemin2" class="input-text Wdate"  width="45%" name = "device.removeTime"/>
        </span></span></div>

 <label class="form-label col-2 text-r " ><span class="c-red"></span>使用登记证到期日期：</label>
        <span class="form-label col-3"> <span class="formControls col-12">
          <input type="text" class="input-text" value="" placeholder="" id="input2" name="device.usecardExpireTime" width="45%" />
          </span></span>
    </div>
    <div class="row cl mb-10">
      <div class="col-10 col-offset-5 pt-10 pb-10">
        <button onClick="article_save();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
        <button onClick="layer_close();" class="btn btn-secondary radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
      </div>
  </div>
  </div>
     </form>
  </div>
  
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
</body>
</html>