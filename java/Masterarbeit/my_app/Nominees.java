package Masterarbeit.my_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class Nominees 
{
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	
	private ArrayList <ArrayList<String>> bestPicture;
	private ArrayList <ArrayList<String>> actorLeading;
	private ArrayList <ArrayList<String>> actressLeading;
	private ArrayList <ArrayList<String>> actorSupporting;
	private ArrayList <ArrayList<String>> actressSupporting;
	private ArrayList <ArrayList<String>> animatedFeature;
	private ArrayList <ArrayList<String>> cinematography;
	private ArrayList <ArrayList<String>> costume;
	private ArrayList <ArrayList<String>> directing;
	private ArrayList <ArrayList<String>> documentary;
	private ArrayList <ArrayList<String>> documentaryShort;
	private ArrayList <ArrayList<String>> filmEditing;
	private ArrayList <ArrayList<String>> foreignFilm;
	private ArrayList <ArrayList<String>> makeup;
	private ArrayList <ArrayList<String>> score;
	private ArrayList <ArrayList<String>> song;
	private ArrayList <ArrayList<String>> productionDesign;
	private ArrayList <ArrayList<String>> shortFilmAnim;
	private ArrayList <ArrayList<String>> shortFilm;
	private ArrayList <ArrayList<String>> soundEditing;
	private ArrayList <ArrayList<String>> soundMixing;
	private ArrayList <ArrayList<String>> visualEffects;
	private ArrayList <ArrayList<String>> writingAdap;
	private ArrayList <ArrayList<String>> writingOrig;
	private ArrayList <String> categories;
	private ArrayList <ArrayList <String>> winners;
	private static final Logger log = LoggerFactory.getLogger(User.class);
	
	public Nominees () throws IOException, SQLException 
	{
		this.bestPicture = new ArrayList <ArrayList <String>> ();
		this.actorLeading = new ArrayList <ArrayList<String>> ();
		this.actressLeading = new ArrayList <ArrayList<String>> ();
		this.actorSupporting = new ArrayList <ArrayList<String>> ();
		this.actressSupporting = new ArrayList <ArrayList<String>> ();
		this.animatedFeature = new ArrayList <ArrayList<String>> ();
		this.cinematography = new ArrayList <ArrayList<String>> ();
		this.costume = new ArrayList <ArrayList<String>> ();
		this.directing = new ArrayList <ArrayList<String>> ();
		this.documentary = new ArrayList <ArrayList<String>> ();
		this.documentaryShort = new ArrayList <ArrayList<String>> ();
		this.filmEditing = new ArrayList <ArrayList<String>> ();
		this.foreignFilm = new ArrayList <ArrayList<String>> ();
		this.makeup = new ArrayList <ArrayList<String>> ();
		this.score = new ArrayList <ArrayList<String>> ();
		this.song = new ArrayList <ArrayList<String>> ();
		this.productionDesign = new ArrayList <ArrayList<String>> ();
		this.shortFilmAnim = new ArrayList <ArrayList<String>> ();
		this.shortFilm = new ArrayList <ArrayList<String>> ();
		this.soundEditing = new ArrayList <ArrayList<String>> ();
		this.soundMixing = new ArrayList <ArrayList<String>> ();
		this.visualEffects = new ArrayList <ArrayList<String>> ();
		this.writingAdap = new ArrayList <ArrayList<String>> ();
		this.writingOrig = new ArrayList <ArrayList<String>> ();
		this.winners = new ArrayList <ArrayList <String>> ();
		this.categories = new ArrayList <String> ();		
		String [] c = new String[] {"Bester Film", "Bester Hauptdarsteller", "Beste Hauptdarstellerin", "Bester Nebendarsteller", "Beste Nebendarstellerin", "Bester Animationsfilm", "Beste Kameraarbeit", "Kostüm", "Beste Regie", "Bester Dokumentarfilm", "Bester Dokumentarfilm kurz", "Bestes Filmediting", "Bester fremdsprachiger Film", "Bestes Makeup", "Bester Soundtrack", "Bester Song", "Bestes Produktionsdesign", "Bester animierter Kurzfilm", "bester Kurzfilm", "Bestes Soundediting", "Bestes Soundmixing", "Bestes adaptiertes Drehbuch", "Bestes Originaldrehbuch", "Visual Effects"};
		for(int i=0; i<c.length; i++)
		{
			this.categories.add(c[i]);
		}
		if(getNominees())
		{
			log.info("Arrays erfolgreich mit Nominierten befüllt.");
		}
		else
		{
			log.info("Arrays konnten nicht befüllt werden.");
		}
		
		this.dataSource = new MysqlDataSource();
		dataSource.setUser("jenny");
		dataSource.setPassword("jenny");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("masterarbeit");
		this.conn = dataSource.getConnection();
		this.stmt = conn.createStatement();
		
	}

	public ArrayList<ArrayList <String>> getWinners() 
	{
		return winners;
	}

	public void setWinners(ArrayList<ArrayList <String>> winners, boolean b) throws SQLException 
	{
		this.winners = winners;
		if(winners.size() == 24 && b)
		{
			log.info("Winners werden in DB eingetragen");
			StringBuilder t = new StringBuilder();
			for(int i=0;i<winners.size(); i++)
			{
				if(i<winners.size()-1)
				{
					t.append("'"+winners.get(i).get(0)+"_"+winners.get(i).get(1)+"',");
				}
				else
				{
					t.append("'"+winners.get(i).get(0)+"_"+winners.get(i).get(1)+"'");
				}
			}
			String query = "INSERT INTO winners (bestPicture, actorLeading, actressLeading, actorSupporting, actressSupporting, animatedFeature, cinematography, costume, directing, documentary, documentaryShort, filmEditing, foreignFilm, makeup, score, song, productionDesign, shortFilmAnim, shortFilm, soundEditing, soundMixing, visualEffects, writingAdap, writingOrig)  VALUES ("+t.toString()+");";
			stmt.executeUpdate(query);
			log.info("Winners in DB eingetragen");
		}
		else
		{
			log.info("Winners werden nicht in DB geschrieben.");
		}
	}

	public ArrayList <ArrayList <String>> getBestPicture() 
	{
		return bestPicture;
	}

	public void setBestPicture(ArrayList <ArrayList <String>> bestPicture) 
	{
		this.bestPicture = bestPicture;
	}

	public ArrayList <ArrayList<String>> getActorLeading() 
	{
		return actorLeading;
	}

	public void setActorLeading(ArrayList <ArrayList<String>> actorLeading) 
	{
		this.actorLeading = actorLeading;
	}

	public ArrayList <ArrayList<String>> getActressLeading() 
	{
		return actressLeading;
	}

	public void setActressLeading(ArrayList <ArrayList<String>> actressLeading) 
	{
		this.actressLeading = actressLeading;
	}

	public ArrayList <ArrayList<String>> getActorSupporting() 
	{
		return actorSupporting;
	}

	public void setActorSupporting(ArrayList <ArrayList<String>> actorSupporting) 
	{
		this.actorSupporting = actorSupporting;
	}

	public ArrayList <ArrayList<String>> getActressSupporting() 
	{
		return actressSupporting;
	}

	public void setActressSupporting(ArrayList <ArrayList<String>> actressSupporting) 
	{
		this.actressSupporting = actressSupporting;
	}

	public ArrayList <ArrayList<String>> getAnimatedFeature() 
	{
		return animatedFeature;
	}

	public void setAnimatedFeature(ArrayList <ArrayList<String>> animatedFeature) 
	{
		this.animatedFeature = animatedFeature;
	}

	public ArrayList <ArrayList<String>> getCinematography() 
	{
		return cinematography;
	}

	public void setCinematography(ArrayList <ArrayList<String>> cinematography) 
	{
		this.cinematography = cinematography;
	}

	public ArrayList <ArrayList<String>> getCostume() 
	{
		return costume;
	}

	public void setCostume(ArrayList <ArrayList<String>> costume) 
	{
		this.costume = costume;
	}

	public ArrayList <ArrayList<String>> getDirecting() 
	{
		return directing;
	}

	public void setDirecting(ArrayList <ArrayList<String>> directing) 
	{
		this.directing = directing;
	}

	public ArrayList <ArrayList<String>> getDocumentary() 
	{
		return documentary;
	}

	public void setDocumentary(ArrayList <ArrayList<String>> documentary) 
	{
		this.documentary = documentary;
	}

	public ArrayList <ArrayList<String>> getDocumentaryShort() 
	{
		return documentaryShort;
	}

	public void setDocumentaryShort(ArrayList <ArrayList<String>> documentaryShort) 
	{
		this.documentaryShort = documentaryShort;
	}

	public ArrayList <ArrayList<String>> getFilmEditing() 
	{
		return filmEditing;
	}

	public void setFilmEditing(ArrayList <ArrayList<String>> filmEditing) 
	{
		this.filmEditing = filmEditing;
	}

	public ArrayList <ArrayList<String>> getForeign() 
	{
		return foreignFilm;
	}

	public void setForeign(ArrayList <ArrayList<String>> foreign) 
	{
		this.foreignFilm = foreign;
	}

	public ArrayList <ArrayList<String>> getMakeup() 
	{
		return makeup;
	}

	public void setMakeup(ArrayList <ArrayList<String>> makeup) 
	{
		this.makeup = makeup;
	}

	public ArrayList <ArrayList<String>> getScore() 
	{
		return score;
	}

	public void setScore(ArrayList <ArrayList<String>> score) 
	{
		this.score = score;
	}

	public ArrayList <ArrayList<String>> getSong() 
	{
		return song;
	}

	public void setSong(ArrayList <ArrayList<String>> song) 
	{
		this.song = song;
	}

	public ArrayList <ArrayList<String>> getProductionDesign() 
	{
		return productionDesign;
	}

	public void setProductionDesign(ArrayList <ArrayList<String>> productionDesign) 
	{
		this.productionDesign = productionDesign;
	}

	public ArrayList <ArrayList<String>> getShortFilmAnim() 
	{
		return shortFilmAnim;
	}

	public void setShortFilmAnim(ArrayList <ArrayList<String>> shortFilmAnim) 
	{
		this.shortFilmAnim = shortFilmAnim;
	}

	public ArrayList <ArrayList<String>> getShortFilm() 
	{
		return shortFilm;
	}

	public void setShortFilm(ArrayList <ArrayList<String>> shortFilm) 
	{
		this.shortFilm = shortFilm;
	}

	public ArrayList <ArrayList<String>> getSoundEditing() 
	{
		return soundEditing;
	}

	public void setSoundEditing(ArrayList <ArrayList<String>> soundEditing) 
	{
		this.soundEditing = soundEditing;
	}

	public ArrayList <ArrayList<String>> getSoundMixing() 
	{
		return soundMixing;
	}

	public void setSoundMixing(ArrayList <ArrayList<String>> soundMixing) 
	{
		this.soundMixing = soundMixing;
	}

	public ArrayList <ArrayList<String>> getVisualEffects() 
	{
		return visualEffects;
	}

	public void setVisualEffects(ArrayList <ArrayList<String>> visualEffects) 
	{
		this.visualEffects = visualEffects;
	}

	public ArrayList <ArrayList<String>> getWritingAdap() 
	{
		return writingAdap;
	}

	public void setWritingAdap(ArrayList <ArrayList<String>> writingAdap) 
	{
		this.writingAdap = writingAdap;
	}

	public ArrayList <ArrayList<String>> getWritingOrig() 
	{
		return writingOrig;
	}

	public void setWritingOrig(ArrayList <ArrayList<String>> writingOrig) 
	{
		this.writingOrig = writingOrig;
	}

	public ArrayList <String> getCategories() 
	{
		return categories;
	}

	public void setCategories(ArrayList <String> categories) 
	{
		this.categories = categories;
	}
	
	public boolean getNominees() throws IOException
	{
		URL url = new URL("https://de.wikipedia.org/api/rest_v1/page/html/Oscarverleihung_2019");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Api-User-Agent", "pfeifferjenny89@gmail.com");
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		if(content.length() != 0) 
		{
			//System.out.println(content.toString());
			Document doc = Jsoup.parse(content.toString());
			
			//Best Picture
			Elements els = doc.select("[data-mw-section-id=\"2\"]");
			Elements noms = els.select("li");
			Elements noms2 = els.select("b");
			ArrayList <String> w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			ArrayList<ArrayList <String>> list = new ArrayList <ArrayList <String>> ();
			list.add(w);
			Elements tmp;
			for (int i=0; i<noms.size(); i++)
			{
				System.out.println("i: "+i);
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					System.out.println("Inhalt: "+tmp.get(j).text());
					a.add(tmp.get(j).text().toString());
				}
				list.add(a);
			}
			if(list != null)
			{
				setBestPicture(list);
			}

			
			//Best Actor
			els = doc.select("[data-mw-section-id=\"4\"]");
			noms = els.select("li");
			list = new ArrayList <ArrayList <String>>();
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setActorLeading(list);
			
			//Best Actress
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"5\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setActressLeading(list);
			
			//Best ActorSupporting
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"6\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setActorSupporting(list);
			
			//Best ActressSupporting
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"7\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setActressSupporting(list);
			
			//Best Animated Feature
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"20\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text().toString());
				}
				list.add(a);
			}
			setAnimatedFeature(list);
			
			
			//Beste Kamera
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"10\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setCinematography(list);
			
			//Bestes Kostüm
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"12\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setCostume(list);
			
			//Beste Regie
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"3\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);

			}
			setDirecting(list);
			
			//Bester Dokumentarfilm
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"23\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setDocumentary(list);
			
			//Bester Dokumentarfilm kurz
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"24\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setDocumentaryShort(list);
			
			//Bestes Filmediting
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"16\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setFilmEditing(list);
			
			//Bester fremdsprachiger Film
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"25\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setForeign(list);
			
			//Bestes Makeup
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"15\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setMakeup(list);
			
			//Bester Soundtrack
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"13\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setScore(list);
			
			//Bester Song
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"14\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setSong(list);
			
			//Bestes Produktionsdesign
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"11\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setProductionDesign(list);
			
			//Bester anim Kurzfilm
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"21\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setShortFilmAnim(list);
			
			//Bester Kurzfilm
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"22\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setShortFilm(list);
			
			//Bestes Soundediting
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"17\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setSoundEditing(list);
			
			//Bestes Soundmixing
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"18\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setSoundMixing(list);
			
			//Bestes adaptiertes Drehbuch
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"8\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setWritingAdap(list);
			
			//Bestes original Drehbuch
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"9\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setWritingOrig(list);
			
			//Beste Visual Effects
			list = new ArrayList <ArrayList <String>>();
			els = doc.select("[data-mw-section-id=\"19\"]");
			noms = els.select("li");
			noms2 = els.select("b");
			w = new ArrayList <String>();
			noms2 = noms2.get(0).select("a");
			for(int k=0; k<noms2.size(); k++)
			{
				w.add(noms2.get(k).text().toString());
			}
			list.add(w);
			for (int i=0; i<noms.size(); i++)
			{
				tmp = noms.get(i).select("a");
				ArrayList <String> a = new ArrayList <String>();
				for(int j=0; j<tmp.size(); j++)
				{
					a.add(tmp.get(j).text());
				}
				list.add(a);
			}
			setVisualEffects(list);
			
			con.disconnect();
		}
		else
		{
			System.out.println("Dokument leer");
			con.disconnect();
		}

		return true;
	}
	
	public void fillTips(String [] film, String username) throws SQLException
	{
		if(username == "" || isEmpty(film)) 
		{
			log.debug("Username or Tipps didn't contain any items.");
		}
		else
		{
			//Check if user already tipped before, if yes, delete
			String checkquery = "SELECT * FROM tipps WHERE username='"+username+"';";
			ResultSet re = stmt.executeQuery(checkquery);
			if(re.next())
			{
				checkquery = "DELETE FROM tipps WHERE username='"+username+"';";
				stmt.executeUpdate(checkquery);
			}
			
			StringBuilder t = new StringBuilder();
			for(int i=0;i<film.length; i++)
			{
				if(i<film.length-1)
				{
					t.append("'"+film[i]+"',");
				}
				else
				{
					t.append("'"+film[i]+"'");
				}
			}
			String query = "INSERT INTO tipps (username, bestPicture, actorLeading, actressLeading, actorSupporting, actressSupporting, animatedFeature, cinematography, costume, directing, documentary, documentaryShort, filmEditing, foreignFilm, makeup, score, song, productionDesign, shortFilmAnim, shortFilm, soundEditing, soundMixing, visualEffects, writingAdap, writingOrig)  VALUES ('"+username+"', "+t.toString()+");";
			System.out.println(query);
			stmt.executeUpdate(query);
		}
	}
	
	public boolean isEmpty(String [] arr)
	{
		boolean empty = true;
		for (int i=0; i<arr.length; i++) 
		{
		  if (arr[i] != null) 
		  {
		    empty = false;
		    break;
		  }
		}
		return empty;
	}
	
	public ArrayList <String> getTips(String username) throws SQLException
	{
		if(username == "")
		{
			log.debug("username wasn't submitted");
			ArrayList <String> list = new ArrayList <String> ();
			for(int i=0; i<24; i++)
			{
				list.add("");
			}
			return list;
		}
		else
		{
			ArrayList <String> tips = new ArrayList <String> ();
			String query = "SELECT * FROM tipps WHERE username='"+username+"';";
			ResultSet r = stmt.executeQuery(query);
			if(r.next())
			{
				for(int i=2; i<=25; i++)
				{
					if(r.getString(i).equals(null))
					{
						tips.add("");
					}
					else
					{
						tips.add(r.getString(i));
						System.out.println("DB-Eintrag: "+r.getString(i));
					}
				}
			}
			return tips;
		}
	}
	
	public boolean findWinners() throws UnsupportedEncodingException, IOException
	{
		boolean success = false;
		ArrayList <ArrayList <String>> wins = new ArrayList <ArrayList <String>>();
		URL url = new URL("https://de.wikipedia.org/api/rest_v1/page/html/Oscarverleihung_2019");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Api-User-Agent", "pfeifferjenny89@gmail.com");
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		if(content.length() != 0) 
		{
			//System.out.println(content.toString());
			Document doc = Jsoup.parse(content.toString());
			
			//Best Picture
			Elements els = doc.select("[data-mw-section-id=\"2\"]");
			Elements noms = els.select("b");
			Elements tmp;
			ArrayList <String> a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);

			//Best Actor
			els = doc.select("[data-mw-section-id=\"4\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Best Actress
			els = doc.select("[data-mw-section-id=\"5\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Best ActorSupporting
			els = doc.select("[data-mw-section-id=\"6\"]");
			noms = els.select("b");
			System.out.println(noms);
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Best ActressSupporting
			els = doc.select("[data-mw-section-id=\"7\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Best Animated Feature
			els = doc.select("[data-mw-section-id=\"20\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			
			//Beste Kamera
			els = doc.select("[data-mw-section-id=\"10\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes Kostüm
			els = doc.select("[data-mw-section-id=\"12\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Beste Regie
			els = doc.select("[data-mw-section-id=\"3\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bester Dokumentarfilm
			els = doc.select("[data-mw-section-id=\"23\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bester Dokumentarfilm kurz
			els = doc.select("[data-mw-section-id=\"24\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes Filmediting
			els = doc.select("[data-mw-section-id=\"16\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bester fremdsprachiger Film
			els = doc.select("[data-mw-section-id=\"25\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes Makeup
			els = doc.select("[data-mw-section-id=\"15\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bester Soundtrack
			els = doc.select("[data-mw-section-id=\"13\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bester Song
			els = doc.select("[data-mw-section-id=\"14\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes Produktionsdesign
			els = doc.select("[data-mw-section-id=\"11\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bester anim Kurzfilm
			els = doc.select("[data-mw-section-id=\"21\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bester Kurzfilm
			els = doc.select("[data-mw-section-id=\"22\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes Soundediting
			els = doc.select("[data-mw-section-id=\"17\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes Soundmixing
			els = doc.select("[data-mw-section-id=\"18\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes adaptiertes Drehbuch
			els = doc.select("[data-mw-section-id=\"8\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Bestes original Drehbuch
			els = doc.select("[data-mw-section-id=\"9\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			//Beste Visual Effects
			els = doc.select("[data-mw-section-id=\"19\"]");
			noms = els.select("b");
			a = new ArrayList <String> ();
			tmp = noms.get(0).select("a");
			a.add(tmp.get(0).text());
			a.add(tmp.get(1).text());
			wins.add(a);
			
			con.disconnect();
			success = true;
			try 
			{
				setWinners(wins, true);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Fehler beim Befüllen der Datanbank: "+e);
			}
		}
		else
		{
			System.out.println("Dokument leer");
			con.disconnect();
		}
		
		return success;
	}
	
	public boolean rateTipps() throws SQLException
	{
		//Punktezahl für einen User
		int p = 0;
		//String für Username
		String user = "";
		//Winners auslesen
		ArrayList <ArrayList <String>> tmp = new ArrayList <ArrayList<String>>();
		//Falls Winners noch nicht befüllt, Winners aus DB befüllen
		if(winners.size()==0)
		{
			String query = "SELECT * FROM winners;";
			try
			{
				ResultSet r = stmt.executeQuery(query);
				int i = 1;
				while(r.next())
				{
					String a = r.getString(i).substring(r.getString(i).lastIndexOf("_")+1);
					String b = r.getString(i).substring(0, r.getString(i).lastIndexOf("_")-1);
					ArrayList <String> t = new ArrayList<String>();
					t.add(a);
					t.add(b);
					tmp.add(t);
					i++;
				}
				setWinners(tmp, false);
				log.info("Winners Größe: "+getWinners().size());
			
			}
			catch(Exception e)
			{
				log.info("winners konnten nicht ausgelesen werden.");
			}
			
		}
		
		//Tipps für alle User prüfen und Punkte in DB schreiben
		ArrayList <String> tips;
		String query = "SELECT * FROM tipps;";
		try
		{
			ResultSet r = stmt.executeQuery(query);
			ArrayList <ArrayList <String>> points = new ArrayList <ArrayList <String>> ();
			ArrayList <String> t = new ArrayList <String>();
			//iterieren durch alle Zeilen
			while(r.next())
			{
				//Punkte für neue Row und damit neuen User wieder auf 0 setzen
				p = 0;
				user = r.getString(1);
				log.info("User in Bearbeitung: "+user);
				//Iterieren durch alle Spalten der aktuellen Zeile
				tips = new ArrayList <String>();
				for(int j = 2; j<26; j++)
				{
					tips.add(r.getString(j));
				}
				log.info("Tipps Größe: "+tips.size());
				//Vergleichen mit Gewinnern und Punkte zählen
				for(int j=0; j<24; j++)
				{
					log.info("Tipps: "+tips.get(j));
					log.info("Winners: "+getWinners().get(j).get(0)+"_"+getWinners().get(j).get(1));
					if(tips.get(j).equals(getWinners().get(j).get(0)+"_"+getWinners().get(j).get(1)))
					{
						p++;
					}
				}
				t = new ArrayList <String>();
				t.add(user);
				t.add(String.valueOf(p));
				points.add(t);
			}
			for (int i =0; i<points.size(); i++)
			{
				String pointQuery = "UPDATE users SET points = "+points.get(i).get(1)+" WHERE username='"+points.get(i).get(0)+"';";
				stmt.executeUpdate(pointQuery);
				log.info("Es wurden "+points.get(i).get(1)+" Punkte für User "+points.get(i).get(0)+" eingetragen.");
			}

		}
		catch(Exception e)
		{
			log.info("Couldn't make call to db tipps: "+e);
		}
		finally
		{
			stmt.close();
		}
		
		return true;
	}


}

