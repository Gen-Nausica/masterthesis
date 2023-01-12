<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="include.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="../my-app-0.0.1-SNAPSHOT/css/style.css">
	<!-- script section -->
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<body>
<div class="main">
		<div class="row desktop-header">
			<div class="col-xs-12 col-md-8 col-lg-8">
				<h1>Hier kannst du dich registrieren</h1>
			</div>
		</div>
		<div class="section">
			<div class="row">
				<div class="col-xs-12 col-md-12 col-lg-12">
<%
	if(request.getParameter("s") != null)
	{
%>
					<h3>Da ist etwas schiefgelaufen!</h3>
					<p>Der Nutzername, den du eingegeben hast, existiert schon, bitte versuche es noch einmal.</p>
<%
	}
%>
					<form action="Register" method="post">
						<div class="form__row">
					    	<label class="hiddenLabel" for="username">Username</label>
					    	<input type="text" name="username" maxlength="30" placeholder="Username">
					    </div>
						<div class="form__row">
							<label class="hiddenLabel" for="password">Passwort</label>
							<input type="password" name="password" maxlength="30" placeholder="Passwort">
						</div>
						<div class="form__row">
					    	<input type="submit" class="submit__btn" value="registrieren">
					    </div>
					  </form>
				 </div>
			</div>
		</div>
	</div>
</body>
</html>