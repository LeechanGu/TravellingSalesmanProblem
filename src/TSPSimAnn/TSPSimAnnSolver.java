package TSPSimAnn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import TSP.ITSPSolver;
import TSP.TSPCity;
import TSP.TSPContext;
import TSP.TSPPath;
import TSP.TSPQuestion;
import TSP.TSPSolution;
import TSP.TSPStatistics;

public class TSPSimAnnSolver implements ITSPSolver{
	
	
	SimAnnHeater heater;
	public TSPSimAnnSolver(SimAnnHeater heater)
	{
		this.heater = heater;
	}

	@Override
	public TSPSolution solve(TSPQuestion question) throws Exception {
		// randomly generate a configuration
		TSPContext context = new TSPContext(question);
		TSPPath path = getRandomTravelPath(context);
		
		Random random = new Random();
		//heater = new SineHeater(100000000,20,1000);
		//SimAnnHeater heater = new LinearHeater(10000000,1,200000);
		//SimAnnHeater heater = new RectangleHeater(1000000,10,20);
		double minLen = path.getDistance();
		TSPPath minPath = path;
		int cnt=0;
		while (!heater.isStop())
		{
			int rand1 = random.nextInt(path.getVisitedCities().size()-2)+1;
			int rand2 = random.nextInt(path.getVisitedCities().size()-2)+1;
			if (rand1==rand2) continue;
			double oldDist = path.getDistance();
			path.swapVisitOrder(rand1,rand2);
			double newDist = path.getDistance();
			double delta = newDist-oldDist;
			double temp = heater.getTemperature();
			double p = Math.exp(-delta/temp);//0.9;			
			if (delta<0 || random.nextDouble()<p)
			{
				// accept it
			}
			else
			{
				// reject it
				path.swapVisitOrder(rand1,rand2);	// swap back
			}
			double updatedDist = path.getDistance();
			if (minLen>updatedDist)
			{
				minLen = updatedDist;
				minPath = path.cloneIt();
				System.out.println(minLen);
			}
			//if ((cnt++)%100==0)
			//	System.out.println(minLen);

		}
		return new TSPSolution(question,minPath,new TSPStatistics());
	}
	
	private TSPPath getRandomTravelPath(TSPContext context) throws Exception
	{
		List<TSPCity> cities = new ArrayList<TSPCity>();
		cities.addAll(context.getAllCities());
		Random random = new Random();
		for (int i=2;i<cities.size();i++)
		{
			int rand1 = random.nextInt(i-1)+1;
			int rand2 = random.nextInt(i-1)+1;
			Collections.swap(cities, rand1, rand2);
		}
		cities.add(context.getFirstCity());
		TSPPath path = new TSPPath(cities, context);
		return path;
		
	}

}
