<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrierung</title>
</head>
<body>
<body>
	<form method="post" action="Register">
		<p>Username
			<input type="text" name="username" />
		</p>	
		<p>Passwort
			<input type="password" name="password" />
		</p>
		<input type="submit" value="registrieren" /> 
	</form>
</body>
</html>