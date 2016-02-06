package TSP;
import java.util.List;

import TSPAStar.TSPAStarSolver;
import TSPSimAnn.LinearHeater;
import TSPSimAnn.RectangleHeater;
import TSPSimAnn.SineHeater;
import TSPSimAnn.TSPSimAnnSolver;
/**
 * The main method executing the whole program
 * @author Administrator
 *
 */
public class TSPMain {
	public static void main(String[] args) {
    	List<TSPQuestion> questionList = TSPQuestionLoader.readAllQuestionsFromFolder("C:\\Users\\Administrator\\Google ‘∆∂À”≤≈Ã\\Slides\\CS686\\A1\\problem\\randTSP\\");
    	for (TSPQuestion question:questionList)
    	{
    		if (question.num!=36)	// ignore the test case with more than 16 cities
    			continue;
    		System.out.println(question);
    		try {
    			//ITSPSolver solver = new TSPAStarSolver();
    			ITSPSolver solver = new TSPSimAnnSolver(new RectangleHeater(10,10,50));
				TSPSolution solution = solver.solve(question);
				//solution.printStatistics();
				solution.printSolution();
				solution.toGraph();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}

}
