<html>
 <head>
 </head>
 <body>
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
								  <img src="<%=request.getContextPath()%>/images/learning_.jpg" alt="" class="img-responsive" onclick="javascript:window.location.href='<%=request.getContextPath()%>/do/learning'">
								  <a href="<%=request.getContextPath()%>/do/learning" class="captionLink">Learning <span></span></a>
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
 </body>
</html>