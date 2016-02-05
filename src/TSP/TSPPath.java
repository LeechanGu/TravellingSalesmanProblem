package TSP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPPath {
	private List<TSPCity> cities;
	private TSPContext context;
	private double cacheDist;
	public TSPPath(List<TSPCity> cities, TSPContext context) throws Exception
	{
		if (!cities.get(0).equals(cities.get(cities.size()-1)))
			throw new Exception();
		this.cities = cities;
		this.context = context;
		updateDistance();
	}
	public List<TSPCity> getVisitedCities()
	{
		return cities;
	}
	
	public TSPPath cloneIt() throws Exception
	{
		List<TSPCity> list = new ArrayList<TSPCity>();
		list.addAll(cities);
		return new TSPPath(list,context);
	}
	
	public void swapVisitOrder(int i, int j) throws Exception
	{
		if (i<=0 || j>=cities.size()-1)	// the first and last can't be swapped
			throw new Exception();
		Collections.swap(cities, i, j);
		updateDistance();
	}
	
	private double calDistance()
	{
		double dist = 0;
		for (int i=0;i<cities.size()-1;i++)
		{
			dist+=cities.get(i).getDistanceTo(cities.get(i+1));
		}
		return dist;
	}
	
	private void updateDistance()
	{
		cacheDist = calDistance();
	}
	
	public double getDistance()
	{
		return cacheDist;
	}
}
