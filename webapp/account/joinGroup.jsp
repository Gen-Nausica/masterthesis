<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
<!-- script section -->
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/my-app-0.0.1-SNAPSHOT/js/script.js"></script>
<title>Gruppe beitreten</title>
</head>
<body>
<%@page import="org.apache.shiro.subject.Subject" %>
<%@page import="Masterarbeit.my_app.RoleCheck" %>
<%@page import="java.util.ArrayList" %>
<%
Subject currentUser = SecurityUtils.getSubject();
String p = (String) currentUser.getPrincipal();
RoleCheck r = new RoleCheck();
r.findUserRoles(p);
ArrayList <String> roles = r.getUserRoles();
String s = "";
if(request.getParameter("s") == null)
{
	s="true";
}
else
{
	s="false";
}
%>
<div class="main">
		<div class="row desktop-header">
			<div class="col-xs-12 col-md-8 col-lg-8">
				<h1>Gruppe beitreten</h1>
			</div>
		</div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbarMd">
		        <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="navbar-collapse collapse" id="collapsingNavbarMd">
		        <ul class="navbar-nav">
		            <li class="nav-item">
		                <a class="nav-link" href="/my-app-0.0.1-SNAPSHOT/home.jsp">Home</a>
		            </li>
		            <li class="nav-item">
		                <a class="nav-link" href="/my-app-0.0.1-SNAPSHOT/impressum.html">Impressum</a>
		            </li>
		            <li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Gruppenseiten</a>
		                <div class="dropdown-menu">
		                	<a class="dropdown-item" href="/my-app-0.0.1-SNAPSHOT/account/createGroup.jsp">Gruppe erstellen</a>
		                	<a class="dropdown-item" href="/my-app-0.0.1-SNAPSHOT/account/joinGroup.jsp">Gruppe beitreten</a>
		                	<%
								for (int i=0; i<roles.size(); i++)
								{
									out.println("<a class='dropdown-item' href='/my-app-0.0.1-SNAPSHOT/account/groupPage.jsp?gn="+roles.get(i)+"'>Gruppenseite "+roles.get(i)+"</a>");
								}
							%>
						</div>
					</li>
					<shiro:hasRole name="SysAdmin">
					<li class="nav-item">	
		               <a class="nav-link" href="/my-app-0.0.1-SNAPSHOT/account/adminPage.jsp">AdminPage</a>
		             </li>
		             </shiro:hasRole>
		             <li class="nav-item">	
		               <a class="nav-link" href="/my-app-0.0.1-SNAPSHOT/account/tip.jsp">Tippseite</a>
		             </li>    		                			                	
		        </ul>
		    </div>
		</nav><!-- end nav -->
		<div class="section">
			<div class="row">
				<div class="col-xs-12 col-md-12 col-lg-12">
<%if(s.equals("true"))
{
%>
					<h3>Endlich mittippen!</h3>
					<p>Hier kannst du einer vorhandenen Tippgruppe beitreten. Gib einfach deinen Nutzernamen, den Gruppenamen und das Passwort ein. Das Passwort und den Gruppennamen solltest du vom Gruppenadmin, der dich eingeladen hat, bekommen haben. Wenn du keinen Gruppennamen oder Passwort bekommen hast, kannst du <a href="/my-app-0.0.1-SNAPSHOT/account/createGroup.jsp">hier</a> eine neue Gruppe gründen.</p>
<%
}
else if (s.equals("false"))
{
%>
					<h3>Da ist etwas schief gelaufen!</h3>
					<p>Bitte überprüfe, ob der Gruppenname und das Passwort korrekt sind. Falls du schon Mitglied der Gruppe bist, kannst du direkt tippen. Ob du ein Mitglied bist, siehst du auf der <a href="/my-app-0.0.1-SNAPSHOT/home.jsp">Home-Seite</a>.</p>
<%
}
%>
				</div>
				<div class="col-xs-12 col-md-8 col-lg-8">
					<form action="JoinGroupServlet" method="post">
						<div class="form__row">
							<label class="hiddenLabel" for="username">Username</label>
					    	<input type="text" name="username" maxlength="30" value="<%=p%>" placeholder="Username">
					    </div>
					    <div class="form__row">
					    	<label class="hiddenLabel" for="groupname">Gruppenname</label>
					    	<input type="text" name="groupname" maxLength="30" placeholder="Gruppenname">
					    </div>
					    <div class="form__row">
					    	<label class= "hiddenLabel" for="grouppassword">Gruppenpasswort</label>
					    	<input type="password" name="grouppassword" maxLength="30">
					    </div>
					    <div class="form__row">
					    	<input type="submit" class="submit__btn" name="submit" value="Gruppe beitreten">
					    </div>
					 </form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>