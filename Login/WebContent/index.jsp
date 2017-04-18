<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>HFC - Admin Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/ico" href="<%=request.getContextPath()%>/images/favicon.ico" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/materialize.min.css">
  <style>
    body {
      	background: rgba(0,91,158,1);
		background: -moz-linear-gradient(left, rgba(0,91,158,1) 0%, rgba(207,209,198,1) 51%, rgba(176,173,180,1) 100%);
		background: -webkit-gradient(left top, right top, color-stop(0%, rgba(0,91,158,1)), color-stop(51%, rgba(207,209,198,1)), color-stop(100%, rgba(176,173,180,1)));
		background: -webkit-linear-gradient(left, rgba(0,91,158,1) 0%, rgba(207,209,198,1) 51%, rgba(176,173,180,1) 100%);
		background: -o-linear-gradient(left, rgba(0,91,158,1) 0%, rgba(207,209,198,1) 51%, rgba(176,173,180,1) 100%);
		background: -ms-linear-gradient(left, rgba(0,91,158,1) 0%, rgba(207,209,198,1) 51%, rgba(176,173,180,1) 100%);
		background: url('<%=request.getContextPath()%>/images/background/back-hexa.png'),linear-gradient(to right, rgba(0,91,158,1) 0%, rgba(207,209,198,1) 51%, rgba(176,173,180,1) 100%);
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#005b9e', endColorstr='#b0adb4', GradientType=1 );
    }
    .login-wrapper{
        background-color: white;
        display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;border-bottom: 5px solid #FFDE00;
    }
    .toast{
    	background-color:white!important;
    	color:black!important;
    	font-weight:700;
    }
    main {
      flex: 1 0 auto;
    }
    .title-login{
    	color:white!important;
    }
    .input-field input[type=date]:focus + label,
    .input-field input[type=text]:focus + label,
    .input-field input[type=email]:focus + label,
    .input-field input[type=password]:focus + label {
      color: #e91e63;
    }
    .input-field input[type=date]:focus,
    .input-field input[type=text]:focus,
    .input-field input[type=email]:focus,
    .input-field input[type=password]:focus {
      border-bottom: 2px solid #e91e63;
      box-shadow: none;
    }
  </style>
</head>

<body>
  <div class="background-image"></div>
  <div class="section"></div>
  <main>
    <center>
      <div class="section"></div>

      <h5 class="title-login">Please, login into your account</h5>
      <div class="section"></div>

      <div class="container">
        <div class="z-depth-1 row login-wrapper">

          <form class="col s12" method="post">
            <div class='row'>
              <div class='col s12'>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
              	<input type="text" class="validate" name="username" id="username"/>
                <label for='username'>Enter your username</label>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                <input class='validate' type='password' name='password' id='password' />
                <label for='password'>Enter your password</label>
              </div>
              <label style='float: right;'>
								<a id="forgotPassword" class='pink-text' href='#!'><b>Forgot Password?</b></a>
							</label>
            </div>

            <br />
            <center>
              <div class='row'>
                <button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect indigo btn-login'>Login</button>
              </div>
            </center>
          </form>
          <div id="div-post"></div>
        </div>
      </div>
    </center>

    <div class="section"></div>
    <div class="section"></div>
  </main>

  <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/materialize.min.js"></script>
  <script type="text/javascript">
    	$(function(){
	    	<%
	          	System.out.println(request.getParameter("error"));
	          	if(request.getParameter("error") != null) {
	          		if(request.getParameter("error").equals("wrongAccount")) {
	          			%> 
	          				var $toastContent = $("<span>invalid username / password</span>");
	            			Materialize.toast($toastContent, 2000); 
	            		<%	
	          		} else if(request.getParameter("error").equals("tokenExpired")) {
	          			%> 
          				var $toastContent = $("<span>session expired</span>");
            			Materialize.toast($toastContent, 2000); 
            		<%	
          			}
	          	}
	        %>
    		
    		
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
    				Materialize.toast('Please insert user name', 2000);
    				return false;
    			}else if(password=''){
    				Materialize.toast('Please insert your password', 2000);
    				return false;
    			}
    			return true;
    		}
    		
    		var forgotPassword = function() {
    			var rand = Math.random().toString(36).substring(7);
    			var url = protocol+"://"+domain+":"+defaultPort+webTransContext+"/do/forgotPassword";
    			var html = "<form id='"+rand+"' style='display:none' method='post' action='"+url+"'><input type='submit'></form>";
				$('#div-post').html(html);
				$('#'+rand).submit();
				$('#div-post').html('');
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
    				
    				var rand = Math.random().toString(36).substring(7);
    				var html = "<form id='"+rand+"' style='display:none' method='post' action='"+url+"'><input type='hidden' name='token' value='"+token+"'/><input type='submit'></form>";
    				$('#div-post').html(html);
    				$('#'+rand).submit();
    				$('#div-post').html('');
    			}
    		}
    		
    		$('.btn-login').click(function(e){
    			e.preventDefault();
    			loginPost(2);
    		});
    		
    		$('#forgotPassword').click(function(e){
    			e.preventDefault();
    			forgotPassword();
    		});
    		
    		var getExpiry = function(){
    			var date = new Date();
    			var expiry = new Date(date.getTime() + minutes*60000);
    			return expiry.yyyymmdd();
    		}
    	})
	</script>
</body>
</html>