
$(document).ready(function() { 

	$("#clearButton").click(function(){
		$("#convalue").val("");
	});


}); 

$(document).ready()

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

function test()
{
	var test = confirm("你好"); 
	if(confirm('你确定删除这些设备吗？'))
		{
	console.log("test");
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

