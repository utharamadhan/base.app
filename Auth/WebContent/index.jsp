<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login Site</title>
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
		background: linear-gradient(to right, rgba(0,91,158,1) 0%, rgba(207,209,198,1) 51%, rgba(176,173,180,1) 100%);
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/26UC1OMEIT.js"></script>
  <script type="text/javascript">
  		var url = "${pageContext.request.contextPath}";
  		url = url.replace("Auth","Site");
  		
  		function setCookie(cname, cvalue, exdays) {
  		    var d = new Date();
  		    d.setTime(d.getTime() + (exdays * 60 * 60 * 1000));
  		    var expires = "expires="+d.toUTCString();
  		    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  		}

  		function getCookie(cname) {
  		    var name = cname + "=";
  		    var ca = document.cookie.split(';');
  		    for(var i = 0; i < ca.length; i++) {
  		        var c = ca[i];
  		        while (c.charAt(0) == ' ') {
  		            c = c.substring(1);
  		        }
  		        if (c.indexOf(name) == 0) {
  		            return c.substring(name.length, c.length);
  		        }
  		    }
  		    return "";
  		}
  		
    	$(function(){
	        $('.btn-login').click(function(e){
	        	e.preventDefault();
	        	var username = $('#username').val();
	        	var password = $('#password').val();
	        	if(!username || !password){
	        		alert("Username and Password Wrong!");
	        	}else{
	        		if(username=='ADMIN' && password =='master'){
	        			setCookie("AUTH_KEY",username,4)
	        			window.location.href = url;
	        		}else{
	        			alert("Username and Password Wrong!");
	        		}
	        	}
	        });
    	});
	</script>
</body>
</html>