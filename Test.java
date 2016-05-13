import java.io.IOException;

/**
 * Created by iamupen on 3/19/2016.
 */
public class Test {

    public static void main(String args[]) throws IOException {

        /**
        FileCreator newFile = new FileCreator();
        try {
            newFile.createFile();
            System.out.println("Success");
        } catch (IOException e) {
            e.printStackTrace();
        }
         **/

        String [] names = {"War", "Michael Jackson", "Peace", "love", "programming", "war", "peace"};

        FileCreator wiki = new FileCreator();
        wiki.createFile("Michael Jackson");    }
}
