import java.util.ArrayList;

public class Minhash{

	private ArrayList<String> fileName = new ArrayList<String>();

	private ArayList<String> getFiles(ArrayList<String> files)
	{
		ArayList<String> docs = new ArrayList<String>();

		for(int i = 0; i < files.size(); i++)
		{
			Scanner read = new Scanner(new File(files.get(i)));
			docs.add(read.next());
			fileName.add(files.get(i));
		}

		return docs;
	}

}