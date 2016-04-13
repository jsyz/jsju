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
		<link href="css/city.css" rel="stylesheet" type="text/css" />
		<title>宜兴区域</title>
	</head>
	<body>
		<div class="cityWraper"
			style="background-image: url(images/admin-login-bg.jpg); background-position: bottom;">
			<div class="cityBox"
				style="box-shadow: -2px 0px 8px #6CF, 0px -2px 8px #6F9AAA, 0px 2px 8px #3D728C, 2px 0px 8px #424B67;">
				<div class="city-tit">
					<div class="bname">
						--- 宜 兴 ---
					</div>
				</div>
				<div class="city-list">
					<table width="101%" height="455"
						class="table table-hover2 pt-5 c-twhite ">
						<thead>
							<tr class="text-c"
								style="background-color: #30709A; font-size: 14px;">
								<th class="c-white  f-14" width="6%">
									序号
								</th>
								<th class="c-white  f-14" width="24%">
									片区名称
								</th>
								<th class="c-white  f-14" width="9%">
									项目数
								</th>
								<th class="c-white  f-14" width="21%">
									建筑面积（m
									<sup>
										2
									</sup>
									）
								</th>
								<th class="c-white  f-14" width="15%">
									造 价（万）
								</th>
								<!-- 
								<th class="c-white  f-14" width="25%">
									更新时间
								</th>
								 -->
							</tr>
						</thead>
						<s:iterator value="areaVOs" var="areaVO" status="status">
							<tr class="text-c">
								<td>
									<s:property value="index" />
								</td>
								<td>
									<a
										href="projectAction!list?areaIndex=<s:property value="index"/>"><s:property
											value="areaName" /> </a>
								</td>
								<td>
									<s:property value="projectNumberTotal" />
								</td>
								<td>
									<s:property value="buildingAreaTotal" />
								</td>
								<td>
									<s:property value="buildingCostTotal" />
								</td>
								<!--  
								<td>
									2015-10-11
								</td>
								-->
							</tr>
						</s:iterator>
					</table>

				</div>
				<div class="city-hj">
					<table height="63" class="table table-hover pt-5 ">
						<thead>
							<tr class="text-c">
								<th class=" f-12" width="6%"></th>
								<th class="  f-12" width="24%">
									合计：
								</th>
								<th class="  f-12" width="9%">
									<s:property value="allNumberTotal" />
								</th>
								<th class="  f-12" width="21%">
									<s:property value="allAreaTotal" />
									（m
									<sup>
										2
									</sup>
									）
								</th>
								<th class="f-12" width="15%">
									<s:property value="allCostTotal" />
									（万）
								</th>
								<!--  
								<th class="f-12" width="25%">
									2015-10-11(最新时间)
								</th>
								-->
							</tr>
						</thead>
					</table>
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
	"aaSorting": [[ 0, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,1,2,4,5]}// 制定列不参与排序
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
	</body>
</html>