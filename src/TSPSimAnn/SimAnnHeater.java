package TSPSimAnn;

public abstract class SimAnnHeater {
	private double temperature;
	private double heatTime;
	private int current;
	private int numPeriod;
	public SimAnnHeater(double heatTime, int numPeriod,double temperature)
	{
		reset();
		this.heatTime = heatTime;
		this.temperature = temperature;
		this.numPeriod = numPeriod;
	}
	
	public int getNumPeriod()
	{
		return numPeriod;
	}
	
	public int getPeriod()
	{
		return (int) (heatTime/numPeriod);
	}
	
	public double getMaxTemp()
	{
		return temperature;
	}
	
	public int getCurrentTime()
	{
		return current;
	}
	
	public void nextMoment() throws Exception
	{
		if (!isStop())
			current++;
		else
			throw new Exception();
	}
	
	
	public void reset()
	{
		//startTime = System.currentTimeMillis();
		current = 0;
	}
	
	public boolean isStop()
	{
		return current>=heatTime;
	}
	
	public abstract double getTemperature();
}
