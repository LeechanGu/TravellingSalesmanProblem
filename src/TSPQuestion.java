import java.util.ArrayList;
import java.util.List;


/**
 * TSPQuestion is using TSPQuestionLoader to be loaded from file, and solved by TSPSolver, generating a TSPSolution
 * 
 * @author Administrator
 *
 */
public class TSPQuestion
{
	String URL;
	int num;
	List<TSPCity> list;
	
	TSPQuestion(int num, String URL)
	{
		this.num=num;
		this.URL = URL;
		list = new ArrayList<TSPCity>();
	}
	private void addLocation(TSPCity location)
	{
		list.add(location);
	}

	public void addCity(String line)
	{
		String[] parts = line.split(" ");
		addLocation(new TSPCity(parts[0],Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));
	}

	public List<TSPCity> getCities()
	{
		return list;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Question [URL:"+URL+", num:"+num+"]\n");
		sb.append("Locations:");
		for (TSPCity each:list)
		{
			sb.append(each);
		}
		return sb.toString();
	}
}
