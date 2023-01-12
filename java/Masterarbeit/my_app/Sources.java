package Masterarbeit.my_app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.GZIPInputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Sources 
{
	private static final Logger log = LoggerFactory.getLogger(Sources.class);
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	private final String omdbKey = "703d8692";
	
	public Sources()
	{
		this.dataSource = new MysqlDataSource();
		dataSource.setUser("jenny");
		dataSource.setPassword("jenny");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("masterarbeit");
		try
		{
			this.conn = dataSource.getConnection();
			this.stmt = conn.createStatement();
		}
		catch(Exception e)
		{
			log.info("SQL-Exception occurred");
		}

	}
	
	public synchronized void getFileSource(String filename)
	{
		//save file on filesystem
		URL website;
		try {
			website = new URL("https://datasets.imdbws.com/"+filename);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("D:\\eclipse-workspace\\Data\\"+filename);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			gunzipIt(filename);
			insertIntoTable(filename.split(".gz")[0]);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//function for unzipping gzipped data
	public synchronized static void gunzipIt(String filename){

	    byte[] buffer = new byte[1024];

	    try{

	        GZIPInputStream gzis = new GZIPInputStream(new FileInputStream("D:\\eclipse-workspace\\Data\\"+filename));
	        filename = filename.split(".gz")[0];
	        FileOutputStream out = new FileOutputStream("D:\\eclipse-workspace\\Data\\"+filename);

	        int len;
	        while ((len = gzis.read(buffer)) > 0) {
	            out.write(buffer, 0, len);
	        }

	        gzis.close();
	        out.close();

	        System.out.println("Extracted " + filename);

	    } catch(IOException ex){
	        ex.printStackTrace();
	    }
	}
	
	public synchronized boolean checkExistence(String filename)
	{
		boolean exists = false;
		File f = new File("D:\\eclipse-workspace\\Data\\"+filename);
		if(f.exists()) { 
		    exists = true;
		    log.info("exists");
		}
		return exists;
	}
	
	public synchronized boolean checkActuality (String filename)
	{
		boolean topNotch = false;
		File f = new File("D:\\eclipse-workspace\\Data\\"+filename);
		Calendar date = Calendar.getInstance();
		long diff = date.getTimeInMillis() - f.lastModified();
		log.info("Last Modified: "+f.lastModified()+"currTime: "+(date.getTimeInMillis())+"Diff: "+diff);
		if(diff < 24 * 60 * 60 * 1000)
		{
			log.info("Topnotch sources");
			topNotch = true;
		}
		return topNotch;
	}
	
	public synchronized boolean insertAllIntoTable(String filename)
	{
		String sql = " INSERT INTO actors (nconst,primaryName,birthYear,deathYear,primaryProfession,knownForTitles) VALUES(?,?,?,?,?,?) ";
		boolean success = false;

		try { 
		        BufferedReader bReader = new BufferedReader(new FileReader("D:\\eclipse-workspace\\Data\\"+filename));
		        String line = ""; 
		        while (line != null) 
		        {
		        	line = bReader.readLine();
		            try {

		                if (line != null) 
		                {
		                    String[] array = line.split("\t");
		                    for(String result:array)
		                    {
		                         System.out.println(result);
				                 PreparedStatement ps = conn.prepareStatement(sql);
				                 ps.setString(1,array[0]);
				                 ps.setString(2,array[1]);
				                 ps.setString(3,array[2]);
				                 ps.setString(4,array[3]);
				                 ps.setString(5,array[4]);
				                 ps.setString(6,array[5]);
				                 ps.executeUpdate();
				                 ps. close();
				                 success = true;
		                    }
		                } 
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		            finally
		            {
		               if (bReader == null) 
		                {
		                    bReader.close();
		                }
		            }
		        }
		    } catch (FileNotFoundException ex) {
		        ex.printStackTrace();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		            
		return success;
	}
		
	public synchronized boolean insertIntoTable(String filename) throws IOException
	{
		boolean success = false;
		log.info("insertIntoTable");
	/*
	 * This strategy takes way too long, rather import file directly via mysql,
	 * see below
			String sql = " INSERT INTO actors (nconst,primaryName,birthYear,deathYear,primaryProfession,knownForTitles) VALUES(?,?,?,?,?,?) ";
			try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			    String line = lines.skip(9230678).findFirst().get();
			    try {
	
	                if (line != null) 
	                {
	                    String[] array = line.split("\t");
	                    for(String result:array)
	                    {
	                         System.out.println(result);
			                 PreparedStatement ps = conn.prepareStatement(sql);
			                 ps.setString(1,array[0]);
			                 ps.setString(2,array[1]);
			                 ps.setString(3,array[2]);
			                 ps.setString(4,array[3]);
			                 ps.setString(5,array[4]);
			                 ps.setString(6,array[5]);
			                 ps.executeUpdate();
			                 ps. close();
			                 success = true;
	                    }
	                } 
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
			}
	*/
	/*
	 * This strategy is supposed to be more efficient
	 * should still only be executed once per day and in not busy times	+ via separate Thread
	 */
		//Remove existing content
		String sql = "Truncate actors;";
		try
		{
			stmt.executeUpdate(sql);
			log.info("removed data from table");
		}
		catch (Exception e)
		{
			log.error("Fehler aufgetreten: "+e);
		}
		sql = "LOAD DATA Local INFILE 'D:/eclipse-workspace/Data/name.basics.tsv' INTO TABLE actors fields terminated by '\\t' lines terminated by '\\n' ignore 1 lines (nconst, primaryName, birthYear, deathYear, primaryProfession, knownforTitles);";
		try
		{
			stmt.executeLargeUpdate(sql);
			success = true;
		}
		catch (Exception e)
		{
			log.error("Fehler aufgetreten: "+e);
		}
		log.info("successfully updated data");
		return success;
	}
	
	public JSONObject getInformationMovie(String title, String category) throws MalformedURLException
	{
		//ArrayList <String> infos = new ArrayList <String>();
		JSONObject json = new JSONObject();
		String imdbID = getIDFilm(title);
		if(imdbID.contentEquals("empty"))
		{
			try 
			{
				json.put("content", "empty");
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				json.put("content", "ja");
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//do movie-stuff
			/*
			 * 1. get imdb-ID of movie and some infos
			 */
			title = title.split(" – ")[0];
			title = cleanString(title, true);
			log.info("Title: "+title);
			try
			{
				URL url = new URL("http://www.omdbapi.com/?apikey="+omdbKey+"&i="+imdbID);
				log.info("URL: "+url);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String input = readAll(in);
				json = new JSONObject(input);
				json.put("content", "ja");
				in.close();
			}
			catch(Exception e)
			{
				log.info("Exception: "+e);
			}
			try 
			{
				json.put("count", getAwardCount(imdbID, category));
			} 
			catch (JSONException e) 
			{
				log.error("Fehler beim Zählen der Awards (Film): "+e);
			}
			log.info(json.toString());
		}

		return json;
	}
	
	public JSONObject getInformationActor(String title, String category, String movie)
	{
		JSONObject json = new JSONObject();
		String imdbID = getIDActor(title, movie);
		log.info("getInformation Actor ID: "+imdbID);
		String primaryProf = "";
		if(imdbID.equals("empty"))
		{
			try 
			{
				json.put("content", "empty");
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				json.put("content", "ja");
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList <String> knownForTitles = new ArrayList <String>();
			String sql = "SELECT primaryProfession, knownForTitles from demoActors where nconst='"+imdbID+"';";
			//log.info("Query for Information Actor: "+sql);
			ArrayList <String> knownForTitlesID = new ArrayList <String>();
			JSONArray array = new JSONArray();
			try
			{
				//get primaryProfession and ids of known for Titles from Database
				ResultSet r = stmt.executeQuery(sql);
				if(r.next())
				{
					primaryProf = r.getString(1);
					String tmp = r.getString(2);
					String [] t = tmp.split(",");
				
					for(int i=0; i<t.length; i++)
					{
						knownForTitlesID.add(t[i]);
					}
				}
				//get names of titles from omdb
				for(int i=0; i<knownForTitlesID.size(); i++)
				{
					URL url = new URL("http://www.omdbapi.com/?apikey="+omdbKey+"&i="+knownForTitlesID.get(i));
					//log.info("Primary-Profession-URL: "+url);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
					String input = readAll(in);
					JSONObject titles = new JSONObject(input);
					//log.info(titles.getString("Title"));
					knownForTitles.add(titles.getString("Title"));
					in.close();
				}
				//write titles into JSONObject json with Key KnownForTitles > Name
				JSONObject cache = new JSONObject();
				for(int i =0; i<knownForTitles.size(); i++)
				{
					cache = new JSONObject();
					//log.info("Title: "+knownForTitles.get(i));
					cache.put("Name", knownForTitles.get(i));
					array.put(cache);
				}
				json.put("KnownForTitles", array);
				json.put("primaryProf",primaryProf);
				
			}
			catch(Exception e)
			{
				//log.error("Fehler beim Holen der Primary Profession aus DB: "+e);
				try 
				{
					JSONObject cache = new JSONObject();
					for(int i =0; i<knownForTitles.size(); i++)
					{
						cache = new JSONObject();
						//log.info("Title: "+knownForTitles.get(i));
						cache.put("Name", knownForTitles.get(i));
						array.put(cache);
					}
					json.put("KnownForTitles", array);
					json.put("primaryProf",primaryProf);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try 
			{
				json.put("count", getAwardCount(imdbID, category));
			}
			catch (JSONException e) 
			{
				log.error("Fehler beim Zählen der Awards (Actor): "+e);
			}
		}

		//log.info(json.toString());
		
		/*
		 * Get count of nominations of nominated movie
		 */
		String movieID = getIDFilm(movie);
		JSONArray nomCount = getAwardCount(movieID, "film");
		try {
			json.put("NomCount", nomCount);
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			log.error("Fehler beim Hinzufügen der Nominierungen");
		}
		JSONArray array = getNominations(movie);
		try {
			json.put("Nominations", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			log.error("Fehler beim hinzufügen des Arrays");
		}
		log.info("JSON_Object: "+json.toString());
		return json;
	}
	
	private JSONArray getAwardCount(String imdbID, String category)
	{
		JSONArray arrAwards = new JSONArray();
		try
		{
			//get count awards from imdb
			String resturl = "";
			if(category.contentEquals("film"))
			{
				resturl = "https://www.imdb.com/title/"+imdbID+"/awards?ref_=tt_awd";
			}
			else if(category.contentEquals("actor"))
			{
				resturl = "https://www.imdb.com/name/"+imdbID+"/awards?ref_=tt_awd";
			}
			System.out.println(resturl);
			URL url = new URL(resturl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
				Elements els = doc.select("div.desc");
				System.out.println("Els: "+els.size());
				String awards = "";
				String nominations = "";
				if(els.size()!=0)
				{
					String tmp = els.get(0).toString().split("Showing all ")[1];
					System.out.println("TMP: "+tmp);
					String [] t = new String[1];
					if(tmp.contains("1 win ")) {
						t = tmp.split(" win and ");
					}
					else
					{
						t = tmp.split(" wins and ");
					}
					System.out.println("t: "+t[1]);
					awards = t[0];
					if(t[1].contains("1 nomination")) {
						nominations = t[1].split(" nomination")[0];
					}
					else
					{
						nominations = t[1].split(" nominations")[0];
					}
					//log.info("Awards: "+awards);
					//log.info("Noms: "+nominations);
				}
				else
				{
					awards = "";
					nominations = "";
				}
				
				els = doc.select("img.poster");
				JSONObject awa = new JSONObject();
				awa.put("Awards", awards);
				JSONObject nomin = new JSONObject();
				nomin.put("Nominations", nominations);
				arrAwards.put(awa);
				arrAwards.put(nomin);
				if(els.size()==0)
				{
					arrAwards.put(new JSONObject().put("Image", "none"));
				}
				else
				{
					//log.info("Image: "+els.get(0).attr("src"));
					JSONObject img = new JSONObject();
					String src = els.attr("src");
					src = src.substring(0,src.indexOf("@")+3);
					src += ".jpg";
					//src = src.substring(0,88);
					img.put("Image", src);

					arrAwards.put(img);
				}
				//log.info("Image: "+arrAwards.getString(2));
				
			}
		}
		catch(Exception e)
		{
			log.info("Exception: "+e);
		}
		return arrAwards;
	}
	
	private static String readAll(Reader rd) throws IOException 
	{
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	 }
	
	public JSONObject getAwards (String title, String category, String movie)
	{
		JSONObject json = new JSONObject();
		String imdbID = "";
		if(category.equals("film"))
		{
			imdbID = getIDFilm(title);
		}
		else
		{
			imdbID = getIDActor(title, movie);
		}
		if(imdbID.contentEquals("empty"))
		{
			try 
			{
				json.put("content", "empty");
			} 
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				json.put("content", "ja");
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList <String> aw = new ArrayList <String>();
		try
		{	
			String resturl = "";
			if(category.equals("film"))
			{
				resturl = "https://www.imdb.com/title/"+imdbID+"/awards?ref_=tt_awd";
			}
			else if(category.equals("actor"))
			{
				resturl = "https://www.imdb.com/name/"+imdbID+"/awards?ref_=tt_awd";
			}
			//log.info("Awards-URL: "+resturl);
			//get awards from imdb
			URL url = new URL(resturl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
				Elements els = new Elements();
				if(category.equals("film"))
				{
					els = doc.select("td.title_award_outcome");
				}
				else if(category.equals("actor"))
				{
					els = doc.select("td.award_outcome");
				}
				System.out.println("Els: "+els.size());
				//iterate through elements
				for (int i=0; i<els.size(); i++)
				{
					//check, if winner or nomination
					if(els.get(i).select("b").text().equals("Winner"))
					{
						//if winner, add name of award to arraylist
						aw.add(els.get(i).select("span.award_category").text());
					}
				}
				//add contents of ArrayList to JSONArray
				JSONArray awa = new JSONArray();
				JSONObject ob = new JSONObject();
				for(int i=0; i<aw.size(); i++)
				{
					ob = new JSONObject();
					//log.info("Award: "+aw.get(i));
					ob.put("Name",aw.get(i));
					awa.put(ob);
				}
				//put jsonarray in jsonobject
				json.put("Awards", awa);
				
			}
		}
		catch(Exception e)
		{
			log.info("Exception: "+e);
		}
		
		return json;
	}
	
	public JSONArray getNominations (String movietitle)
	{
		JSONArray array = new JSONArray();
		//JSONObject json = new JSONObject();
		String imdbID = getIDFilm(movietitle);
		//array.put(json);
		ArrayList <String> aw = new ArrayList <String>();
		try
		{	
			String resturl = "https://www.imdb.com/title/"+imdbID+"/awards?ref_=tt_awd";
			//log.info("Awards-URL get Nominations: "+resturl);
			//get awards from imdb
			URL url = new URL(resturl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
				Elements els = doc.select("table.awards");
				//log.info("Elements-Size get Nominations: "+els.size());
				Element e = els.get(0);
				String awardYear = e.select("span.award_year").text();
				if(awardYear == "")
				{
					awardYear = "1";
				}
//				log.info("e content: "+e);
//				log.info("award_category: "+e.select("span.award_category").text());
//				log.info("award_year: "+e.select("span.award_year").text());
				//els = e.select("td.title_award_outcome");
				els = e.select("tr");
				for(int i=0; i< els.size(); i++)
				{
					Element tmp = els.get(i);
					log.info(tmp.toString());
					log.info(tmp.select("b").html());
					if(/*tmp.select("span.award_category").html().equals("Oscar") && */!tmp.select("td.title_award_outcome").select("b").html().equals("Winner") && (awardYear.equals("2019") || awardYear.equals("")))
					{
						log.info("im if");
						aw.add(tmp.select("td.award_description:not(a)").text());
						/*
						for(int j=0; j<el.size(); j++)
						{
							aw.add(el.get(j).text());
						}
						*/
						
					}
				}
				
				//add contents of ArrayList to JSONArray
				JSONObject ob = new JSONObject();
				for(int i=0; i<aw.size(); i++)
				{
					ob = new JSONObject();
					//log.info("Award-Titel: "+aw.get(i));
					ob.put("Name",aw.get(i));
					array.put(ob);
				}
				//put jsonarray in jsonobject
				//json.put("Nomination", array);
				
			}
		}
		catch(Exception e)
		{
			log.info("Exception: "+e);
		}
		//log.info(array.toString());
		return array;
	}
	
	public String getIDFilm (String title)
	{
		title = findOriginalTitle(title);
		//log.info("Title for ID: "+title);
		JSONObject json = new JSONObject();
		String imdbID = "";
		//check, if category is person or movie
		//do movie-stuff
		/*
		 * 1. get imdb-ID of movie
		 */
		title = title.split(" – ")[0];
		title = cleanString(title, false);
		log.info("title: "+title);
		try
		{
			URL url = new URL("http://www.omdbapi.com/?apikey="+omdbKey+"&t="+title);
			log.info("URL for ID-Finden: "+url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String input = readAll(in);
			json = new JSONObject(input);
			imdbID = json.get("imdbID").toString();
			in.close();

		}
		catch(Exception e)
		{
			log.info("Exception: "+e);
		}
		if(imdbID.equals(""))
		{
			imdbID = "empty";
		}
		return imdbID;
	}
	
	private String getIDActor (String title, String movie)
	{
		String imdbID = "";
		String movieID = getIDFilm(movie);

		//get imdb-id from database
		String query = "";
		if(!movieID.contentEquals("empty"))
		{
			query = "SELECT nconst FROM demoActors where primaryName='"+title+"' and knownForTitles like'%"+movieID+"%';";
		}
		else
		{
			query = "SELECT nconst FROM demoActors where primaryName='"+title+"';";
		}
		
		log.info("Query in getIDActor: "+query);
		try
		{
			ResultSet r = stmt.executeQuery(query);
			if(r.next())
			{
				imdbID = r.getString(1);
			}
		}
		catch(Exception e) 
		{
			log.error("Fehler beim Holen der Schauspieler-ID aus DB: "+e);
		}
		
		if(imdbID.equals(""))
		{
			query = "SELECT nconst FROM demoActors where primaryName='"+title+"';";
			try
			{
				ResultSet r = stmt.executeQuery(query);
				if(r.next())
				{
					imdbID = r.getString(1);
				}
			}
			catch(Exception e) 
			{
				log.error("Fehler beim Holen der Schauspieler-ID aus DB ohne KnownForTitles: "+e);
			}
		}
		if(imdbID.equals(""))
		{
			imdbID = "empty";
		}
		return imdbID;
	}
	
	private String findOriginalTitle (String title)
	{
		String titleOrig = "";
		String t = cleanString(title, true);
		log.info("Suche nach Title: "+t);
		try
		{
			URL url = new URL("https://de.wikipedia.org/api/rest_v1/page/html/"+t);
			log.info("Banana "+url);
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
				Elements els = doc.select("table.infobox");
				if(els.size()!=0)
				{
					String orig = els.get(0).attr("data-mw");
					//log.info("orig-String: "+orig);

					JSONObject j = new JSONObject(orig);
					//log.info("Json-Objekt: "+j);
					JSONArray tO = j.getJSONArray("parts");
					//log.info(tO.toString());
					JSONObject tOr = tO.getJSONObject(0).getJSONObject("template");
					tOr = tOr.getJSONObject("params");
					tOr = tOr.getJSONObject("Originaltitel");
					titleOrig = tOr.getString("wt");
				}
				else
				{
					if(t.equals("Bohemian_Rhapsody"))
					{
						url = new URL("https://de.wikipedia.org/api/rest_v1/page/html/"+t+"_(Film)");
					}
					else if(t.equals("Bao"))
					{
						url= new URL("https://de.wikipedia.org/api/rest_v1/page/html/"+t+"_(Kurzfilm)");
					}
					else
					{
						url = new URL("https://de.wikipedia.org/api/rest_v1/page/html/"+t+"_(2018)");
					}
					con = (HttpURLConnection) url.openConnection();
					con.setRequestProperty("Api-User-Agent", "pfeifferjenny89@gmail.com");
					con.setRequestMethod("GET");
					con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
					
					in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
					content = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
					    content.append(inputLine);
					}
					in.close();
					if(content.length() != 0) 
					{
						doc = Jsoup.parse(content.toString());
						els = doc.select("table.infobox");
						String orig = els.get(0).attr("data-mw");
						//log.info("orig-String: "+orig);

						JSONObject j = new JSONObject(orig);
						//log.info("Json-Objekt: "+j);
						JSONArray tO = j.getJSONArray("parts");
						//log.info(tO.toString());
						JSONObject tOr = tO.getJSONObject(0).getJSONObject("template");
						tOr = tOr.getJSONObject("params");
						tOr = tOr.getJSONObject("Originaltitel");
						titleOrig = tOr.getString("wt");
					}
				}

			}
		}
		catch(Exception e)
		{
			log.error("Exception im findOrigTitle: "+e);
		}
		//log.info("Originaltitel: "+titleOrig);
		if(titleOrig == "")
		{
			titleOrig = title;
		}
		if(titleOrig.equals("Werk ohne Autor"))
		{
			titleOrig = "Never look away";
		}
		if(titleOrig.equals("Zimna wojna"))
		{
			titleOrig = "Cold War – Der Breitengrad der Liebe";
		}
		if(title.contentEquals("Capernaum – Stadt der Hoffnung"))
		{
			titleOrig = "Capernaum";
		}
		if(title.equals("Mirai – Das Mädchen aus der Zukunft"))
		{
			titleOrig = "Mirai";
		}

		return titleOrig;
	}
	
	private String cleanString(String title, boolean symbol)
	{
		String cleanString = title;
		if(symbol) 
		{
			cleanString = cleanString.replace(" ", "_");
		}
		else
		{
			cleanString = cleanString.replace(" ", "+");
		}
		cleanString = cleanString.replace("?", "%3F");
		cleanString = cleanString.replace("ä", "%C3%A4");
		cleanString = cleanString.replace("–", "%E2%80%93");
		cleanString = cleanString.replace("’", "%E2%80%99");
		cleanString = cleanString.replace("ö", "%C3%B6");
		cleanString = cleanString.replace("ü", "%C3%BC");
		cleanString = cleanString.replace("ł","%C5%82");
		cleanString = cleanString.replace("Ł","%C5%81");
		cleanString = cleanString.replace("Ż","%C5%BB");
		
		return cleanString;
	}
}
