import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PageCache {

    private HashMap<String, WebPage> cache;

    public PageCache() {
        this.cache = new HashMap<>();
    }

    public HashMap<String, WebPage> getCache() {
        return cache;
    }

    public WebPage readFromCache(String url) throws CacheMissException{

        if(cache.containsKey(url)){
            return cache.get(url);
        } else{
            throw new CacheMissException("URL ist im Cache nicht enthalten.");
        }

    }

    public void writeToCache(WebPage webPage){
        cache.put(webPage.getUrl(),webPage);
    }

    public void warmUp(String pathToUrls) throws UrlLoaderException {

        try(BufferedReader br = new BufferedReader(new FileReader(pathToUrls))){

            String lineUrl;

            while((lineUrl = br.readLine())!=null){

                WebPage webPage = UrlLoader.loadWebPage(lineUrl);
                writeToCache(webPage);

            }


        } catch (FileNotFoundException e){
            throw new UrlLoaderException(e);
        } catch (IOException e){
            throw new UrlLoaderException(e);
        }

    }


}
