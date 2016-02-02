import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.PriorityQueue;

public class TSPSolver
{
	/**
	 * Use A star Search to solve the TSP problem
	 * 
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public static TSPSolution solve(TSPQuestion question) throws Exception
	{
		TSPStatistics statistics = new TSPStatistics();
		
		TSPNode initial = new TSPNode(question);
		PriorityQueue<TSPNode> queue = new PriorityQueue<TSPNode>(600000, TSPNode.NodeComparator);
		queue.add(initial);
		statistics.addCounter(1); 
		TSPNode current = null;
		while (true)
		{
			current = queue.poll();	// get the node with the minimal f value
			List<TSPNode> nodeList = current.getSuccessors();
			statistics.addCounter(nodeList.size()); 
			if (nodeList.isEmpty())
				break;
			else
				queue.addAll(nodeList);
		}
		statistics.timerStop();		
		return new TSPSolution(question,current.getVisitedCities(),current.getBackDist(),statistics);
	}
	
}