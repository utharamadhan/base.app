<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
<html>
 <body>
 	<link href="<%=request.getContextPath()%>/css/home.css" rel="stylesheet" />
		<div id="aboutArea" class="aboutArea">
		  <div class="container">
		  	<img src="<%=request.getContextPath()%>/images/hfc-pcb.png" class="back-pcb"/>
			<div class="row clearfix">
				<div class="about_inner">
					<div class="col-md-4">
						<div class="hexagon_learning hexagon_clipath hexagon_bg hexagon_learning" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/mainProgram/learning'">
							<img src="<%=request.getContextPath()%>/images/learning_.jpg"/>
						</div>
						<label class="learning_text">Learning</label>
					</div>
					<div class="col-md-4">
						<div class="hexagon_advisory hexagon_clipath hexagon_bg hexagon_advisory" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory'">
							<img src="<%=request.getContextPath()%>/images/advisory_.jpg"/>
						</div>
						<label class="advisory_text">Advisory</label>
					</div>
					<div class="col-md-4">
						<div class="hexagon_research hexagon_clipath hexagon_bg hexagon_rnd" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/research-development'">
							<img src="<%=request.getContextPath()%>/images/rnd_.jpg"/>
						</div>
						<label class="rnd_text">Research<br/>&#38;<br/>Development</label>
						<div class="hexagon_hfc">
							<img src="<%=request.getContextPath()%>/images/icon-hfc.png"/>
						</div>
						<div class="hexagon_light_learning hll1"></div>
						<div class="hexagon_light_learning hll2"></div>
						<div class="hexagon_light_learning hll3"></div>
						<div class="hexagon_light_advisory hla1"></div>
						<div class="hexagon_light_advisory hla2"></div>
						<div class="hexagon_light_advisory hla3"></div>
						<div class="hexagon_light_rnd hlr1"></div>
						<div class="hexagon_light_rnd hlr2"></div>
						<div class="hexagon_light_rnd hlr3"></div>
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
		<div class="mainHomeArea">
			<div class="banner carousel slide" id="recommended-item-carousel" data-ride="carousel">
				<div class="banner-wrapper back-gradient-transparent">
					<div class="container">
						<div class="form-wrapper">
							<div class="banner-header">
								<h2 class="top-header">SEMUA INFORMASI PEMBIAYAAN PERUMAHAN ADA DISINI</h2>
								<h2 class="sub-header">Ikuti 4 Langkah Berikut Ini</h2>
							</div>
							<div style="clear:both"></div>
							<div class="row step-number">
								<div class="col-md-3"><label>1</label></div>
								<div class="col-md-3"><label>2</label></div>
								<div class="col-md-3"><label>3</label></div>
								<div class="col-md-3"><label>4</label></div>
							</div>
							<div class="row step-expert">
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<span>Akses Housing Finance Center</span>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<span>Browser Publikasi</span>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<span>Pilih Program Kami</span>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content help-form">
											<span>Butuh Bantuan? Hubungi Layanan Pelanggan</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row step-expert-line">
							</div>						
						</div>
					</div>
				</div>
			</div><!--end banner-->
				<div class="clear"></div>
		 		<div id="aboutUsArea" class="aboutUsArea back-hexa">
				<div class="container">
					<div class="row">
						<div class="col-md-10 col-md-offset-1">
							<div class="row circle-top">
								<div class="col-md-12">
									<div class="title-center">
										<h2 style="color: white;padding-top: 15%;">SIAPAKAH KAMI?</h2>
									</div>
								</div>
							</div>
							<div class="row" style="height:385px">
								<div class="col-md-8 main-content-left"><iframe width="100%" height="100%" src="https://www.youtube.com/embed/felFVPmAu5s" frameborder="0" allowfullscreen></iframe></div>
								<div class="col-md-4 main-content-right">
									<div class="panel-group" id="accordion">
								        <div class="panel panel-default">
								            <div class="panel-heading">
								                <h4 class="panel-title">
								                    <a data-toggle="collapse" data-parent="#accordion" href="#cHFC">Housing Finance Center</a>
								                </h4>
								            </div>
								            <div id="cHFC" class="panel-collapse collapse in">
								                <div class="panel-body">
								                    <p>Deskripsi Housing Finance Center <a href="#" target="_blank">Selengkapnya.</a></p>
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
								                    <p>Memberikan pelayanan unggul, inovatif dan terintegrasi dalam riset, edukasi dan konsultasi terkait pembiayaan perumahan bagi klien <a href="#" target="_blank">Selengkapnya.</a></p>
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
								                    <p>Memberikan pelayanan unggul, inovatif dan terintegrasi dalam riset, edukasi dan konsultasi terkait pembiayaan perumahan bagi klien <a href="#" target="_blank">Selengkapnya.</a></p>
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
								                    <p>Memberikan pelayanan unggul, inovatif dan terintegrasi dalam riset, edukasi dan konsultasi terkait pembiayaan perumahan bagi klien <a href="#" target="_blank">Selengkapnya.</a></p>
								                </div>
								            </div>
								        </div>
								    </div>
								</div>
							</div>
							<div class="row" style="position:relative">
								<div class="circle-bottom1">
									<div class="title-center">
										<h2 style="color: white;padding-top: 7%;font-size: 24px;">Land & Environment</h2>
									</div>
								</div>
								<div class="circle-bottom2">
									<div class="title-center">
										<h2 style="color: white;padding-top: 15%;font-size: 24px;">Capital</h2>
									</div>
								</div>
								<div class="circle-bottom3">
									<div class="title-center">
										<h2 style="color: white;padding-top: 7%;font-size: 24px;">Legal</h2>
									</div>
								</div>
								<div class="circle-bottom4">
									<div class="title-center">
										<h2 style="color: white;padding-top: 10%;font-size: 24px;">Skills</h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="newsArea back-gradient-transparent">
				<div class="title-center">
					<h2>BERITA TERKINI</h2>
				</div>
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
		</div>
		<div class="clear"></div>
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
			$('.arrowBottom').click(function(){
				scrollToBottom('aboutUsArea', 72);
			});
			
			$.get( "/Site/page/content/getDataForHome", function( data ) {
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
				
				var n = data.newsList;
				html = "";
				for(var i=0;i<n.length;i++){
					html += "<li>";
					html += "<div>";
					html += "<img src='"+n[i].encodedPicture+"' alt='' />";
					html += "<h1>"+n[i].title+"</h1>";
					html += "<p><a href='/Site/page/engagement/list' class='btn btn-third btn-medium' style='width:90%'>selengkapnya</a></p>";
					html += "</div>";
					html += "</li>";	
				}
				$('#categories').html(html);
				
				var t = data.testimonialList;
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
				});
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
