
$(document).ready(function() { 

	$("#clearButton").click(function(){
		$("#convalue").val("");
	});


}); 


function getCheckVal()
{
	var checkedIDs='';
	for(var i =0;i<$(".indexID").length;i++)
	{
		if($(".indexID")[i].checked)
		{
			checkedIDs = checkedIDs+$(".indexID")[i].value+",";
		}
	}
}

function deleteAllCheckedUseros()
{
		if(confirm('你确定删除这些人员吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			console.log(checkedIDs);
			$.ajax({   
			            url:'deleteUseros',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data:{"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}
function deleteAllCheckedProjects()
{
		if(confirm('你确定删除这些项目吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			console.log(checkedIDs);
			$.ajax({   
			            url:'deleteProjects',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data:{"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}
function deleteAllCheckedDevices()
{
		console.log("hello");
		if(confirm('你确定删除这些设备吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			console.log(checkedIDs);
			$.ajax({   
			            url:'deleteDevices',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data:{"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}
function deleteAllCheckedPromans()
{
		console.log("hello");
		if(confirm('你确定删除这些人员吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			console.log(checkedIDs);
			$.ajax({   
			            url:'deletePromans',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data:{"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

//删除分包单位
function deleteSubunit(subid)
{
		if(confirm('你确定删除该分包单位吗？'))
		{
			$.ajax({   
			            url:'deleteSubunit',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"subid":subid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

//删除论证情况
function deleteDangerargument(danid)
{
		if(confirm('你确定删除该论证情况吗？'))
		{
			$.ajax({   
			            url:'deleteDangerargument',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"danid":danid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}


function deleteEducationpic(edid)
{
		if(confirm('你确定删除该教育图片吗？'))
		{
			$.ajax({   
			            url:'deleteEducationpic',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"edid":edid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

function deleteConstructionpic(row,conid)
{	
		if(confirm('你确定删除该图片吗？'))
		{
			$.ajax({   
			            url:'deleteConstructionpic',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: {"pic_row":row,"cid":conid},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}

function deleteAllCheckedSpreadsheets()
{
		if(confirm('你确定删除这些信息吗？'))
		{
			var checkedIDs='';
			for(var i =0;i<$(".indexID").length;i++)
			{
				if($(".indexID")[i].checked)
				{
					checkedIDs = checkedIDs+$(".indexID")[i].value+",";
				}
			}
			console.log(checkedIDs);
			$.ajax({   
			            url:'deleteSpreadsheets',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data:{"checkedIDs":checkedIDs},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('删除失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}


function changeUpload(pid,uploadState)
{
		var msg = "";
		if(uploadState==1)
		{
			msg = "你确定上传该项目吗？";
		}else
		{
			msg = "你确定撤销该项目吗？";
		}
		
		if(confirm(msg))
		{
			$.ajax({   
			            url:'changeUpload',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data:{"pid":pid,"uploadState":uploadState},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	alert('上传失败.');   
			            },   
			            success: function(msg)
			            { //成功
			            	alert(msg.message);
			            	location.replace(location.href);
						}
					});
		}
}