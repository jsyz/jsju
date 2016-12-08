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
		
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="js/H-ui.js"></script>
		<script type="text/javascript" src="js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/pageKit.js"></script>
		<script type="text/javascript" src="js/checkUtil.js"></script>
		<script type="text/javascript" src="js/commonUtil.js"></script>
		
		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
		<title>空白页</title>
	</head>
	<body>
		<div class="xmWraper ">
			<div class="xmconbox pd-20">
				<div class="row cl box-shadow pd-10  bk-gray radius"
					style="background-color: #FFF;">
					<div class="cl pd-5 bg-1 bk-gray mb-20">
						<span class="l"> <a class="btn btn-primary radius"
							
							onclick="concretercord_add('新增合同备案','concreterecordAction!goToAdd',<s:property value ="concreterecordType" />)"
							href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>
								新增备案合同</a> </span>
						<span class="r">共有数据：<strong>2</strong> 条</span>

					</div>
					<div class="mt-20">
						<div style="text-align: center; line-height: 1.5; font-size: 24px">
							<div class="mt-20">
								<div class="row" style="margin-top: 5px; margin-bottom: 5px;">
									<div class="text-c">
										<form name="concreterecordForm" method="post"
											action="concreterecordAction!list?concreterecordType=<s:property value="concreterecordType" />" target="_self">
											<s:hidden name="areaIndex"></s:hidden>
											<s:hidden name="pid"></s:hidden>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" style="line-height: 35px;">
												<tr height="35">
													<td width="21%" align="right" style="padding-right: 50px;">
														<s:select list="#{0:'选择类型',1:'单位名称'}"
															cssClass="input-text" name="con" listKey="key"
															listValue="value" cssStyle="width:180px"></s:select>
													</td>
													<td width="310px;">
														<s:textfield name="convalue" id="convalue"
															cssClass="input-text"></s:textfield>
													</td>
													<td align="left" style="padding-left: 172px;">
														<button type="submit" class="btn btn-success" id="button2"
															name="" onClick="">
															<i class="Hui-iconfont">&#xe665;</i>  
														</button>
													</td>
												</tr>
											</table>
										</form>
									</div>
								</div>
							</div>
							<table
								class="table table-border table-bordered table-hover table-bg">
								<thead>
									<tr class="text-c">

										<th>
											序号
										</th>
										<th>
											单位名称
										</th>
										<th>
											<s:if test="%{concreterecordType == 0}">预拌混凝土项目合同备案表</s:if>
											<s:else>砂浆项目合同备案表</s:else>
										</th>
										<th>
											建筑材料及构配件项目负责人质量终身责任承诺书
										</th>
										<th>
											法定代表人授权书
										</th>
										<th>
											法定代表人身份证复印件
										</th>
										<th>
											项目负责人身份证复印件
										</th>
										<th>
											合同复印件
										</th>
										<th>
											操作
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="concreterecords" var="concreterecord"
										status="index">
										<tr class="text-c">

											<td>
												<s:property value="#index.index+1" />
											</td>
											<td>
												<s:property value="name" />
											</td>
											<td class="text-c">
												 <a href="javascript:void(0);"
			onClick="addPage('查看图片','concreterecordAction!loadPic?id=<s:property value = "id"/>&picid=1','650','300')">
			<img width="45px;" height="35px;" src="images/picture.png"></img>
		</a>
											</td>
											<td>
													 <a href="javascript:void(0);"
			onClick="addPage('查看图片','concreterecordAction!loadPic?id=<s:property value = "id"/>&picid=2','650','300')">
			<img width="45px;" height="35px;" src="images/picture.png"></img>
		</a>
											</td>
											<td>
													 <a href="javascript:void(0);"
			onClick="addPage('查看图片','concreterecordAction!loadPic?id=<s:property value = "id"/>&picid=3','650','300')">
			<img width="45px;" height="35px;" src="images/picture.png"></img>
		</a>
											</td>
											<td>
													 <a href="javascript:void(0);"
			onClick="addPage('查看图片','concreterecordAction!loadPic?id=<s:property value = "id"/>&picid=3','650','300')">
			<img width="45px;" height="35px;" src="images/picture.png"></img>
		</a>
											</td>
											<td>
													 <a href="javascript:void(0);"
			onClick="addPage('查看图片','concreterecordAction!loadPic?id=<s:property value = "id"/>&picid=4','650','300')">
			<img width="45px;" height="35px;" src="images/picture.png"></img>
		</a>
											</td>
											<td>
													 <a href="javascript:void(0);"
			onClick="addPage('查看图片','concreterecordAction!loadPic?id=<s:property value = "id"/>&picid=5','650','300')">
			<img width="45px;" height="35px;" src="images/picture.png"></img>
		</a>
											</td>
											<td class="f-14 td-manage">
												<a style="text-decoration: none" class="ml-5"
																				onClick="article_edit('编辑','concreterecordAction!load?id=<s:property value="id"/>','10001')"
																				href="javascript:;" title="编辑"><i
																				class="Hui-iconfont">&#xe6df;</i> </a>
												<a style="text-decoration: none" class="ml-5"
																			onClick="return confirm('你确定删除该信息吗？')" title="删除"
																			href="concreterecordAction!delete?id=<s:property value="id"/>"
																			title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
											</td>
										</tr>
									</s:iterator>
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
			<script type="text/javascript"
				src="lib/My97DatePicker/WdatePicker.js"></script>
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

function concretercord_add(title,url,type){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+'?concreterecordType='+type
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