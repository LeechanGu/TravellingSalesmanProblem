package TSP;
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
	TSPPath path;
	TSPQuestion question;
	private TSPStatistics statistics;
	public TSPSolution(TSPQuestion q,TSPPath path,TSPStatistics statistics)
	{
		this.question = q;
		this.path = path;
		this.statistics = statistics;
	}
	
	
	public void printSolution()
	{
		System.out.println("Shortest path:");
		for (TSPCity city:path.getVisitedCities())
		{
			System.out.print(city.name+" -> ");
		}
		System.out.println();
		System.out.println("Cnt: "+statistics.getCounter());
		System.out.println("Dist: "+path.getDistance());
	}
	
	public TSPPath getPath()
	{
		return path;
	}
	
	public void printStatistics()
	{
		System.out.println(question.num+" "+statistics.getCounter()+" "+statistics.getTimeElapsed()+" "+path.getDistance());
	}
	
	public void toGraph()
	{
		TSPSolutionDrawer.drawTSPSolution(this);
	}
}
