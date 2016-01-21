import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class QuestionLoader {
	public List<Question> readAllQuestionsFromFolder(String folderName)
    {
    	List<Question> questionSet = new LinkedList<>();
    	List<String> urls = listFilesForFolder(folderName);
    	for (String fileName:urls)
    	{
    		try {
    			Question newQues = readQuestionFromFile(fileName);
				questionSet.add(newQues);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return questionSet;
    }
    
    public void listFilesForFolder(final File folder, List<String> URLs) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry,URLs);
	        } else {
	        	URLs.add(fileEntry.getAbsolutePath());
	        }
	    }
	}
    
    public List<String> listFilesForFolder(String folderName) {
    	List<String> list = new LinkedList<String>();
    	listFilesForFolder(new File(folderName),list);
    	return list;
	}
    
    public Question readQuestionFromFile(String fileName) throws Exception 
    {
    	BufferedReader br;
    	Question q;
		br = new BufferedReader(new FileReader(fileName));
		q = new Question(Integer.parseInt(br.readLine()),fileName);
	    String line = br.readLine();
	    while (line != null) {
	    	q.addLocation(line);
	        line = br.readLine();
	    }

    	return q;
    	
    }
}
