<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
							  </button>
							  <a class="navbar-brand logo clearfix" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/logo-btn.png" alt="" class="img-responsive logo-btn"></a>
							  <a class="navbar-brand logo clearfix" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/logo-hfc.png" alt="" class="img-responsive"></a>
							  <a class="navbar-brand logo clearfix border-left-yellow" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/hfc-title.png" alt="" class="img-responsive"></a>
							</div>
							<div class="collapse navbar-collapse" id="bs-navbar-collapse-1">
							  <ul class="nav navbar-nav navbar-right">
								<li id="beranda" class=""><a class="parent-menu" href="<%=request.getContextPath()%>/">BERANDA</a></li>
								<li id="tentang-kami" class="dropdown mega-dropdown">
								  <a href="#" class="parent-menu dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">TENTANG KAMI <span class="caret"></span></a>
								  <ul class="dropdown-menu mega-dropdown-menu row about-us-mega-menu">
									<li class="col-sm-4">
										<ul id="common-post-content">
											<c:forEach items="${commonPostList}" var="post">
											<li class="dropdown-header"><a href="${ctx}/page/about-us/${post.permalink}">${post.title}</a></li>
											</c:forEach>
										</ul>
										<%-- <ul>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/team/teacher">TIM PENGAJAR</a></li>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/team/advisor">TIM ADVISOR</a></li>
										</ul> --%>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">KERJASAMA</a></li>
										</ul>
										<ul id="engagement-content">
											<c:forEach items="${engagementList}" var="engage">
											<li><a href="${ctx}/page/engagement/${engage.permalink}">${engage.title}</a></li>
											</c:forEach>
										</ul>
										<a href="${ctx}/page/engagement"><span class="button-blue">Selengkapnya...</span></a>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">PROGRAM HFC</a></li>
										</ul>
										<ul id="program-content">
											<c:forEach items="${programList}" var="program">
											<li><a href="${ctx}/page/program/${program.permalink}">${program.title}</a></li>
											</c:forEach>
										</ul>
										<a href="${ctx}/page/program"><span class="button-blue">Selengkapnya...</span></a>
									</li>
								  </ul>
								</li>
								<li id="publikasi" class="dropdown">
								  <a href="/#" class="parent-menu dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">PUBLIKASI <span class="caret"></span></a>
								  <ul class="dropdown-menu mega-dropdown-menu row publications-mega-menu">
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/ebook">E-book (Artikel Properti)</a></li>
										</ul>
										<ul id="digital-book-content" class="content">
											<c:forEach items="${digitalBookList}" var="ebook">
											<li>
												<div class='thumb-mini-img1'><img src='${ebook.encodedPicture}'/></div>
												<div class='menu-label1'>
													<a href='${ctx}/page/ebook/${ebook.permalink}'>${ebook.title}</a>
												</div>
												<div class='clear'></div>
											</li>
											</c:forEach>
										</ul>
										<a href="${ctx}/page/ebook"><span class="button-blue">Selengkapnya...</span></a>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">News</a></li>
										</ul>
										<ul id="news-content" class="content">
											<c:forEach items="${newsList}" var="news">
											<li>
												<div class='thumb-mini-img2 thumb-home'><img src='${news.encodedPicture}'/></div>
												<div class='menu-label2'>
													<a href='${ctx}/page/news/${news.permalink}'>${news.title}</a>
												</div>
												<div class='clear'></div>
											</li>
											</c:forEach>
										</ul>
										<a href="${ctx}/page/news"><span class="button-blue">Selengkapnya...</span></a>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="<%=request.getContextPath()%>/page/ebook">Program</a></li>
										</ul>
										<ul id="event-content" class="content">
											<c:forEach items="${eventList}" var="event">
											<li>
												<div class='thumb-mini-img2 thumb-hexagon'><img src='${event.encodedPicture}'/></div>
												<div class='menu-label2'>
													<a href='${ctx}/page/event/${event.permalink}'>${event.title}</a>
												</div>
												<div class='clear'></div>
											</li>
											</c:forEach>
										</ul>
										<a href="${ctx}/page/event/upcoming"><span class="button-blue">Selengkapnya...</span></a>
									</li>
								  </ul>
								</li>
								<li id="program" class="dropdown">
								  <a class="parent-menu" href="<%=request.getContextPath()%>/page/main-program" role="button" aria-haspopup="true" aria-expanded="false">LAYANAN</a>
								</li>
								<li id="galeri" class="dropdown">
								  <a class="parent-menu" href="<%=request.getContextPath()%>/page/galeri" role="button" aria-haspopup="true" aria-expanded="false">GALERI</a>
								</li>
								<li id="kontak-kami" ><a class="parent-menu" href="<%=request.getContextPath()%>/page/contact">KONTAK KAMI</a></li>
								<li class="btn-search">
									<img src="<%=request.getContextPath()%>/images/search.png" alt="" class="img-responsive main-search-icon">
									<img src="<%=request.getContextPath()%>/images/close.png" alt="" class="img-responsive main-search-close-icon" style="display:none">
								</li>
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
				<div class="main-search-wrapper" style="display:none">
					<form action="${ctx}/page/search" method="GET">
						<div class="row">
							<div class="col-md-10">
								<input type="text" name="s" class="form-control input-search" placeholder="Masukkan Kata Kunci" value="">
							</div>
							<div class="col-md-2">
								<input type="submit" class="button-blue" value="Search"/>
							</div>
						</div>
					</form>
				</div>
			  </div><!-- /.container -->
			</nav><!-- navbar -->
		</div>
		<div class="margin-top">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-xs-12"></div>
			</div>
		  </div>
		</div>
		<script type="text/javascript" th:inline="javascript">
		/*<![CDATA[*/
		$(function(){
			$(".main-search-icon").click(function(){
				$(".main-search-wrapper").show(300);
				$(".main-search-icon").hide();
				$('.main-search-close-icon').show();
		    });
			$(".main-search-close-icon").click(function(){
				$(".main-search-wrapper").hide(300);
				$(".main-search-icon").show();
				$('.main-search-close-icon').hide();
		    });
		});
		/*]]>*/
		</script>