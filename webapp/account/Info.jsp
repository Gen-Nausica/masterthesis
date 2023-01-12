<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include.jsp" %>
<%@page import="Masterarbeit.my_app.Sources"  %>
<%@page import="Masterarbeit.my_app.SourceCheck"  %>
<%@page import="org.json.JSONObject" %>
<%@page import="org.json.JSONArray" %>
<%@page import="java.util.ArrayList" %>
<%@ include file="../include.jsp" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<%@page import="Masterarbeit.my_app.RoleCheck" %>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- style section -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
<!-- script section -->
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- eigenes Script -->
<script src="../js/info.js"></script>
<title>Infoseite</title>
</head>
<body>
<div id="infos" hidden>
<%
	Subject currentUser = SecurityUtils.getSubject();
	String p = (String) currentUser.getPrincipal();
	RoleCheck r = new RoleCheck();
	r.findUserRoles(p);
	ArrayList <String> roles = r.getUserRoles();

	String q = request.getParameter("q");
	String c = request.getParameter("c");
	String m= "";
	if(c.equals("actor"))
	{
		m = request.getParameter("m");
	}
	System.out.println("c: "+c);
	Sources s = new Sources();
	if(s.checkActuality("name.basics.tsv.gz"))
	{
			System.out.println("falsch");
			Thread t = new Thread(new SourceCheck());
			t.start();
		
	}
	JSONObject json = new JSONObject();
	JSONObject awards = new JSONObject();
	if(c.equals("film"))
	{
		json = s.getInformationMovie(q, c);
		awards = s.getAwards(q, c, "");
	}
	else
	{
		json = s.getInformationActor(q, c, m);
		awards = s.getAwards(q, c, m);
	}

	if(json.length()!=0 && c.equals("film"))
	{
		if(json.getString("content").equals("empty"))
		{
			out.println("Zu Ihrer Anfrage gibt es leider keine Informationen.");
		}
		else
		{
			out.println("<div id='title'>Originaltitel: "+json.get("Title")+"</div>");
			out.println("<div id='director'>"+json.get("Director")+"</div>");
			out.println("<div id='actors'>"+json.get("Actors")+"</div>");
			out.println("<div id='writer'>"+json.get("Writer")+"</div>");
			out.println("<div id='plot'>"+json.get("Plot")+"</div>");
			out.println("<div id='img'>"+json.get("Poster")+"</div>");
			out.println("<div class='rating'>");
			JSONArray arr = json.getJSONArray("Ratings");
			for (int i = 0; i < arr.length(); i++)
			{
				out.println("<div class='ratingName'>"+arr.getJSONObject(i).getString("Source")+"</div><div class='ratingVal'>"+arr.getJSONObject(i).getString("Value")+"</div>");
			}
			out.println("</div>");
			arr = json.getJSONArray("count");
			out.println("<div id='awardCount'>"+arr.getJSONObject(0).getString("Awards")+"</div>");
			out.println("<div id='nomCount'>"+arr.getJSONObject(1).getString("Nominations")+"</div>");
		}
		
	}
	if(json.length()!=0 && c.equals("actor"))
	{
		if(json.getString("content").equals("empty"))
		{
			out.println("Zu Ihrer Anfrage gibt es leider keine Informationen.");
		}
		else
		{
			out.println("<div id='movieName'>"+m+"</div>");
			out.println("<div id='title'>"+q+"</div>");
			out.println("<div id='primaryProf'>"+json.getString("primaryProf")+"</div>");
			JSONArray arr = json.getJSONArray("KnownForTitles");
			for (int i = 0; i < arr.length(); i++)
			{
				out.println("<div class='knownForTitles'>"+ arr.getJSONObject(i).getString("Name")+"</div>");
			}
			arr = json.getJSONArray("count");
			out.println("<div id='awardCount'>"+arr.getJSONObject(0).getString("Awards")+"</div>");
			out.println("<div id='nomCount'>"+arr.getJSONObject(1).getString("Nominations")+"</div>");
			out.println("<div id='img'>"+arr.getJSONObject(2).getString("Image")+"</div>");
			arr = json.getJSONArray("NomCount");
			out.println("<div id='movieNom'>"+arr.getJSONObject(1).getString("Nominations")+"</div>");
			arr = json.getJSONArray("Nominations");
			for (int i = 0; i < arr.length(); i++)
			{
				out.println("<div class='nominations'>"+ arr.getJSONObject(i).getString("Name")+"</div>");
			}
		}
		
	}
	
	if(awards.length()!=0)
	{
		if(json.getString("content").equals("empty"))
		{
			out.println("Zu Ihrer Anfrage gibt es leider keine Informationen.");
		}
		else
		{
			System.out.println(awards);
			JSONArray tmp = awards.getJSONArray("Awards");
			for(int i=0; i<tmp.length(); i++)
			{
				out.println("<div class='awards'>"+tmp.getJSONObject(i).getString("Name")+"</div>");
			}
		}
	
	}	
%>
</div><!-- end hidden infos -->
<div class="main">
		<div class="row desktop-header">
			<div class="col-xs-12 col-md-8 col-lg-8">
				<h1><%=q%></h1>
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
		<div class="row">
			<div class="col-sm-4 col-xs-12">
				<div class="image_container">
					<!-- image here -->
				</div>
				<div class="h2_container">
					<!-- Title or name here -->
				</div>
				<div class="plot">
					<!-- plot here -->
				</div>
			</div><!-- end col-sm-4 col-xs-12 -->
			<div class="col-sm-8">
				<div class="node firstNode">
					<!-- director or primary profession here -->
				</div>
				<div class="node secondNode">
					<!-- cast or movies here -->
				</div>
				<div class="node thirdNode">
					<!-- awards here -->
				</div>
				<div class="node forthNode">
					<!-- rating here -->
				</div>					
			</div><!-- end col-sm-8 -->
		</div><!-- end row -->
	</div><!-- end main -->
</body>
</html>