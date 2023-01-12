package Masterarbeit.my_app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Group 
{
	private String groupname;
	private String grouppassword;
	private String groupadmin;
	private String username;
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	
	private static final Logger log = LoggerFactory.getLogger(User.class);

	
	public Group () throws SQLException
	{
		this.groupname = "";
		this.grouppassword = "";
		this.groupadmin = "";
		this.username = "";
		this.dataSource = new MysqlDataSource();
		dataSource.setUser("jenny");
		dataSource.setPassword("jenny");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("masterarbeit");
		this.conn = dataSource.getConnection();
		this.stmt = conn.createStatement();
	}

	public String getGroupname() 
	{
		return groupname;
	}

	public void setGroupname(String groupname) 
	{
		this.groupname = groupname;
	}

	public String getGrouppassword() 
	{
		return grouppassword;
	}

	public void setGrouppassword(String grouppassword) 
	{
		this.grouppassword = grouppassword;
	}

	public String getGroupadmin() 
	{
		return groupadmin;
	}

	public void setGroupadmin(String groupadmin) 
	{
		this.groupadmin = groupadmin;
	}
	public boolean createGroup() throws SQLException
	{
		if(getGroupname() == "" || getGrouppassword() == "" || getGroupadmin() == "")
		{
			log.info("groupname or password or admin aren't set yet.");
			return false;
		}
		else
		{
			String queryfirst = "SELECT * FROM group_credentials WHERE groupname='"+getGroupname()+"';";
			ResultSet r =  stmt.executeQuery(queryfirst);
			if(r.next())
			{
				log.info("groupname already exists");
				return false;
			}
			String query = "INSERT INTO group_credentials (groupname,grouppassword) VALUES ('"+getGroupname()+"','"+getGrouppassword()+"');";
			stmt.executeUpdate(query);
			String queryNext = "INSERT INTO user_roles (username, role_name) VALUES ('"+getGroupadmin()+"','"+getGroupname()+"');";
			stmt.executeUpdate(queryNext);
			String queryLast = "INSERT INTO user_roles (username, role_name) VALUES ('"+getGroupadmin()+"','"+getGroupname()+"Admin');";
			stmt.executeUpdate(queryLast);
			return true;
		}
	}
	
	public boolean deleteGroup(String groupname) throws SQLException
	{
		if(groupname == "")
		{
			return false;
		}
		else
		{
			String query = "DELETE from user_roles WHERE role_name= '"+groupname+"';";
			stmt.executeUpdate(query);
			query = "DELETE from group_credentials WHERE groupname = '"+groupname+"';";
			stmt.executeUpdate(query);
			query = "DELETE from user_roles WHERE role_name = '"+groupname+"Admin';";
			stmt.executeUpdate(query);
			return true;			
		}
	}
	
	public boolean addUserToGroup() throws SQLException
	{
		if(getUsername() == "" || getGroupname() == "")
		{
			log.info("No username or groupname specified");
			return false;
		}
		else
		{
			String queryfirst = "SELECT * FROM user_roles WHERE username='"+username+"' AND role_name='"+getGroupname()+"';";
			ResultSet r =  stmt.executeQuery(queryfirst);
			if(r.next())
			{
				log.info("user already has group.");
				return false;
			}
			if(checkGroupPassword(getGroupname(), getGrouppassword()))
			{
				System.out.println("username: "+getUsername()+", Groupname: "+getGroupname());
				String query = "INSERT INTO user_roles (username, role_name) VALUES ('"+getUsername()+"','"+getGroupname()+"');";
				stmt.executeUpdate(query);
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public boolean addUserToGroupAdmin() throws SQLException
	{
		if(getUsername() == "" || getGroupname() == "")
		{
			log.info("No username or groupname specified");
			return false;
		}
		else
		{
			String queryfirst = "SELECT * FROM user_roles WHERE username='"+username+"' AND role_name='"+getGroupname()+"';";
			ResultSet r =  stmt.executeQuery(queryfirst);
			if(r.next())
			{
				log.info("user already has group.");
				return false;
			}
			System.out.println("username: "+getUsername()+", Groupname: "+getGroupname());
			String query = "INSERT INTO user_roles (username, role_name) VALUES ('"+getUsername()+"','"+getGroupname()+"');";
			stmt.executeUpdate(query);
			return true;
		}
	}
	
	public boolean checkGroupPassword(String groupname, String pwd) throws SQLException
	{
		System.out.println("PWD: "+pwd);
		if(groupname == "" || pwd == "")
		{
			log.info("groupname or password can't be empty");
			return false;
		}
		else
		{
			String query = "SELECT grouppassword FROM group_credentials WHERE groupname ='"+groupname+"';";
			ResultSet r = stmt.executeQuery(query);
			r.next();
			System.out.println("Passwort in DB: "+r.getString(1));
			System.out.println("Eingegebenes PW: "+pwd);
			if(r.getString(1).equals(pwd))
			{
				System.out.println("true");
				return true;
			}
			else
			{
				System.out.println("falsches Passwort");
				return false;
			}
		}
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String [] getUsersOfGroup(String groupname) throws SQLException
	{
		if(groupname == "")
		{
			log.info("groupname wasn't submitted.");
		}
		String query = "Select * from user_roles where role_name ='"+groupname+"';";
		ResultSet r = stmt.executeQuery(query);
		int i=0;
		while(r.next())
		{
			i++;
		}
		String [] users = new String [i];
		r.first();
		for(int j=0; j<i; j++)
		{
			users[j] = r.getString("username");
			r.next();
		}
		return users;
	}
	
	public void removeUserFromGroup () throws SQLException
	{
		if(getUsername() == "")
		{
			log.debug("Username wasn't submitted");
		}
		String query = "Delete from user_roles where username = '"+getUsername()+"' and role_name='"+getGroupname()+"';";
		log.info("Deleting "+getUsername()+" from user_roles");
		stmt.executeUpdate(query); 
		log.info("deletion successful");
	}
	
	public ArrayList <String> getAllGroups() throws SQLException
	{
		ArrayList <String> groups = new ArrayList <String>();
		String query = "SELECT * FROM group_credentials;";
		ResultSet r = stmt.executeQuery(query);
		while(r.next())
		{
			groups.add(r.getString(1));
		}
		return groups;
		
	}
	
	public ArrayList <String> getUsersTipped(ArrayList <String> users) throws SQLException
	{
		ArrayList <String> user = new ArrayList <String>();
		for(int i=0; i<users.size(); i++)
		{
			String query = "SELECT username FROM Tipps WHERE username = '"+users.get(i)+"';";
			ResultSet r = stmt.executeQuery(query);
			if(r.next())
			{
				user.add(users.get(i));
			}
			
		}

		return user;
	}
	
}
