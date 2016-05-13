import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by iamupen on 3/20/2016.
 */
public String readFromURL(String urlString){
    String text = "";
    try{
        URL obj = new URL(urlString);
        InputStream is = obj.openConnection().getInputStream();
        byte[] bytes = new byte[512];
		int bytesRead = is.read(bytes, 0, bytes.length);
		while (bytesRead != -1)
		{
			text += new String(bytes);
			bytesRead = is.read(bytes, 0, bytes.length);
		}
    }
    catch (Exception e){
        System.out.println(e);
    }
    return text;
}

public class readFromURL2(String url) {

    //String url = "https://en.wikipedia.org/wiki/" + topic;
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

}
