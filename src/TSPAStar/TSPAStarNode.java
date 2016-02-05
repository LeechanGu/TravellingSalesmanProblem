package TSPAStar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import TSP.TSPCity;
import TSP.TSPContext;
import TSP.TSPPath;
import TSP.TSPQuestion;

/**
 * 
 * TSPNode is the unit of spanning for A* tree
 * 
 * @author Liquan Gu
 *
 */
public class TSPAStarNode
{
	private TSPCity city;
	private List<TSPCity> unvisited;
	private double backDist;
	private double heuristic;
	private TSPAStarNode parent;
	private TSPContext context;
	
	TSPAStarNode(TSPQuestion question)
	{
		unvisited = new ArrayList<TSPCity>();
		backDist = 0;
		heuristic = 0;
		parent=null;
		context = new TSPContext(question);

		// get the first in the list to be first, others put in unvisited set
		for (int i=0;i<context.getAllCities().size();i++)
		{
			TSPCity loc = context.getAllCities().get(i);
			if (i!=0)
				unvisited.add(loc);
		}  		
		city = context.getFirstCity();
	}
	
	public TSPCity getCurrentCity()
	{
		return city;
	}
	
	public TSPAStarNode getParentNode()
	{
		return parent;
	}
	
	public TSPPath getPath() throws Exception
	{
		List<TSPCity> cityList = new ArrayList<TSPCity>();
		TSPAStarNode node = this;
		while (node!=null)
		{
			cityList.add(0,node.getCurrentCity());
			node = node.getParentNode();
		}
		TSPPath path = new TSPPath(cityList, context);
		return path;
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
	TSPAStarNode(TSPAStarNode prev, TSPCity loc) throws Exception
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
	public List<TSPAStarNode> getSuccessors() throws Exception
	{
		List<TSPAStarNode> list = new LinkedList<TSPAStarNode>();

		if (!unvisited.isEmpty())
		{
			for (TSPCity eachLoc:unvisited)
			{
				list.add(generateChildByCity(eachLoc));
			}	
		}
		else
		{
			if (city== context.getFirstCity())
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
			return getDistanceTo( context.getFirstCity());
		double minTotal = distToClosestCity(city,unvisited);

			unvisited.add( context.getFirstCity());
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
			double len = context.distanceXY(loc,each);
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
		return context.distanceXY(this.city,loc);
	}
	
	
	private double getBackwardDistance()
	{
		return backDist;
	}
	
	private double getF()
	{
		return getHeuristic()+getBackwardDistance();
	}
	
	private TSPAStarNode generateChildByCity(TSPCity loc) throws Exception
	{
		if (!this.unvisited.contains(loc))
			throw new Exception();
		TSPAStarNode next = new TSPAStarNode(this,loc);
		return next;
	}
	
	private TSPAStarNode generateNodeOfReturningFirstCity() throws Exception
	{
		TSPAStarNode next = new TSPAStarNode(this, context.getFirstCity());
		return next;
	}
	
	/**
	 * The comparator is based on the F value of A* search 
	 */
	public static Comparator<TSPAStarNode> NodeComparator = new Comparator<TSPAStarNode>(){
        
        @Override
        public int compare(TSPAStarNode c1, TSPAStarNode c2) {
            return (int) (c1.getF() - c2.getF());
        }
    };
	
}