<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${resourcesUrl}/common/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${resourcesUrl}/common/css/font-awesome.min.css">

<!-- SmartAdmin Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${resourcesUrl}/common/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${resourcesUrl}/common/css/smartadmin-skins.min.css">

<!--artDialog Styles-->
<link rel="stylesheet"
	href="${resourcesUrl}/common/components/artDialog/ui-dialog.css">

<!-- Custom stylesheets ( Put your own changes here ) -->
<link href="${resourcesUrl}/common/css/global-style.css"
	rel="stylesheet" />

<!-- HEADER -->
<header id="header">
	<div id="logo-group">
		<div
			style="text-align: center; line-height: 50px; font-weight: bold; font-size: 20px">东软照片管理平台</div>
	</div>

	<!-- pulled right: nav area -->
	<div class="pull-right">

		<!-- logout button -->
		<div id="logout" class="btn-header transparent pull-right" style="padding: 10px">
			<span>
				<button
					onclick="showConfirm('是否确定退出登录？',function(){window.location.href = getContextPath() + '/login/logout';})"
					title="退出" data-action="userLogout"
					data-logout-msg="You can improve your security further after logging out by closing this opened browser">
					<i class="fa fa-sign-out"></i>
				</button>
			</span>
		</div>
		<!-- end logout button -->

	</div>
	<!-- end pulled right: nav area -->

</header>
<!-- END HEADER -->