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
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
					<nav>
					<a class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新当前页"><i
						class="Hui-iconfont">&#xe68f;</i> </a><a
						class="btn btn-success radius r mr-5 f-r"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:history.go(-1);" title="返回表"><i
						class="Hui-iconfont">&#xe66b;</i> </a>
					</nav>

				</div>
			</div>
			<div class="xmconbox pd-20">
				<div class="row cl box-shadow pd-10  bk-gray radius"
					style="background-color: #FFF;">
					<div class="cl pd-5 bg-1 bk-gray mb-20">
						<span class="l"> <a href="javascript:;" onClick="datadel()"
							class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
								导出word表格</a> <a class="btn btn-primary radius"
							onClick="article_add('新增项目','xm-add.html')" href="javascript:;"><i
								class="Hui-iconfont">&#xe600;</i> 导出excl表格</a> </span>
						
					</div>
					<form name="integratedQueryListForm" method="post"
						action="integratedQueryAction!count" target="_self">
						<div class="text-c">
							<!-- 
  <select class="input-text" style="width:120px;margin:0 auto" datatype="*" nullmsg="请选择" >
          <option value="1">施工单位</option>
          <option value="2">监理单位</option>
        </select>
       -->

							<s:select list="#{0:'选择类型',1:'施工单位',2:'监理单位',3:'项目总监',4:'项目经理'}"
								cssClass="input-text" name="con" listKey="key" listValue="value"
								cssStyle="width:180px"></s:select>
							<!-- 
							<input type="text" name="input" id="input" placeholder=" 请输入关键字"
								style="width: 180px" class="input-text">
							-->
							<s:textfield name="convalue" id="convalue" placeholder=" 请输入关键字"
								style="width: 180px" cssClass="input-text"></s:textfield>
							<!-- 
							<s:select list="#{0:'选择类型',1:'总监',2:'项目经理'}"
								cssClass="input-text" name="personType" listKey="key" listValue="value"
								cssStyle="width:180px"></s:select>
							
							<input type="text" name="input1" id="input1"
								placeholder=" 请输入关键字" style="width: 180px" class="input-text">
								
							<s:textfield name="input1" id="input1" placeholder=" 请输入关键字"
								style="width: 180px" cssClass="input-text"></s:textfield>	
							-->	
							<button name="" id="input" class="btn btn-success" type="submit">
								<i class="Hui-iconfont">&#xe665;</i> 搜索
							</button>



						</div>
					</form>
					<div class="mt-20">
						<div style="text-align: center; line-height: 1.5; font-size: 24px">
							<s:property value="showName" />
						</div>
						<table
							class="table table-border table-bordered table-hover table-bg">
							<thead>
								<s:if test="%{integratedQuertyType == 0}">
							
								</s:if>
								
								<s:if test="%{integratedQuertyType == 1}">

								</s:if>
								<s:if test="%{integratedQuertyType == 2}">

								</s:if>
								<s:if test="%{integratedQuertyType == 3}">

								</s:if>
								<tr class="text-c">
									<th width="35">
										<input type="checkbox" name="" value="">
									</th>
									<th width="51">
										序号
									</th>
									<th width="97">
										乡镇
									</th>
									<th width="151">
										项目名称
									</th>
									<th width="71">
										面积m
										<sup>
											2
										</sup>
									</th>
									<th width="70">
										造价(万)
									</th>
									<th width="68">
										开工日期
									</th>
									<th width="68">
										竣工日期
									</th>
									<th width="68">
										 <s:if test="%{isConstructionUnit == 1}">监理单位</s:if>
    									<s:else>施工单位</s:else>
									</th>
									<th width="68">
										项目经理
									</th>
									<th width="68">
										项目总监
									</th>
									<th width="68">
										形象进度
									</th>
									
								</tr>
							</thead>
							<tbody>
								<s:if test="%{projects.size()==0}">
									<td colspan="14" align="center">
										暂无任何数据
									</td>
								</s:if>
								<s:iterator value="projects" var="project" status="index">
									<tr class="text-c">
										<td>
											<input type="checkbox" value="" name="">
										</td>
										<td>
											<s:property value="#index.index+1" />
										</td>
										<td class="text-c">
											<s:property value="yxarea.areaname" />
										</td>
										<td>
											<s:property value="name" />
										</td>
										<td>
											<s:property value="buildingArea" />
										</td>
										<td>
											<s:property value="buildingCost" />
										</td>
										<td>
											<s:property value="startDate" />
										</td>
										<td>
											<s:property value="planendDate" />
										</td>
										<td>
										<s:if test="%{isConstructionUnit == 1}"> <s:property value="supervisionUnit" /></s:if>
										<s:else><s:property value="constructionUnit" /></s:else>
											
										</td>
										<td>
											<s:property value="projectManager" />
										</td>
										<td>
											<s:property value="engineeringDirector" />
										</td>
										<td>
											<s:property value="graphicProgress" />
										</td>
										
									</tr>
								</s:iterator>
							</tbody>
						</table>

						<div
							style="text-align: center; line-height: 1.5; font-size: 24px; margin-top: 10px;">
							奖惩情况
						</div>
						<table
							class="table table-border table-bordered table-hover table-bg">
							<thead>
								<tr class="text-c">
									<th width="35">
										<input type="checkbox" name="" value="">
									</th>
									<th width="51">
										序号
									</th>
									<th width="151">
										项目名称
									</th>
									<th width="300">
										奖惩内容
									</th>
									<th width="68">
										时间
									</th>
									<th width="68">
										备注
									</th>
									<th width="103">
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<tr class="text-c">
									<td>
										<input type="checkbox" value="" name="">
									</td>
									<td>
										001
									</td>
									<td class="text-c">
										政府投资
									</td>
									<td>
										<a href="xmshow.html">官林市某中心</a>
									</td>
									<td>
										2010
									</td>
									<td></td>
									<td class="f-14 td-manage">
										<a style="text-decoration: none" class="ml-5"
											onClick="article_edit('项目编辑','xm-add.html','10001')"
											href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>
										</a>
										<a style="text-decoration: none" class="ml-5"
											onClick="article_del(this,'10001')" href="javascript:;"
											title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
									</td>
								</tr>
							</tbody>
						</table>

						<ul class="forminfo" style="line-height: 40px; font-size: 14px;">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="1" bgcolor="#93CDF3" style="margin-top: 8px">
								<tr align="right" bgcolor="#EEF4EA">
									<td height="34" align="center" bgcolor="#FFFFFF">
										&nbsp;

									</td>
									<td height="34" colspan="6" align="center" bgcolor="#FFFFFF">
										<a href="" target="rightFrame">首页</a>&nbsp;&nbsp;
										<a href="" target="rightFrame">上一页</a>&nbsp;&nbsp;&nbsp;
										<a href="" target="rightFrame">下一页</a>&nbsp;&nbsp;&nbsp;
										<a href="" target="rightFrame">尾页</a>&nbsp;&nbsp;&nbsp;
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
	</body>
</html>