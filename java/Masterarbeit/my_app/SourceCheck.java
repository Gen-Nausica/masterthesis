package Masterarbeit.my_app;

public class SourceCheck implements Runnable {
	private volatile boolean stopWork;
	@Override
	public void run() {
		Sources s = new Sources();
		while(!stopWork)
		{
			s.getFileSource("name.basics.tsv.gz");
			stopWork = true;
		}

	}
	
	public void stop()
	{
		stopWork = true;
	}

}
