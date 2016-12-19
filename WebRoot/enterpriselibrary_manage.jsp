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
		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
		<title>空白页</title>
		<style type="text/css">
.tt {
	display: none;
}
</style>
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20"></div>
			<div class="xmconbox pd-20">
				<div class="row cl box-shadow pd-10  bk-gray radius"
					style="background-color: #FFF;">

					<div class="text-c">

						<form name="enterpriselibraryListForm" method="post"
							action="enterpriseLibraryAction!list" target="_self">

							<s:textfield name="convalue" id="convalue" placeholder=" 请输入关键字" style="width:380px" cssClass="input-text"></s:textfield>


							<button name="" id="input" class="btn btn-success" type="submit">
								<i class="Hui-iconfont">&#xe665;</i> 搜索
							</button>

						</form>

					</div>
					<div class="mt-20">
						<table
							class="table table-border table-bordered table-hover table-bg">
							<thead>
								<tr class="text-c">

									<th width="50">
										序号
									</th>
									<th width="200">
										单位名称
									</th>
									<th width="200">
										资质
									</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="enterpriseLibrarys" var="enterpriseLibrary"
									status="index">
									<tr class="text-c">
										<td>
											<s:property value="#index.index+1" />
										</td>
										<td class="text-c">
											<s:property value="name" />
										</td>
										<td>
											<s:property value="qualification" />
										</td>

									</tr>
								</s:iterator>
							</tbody>
						</table>

						<ul class="forminfo" style="line-height: 40px; font-size: 14px;">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="1" bgcolor="#93CDF3" style="margin-top: 8px">
								<tr align="right" bgcolor="#EEF4EA">

									<td height="34" colspan="6" align="center" bgcolor="#FFFFFF">
										<a href="" >首页</a>&nbsp;&nbsp;
										<a href="" >上一页</a>&nbsp;&nbsp;&nbsp;
										<a href=""  >下一页</a>&nbsp;&nbsp;&nbsp;
										<a href=""  >尾页</a>&nbsp;&nbsp;&nbsp;
										<input type='button' class="btn btn-primary radius size-S"
											onclick="" value='转到' />
										&nbsp; 当前页：
										<input onpaste="return false"
											onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if (! /^\d+$/ig.test(this.value)){this.value='';}}"
											id="page" type="text" name="page" value="1" size="2"
											style="width: 25px; height: 20px; line-height: 18px; BORDER-RIGHT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; FONT-SIZE: 13px; BORDER-LEFT: #cccccc 1px solid; COLOR: #000000; BORDER-BOTTOM: #cccccc 1px solid; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff;" />
										/共 1 页
									</td>
								</tr>
							</table>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,2,4,5,6,7,8,9,10,11,12]}// 制定列不参与排序
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

</script>
		<script type="text/javascript">
$(document).ready(function(){
  $(".btn1").click(function(){
  $("table").show();
  });
  $(".btn2").click(function(){
  $("table").show();
  });
});
</script>
	</body>
</html>