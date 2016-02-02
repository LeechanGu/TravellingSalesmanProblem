/**
 * Each TSPCity represents a city in TSP problem
 * @author Liquan Gu
 */
public class TSPCity
{
	String name;
	int x;
	int y;
	TSPCity(String name,int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "("+name+","+x+","+y+")";
	}
	
	public double getDistanceTo(TSPCity loc)
	{
		int dx = loc.x-x;
		int dy = loc.y-y;
		return Math.sqrt(dx*dx+dy*dy);
	}
}