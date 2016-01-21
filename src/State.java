import java.util.HashSet;
import java.util.Set;

public class State
{
	Location first;
	Location location;
	Set<Location> remained;
	double backwardDistance;
	double heuristic;
	State parent;
	
	
	State()
	{
		remained = new HashSet<Location>();
		backwardDistance = 0;
		heuristic = 0;
		parent=null;
	}
	
	State(State prev, Location loc) throws Exception
	{
		first = prev.first;
		location = loc;
		remained = new HashSet<Location>(prev.remained);
		if (remained.contains(loc))
			remained.remove(loc);
		parent = prev;
		heuristic = getDistanceTo(first);
		backwardDistance = prev.getBackwardDistance() + prev.getDistanceTo(loc);				
	}	
	
	public double getHeuristic()
	{
		return heuristic;
	}
	
	
	public double getDistanceTo(Location loc)
	{
		int dx = (location.x-loc.x);
		int dy = (location.y-loc.y);
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public double getBackwardDistance()
	{
		return backwardDistance;
	}
	
	public double getFValue()
	{
		return getHeuristic()+getBackwardDistance();
	}
	
	public State getNextState(Location loc) throws Exception
	{
		if (!this.remained.contains(loc))
			throw new Exception();
		State next = new State(this,loc);
		return next;
	}
	
	public State getEndState() throws Exception
	{
		if (!this.remained.isEmpty())
			throw new Exception();
		State next = new State(this,this.first);
		return next;
	}
	
}