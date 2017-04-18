<%@page import="id.base.app.LoginSession"%>
<style>
.unread-notification {
	color : red;
}
.notification-label {
	position: absolute;
    top: 5px;
    right: 35px;
    background: red;
    font-size: 12px;
    line-height: 100%;
    padding: 3px;
    color: #fff !important;
}
</style>
<ul class="nav navbar-nav quick-actions">
	<li class="dropdown divided" id="current-user" style="min-width: 150px">
		<a id="dLabel" class="dropdown-toggle options" data-toggle="dropdown" href="#" style="float: right" role="button">
			<i class="fa fa-bell" data-count="2">
			</i>
			<i class="fa fa-caret-down"></i>
		</a>
		<div id="container-unread-count" style="display: none;">
			<label id="notification-unread-count" class="notification-label"></label>
		</div>
		<section id="notification-five-list"></section>
	</li>
	<li class="dropdown divided user" id="current-user" style="min-width: 163px">
		<a class="dropdown-toggle options" data-toggle="dropdown" href="#" style="float: right"> 
			<span class="fa-stack fa-lg"> 
				<i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-key fa-stack-1x fa-inverse"></i>
			</span>
			<% LoginSession user = (LoginSession) session.getAttribute("user");%><%=user.getName() %>
			<i class="fa fa-caret-down"></i>
		</a>
		<ul class="dropdown-menu arrow settings">
			<li>
				<a href="#"><i class="fa fa-user"></i> Profile</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="#" id="logoutBtn"><i class="fa fa-power-off"></i>Logout</a>
				<script>
					$('#logoutBtn').click(function(){ 
						window.location.href = '/Web/do/login/out';
					});
				</script>
			</li>
		</ul>
	</li>
</ul>