
public class Location
{
	String name;
	int x;
	int y;
	int id;
	Location(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "("+name+","+x+","+y+")";
	}
	
}