import javax.swing.JFrame;
import java.awt.Graphics;

import javax.swing.JComponent;


public class Answer {
	State endState;
	Question question;
	long cnt;
	long timeElapsed;
	Answer(Question q,State endState,long cnt,long timeElapsed)
	{
		this.question = q;
		this.endState = endState;
		this.cnt = cnt;
		this.timeElapsed = timeElapsed;
	}
	
	
	public void printAnswer()
	{
		State state = endState;
		System.out.println("Shortest path:");
		while (state.parent!=null)
		{
			System.out.print(state.location.name+" -> ");
			state = state.parent;
		}
		System.out.println(state.location.name);
		double totalDist = endState.backwardDistance;
		System.out.println("Distance: "+ totalDist+"");
		System.out.println("Cnt: "+cnt);
		System.out.println();
	}
	
	public void printStatistics()
	{
		System.out.println(question.num+" "+cnt+" "+timeElapsed);
	}
	
	public void drawAnswer()
	{
		AnswerGraph graph= new AnswerGraph(this);		
	}
}
