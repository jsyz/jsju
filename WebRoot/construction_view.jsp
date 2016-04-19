<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>文明施工</title>
</head>
<body>
<div class="xmWraper ">
<form action="constructionAction!update" method="post" enctype="multipart/form-data"> 
    <s:hidden name="construction.id"></s:hidden>
    <s:hidden name="areaIndex"></s:hidden>
    <s:hidden name="pid"></s:hidden>
   <div class="xmconbox pd-20">
   <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
   <nav>
   <input type="submit" value="保存"
							style="line-height: 1.6em; margin-top: 3px"
							class="btn btn-success radius r mr-5 f-r" />
   <a class="btn btn-success radius r mr-5 f-r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新当前页" ><i class="Hui-iconfont">&#xe68f;</i></a><a class="btn btn-success radius r mr-5 f-r" style="line-height:1.6em;margin-top:3px" href="javascript:history.go(-1);" title="返回" ><i class="Hui-iconfont">&#xe66b;</i></a></nav>
   <p style="line-height:35px; margin-bottom:0px; text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.5);"><i class="Hui-iconfont">&#xe64b;</i> 当前片区：<s:property value="areaVO.areaName"/><span class="pipe">|</span>【项目总数  <s:property value="areaVO.projectNumberTotal" />　　建筑面积 <s:property value="areaVO.buildingAreaTotal" /><sup>2</sup>     　　造价  <s:property value="areaVO.buildingCostTotal" />万 】<a href="yxareaAction!list"><span class="label label-warning radius">片区切换</span></a></p>
   </div>
   </div>
    
   <div class="xmconbox pd-20">
   <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
   <p><i class="Hui-iconfont">&#xe623;</i> <s:property value= "project.name"/>- 文明施工</p>
   </div>
   
  <div class="row cl Huialert-info box-shadow pd-5 bk-gray radius">
 
    <table class="table table-border table-bordered radius table-bg " style="background-color:#FFF">
    <thead class="text-c">
    <tr>
        <th width="5%">序号</th>
        <th width="37%">文明施工内容</th>
        <th width="15%">状态</th>
        <th width="10%">查看图片</th>
        <th width="10%">操作</th>
        </tr>
    </thead>
      <tbody>
      
      <tr class="text-c">
        <td class="text-c">1</td>
        <td>冲洗设备是否设置</td>
        <td><div class="formControls col-12">
		<!--  	<div class="radio-box">
					<input type="radio" id="r-1" name="r" datatype="*" checked="true">
					<label for="r-1">是</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="r-2" name="r">
					<label for="r-2">否</label>
					
				</div>
				-->
				<s:radio list="#{'1':'是','0':'否'}" name="construction.isWashSet" value="construction.isWashSet"/>
			</div> 
				
			</td>
     	<!--   <td class="text-c"><input type="file" value="上传图片"></input></td> -->
      			<s:if test ="%{construction.washSetImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture1" accept="image/jpeg,image/png,image/jpg"
										 id="myfile1"></s:file>
					</td>
				</s:if>
				<s:else>
					<td>
					<s:hidden name ="construction.washSetImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "1"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteConstructionpic(<s:property value="1" />,<s:property value="construction.id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else>
        </tr>
        
      <tr class="text-c">
        <td class="text-c">2</td>
        <td>场内排水是否畅通</td>
        <td><div class="formControls col-12">
        <!--  
          <div class="radio-box">
            <input type="radio" id="r-3" name="r1" datatype="*" checked="true">
            <label for="r-3">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-4" name="r">
            <label for="r-4">否</label>
            </div>
            -->
            <s:radio list="#{'1':'是','0':'否'}" name="construction.isWaterClear" value="construction.isWaterClear"/>
        </div>
        
        	
        </td>
       	<s:if test ="%{construction.waterClearImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture2" accept="image/jpeg,image/png,image/jpg"
										 id="myfile2"></s:file>
					</td>
				</s:if>
				<s:else>
					<td>
					<s:hidden name ="construction.waterClearImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "2"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
      <tr class="text-c">
        <td class="text-c">3</td>
        <td>电动伸缩门是否符合要求</td>
        <td><br>
        <div class="formControls col-12 skin-minimal " >
        <!--  
             <div class="radio-box">
            <input type="radio" id="r-11" name="r2" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
         -->
          <s:radio list="#{'1':'是','0':'否'}" name="construction.isDoorConform" value="construction.isDoorConform"/>
        </div>
        
         
        <br></td>
         	<s:if test ="%{construction.doorConformImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture3" accept="image/jpeg,image/png,image/jpg"
										 id="myfile3"></s:file>
					</td>
				</s:if>
				<s:else>
					<td>
					<s:hidden name ="construction.doorConformImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "3"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
      <tr class="text-c">
        <td class="text-c">4</td>
        <td>门卫是否符合要求</td>
        <td> <div class="formControls col-12 skin-minimal " >
        <!--  
          <div class="radio-box">
            <input type="radio" id="r-11" name="r3" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
            -->
             <s:radio list="#{'1':'是','0':'否'}" name="construction.isGuardConform" value="construction.isGuardConform"/>
        </div></td>
        <s:if test ="%{construction.guardConformImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture4" accept="image/jpeg,image/png,image/jpg"
										 id="myfile4"></s:file>
					</td>
				</s:if>
				<s:else>
					<td>
					<s:hidden name ="construction.guardConformImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "4"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
      </tr>
      <tr class="text-c">
        <td class="text-c">5</td>
        <td>围挡及公益广告是否符合要求</td>
        <td><div class="formControls col-12">
        <!-- 
          <div class="radio-box">
            <input type="radio" id="r-11" name="r5" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
             -->
              <s:radio list="#{'1':'是','0':'否'}" name="construction.isAdConform" value="construction.isAdConform"/>
        </div></td>
        <s:if test ="%{construction.adConformImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture5" accept="image/jpeg,image/png,image/jpg"
										 id="myfile5"></s:file>
					</td>
				</s:if>
       			 <s:else>
					<td>
					<s:hidden name ="construction.adConformImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "5"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
      <tr class="text-c">
        <td class="text-c">6</td>
        <td>主要道路是否硬化</td>
        
        <td><div class="">   <div class="radio-box">
        <!--  
            <input type="radio" id="r-11" name="r6" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
            -->
            <s:radio list="#{'1':'是','0':'否'}" name="construction.isRoadHarden" value="construction.isRoadHarden"/>
        </div></td>
         <s:if test ="%{construction.roadHardenImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture6" accept="image/jpeg,image/png,image/jpg"
										 id="myfile6"></s:file>
					</td>
				</s:if>
       			 <s:else>
					<td>
					<s:hidden name ="construction.roadHardenImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "6"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
      <tr class="text-c">
        <td class="text-c">7</td>
        <td>主要道路是否通畅</td>
        <td> <div class="formControls col-12 skin-minimal " >
        <!--  
          <div class="radio-box">
            <input type="radio" id="r-11" name="r7" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
            -->
            <s:radio list="#{'1':'是','0':'否'}" name="construction.isRoadClear" value="construction.isRoadClear"/>
        </div></td>
          <s:if test ="%{construction.roadClearImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture7" accept="image/jpeg,image/png,image/jpg"
										 id="myfile7"></s:file>
					</td>
				</s:if>
       			 <s:else>
					<td>
					<s:hidden name ="construction.roadClearImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "7"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
      <tr class="text-c">
        <td class="text-c">8</td>
        <td>公示标牌内容是否齐全</td>
        <td><div class="formControls col-12 skin-minimal " >
        <!-- 
           <div class="radio-box">
            <input type="radio" id="r-11" name="r8" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
            -->
             <s:radio list="#{'1':'是','0':'否'}" name="construction.isLabelComplete" value="construction.isLabelComplete"/>
        </div></td>
        <s:if test ="%{construction.labelCompleteImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture8" accept="image/jpeg,image/png,image/jpg"
										 id="myfile8"></s:file>
					</td>
				</s:if>
       			 <s:else>
					<td>
					<s:hidden name ="construction.labelCompleteImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "8"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
      <tr class="text-c">
        <td class="text-c">9</td>
        <td>作业区、工作区和生活区采取隔离措施，布局是否合理</td>
        <td><div class="formControls col-12 skin-minimal " >
        <!--  
           <div class="radio-box">
            <input type="radio" id="r-11" name="r9" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
            -->
            <s:radio list="#{'1':'是','0':'否'}" name="construction.isLayoutRational" value="construction.isLayoutRational"/>
        </div></td>
       <s:if test ="%{construction.layoutRationalImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture9" accept="image/jpeg,image/png,image/jpg"
										 id="myfile9"></s:file>
					</td>
				</s:if>
       			 <s:else>
					<td>
					<s:hidden name ="construction.layoutRationalImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "9"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
	<tr class="text-c">
        <td class="text-c">10</td>
        <td>办公区、生活区临时建筑是否满足防火要求</td>
        <td><br><div class="formControls col-12 skin-minimal " >
        <!-- 
           <div class="radio-box">
            <input type="radio" id="r-11" name="r10" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
             -->
             <s:radio list="#{'1':'是','0':'否'}" name="construction.isMeetFire" value="construction.isMeetFire"/>
        </div><br></td>
         <s:if test ="%{construction.meetFireImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture10" accept="image/jpeg,image/png,image/jpg"
										 id="myfile10"></s:file>
					</td>
				</s:if>
       			 <s:else>
					<td>
					<s:hidden name ="construction.meetFireImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "10"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        </tr>
      <tr class="text-c">
        <td class="text-c">11</td>
        <td>建筑扬尘防治措施是否到位</td>
        <td><div class="formControls col-12 skin-minimal " >
        <!--  
           <div class="radio-box">
            <input type="radio" id="r-11" name="r11" datatype="*" checked="true">
            <label for="r-11">是</label>
            </div>
          <div class="radio-box">
            <input type="radio" id="r-12" name="r">
            <label for="r-12">否</label>
            </div>
         -->
          <s:radio list="#{'1':'是','0':'否'}" name="construction.isMeasurePlace" value="construction.isMeasurePlace"/>
        </div></td>
        <s:if test ="%{construction.measurePlaceImg == null}">
      				 <td class="text-c" colspan="3" align="center">
      				 		<s:file name="picture11" accept="image/jpeg,image/png,image/jpg"
										 id="myfile11"></s:file>
					</td>
				</s:if>
       			 <s:else>
					<td>
					<s:hidden name ="construction.measurePlaceImg"/>
						<a href="javascript:void(0);"
							onClick="addPage('查看图片','constructionAction!loadPic?pic_row=<s:property value = "11"/>&cid=<s:property value = "construction.id"/>','650','300')"><img
							width="45px;" height="35px;" src="images/picture.png"></img>
						</a>
					</td>
					<td>
						<a style="text-decoration: none" class="ml-5"
															href="javascript:;"
															onclick="deleteEducationpic(<s:property value="id" />);"
															title="删除"><i class="Hui-iconfont">&#xe6e2;</i> </a>
					</td>
				</s:else> 
        
        </tr>

		
      </tbody>
     
    </table>
    
    
   
  </div>

  </div>
   </form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
	<script type="text/javascript">
															function change1() {
															    var pic1 = document.getElementById("myimage1"),
															        file1 = document.getElementById("myfile1");
															    var ext1=file1.value.substring(file1.value.lastIndexOf(".")+1).toLowerCase();
															     // gif在IE浏览器暂时无法显示
															     if(ext1!='png'&&ext1!='jpg'&&ext1!='jpeg'){
															         alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
															         return;
															     }
															     var isIE = navigator.userAgent.match(/MSIE/)!= null,
															         isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
															     if(isIE) {
															        file1.select();
															        var reallocalpath = document.selection.createRange().text;
															 
															        // IE6浏览器设置img的src为本地路径可以直接显示图片
															         if (isIE6) {
															            pic1.src = reallocalpath;
															         }else {
															            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
															             pic1.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
															             // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
															             pic1.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
															         }
															     }else {
															        html5Reader1(file1);
															     }
															     pic1.alt = '图片';
															}
															 function html5Reader1(file1){
															     var file1 = file1.files[0];
															     var reader1 = new FileReader();
															     reader1.readAsDataURL(file1);
															     reader1.onload = function(e){
															         var pic1 = document.getElementById("myimage1");
															         pic1.src=this.result;
															     }
															 }
												</script>
</body>
</html>