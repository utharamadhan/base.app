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
		  				<span class="address">Menara Bank BTN, Jl. Gajah Mada No.1, Jakarta 10130</span><br/>
		  				<span class="telp">Telp. : (021) 6336789</span><br/>
		  				<span class="fax">Fax. : (021) 6336719</span><br/>
		  				<span class="email">Email Contact Center :<br/>btncontactcenter@btn.co.id</span>
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
		  </div>
		</div>
		<div class="footer">
		  <div class="container">
			<div class="row clearfix">
			  <div class="col-sm-5 col-xs-12">
				<p class="copyRight">© 2017 Copyright Housing Finance Center</p>
			  </div>
			</div>
		  </div>
		</div>
		<script>
		$(document).ready(function(){
			$.get( "/Site/page/content/getData", function( data ) {
				var cp = data.commonPostList;
				html = "";
				for(var i=0;i<cp.length;i++){
					html += "<li class='dropdown-header'><a href='/Site/page/post/"+cp[i].pkCommonPost+"/"+(cp[i].title).replace(/\s+/g, '-')+"'>"+cp[i].title.toUpperCase()+"</a></li>";	
				}
				$('#common-post-content').html(html);
				
				var e = data.engagementList;
				html = "";
				for(var i=0;i<e.length;i++){
					html += "<li><a href='/Site/page/engagement/"+e[i].permalink+"'>"+e[i].title+"</a></li>";
				}
				$('#engagement-content').html(html);
				
				var p = data.programList;
				html = "";
				for(var i=0;i<p.length;i++){
					html += "<li><a href='/Site/page/program/"+p[i].permalink+"'>"+p[i].title+"</a></li>";
				}
				$('#program-content').html(html);
				
				var db = data.digitalBookList;
				html = "";
				for(var i=0;i<db.length;i++){
					html += "<li><div class='thumb-mini-img1'><img src='"+db[i].encodedPicture+"'/></div><div class='menu-label1'><a href='/Site/page/ebook/"+db[i].permalink+"'>"+db[i].title+"</a></div><div class='clear'></div></li>";
				}
				$('#digital-book-content').html(html);
				
				var n = data.newsList;
				html = "";
				for(var i=0;i<n.length;i++){
					html += "<li><div class='thumb-mini-img2 thumb-home'><img src='"+n[i].encodedPicture+"'/></div><div class='menu-label2'><a href='/Site/page/news/"+n[i].permalink+"'>"+n[i].title+"</a></div><div class='clear'></div></li>";
				}
				$('#news-content').html(html);
				
				var e = data.eventList;
				html = "";
				for(var i=0;i<e.length;i++){
					html += "<li><div class='thumb-mini-img2 thumb-hexagon'><img src='"+e[i].encodedPicture+"'/></div><div class='menu-label2'><a href='/Site/page/event/"+e[i].permalink+"'>"+e[i].title+"</a></div><div class='clear'></div></li>";
				}
				$('#event-content').html(html);
				
				var lu = data.linkUrlList;
				html = "";
				for(var i=0;i<lu.length;i++){
					html += "<li><a href='"+lu[i].url+"'>"+lu[i].title+"</a></li>";
				}
				$('#link-url-content').html(html);
			});
		});
		</script>