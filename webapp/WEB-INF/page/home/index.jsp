<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/commonPage.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
<meta charset="utf-8" />
<title></title>
<!--Page Related styles-->
</head>
<!-- /Head -->
<!-- Body -->
<body>
	<!-- 公共header start-->
	<%@ include file="/WEB-INF/page/common/header.jsp"%>	
	<!-- 公共header end-->
	<!-- 公共left bar start -->
	<%@ include file="/WEB-INF/page/common/leftSideBar.jsp"%>	
	<!-- 公共left bar end -->
	<div id="main" role="main">
		<!-- 路径导航开始 -->
		<div id="ribbon">
			<ol class="breadcrumb">
				<li>照片管理</li>
				<li>相册管理</li>
			</ol>
		</div>
		<!-- 路径导航结束 -->
		<!-- 主题内容 开始-->
		<div id="content">
			<div class="row">
				<!-- article -->
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<!-- Widget ID (each widget will need unique ID)-->
					<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"
						data-widget-editbutton="true" data-widget-togglebutton="true"
						data-widget-collapsed="false" data-widget-custombutton="true"
						data-widget-deletebutton="true"
						data-widget-fullscreenbutton="true">

						<header>
							<span class="widget-icon"> <i class="fa  fa-list"></i>
							</span>
							<h2>相册列表信息</h2>
						</header>

						<!-- widget div-->
						<div>
							<!-- widget edit box -->
							<div class="jarviswidget-editbox"></div>
							<!-- end widget edit box -->
							 <div class="row" style="margin-bottom:20px;">
							 		 <section class="col col-lg-10 col-md-10 col-sm-12 col-sm-4">
								      </section>
									<section class="col col-lg-2 col-md-2 col-sm-12 col-xs-12">
										 <div align="right">
							    			<button type="button" name="queryBtn" id="addBtn" onclick="window.location.href='${base}/home/addAlbum';"
												class="btn btn-info">创建相册</button>
										</div>
								    </section>
								</div>
							<!-- widget content -->
							<div class="widget-body no-padding" id="albumListTplDiv">
								<table id="albumTable"
									class="table table-striped table-bordered table-hover text-center">
									<thead>
										<tr>
										   
											<th class="text-center" data-field="albumname">名称</th>
											<th class="text-center" data-field="description">描述</th>
											<th class="text-center" data-field="builddate">创建时间</th>
											<th class="text-center" data-formatter="operateFormatter" data-events="operateEvents"><i
												class="fa fa-fw fa-cog txt-color-blue hidden-md hidden-sm hidden-xs"></i>
												操作</th> 
										</tr>
									</thead>
								</table>
							</div>
							<!-- end widget content -->

						</div>
						<!-- end widget div -->

					</div>
					<!-- end widget -->

				</article>
				<!-- end article -->
			</div>
		</div>
		<!-- 主体内容结束 -->
	</div>
	<%@ include file="/WEB-INF/page/common/footer.jsp"%>
	
	<!-- 引入bootstrap datatable脚本 -->
	<script type="text/javascript"
		src="${resourcesUrl}/common/components/bootstrapTable/tableExport.js"></script>
	<script type="text/javascript"
		src="${resourcesUrl}/common/components/bootstrapTable/jquery.base64.js"></script>
	<script
		src="${resourcesUrl}/common/components/bootstrapTable/bootstrap-table-all.min.js"></script>
	<script type="text/javascript"
		src="${resourcesUrl}/common/components/bootstrapTable/colResizable-1.5.min.js"></script>
	<script
		src="${resourcesUrl}/common/components/bootstrapTable/bootstrap-table-zh-CN.min.js"></script>
	<script
		src="${resourcesUrl}/common/components/bootstrapTable/lodash.min.js"></script>
		
	<!-- 自定义JS -->
	<script src="${resourcesUrl}/js/albumList.js"></script>
	
	
	
</body>
</html>
