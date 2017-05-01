<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
<div class="header fixed-top">
			<nav class="navbar navbar-default">
			  <div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="header_inner">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
							  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							  </button>
							  <a class="navbar-brand logo clearfix" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/logo-btn.png" alt="" class="img-responsive logo-btn"></a>
							  <a class="navbar-brand logo clearfix" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/logo-hfc.png" alt="" class="img-responsive"></a>
							  <a class="navbar-brand logo clearfix border-left-yellow" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/hfc-title.png" alt="" class="img-responsive"></a>
							</div>
							<div class="collapse navbar-collapse" id="bs-navbar-collapse-1">
							  <ul class="nav navbar-nav navbar-right">
								<li id="beranda" class=""><a class="parent-menu" href="<%=request.getContextPath()%>/">Beranda</a></li>
								<li id="tentang-kami" class="dropdown mega-dropdown">
								  <a href="#" class="parent-menu dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Tentang Kami <span class="caret"></span></a>
								  <ul class="dropdown-menu mega-dropdown-menu row about-us-mega-menu">
									<li class="col-sm-4">
										<ul>
											<c:forEach items="${posts}" var="post" varStatus="loop">
											<li class="dropdown-header"><a href="${ctx}/page/post/${post.pkCommonPost}/${fn:replace(post.title,' ','-')}">${post.title}</a></li>
											<li class="divider"></li>
											</c:forEach>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/lecturer">Tim Pengajar</a></li>
											<li class="divider"></li>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/advisory/sub/advisor">Tim Advisor</a></li>
										</ul>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">Kerjasama</a></li>
											<c:forEach items="${engages}" var="engage" varStatus="loop">
											<li><a href="${ctx}/page/engagement/${engage.permalink}">${engage.title}</a></li>
											</c:forEach>
										</ul>
										<br/><a href="${ctx}/page/engagement"><span class="button-blue">Lainnya...</span></a>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">Program HFC 2016</a></li>
											<c:forEach items="${programs}" var="program" varStatus="loop">
											<li><a href="${ctx}/page/program/${program.permalink}">${program.title}</a></li>
											</c:forEach>
										</ul>
										<br/><a href="${ctx}/page/program"><span class="button-blue">Lainnya...</span></a>
									</li>
								  </ul>
								</li>
								<li id="publikasi" class="dropdown">
								  <a href="/#" class="parent-menu dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Publikasi <span class="caret"></span></a>
								  <ul class="dropdown-menu mega-dropdown-menu row publications-mega-menu">
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/ebook">Buku Elektronik</a></li>
											<c:forEach items="${ebooksLatest}" var="ebookLatest" varStatus="loop">
											<li><a href="${ctx}/page/ebook/${ebookLatest.permalink}">${ebookLatest.title}</a></li>
											</c:forEach>
										</ul>
										<br/><a href="${ctx}/page/ebook"><span class="button-blue">Lainnya...</span></a>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">Berita</a></li>
											<c:forEach items="${newsLatest}" begin="0" end="4" var="news" varStatus="loop">
											<li><a href="${ctx}/page/news/${news.permalink}">${news.title}</a></li>
											</c:forEach>
										</ul>
										<br/><a href="${ctx}/page/news"><span class="button-blue">Lainnya...</span></a>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/ebook">Kegiatan</a></li>
											<c:forEach items="${eventLatest}" var="event" varStatus="loop">
											<li><a href="${ctx}/page/event/detail/${event.permalink}">${event.title}</a></li>
											</c:forEach>
										</ul>
										<br/><a href="${ctx}/page/event/upcoming"><span class="button-blue">Lainnya...</span></a>
									</li>
								  </ul>
								</li>
								<li id="program" class="dropdown">
								  <a class="parent-menu" href="<%=request.getContextPath()%>/page/learning" role="button" aria-haspopup="true" aria-expanded="false">Program</a>
								</li>
								<li id="kontak-kami" ><a class="parent-menu" href="<%=request.getContextPath()%>/page/contact">Kontak Kami</a></li>
								<li class="btn-signup" style="display:none"><a href="#" data-toggle="modal" data-target="#sign-in-modal">Sign In</a></li>
							  </ul>
							</div><!-- /.navbar-collapse -->
							<!-- Modal -->							
							<div class="modal fade" id="aos-modal" tabindex="-1" role="dialog" aria-labelledby="aosLabel">
							  <div class="modal-dialog" role="document">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title">Area of Studies</h4>
								  </div>
								  <div class="modal-body">
									<form>
										<c:forEach items="${tags_course}" var="tag_course" varStatus="loop">
										<div class="col-md-6">
											<div class="checkbox">
											  <label><input type="checkbox" class="aos-search tags" value="${tag_course.pkTag}">${tag_course.name}</label>
											</div>
										</div>
										</c:forEach>
									</form>
									<div style="clear:both"></div>
								  </div>
								  <div class="modal-footer">
									<div>
										<button type="button" class="btn btn-default do-reset-aos reset_tag">Reset</button>
										<button type="button" class="btn btn-primary do-search-aos btn_search_tag" data-dismiss="modal">Search</button>
									</div>
								  </div>
								</div>
							  </div>
							</div>
						</div>
					</div>
				</div>
			  </div><!-- /.container -->
			</nav><!-- navbar -->
		</div>
		<script>
			jQuery(function($){
				$('.no-account').click(function(e){
					e.preventDefault();
					$('.signin-content').fadeOut(500);
					$('.reg-content').fadeIn(500);
					$('.signin-button').hide();
					$('.reg-button').show();
				});
				$('.signin-button-back').click(function(e){
					e.preventDefault();
					$('.signin-content').fadeIn(500);
					$('.reg-content').fadeOut(500);
					$('.signin-button').show();
					$('.reg-button').hide();
				});
				$('.btn-signup a').click(function(){
					$('.warning-sign-first').hide();
				});
				$('.do-signin').click(function(){
				});
				$('.do-signup').click(function(){
					window.location.href = "#";
				});
				$('.do-enroll').click(function(){
					window.location.href = "#";
				});
				$('.do-wishlist').click(function(){
					window.location.href = "#";
				});
			});
		</script>
		<!-- div class="inlineMenu margin-top" -->
		<div class="margin-top">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-xs-12">
			  </div><!-- col-xs-12 -->
			</div><!-- row clearfix-->
		  </div><!-- container  -->
		</div><!-- inlineMenu -->
