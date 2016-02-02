import java.util.List;
/**
 * The main method executing the whole program
 * @author Administrator
 *
 */
public class TSPMain {
	public static void main(String[] args) {
    	List<TSPQuestion> questionList = TSPQuestionLoader.readAllQuestionsFromFolder("C:\\Users\\Administrator\\Google ÔÆ¶ËÓ²ÅÌ\\Slides\\CS686\\A1\\problem\\randTSP\\");
    	for (TSPQuestion question:questionList)
    	{
    		if (question.num>16)	// ignore the test case with more than 16 cities
    			continue;
    		System.out.println(question);
    		try {
				TSPSolution solution = TSPSolver.solve(question);
				//solution.printStatistics();
				solution.printSolution();
				solution.toGraph();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}

}
