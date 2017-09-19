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
		  			<ul class="type-none">
		  				<li><a href="<%=request.getContextPath()%>/page/${toc}">${tocTitle}</a></li>
		  				<li><a href="<%=request.getContextPath()%>/page/faq">Pertanyaan Yang Sering Ditanyakan</a></li>
		  				<li><a href="<%=request.getContextPath()%>/page/sitemap"">Peta Situs</a></li>
		  				<li><a href="<%=request.getContextPath()%>/page/contact/memberi-masukan">Memberi Masukan</a></li>
		  			</ul>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Situs Lain</h4>
		  			<ul id="link-url-content" class="type-none">
			  			<c:forEach items="${linkUrlList}" var="flu">
			  				<c:if test="${flu.isParent}">
								<li><a href="${ctx}/page/other-site/${flu.permalink}">${flu.title}</a></li>
							</c:if>
							<c:if test="${!flu.isParent}">
								<li><a href="${flu.url}">${flu.title}</a></li>
							</c:if>
						</c:forEach>
		  			</ul>
		  		</div>
		  		<div class="col-md-3">
		  			<h4>Ikuti Kami</h4>
		  			<div class="socialFollow">
		  				<c:forEach items="${socialMedia}" var="sm">
		  					<a href="${sm.value}"><i class="fa ${sm.attribute}"></i></a>
		  				</c:forEach>
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
		<c:out value="${footerScript}" escapeXml="false"/>