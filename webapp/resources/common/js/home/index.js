$(function() {
	// 加载首页轮动图片
	loadScrollImg(5);
	// 加载首页最新课程
	getCourseNewList();
	// 加载首页最新项目
	getProjectNewList();
});

// 复用调用服务
function ajaxLoad(uri, maxNum, tpl, showDiv) {
	// 调用服务逻辑
	$.ajax({
		url : getContextPath() + uri,
		type : "POST",
		data : {
			maxNum : maxNum
		},
		dataType : "json",
		timeout : 20000,
		beforeSend : function() {
			$('#' + showDiv).html(LOADING_SMALL);
		},
		error : function() {
			showAlert('运行错误。');
			$('#' + showDiv).html('加载失败');
		},
		complete : function() {
		},
		success : function(jsonData) {
			var tplHtml = $('#' + tpl).html();
			var forShowInfo = juicer(tplHtml, jsonData);
			$('#' + showDiv).empty().append(forShowInfo);// 在模版指定位置添加加载模版
		}
	});
}

// 加载首页轮动图片
function loadScrollImg(maxNum) {
	// 准备调用地址、返回ID 生成
	var uri = '/home/getScrollPicList';
	var tpl = 'indexScrollTpl';
	var showDiv = 'scrollPicDiv1';

	// 调用ajax服务逻辑
	ajaxLoad(uri, maxNum, tpl, showDiv);
}

// 加载首页最新课程
function loadCourseNew(maxNum) {
	// 准备调用地址、返回ID 生成
	var uri = '/home/courseNewList';
	var tpl = 'courseNewListTpl';
	var showDiv = 'courseNewList';
	// 调用ajax服务逻辑
	ajaxLoad(uri, maxNum, tpl, showDiv);
}

// 首页最新课程列表
function getCourseNewList() {
	$.ajax({
		type : "POST",
		url : getContextPath() + "/course/courseListPage",
		data : {
			queryStr : "",
			orderByStr : "",
			pageNo : 1,
			pageSize : 8
		},
		async : true,
		dataType : 'json',
		timeout : 10000,
		beforeSend : function() {
			$('#courseNewList').html(LOADING_SMALL);
		},
		error : function() {
			showAlert("查询出现错误！");
		},
		success : function(data) {
			juicer.register("formatName", getFormatName);

			var tpl = $('#courseNewListTpl').html();
			var html = juicer(tpl, data.RET_OBJ);
			$("#courseNewList").html(html);

			$("a").popover({
				trigger : 'hover'
			});

			// 绑定浮动样式
			$(".img-hover").hover(function() {
				$(this).find("p").css("color", "#666666");
				$(this).find("a").find("img").addClass("img-out");
			}, function() {
				$(this).find("p").css("color", "#333");
				$(this).find("a").find("img").removeClass("img-out");
			});
		}
	});
};

// 首页最新项目列表
function getProjectNewList() {
	$.ajax({
		type : "POST",
		url : getContextPath() + "/project/projectList",
		data : {
			prjType : "all",
			queryStr : "",
			orderByStr : "",
			pageNo : 1,
			pageSize : 8
		},
		async : true,
		dataType : 'json',
		timeout : 10000,
		beforeSend : function() {
			$('#projectNewList').html(LOADING_SMALL);
		},
		error : function() {
			showAlert("查询出现错误！");
		},
		success : function(data) {
			juicer.register("formatName", getFormatName);
			var tpl = $('#projectNewListTpl').html();
			var html = juicer(tpl, data);
			$("#projectNewList").html(html);

			$("a").popover({
				trigger : 'hover'
			});

			// 绑定浮动样式
			$(".img-hover").hover(function() {
				$(this).find("p").css("color", "#666666");
				$(this).find("a").find("img").addClass("img-out");
			}, function() {
				$(this).find("p").css("color", "#333");
				$(this).find("a").find("img").removeClass("img-out");
			});
		}
	});
};

// 设置列表图片高速
function setImgHeight() {
	$(".imgListDiv").css("height", getImgHeight());
	$(".imgListDiv img").css("width", "100%");
	$(".imgListDiv img").css("height", getImgHeight());
}

// 计算图片高度
function getImgHeight() {
	var winWidthAll = $(document).width();
	var imgHeight;
	if (winWidthAll >= 1200) {
		console.log(winWidthAll + 'winWidthAll >= 1200');
		imgHeight = winWidthAll * (5 / 6) * (3 / 12) * (9 / 14);
		imgHeight = imgHeight - 13 * 2;
	} else if (winWidthAll >= 992) {
		console.log(winWidthAll + 'winWidthAll >= 992');
		imgHeight = winWidthAll * (5 / 6) * (3 / 12) * (9 / 14);
		imgHeight = imgHeight - 13 * 2;
	} else if (winWidthAll >= 768) {
		console.log(winWidthAll + 'winWidthAll >= 768');
		imgHeight = winWidthAll * (3 / 12) * (9 / 14);
		imgHeight = imgHeight - 13 * 2;
	} else {
		console.log(winWidthAll + 'winWidthAll < 768');
		imgHeight = winWidthAll * (4 / 12) * (9 / 14);
		imgHeight = imgHeight - 13 * 2;
	}
	return imgHeight;
}

// 格式化资源名称
var getFormatName = function(data) {
	return cut_str(data, 9);
}

// 字符串截取 + ..
function cut_str(str, len) {
	if (str == null || str == "") {
		return "";
	}
	var returnStr = "";
	var char_length = 0;
	for (var i = 0; i < str.length; i++) {
		var son_str = str.charAt(i);
		encodeURI(son_str).length > 2 ? char_length += 1 : char_length += 0.5;
		if (char_length >= len) {
			var sub_len = char_length == len ? i + 1 : i;
			returnStr = str.substr(0, sub_len);
			break;
		}
	}
	if (returnStr == "") {
		returnStr = str;
	} else {
		returnStr = returnStr + "..";
	}
	return returnStr;
}
