import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlLoader {

    public static WebPage loadWebPage(String url) throws UrlLoaderException{

        try {
            URL myUrl = new URL(url);
            String content = "";
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(myUrl.openStream()));

            while((line = br.readLine())!=null){
                content = content + line;
            }

            return new WebPage(url,content);

        } catch (MalformedURLException e) {
            throw new UrlLoaderException(e);
        } catch (IOException e){
            throw new UrlLoaderException(e);
        }

    }

}
