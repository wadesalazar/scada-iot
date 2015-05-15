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
<meta name="author" content="Dilip Kari">

<title>Home Page</title>
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
.hero-spacer {
	margin-top: 50px;
}

.hero-feature {
	margin-bottom: 30px;
}

footer {
	margin: 50px 0;
}
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
<body body style="background-color: #f1f2f6;" data-spy="scroll"
	data-target=".bs-docs-sidebar" data-twttr-rendered="true">

	<!-- Fixed navbar -->
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
								onclick="submitAction('${contextPath}/iot');">Random Data</a></li>							
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

		<div class="col-md-12" style="padding-left: 0px; padding-right: 0px;">
			<ol class="breadcrumb breadcrumb-arrow">
				<li><a href="#"
					onclick="submitAction('${contextPath}/home/homePage');"><i
						class="glyphicon glyphicon-home"></i> Home</a></li>
				<li class="active"><span>Overview</span></li>
			</ol>
		</div>

		<form name="myForm" class="form" method="post" action=""
			accept-charset="UTF-8">

			<!--h1>HOme Page Content go here ....</h1-->
		</form>

		<br />

		<!-- Jumbotron Header -->
		<!-- <div class="alert alert-success alert-dismissable"
			style="padding-top: 15px; margin-top: 30px;">

			<h4></h4>
			<p>IOT Data related text...</p>
			<p>
				<a class="btn btn-success" href="#">learn more...</a>
			</p>
		</div>  -->

		<!-- <div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Panel title</h3>
			</div>
			<div class="panel-body">Panel content</div>
		</div> -->


		<div class="example">
			<h4 class="example-title" style="color: #073763; margin-bottom: 5px;">Data Analytics Selfsevice Menu</h4>
			<div class="row">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img class="img-rounded"
							src="${contextPath}/resources/img/lineChart.png"
							style="height: 150px; width: 170px;">
						<div class="caption text-center">
							<h3>Equipment Monitor</h3>
							<p>This service includes the review of Equipment Monitor streams .....</p>
							<p>
								<a href="#"
									onclick=""
									class="btn btn-warning" role="button">Chart</a> <a href="#"
									onclick=""
									class="btn btn-default" role="button">Data</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img class="img-rounded"
							src="${contextPath}/resources/img/barChart.png"
							style="height: 150px; width: 155px;">
						<div class="caption text-center">
							<h3>Equipment repository</h3>
							<p>This includes the review of Equipment repository ...</p>
							<p>
								<a href="#"
									onclick=""
									class="btn btn-warning" role="button">Chart</a> <a href="#"
									onclick=""
									class="btn btn-default" role="button">Data</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img class="img-rounded"
							src="${contextPath}/resources/img/chart.png"
							style="height: 150px; width: 190px;">
						<div class="caption text-center">
							<h3>Enhanced Analysis</h3>
							<p>This includes review of Enhanced Analysis ...</p>
							<p>
								<a href="#"
									onclick=""
									class="btn btn-warning" role="button">Chart</a> <a href="#"
									onclick=""
									class="btn btn-default" role="button">Data</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img class="img-rounded"
							src="${contextPath}/resources/img/table.png"
							style="height: 150px; width: 170px;">
						<div class="caption text-center">
							<h3>Control System Data</h3>
							<p>This includes review of IOT Data review...</p>
							<p>
								<a href="#"
									onclick="submitAction('${contextPath}/iot');"
									class="btn btn-warning" role="button">Chart</a> <a href="#"
									onclick="submitAction('${contextPath}/iot');"
									class="btn btn-default" role="button">Data</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- Footer -->
		<footer style="margin-top: 40px; margin-bottom: 20px;">
			<div class="row">
				<div class="col-lg-12">
					<!-- <p>Copyright ©</p> -->
				</div>
			</div>
		</footer>



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