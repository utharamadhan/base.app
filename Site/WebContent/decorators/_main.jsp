<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
    <jsp:include page="/templates/header.jsp"></jsp:include>
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/common-site.js"></script>
	<body>
		<div class="main_wrapper">
			<jsp:include page="/templates/navbar.jsp"></jsp:include>
			<div class="content_body"><decorator:body /></div>
			<jsp:include page="/templates/footer.jsp"></jsp:include>
		</div>
		<div class="template_part"></div>
		<script src="<%=request.getContextPath()%>/js/moment.min.js"></script>
	    <script src="<%=request.getContextPath()%>/plugin/vendor/owl-carousel/owl.carousel.min.js"></script>
	    <script src="<%=request.getContextPath()%>/js/fullcalendar.min.js"></script>
	    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.flexslider.js"></script>
	    <script src="<%=request.getContextPath()%>/js/jquery.selectbox-0.1.3.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.magnific-popup.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath()%>/js/jspdf.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/running-text/breakingNews.js.download"></script>
		<script src="<%=request.getContextPath()%>/js/parallax.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath()%>/js/mrdy-plugin.js"></script>
		<script src="<%=request.getContextPath()%>/js/custom.js"></script>
	</body>
</html>