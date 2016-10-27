/**
 * 系统公告列表页面JS
 * 
 * @chechangying
 */

// 全局参数
var totalNum = 0;

// 分页开始
var pageNo = 1;
var allPage = 1;
var pageSize = 5;
function changePage(page) {
	pageNo = page;
}
// 分页结束

/**
 * 初始化
 */
$(document).ready(function() {

	// 获取系统公告列表
	getNoticeList();

});

// 系统公告列表
var getNoticeList = function() {
	$.ajax({
		type : "POST",
		url : getContextPath() + "/personal/notice/list",
		data : {
			pageNo : pageNo,
			pageSize : pageSize
		},
		async : true,
		dataType : 'json',
		beforeSend : function() {
			$('#noticeListTplDiv').html(LOADING_SMALL);
		},
		error : function() {
			showAlert("查询出现错误！");
		},
		success : function(data) {
			var tpl = $('#noticeListTpl').html();
			var html = juicer(tpl, data.RET_OBJ);
			$("#noticeListTplDiv").html(html);

			if (data.RET_OBJ && data.RET_OBJ.totalNum) {
				totalNum = parseInt(data.RET_OBJ.totalNum);
				allPage = Math.ceil(totalNum / pageSize);
				if (allPage < 1) {
					allPage = 1;
				}
			}

			// 调用通用分页
			callPageShow('pagination', pageNo, allPage, getNoticeList,
					changePage);
		}
	});
};

/**
 * 用户查看系统公告
 */
function addNoticeUser(noticeId) {
	$.ajax({
		type : "POST",
		url : getContextPath() + "/personal/notice/addUser",
		data : {
			noticeId : noticeId
		},
		async : true,
		dataType : 'json',
		beforeSend : function() {
		},
		error : function() {
			showAlert("查看公告出现错误！");
		},
		success : function(data) {
			if (data.loginStatus == 'no') {
				showAlert("无法查看公告！");
			} else if (data.RET_CODE == 'success') {
				window.location.href = getContextPath()
						+ "/personal/notice/info?noticeId=" + noticeId;
			} else {
				showAlert("查看公告失败！");
			}
		}
	});
}
