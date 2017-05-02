<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
<html>
 <body>
 	<link href="<%=request.getContextPath()%>/css/home.css" rel="stylesheet" />
		<div id="aboutArea" class="aboutArea">
		  <div class="container">
			<div class="row clearfix">
				<div class="about_inner">
					<div class="col-md-4">
						<div class="hexagon_learning hexagon_clipath hexagon_bg hexagon_learning" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/learning'">
							<img src="<%=request.getContextPath()%>/images/learning_.jpg"/>
						</div>
						<img src="<%=request.getContextPath()%>/images/learning_text.png" class="learning_text" />
					</div>
					<div class="col-md-4">
						<div class="hexagon_advisory hexagon_clipath hexagon_bg hexagon_advisory" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory'">
							<img src="<%=request.getContextPath()%>/images/advisory_.jpg"/>
						</div>
						<img src="<%=request.getContextPath()%>/images/advisory_text.png" class="advisory_text" />
					</div>
					<div class="col-md-4">
						<div class="hexagon_research hexagon_clipath hexagon_bg hexagon_rnd" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/research-development'">
							<img src="<%=request.getContextPath()%>/images/rnd_.jpg"/>
						</div>
						<img src="<%=request.getContextPath()%>/images/rnd_text.png" class="rnd_text" />
						<div class="hexagon_hfc">
							<img src="<%=request.getContextPath()%>/images/logo-hfc.png"/>
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
								<h2 class="top-header">Menjadi Ahli di Bidang Properti</h2>
								<h2 class="sub-header">hanya dengan 4 langkah di bawah ini</h2>
							</div>
							<div style="clear:both"></div>
							<div class="row step-expert">
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<span>Akses Housing Finance Center Website</span>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<span>Pilih Layanan Kami :</span>
											<ul>
												<li><a href="${ctx}/page/learning">Learning</a></li>
												<li><a href="${ctx}/page/research-development">Research & Development</a></li>
												<li><a href="${ctx}/page/advisory">Advisory</a></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content">
											<span>Pelajari Materi Berkualitas Kami</span>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="step-expert-border"></div>
									<div class="step-expert-icon-wrapper">
										<div class="step-expert-content help-form">
											<span>Butuh Bantuan?</span>
											<select id="help-select"></select>
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
		 		<div id="aboutUsArea" class="aboutUsArea">
				<div class="container">
					<div class="title-center">
						<h2>Siapakah Kami?</h2>
					</div>
					<div class="separator-container">
                        <div class="separator line-separator">&#x25A0;</div>
                    </div>
					<div class="grid-wrapper">
						<div class="row-grid">
							<div class="grid triangle_top"></div>
						</div>
						<div class="row-grid">
							<div class="grid video_tujuan_program">
								<iframe width="100%" height="100%" src="https://www.youtube.com/embed/felFVPmAu5s" frameborder="0" allowfullscreen></iframe>
							</div>
							<div class="grid grid-blue content_tujuan_program">
								<div class="grid-content">
									<h2 class="main-title text-secondary to-uppercase">Tujuan</h2>
									<p>Memberikan pelayanan unggul, inovatif dan terintegrasi dalam riset, edukasi dan konsultasi terkait pembiayaan perumahan bagi klien</p>
									<a href="page/post/1" class="btn btn-secondary btn-medium">selengkapnya</a>
								</div>
							</div>
						</div>
						<div class="row-grid">
							<div class="grid grid-yellow grid-align-right content_kerja_sama">
								<div class="grid-content">
									<h2 class="main-title text-secondary to-uppercase">Kerjasama</h2>
									<p>Untuk memberikan kualitas materi yang terbaik bagi anda, kami telah menjalin kerjasama dengan beberapa pihak</p>
									<a href="page/engagement/list" class="btn btn-third btn-medium">selengkapnya</a>
								</div>
							</div>
							<div class="grid grid-red content_program_hfc">
								<div class="grid-content">
									<h2 class="main-title text-secondary to-uppercase">Program</h2>
									<p>Memberikan pelayanan unggul, inovatif dan terintegrasi dalam riset, edukasi dan konsultasi terkait pembiayaan perumahan bagi klien</p>
									<a href="page/post/1" class="btn btn-secondary btn-medium">selengkapnya</a>
								</div>
							</div>
						</div>
						<div class="row-grid">
							<div class="grid triangle_bottom"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="newsArea back-gradient-transparent">
				<div class="title-center">
					<h2>Berita Terkini</h2>
				</div>
				<div class="separator-container">
                    <div class="separator line-separator">&#x25A0;</div>
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
					<h3 class="text-primary">Apa kata mereka tentang Housing Finance Center?</h3>
				</div>
				<div class="separator-container">
                    <div class="separator line-separator">&#x25A0;</div>
                </div>
			</div>
			<div class="row">
				<div class="col-md-offset-1 col-md-10">
					<div id="testimonial-slider" class="owl-carousel">
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
			
			$.get( "/Site/page/content/getDataForHome", function( data ) {
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
