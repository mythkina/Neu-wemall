<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="org.springframework.web.util.UrlPathHelper,com.neusoft.core.util.PCache" %>
<aside id="left-panel">
	<!-- User info -->
	<div class="login-info">
		<span style="text-align:center;line-height: 39px"><%=session.getAttribute("user") %></span>
	</div>
	<!-- end user info -->
	<c:set var="uri" value="<%= new UrlPathHelper().getOriginatingRequestUri(request) %>"/>
	
	<nav>
		<ul>
			<li> 
				<%-- <a href="${base}/home/index"> --%>
				<a href="#">
					<i class="fa fa-lg fa-fw fa-picture-o"></i> 
					<span class="menu-item-parent">照片管理</span>
				</a>
				<ul>
					<li class="
						<c:choose>
							<c:when test="${fn:startsWith(fn:substringAfter(uri,base), '/home/index')}">active</c:when>
							<c:when test="${fn:startsWith(fn:substringAfter(uri,base), '/home/photo')}">active</c:when>
							<c:when test="${fn:startsWith(fn:substringAfter(uri,base), '/home/viewPhoto')}">active</c:when>
							<c:when test="${fn:startsWith(fn:substringAfter(uri,base), '/home/addPhoto')}">active</c:when>
							<c:when test="${fn:startsWith(fn:substringAfter(uri,base), '/home/addAlbum')}">active</c:when>
							<c:when test="${fn:startsWith(fn:substringAfter(uri,base), '/home/editPhoto')}">active</c:when>
							<c:when test="${fn:startsWith(fn:substringAfter(uri,base), '/home/editAlbum')}">active</c:when>
						</c:choose>
					">
						<a href="${base}/home/index">
								相册管理
						</a>
					</li>
					<li class="<c:if test="${fn:startsWith(fn:substringAfter(uri,base), '/home/searchPhoto')}">active</c:if>"><a href="${base}/home/searchPhoto">
							搜索照片
						</a>
					</li>
				</ul>
			</li>
			<li class="<c:if test="${fn:startsWith(fn:substringAfter(uri,base), '/user/user')}">active</c:if>">
				<a href="${base}/user/user">
					<i class="fa fa-lg fa-fw fa-user"></i> 
					<span class="menu-item-parent">用户管理</span>
				</a>
			</li>
			<li class="<c:if test="${fn:startsWith(fn:substringAfter(uri,base), '/home/statistics')}">active</c:if>">
				<a href="${base}/home/statistics">
					<i class="fa fa-lg fa-fw fa-bar-chart-o"></i>
					<span class="menu-item-parent">统计</span>
				</a>
			</li>
		</ul>
	</nav>
	<span class="minifyme" data-action="minifyMenu"> 
		<i class="fa fa-arrow-circle-left hit"></i> 
	</span>
</aside>