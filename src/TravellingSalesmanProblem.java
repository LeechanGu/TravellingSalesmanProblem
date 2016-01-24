import java.util.List;

public class TravellingSalesmanProblem {

	public static void main(String[] args) {
		QuestionLoader loader = new QuestionLoader();
    	List<Question> questionList = loader.readAllQuestionsFromFolder("C:\\Users\\Administrator\\Google ÔÆ¶ËÓ²ÅÌ\\Slides\\CS686\\problem\\randTSP\\");
    	for (Question question:questionList)
    	{
    		AStarSearch search = new AStarSearch(question);
    		if (question.num>16)
    			continue;
    		//System.out.println(question);
    		try {
				Answer answer = search.getAnswer();
				answer.printStatistics();
				//answer.printAnswer();
				//answer.drawAnswer();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}

}
