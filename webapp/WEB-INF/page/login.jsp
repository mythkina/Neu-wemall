 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/commonPage.jsp"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page">
	<head>
		<meta charset="utf-8">
		<title>Neusoft-${websiteName}</title>
		<meta name="description" content="">
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <!-- CSS -->
        <link rel="stylesheet" href="${resourcesUrl}/css/reset.css">
        <link rel="stylesheet" href="${resourcesUrl}/css/supersized.css">
        <link rel="stylesheet" href="${resourcesUrl}/css/style.css">
        <style>
        	.page-footer{
			    	height: 52px;
				    padding: 15px 13px 0;
				    width: 100%;
				    position: absolute;
				    display: block;
				    bottom: 0;
			    }
        </style>
    </head>

    <body>
    	<div>
	        <div class="page-container">
	            <h1>Login</h1>
	            <form action="${base}/home/index" method="post" id="login-form" class="smart-form client-form" AutoComplete="off">
	                <input type="text" id="username" name="username" class="username" placeholder="用户名">
	                <input type="password" id="password" name="password" class="password" placeholder="密码">
	                <span id="error" style="color: red;"></span>
	                <button type="submit">登    录</button>
	            </form>
	            <input type="text" class="hidden" style="display:none;"
							value="<%=request.getSession().getAttribute("user")%>">
	        </div>
			<div class="page-footer">
				<div class="row">
					<div class="col-lg-9 col-md-9 col-xs-12 col-sm-6">
					</div>
					<div class="col-lg-3 col-md-9 col-xs-12 col-sm-6">
						<span class="txt-color-white">东 软 图 书 管 理 平 台    Neusoft © 2016</span>
					</div>
				</div>
			</div>
		</div>
        <!-- Javascript -->
        <script src="${resourcesUrl}/common/js/jquery-1.8.2.min.js"></script>
        <script src="${resourcesUrl}/common/js/supersized.3.2.7.min.js"></script>
        <script src="${resourcesUrl}/common/js/supersized-init.js"></script>
        <script src="${resourcesUrl}/common/js/scripts.js"></script>
        <script src="${resourcesUrl}/common/js/plugin/jquery-validate/jquery.validate.min.js"></script>
        <script src="${resourcesUrl}/common/js/plugin/pace/pace.min.js"></script>

		<!-- BOOTSTRAP JS -->		
		<script src="${resourcesUrl}/common/js/bootstrap/bootstrap.min.js"></script>
		
		<!-- 表单验证 -->
		<script src="${resourcesUrl}/common/js/plugin/jquery-validate/jquery.validate.min.js"></script>
		<!-- 表单提交 -->
		<script src="${resourcesUrl}/common/js/plugin/jquery-form/jquery-form.min.js"></script>
		<!-- JQUERY MASKED INPUT -->
		<script src="${resourcesUrl}/common/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
        
      	<script type="text/javascript">
			var NEED_CONTEXT = true;
			// 获得地址
			function getContextPath() {
				var p = document.location.pathname;
				if (NEED_CONTEXT) {
					var index = p.substr(1).indexOf("/");
					p = p.substr(0, index + 1);
					p = window.location.host + p;
				} else {
					p = window.location.host;
				}
				return 'http://' + p;
			};
			
			
			$(function() {
				
				//判断是否登录
				var logined = $(".hidden").val();
				if (logined != "null") {
					window.location.href = getContextPath() + "/home/index";
				}
				// 登录验证
				$("#login-form").validate({
					submitHandler:function(form){
						 $("#login-form").ajaxSubmit({
							type:"post",
							url:getContextPath()+"/login/getUserByName",
							success:function(data){
								if(data.RET_OBJ=="登录成功"){
									window.location.href = getContextPath() + "/home/index";
								}else{
									$("#error").text(data.RET_OBJ);
								}
							},
							error:function(){
								alert("AJAX ERROR");
							}
						})
					},
					rules : {
						username : {
							required : true
						},
						password : {
							required : true,
							minlength : 1,
							maxlength : 20
						},
					},
	
					// Messages for form validation
					messages : {
						username : {
							required : '请输入您的用户名'
						},
						password : {
							required : '请输入您的密码',
							minlength : $.validator.format("请输入最少 {0} 字符."),
							maxlength : $.validator.format("请输入最多 {0} 字符.")
						},
					}
				});
			});
	   </script>
    </body>
	
</html>
