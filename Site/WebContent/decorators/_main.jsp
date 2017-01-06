<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page" %>
<%@page import="id.base.app.webMember.SessionConstants"%>
<%@page import="id.base.app.SystemConstant"%>
<%@page import="id.base.app.webMember.SessionConstants"%>
<%@page import="id.base.app.valueobject.AppFunction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
    <jsp:include page="/templates/header.jsp"></jsp:include>
	<body>
		<div class="main_wrapper">
			<jsp:include page="/templates/navbar.jsp"></jsp:include>
			<decorator:body />
			<jsp:include page="/templates/footer.jsp"></jsp:include>
		</div>
		<!-- jQuery -->
	    <script type="text/javascript" async="" src="<%=request.getContextPath()%>/js/themes.iamabdus.com.js"></script>
	    <script async="" charset="utf-8" src="<%=request.getContextPath()%>/images/saved_resource" type="text/javascript"></script>
		<script type="text/javascript" async="" src="<%=request.getContextPath()%>/js/log.js"></script>
		<script async="" src="<%=request.getContextPath()%>/js/analytics.js"></script>
	    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script><!-- jQuery master -->
	    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script><!-- bootstrap -->
		<script src="<%=request.getContextPath()%>/js/jquery.flexslider.js"></script><!-- flexslider -->
	     <script src="<%=request.getContextPath()%>/js/jquery.selectbox-0.1.3.min.js"></script><!-- select box -->
		 <script src="<%=request.getContextPath()%>/js/jquery.magnific-popup.js" type="text/javascript" charset="utf-8"></script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/js/optionswitcher.js"></script><!-- custom js -->
		<script src="<%=request.getContextPath()%>/js/custom.js"></script><!-- custom js -->
	</body>
</html>