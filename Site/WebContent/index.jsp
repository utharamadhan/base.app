<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
<html>
 <body>
 	<link href="<%=request.getContextPath()%>/css/home.css" rel="stylesheet"/>
		<div id="aboutArea" class="aboutArea">
		  <div class="container">
			<div class="row clearfix">
				<div class="about_inner">
					<div class="col-md-4">
						<div class="mini_hexagon4 hexa_position4"></div>
						<div class="hexagon_learning hexagon_clipath hexagon_bg hexagon_learning" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/learning'">
							<img src="<%=request.getContextPath()%>/images/learning_.jpg"/>
						</div>
						<img src="<%=request.getContextPath()%>/images/learning_text.png" class="learning_text" />
					</div>
					<div class="col-md-4">
						<div class="mini_hexagon1 hexa_position1"></div>
						<div class="mini_hexagon2 hexa_position2"></div>
						<div class="mini_hexagon3 hexa_position3"></div>
						<div class="hexagon_advisory hexagon_clipath hexagon_bg hexagon_advisory" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory'">
							<img src="<%=request.getContextPath()%>/images/advisory_.jpg"/>
						</div>
						<img src="<%=request.getContextPath()%>/images/advisory_text.png" class="advisory_text" />
					</div>
					<div class="col-md-4">
						<div class="mini_hexagon5 hexa_position5"></div>
						<div class="hexagon_research hexagon_clipath hexagon_bg hexagon_rnd" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/research-development'">
							<img src="<%=request.getContextPath()%>/images/rnd_.jpg"/>
						</div>
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
