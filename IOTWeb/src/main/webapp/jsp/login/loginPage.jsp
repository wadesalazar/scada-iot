<%@page contentType="text/html; utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" scope="request"><%= request.getContextPath()%></c:set>

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

<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/docs.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/bootflat.min.css" />

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
	/*background-image: url('${contextPath}/resources/img/NavBg.png');*/
	background-color: #073763;
	background-repeat: repeat-x;
}

.footerNav {
	background-color: #073763;
	background-repeat: repeat-x;
}

.span-or {
	display: block;
	position: absolute;
	left: 50%;
	top: -2px;
	margin-left: -25px;
	background-color: #fff;
	width: 50px;
	text-align: center;
}

.login-or {
	position: relative;
	font-size: 18px;
	color: #aaa;
	margin-top: 10px;
	margin-bottom: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
}

.hr-or {
	background-color: #cdcdcd;
	height: 1px;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
}

</style>
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true">
	
	<!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top topNav" role="navigation">
      <div class="container navbar-inner">
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
					<img class="img-responsive" style="height: 50px; width: 50px;" src="/IOTWebApp/resources/img/hadoop_med.png"> 
					<!-- <p style="font-size: 24px; color: #00ff00; padding-top:5px;"><i>Internet of Things</i></p>  -->
				</a>

				<!-- <a class="navbar-brand" href="#" style="height: 20px;"><div class="logo-style">Schlumberger RTS</div>
				</a> -->

			</div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="./">Login</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

	
	
	
	
	
	<div class="container">
	<div class="row .col-md-12">

		<br /> <br /> <br />
		<div class="row">
		<div class="col-md-6">
			<div class="col-md-8" style="float:right">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<strong>Sign in </strong>
						</h3>
					</div>
					<div class="panel-body">
						<form class="form" role="form" method="post" action="home/homePage"
							accept-charset="UTF-8" id="login-nav">
							<div class="form-group">
								<label class="sr-only" for="exampleInputEmail1">Email
									address</label> <input type="email" class="form-control"
									id="exampleInputEmail1" placeholder="Email address" required>
							</div>
							<div class="form-group">
								<label class="sr-only" for="exampleInputPassword2">Password</label>
								<input type="password" class="form-control"
									id="exampleInputPassword2" placeholder="Password" required>
							</div>
							
							<div class="form-group">

								<input type="checkbox"> Remember me</input>
								<div class="pull-right"><a href="#">Forgot password?</a></div>

							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-block">Sign
									in</button>
							</div>


							<div class="login-or">
								<hr class="hr-or">
								<span class="span-or">or</span>
							</div>

							<div>
							
							<input class="btn btn-primary btn-block" type="button"
									id="sign-in-facebooke" value="Sign In with LDAP Account">
							</div>


						</form>
					</div>
				</div>
			</div>
			</div>

			<div class="col-md-6">
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<strong>Don't have an account! Sign Up Here</strong>
						</h3>
					</div>
					<div class="panel-body">
						<form class="form" role="form" method="post" action="home/homePage"
							accept-charset="UTF-8" id="login-nav">

							<div class="form-group">
								<div class="row">
									<div class="col-xs-6 col-md-6">
										<input class="form-control" name="firstname"
											placeholder="First Name" type="text" required autofocus />
									</div>
									<div class="col-xs-6 col-md-6">
										<input class="form-control" name="lastname"
											placeholder="Last Name" type="text" required />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only" for="exampleInputEmail2">Email</label> <input
									type="email" class="form-control" id="exampleInputEmail2"
									placeholder="Your Email" required>
							</div>
							<div class="form-group">
								<label class="sr-only" for="exampleInputPassword2">Password</label>
								<input type="password" class="form-control"
									id="exampleInputPassword2" placeholder="Password" required>
							</div>
							<div class="form-group">
								<label class="sr-only" for="exampleInputPassword2">Confirm
									Password</label> <input type="password" class="form-control"
									id="exampleInputPassword2" placeholder="Confirm Password"
									required>
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-success btn-block">Register</button>
							</div>
							<small>By signing up, you agree to our <a href="/terms" target="_blank">terms and conditions.</a></small>

						</form>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
	
	
	
	
	
	
	
	
	<!--div id="page-footer" class="container">
    <nav class="navbar navbar-default navbar-fixed-bottom footerNav" style="line-height:10px; height:10px;">
        <div class="navbar-inner navbar-content-center">

            <div class="content">
            <div class="row">
                 <div class="col-md-12 text-center">Schlumberger RTS</div>
            </div>
        </div>
        </div>
    </nav>
</div-->

	<script type='text/javascript'>
		$(document).ready(function() {
		});
	</script>
	
</body>
</html>