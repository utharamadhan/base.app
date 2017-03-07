<html>
 <head>
 </head>
 <body>
 	<style>
		.margin-top{
			margin-top:0px;
		}
		.banner-wrapper{
			background-image:url('<%=request.getContextPath()%>/images/background-hfc.jpg');
			background-size: 100% 100%;
			background-repeat: no-repeat;
			background-position: left top;
			height: 330px;
		}
		.banner-wrapper .container{
			padding: 35px 20px 95px;
		}
		.banner-wrapper .form-wrapper {
			max-width: 750px;
			text-align:center;
			margin-left:auto;
			margin-right:auto;
		}
		.banner-wrapper .form-wrapper .banner-header{
			padding-bottom: 25px;
		    background-color: rgba(255,255,255,0.7);
		    border-radius: 25px;
		    padding-top: 25px;
		}
		.banner-header .top-header{
			font-size:34px;
			color:#004fb6;
		}
		.banner-header .sub-header{
			margin-top:10px;
			color:#004fb6;
		}
		.help-form-header{
			padding: 15px 20px;
		    background: #004fb6;
		    color: #fff;
		    font-size: 18px;
		    display: -webkit-box;
		    display: -ms-flexbox;
		    -webkit-box-align: center;
		    -ms-flex-align: center;
		    align-items: center;
		    -webkit-transition: opacity 150ms linear;
		    transition: opacity 150ms linear;
		    float: left;
		    width: 50%;
		    border-bottom-left-radius: 25px;
		    border-top-left-radius: 25px;			
		}
		.help-form-body{
		    font-size: 18px;
		    padding: 5px;
		    background: #fcdb00;
		    margin: 0px;
		    border-top-right-radius: 25px;
		    border-bottom-right-radius: 25px;
		    width: 50%;
		    float: right;
		}
		.help-form-body select{
			padding:10px 20px;
			border-radius: 25px;
			border: solid 1px #fcdb00;
			width:100%;
		}
		.testimonial{
			text-align: center;
			padding: 100px 40px 5px;
			margin-top: 100px;
			border-top: 5px solid #004fb6;
			position: relative;
		}
		.testimonial:before{
			content: "\f10d";
			font-family: fontawesome;
			position: absolute;
			top: 20px;
			left: 20px;
			font-size: 50px;
			color: #004fb6;
			opacity: 0.5;
		}
		.testimonial .pic{
			width: 150px;
			height: 150px;
			border: 5px solid #004fb6;
			position: absolute;
			top: -80px;
			left: 0;
			right: 0;
			margin: 0 auto;
		}
		.testimonial .pic img{
			width: 100%;
			height: auto;
		}
		.testimonial .description{
			font-size: 14px;
			color: #6f6f6f;
			line-height: 25px;
			margin-bottom: 30px;
		}
		.testimonial .testimonial-title{
			display: block;
			text-align: right;
			font-size: 22px;
			color: #004fb6;
			margin: 0;
		}
		.testimonial .position{
			display: block;
			font-size: 12px;
			color: #6f6f6f;
			margin-top: 7px;
		}
		.owl-theme .owl-controls .owl-page span{
			width: 20px;
			height: 3px;
			background: #004fb6;
		}
		@media only screen and (max-width: 479px){
			.testimonial{ padding: 80px 15px 5px; }
			.testimonial::before{ top: 10px; }
			.testimonial .pic{
				width: 120px;
				height: 120px;
				top: -60px;
			}
		}
		.title-center{
			text-align:center;
		}
		.grid-wrapper{
			margin:30px 3%;
		}
		.grid-wrapper img{
			width:100%;
		}
		.row-grid{
			width:100%;
		}
		.grid{
			width:50%;
			height:279px;
			float:left;
		}
		.grid-content{
			padding: 40px;
		}
		.grid-content .main-title{
			margin-bottom: 1.5rem;
		}
		.grid-content p{
			text-align: initial!important;
		}
		.grid-blue{
			background-color: #004fb6;
			color: #fefefe;
		}
		.grid-white{
			background-color:#ffffff;
		}
		.grid-white .main-title{
			color:#004fb6;
		}
		.grid-align-right .grid-content{
			text-align:right;
		}
		.grid-align-right .grid-content p{
			text-align:right!important;
		}
		.text-secondary{
			color:#fcdb00
		}
		.to-uppercase {
			text-transform: uppercase;
		}
		.aboutUsArea{
			padding:50px 0px;
			background-color:#f7f7f7;
		}
		.testimonialArea{
			padding:40px 0px;
			background-color:#f5f3f3;
		}
		.contactArea{
			background-image: url('<%=request.getContextPath()%>/images/background-contact.jpg');
			background-size: auto 100%;
			background-repeat: no-repeat;
			background-position: left top;
			height: 550px;
		}
		.contactArea .contact-wrapper{
			max-width: 600px;
			padding-top: 40px;
		}
		.contactArea .contact-wrapper .contact-intro span{
			font-size: 22px;
			font-weight: 700;
			color: #004fb6;
			line-height: 2;
		}
		.contactArea .contact-button{
			padding-top: 15px;
		}
		.btn-secondary{
			color: #004fb6;
			background-color: #fcdb00;
			border-color: #fcdb00;
			text-transform: uppercase;
			font-weight:700;
		}
		.btn-medium{
		    padding: 10px 25px;
			border-radius: 25px;
		}
		.arrowBottomWrapper{
			position:absolute;
			left: 0; 
  			right: 0; 
  			margin-left: auto; 
  			margin-right: auto; 
		    width: 70px;
		}
		.arrowBottom{
			width: 70px;
		    height: 70px;
		    margin-top: -190px;
		    cursor: pointer;
		    border-radius: 50px;
		    font-size: 40px;
		    text-align: center;
		    padding-top: 15px;
		    clear: both;
		    color: rgba(0, 79, 182, 1);
		    background-color: rgba(167, 205, 255, 0.3);
		    transition: all 0.2s ease;
		    -webkit-animation: arrowmove 1s infinite; /* Safari 4.0 - 8.0 */
    		animation: arrowmove 1s infinite;
    	}
    	.arrowBottom:hover{
    		background-color: rgba(167, 205, 255, 0.8);
    	}
    	/* Safari 4.0 - 8.0 */
		@-webkit-keyframes arrowmove {
		    0%   {margin-top: -100px;}
		    100% {margin-top: -50px;}
		}
		
		/* Standard syntax */
		@keyframes arrowmove {
		    0%   {margin-top: -100px;}
		    100% {margin-top: -50px;}
		}
	</style>
		<div id="aboutArea" class="aboutArea">
		  <div class="container">
			<div class="row clearfix">
				<div class="about_inner">
					<div class="col-md-4">
							<div class="aboutImage">
							  <img src="<%=request.getContextPath()%>/images/learning_.jpg" alt="" class="img-responsive" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/learning'">
							  <a href="<%=request.getContextPath()%>/page/learning" class="captionLink">Learning <span></span></a>
							</div><!-- aboutImage -->
					</div>
					<div class="col-md-4">
							<div class="aboutImage">
							  <img src="<%=request.getContextPath()%>/images/advisory_.jpg" alt="" class="img-responsive" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory'">
							  <a href="<%=request.getContextPath()%>/page/advisory" class="captionLink">Advisory <span></span></a>
							</div><!-- aboutImage -->
					</div>
					<div class="col-md-4">
							<div class="aboutImage">
							  <img src="<%=request.getContextPath()%>/images/rnd_.jpg" alt="" class="img-responsive" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/research-development'">
							  <a href="<%=request.getContextPath()%>/page/research-development" class="captionLink">Research & Development <span></span></a>
							</div><!-- aboutImage -->
					</div>
				</div>
			</div><!-- row clearfix -->
		  </div><!-- container -->
		</div><!-- aboutArea -->
		<div style="clear:both"></div>
		<div class="banner carousel slide" id="recommended-item-carousel" data-ride="carousel">
			<div class="banner-wrapper">
				<div class="container">
					<div class="form-wrapper">
						<div class="banner-header">
							<h2 class="top-header">Being Expert in Property</h2>
							<h2 class="sub-header">Now available in your hand</h2>
						</div>
						<div style="clear:both"></div>
						<div class="help-form-header">
							Apa yang bisa kami bantu?
						</div>
						<div class="help-form-body">
							<select id="help-select">
								<option>-- Pilih Bantuan --</option>
								<option value="CC">Hubungi Call Center</option>
								<option value="PR">Ingin Mendaftar Program</option>
								<option value="CO">Ingin Berkonsultasi</option>
							</select>
						</div>						
					</div>
				</div>
			</div>
			<div class="arrowBottomWrapper">
				<div class="arrowBottom"><i class="fa fa-chevron-down" aria-hidden="true"></i></div>
			</div>
		</div><!--end banner-->
		<div style="clear:both"></div>
 		<div id="aboutUsArea" class="aboutUsArea">
			<div class="container">
				<div class="title-center">
					<h3 class="text-primary">Siapakah Kami?</h3>
				</div>
				<div class="grid-wrapper">
					<div class="row-grid">
						<div class="grid">
							<iframe width="100%" height="100%" src="https://www.youtube.com/embed/felFVPmAu5s" frameborder="0" allowfullscreen></iframe>
						</div>
						<div class="grid grid-blue">
							<div class="grid-content">
								<h2 class="main-title text-secondary to-uppercase">Tujuan Program</h2>
								<p>Memberikan pelayanan unggul, inovatif dan terintegrasi dalam riset, edukasi dan konsultasi terkait pembiayaan perumahan bagi klien</p>
								<a href="page/post/1" class="btn btn-secondary btn-medium">selengkapnya</a>
							</div>
						</div>
					</div>
					<div class="row-grid">
						<div class="grid grid-white grid-align-right">
							<div class="grid-content">
								<h2 class="main-title text-secondary to-uppercase">Kerjasama</h2>
								<p>Untuk memberikan kualitas materi yang terbaik bagi anda, kami telah menjalin kerjasama dengan beberapa pihak</p>
								<a href="page/engagement/list" class="btn btn-secondary btn-medium">selengkapnya</a>
							</div>
						</div>
						<div class="grid">
							<img src="<%=request.getContextPath()%>/images/handshaking.jpg"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="clear:both"></div>
		<div class="testimonialArea">
		  <div class="container">
			<div class="row">
				<div class="title-center">
					<h3 class="text-primary">Apa kata mereka tentang Housing Finance Center?</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-offset-1 col-md-10">
					<div id="testimonial-slider" class="owl-carousel">
						<div class="testimonial">
							<div class="pic">
								<img src="<%=request.getContextPath()%>/images/img-1.jpg" alt="">
							</div>
							<p class="description">
								Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eu dolor eget ante pretium commodo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean purus sem, aliquam eget lorem at, efficitur mattis risus. Morbi efficitur molestie cursus. Etiam eget sodales lorem. Proin volutpat lectus at pulvinar consectetur. Aliquam erat volutpat. Sed.
							</p>
							<h3 class="testimonial-title">
								Williamson
								<small class="position">CEO - PT. Property Makmur Sentosa</small>
							</h3>
						</div>
		 
						<div class="testimonial">
							<div class="pic">
								<img src="<%=request.getContextPath()%>/images/img-2.jpg" alt="">
							</div>
							<p class="description">
								Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eu dolor eget ante pretium commodo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean purus sem, aliquam eget lorem at, efficitur mattis risus. Morbi efficitur molestie cursus. Etiam eget sodales lorem. Proin volutpat lectus at pulvinar consectetur. Aliquam erat volutpat. Sed.
							</p>
							<h3 class="testimonial-title">
								Kristiana
								<small class="position">Owner - Home Bright</small>
							</h3>
						</div>
						<div class="testimonial">
							<div class="pic">
								<img src="<%=request.getContextPath()%>/images/img-3.jpg" alt="">
							</div>
							<p class="description">
								Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eu dolor eget ante pretium commodo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean purus sem, aliquam eget lorem at, efficitur mattis risus. Morbi efficitur molestie cursus. Etiam eget sodales lorem. Proin volutpat lectus at pulvinar consectetur. Aliquam erat volutpat. Sed.
							</p>
							<h3 class="testimonial-title">
								Steve Thomas
								<small class="position">COO - Factory Property</small>
							</h3>
						</div>
					</div>
				</div>
			</div>
		  </div>
		</div>
		<div class="contactArea">
			<div class="container">
				<div class="contact-wrapper">
					<div class="contact-intro">
						<span>Lalu tunggu apa lagi?<br/>Jadilah bagian dalam perubahan dunia property</span>
					</div>
					<div class="contact-button">
						<a href="page/contact" class="btn btn-secondary btn-medium">Kontak Kami</a>
					</div>
				</div>
			</div>
		</div>
		<div class="socMedFooter">
			<div class="container">
				<div class="socialFollow">
					<span class="labelSocialFollow">Follow Us</span>
					<a href="#"><i class="fa fa-twitter"></i></a>
					<a href="#"><i class="fa fa-facebook"></i></a>
					<a href="#"><i class="fa fa-google-plus"></i></a>
					<a href="#"><i class="fa fa-youtube-play"></i></a>
				</div>
			</div>
		</div>
		<script>
		setNavbarActive('beranda');
		$(document).ready(function(){
			$("#testimonial-slider").owlCarousel({
				items:1,
				itemsDesktop:[1000,1],
				itemsDesktopSmall:[979,1],
				itemsTablet:[768,1],
				pagination:true,
				navigation:false,
				navigationText:["",""],
				slideSpeed:1000,
				singleItem:true,
				autoPlay:true
			});
			$('#help-select').change(function(){
				if($(this).val()=="CO"){
					location.href = "page/advisory/sub/consulting";
				}else if($(this).val()!=""){
					location.href = "page/contact?type="+$(this).val();
				}
			});
			$('.arrowBottom').click(function(){
				scrollToBottom('aboutUsArea', 72);
			});
		});
	</script>
 </body>
</html>