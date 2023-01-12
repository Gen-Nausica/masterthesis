package Masterarbeit.my_app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class CreateDemoTable 
{
	private static final Logger log = LoggerFactory.getLogger(Sources.class);
	private MysqlDataSource dataSource;
	private Connection conn;
	private Statement stmt;
	
	public CreateDemoTable()
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
		createTable();
	}
	
	public void createTable()
	{
		System.out.println("Banana");
		String sql = "Truncate demoActors;";
		try
		{
			stmt.executeUpdate(sql);
			log.info("removed data from demoTable");
		}
		catch (Exception e)
		{
			log.error("Fehler aufgetreten: "+e);
		}
		sql = "LOAD DATA Local INFILE 'D:/eclipse-workspace/Data/demoActors.tsv' INTO TABLE demoActors fields terminated by '\\t' lines terminated by '\\n' ignore 1 lines (nconst, primaryName, birthYear, deathYear, primaryProfession, knownforTitles);";
		try
		{
			stmt.executeLargeUpdate(sql);
		}
		catch (Exception e)
		{
			log.error("Fehler aufgetreten: "+e);
		}
		log.info("successfully updated data");
	
	}
}
