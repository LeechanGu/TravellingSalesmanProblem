import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

/**
 *
 * 
 * @author Administrator
 *
 */
public class TSPSolution {
	List<TSPCity> cities;
	TSPQuestion question;
	private double dist;
	private TSPStatistics statistics;
	TSPSolution(TSPQuestion q,List<TSPCity> cities, double dist,TSPStatistics statistics)
	{
		this.question = q;
		this.cities = cities;
		this.statistics = statistics;
		this.dist = dist;
	}
	
	
	public void printSolution()
	{
		System.out.println("Shortest path:");
		for (TSPCity city:cities)
		{
			System.out.print(city.name+" -> ");
		}
		System.out.println("Cnt: "+statistics.getCounter());
		System.out.println();
	}
	
	public void printStatistics()
	{
		System.out.println(question.num+" "+statistics.getCounter()+" "+statistics.getTimeElapsed()+" "+dist);
	}
	
	public void toGraph()
	{
		TSPSolutionDrawer.drawTSPSolution(this);
	}
}
