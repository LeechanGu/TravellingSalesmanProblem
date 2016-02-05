package TSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TSPContext {
	/**
	 * The first city must be recorded for the node to go back
	 */
	private TSPCity first;
	/**
	 *  We pre-compute the distances for each two cities. The indices of cities is mapped by locationIdMap 
	 */
	private double[][] dist;
	/**
	 * LocationIdMap map a TSPCity to the index used for distance calculation
	 */
	private HashMap<TSPCity, Integer> locationIdMap;
	private List<TSPCity> cityList;
	
	public List<TSPCity> getAllCities()
	{
		return cityList;
	}
	
	public TSPContext(TSPQuestion question)
	{
		// distance saved in matrix dist, indices saved in locationIdMap
		int n = question.num;
		cityList = question.list;
		dist = new double[n][n];
		for (int i=0;i<n;i++)
			for (int j=0;j<n;j++)
			{
				dist[i][j] = cityList.get(i).getDistanceTo(cityList.get(j));
				dist[j][i] = dist[i][j];
			}
		locationIdMap = new HashMap<>();
		for (int i=0;i<n;i++)
			locationIdMap.put(cityList.get(i), i);
		
		// get the first in the list to be first, others put in unvisited set
		for (int i=0;i<question.list.size();i++)
		{
			TSPCity loc = question.list.get(i);
			if (i==0)
				first = loc;
			else
				break;
		}  	
	}
	
	public TSPCity getFirstCity()
	{
		return first;
	}
	
	public double distanceXY(TSPCity x, TSPCity y)
	{
		return dist[locationIdMap.get(x)][locationIdMap.get(y)];
	}
}
