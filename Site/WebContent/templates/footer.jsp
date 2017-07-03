<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
		<div class="bottom-left-yellow"></div>
		<div class="bottom-right-blue"></div>
		<div class="menuFooter">
		  <div class="container">
		  	<div class="row">
		  		<div class="col-md-1">
		  			<div class="logo-footer">
		  				<img src="/Site/images/logo-hfc.png" alt="" class="img-responsive logo-btn">
		  			</div>
		  		</div>
		  		<div class="col-md-2">
		  			<div class="info-hfc">
		  				<span class="title">Kantor Pusat :</span><br/>
		  				<span class="address">Menara BTN Lt. 18, Jl. Gajah Mada 1 Jakarta 10130</span><br/>
		  				<span class="telp">Telp. 021 - 6336789 ext. 1831</span><br/>
		  				<span class="title">Sekretariat :</span><br/>
		  				<span class="address">Jl. HOS Cokroaminoto 95, Menteng, Jakarta Pusat</span><br/>
		  				<span class="title">Email Contact Center :</span>
		  				<span class="email">HFC@btn.co.id</span>
		  			</div>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Tentang Website</h4>
		  			<ul>
		  				<li><a href="#">Syarat & Ketentuan</a></li>
		  				<li><a href="<%=request.getContextPath()%>/page/faq">FAQ</a></li>
		  				<li><a href="#">Peta Situs</a></li>
		  				<li><a href="<%=request.getContextPath()%>/page/contact/MM">Memberi Masukan</a></li>
		  			</ul>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Situs Lain</h4>
		  			<ul id="link-url-content">
			  			<c:forEach items="${linkUrlList}" var="flu">
							<li><a href="${flu.url}">${flu.title}</a></li>
						</c:forEach>
		  			</ul>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Ikuti Kami</h4>
		  			<div class="socialFollow">
			  			<a href="#"><i class="fa fa-twitter"></i></a>
						<a href="#"><i class="fa fa-facebook"></i></a>
						<a href="#"><i class="fa fa-instagram"></i></a>
						<a href="#"><i class="fa fa-google-plus"></i></a>
						<a href="#"><i class="fa fa-youtube-play"></i></a>
					</div>
		  		</div>
		  	</div>
		  </div>
		</div>
		<div class="footer">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-sm-5 col-xs-12">
				<p class="copyRight">&#169; 2017 Copyright Housing Finance Center</p>
			  </div>
			</div>
		  </div>
		</div>