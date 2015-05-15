<%@page contentType="text/html; utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" scope="request"><%=request.getContextPath()%></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- sets you up for cross-device layout -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Landing screen of the site">
<meta name="author" content="Pavan Guthikonda">

<title>IOT Data</title>
<head>
	
<link rel="stylesheet"
	href="${contextPath}/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/docs.css" />
<link rel="stylesheet"
	href="${contextPath}/resources/css/bootflat.min.css" />
<link rel="stylesheet"
	href="${contextPath}/resources/css/triangle-breadcrumb.css">

<script src="${contextPath}/resources/js/jquery-2.0.3.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<style type="text/css">
body {
	/*min-height: 300px;*/
	padding-top: 70px;
}

#page-footer {
	background-color: #B9C2C9;
	bottom: 0; //
	height: 10px;
	position: relative;
	width: 100%;
}

/* Narbar background*/
.topNav {
	background-color: #073763;
	background-repeat: repeat-x;
}

.footerNav {
	/*background-color: #656d78;*/
	background-image: url('${contextPath}/resources/img/NavBg.png');
	background-repeat: repeat-x;
}

/* menu hover changes */
.logo-style {
	font-family: 'Arial Black';
	font-style: normal;
	font-weight: normal;
	font-size: 30px;
}

.navbar-default .navbar-nav>li>a, .navbar-default .navbar-nav>li>a {
	color: #ffffff;
	font-weight: bold;
	font-family: verdana;
}

.navbar-default .navbar-nav>li>a:focus, .navbar-default .navbar-nav>li>a:focus
	{
	color: #ffffff;
	font-weight: bold;
	font-family: verdana;
}

.navbar-default .navbar-nav>li>a:hover, .navbar-default .navbar-nav>li>a:focus
	{
	/*color: #000000;*/
	background-color: #434a54;
}

.navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:hover,
	.navbar-default .navbar-nav>.active>a:focus {
	/*color: #000000;*/
	background-color: #434a54;
	border-radius: 5px 5px 0 0;
}

.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:hover,
	.navbar-default .navbar-nav>.open>a:focus {
	/*color: #000000;*/
	background-color: #434a54;
	border-radius: 5px 5px 0 0;
}

.navbar-default .navbar-nav>.open>a .caret, .navbar-default .navbar-nav>.open>a:hover .caret,
	.navbar-default .navbar-nav>.open>a:focus .caret {
	border-top-color: #434a54;
	border-bottom-color: #434a54;
}

/*.navbar-default .navbar-nav>li>ul>li .divider{
	background-color: red !important;
	height: 40px;
}*/
</style>

<script>
	$(function() {
		// Nav bar hover slidedown and highlight menu item
		$('ul.nav li.dropdown').hover(
				function() {
					//$(this).children('ul.dropdown-menu').slideDown();
					$(this).find('.dropdown-menu').first().stop(true, true)
							.delay(250).slideDown();
					$(this).addClass('open');
				},
				function() {
					//$(this).children('ul.dropdown-menu').slideUp();
					$(this).find('.dropdown-menu').first().stop(true, true)
							.delay(250).slideUp();
					$(this).removeClass('open');
				});

	});

	function submitAction(actionString) {
		document.myForm.action = actionString;
		return document.myForm.submit();
	}
</script>

</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar"
	data-twttr-rendered="true">

	<div class="navbar navbar-default navbar-fixed-top topNav"
		role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<!--a class="navbar-brand" href="#"> <img
					class="img-responsive" src="${contextPath}/resources/img/logo.jpg">
				</a-->

				<a class="navbar-brand" href="#"
					style="margin-left: 0px; padding-top: 2px; padding-bottom: 0px; padding-left: 0px;">
					<img class="img-responsive" style="height: 50px; width: 50px;" src="${contextPath}/resources/img/hadoop_med.png"> 
					<!-- <p style="font-size: 24px; color: #00ff00; padding-top:5px;"><i>Internet of Things</i></p>  -->
				</a>

				<!-- <a class="navbar-brand" href="#" style="height: 20px;"><div class="logo-style">Schlumberger RTS</div>
				</a> -->

			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="#"
						onclick="submitAction('${contextPath}/home/homePage');"> <!--span class="glyphicon glyphicon-home"-->Home
					</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">IOT Data <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"
								onclick="submitAction('${contextPath}/forms/allPumps');">Random Data</a></li>							
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Guest User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">User Profile</a></li>
							<li><a href="#">Settings</a></li>
							<!--li class="divider"></li-->
							<li><a href="#">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>







	<div class="container">

		<!--div id="page-wrap">
			<ul class="breadcrumb-css" style="padding-left: 0px !important;">
				<li><a href="#">Home</a></li>
				<li><a href="#">Charts</a></li>
				<li><a href="#">Query by Depth</a></li>
				<li><a href="#">Line Chart</a></li>
			</ul>
		</div-->

		<div class="col-md-12" style="padding-left: 0px; padding-right: 0px;">
			<ol class="breadcrumb breadcrumb-arrow">
				<li><a href="#"
					onclick="submitAction('${contextPath}/home/homePage');"><i
						class="glyphicon glyphicon-home"></i> Home</a></li>
				<li><a href="#">IOT Data</a></li>
				<li class="active"><span>Random Data</span></li>
				
			</ol>
		</div>

		<!--div class="row col-md-12">
			<ul class="breadcrumb">  
			  <li>  
			    <a href="#">Home</a> <span class="divider">></span>  
			  </li>  
			  <li>  
			    <a href="#">Tutorials</a> <span class="divider">></span>  
			  </li>  
			  <li class="active">HTML5</li>  
			</ul>  
		</div-->


		<div class="row">	

			<div class="col-md-12">
				<%@ include file="./iot.jsp"%>
			</div>
		</div>
	</div>

	<script type='text/javascript'>
		$(document).ready(function() {
			$('#chartTypeCombo li a').click(function() {
				var value = $(this).text();
				$('#selectedChartType').val(value);
				// var v = $('#selectedChartType').val();
				// alert(v);

				$("#chartType:first-child").text($(this).text());
				$("#chartType:first-child").val($(this).text());
			});
		});
	</script>

</body>
</html>