
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
