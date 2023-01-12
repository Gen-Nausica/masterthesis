package Masterarbeit.my_app;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;



public class User 
{
	private String username;
	private String password;
	private static final Logger log = LoggerFactory.getLogger(User.class);
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	private String points;
	
	
	public User() throws SQLException 
	{
		this.username = "";
		this.password = "";
		this.points = "";
		this.dataSource = new MysqlDataSource();
		dataSource.setUser("jenny");
		dataSource.setPassword("jenny");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("masterarbeit");
		this.conn = dataSource.getConnection();
		this.stmt = conn.createStatement();
	}


	public ArrayList <ArrayList <String>> getAllPoints() throws SQLException {
		String query = "Select * from users;";
		ArrayList <ArrayList <String>> ps = new ArrayList <ArrayList <String>>();
		ArrayList <String> s = new ArrayList <String>();
		try {
			ResultSet r = stmt.executeQuery(query);
			while(r.next())
			{
				s = new ArrayList <String>();
				s.add(r.getString(1));
				s.add(r.getString(4));
				ps.add(s);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(ps, new Comparator<ArrayList<String>>() {    
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	            return Integer.valueOf(o1.get(1)).compareTo(Integer.valueOf(o2.get(1)));
	        }               
		});
		Collections.reverse(ps);
		return ps;
	}


	public void setPoints(String points) {
		this.points = points;
	}


	public String getUsername() 
	{
		return username;
	}


	public void setUsername(String username) 
	{
		this.username = username;
	}


	public String getPassword() 
	{
		return password;
	}


	public void setPassword(String password) 
	{
		this.password = password;
	}

	
//encrypt password the user has entered with sha256 and randomly generated salt 	
	public boolean encryptP (String pw) 
	{
		if(pw == "") 
		{
			return false;
		}
		else
		{
			PasswordService ps = new DefaultPasswordService();
			String encrypted = ps.encryptPassword(pw);
			Locale.setDefault(Locale.ENGLISH);
			setPassword(encrypted);
			return true;
		}
	}
	
	public boolean createUser() throws Exception
	{
		if(getUsername() == "" || getPassword() == "")
		{
			log.info("username or password or salt aren't set yet.");
			return false;
		}
		else
		{
			String queryfirst = "SELECT * FROM users WHERE username='"+getUsername()+"';";
			ResultSet r =  stmt.executeQuery(queryfirst);
			if(r.next())
			{
				log.info("username already exists");
				return false;
			}
			String query = "INSERT INTO users (username,password, points) VALUES ('"+getUsername()+"','"+getPassword()+"', '0');";
			System.out.println(query);
			stmt.executeUpdate(query);
			return true;
		}
	}
	
	public void removeUser (String user) throws SQLException
	{
		if(user == "")
		{
			log.info("username wasn't submitted.");
		}
		else
		{
			String queryfirst = "SELECT * FROM users WHERE username='"+user+"';";
			ResultSet r =  stmt.executeQuery(queryfirst);
			if(r.next())
			{
				log.info("username exists");
				String query = "DELETE FROM users WHERE username='"+user+"';";
				stmt.executeUpdate(query);
				log.info("username "+user+"removed from table users");
				query = "DELETE FROM user_roles WHERE username='"+user+"';";
				stmt.executeUpdate(query);
				log.info("username "+user+"removed from table user_roles");
				query  = "DELETE FROM tipps WHERE username='"+user+"';";
				stmt.executeUpdate(query);
				log.info("username "+user+"removed from table tipps");
				log.info("user "+user+" globally removed.");
				
			}
			else
			{
				log.info("username didn't exist");
			}
		}
	}
	
	public ArrayList <String> getAllUsers() throws SQLException
	{
		ArrayList <String> users = new ArrayList <String>();
		String query = "SELECT * FROM users;";
		ResultSet r = stmt.executeQuery(query);
		while(r.next())
		{
			users.add(r.getString(1));
		}
		return users;
	}
}
