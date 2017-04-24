var protocol = 'http';
var uniqueToken = 'LzJwYXNz519yZCI6Im51c3RlciJ9';
var webAdminContext = '/WebAdmin';
var webTransContext = '/Web';
var minutes = 20;

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

var forgotPassword = function(defaultPort, domain) {
	var rand = Math.random().toString(36).substring(7);
	var url = protocol+"://"+domain+":"+defaultPort+webTransContext+"/do/forgotPassword";
	var html = "<form id='"+rand+"' style='display:none' method='post' action='"+url+"'><input type='submit'></form>";
	$('#div-post').html(html);
	$('#'+rand).submit();
	$('#div-post').html('');
}

var loginPost = function(num, username, password, defaultPort, domain){
	if(checkUserName(username, password)){
		var currToken = uniqueToken;
		var currPort = defaultPort;
		var host = domain;
		var ptcl = protocol;
		var restServiceURL = '/do/landingPage';
		var jsonToken = {"randomNumber":'900'};
		jsonToken['username'] = username;
		jsonToken['expiredTime'] = getExpiry();
		jsonToken['password'] = password;
		jsonEncoded = btoa(btoa(JSON.stringify(jsonToken))+uniqueToken);
		//var token = '900|'+username+'|'+getExpiry()+'|'+password+'|sadasdaijwiajdiwdakdmaskdmasdksaa'+currToken;
		var url = ptcl+"://"+host+":"+currPort;
		if(num==1){	
			url = url + webAdminContext + restServiceURL;
		}else if(num==2){
			url = url + webTransContext + restServiceURL;
		}
		var rand = Math.random().toString(36).substring(7);
		var html = "<form id='"+rand+"' style='display:none' method='post' action='"+url+"'><input type='hidden' name='1c2Vybm' value='"+jsonEncoded+"'/><input type='submit'></form>";
		$('#div-post').html(html);
		$('#'+rand).submit();
		$('#div-post').html('');
	}
}

var getExpiry = function(){
	var date = new Date();
	var expiry = new Date(date.getTime() + minutes*60000);
	return expiry.yyyymmdd();
}