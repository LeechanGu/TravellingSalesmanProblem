package TSPAStar;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


import java.util.PriorityQueue;

import TSP.ITSPSolver;
import TSP.TSPContext;
import TSP.TSPQuestion;
import TSP.TSPSolution;
import TSP.TSPStatistics;

public class TSPAStarSolver implements ITSPSolver
{
	/**
	 * Use A star Search to solve the TSP problem
	 * 
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public TSPSolution solve(TSPQuestion question) throws Exception
	{
		TSPStatistics statistics = new TSPStatistics();
		
		TSPAStarNode initial = new TSPAStarNode(question);
		PriorityQueue<TSPAStarNode> queue = new PriorityQueue<TSPAStarNode>(600000, TSPAStarNode.NodeComparator);
		queue.add(initial);
		statistics.addCounter(1); 
		TSPAStarNode current = null;
		while (true)
		{
			current = queue.poll();	// get the node with the minimal f value
			List<TSPAStarNode> nodeList = current.getSuccessors();
			statistics.addCounter(nodeList.size()); 
			if (nodeList.isEmpty())
				break;
			else
				queue.addAll(nodeList);
		}
		statistics.timerStop();		
		return new TSPSolution(question,current.getPath(),statistics);
	}
	
}