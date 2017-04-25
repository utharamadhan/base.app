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
		    width: 47%;
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
    		border-right: 46.8px solid #004fb6;
    		top: 0px;
		}
		.help-form-header:before{
			left: 100%;
    		border-left: 46.8px solid #004fb6;
    		top: 0px;
		}
		.help-form-body{
		    font-size: 18px;
		    padding: 5px;
		    background: #fcdb00;
		    margin: 0px;
		    width: 47%;
		    float: right;
		    position: relative;
		    left: 51px;
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
    		border-right: 46.8px solid #fcdb00;
    		top: 0px;
		}
		.help-form-body:before{
			left: 100%;
    		border-left: 46.8px solid #fcdb00;
    		top: 0px;
		}
		.help-form-body select{
			padding:12px 20px;
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
			clear:both;
			position:relative;
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
		
		.triangle_top{
		    -webkit-clip-path: polygon(50% 33%, 22% 100%, 78% 100%);
    		clip-path: polygon(50% 33%, 22% 100%, 78% 100%);
    		width: 100%;
    		position: absolute;
    		margin-top: -290px;
    		float: right;
    		background-color:#004fb6;
		}
		.triangle_bottom{
			-webkit-clip-path: polygon(50% 20%, 40% 0, 60% 0);
		    clip-path: polygon(50% 60%, 20.5% 0, 76% 0);
		    width: 100%;
		    position: absolute;
		    float: right;
		    background-color: #fcdb00;
		    margin-top: 22px;
		}
		.video_tujuan_program{
			position: absolute;
		    float: left;
		    width: 53%;
		    -webkit-clip-path: polygon(40% 0, 90% 0, 100% 50%, 90% 100%, 0 100%, 0 50%);
		    clip-path: polygon(40% 0, 90% 0, 100% 50%, 90% 100%, 0 100%, 0 50%);
		}
		.detail_tujuan_program{
			position: relative;
		    float: right;
		    width: 51%;
		    padding-left: 4%;
		    padding-right: 7%;
		    -webkit-clip-path: polygon(60% 0, 100% 50%, 100% 100%, 0 100%, 10.3% 50%, 0 0);
		    clip-path: polygon(60% 0, 100% 50%, 100% 100%, 0 100%, 10.3% 50%, 0 0);
		}
		
		.content_kerja_sama{
			position: absolute;
		    top: 11px;
		    float: left;
		    width: 47.3%;
		    padding-right: 4%;
		    -webkit-clip-path: polygon(100% 0, 90% 50%, 100% 100%, 40% 100%, 0 50%, 0 0);
		    clip-path: polygon(100% 0, 90% 50%, 100% 100%, 40% 100%, 0 50%, 0 0);
		}
		.detail_kerja_sama{
			position: relative;
		    top: 11px;
		    float: right;
		    width: 56.1%;
		    -webkit-clip-path: polygon(8.5% 0, 100% 0, 100% 50%, 60% 100%, 8.5% 100%, 0 50%);
		    clip-path: polygon(8.5% 0, 100% 0, 100% 50%, 60% 100%, 8.5% 100%, 0 50%);
		}
		.text-secondary{
			color:#fcdb00
		}
		.to-uppercase {
			text-transform: uppercase;
		}
		.aboutUsArea{
			padding:90px 0px 90px 0px;
			background-color:#f7f7f7;
			background: url('<%=request.getContextPath()%>/images/background-about.png');
		}
		.testimonialArea{
			padding:40px 0px;
			background-image: url('<%=request.getContextPath()%>/images/background-testimonial.jpg');
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
		
		/* News Hexagonal */
		#categories{
		  overflow:hidden;
		  width:95%;
		  margin:0 auto;
		}
		.clr:after{
		  content:"";
		  display:block;
		  clear:both;
		}
		#categories li{
		  position:relative;
		  list-style-type:none;
		  float:left;
		  overflow:hidden;
		  visibility:hidden;
		  transform: rotate(-60deg) skewY(30deg);
		}
		#categories li *{
		  position:absolute;
		  visibility:visible;
		}
		#categories li > div{
		  width:100%;
		  height:100%;
		  text-align:center;
		  color:#fff;
		  overflow:hidden;
		  transform: skewY(-30deg) rotate(60deg);  
			backface-visibility:hidden;
		  
		}
		
		/* HEX CONTENT */
		#categories li img{
		  left:-100%; right:-100%;
		  width: auto; height:100%;
		  margin:0 auto;   
		  max-width: inherit;
		}
		
		#categories div h1, #categories div p{
		  width:100%;
		  padding:0 5%;
		  background-color: rgba(252, 219, 0, 1);
		  transition: top .2s ease-out, bottom .2s ease-out, .2s padding .2s ease-out;
		}
		#categories li h1{
		  bottom:110%;
		  font-weight:600;
		  font-size:14px;
		  padding-top:100%;
		  padding-bottom:100%;
		  color:#004fb6;
		}
		#categories li h1:after{
			content:'';
		  display:block;
		  position:absolute;
		  bottom:-1px; left:45%;
		  width:10%;
		  text-align:center;
		  z-index:1;
		  border-bottom:2px solid #fff;
		}
		#categories li p{
			padding-top:50%;
			top:110%;
			padding-bottom:50%;
		}
		
		
		/* HOVER EFFECT  */
		
		#categories li div:hover h1 {
		  bottom:50%;
		  padding-bottom:10%;
		}
		
		#categories li div:hover p{
		  top:50%;
		  padding-top:10%;
		}
		@media (min-width:1201px) {
		  #categories li{
		    width:19.2%; /* = (100-4) / 5 */
		    padding-bottom: 22.17%; /* =  width /0.866 */
		  }
		  #categories li:nth-child(9n+6), #categories li:nth-child(9n+7), #categories li:nth-child(9n+8), #categories li:nth-child(9n+9) {
		    margin-top: -4.7%;
		    margin-bottom: -4.2%;
		    transform: translateX(50%) rotate(-60deg) skewY(30deg);
		  }
		  #categories li:nth-child(9n+6):last-child, #categories li:nth-child(9n+7):last-child, #categories li:nth-child(9n+8):last-child, #categories li:nth-child(9n+9):last-child{
		    margin-bottom:0;
		  }
		  #categories li:nth-child(9n+6){
		    margin-left:0.5%;
		    clear:left;
		  }
		   #categories li:nth-child(9n+10){
		    clear:left;
		  }
		  #categories li:nth-child(9n+2), #categories li:nth-child(9n+ 7)  {
		    margin-left:1%;
		    margin-right:1%;
		  }
		  #categories li:nth-child(9n+3), #categories li:nth-child(9n+4), #categories li:nth-child(9n+8)
		  {
		    margin-right:1%;
		  }
		}
		
		@media (max-width: 1200px) and (min-width:901px) {
		  #categories li{
		    width:24.25%; /* = (100-3) / 4 */
		    padding-bottom: 28.002%; /* =  width /0.866 */
		  }
		  #categories li:nth-child(7n+5), #categories li:nth-child(7n+6), #categories li:nth-child(7n+7) {
		    margin-top: -6.1%;
		    margin-bottom: -6.1%;
		    transform: translateX(50%) rotate(-60deg) skewY(30deg);
		  }
		  #categories li:nth-child(7n+5):last-child, #categories li:nth-child(7n+6):last-child, #categories li:nth-child(7n+7):last-child{
		    margin-bottom:0;
		  }
		  #categories li:nth-child(7n+2), #categories li:nth-child(7n+6)  {
		    margin-left:1%;
		    margin-right:1%;
		  }
		  #categories li:nth-child(7n+3){
		    margin-right:1%;
		  }
		  #categories li:nth-child(7n+8){
		    clear:left;
		  }
		  #categories li:nth-child(7n+5){
		    clear:left;
		    margin-left:0.5%;
		  }
		}
		
		
		@media (max-width: 900px) and (min-width:601px) {
		  #categories li{
		    width:32.666%; /* = (100-2) / 3 */
		    padding-bottom: 37.721%; /* =  width /0.866 */
		  }
		  #categories li:nth-child(5n+4), #categories li:nth-child(5n+5) {
		    margin-top: -8.622%;
		    margin-bottom: -8.622%;
		    transform: translateX(50%) rotate(-60deg) skewY(30deg);
		  }
		  #categories li:nth-child(5n+4):last-child, #categories li:nth-child(5n+5):last-child{
		    margin-bottom:0;
		  }
		  #categories li:nth-child(5n+4){
		    margin-right:1%;
		    margin-left:0.5%;
		  }
		  #categories li:nth-child(5n+2) {
		    margin-left:1%;
		    margin-right:1%;
		  }
		  #categories li:nth-child(5n+6) {
		    clear:left;
		  }
		  
		}
		@media (max-width: 600px) {
		  #categories li{
		    width:49.5%; /* = (100-1) / 2 */
		    padding-bottom: 57.16%; /* =  width /0.866 */
		  }
		  #categories li:nth-child(3n+3){
		    margin-top: -13.392%;
		    margin-bottom: -13.392%;
		    transform: translateX(50%) rotate(-60deg) skewY(30deg);
		  }
		  #categories li:nth-child(3n+3):last-child{
		    margin-bottom:0;
		  }
		  #categories li:nth-child(3n+3){
		    margin-left:0.5%;
		  }
		  #categories li:nth-child(3n+2) {
		    margin-left:1%;
		  }
		  #categories li:nth-child(3n+4) {
		    clear:left;
		  }
		}
		#fork{
		  position:fixed;
		  bottom:0;
		  right:0;
		  color:#000;
		  text-decoration:none;
		  border:1px solid #000;
		  padding:.5em .7em;
		  margin:1%;
		  transition: color .5s;
		  overflow:hidden;
		}
		#fork:before {
		  content: '';
		  position: absolute;
		  top: 0; left: 0;
		  width: 130%; height: 100%;
		  background: #000;
		  z-index: -1;
		  transform-origin:0 0 ;
		  transform:translateX(-100%) skewX(-45deg);
		  transition: transform .5s;
		}
		#fork:hover {
		  color: #fff;
		}
		#fork:hover:before {
		  transform: translateX(0) skewX(-45deg);
		}
		.hexagon_clipath{
			-webkit-clip-path: polygon(50% 0%, 100% 25%, 100% 75%, 50% 100%, 0% 75%, 0% 25%);
			clip-path: polygon(50% 0%, 100% 25%, 100% 75%, 50% 100%, 0% 75%, 0% 25%);
			width: 280px;
			height: 300px;
		}
		.hexagon_bg{
			background-size: 100% 100%;
		}
	</style>
		<div id="aboutArea" class="aboutArea">
		  <div class="container">
			<div class="row clearfix">
				<div class="about_inner">
					<div class="col-md-4">
						<div class="mini_hexagon4 hexa_position4"></div>
						<div class="hexagon_learning hexagon_clipath hexagon_bg" style="background-image: url(<%=request.getContextPath()%>/images/learning_.jpg)" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/learning'"></div>
						<img src="<%=request.getContextPath()%>/images/learning_text.png" class="learning_text" />
					</div>
					<div class="col-md-4">
						<div class="mini_hexagon1 hexa_position1"></div>
						<div class="mini_hexagon2 hexa_position2"></div>
						<div class="mini_hexagon3 hexa_position3"></div>
						<div class="hexagon_advisory hexagon_clipath hexagon_bg" style="background-image: url(<%=request.getContextPath()%>/images/advisory_.jpg)" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory'"></div>
						<img src="<%=request.getContextPath()%>/images/advisory_text.png" class="advisory_text" />
					</div>
					<div class="col-md-4">
						<div class="mini_hexagon5 hexa_position5"></div>
						<div class="hexagon_research hexagon_clipath hexagon_bg" style="background-image: url(<%=request.getContextPath()%>/images/rnd_.jpg)" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/research-development'"></div>
						<img src="<%=request.getContextPath()%>/images/rnd_text.png" class="rnd_text" />
						<div class="housing_index">
							<div class="breakingNews bn-red" id="bn1">
						    	<div class="bn-title" style="width: auto;"><h2 style="display: inline-block;">BTN Housing Index</h2><span></span></div>
						        <ul style="left: 208px;" id="btnHIContent"></ul>
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
							<select id="help-select"></select>
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
						<div class="grid triangle_top"></div>
					</div>
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
					<div class="row-grid">
						<div class="grid triangle_bottom"></div>
					</div>
				</div>
			</div>
		</div>
		<div style="clear:both"></div>
		<div class="newsArea">
			<ul id="categories" class="clr">
			  <c:forEach items="${newsLatest}" var="news" varStatus="loop" begin="0" end="9" step="1">
			  	<li>
				    <div>
				      <img src="${news.encodedPicture}" alt="" />
				      <h1>${news.title}</h1>
				      <p><a href="page/engagement/list" class="btn btn-third btn-medium" style="width:90%">selengkapnya</a></p>
				    </div>
				  </li>
			  </c:forEach>
			</ul>
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
									<h2 style="color: #ffffff; position:relative; text-align: center; display: block; padding-bottom: 20px;">Williamson</h2>
									<h4 style="color: #1f85e5; position:relative; text-align: center; display: block; padding-bottom: 20px;">CEO - PT. Property Makmur Sentosa</h4>
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
									<h2 style="color: #ffffff; position:relative; text-align: center; display: block; padding-bottom: 20px;">Kristiana</h2>
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
									<h2 style="color: #ffffff; position:relative; text-align: center; display: block; padding-bottom: 20px;">Steve Thomas</h2>
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
		<script>
		setNavbarActive('beranda');
		$(document).ready(function(){
			$('#help-select').change(function(){
				if($(this).val()=="CO"){
					location.href = "page/advisory/sub/consulting";
				}else if($(this).val()!=""){
					location.href = "page/contact/"+$(this).val();
				}
			});
			$('.arrowBottom').click(function(){
				scrollToBottom('aboutUsArea', 72);
			});
			
			$.get( "/Site/page/homeContent/getDataForHome", function( data ) {
				var h = data.categoryHelpList;
				var htmlHelp = "<option>-- Pilih Bantuan --</option>";
				for(var i=0;i<h.length;i++){
					htmlHelp += "<option code='"+h[i].code+"' value='"+h[i].name+"'>"+h[i].descr+"</option>";
				}
				$('#help-select').html(htmlHelp);
				
				var hi = data.housingIndexList;
				var html = "";
				for(var i=0;i<hi.length;i++){
					html += "<li><a href="+hi[i].url+" target='_blank'><span>"+hi[i].title+"</span></a></li>";	
				}
				$('#btnHIContent').html(html);
				$("#bn1").breakingNews({
					effect		:"slide-h",
					autoplay	:true,
					timer		:3000,
					color		:"red",
					border		:true
				});
				var t = data.testimonialList;
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
			});
		});
	</script>
 </body>
</html>
