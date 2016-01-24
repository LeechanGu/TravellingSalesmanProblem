
public class Location
{
	String name;
	int x;
	int y;
	Location(String name,int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "("+name+","+x+","+y+")";
	}
	
	public double getDistanceTo(Location loc)
	{
		int dx = loc.x-x;
		int dy = loc.y-y;
		return Math.sqrt(dx*dx+dy*dy);
	}
}