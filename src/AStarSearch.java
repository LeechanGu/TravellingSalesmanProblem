import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.PriorityQueue;

public class AStarSearch
{
	Question question;
	long cnt;
	long startTime;
	long endTime;
	
	public AStarSearch(Question q)
	{
		question = q;
	}
	
	
	public Answer getAnswer() throws Exception
	{
		startTime = System.currentTimeMillis();
		State initial = new State(question);
		PriorityQueue<State> queue = new PriorityQueue<State>(600000, State.StateComparator);
		queue.add(initial);
		cnt = 1;
		State current = null;
		while (!queue.isEmpty())
		{
			current = queue.poll();
			//System.out.print(current.location.toString());
			if (current.remained.isEmpty())
				break;
			for (Location eachLoc:current.remained)
			{
				queue.add(current.getNextState(eachLoc));
				cnt++;
			}
		}
		
		current = current.getEndState();
		State winState = current;
		endTime = System.currentTimeMillis();
		return new Answer(question,winState,cnt, endTime-startTime);
	}
	
}