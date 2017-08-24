<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rq" value="${pageContext.request }" scope="request" />
<c:set var="ctx" value="${rq.contextPath }" scope="request" />
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-title" content="hfc" />
	<meta charset="utf-8" />
    <title>Housing Finance Center</title>
	<link rel="icon" type="image/png" href="/img/favicon.png">
	<link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/select_option1.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/fullcalendar.min.css">
	<link rel="stylesheet" href='<%=request.getContextPath()%>/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/default.css" id="option_color">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/running-text/breakingNews.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/owl-carousel/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugin/vendor/owl-carousel/css/owl.theme.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script><!-- jQuery master -->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <c:out value="${headerScript}" escapeXml="false"/>
</head>