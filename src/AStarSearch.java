import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;

public class AStarSearch
{
	State initial;
	Question question;
	int cnt=1;
	public AStarSearch(Question q)
	{
		question = q;
		initial = getFistStateFromQuestion();
	}
	
	public State getFistStateFromQuestion()
	{		
		State first = new State();
		int cnt=0;
		for (Location loc:question.list)
		{
			if (cnt==0)
				first.location = loc;
			else
				first.remained.add(loc);
			cnt++;
		}  	
		first.first = first.location;
		return first;
	}
	
	
	public Answer getAnswer() throws Exception
	{
		State winState = getWinState();
		return new Answer(question,winState,cnt);
	}
	
	private State getWinState() throws Exception
	{
		PriorityQueue<State> queue = new PriorityQueue<State>(10, StateComparator);
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
		return current;
	}

	private static Comparator<State> StateComparator = new Comparator<State>(){
        
        @Override
        public int compare(State c1, State c2) {
            return (int) (c1.getFValue() - c2.getFValue());
        }
    };


}