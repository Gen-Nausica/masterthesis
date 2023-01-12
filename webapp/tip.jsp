<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/my-app-0.0.1-SNAPSHOT/js/tip.js"></script>
<title>Tipping</title>
</head>
<body>
<%@page import="Masterarbeit.my_app.Nominees" %>
<%@page import="java.util.ArrayList" %>
<%
	Nominees nom = new Nominees();
	String username = SecurityUtils.getSubject().getPrincipal().toString();
	request.setCharacterEncoding("UTF-8");
	ArrayList <String> userTips = new ArrayList <String> (24);
	for(int i=0; i<24; i++)
	{
		userTips.add("");
	}
	if(request.getParameter("submit") != null)
	{
		String[] tips = new String[24];
		tips[0] = request.getParameter("BestPicture");
		tips[1] = request.getParameter("ActorLeading");
		tips[2] = request.getParameter("ActressLeading");
		tips[3] = request.getParameter("ActorSupporting");
		tips[4] = request.getParameter("ActressSupporting");
		tips[5] = request.getParameter("AnimatedFeature");
		tips[6] = request.getParameter("Cinematography");
		tips[7] = request.getParameter("Costume");
		tips[8] = request.getParameter("Directing");
		tips[9] = request.getParameter("Documentary");
		tips[10] = request.getParameter("DocumentaryShort");
		tips[11] = request.getParameter("FilmEditing");
		tips[12] = request.getParameter("ForeignFilm");
		tips[13] = request.getParameter("Makeup");
		tips[14] = request.getParameter("Score");
		tips[15] = request.getParameter("Song");
		tips[16] = request.getParameter("ProductionDesign");
		tips[17] = request.getParameter("ShortFilmAnim");
		tips[18] = request.getParameter("ShortFilm");
		tips[19] = request.getParameter("SoundEditing");
		tips[20] = request.getParameter("SoundMixing");
		tips[21] = request.getParameter("VisualEffects");
		tips[22] = request.getParameter("WritingAdap");
		tips[23] = request.getParameter("WritingOrig");
	    username= request.getParameter("username");
		
		nom.fillTips(tips, username);
	}
	ArrayList <String> tmp = nom.getTips(username);
	System.out.println(tmp.size());
	if(tmp.size() == 24)
	{
		userTips = tmp;
		System.out.println("Usertips size: "+tmp.size());
	}
	else
	{
		System.out.println("Waruuuuum????");
	}

%>
	<p>Hi <shiro:user><shiro:principal/></shiro:user>!</p>
	<div>
		<p>Hier kannst du deine Tipps abgeben:</p>
	</div>
	<div>
	<form action="" method="post" name="tippform">
		<div>
			<h2>Bester Film</h2>
				<%
					for(int i=0; i<nom.getBestPicture().size(); i++)
					{
						out.println("<div>");
						ArrayList <String> film = nom.getBestPicture().get(i);
						if(userTips.get(0).equals(film.get(0)+"_"+film.get(1)))
						{
							out.println("<input type='checkbox' class='bestPicture' name='BestPicture' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
						}
						else
						{
							out.println("<input type='checkbox' class='bestPicture' name='BestPicture' value='"+film.get(0)+"_"+film.get(1)+"'/>");
						}
						out.println("<label for='"+i+"'>");
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
						for(int j=1; j<film.size(); j++)
						{
							out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
						}
						out.println("</label>");
						out.println("</div>");
					}
				%>
			</div>
			<div>
				<h2>Bester Hauptdarsteller</h2>
				<%
					for(int i=0; i<nom.getActorLeading().size(); i++)
					{
						out.println("<div>");
						ArrayList <String> film = nom.getActorLeading().get(i);
						if(userTips.get(1).equals(film.get(0)+"_"+film.get(1)))
						{
							out.println("<input type='checkbox' class='ActorLeading' name='ActorLeading' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
						}
						else
						{
							out.println("<input type='checkbox' class='ActorLeading' name='ActorLeading' value='"+film.get(0)+"_"+film.get(1)+"'/>");
						}
						out.println("<label for='"+i+"'>");
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
						for(int j=1; j<film.size(); j++)
						{
							out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
						}
						out.println("</label>");
						out.println("</div>");
					}
				%>
			</div>
			<div>
				<h2>Beste Hauptdarstellerin</h2>
				<%
				for(int i=0; i<nom.getActressLeading().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getActressLeading().get(i);
					System.out.println("Film 1 "+film.get(0));
					System.out.println("Länge "+film.size());
					if(userTips.get(2).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='ActressLeading' name='ActressLeading' value='"+film.get(0)+"_"+film.get(1)+"'checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='ActressLeading' name='ActressLeading' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester Nebendarsteller</h2>
				<%
				for(int i=0; i<nom.getActorSupporting().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getActorSupporting().get(i);
					System.out.println("Tipp: "+userTips.get(3)+", Film_:"+film.get(0)+"_"+film.get(1));
					if(userTips.get(3).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='ActorSupporting' name='ActorSupporting' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='ActorSupporting' name='ActorSupporting' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>	
			</div>
			<div>
				<h2>Beste Nebendarstellerin</h2>
				<%
				for(int i=0; i<nom.getActressSupporting().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getActressSupporting().get(i);
					if(userTips.get(4).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='ActressSupporting' name='ActressSupporting' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='ActressSupporting' name='ActressSupporting' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester Animationsfilm</h2>
				<%
				for(int i=0; i<nom.getAnimatedFeature().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getAnimatedFeature().get(i);
					System.out.println("UserTips: "+userTips.get(5)+", Film: "+film.get(0)+"_"+film.get(1));
					if(userTips.get(5).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='AnimatedFeature' name='AnimatedFeature' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='AnimatedFeature' name='AnimatedFeature' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Beste Kameraarbeit</h2>
				<%
				for(int i=0; i<nom.getCinematography().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getCinematography().get(i);
					if(userTips.get(6).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='cinematography' name='Cinematography' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='cinematography' name='Cinematography' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Kostüm</h2>
				<%
				for(int i=0; i<nom.getCostume().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getCostume().get(i);
					if(userTips.get(7).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='costume' name='Costume' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='costume' name='Costume' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Beste Regie</h2>
				<%
				for(int i=0; i<nom.getDirecting().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getDirecting().get(i);
					if(userTips.get(8).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='directing' name='Directing' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='directing' name='Directing' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester Dokumentarfilm</h2>
				<%
				for(int i=0; i<nom.getDocumentary().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getDocumentary().get(i);
					if(userTips.get(9).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='documentary' name='Documentary' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='documentary' name='Documentary' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester Dokumentarfilm kurz</h2>
				<%
				for(int i=0; i<nom.getDocumentaryShort().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getDocumentaryShort().get(i);
					if(userTips.get(10).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='documentaryShort' name='DocumentaryShort' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='documentaryShort' name='DocumentaryShort' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bestes Filmediting</h2>
				<%
				for(int i=0; i<nom.getFilmEditing().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getFilmEditing().get(i);
					if(userTips.get(11).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='filmEditing' name='FilmEditing' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='filmEditing' name='FilmEditing' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester fremdsprachiger Film</h2>
				<%
				for(int i=0; i<nom.getForeign().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getForeign().get(i);
					if(userTips.get(12).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='foreign' name='ForeignFilm' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='foreign' name='ForeignFilm' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bestes Makeup</h2>
				<%
				for(int i=0; i<nom.getMakeup().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getMakeup().get(i);
					if(userTips.get(13).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='makeup' name='Makeup' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='makeup' name='Makeup' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester Soundtrack</h2>
				<%
				for(int i=0; i<nom.getScore().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getScore().get(i);
					if(userTips.get(14).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='score' name='Score' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='score' name='Score' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester Song</h2>
				<%
				for(int i=0; i<nom.getSong().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getSong().get(i);
					if(userTips.get(15).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='song' name='Song' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='song' name='Song' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bestes Szenenbild</h2>
				<%
				for(int i=0; i<nom.getProductionDesign().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getProductionDesign().get(i);
					if(userTips.get(16).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='productionDesign' name='ProductionDesign' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='productionDesign' name='ProductionDesign' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester animierter Kurzfilm</h2>
				<%
				for(int i=0; i<nom.getShortFilmAnim().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getShortFilmAnim().get(i);
					if(userTips.get(17).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='shortFilmAnim' name='ShortFilmAnim' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='shortFilmAnim' name='ShortFilmAnim' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bester Kurzfilm</h2>
				<%
				for(int i=0; i<nom.getShortFilm().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getShortFilm().get(i);
					if(userTips.get(18).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='shortFilm' name='ShortFilm' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='shortFilm' name='ShortFilm' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bestes Soundediting</h2>
				<%
				for(int i=0; i<nom.getSoundEditing().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getSoundEditing().get(i);
					if(userTips.get(19).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='soundEditing' name='SoundEditing' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='soundEditing' name='SoundEditing' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bestes Soundmixing</h2>
				<%
				for(int i=0; i<nom.getSoundMixing().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getSoundMixing().get(i);
					if(userTips.get(20).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='soundMixing' name='SoundMixing' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='soundMixing' name='SoundMixing' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Visual Effects</h2>
				<%
				for(int i=0; i<nom.getVisualEffects().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getVisualEffects().get(i);
					if(userTips.get(21).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='visualEffects' name='VisualEffects' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='visualEffects' name='VisualEffects' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bestes adaptiertes Drehbuch</h2>
				<%
				for(int i=0; i<nom.getWritingAdap().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getWritingAdap().get(i);
					System.out.println("Tipp: "+userTips.get(21)+", Film_:"+film.get(0)+"_"+film.get(1));
					if(userTips.get(22).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='writingAdap' name='WritingAdap' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='writingAdap' name='WritingAdap' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
			<div>
				<h2>Bestes Originaldrehbuch</h2>
				<%
				for(int i=0; i<nom.getWritingOrig().size(); i++)
				{
					out.println("<div>");
					ArrayList <String> film = nom.getWritingOrig().get(i);
					System.out.println("Tipp: "+userTips.get(22)+", Film_:"+film.get(0)+"_"+film.get(1));
					if(userTips.get(23).equals(film.get(0)+"_"+film.get(1)))
					{
						out.println("<input type='checkbox' class='writingOrig' name='WritingOrig' value='"+film.get(0)+"_"+film.get(1)+"' checked/>");
					}
					else
					{
						out.println("<input type='checkbox' class='writingOrig' name='WritingOrig' value='"+film.get(0)+"_"+film.get(1)+"'/>");
					}
					out.println("<label for='"+i+"'>");
					out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(0)+"'><b>"+film.get(0)+"</b></a>");
					for(int j=1; j<film.size(); j++)
					{
						out.println("<a href='/my-app-0.0.1-SNAPSHOT/account/Info.jsp?q="+film.get(j)+"'>"+film.get(j)+"</a>");
					}
					out.println("</label>");
					out.println("</div>");
				}
				%>
			</div>
				<input type="hidden" name="username" value="<shiro:principal/>"/>
				<input type="submit" name="submit" value="Tipps speichern"/>
			</form>
		</div>
	</div>
</body>
</html>