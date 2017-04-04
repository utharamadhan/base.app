<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page" %>
<%@page import="id.base.app.webMember.SessionConstants"%>
<%@page import="id.base.app.SystemConstant"%>
<%@page import="id.base.app.webMember.SessionConstants"%>
<%@page import="id.base.app.valueobject.AppFunction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
    <jsp:include page="/templates/header.jsp"></jsp:include>
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script><!-- jQuery master -->
    <script src="<%=request.getContextPath()%>/js/common-site.js"></script><!-- jQuery master -->
	<body>
		<div class="main_wrapper">
			<jsp:include page="/templates/navbar.jsp"></jsp:include>
			<div class="content_body"><decorator:body /></div>
			<jsp:include page="/templates/footer.jsp"></jsp:include>
		</div>
		<div class="template_part">
		</div>
		<!-- jQuery -->
	    <%-- <script async="" charset="utf-8" src="<%=request.getContextPath()%>/images/saved_resource" type="text/javascript"></script>
		<script type="text/javascript" async="" src="<%=request.getContextPath()%>/js/log.js"></script>
		<script async="" src="<%=request.getContextPath()%>/js/analytics.js"></script> --%>
		<script src="<%=request.getContextPath()%>/js/moment.min.js"></script><!-- jQuery master -->
	    <script src="<%=request.getContextPath()%>/plugin/vendor/owl-carousel/owl.carousel.min.js"></script>
	    <script src="<%=request.getContextPath()%>/js/fullcalendar.min.js"></script><!-- jQuery master -->
	    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script><!-- bootstrap -->
		<script src="<%=request.getContextPath()%>/js/jquery.flexslider.js"></script><!-- flexslider -->
	     <script src="<%=request.getContextPath()%>/js/jquery.selectbox-0.1.3.min.js"></script><!-- select box -->
		 <script src="<%=request.getContextPath()%>/js/jquery.magnific-popup.js" type="text/javascript" charset="utf-8"></script>
		 <script src="<%=request.getContextPath()%>/js/socialCircle.js" type="text/javascript" charset="utf-8"></script> <!-- Behaviour Share Button -->
		 <%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/optionswitcher.js"></script><!-- custom js --> --%>
		 <%-- <script src="<%=request.getContextPath()%>/plugin/vendor/quill/quill-1.1.7.js"></script> --%>
		 <script src="<%=request.getContextPath()%>/js/parallax.min.js" type="text/javascript" charset="utf-8"></script>
		 <script src="<%=request.getContextPath()%>/js/running-text/breakingNews.js.download"></script><!-- mrdy plugin js -->
		<script src="<%=request.getContextPath()%>/js/custom.js"></script><!-- custom js -->
		<script src="<%=request.getContextPath()%>/js/mrdy-plugin.js"></script><!-- mrdy plugin js -->
	</body>
</html>
