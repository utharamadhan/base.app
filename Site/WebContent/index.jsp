<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
<html>
 <body>
 	<link href="<%=request.getContextPath()%>/css/home.css" rel="stylesheet" />
		<div id="aboutArea" class="aboutArea">
			<div class="clearfix">
				<div class="about_inner">
					<div class="hexagon_hfc">
						<img src="<%=request.getContextPath()%>/images/icon-hfc.png"/>
					</div>
					<div class="light_hfc">
						<svg width="100%" height="100%" viewBox="0 0 1000 1000" preserveAspectRatio="none">
						    <defs>  
						        <linearGradient id="hfc-proyektor" x1="0%" y1="100%" x2="50%" y2="100%" >
						            <stop stop-opacity=".5" offset="5%" stop-color="#0056a1">
						                <animate attributeName="stop-color" values="#0056a1; #FFFFFF; #0056a1" dur="3s" repeatCount="indefinite"></animate>
						            </stop>
						            <stop stop-opacity=".5" offset="95%" stop-color="#FFFFFF">
						                <animate attributeName="stop-color" values="#FFFFFF; #0056a1; #FFFFFF" dur="3s" repeatCount="indefinite"></animate>
						            </stop>
						        </linearGradient>
						    </defs>
						    <g>
						      <path fill="url('#hfc-proyektor')" d="M889 610  L724 150 L650 31 L0 0 L0 1000 L490 940 L860 800 L860 750 L840 750 Z"></path>
						    </g>
						</svg>
					</div>
					<img src="<%=request.getContextPath()%>/images/pcb-learning.png" class="pcb-learning desktop-content"/>
					<img src="<%=request.getContextPath()%>/images/pcb-advisory.png" class="pcb-advisory desktop-content"/>
					<img src="<%=request.getContextPath()%>/images/pcb-rnd.png" class="pcb-rnd desktop-content"/>
					<img src="<%=request.getContextPath()%>/images/pcb-learning-small.png" class="pcb-learning mobile-content"/>
					<img src="<%=request.getContextPath()%>/images/pcb-advisory-small.png" class="pcb-advisory mobile-content"/>
					<img src="<%=request.getContextPath()%>/images/pcb-rnd-small.png" class="pcb-rnd mobile-content"/>
					<div class="hexagon_learning hexagon_bg_top hexagon_learning" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/main-program/learning/seminar'">
						<div class="hb-1">
							<div class="hb-2">
								<img src="<%=request.getContextPath()%>/images/learning_.jpg"/>
							</div>
						</div>
					</div>
					<label class="learning_text">Learning</label>
					<div class="hexagon_advisory hexagon_bg_top hexagon_advisory" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory/advisory1'">
						<div class="hb-1">
							<div class="hb-2">
								<img src="<%=request.getContextPath()%>/images/advisory_.jpg"/>
							</div>
						</div>
					</div>
					<label class="advisory_text">Advisory</label>
					<div class="hexagon_rnd hexagon_bg_top" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/main-program/research-development/research'">
						<div class="hb-1">
							<div class="hb-2">
								<img src="<%=request.getContextPath()%>/images/rnd_.jpg"/>
							</div>
						</div>
					</div>
					<label class="rnd_text">Research<br/>&#38;<br/>Development</label>
					<div class="housing_index">
						<div class="breakingNews bn-yellow" id="bn1">
					    	<div class="bn-title"><h2>BTN Housing Index</h2><span></span></div>
					        <ul id="btnHIContent"></ul>
					        <div class="bn-navi"><span></span><span></span></div>
					    </div>
					</div>
					</div>
				</div>
		</div>
		<div class="clear"></div>
		<div class="mainHomeArea">
			<div class="banner carousel slide" id="recommended-item-carousel" data-ride="carousel">
				<div class="banner-wrapper back-gradient-transparent">
					<div class="container">
						<div class="form-wrapper">
							<div class="banner-header">
								<h2 class="top-header">SEMUA INFORMASI PEMBIAYAAN PERUMAHAN ADA DISINI</h2>
								<h2 class="sub-header">Ikuti 4 Langkah Berikut Ini</h2>
							</div>
							<div class="clear"></div>
							<div class="row step-number">
								<div class="col-md-3"><label>1</label></div>
								<div class="col-md-3"><label>2</label></div>
								<div class="col-md-3"><label>3</label></div>
								<div class="col-md-3"><label>4</label></div>
							</div>
							<div class="row step-expert">
								<div class="col-md-3">
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<a href="page/contact"><span>Akses Website<br/>Housing Finance<br/>Center</span></a>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<a href="#"><span>Browser<br/>Publikasi<br/>Kami</span></a>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<a href="page/main-program"><span>Pilih<br/>Program<br/>Kami</span></a>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<a href="page/contact"><span>Butuh Bantuan?<br/>Hubungi Layanan<br/>Pelanggan</span></a>
										</div>
									</div>
								</div>
							</div>
							<div class="row step-expert-line">
								<div class="col-md-12"></div>
							</div>						
						</div>
					</div>
				</div>
			</div>
				<div class="clear"></div>
		 		<div id="aboutUsArea" class="aboutUsArea" data-parallax="scroll" data-image-src="/Site/images/background-home.png">
				<div class="container">
					<div class="row">
						<div class="col-md-10 col-md-offset-1">
							<div class="row circle-top">
								<div class="col-md-12">
									<div class="title-center">
										<h2>SIAPAKAH KAMI?</h2>
									</div>
								</div>
							</div>
							<div class="row middleContent">
								<div class="col-md-8 main-content-left"><iframe width="100%" height="100%" src="https://www.youtube.com/embed/felFVPmAu5s" frameborder="0" allowfullscreen></iframe></div>
								<div class="col-md-4 main-content-right">
									<div class="panel-group" id="accordion">
								        <div class="panel panel-default">
								            <div class="panel-heading">
								                <h4 class="panel-title">
								                    <a data-toggle="collapse" data-parent="#accordion" href="#cHFC">BTN HOUSING FINANCE CENTER</a>
								                </h4>
								            </div>
								            <div id="cHFC" class="panel-collapse collapse in">
								                <div class="panel-body">
								                    <p>Sebagai leader pembiayaan perumahan di Indonesia, BTN HFC menjadi motor penggerak dan pusat referensi property di Indonesia. Sesuai dengan tujuan didirikannya BTN HFC akan menjadi pengelola pusat pembelajaraan perbankan dan riset  perumahan yang profesional dan terkemuka di Indonesia. BTN HFC akan menjadi sumber inspirasi para pelaku bisnis di bidang pembiayaan perumahan. Menjawab kebutuhan bisnis pembiayaan perumahan apakah itu dari dunia perbankan ataupun para pelaku pembangunan perumahan, BTN HFC adalah tempatnya. Ini akan menjadi pusat menjawab kebutuhan masalah perumahan di Indonesia.</p>
								                </div>
								            </div>
								        </div>
								        <div class="panel panel-default">
								            <div class="panel-heading">
								                <h4 class="panel-title">
								                    <a data-toggle="collapse" data-parent="#accordion" href="#cTujuan">Tujuan</a>
								                </h4>
								            </div>
								            <div id="cTujuan" class="panel-collapse collapse">
								                <div class="panel-body">
								                    <p>Housing Finance Center BTN yang selaras dengan tujuan didirikannya, yakni :</p>
													<ul>
														<li>Pusat Pembelajaran perbankan dan riset perumahan yang profesional terkemuka di Indonesia,</li>
														<li>Menjadi sumber inspirasi para pelaku bisnis di bidang pembiayaan perumahan,</li>
														<li>Menjawab kebutuhan bisnis pembiayaan perumahan baik dunia perbankan maupun pengembang perumahan.</li>
													</ul>
								                </div>
								            </div>
								        </div>
								        <div class="panel panel-default">
								            <div class="panel-heading">
								                <h4 class="panel-title">
								                    <a data-toggle="collapse" data-parent="#accordion" href="#cProgram">Program</a>
								                </h4>
								            </div>
								            <div id="cProgram" class="panel-collapse collapse">
								                <div class="panel-body">
								                    <p>Program kerja di bidang  Learning :</p>
													<ol>
													<li>Foundation Property Developer : diperuntukkan bagi developer kecil atau pemula. Selain itu program ini dirancang bagi masyarakat umum yang memiliki passion untuk tumbuh menjadi pengembang.</li>
													<li>Professional  Developer : diperuntukkan bagi  pengembang skala menengah, yang telah membangun di beberapa titik lokasi sekaligus.</li>
													<li>Executive  Property Developer : Diperuntukkan bagi pengembang skala besar, yang berorientasi pada pembangunan suatu kawasan atau kota mandiri.</li>
													</ol>
								                </div>
								            </div>
								        </div>
								        <div class="panel panel-default">
								            <div class="panel-heading">
								                <h4 class="panel-title">
								                    <a data-toggle="collapse" data-parent="#accordion" href="#cKerjasama">Kerjasama</a>
								                </h4>
								            </div>
								            <div id="cKerjasama" class="panel-collapse collapse">
								                <div class="panel-body">
								                    <ol>
													<li>MoU BTN - HDFC India</li>
													<li>MoU BTN dengan Pengembang 21-22 April 2015</li>
													<li>MoU BTN - SBM ITB 24 Oktober 2015</li>
													<li>MoU BTN - IAEI 3 Maret 2016</li>
													<li>PKS BTN dengan UNDIP Semarang, 20 April 2017</li>
													<li>MoU BTN dengan Balai Pustaka, 2 Mei 2017</li>
													</ol>
								                </div>
								            </div>
								        </div>
								    </div>
								</div>
							</div>
							<div class="row circle-bottom-wrapper">
								<div class="circle-bottom1">
									<div class="title-center link-pilar">
										<h2>Land &#38; Environment</h2>
									</div>
								</div>
								<div class="circle-bottom2">
									<div class="title-center link-pilar">
										<h2>Capital</h2>
									</div>
								</div>
								<div class="circle-bottom3">
									<div class="title-center link-pilar">
										<h2>Legal</h2>
									</div>
								</div>
								<div class="circle-bottom4">
									<div class="title-center link-pilar">
										<h2>Skills</h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div class="newsArea back-gradient-transparent">
				<div class="title-center">
					<h2>BERITA TERKINI</h2>
				</div>
				<ul id="categories" class="latest-news clr">
				  <c:forEach items="${newsLatest}" var="news" varStatus="loop" begin="0" end="9" step="1">
				  	<li>
				    	<div>
				      		<img src="${news.encodedPicture}" alt="" />
				      		<h1>${news.title}</h1>
				      		<p><a href="page/engagement/list" class="btn btn-third btn-medium">selengkapnya</a></p>
				    	</div>
				  	</li>
				  </c:forEach>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
		<div class="testimonialWrapper" data-parallax="scroll" data-image-src="/Site/images/background-home.png">
		<div class="testimonialArea">
		  <div class="container">
			<div class="row">
				<div class="title-center">
					<h2>APA KATA MEREKA?</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<ul id="categories" class="clr">
					  <c:forEach var="i" begin="1" end="14">
					  	<li>
					    	<div>
					    		<img src="<%=request.getContextPath()%>/images/faces/${i}.jpg"/>
					    	</div>
					  	</li>
					  </c:forEach>
					</ul>
				</div>
			</div>
		  </div>
		</div>
		</div>
		<div class="contactArea">
			<div class="contactArea-overlay">
			<div class="container">
				<div class="contact-wrapper">
					<div class="contact-intro">
						<h2>HOUSING FINANCE CENTER</h2>
						<hr/>
						<ul>
							<li>Memberikan pelayanan unggul, inovatif dan terintegrasi yang bisa diandalkan</li>
							<li>Fokus pada bidang riset, konsultasi dan edukasi</li>
							<li>Menjadi pusat riset, edukasi dan konsultasi terkait pembiayaan perumahan yang terdepan dengan pelayanan <i>CEPAT</i></li>
						</ul>
					</div>
					<div class="contact-button">
						<a href="page/contact" class="btn btn-secondary btn-medium">Kontak Kami</a>
					</div>
				</div>
			</div>
			</div>
		</div>
		<script>
		setNavbarActive('beranda');
		$(document).ready(function(){
			$('.link-pilar').click(function(){
				location.href='/Site/page/pilar';
			});
			$('.arrowBottom').click(function(){
				scrollToBottom('aboutUsArea', 72);
			});
			
			$.get( "/Site/page/content/getDataForHome", function( data ) {
				var hi = data.housingIndexList;
				var html = "";
				for(var i=0;i<hi.length;i++){
					html += "<li><label>"+hi[i].title+" : </label>&nbsp;<span>"+hi[i].value+"</span></a></li>";	
				}
				$('#btnHIContent').html(html);
				$("#bn1").breakingNews({
					effect		:"slide-h",
					autoplay	:true,
					timer		:3000,
					color		:"yellow",
					border		:true
				});
				
				var n = data.newsList;
				html = "";
				for(var i=0;i<n.length;i++){
					html += "<li>";
					html += "<div>";
					html += "<img src='"+n[i].encodedPicture+"' alt='' />";
					html += "<h1>"+n[i].title+"</h1>";
					html += "<p><a href='/Site/page/engagement/list' class='btn btn-third btn-medium'>selengkapnya</a></p>";
					html += "</div>";
					html += "</li>";	
				}
				$('.latest-news').html(html);
				
				/* var t = data.testimonialList;
				html = "";
				for(var i=0;i<t.length;i++){
					html += '<div class="custom_testimony">';
					html += '<div class="sub_hexagon1 sub_hexagon1_post" style="display: inline-block;">';
					html += '<div style="position: absolute;width:100%;">';
					html += '<h2 style="color: #ffffff; position:relative; text-align: center; display: block; padding-bottom: 20px;">'+t[i].name+'</h2>';
					html += '<h4 style="color: #1f85e5; position:relative; text-align: center; display: block; padding-bottom: 20px;">'+t[i].title+'</h4>';
					html += '</div>';
					html += '</div>';
					html += '<div class="main_hexagon1" style="background-image: url('+t[i].photoURL.replace(/\\/g,"/")+');display: inline-block;">';
					html += '<div class="main_hexagon1_hexTop"></div>';
					html += '<div class="main_hexagon1_hexBottom"></div>';
					html += '</div>';
					html += '<div class="sub_hexagon2 sub_hexagon2_post" style="display: inline-block;">';
					html += '<div style="position: absolute;">';
					html += '<label style="padding-left: 12px; padding-right: 12px; font-size: 12px;">'+t[i].description+'</labe>';
					html += '</div>';
					html += '</div>';
					html += '</div>';
				}
				$('#testimonial-slider').html(html);
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
				}); */
			});
			
			var colors = new Array(
			  [255,255,255],
			  [209,215,223],
			  [255,255,255],
			  [244,236,184],
			  [255,255,255],
			  [240,226,131],
			  [255,255,255],
			  [250,222,30],
			  [255,255,255],
			  [253,237,133]);

			var step = 0;
			var colorIndices = [0,1,2,3];
			var gradientSpeed = 0.002;

			function updateGradient()
			{
			  
			  if ( $===undefined ) return;
			  
			var c0_0 = colors[colorIndices[0]];
			var c0_1 = colors[colorIndices[1]];
			var c1_0 = colors[colorIndices[2]];
			var c1_1 = colors[colorIndices[3]];

			var istep = 1 - step;
			var r1 = Math.round(istep * c0_0[0] + step * c0_1[0]);
			var g1 = Math.round(istep * c0_0[1] + step * c0_1[1]);
			var b1 = Math.round(istep * c0_0[2] + step * c0_1[2]);
			var color1 = "rgba("+r1+","+g1+","+b1+",0.9)";

			var r2 = Math.round(istep * c1_0[0] + step * c1_1[0]);
			var g2 = Math.round(istep * c1_0[1] + step * c1_1[1]);
			var b2 = Math.round(istep * c1_0[2] + step * c1_1[2]);
			var color2 = "rgba("+r2+","+g2+","+b2+",0.9)";

			 $('.banner-wrapper').css({
			   background: "-webkit-gradient(linear, left top, right top, from("+color1+"), to("+color2+"))"}).css({
			    background: "-moz-linear-gradient(left, "+color1+" 0%, "+color2+" 100%)"});
			  
			  step += gradientSpeed;
			  if ( step >= 1 )
			  {
			    step %= 1;
			    colorIndices[0] = colorIndices[1];
			    colorIndices[2] = colorIndices[3];
			    colorIndices[1] = ( colorIndices[1] + Math.floor( 1 + Math.random() * (colors.length - 1))) % colors.length;
			    colorIndices[3] = ( colorIndices[3] + Math.floor( 1 + Math.random() * (colors.length - 1))) % colors.length;
			  }
			}

			setInterval(updateGradient,10);
			
		});
	</script>
 </body>
</html>
