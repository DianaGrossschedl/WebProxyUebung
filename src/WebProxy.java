import java.io.*;

public class WebProxy {

    private PageCache cache;
    private int numCacheHits;
    private int numCacheMisses;

    public WebProxy() {
        this.cache = new PageCache();
    }

    public WebProxy(PageCache cache) {
        this.cache = cache;
    }

    public WebPage fetch(String url) throws UrlLoaderException{

        WebPage returnWebPage = null;

        try{
            returnWebPage = cache.readFromCache(url);
            numCacheHits++;
        } catch (CacheMissException e){
            returnWebPage = UrlLoader.loadWebPage(url);
            cache.writeToCache(returnWebPage);
            numCacheMisses++;
        }

        return returnWebPage;
    }

    public String statsHits(){
        return "stats hits: " + numCacheHits;
    }

    public String statsMisses(){
        return "stats misses: " + numCacheMisses;
    }

    public boolean writePageCacheToFile(String pathToFile){

        try{
            File file = new File(pathToFile);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (String url:cache.getCache().keySet()
            ) {
                bw.write(url + ";" + cache.getCache().get(url).getContent());
                bw.newLine();
                bw.flush();
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
