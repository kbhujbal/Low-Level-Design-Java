public class NewsChannel implements Subscriber {
    private String news;

    @Override
    public void update(String news) {
        this.news = news;
        System.out.println("NewsChannel received news update: " + news);
    }

    public String getNews() {
        return news;
    }
}
