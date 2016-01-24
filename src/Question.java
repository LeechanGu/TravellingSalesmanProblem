import java.util.ArrayList;
import java.util.List;



public class Question
{
	String URL;
	int num;
	List<Location> list;
	
	Question(int num, String URL)
	{
		this.num=num;
		this.URL = URL;
		list = new ArrayList<Location>();
	}
	private void addLocation(Location location)
	{
		list.add(location);
	}

	public void addLocation(String line)
	{
		String[] parts = line.split(" ");
		addLocation(new Location(parts[0],Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));
	}

	public List<Location> getLocations()
	{
		return list;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Question [URL:"+URL+", num:"+num+"]\n");
		sb.append("Locations:");
		for (Location each:list)
		{
			sb.append(each);
		}
		return sb.toString();
	}
}
