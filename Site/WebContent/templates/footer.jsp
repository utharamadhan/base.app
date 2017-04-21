<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
		<div class="menuFooter">
		  <div class="container">
		  	<div class="row">
		  		<div class="col-md-3">
		  			<div class="logo-footer">
		  				<img src="/Site/images/logo-btn.png" alt="" class="img-responsive logo-btn">
		  			</div>
		  			<div>
		  				<span class="address">Menara Bank BTN, Jl. Gajah Mada No.1, Jakarta 10130</span><br/>
		  				<span class="telp">Telp. : (021) 6336789</span><br/>
		  				<span class="fax">Fax. : (021) 6336719</span><br/>
		  				<span class="email">Email Contact Center :<br/>btncontactcenter@btn.co.id</span>
		  			</div>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Tentang Website</h4>
		  			<ul>
		  				<li>Syarat & Ketentuan</li>
		  				<li>FAQ</li>
		  				<li>Peta Situs</li>
		  				<li>Memberi Masukan</li>
		  			</ul>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Partner Link</h4>
		  			<ul>
			  			<c:forEach items="${footerLinkUrl}" var="flu" varStatus="loop">
							<li><a href="${flu.url}">${flu.title}</a></li>
						</c:forEach>
		  			</ul>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Ikuti Kami</h4>
		  			<div class="socialFollow">
			  			<a href="#"><i class="fa fa-twitter"></i></a>
						<a href="#"><i class="fa fa-facebook"></i></a>
						<a href="#"><i class="fa fa-google-plus"></i></a>
						<a href="#"><i class="fa fa-youtube-play"></i></a>
					</div>
		  		</div>
		  	</div>
		  </div><!-- container -->
		</div><!-- menuFooter -->
		<div class="footer">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-sm-5 col-xs-12">
				<p class="copyRight">© 2017 Copyright Housing Finance Center</p>
			  </div><!-- col-sm-6 col-xs-12 -->
			</div><!-- row clearfix -->
		  </div><!-- container -->
		</div><!-- footer -->