import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class TSPQuestionLoader {
	private TSPQuestionLoader(){}
	
	public static List<TSPQuestion> readAllQuestionsFromFolder(String folderName)
    {
    	List<TSPQuestion> questionSet = new LinkedList<>();
    	List<String> urls = listFilesForFolder(folderName);
    	for (String fileName:urls)
    	{
    		try {
    			TSPQuestion newQues = loadSingleQuestion(fileName);
				questionSet.add(newQues);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return questionSet;
    }
    
    private static void listFilesForFolder(final File folder, List<String> URLs) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry,URLs);
	        } else {
	        	URLs.add(fileEntry.getAbsolutePath());
	        }
	    }
	}
    
    private static List<String> listFilesForFolder(String folderName) {
    	List<String> list = new LinkedList<String>();
    	listFilesForFolder(new File(folderName),list);
    	return list;
	}
    
    private static TSPQuestion loadSingleQuestion(String fileName) throws Exception 
    {
    	BufferedReader br;
    	TSPQuestion q;
		br = new BufferedReader(new FileReader(fileName));
		q = new TSPQuestion(Integer.parseInt(br.readLine()),fileName);
	    String line = br.readLine();
	    while (line != null) {
	    	q.addCity(line);
	        line = br.readLine();
	    }

    	return q;
    }
}
