<!DOCTYPE html>	
<html>
<head>
	<meta charset="utf-8">
	<title>BaseApp - login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8" />
    <link rel="icon" type="image/ico" href="<%=request.getContextPath()%>/images/favicon.ico" />
    
    <link href="<%=request.getContextPath()%>/css/vendor/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/vendor/bootstrap-checkbox.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/vendor/bootstrap/bootstrap-dropdown-multilevel.css">
    
    <link href="<%=request.getContextPath()%>/css/minimal.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/script/jquery-1.11.1.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="bg-1">
	<!-- Wrap all page content here -->
    <div id="wrap">
      <!-- Make page fluid -->
      <div class="row">
        <!-- Page content -->
        <div id="content" class="col-md-12 full-page login">
          <div class="inside-block">
          	<img src="<%=request.getContextPath()%>/images/beta-stamp.png" style="width: 200px;position: absolute;margin-top: 10px;margin-left: 200px;">
            <img src="<%=request.getContextPath()%>/images/logo.png" class="logo" style="width:100%">
            <h1><strong>Selamat Datang</strong> Anggota</h1>
            
            <form id="form-signin" class="form-signin">
              <section>
                <div class="input-group">
                  <input type="text" class="form-control" name="username" placeholder="Username" id="username" required oninvalid="this.setCustomValidity('Username tidak boleh kosong')"/>
                  <div class="input-group-addon"><i class="fa fa-user"></i></div>
                </div>
                <div class="input-group">
                  <input type="password" class="form-control" name="password" placeholder="Password" id="password" required oninvalid="this.setCustomValidity('Password tidak boleh kosong')">
                  <div class="input-group-addon"><i class="fa fa-key"></i></div>
                </div>
              </section>
              <section class="controls">
                <div class="checkbox check-transparent">
                  <input type="checkbox" value="1" id="remember" checked>
                  <label for="remember">Ingatkan saya</label>
                </div>
                <a href="#">Lupa Password?</a>
              </section>
              <section class="log-in">
                <button class="btn btn-greensea btn-login">Log In</button>
                <span>atau</span>
                <button class="btn btn-slategray">Registrasi Akun</button>
              </section>
            </form>
          </div>
        </div>
        <!-- /Page content -->  
      </div>
    </div>
    <!-- Wrap all page content end -->
    <script type="text/javascript">
    	$(function(){
    		//BEGIN: change this to your server environment
    		var protocol = 'http';
    		var domain = '<%=request.getServerName()%>';
    		var defaultPort = '<%=request.getServerPort()%>'; 
    		var webAdminContext = '/WebAdmin';
    		var webTransContext = '/Web';
    		var minutes = 20; //don't set more than 60
    		//END: change this to your server environment
    		
    		var uniqueToken = 'sSs';
    		
    		Date.prototype.yyyymmdd = function() {
    		   var yyyy = this.getFullYear().toString();
    		   var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based
    		   var dd  = this.getDate().toString();
    		   var hr = this.getHours().toString();
    		   var min = this.getMinutes().toString();
    		   return yyyy + (mm[1]?mm:"0"+mm[0]) + (dd[1]?dd:"0"+dd[0]) + (hr[1]?hr:"0"+hr[0]) + (min[1]?min:"0"+min[0]); // padding
    		  };
    		
    		var checkUserName = function(user, password){
    			if(user==''){
    				alert('Please insert user name');
    				return false;
    			}else if(password=''){
    				alert('Please insert your password');
    				return false;
    			}
    			return true;
    		}
    		
    		var loginPost = function(num){
    			var user = document.getElementById('username').value;
    			var password = document.getElementById('password').value;
    			if(checkUserName(user, password)){
    				var currToken = uniqueToken;
    				var currPort = defaultPort;
    				var host = domain;
    				var ptcl = protocol;
    				var restServiceURL = '/do/landingPage';
    				var token = '900|'+user+'|'+getExpiry()+'|'+password+'|sadasdaijwiajdiwdakdmaskdmasdksaa'+currToken;
    				var url = ptcl+"://"+host+":"+currPort;
    				if(num==1){	
    					url = url + webAdminContext + restServiceURL;
    				}else if(num==2){
    					url = url + webTransContext + restServiceURL;
    				}
    				
    				var form = document.createElement("form");
    			    form.setAttribute("method", "post");
    			    form.setAttribute("action", url);
    			    var tokenField = document.createElement("input");
    			    	tokenField.setAttribute("type", "hidden");
    			    	tokenField.setAttribute("name", "token");
    			    	tokenField.setAttribute("value", token);
    			    	form.appendChild(tokenField);
  			    	var button = document.createElement("input");
  			    	  	button.setAttribute('type', "submit");
  			    		form.appendChild(button);
    			    form.submit();
    			}
    		}
    		
    		$('.btn-login').click(function(e){
    			e.preventDefault();
    			loginPost(2);
    		});
    		
    		$('.btn-slategray').click(function(e){
    			e.preventDefault();
    			register();
    		});
    		
    		var register = function(){
    			var currPort = defaultPort;
    			var currToken = uniqueToken;
    			var user = document.getElementById('username').value;
    			var host = domain;
    			var ptcl = protocol;
    			var restOfUrl = ptcl+'://'+host+':'+currPort+webTransContext+'/do/registration';
    			window.open(restOfUrl);
    		}
    		
    		var getExpiry = function(){
    			var date = new Date();
    			var expiry = new Date(date.getTime() + minutes*60000);
    			return expiry.yyyymmdd();
    		}
    	})
	</script>
	<!--Start of Tawk.to Script-->
	<script type="text/javascript">
	var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
	(function(){
	var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
	s1.async=true;
	s1.src='https://embed.tawk.to/573dfd4cc59e45d972c2fa1e/default';
	s1.charset='UTF-8';
	s1.setAttribute('crossorigin','*');
	s0.parentNode.insertBefore(s1,s0);
	})();
	</script>
	<!--End of Tawk.to Script-->
</body>
</html>