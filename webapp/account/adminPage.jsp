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
<title>AdminPage</title>
</head>
<body>
<%@page import="Masterarbeit.my_app.Group" %>
<%@page import="Masterarbeit.my_app.User" %>
<%@page import="java.util.ArrayList" %>
<%@page import= "Masterarbeit.my_app.Nominees" %>
<shiro:hasRole name="SysAdmin">
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
			<div class="col-xs-12 col-md-8 col-lg-8">
				<h1>Hier sind deine Admin-Funktionalitäten</h1>
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
					<div class="form__row">
						<input type="button" class="readTips submit__btn" value="Tipps auslesen"/>
						<input type="button" class="resetAll submit__btn" value="resetAll"/>
					</div>
					<div class="form__row">
						<h3>Das hier sind die vorhandenen Gruppen: </h3>
					<ul>
<%
	if(request.getParameter("final") != null)
	{
		String fi = request.getParameter("final");
		if(fi.equals("readTips"))
		{
			System.out.println("Im richtigen if");
			Nominees nom = new Nominees();
			nom.findWinners();
			System.out.println(nom.rateTipps());
		}
	}
	Group group = new Group();
	String gn = "";
	String gpwd = "";
	if(request.getParameter("gn") != "")
	{
		gn = request.getParameter("gn");
		if (request.getParameter("gpwd") != null)
		{
			gpwd = request.getParameter("gpwd");
		}
	}
	String act = "";
	if(request.getParameter("act") != null)
	{
		act = request.getParameter("act");

		if(act.equals("remove"))
		{
			group.deleteGroup(gn);
		}
		else if(act.equals("add"))
		{
			System.out.println("Groupname: "+gn+" Passwort: "+gpwd);
			group.setGroupname(gn);
			group.setGrouppassword(gpwd);
			group.setGroupadmin("Jenny");
			group.createGroup();
		}
		
	}

	ArrayList <String> groups = group.getAllGroups();
	for(int i=0; i<groups.size(); i++)
	{
		//Parameter Username und Remove hinzufügen, bei Class User removeUser hinzufügen, Registerservlet erweitern, oder neues ManageUserServlet erstellen
		out.print("<li id='"+groups.get(i)+"'>"+groups.get(i)+"</li>");
	}
%>
					</ul>
					<input type="button" class="removeGroup submit__btn" value="Gruppe entfernen"/>
					<input type="button" class="addGroup submit__btn" value="Gruppe hinzufügen"/>
					<div class="addRemoveGroup"></div>
				</div>
					<div class="form__row">
						<h3>Das hier sind die vorhandenen User: </h3>
						<ul>
<%
	User user = new User();
	String un = new String("");
	if(request.getParameter("un") != "")
	{
		un = request.getParameter("un");
	}
	String uAct = "";
	if(request.getParameter("uAct") != null)
	{
		uAct = request.getParameter("uAct");

		if(uAct.equals("remove"))
		{
			user.removeUser(un);
		}
		else if(uAct.equals("add"))
		{
			System.out.println("Username: "+un);
			user.setUsername(un);
			user.setPassword("12345");
			user.createUser();
		}
		
	}

	ArrayList <String> users = user.getAllUsers();
	for(int i=0; i<users.size(); i++)
	{
		//Parameter Username und Remove hinzufügen, bei Class User removeUser hinzufügen, Registerservlet erweitern, oder neues ManageUserServlet erstellen
		out.print("<li id='"+users.get(i)+"'>"+users.get(i)+"</li>");
	}
%>
						</ul>
							<input type="button" class="globalRemoveUser submit__btn" value="User entfernen"/>
							<input type="button" class="globalAddUser submit__btn" value="User hinzufügen"/>
							<div class="addRemoveUser"></div>	
						</div>
						<div class="ranking form__row">
							<h3>So ist das aktuelle Ranking: </h3>
							<ol>
<%
	ArrayList <ArrayList <String>> points = user.getAllPoints();
	for(int i=0; i<points.size(); i++)
	{
		out.println("<li>"+points.get(i).get(0)+" hat "+points.get(i).get(1)+" Punkte.");
	}
	
%>
				</ol>
			</div>
		</div>
	</div>
</div>
</shiro:hasRole>

</body>
</html>