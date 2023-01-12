<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="../css/style.css">
	<!-- script section -->
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<title>Neue Gruppe erstellen</title>
</head>
<body>
<div class="main">
	<div class="row desktop-header">
		<div class="col-xs-12 col-md-8 col-lg-8">
			<h1>Erstelle eine neue Tipp-Gruppe!</h1>
		</div>
	</div>
	<div class="section">
		<div class="row">
			<div class="col-xs-12 col-md-12 col-lg-12">
				<form action="CreateGroupServlet" method="post">
					<div class="form__row">
						<input type="text" style="display:none; height:1px" value="<shiro:user><shiro:principal/></shiro:user>" name="groupadmin"/>
					</div>
					<div class="form__row">
						<input type="text" name="groupname" placeholder="Gruppenname"/>
					</div>
					<div class="form__row">
						<input type="password" name="grouppassword" placeholder="Gruppenpasswort"/>
					</div>
					<div class="form__row">
						<input class="submit__btn" type="submit" value="Gruppe erstellen" name="submit"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>