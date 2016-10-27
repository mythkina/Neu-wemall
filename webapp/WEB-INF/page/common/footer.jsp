<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--Basic Scripts-->
<script src="${resourcesUrl}/common/js/libs/jquery-2.0.2.min.js"></script>
<script src="${resourcesUrl}/common/js/libs/jquery-ui-1.10.3.min.js"></script>
<script src="${resourcesUrl}/common/js/libs/jquery.min.map"></script>

<!-- Bootstrap Scripts -->
<script src="${resourcesUrl}/common/js/bootstrap/bootstrap.min.js"></script>

<!--SmartAdmin Scripts-->
<script src="${resourcesUrl}/common/js/app.config.js"></script>
<script src="${resourcesUrl}/common/js/app.min.js"></script>

<!--artDialog Scripts-->
<script src="${resourcesUrl}/common/components/artDialog/dialog-min.js"></script>
<script src="${resourcesUrl}/common/components/juicer/juicer.js"></script>

<!--Custom Scripts-->
<script src="${resourcesUrl}/common/js/global-ready.js"></script>
<script src="${resourcesUrl}/common/js/custom.js"></script>
<script src="${resourcesUrl}/common/js/home/navbar.js"></script>

<!-- 表单验证 -->
<script
	src="${resourcesUrl}/common/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<!-- 表单提交 -->
<script
	src="${resourcesUrl}/common/js/plugin/jquery-form/jquery-form.min.js"></script>
<script>
$(document).ready(function(){
	$.chat_list_btn = $('#chat-container > .chat-list-open-close');
	// open chat list
	$.chat_list_btn.click(function() {
		$(this).parent('#chat-container').toggleClass('open');
	})
	
});
</script>
<div class="page-footer">
	<div class="row">
		<div class="col-lg-9 col-xs-12 col-sm-6">
		</div>
		<div class="col-lg-3 col-xs-12 col-sm-6">
			<span class="txt-color-white">东 软 照 片 管 理 平 台    Neusoft © 2016</span>
		</div>
	</div>
</div>