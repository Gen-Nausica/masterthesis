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
<%@page import="org.apache.shiro.subject.Subject" %>
<%@page import="Masterarbeit.my_app.RoleCheck" %>
<%
Subject currentUser = SecurityUtils.getSubject();
String p = (String) currentUser.getPrincipal();
RoleCheck r = new RoleCheck();
r.findUserRoles(p);
ArrayList <String> roles = r.getUserRoles();
%>
	<div class="main">
		<div class="row desktop-header">
			<div class="col-xs-12 col-md-12 col-lg-12">
				<h1>Das wunderbarste Oscar-Tippspiel der Welt</h1>
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
			<section>
				<div class="col-md-5">
					<p>Hallo <shiro:guest>Gast</shiro:guest><shiro:user><shiro:principal/></shiro:user>!
					    ( <shiro:user><a href="<c:url value="/logout"/>">Log out</a></shiro:user>
					    <shiro:guest><a href="<c:url value="/login.jsp"/>">Log in</a></shiro:guest> )
					</p>
				</div>
				<div class="col-md-5">
					<shiro:guest><p class="register"><a href="<c:url value="/register.jsp"/>">Registrieren</a></p></shiro:guest>
				</div>
				<div class="col-md-5">
					<p>Willkommen zum Oscar Tippspiel!</p>
				</div>
				<div class="col-md-5">
					<shiro:user><p>Trete einer <a href="<c:url value="/account/joinGroup.jsp"/>">Gruppe</a> bei.</p></shiro:user>
					<shiro:user><p>Erstelle eine <a href="<c:url value="/account/createGroup.jsp"/>">neue Gruppe</a></p></shiro:user>
				</div>
				<shiro:user>
				<div class="col-md-5">
					<p>Du bist bereits Mitglied folgender Gruppen:</p>
				</div>
				<div class="col-md-5">
					<%@page import="org.apache.shiro.subject.Subject" %>
					<%@page import="Masterarbeit.my_app.RoleCheck" %>
					<%@page import="java.util.ArrayList" %>
					<%
						for (int i=0; i<roles.size(); i++)
						{
							out.print("<p>"+roles.get(i)+" : <a href='/my-app-0.0.1-SNAPSHOT/account/groupPage.jsp?gn="+roles.get(i)+"'>Zur Gruppenseite wechseln</a></p>");
						}
					%>
					</div>
					<div class="col-md-5">
					<p>Du bist Admin folgender Gruppen:</p>
					<%
						ArrayList <String> adminRoles = r.getUserAdminRoles();
						for(int i=0; i<adminRoles.size(); i++)
						{
							out.print("<p>"+adminRoles.get(i)+" : <a href='/my-app-0.0.1-SNAPSHOT/account/groupPage.jsp?gn="+adminRoles.get(i)+"'>Zur Adminseite wechseln</a></p>");
						}
					%>
				</div>
				<shiro:hasRole name="SysAdmin">
				<div class="col-md-5">
					<a href="/my-app-0.0.1-SNAPSHOT/account/adminPage.jsp">Zur Sys-Adminseite wechseln</a>
				</div>
				<div class="col-md-5">
					<p>Beginne mit dem <a href="/my-app-0.0.1-SNAPSHOT/account/tip.jsp">Tippen</a>.</p>
				</div>
				</shiro:hasRole>
				</shiro:user>
			</section>
		</article>
	</div>
</body>
</html>
