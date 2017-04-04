<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
<html>
 <head>
 </head>
 <body>
 	<style>
		.margin-top{
			margin-top:0px;
		}
		.banner-wrapper{
			height: 275px;
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
			padding: 17px 20px;
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
	        position: relative;
    		right: 50px;	
		}
		.help-form-header:after,
		.help-form-header:before{
			content: "";
			width: 0;
		  position: absolute;
		  border-top: 29px solid transparent;
		  border-bottom: 29px solid transparent;
		}
		.help-form-header:after{
			right: 100%;
			border-right: 28.87px solid #004fb6;
			top: 0px;
		}
		.help-form-header:before{
			left: 100%;
			border-left: 28.87px solid #004fb6;
			top: 0px;
		}
		.help-form-body{
		    font-size: 18px;
		    padding: 5px;
		    background: #fcdb00;
		    margin: 0px;
		    width: 50%;
		    float: right;
		    position: relative;
    		left: 75px;
		}
		.help-form-body:after,
		.help-form-body:before{
			content: "";
			width: 0;
		  position: absolute;
		  border-top: 29px solid transparent;
		  border-bottom: 29px solid transparent;
		}
		
		.help-form-body:after{
			right: 100%;
			border-right: 28.87px solid #fcdb00;
			top: 0px;
		}
		.help-form-body:before{
			left: 100%;
			border-left: 28.87px solid #fcdb00;
			top: 0px;
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
			background-color:#fcdb00;
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
		.video_tujuan_program{
			position: relative;
			right: 25px;
		}
		.detail_tujuan_program{
			position: relative;
			left: 25px;
		}
		
		.content_kerja_sama{
			position: relative;
			right: 25px;
			top: 25px;
		}
		.detail_kerja_sama{
			position: relative;
			left: 25px;
			top: 25px;
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
			background-color:#ced0d6;
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
		.btn-third{
			color: #fcdb00;
			background-color: #004fb6;
			border-color: #004fb6;
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
						<div class="mini_hexagon4 hexa_position4"></div>
						<img src="<%=request.getContextPath()%>/images/hexagon_learning_big.png" alt="" class="img-responsive hexagon_learning" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/learning'">
					</div>
					<div class="col-md-4">
						<div class="mini_hexagon1 hexa_position1"></div>
						<div class="mini_hexagon2 hexa_position2"></div>
						<div class="mini_hexagon3 hexa_position3"></div>
						<img src="<%=request.getContextPath()%>/images/hexagon_advisory_big.png" alt="" class="img-responsive hexagon_advisory" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory'">
					</div>
					<div class="col-md-4">
						<div class="mini_hexagon5 hexa_position5"></div>
						<img src="<%=request.getContextPath()%>/images/hexagon_research_big.png" alt="" class="img-responsive hexagon_research" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/research-development'">
						<div class="housing_index">
							<div class="breakingNews bn-red" id="bn1">
						    	<div class="bn-title" style="width: auto;"><h2 style="display: inline-block;">Index</h2><span></span></div>
						        <ul style="left: 208px;">
						        	<li><a href="http://tevratgundogdu.com/works/breakingnewsticker/index.html#"><span>Albert Einstein</span> - Lorem Ipsum is simply dummy text of the printing and typesetting industry</a></li>
						            <li><a href="http://tevratgundogdu.com/works/breakingnewsticker/index.html#"><span>Nelson Mandela</span> - Lorem Ipsum has been the industry's standard dummy text ever since the</a></li>
						            <li><a href="http://tevratgundogdu.com/works/breakingnewsticker/index.html#"><span>Mohandas Karamçand Gandi</span> - There are many variations of passages of Lorem Ipsum available</a></li>
						            <li><a href="http://tevratgundogdu.com/works/breakingnewsticker/index.html#"><span>Atatürk</span> - Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia</a></li>
						            <li><a href="http://tevratgundogdu.com/works/breakingnewsticker/index.html#"><span>Napolyon</span> - The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested</a></li>
						        </ul>
						        <div class="bn-navi">
						        	<span></span>
						            <span></span>
						        </div>
						    </div>
						</div>
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
		</div><!--end banner-->
		<div style="clear:both"></div>
 		<div id="aboutUsArea" class="aboutUsArea">
			<div class="container">
				<div class="title-center">
					<h3 class="text-primary">Siapakah Kami?</h3>
				</div>
				<div class="grid-wrapper">
					<div class="row-grid">
						<div class="grid video_tujuan_program">
							<iframe width="100%" height="100%" src="https://www.youtube.com/embed/felFVPmAu5s" frameborder="0" allowfullscreen></iframe>
						</div>
						<div class="grid grid-blue detail_tujuan_program">
							<div class="grid-content">
								<h2 class="main-title text-secondary to-uppercase">Tujuan Program</h2>
								<p>Memberikan pelayanan unggul, inovatif dan terintegrasi dalam riset, edukasi dan konsultasi terkait pembiayaan perumahan bagi klien</p>
								<a href="page/post/1" class="btn btn-secondary btn-medium">selengkapnya</a>
							</div>
						</div>
					</div>
					<div class="row-grid">
						<div class="grid grid-white grid-align-right content_kerja_sama">
							<div class="grid-content">
								<h2 class="main-title text-secondary to-uppercase">Kerjasama</h2>
								<p>Untuk memberikan kualitas materi yang terbaik bagi anda, kami telah menjalin kerjasama dengan beberapa pihak</p>
								<a href="page/engagement/list" class="btn btn-third btn-medium">selengkapnya</a>
							</div>
						</div>
						<div class="grid detail_kerja_sama">
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
						<div class="custom_testimony">
							<div class="sub_hexagon1 sub_hexagon1_post" style="display: inline-block;">
								<div style="position: absolute;width:100%;">
									<h2 style="color: #004fb6; position:relative; text-align: center; display: block; padding-bottom: 20px;">Williamson</h2>
									<h4 style="color: #1f85e5; position:relative; text-align: center; display: block; padding-bottom: 20px;">CEO</h4>
									<p style="color: #b5c4ec; position:relative; text-align: center; display: block;">PT. Property Makmur Sentosa</p>
								</div>	
							</div>
							<div class="main_hexagon1" style="background-image: url(<%=request.getContextPath()%>/images/img-1.jpg);display: inline-block;">
							  <div class="main_hexagon1_hexTop"></div>
							  <div class="main_hexagon1_hexBottom"></div>
							</div>
							<div class="sub_hexagon2 sub_hexagon2_post" style="display: inline-block;">
								<div style="position: absolute;">
									<label style="padding-left: 2px; padding-right: 2px; font-size: 12px;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eu dolor eget ante pretium commodo. Cum sociis natoque penatibus ...</labe>
								</div>
							</div>
						</div>
		 
						<div class="custom_testimony">
							<div class="sub_hexagon1 sub_hexagon1_post" style="display: inline-block;">
								<div style="position: absolute;width:100%;">
									<h2 style="color: #004fb6; position:relative; text-align: center; display: block; padding-bottom: 20px;">Kristiana</h2>
									<h4 style="color: #1f85e5; position:relative; text-align: center; display: block; padding-bottom: 20px;">Owner</h4>
									<p style="color: #b5c4ec; position:relative; text-align: center; display: block;">Home Bright</p>
								</div>
							</div>
							<div class="main_hexagon1" style="background-image: url(<%=request.getContextPath()%>/images/img-2.jpg);display: inline-block;">
							  <div class="main_hexagon1_hexTop"></div>
							  <div class="main_hexagon1_hexBottom"></div>
							</div>
							<div class="sub_hexagon2 sub_hexagon2_post" style="display: inline-block;">
								<div style="position: absolute;">
									<label style="padding-left: 2px; padding-right: 2px; font-size: 12px;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eu dolor eget ante pretium commodo. Cum sociis natoque penatibus ...</labe>
								</div>
							</div>
						</div>
						<div class="custom_testimony">
							<div class="sub_hexagon1 sub_hexagon1_post" style="display: inline-block;">
								<div style="position: absolute;width:100%;">
									<h2 style="color: #004fb6; position:relative; text-align: center; display: block; padding-bottom: 20px;">Steve Thomas</h2>
									<h4 style="color: #1f85e5; position:relative; text-align: center; display: block; padding-bottom: 20px;">COO</h4>
									<p style="color: #b5c4ec; position:relative; text-align: center; display: block;">Factory Property</p>
								</div>
							</div>
							<div class="main_hexagon1" style="background-image: url(<%=request.getContextPath()%>/images/img-3.jpg);display: inline-block;">
							  <div class="main_hexagon1_hexTop"></div>
							  <div class="main_hexagon1_hexBottom"></div>
							</div>
							<div class="sub_hexagon2 sub_hexagon2_post" style="display: inline-block;">
								<div style="position: absolute;">
									<label style="padding-left: 2px; padding-right: 2px; font-size: 12px;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eu dolor eget ante pretium commodo. Cum sociis natoque penatibus ...</labe>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		  </div>
		</div>
		<div class="newsArea">
		  <div class="container">
			<div class="row">
				<c:forEach items="${newsLatest}" var="news" varStatus="loop" begin="1" end="4" step="1">
				<div class="col-md-3">
					<div class="news_hexa1" style="background-image: url(${news.encodedPicture});">
					  <div class="news_hexa1_hexTop"></div>
					  <div class="news_hexa1_hexBottom"></div>
					</div>
					<div class="news_title${loop.index} news_title_post">
						<div style="position: absolute;width: 100%; text-align: center;">
							${news.title}
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4 btn_see_more_hexa_wrapper"><div class="btn_see_more_hexa" onclick="javascript:window.location.href='${ctx}/page/news'">See More</div></div>
				<div class="col-md-4"></div>
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
			
			$("#bn1").breakingNews({
				effect		:"slide-h",
				autoplay	:true,
				timer		:3000,
				color		:"red",
				border		:true
			});
		});
	</script>
 </body>
</html>