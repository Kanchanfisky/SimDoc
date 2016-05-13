/**
* Created by iamupen on 3/19/2016.
*/
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileCreator{



public String giveStringBack(String topic) {
    String url = "https://en.wikipedia.org/wiki/" + topic;
    URL obj = null;
    try {
        obj = new URL(url);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    HttpURLConnection con = null;
    try {
        con = (HttpURLConnection) obj.openConnection();
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        con.setRequestMethod("GET");
    } catch (ProtocolException e) {
        e.printStackTrace();
    }

    BufferedReader in = null;
    try {
        in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
    } catch (IOException e) {
        e.printStackTrace();
    }
    String inputLine;
    StringBuffer response = new StringBuffer();

    try {
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        in.close();
    } catch (IOException e) {
        e.printStackTrace();
    }


    String html = response.toString();
    Document doc = Jsoup.parse(html);
    String text = doc.body().text();

    return text;
}


    public void createFile(String whatToSearch) throws IOException {
        File file = new File(timeStampFileName()+ ".txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(giveStringBack(whatToSearch));
        out.close();


    }

    private String timeStampFileName(){
        String fileName = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss").format(new Date());
        return fileName;
    }



}