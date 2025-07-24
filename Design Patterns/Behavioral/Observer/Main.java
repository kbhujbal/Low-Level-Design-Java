public class Main {
    public static void main(String[] args) {
        NewsAgenecy agency = new NewsAgenecy();

        NewsChannel channel1 = new NewsChannel();
        NewsChannel channel2 = new NewsChannel();

        agency.addObserver(channel1);
        agency.addObserver(channel2);

        agency.setNews("Breaking News: Observer Pattern Implemented!");

        // Output:
        // NewsChannel received news update: Breaking News: Observer Pattern Implemented!
        // NewsChannel received news update: Breaking News: Observer Pattern Implemented!
    }
}
