import java.util.ArrayList;
import java.util.List;

public class NewsAgenecy implements Publisher {
    private List<Subscriber> subscribers = new ArrayList<>();
    private String news;

    @Override
    public void addObserver(Subscriber o) {
        // Subscriber.add(o);
    }

    @Override
    public void removeObserver(Subscriber o) {
        // Subscriber.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Subscriber o : subscribers) {
            o.update(news);
        }
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}
