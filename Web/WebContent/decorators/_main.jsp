<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page" %>
<%@page import="id.base.app.webMember.SessionConstants"%>
<%@page import="id.base.app.SystemConstant"%>
<%@page import="id.base.app.webMember.SessionConstants"%>
<%@page import="id.base.app.valueobject.AppFunction"%>
<%@page import="id.base.app.valueobject.master.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
    <head>
		<meta charset="utf-8"/>
		<title>base.co.id &nbsp;-&nbsp; Member Application - <decorator:title default="Welcome!" /></title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="UTF-8" />
		<meta content="base application" name="description"/>
		<meta content="base.id team" name="author"/>
    
    	<link rel="icon" type="image/ico" href="<%=request.getContextPath()%>/images/favicon.ico" />
    	
    	<!-- Bootstrap -->
	    <link href="<%=request.getContextPath()%>/css/vendor/bootstrap/bootstrap.min.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/css/weather-icons.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/vendor/animate/animate.min.css">
	    <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/plugin/vendor/mmenu/css/jquery.mmenu.all.css" />
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/videobackground/css/jquery.videobackground.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/vendor/bootstrap-checkbox.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/vendor/bootstrap/bootstrap-dropdown-multilevel.css">
	
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/rickshaw/css/rickshaw.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/morris/css/morris.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/tabdrop/css/tabdrop.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/summernote/css/summernote.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/summernote/css/summernote-bs3.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/chosen/css/chosen.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/chosen/css/chosen-bootstrap.css">
	    <link rel="stylesheet" href='<%=request.getContextPath()%>/plugin/vendor/datatables/css/dataTables.bootstrap.css'>
	    <link rel="stylesheet" href='<%=request.getContextPath()%>/plugin/vendor/jquery-ui/jquery-ui.css'>
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/typeahead/css/typeahead.js-bootstrap.css"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/fuelux/css/wizzard.min.css"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/bs-wizard/bs-wizard-min.css" type="text/css" media="all">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minimal.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	    <![endif]-->
	    
	    <script src="<%=request.getContextPath()%>/js/jquery/jquery-2.1.3.min.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/js/jquery/jquery.alphanumeric.js" type="text/javascript"></script>
		<script>
		function setMenuActive(menu){
			$('#navigation ul.menu li.active').removeClass('active');
			$('#navigation ul.menu li#'+menu).addClass('active');
		}
		
       	$(document).ready(function() {
       		$('.autonumeric').autoNumeric('init', {aSep: '.', aDec: ',', mDec: '0'});
       		
       		$(document).ajaxStart(function() {
       			startAJAXLoader();
       			
       		}).ajaxStop(function() {
       			stopAJAXLoader();
       			
       		}).ajaxError(function(e, jqxhr, settings, exception) {
       			alert('System error occurred. Please refresh the browser and contact the system administrator.'); 
       		}).ajaxComplete(function(e, jqxhr, settings) {
       			var ajaxExpired = jqxhr.getResponseHeader('ajax-expired');
       			if(ajaxExpired=='true'){
       				alert('Your session is either expired or you are not login.');
       			}
       		});
       	});
       	</script>
	</head>
	<!-- Preloader -->
    <div id="mask" class="mask"><div id="loader"></div></div>
    <!--/Preloader -->
	
	<body class="bg-1" onload="initAJAXLoader('<%=request.getContextPath()%>'); initializeDialog(5);">
	<% 
		LinkedList<AppFunction> menus = (LinkedList<AppFunction>)session.getAttribute(SessionConstants.MENU_OBJECT_KEY);
		if(menus!=null&&menus.size()>0){
	%>
	<!-- Wrap all page content here -->
    <div id="wrap">

      <!-- Make page fluid -->
      <div class="row">

        <!-- Fixed navbar -->
        <div class="navbar navbar-default navbar-fixed-top navbar-transparent-black mm-fixed-top collapsed" role="navigation" id="navbar">

          <!-- Branding -->
          <div class="navbar-header col-md-2">
            <img src="<%=request.getContextPath()%>/images/logo.png" style="width:55%">
            <div class="sidebar-collapse">
              <a href="#">
                <i class="fa fa-bars"></i>
              </a>
            </div>
          </div>
          <!-- Branding end -->
          
          <!-- Dropdown Company -->
          <div class="col-md-5" style="display: inline-block;margin-left: -25px;">
          	<div class="form-horizontal form-group" style="padding:5px">
            	<label for="input07" class="col-sm-5 control-label input-sm top-filter"> Pilih Penggilingan Padi:</label>
				<div class="col-sm-7">
					<select id="session-company-selected" class="chosen-select form-control input-sm" readonly="" id="input07">
					<% 
						ArrayList<Company> companyList = (ArrayList<Company>) session.getAttribute(SessionConstants.COMPANY_LIST);
						Long companySelected = (Long) session.getAttribute(SessionConstants.COMPANY_SELECTED);
						if(companyList != null && companyList.size() > 0) {
							for(Company company : companyList) {
								String companyName = company.getName();
								Long pkCompany = company.getPkCompany();
								if(companySelected == pkCompany){
									%>
				                        <option value="<%=pkCompany%>" selected="selected"><%=companyName%></option>
									<%
								}else{
									%>
			                        	<option value="<%=pkCompany%>"><%=companyName%></option>
									<%
									
								}
							}
						}
					%>
					</select>
				</div>
				</div>
			</div>

          <!-- .nav-collapse -->
          <div class="navbar-collapse">
          	
          	<!-- Quick Actions -->
          	<page:applyDecorator name="_header" page="/headerPage.jsp" />
          	<!-- /Quick Actions -->
          
          	<% 
       		Object isInitialWizard = session.getAttribute(SessionConstants.INITIAL_WIZARD);
       		if (isInitialWizard != null && ((Boolean) isInitialWizard) == true) {
          	%>
          		<!-- Sidebar -->
          		<page:applyDecorator name="_wizardSideBar" page="/wizardSideBar.jsp" />
          		<!-- Sidebar end -->
          	<%  } else { %>
	          	<!-- Sidebar -->
	          	<page:applyDecorator name="_menu" page="/menuPage.jsp" />
	          	<!-- Sidebar end -->
          	<%  } %>
          </div>
          <!--/.nav-collapse -->
         
        </div>
        <!-- Fixed navbar end -->
        
        <!-- Page content -->  
	    <decorator:body />
	    <!-- Page content end -->
	    
	  </div>
      <!-- Make page fluid-->
      
    </div>
    <!-- Wrap all page content end -->
    
    <section class="videocontent" id="video"></section>

	<%}else{ %>
	   <decorator:body />
       <jsp:include page="/blank.jsp"></jsp:include>
    <%} %>
    <script src="<%=request.getContextPath()%>/js/jquery/autoNumeric.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/modal-dialog.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/common.js" type="text/javascript" ></script>
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath()%>/plugin/vendor/bootstrap/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/vendor/bootstrap/bootstrap-dropdown-multilevel.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugin/vendor/mmenu/js/jquery.mmenu.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugin/vendor/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugin/vendor/nicescroll/jquery.nicescroll.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugin/vendor/animate-numbers/jquery.animateNumbers.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugin/vendor/videobackground/jquery.videobackground.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugin/vendor/blockui/jquery.blockUI.js"></script>

    <script src="<%=request.getContextPath()%>/plugin/vendor/flot/jquery.flot.min.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/vendor/flot/jquery.flot.time.min.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/vendor/flot/jquery.flot.selection.min.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/vendor/flot/jquery.flot.animator.min.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/vendor/flot/jquery.flot.orderBars.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/vendor/easypiechart/jquery.easypiechart.min.js"></script>

    <script src="<%=request.getContextPath()%>/plugin/vendor/rickshaw/raphael-min.js"></script> 
    <script src="<%=request.getContextPath()%>/plugin/vendor/rickshaw/d3.v2.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/vendor/rickshaw/rickshaw.min.js"></script>

    <script src="<%=request.getContextPath()%>/plugin/vendor/morris/morris.min.js"></script>

    <script src="<%=request.getContextPath()%>/plugin/vendor/tabdrop/bootstrap-tabdrop.min.js"></script>
 	
    <script src="<%=request.getContextPath()%>/plugin/vendor/summernote/summernote.min.js"></script>

    <script src="<%=request.getContextPath()%>/plugin/vendor/chosen/chosen.jquery.min.js"></script>
    
    <script src="<%=request.getContextPath()%>/plugin/vendor/datatables/jquery.dataTables.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/js/bloodhound.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugin/vendor/typeahead/typeahead.jquery.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/minimal.min.js"></script>
    
    <%-- <script src="<%=request.getContextPath()%>/plugin/vendor/fuelux/fuelux.wizard.min.js"></script> --%>
    <%-- <script src="<%=request.getContextPath()%>/plugin/vendor/fuelux/wizard-elements.min.js"></script> --%>
    <script src="<%=request.getContextPath()%>/plugin/vendor/bs-wizard/bs-wizard.js"></script>
    <script>
	    $(function(){
	    	$('.numeric').autoNumeric('init', {aSep:'.', aDec:',', vMin: '-9999999999.99', vMax:'9999999999.99'});
	    	
	    	$('#session-company-selected').change(function(){
	    		var sURL = "<%=request.getContextPath()%>/do/settings/company/updateCompanySelected";
				$.post(sURL, { pkCompany: $(this).val() }, function(data) {
					if(data.errorList!=null){
						displayErrorParsley(data.errorList);
					}else{
						location.reload();
					}
				});
	    	});
	    });
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
<!-- END BODY -->
</html>
