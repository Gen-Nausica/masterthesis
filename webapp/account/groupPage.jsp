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
<title>Gruppenseite</title>
</head>
<body>
<%@page import="Masterarbeit.my_app.Group" %>
<%@page import="Masterarbeit.my_app.User" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<%@page import="Masterarbeit.my_app.RoleCheck" %>
<%@page import="java.util.ArrayList" %>
<%
Subject currentUser = SecurityUtils.getSubject();
String p = (String) currentUser.getPrincipal();
RoleCheck r = new RoleCheck();
r.findUserRoles(p);
ArrayList <String> roles = r.getUserRoles();
%>
<%
	String gn = "";
	String gnAd = "";
	if(request.getParameter("gn") != null)
	{
		gn = request.getParameter("gn");
		if(gn.contains("Admin"))
		{
			gn= gn.split("Admin")[0];
		}
		gnAd = gn+"Admin";
	}
	else
	{
		gn = "";
	}
	
	String usr = "";
	String act = "";
	if(request.getParameter("usr") != null)
	{
		usr = request.getParameter("usr");
		Group group = new Group();
		if(request.getParameter("act") != null){
			act = request.getParameter("act");
		}
		if(act.equals("remove"))
		{
			group.setGroupname(gn);
			group.setUsername(usr);
			group.removeUserFromGroup();
		}
		else if(act.equals("add"))
		{
			group.setGroupname(gn);
			group.setUsername(usr);
			group.addUserToGroupAdmin();
		}
		
	}
%>
<div class="main">
		<div class="row desktop-header">
			<div class="col-xs-12 col-md-8 col-lg-8">
				<h1>Gruppenseite</h1>
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
					<h3>Das hier ist das Dashboard der Gruppe <%=gn%></h3>
					<p>Das hier sind die Mitglieder der Gruppe: </p>
					<ul>
						<%
							Group group = new Group();
							String [] users = group.getUsersOfGroup(gn);
							for(int i=0; i<users.length; i++)
							{
								//Parameter Username und Remove hinzufügen, bei Class User removeUser hinzufügen, Registerservlet erweitern, oder neues ManageUserServlet erstellen
								out.print("<li id='"+users[i]+"'>"+users[i]+"</li>");
							}
						%>
					</ul>
					<shiro:hasRole name="<%=gnAd%>"><input type="button" class="removeUser submit__btn" value="User entfernen"/></shiro:hasRole>
					<shiro:hasRole name="<%=gnAd%>"><input type="button" class="addUser submit__btn" value="User manuell hinzufügen"/></shiro:hasRole>
					<shiro:hasRole name="<%=gnAd%>"><div class = "addRemoveUser"></div></shiro:hasRole>
					<div class="ranking form__row">
						<h3>So ist das aktuelle Ranking: </h3>
						<ol>
						<%
							User user = new User();
							ArrayList <ArrayList <String>> points = user.getAllPoints();
							for(int i=0; i<points.size(); i++)
							{
								for(int j=0; j<users.length; j++)
								{
									if(users[j].equals(points.get(i).get(0)))
									{
										out.println("<li>"+points.get(i).get(0)+" hat "+points.get(i).get(1)+" Punkte.");
									}
								}
								
							}
							
						%>
						</ol>
					</div>
					<div class="ranking form__row">
						<h3>Diese Nutzer haben schon getippt: </h3>
						<ul>
						<%
							ArrayList <String> tmp = new ArrayList <String>();
							for(int i=0; i<points.size(); i++)
							{
								tmp.add(points.get(i).get(0));
							}

							ArrayList <String> tippedUsers = group.getUsersTipped(tmp);
							for(int i=0; i<tippedUsers.size(); i++)
							{
								out.println("<li>"+tippedUsers.get(i));
							}
						%>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>