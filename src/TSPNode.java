import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/**
 * 
 * TSPNode is the unit of spanning for A* tree
 * 
 * @author Liquan Gu
 *
 */
public class TSPNode
{
	/**
	 * The first city must be recorded for the node to go back
	 */
	static TSPCity first;
	/**
	 *  We pre-compute the distances for each two cities. The indices of cities is mapped by locationIdMap 
	 */
	static double[][] dist;
	/**
	 * LocationIdMap map a TSPCity to the index used for distance calculation
	 */
	static HashMap<TSPCity, Integer> locationIdMap;
	
	private TSPCity city;
	private List<TSPCity> unvisited;
	private double backDist;
	private double heuristic;
	private TSPNode parent;

	
	TSPNode(TSPQuestion question)
	{
		unvisited = new ArrayList<TSPCity>();
		backDist = 0;
		heuristic = 0;
		parent=null;
		
		// distance saved in matrix dist, indices saved in locationIdMap
		int n = question.num;
		List<TSPCity> cityList = question.list;
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
				city = loc;
			else
				unvisited.add(loc);
		}  	
		first = city;		
	}
	
	public TSPCity getCurrentCity()
	{
		return city;
	}
	
	public TSPNode getParentNode()
	{
		return parent;
	}
	
	public List<TSPCity> getVisitedCities()
	{
		List<TSPCity> cityList = new LinkedList<TSPCity>();
		TSPNode node = this;
		while (node!=null)
		{
			cityList.add(0,node.getCurrentCity());
			node = node.getParentNode();
		}
		return cityList;
	}
	
	public double getBackDist()
	{
		return backDist;
	}
	
	/**
	 * 
	 * @param prev
	 * @param loc
	 * @throws Exception
	 */
	TSPNode(TSPNode prev, TSPCity loc) throws Exception
	{
		city = loc;
		unvisited = new ArrayList<>();
		unvisited.addAll(prev.unvisited);
		if (unvisited.contains(loc))
			unvisited.remove(loc);
		parent = prev;
		heuristic = calHeuristic();
		backDist = prev.getBackwardDistance() + prev.getDistanceTo(loc);				
	}	
	
	/**
	 * When there are unvisited cities, return all of them as new nodes. When all cities have been visited, go back to the first node.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TSPNode> getSuccessors() throws Exception
	{
		List<TSPNode> list = new LinkedList<TSPNode>();

		if (!unvisited.isEmpty())
		{
			for (TSPCity eachLoc:unvisited)
			{
				list.add(generateChildByCity(eachLoc));
			}	
		}
		else
		{
			if (city==first)
				return list;
			else
				list.add(generateNodeOfReturningFirstCity());
		}
		return list;
	}
	
	/**
	 * Use the line from current city to closest nodes in unvisited and 
	 * the lines from each unvisited cities to their closest unvisited 
	 * cities plus the initial city to approximate the actual forward path length
	 * 
	 * @return
	 * @throws Exception
	 */
	private double  calHeuristic() throws Exception
	{
		if (unvisited.isEmpty())
			return getDistanceTo(first);
		double minTotal = distToClosestCity(city,unvisited);

			unvisited.add(first);
		for (int i=0;i<unvisited.size()-1;i++)
		{
			TSPCity each = unvisited.get(i);
			minTotal += distToClosestCity(each, unvisited);
		}
			unvisited.remove(unvisited.size()-1);
		return minTotal;
	}
	
	private double distToClosestCity(TSPCity loc, List<TSPCity> remained)
	{
		double minLen = Double.MAX_VALUE;
		for (TSPCity each: remained)
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
	
	public double getDistanceTo(TSPCity loc)
	{
		return distanceXY(this.city,loc);
	}
	
	public double distanceXY(TSPCity x, TSPCity y)
	{
		return dist[locationIdMap.get(x)][locationIdMap.get(y)];
	}
	
	private double getBackwardDistance()
	{
		return backDist;
	}
	
	private double getF()
	{
		return getHeuristic()+getBackwardDistance();
	}
	
	private TSPNode generateChildByCity(TSPCity loc) throws Exception
	{
		if (!this.unvisited.contains(loc))
			throw new Exception();
		TSPNode next = new TSPNode(this,loc);
		return next;
	}
	
	private TSPNode generateNodeOfReturningFirstCity() throws Exception
	{
		TSPNode next = new TSPNode(this,TSPNode.first);
		return next;
	}
	
	/**
	 * The comparator is based on the F value of A* search 
	 */
	public static Comparator<TSPNode> NodeComparator = new Comparator<TSPNode>(){
        
        @Override
        public int compare(TSPNode c1, TSPNode c2) {
            return (int) (c1.getF() - c2.getF());
        }
    };
	
}