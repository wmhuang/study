function alertM(msg) {
	$.toast(msg);
}

function confirmTask(s) {
	s.parentNode.style.display = "none";
	var msg = "任务确认成功，已添加至日程代办。";
	alertM(msg);
}

function addTask() {
	var buttons1 = [ {
		text : '请选择代办类型',
		label : true
	}, {
		text : '会议待办',
		onClick : function() {
			$.router.load("/sui/app/meetTask.html");
			//$.alert("你选择了“会议待办“");
		}
	}, {
		text : '个人待办',
		onClick : function() {
			$.router.load("/sui/app/privateTask.html");
		}
	}, {
		text : ' 事项确认',
		onClick : function() {
			$.alert("你选择了“ 事项确认“");
		}
	}, {
		text : '任务派发',
		onClick : function() {
			$.alert("你选择了“任务派发“");
		}
	}, {
		text : '其他待办',
		onClick : function() {
			$.alert("你选择了“其他待办“");
		}
	} ];
	var buttons2 = [ {
		text : '取消',
		bg : 'danger'
	} ];
	var groups = [ buttons1, buttons2 ];
	$.actions(groups);
}