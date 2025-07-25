import java.util.ArrayList;
import java.util.List;

interface Component {
    void showPrice();
}

class Leaf implements Component {
    int price;
    String name;

    public Leaf(int price, String name){
        this.price = price;
        this.name = name;
    }

    @Override
    public void showPrice(){
        System.out.println(name + ":" + price);
    }
}

class Composite implements Component {
    String name;
    List<Component> components = new ArrayList<>();

    public Composite(String name){
        this.name = name;
    }

    public void addComponent(Component com){
        components.add(com);
    }

    @Override
    public void showPrice(){
        System.out.println(name);
        for (Component c : components) {
            c.showPrice();
        }
    }
}

public class CompositeTest {
    public static void main(String[] args){
        Component hd = new Leaf(4000, "HDD");
        Component monitor = new Leaf(8000, "monitor");
        Component mouse = new Leaf(500, "mouse");
        Component RAM = new Leaf(3000, "RAM");
        Component CPU = new Leaf(9000, "CPU");

        Composite ph = new Composite("peri");
        Composite cabinet = new Composite("Cabinet");
        Composite mb = new Composite("MotherBoard");
        Composite computer = new Composite("Computer");

        mb.addComponent(CPU);
        mb.addComponent(RAM);

        ph.addComponent(mouse);
        ph.addComponent(monitor);

        cabinet.addComponent(hd);
        cabinet.addComponent(mb);

        computer.addComponent(ph);
        computer.addComponent(cabinet);

        ph.showPrice();
    }
}
