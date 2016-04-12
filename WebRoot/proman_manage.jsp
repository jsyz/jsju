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
<title>空白页</title>
</head>
<body>
<div class="xmWraper ">
   <div class="xmconbox pd-20">
   <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
   <nav><a class="btn btn-success radius r mr-5 f-r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新当前页" ><i class="Hui-iconfont">&#xe68f;</i></a><a class="btn btn-success radius r mr-5 f-r" style="line-height:1.6em;margin-top:3px" href="xmshow.html" title="返回" ><i class="Hui-iconfont">&#xe66b;</i></a></nav>
   <p style="line-height:35px; margin-bottom:0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);"><i class="Hui-iconfont">&#xe64b;</i> 当前片区：官林<span class="pipe">|</span>【项目总数  32　　建筑面积 4378m<sup>2</sup>     　　造价  5343万 】<a href="citylist.html"><span class="label label-warning radius">片区切换</span></a></p>
   </div>
   </div>
   <div class="xmconbox pd-20">
   <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
   <p><i class="Hui-iconfont">&#xe623;</i> 省滆湖渔管办二大队执法基地- 人员信息</p>
   </div>
  <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
    <div class="row cl box-shadow pd-10  bk-gray radius" style="background-color: #FFF;">
      <div class="cl pd-5 bg-1 bk-gray mb-20"> <span class="l"> <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="article_add('新增人员','ry-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 新增人员</a></span> <span class="r">共有数据：<strong>2</strong> 条</span></div>
      <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
          <thead>
            <tr class="text-c">
              <th width="35"><input type="checkbox" name="input" value=""></th>
              <th width="51">序号</th>
              <th width="151">职务</th>
              <th width="151">姓名</th>
              <th width="103">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr class="text-c">
              <td ><input type="checkbox" value="" name="input"></td>
              <td>001</td>
              <td>项目经理</td>
              <td><a onClick="xmsb_show('人员信息','xmryshow.html','10001')" href="javascript:;">张勇</a></td>
              <td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑','ry-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
            <tr class="text-c">
              <td ><input type="checkbox" value="" name="input"></td>
              <td>002</td>
              <td>施工员</td>
              <td><a onClick="xmsb_show('人员信息','xmryshow.html','10001')" href="javascript:;">吴丽</a></td>
              <td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑','ry-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
                <tr class="text-c">
              <td ><input type="checkbox" value="" name="input"></td>
              <td>003</td>
              <td>质检员</td>
              <td><a onClick="xmsb_show('人员信息','xmryshow.html','10001')" href="javascript:;">吴xx</a></td>
              <td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑','ry-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
                <tr class="text-c">
              <td ><input type="checkbox" value="" name="input"></td>
              <td>004</td>
              <td>安全员</td>
              <td><a onClick="xmsb_show('人员信息','xmryshow.html','10001')" href="javascript:;">吴xx</a></td>
              <td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑','ry-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
            <tr class="text-c">
              <td ><input type="checkbox" value="" name="input"></td>
              <td>005</td>
              <td>材料员</td>
              <td><a onClick="xmsb_show('人员信息','xmryshow.html','10001')" href="javascript:;">吴xx</a></td>
              <td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('编辑','ry-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
          </tbody>
        </table>
    </div>
    </div>
    
  </div>
  </div>
  
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
  "aaSorting": [[ 1, "desc" ]],//默认第几个排序
  "bStateSave": true,//状态保存
  "aoColumnDefs": [
    //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
    {"orderable":false,"aTargets":[0,2,3,4,5]}// 制定列不参与排序
  ]
});

/*资讯-添加*/
function article_add(title,url,w,h){
  var index = layer.open({
    type: 2,
    title: title,
    content: url
  });
  layer.full(index);
}
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
  var index = layer.open({
    type: 2,
    title: title,
    content: url
  });
  layer.full(index);
}
/*资讯-删除*/
function article_del(obj,id){
  layer.confirm('确认要删除吗？',function(index){
    $(obj).parents("tr").remove();
    layer.msg('已删除!',1);
  });
}
/*项目设备详细*/
function xmsb_show(title,url,w,h){
  layer.open({
    type: 2,
    area: ['800px', '500px'],
    fix: false, //不固定
  title:title,
    maxmin: true,
    content: url
});
}
</script> 

</body>
</html>