$(function() {
	var userJson = $('#userJson').val();
	if (userJson != "undefined" && userJson != "" && userJson != null) {

		// 查询用户未读的系统公告数量
		queryUnReadNoticeCount();
		// 查询用户未读的站内信数量
		queryUnReadUserSmsCount();
		// 查询用户未读的好友申请数量
		queryUnReadUserFriendApplyCount();
	}
});

// 查询用户未读的系统公告数量
function queryUnReadNoticeCount() {
	// 查询满足条件系统公告总数
	$.ajax({
		url : getContextPath() + '/home/getUnReadNoticeCount',
		data : {},
		type : 'POST',
		dataType : 'json',
		timeout : 10000,
		beforeSend : function() {
		},
		error : function() {
			showAlert('查询公告信息出现错误！');
		},
		success : function(jsonData) {
			// 设定总条数显示
			var totalNum = parseInt(jsonData.RET_OBJ.totalNum);
			$('#sysNoteCount').html(totalNum);
			$('#sysNoteBadgeCount').html(totalNum);
		}
	});
}

// 查询用户未读的站内信数量
function queryUnReadUserSmsCount() {
	// 查询满足条件系统公告总数
	$.ajax({
		url : getContextPath() + '/home/getUnReadUserSmsCount',
		data : {},
		type : 'POST',
		dataType : 'json',
		timeout : 10000,
		beforeSend : function() {
		},
		error : function() {
			showAlert('查询未读站内信数量出现错误！');
		},
		success : function(jsonData) {
			// 设定总条数显示
			var totalNum = parseInt(jsonData.RET_OBJ.totalNum);
			$('#userSmsCount').html(totalNum);
		}
	});
}

// 查询用户未读的好友申请数量
function queryUnReadUserFriendApplyCount() {
	// 查询满足条件系统公告总数
	$.ajax({
		url : getContextPath() + '/home/getUnReadUserFriendApplyCount',
		data : {},
		type : 'POST',
		dataType : 'json',
		timeout : 10000,
		beforeSend : function() {
		},
		error : function() {
			showAlert('查询好友申请数量出现错误！');
		},
		success : function(jsonData) {
			// 设定总条数显示
			var totalNum = parseInt(jsonData.RET_OBJ.totalNum);
			$('#userFriendApplyCount').html(totalNum);
		}
	});
}
