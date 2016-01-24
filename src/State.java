import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class State
{
	static Location first;
	static double[][] distance;
	static HashMap<Location, Integer> locationIdMap;
	
	Location location;
	//Set<Location> remained;
	List<Location> remained;
	double backwardDistance;
	double heuristic;
	State parent;

	
	State(Question question)
	{
		remained = new ArrayList<Location>();//new HashSet<Location>();
		backwardDistance = 0;
		heuristic = 0;
		parent=null;
		
		// distance
		int n = question.num;
		List<Location> locationList = question.list;
		distance = new double[n][n];
		for (int i=0;i<n;i++)
			for (int j=0;j<n;j++)
			{
				distance[i][j] = locationList.get(i).getDistanceTo(locationList.get(j));
				distance[j][i] = distance[i][j];
			}
		locationIdMap = new HashMap<>();
		for (int i=0;i<n;i++)
			locationIdMap.put(locationList.get(i), i);
		
		// first, location, remained
		int i=0;
		for (Location loc:question.list)
		{
			if (i==0)
				location = loc;
			else
				remained.add(loc);
			i++;
		}  	
		first = location;
		
	}
	
	State(State prev, Location loc) throws Exception
	{
		location = loc;
		remained = new ArrayList<>();//new HashSet<Location>();
		remained.addAll(prev.remained);
		if (remained.contains(loc))
			remained.remove(loc);
		parent = prev;
		heuristic = calHeuristic();
		backwardDistance = prev.getBackwardDistance() + prev.getDistanceTo(loc);				
	}	
	
	private double  calHeuristic() throws Exception
	{
		if (remained.isEmpty())
			return getDistanceTo(first);
		double minTotal = findClosest(location,remained);
		minTotal+=findClosest(first, remained);
		for (int i=0;i<remained.size()-1;i++)
		{
			Location each = remained.get(i);
			minTotal += findClosest(each, remained);
		}
		return minTotal;
	}
	
	private double findClosest(Location loc, List<Location> remained)
	{
		double minLen = Double.MAX_VALUE;
		for (Location each: remained)
		{
			if (loc==each) continue;
			double len = distanceXY(loc,each);
			if (minLen>len)
				minLen = len;
		}
		return minLen;
	}
	
	public double getHeuristic()
	{
		return heuristic;
	}	
	
	public double getDistanceTo(Location loc)
	{
		return distanceXY(this.location,loc);
	}
	
	public double distanceXY(Location x, Location y)
	{
		return distance[locationIdMap.get(x)][locationIdMap.get(y)];
	}
	
	public double getBackwardDistance()
	{
		return backwardDistance;
	}
	
	public double getFValue()
	{
		return getHeuristic()+getBackwardDistance();
	}
	
	public State getNextState(Location loc) throws Exception
	{
		if (!this.remained.contains(loc))
			throw new Exception();
		State next = new State(this,loc);
		return next;
	}
	
	public State getEndState() throws Exception
	{
		if (!this.remained.isEmpty())
			throw new Exception();
		State next = new State(this,State.first);
		return next;
	}
	
	public static Comparator<State> StateComparator = new Comparator<State>(){
        
        @Override
        public int compare(State c1, State c2) {
            return (int) (c1.getFValue() - c2.getFValue());
        }
    };
	
}