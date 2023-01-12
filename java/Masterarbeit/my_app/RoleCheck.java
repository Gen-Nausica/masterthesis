package Masterarbeit.my_app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class RoleCheck 
{
	private String[] roles;
	private ArrayList <String> userRoles;
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	private ArrayList <String> userAdminRoles;
	
	public RoleCheck() throws SQLException
	{
		this.roles = new String[] {};
		this.userRoles = new ArrayList <String> ();
		this.userAdminRoles = new ArrayList <String> ();
		this.dataSource = new MysqlDataSource();
		dataSource.setUser("jenny");
		dataSource.setPassword("jenny");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("masterarbeit");
		this.conn = dataSource.getConnection();
		this.stmt = conn.createStatement();
		
	}

	public ArrayList <String> getUserAdminRoles() {
		return userAdminRoles;
	}

	public void setUserAdminRoles(ArrayList <String> userAdminRoles) {
		this.userAdminRoles = userAdminRoles;
	}

	public String[] getRoles() 
	{
		return roles;
	}

	public ArrayList <String> getUserRoles() 
	{
		return userRoles;
	}

	public void setRoles(String [] roles) 
	{
		this.roles = roles;
	}

	public void setUserRoles(ArrayList <String> userRoles) 
	{
		this.userRoles = userRoles;
	}
	
	public void findRoles() throws SQLException
	{
		String queryfirst = "SELECT * FROM group_credentials;";
		ResultSet r =  stmt.executeQuery(queryfirst);
		int i = 0;
		String[] ro = new String[] {};
		while(r.next())
		{
			ro[i] = r.getString("groupname");
			i++;
		}
		
		setRoles(ro);
	}
	
	public void findUserRoles(String user) throws SQLException
	{
		
		String query = "SELECT * FROM user_roles WHERE username = '"+user+"';";
		ResultSet r = stmt.executeQuery(query);
		int i = 0;
		while(r.next())
		{
			i++;
		}
		System.out.println("i:"+i);
		ArrayList <String> userRo = new ArrayList <String> ();
		ArrayList <String> userAd = new ArrayList <String> ();
		r.first();
		for(int j=0; j<i; j++)
		{
			if(r.getString("role_name").contains("Admin"))
			{
				userAd.add(r.getString("role_name"));
				r.next();
				continue;
			}
			else
			{
				userRo.add(r.getString("role_name"));
				r.next();
			}
		}
		setUserAdminRoles(userAd);
		setUserRoles(userRo);
	}
	
	
}
