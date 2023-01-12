<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<script src="../js/proto.js"></script>
<%@ include file="../include.jsp" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<%@page import="Masterarbeit.my_app.RoleCheck" %>
<%@page import="java.util.ArrayList" %>
<title>Prototyp für Graph</title>
</head>
<body>
<%
	Subject currentUser = SecurityUtils.getSubject();
	String p = (String) currentUser.getPrincipal();
	RoleCheck r = new RoleCheck();
	r.findUserRoles(p);
	ArrayList <String> roles = r.getUserRoles();
%>
	<div class="main">
		<div class="row desktop-header">
			<div class="col-xs-12 col-md-3 col-lg-4">
				<h1>Das Oscar-Tippspiel</h1>
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
		</nav>
		<div class="row">
			<div class="col-sm-4 col-xs-12">
				<div class="image_container">
					<img class="poster_img" src="https://m.media-amazon.com/images/M/MV5BNmE5ZmE3OGItNTdlNC00YmMxLWEzNjctYzAwOGQ5ODg0OTI0XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg">
				</div>
				<div class="h2_container">
					<h2>A Star Is Born</h2>
				</div>
				<div class="plot">
					<p>A musician helps a young singer find fame as age and alcoholism send his own career into a downward spiral.</p>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="node firstNode">
					<div class="smallNode expand">
						<p>Regisseur (1)</p>
					</div>
					<div class="secondLevel">
						<p class="odd subcat--small link" id="1">Bradley Cooper</p>
					</div>
				</div>
				<div class="node secondNode">
					<div class="biggerNode expand">
						<p>Cast (4)</p>
					</div>
					<div class="secondLevel">
						<p class="odd subcat--bigger link">Lady Gaga</p>
						<p class="even subcat--bigger link">Bradley Cooper</p>
						<p class="odd subcat--bigger link">Sam Elliott</p>
						<p class="even subcat--bigger link">Andrew Dice Clay</p>
					</div>
				</div>
				<div class="node thirdNode">
					<div class="bigNode expand">
						<p>Preise (66)</p>
						<p></p>
					</div>
					<div class="secondLevel">
						<div class="element">
							<div class="odd subcat--big link">Preis1</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis2</div>
						</div>
						<div class="element">
							<div class="odd subNode bigNode subcat--big subNodeExpand">Oscar</div>
							<div class="thirdLevel">
								<p class="subCat subcat--small link">Best Picture</p>
							</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis3</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis4</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis5</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis6</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis7</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis8</div>
						</div>
						<div class="element">
							<div class="even subNode middleNode subcat--big subNodeExpand">Preis9</div>
							<div class="thirdLevel">
								<p class="subCat subcat--small link">Waaahnsinnig guter Film</p>
							</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis10</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis11</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis12</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis13</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis14</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis15</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis16</div>
						</div>
						<div class="element">
							<div class="even subcat--big link">Preis17</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis18</div>
						</div>
						<div class="element">
							<div class="even subNode smallNode subcat--big subNodeExpand">Preis19</div>
							<div class="thirdLevel">
								<p class="subCat subcat--small link">tolle Schauspieler!!!!!</p>
							</div>
						</div>
						<div class="element">
							<div class="odd subcat--big link">Preis20</div>
						</div>
					</div>
				</div>
				<div class="node forthNode">
					<div class="middleNode expand">
						<p>Rating (3)</p>
					</div>
					<div class="secondLevel">
						<div class="element">
							<div class="odd subNode smallNode subNodeExpand subcat--middle">Rotten Tomatoes
							</div>
							<div class="thirdLevel">
								<p class="subCat subcat--small link">88%</p>
							</div>
						</div>
						<div class="element">
							<div class="even subNode smallNode subNodeExpand subcat--middle">IMDB
							</div>
							<div class="thirdLevel">
								<p class="subCat subcat--small link">89%</p>
							</div>
						</div>
						<div class="element">
							<div class="odd subNode smallNode subNodeExpand subcat--middle">Metacritic
							</div>
							<div class="thirdLevel">
								<p class="subCat subcat--small link">78%</p>
							</div>
						</div>
					</div>
				</div>					
			</div>
			<div class="col-sm-4">
			</div>
		</div>
	</div>
</body>
</html>