<html>
 <head>
 </head>
 <body>
 	<style>
		.margin-top{
			margin-top:0px;
		}
		.banner-wrapper{
			background-image:url('<%=request.getContextPath()%>/images/background-hfc.jpg');
			background-size: auto 100%;
			background-repeat: no-repeat;
			background-position: left top;
			height: 768px;
		}
		.banner-wrapper .container{
			padding: 175px 20px 95px;
		}
		.banner-wrapper .form-wrapper {
			max-width: 420px;
			text-align:center;
		}
		.banner-header .top-header{
			font-size:34px;
			color:#0056a1;
			background-color: rgba(255,255,255,0.8);
		}
		.banner-header .sub-header{
			margin-top:10px;
			color:#0056a1;
			background-color: rgba(255,255,255,0.8);
		}
		.help-form-header{
			margin-top:30px;
			padding: 15px 20px;
			background: #0056a1;
			color: #fff;
			width: 100%;
			font-size: 18px;
			display: -webkit-box;
			display: -ms-flexbox;
			display: flex;
			-webkit-box-align: center;
			-ms-flex-align: center;
			align-items: center;
			-webkit-transition: opacity 150ms linear;
			transition: opacity 150ms linear;
			border-top-left-radius: 4px;
			border-top-right-radius: 4px;			
		}
		.help-form-body{
			font-size: 18px;
			padding: 20px;
			background: #0962b1;
			position: relative;
			margin: 0;
			max-width: 100%;
			border-top-left-radius: 0;
			border-top-right-radius: 0;
		}
		.help-form-body select{
			padding:10px 20px;
			border-radius: 4px;
			border: solid 1px #c1c1ba;
			width:100%;
		}
		.intro-3-pilars{
			text-align: center;
			font-size: 18px;
			margin-bottom: 20px;
			color: #0056a1;
			background-color: rgba(255,255,255,0.9);
			line-height: 1.8;
			padding: 20px 0px;
			border-bottom-left-radius: 20px;
			border-bottom-right-radius: 20px;
			font-weight: 700;
			border-bottom: 2px solid #f4202f;
		}
	</style>
		<div class="banner carousel slide" id="recommended-item-carousel" data-ride="carousel">
			<div class="banner-wrapper">
				<div class="container">
					<div class="form-wrapper">
						<div class="banner-header">
							<h2 class="top-header">Being Expert in Property</h2>
							<h2 class="sub-header">Now available in your hand</h2>
						</div>
						<div class="help-form-header">
							Apa yang bisa kami bantu?
						</div>
						<div class="help-form-body">
							<select>
								<option>
									Hubungi Call Center
								</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div><!--end banner-->
 		
			<div class="aboutArea">
			  <div class="container">
			  	<div class="row clearfix">
				  <div class="col-xs-12 intro-3-pilars">
					<span>
						Banyak orang hanya mengetahui property hanya dari kulitnya saja, bahkan beberapa yang lain tidak tahu sama sekali.<br/>
						Dengan 3 Pilar HFC ini, akan membawa anda bukan hanya tahu tentang property tetapi menjadi MASTER di bidang PROPERTY.
					</span>
					</div>
				</div>
				<div class="row clearfix">
				  <div class="col-xs-12">
				  </div><!-- col-sm-3 col-xs-12 -->
				</div><!-- row clearfix -->
				<div class="row clearfix">
				  <div class="col-xs-12">
					<div class="about_inner">
						<div class="col-md-4">
								<div class="aboutImage">
								  <img src="<%=request.getContextPath()%>/images/learning_.jpg" alt="" class="img-responsive" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/learning'">
								  <a href="<%=request.getContextPath()%>/page/learning" class="captionLink">Learning <span></span></a>
								</div><!-- aboutImage -->
						</div>
						<div class="col-md-4">
								<div class="aboutImage">
								  <img src="<%=request.getContextPath()%>/images/rnd_.jpg" alt="" class="img-responsive" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/research-development'">
								  <a href="<%=request.getContextPath()%>/page/research-development" class="captionLink">Research & Development <span></span></a>
								</div><!-- aboutImage -->
						</div>
						<div class="col-md-4">
								<div class="aboutImage">
								  <img src="<%=request.getContextPath()%>/images/advisory_.jpg" alt="" class="img-responsive" onclick="javascript:window.location.href='<%=request.getContextPath()%>/page/advisory'">
								  <a href="<%=request.getContextPath()%>/page/advisory" class="captionLink">Advisory <span></span></a>
								</div><!-- aboutImage -->
						</div>
					</div>
				  </div><!-- col-sm-3 col-xs-12 -->
				  
				</div><!-- row clearfix -->
			  </div><!-- container -->
			</div><!-- aboutArea -->
 </body>
</html>