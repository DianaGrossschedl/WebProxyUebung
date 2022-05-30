import java.util.Objects;

public class WebPage {

    private String url;
    private String content;
    private int size;

    public WebPage(String url, String content) {
        this.url = url;
        this.content = content;
        this.size = content.length();
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "WebPages{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebPage webPages = (WebPage) o;
        return Objects.equals(url, webPages.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}