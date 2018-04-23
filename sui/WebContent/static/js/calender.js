$(function() {
	initCalender();
})

function initCalender() {
	var haveTaskDay = new Array(9, 13, 20);

	var today = new Date();

	var year = today.getFullYear(); // 本年
	var month = today.getMonth() + 1; // 本月
	var day = today.getDate(); // 本日

	// 本月第一天是星期几（距星期日离开的天数）
	var startDay = new Date(year, month - 1, 1).getDay();

	// 本月有多少天(即最后一天的getDate()，但是最后一天不知道，我们可以用“上个月的0来表示本月的最后一天”)
	var nDays = new Date(year, month, 0).getDate();

	// 开始画日历
	var numRow = 0; // 记录行的个数，到达7的时候创建tr
	var i; // 日期
	var html = '';
	html += '<table id="LBody" width="100%"><tbody>';
	// 第一行
	html += '<tr>';
	for (i = 0; i < startDay; i++) {
		html += '<td></td>';
		numRow++;
	}
	for (var j = 1; j <= nDays; j++) {
		// 如果是今天则显示红色
		if (j == day) {
			html += '<td style="color:red;border:1px rgb(191,191,191) solid">';
			html += '<div class="dayDiv">'
			html += j; // 开始加日期
		} else {
			html += '<td  style="border:1px rgb(191,191,191) solid">';
			html += '<div class="dayDiv">'
			html += j; // 开始加日期
			if (arrayHaveVal(haveTaskDay, j)) {
				html += '<br><span  class ="taskText">代办</span>';
			}

		}
		html += '</div>'
		html += '</td>';
		numRow++;
		if (numRow == 7) { // 如果已经到一行（一周）了，重新创建tr
			numRow = 0;
			html += '</tr><tr>';
		}
	}

	html += '</tbody></table>';
	document.getElementById("Container").innerHTML = html;
}

function arrayHaveVal(array, val) {
	for (var i = 0; i < array.length; i++) {
		if (val == array[i]) {
			return true;
		}
	}
	return false;
}
