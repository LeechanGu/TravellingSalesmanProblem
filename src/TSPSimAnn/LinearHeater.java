package TSPSimAnn;

public class LinearHeater extends SimAnnHeater
{
	public LinearHeater(long heatTimeSec, int numPeriod, long temperature) {
		super(heatTimeSec, numPeriod, temperature);
		// TODO Auto-generated constructor stub
	}

	public double getTemperature()
	{
		if (isStop()) return 0;
		long time = super.getTimeElapsed()%super.getPeriod();
		return (-time*super.getMaxTemp()/super.getPeriod()+super.getMaxTemp());
	}
}
