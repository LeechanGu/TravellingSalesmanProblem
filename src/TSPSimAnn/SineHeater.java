package TSPSimAnn;

public class SineHeater extends SimAnnHeater
{

	public SineHeater(long heatTimeSec, int numPeriod, long temperature) {
		super(heatTimeSec, numPeriod, temperature);
		// TODO Auto-generated constructor stub
	}

	public double getTemperature()
	{
		if (isStop()) return 0;
		return super.getMaxTemp()+super.getMaxTemp()*Math.sin((double)(super.getCurrentTime())*2*Math.PI/super.getPeriod());
	}
}