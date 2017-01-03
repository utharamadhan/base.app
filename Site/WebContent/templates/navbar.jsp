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
							  <a class="navbar-brand logo clearfix" href="/index.html"><img src="<%=request.getContextPath()%>/images/logo-btn.png" alt="" class="img-responsive logo-btn"></a>
							  <a class="navbar-brand logo clearfix" href="/index.html"><img src="<%=request.getContextPath()%>/images/logo-hfc.png" alt="" class="img-responsive"></a>
							  <a class="navbar-brand logo clearfix border-left-yellow" href="/index.html"><img src="<%=request.getContextPath()%>/images/hfc-title.png" alt="" class="img-responsive"></a>
							</div>
							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse" id="bs-navbar-collapse-1">
							  <div class="navbar-brand navbar-right border-left-yellow" style="display:none">
								<div class="lang"> 
									<span class="en">
										<img src="content_files/en.png"/>
									</span> 
									<span class="id">
										<img src="content_files/id.png"/>
									</span>
								</div>
							  </div>
							  
								<%-- <div class="avatar-top-wrapper" data-toggle="dropdown">
									<img class="avatar border-white" src="<%=request.getContextPath()%>/images/faces/face-2.jpg" alt="Richard">
								</div>
								<ul class="dropdown-menu menu-user-avatar">
									<li>
										<div class="navbar-login">
											<div class="row">
												<div class="col-lg-4 no-right">
													<p class="text-center">
														<img src="img/faces/face-2.jpg" class="img-profile"/>
													</p>
												</div>
												<div class="col-lg-8">
													<p class="text-left"><strong>Richard</strong></p>
													<p class="text-left small">richard@gmail.com</p>
													<p class="text-left">
														<a href="#" class="btn btn-primary btn-full size-10">Profile</a>
													</p>
												</div>
											</div>
										</div>
									</li>
									<li><a href="#">Dashboard <i class="fa fa-tachometer pull-right" aria-hidden="true"></i></a></li>
									<li><a href="#">Notifications <span class="badge pull-right"> 42 </span></a></li>
									<li><a href="#">Account Settings <i class="fa fa-cog pull-right" aria-hidden="true"></i></a></li>
									<li><a href="#">Sign Out <i class="fa fa-sign-out pull-right" aria-hidden="true"></i></a></li>
								</ul> --%>
							  
							  <ul class="nav navbar-nav navbar-right">
								<li class="active"><a href="<%=request.getContextPath()%>/">Home</a></li>
								<li class="dropdown mega-dropdown">
								  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">About Us <span class="caret"></span></a>
								  <ul class="dropdown-menu mega-dropdown-menu row about-us-mega-menu">
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">Who We Are?</a></li>
											<li class="divider"></li>
											<li class="dropdown-header"><a href="#">Visi Misi & Nilai-Nilai</a></li>
											<li class="divider"></li>
											<li class="dropdown-header"><a href="#">Why HFC Should be your Choice?</a></li>
											<li class="divider"></li>
											<li class="dropdown-header"><a href="#">Layanan HFC</a></li>
											<li class="divider"></li>
											<li class="dropdown-header"><a href="#">Tim Pengajar</a></li>
										</ul>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">Our Engagement</a></li>
											<li><a href="#">Penandatanganan MoU BTN-HFC dengan HDFC</a></li>
											<li><a href="#">Penandatanganan MOU BTN dengan pengembang</a></li>
											<li><a href="#">Penandatanganan MOU BTN HFC dengan SBM-ITB</a></li>
											<li><a href="#">Penandatanganan MOU BTN HFC dengan IAEI</a></li>
											<li><a href="#">Penandatanganan MOU BTN HFC dengan lembaga/institusi lainnya</a></li>
											<li><div style="background-color:#49c32c;width:100px;float:right;text-align:center;color:white;padding:5px;font-size:14px;font-weight:bold">LAINNYA...</div></li>
										</ul>
									</li>
									<li class="col-sm-4">
										<ul>
											<li class="dropdown-header"><a href="#">Program HFC 2016</a></li>
											<li><a href="#">Penyelenggaraan workshop di 3 kota besar ...</a></li>
											<li><a href="#">Penyelenggaraan pendidikan pembiayaan perumahaan tingkat dasar & tingkat lanjut ...</a></li>
											<li><a href="#">Pelatihan Pembiayaan Perumahan bagi pengembang APERSI</a></li>
											<li><a href="#">Pelatihan Pembiayaan Perumahan bagi pengembang REI</a></li>
											<li><div style="background-color:#49c32c;width:100px;float:right;text-align:center;color:white;padding:5px;font-size:14px;font-weight:bold">LAINNYA...</div></li>
										</ul>
									</li>
								  </ul>
								</li>
								<li class="dropdown">
								  <a href="/#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Publications <span class="caret"></span></a>
								  <ul class="dropdown-menu mega-dropdown-menu row publications-mega-menu">
									<li class="col-sm-6">
										<ul>
											<li class="dropdown-header"><a href="#">Research Report</a></li>
											<li class="row">
												<div class="col-md-12">
													<div class="col-md-3">
														<div class="publication-year">2014</div>
													</div>
													<div class="col-md-3">
														<div class="publication-year">2015</div>
													</div>
													<div class="col-md-3">
														<div class="publication-year">2016</div>
													</div>
												</div>
											</li>
											<li><a href="#">Laporan Pembiayaan Properti 2015</a></li>
											<li><a href="#">Potret dan Proyeksi Perumahan 2015</a></li>
											<li><a href="#">Metodologi Analisis Potensi Sektoral Daerah</a></li>
											<li><a href="#">Metodologi Analisis Keuangan Daerah</a></li>
											<li><a href="#">Potensi Kebutuhan Rumah di Indonesia 2015-2016</a></li>
										</ul>
									</li>
									<li class="col-sm-6">
										<ul>
											<li class="dropdown-header"><a href="#">Digital Books</a></li>
											<li>
												<div class="col-md-6">
													<img src="<%=request.getContextPath()%>/content_files/digital-books1.jpg"/>
												</div>
												<div class="col-md-6">
													<img src="<%=request.getContextPath()%>/content_files/digital-books2.jpg"/>
												</div>
										</ul>
									</li>
								  </ul>
								</li>
								<li><a href="#">News</a></li>
								<li class="dropdown">
								  <a href="/index.html#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Events/Workshops <span class="caret"></span></a>
								  <ul class="dropdown-menu mega-dropdown-menu row events-mega-menu">
									<li class="col-sm-6">
										<ul>
											<li class="dropdown-header"><a href="/about.html">Archived</a></li>
										</ul>
									</li>
									<li class="col-sm-6">
										<ul>
											<li class="dropdown-header"><a href="#">Upcoming</a></li>
										</ul>
									</li>
								  </ul>
								</li>
								<li class="btn-signup" style="display:none"><a href="#" data-toggle="modal" data-target="#sign-in-modal">Sign In</a></li>
							  </ul>
							</div><!-- /.navbar-collapse -->
							<!-- Modal -->
							<div class="modal fade" id="sign-in-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							  <div class="modal-dialog" role="document">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title signin-button">Sign In</h4>
									<h4 class="modal-title reg-button" style="display:none">Sign Up</h4>
								  </div>
								  <div class="modal-body">
									<form method="POST" id="signin_sigup_form">
										<label class="warning-sign-first" style="display:none">Please sign first to enroll this program</label>
										<label id="error-sign" style="display:none"></label>
									  <div class="form-group">
										  <div class="input-group">
											<span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
											<input id="login-email" type="text" class="form-control" name="username" value="" placeholder="Email"/>
										  </div>
									  </div>
									  <div class="form-group">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-key" aria-hidden="true"></i></span>
											<input id="login-password" type="password" class="form-control" name="password" value="" placeholder="Password"/>
										</div>
									  </div>
									  <div class="form-group reg-content" style="display:none">
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-key" aria-hidden="true"></i></span>
											<input id="login-password" type="password" class="form-control" name="confirm_password" value="" placeholder="Confirm Password"/>
										</div>
									  </div>
									  <div class="col-lg-12 signin-content">
										  <div class="form-group">
											<div>
											  <a class="pull-right forget-password" href="#modal-forgot-password">
												Forgot Password
											  </a>
											  <label for="user-remember">
												<input type="checkbox" id="user-remember" ng-model="remember" class="ng-pristine ng-valid">
												Remember me
											  </label>
											</div>
										  </div>
									  </div>
									  <div class="col-lg-12">
											<p class="text-center registration-wrapper signin-content">
												Don't have a HFC account? <a class="no-account" href="#"><b>Create a free account</b></a>
											</p>
											<p class="text-center registration-wrapper reg-content" style="display:none">
												By signing up to create an account I accept HFC's<br/><a href="#" target="_blank"><b>Terms of Use</b></a> and <a href="#"><b>Privacy Policy</b></a>
											</p>
										</div>
									</form>
									<div style="clear:both"></div>
								  </div>
								  <div class="modal-footer">
									<div class="signin-button">
										<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
										<button type="button" class="btn btn-primary do-signin">Sign In</button>
									</div>
									<div class="reg-button" style="display:none">
										<button type="button" class="btn btn-default signin-button-back">Sign In</button>
										<button type="button" class="btn btn-primary do-signup">Sign Up</button>
									</div>
								  </div>
								</div>
							  </div>
							</div>							
							<div class="modal fade" id="aos-modal" tabindex="-1" role="dialog" aria-labelledby="aosLabel">
							  <div class="modal-dialog" role="document">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title">Area of Studies</h4>
								  </div>
								  <div class="modal-body">
									<form>
										<div class="col-md-6">
											<div class="checkbox">
											  <label><input type="checkbox" class="aos-search" value="property">Property</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="checkbox">
											  <label><input type="checkbox" class="aos-search" value="developer">Developer</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="checkbox">
											  <label><input type="checkbox" class="aos-search" value="hotel">Hotel</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="checkbox">
											  <label><input type="checkbox" class="aos-search" value="career">Career</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="checkbox">
											  <label><input type="checkbox" class="aos-search" value="industry">Industry</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="checkbox">
											  <label><input type="checkbox" class="aos-search" value="environment">Environment</label>
											</div>
										</div>
									</form>
									<div style="clear:both"></div>
								  </div>
								  <div class="modal-footer">
									<div>
										<button type="button" class="btn btn-default do-reset-aos">Reset</button>
										<button type="button" class="btn btn-primary do-search-aos" data-dismiss="modal">Search</button>
									</div>
								  </div>
								</div>
							  </div>
							</div>
							<div class="modal fade" id="enroll-modal" tabindex="-1" role="dialog" aria-labelledby="enrollLabel">
							  <div class="modal-dialog" role="document">
								<div class="modal-content">
								  <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title">Building the Frame for Business Growth</h4>
								  </div>
								  <div class="modal-body">
									<form>
										<div class="col-md-12">
											<div class="modal-choosing">
											  <ul>
											  <li>
												<input type="radio" id="p-option" name="selector">
												<label for="p-option">Purchase Course &nbsp;&nbsp;&#183;&nbsp;&nbsp; <span class="nominal-fee">Rp. 5.000.000,00</span></label>
												<div class="option-desc">
													<div>
														<img src="img/certificate.png" class="desc-certificate"/>
													</div>
													Commit to earning a Certificate-it's a trusted, shareable way to showcase your new skills.
												</div>
												<div class="check"></div>
											  </li>
											  <li>
												<input type="radio" id="f-option" name="selector">
												<label for="f-option">Free Course, No Certificate</label>
												<div class="option-desc">You will still have access to all course materials for this course.</div>
												<div class="check"><div class="inside"></div></div>
											  </li>
											</ul>
											</div>
										</div>
									</form>
									<div style="clear:both"></div>
								  </div>
								  <div class="modal-footer">
									<div>
										<button type="button" class="btn btn-default do-wishlist" data-dismiss="modal">Wishlist this program</button>
										<button type="button" class="btn btn-primary do-enroll" data-dismiss="modal">Enroll this program</button>
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
		<div class="inlineMenu margin-top">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-xs-12">
			  </div><!-- col-xs-12 -->
			</div><!-- row clearfix-->
		  </div><!-- container  -->
		</div><!-- inlineMenu -->