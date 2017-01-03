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
			<div class="banner carousel slide" id="recommended-item-carousel" data-ride="carousel">
				<div class="slides carousel-inner">
					<div class="item active">
						<div class="back-carousel-video">
							<iframe width="676" height="380" src="https://www.youtube.com/embed/arlnzivE6t0" frameborder="0" allowfullscreen></iframe>
						</div>
						<img src="<%=request.getContextPath()%>/images/back-learning.jpg" class="back-img-video" alt="" style="height:460px"/>
					</div>
					<div class="item">
						<img src="<%=request.getContextPath()%>/images/learning.jpg" class="hover-gray" alt="" style="height:460px"/>
						<div class="banner_caption">
							<div class="container">
								<div class="row">
									<div class="col-xs-12">
										<div class="caption_inner">
											<h1>Learning</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean consectetur ante volutpat sem aliquam lobortis. Mauris porta fermentum volutpat. Praesent est sapien, tincidunt vel arcu vitae, mattis sollicitudin lectus.</p>
											<a href="#">Try Free Now!</a>
										</div><!--end caption_inner-->
									</div>
								</div><!--end row-->
							</div><!--end container-->
						</div><!--end banner_caption-->
					</div>
					<div class="item">
						<img src="<%=request.getContextPath()%>/images/research.jpg" class="hover-gray" alt="" style="height:460px"/>
						<div class="banner_caption">
							<div class="container">
								<div class="row">
									<div class="col-xs-12">
										<div class="caption_inner">
											<h1>Research & Development</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean consectetur ante volutpat sem aliquam lobortis. Mauris porta fermentum volutpat. Praesent est sapien, tincidunt vel arcu vitae, mattis sollicitudin lectus.</p>
											<a href="#">Check it Now!</a>
										</div><!--end caption_inner-->
									</div>
								</div><!--end row-->
							</div><!--end container-->
						</div><!--end banner_caption-->
					</div>
					<div class="item">
						<img src="<%=request.getContextPath()%>/images/advisory.png" class="hover-gray" alt="" style="height:460px"/>
						<div class="banner_caption">
							<div class="container">
								<div class="row">
									<div class="col-xs-12">
										<div class="caption_inner">
											<h1>Advisory</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean consectetur ante volutpat sem aliquam lobortis. Mauris porta fermentum volutpat. Praesent est sapien, tincidunt vel arcu vitae, mattis sollicitudin lectus.</p>
											<a href="#">Try Free Now!</a>
										</div><!--end caption_inner-->
									</div>
								</div><!--end row-->
							</div><!--end container-->
						</div><!--end banner_caption-->
					</div>
				</div>
				
				<a class="left recommended-item-control" href="/index.html#recommended-item-carousel" data-slide="prev">
					<img src="<%=request.getContextPath()%>/images/prev.png">
				  </a>
				  <a class="right recommended-item-control" href="/index.html#recommended-item-carousel" data-slide="next">
					<img src="<%=request.getContextPath()%>/images/next.png">
				  </a>		
						
			</div><!--end banner-->
			
			<div class="aboutArea">
			  <div class="container">
				<div class="row clearfix">
				  <div class="col-xs-12">
				  </div><!-- col-sm-3 col-xs-12 -->
				</div><!-- row clearfix -->
				<div class="row clearfix">
				  <div class="col-xs-12">
					<div class="about_inner">
						<div class="col-md-4">
								<div class="aboutImage">
								  <img src="<%=request.getContextPath()%>/images/learning_.jpg" alt="" class="img-responsive" onclick="">
								  <a href="#" class="captionLink">Learning <span></span></a>
								</div><!-- aboutImage -->
						</div>
						<div class="col-md-4">
								<div class="aboutImage">
								  <img src="<%=request.getContextPath()%>/images/rnd_.jpg" alt="" class="img-responsive" onclick="">
								  <a href="#" class="captionLink">Research & Development <span></span></a>
								</div><!-- aboutImage -->
						</div>
						<div class="col-md-4">
								<div class="aboutImage">
								  <img src="<%=request.getContextPath()%>/images/advisory_.jpg" alt="" class="img-responsive" onclick="">
								  <a href="#" class="captionLink">Advisory <span></span></a>
								</div><!-- aboutImage -->
						</div>
					</div>
				  </div><!-- col-sm-3 col-xs-12 -->
				  
				</div><!-- row clearfix -->
			  </div><!-- container -->
			</div><!-- aboutArea -->
			<jsp:include page="/templates/footer.jsp"></jsp:include>
		</div>
		<!-- jQuery -->
	    <script type="text/javascript" async="" src="<%=request.getContextPath()%>/js/themes.iamabdus.com.js"></script><script async="" charset="utf-8" src="./content_files/saved_resource" type="text/javascript"></script><script src="./content_files/jquery.min.js"></script><!-- jQuery master -->
	    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script><!-- bootstrap -->
		<script src="<%=request.getContextPath()%>/js/jquery.flexslider.js"></script><!-- flexslider -->
	     <script src="<%=request.getContextPath()%>/js/jquery.selectbox-0.1.3.min.js"></script><!-- select box -->
		 <script src="<%=request.getContextPath()%>/js/jquery.magnific-popup.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath()%>/js/custom.js"></script><!-- custom js -->
	</body>
</html>
