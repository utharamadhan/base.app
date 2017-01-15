<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
		<div class="menuFooter">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-sm-3 col-xs-12">
				<ul class="menuLink clearfix">
				  <c:forEach items="${posts}" var="post" varStatus="loop">
				  <li><a href="${ctx}/page/post/${post.pkCommonPost}">${post.title}</a></li>
				  </c:forEach>
				  <li><a href="<%=request.getContextPath()%>/page/lecturer">Tim Pengajar</a></li>
				</ul>
			  </div><!-- col-sm-3 col-xs-12 -->
			  <div class="col-sm-3 col-xs-12 borderLeft clearfix">
				<ul class="menuLink clearfix">
				  <li><a href="<%=request.getContextPath()%>/page/learning">Learning</a></li>
				  <li><a href="<%=request.getContextPath()%>/page/research-development">Research Report</a></li>
				  <li><a href="<%=request.getContextPath()%>/page/ebook">Digital Books</a></li>
				  <li><a href="#">Photo Gallery</a></li>
				</ul>
			  </div><!-- col-sm-3 col-xs-12 -->
			  <div class="col-sm-3 col-xs-12 borderLeft clearfix">
				<ul class="menuLink clearfix">
				  <li><a href="<%=request.getContextPath()%>/page/news">Latest News</a></li>
				  <li><a href="<%=request.getContextPath()%>/page/events/upcoming">Upcoming Events</a></li>
				  <li><a href="<%=request.getContextPath()%>/page/contact">Contact Us</a></li>
				</ul>
			  </div><!-- col-sm-3 col-xs-12 -->
			  <div class="col-sm-3 col-xs-12 borderLeft clearfix">
				<div class="socialArea clearfix">
				  <h5>Find us on:</h5>
				  <ul class="list-inline ">
					<li><a href="#"><i class="fa fa-twitter"></i></a></li>
					<li><a href="#"><i class="fa fa-facebook"></i></a></li>
					<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
					<li><a href="#"><i class="fa fa-youtube-play"></i></a></li>
				  </ul>
				</div><!-- social -->
				<div class="contactNo clearfix">
				  <h5>Call us on:</h5>
				  <h3>(021) 6336789, ext. 1824</h3>
				</div><!-- social -->
			  </div><!-- col-sm-3 col-xs-12 -->
			</div><!-- row clearfix -->
		  </div><!-- container -->
		</div><!-- menuFooter -->
		<div class="footer">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-sm-5 col-xs-12">
				<p class="copyRight">@ 2016 Copyright Housing Finance Center</p>
			  </div><!-- col-sm-6 col-xs-12 -->
			</div><!-- row clearfix -->
		  </div><!-- container -->
		</div><!-- footer -->