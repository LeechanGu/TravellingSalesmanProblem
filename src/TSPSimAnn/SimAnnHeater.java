package TSPSimAnn;

public abstract class SimAnnHeater {
	private double temperature;
	private double heatTime;

	private int numPeriod;
	private long startTime;
	public SimAnnHeater(double heatTime, int numPeriod,double temperature)
	{
		reset();
		this.heatTime = heatTime*1000;
		this.temperature = temperature;
		this.numPeriod = numPeriod;
	}
	
	public int getNumPeriod()
	{
		return numPeriod;
	}
	
	public long getPeriod()
	{
		return (long) (heatTime/numPeriod);
	}
	
	public double getMaxTemp()
	{
		return temperature;
	}
	
	public long getCurrentTime()
	{
		return System.currentTimeMillis();
	}
	
	public long getTimeElapsed()
	{
		return getCurrentTime()-startTime;
	}
		
	
	public void reset()
	{
		startTime = getCurrentTime();
	}
	
	public boolean isStop()
	{
		return getCurrentTime()>=startTime+heatTime;
	}
	
	public abstract double getTemperature();
}
