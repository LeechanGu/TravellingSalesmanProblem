package TSPSimAnn;

public class RectangleHeater extends SimAnnHeater
{

	private double highTmp;
	private double lowTmp;
	
	public RectangleHeater(long heatTimeSec, int numPeriod, long temperature) {
		super(heatTimeSec, numPeriod, temperature);
		// TODO Auto-generated constructor stub
		highTmp = temperature;
		lowTmp = temperature/20;
	}
	

	public double getTemperature()
	{
		if (isStop()) return 0;
		long now = super.getTimeElapsed()%super.getPeriod();
		if (now<super.getPeriod()/2)
			return highTmp;
		else 
			return lowTmp;
	}
}