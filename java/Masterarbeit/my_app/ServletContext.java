package Masterarbeit.my_app;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContext implements ServletContextListener 
{
	private Sources sources;

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

		sources = new Sources();
		if(!sources.checkExistence("name.basics.tsv.gz") || !sources.checkActuality("name.basics.tsv.gz"))
		{
			Runnable r = new SourceCheck();
			Thread t = new Thread(r);
			t.start();
		}
		new CreateDemoTable();

	}

	public void contextDestroyed(ServletContextEvent sce) {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Iterator i = threadSet.iterator();
		while(i.hasNext())
		{
			((SourceCheck) i.next()).stop();
		}
	}

}
