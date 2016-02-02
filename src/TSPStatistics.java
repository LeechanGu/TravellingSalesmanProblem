/**
 * Record the number of generated nodes, the time it takes to run the program
 * @author Administrator
 *
 */
public class TSPStatistics {
	private int counter;
	private long startTime;
	private long timeRecord;
	TSPStatistics()
	{
		counter = 0;
		timerReset();
	}
	
	public void addCounter(int num)
	{
		counter+=num;
	}
	
	public int getCounter()
	{
		return counter;
	}
	
	public void timerReset()
	{
		startTime = System.currentTimeMillis();
	}
	
	public void timerStop()
	{
		timeRecord = System.currentTimeMillis() - startTime;
	}
	
	public long getTimeElapsed()
	{
		return timeRecord;
	}
}
